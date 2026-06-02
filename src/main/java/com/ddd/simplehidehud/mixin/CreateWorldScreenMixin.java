package com.ddd.simplehidehud.mixin;

import java.util.List;
import java.util.Random;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.worldselection.CreateWorldScreen;
import net.minecraft.client.gui.screens.worldselection.WorldCreationUiState;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.util.RandomSource;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;

@Mixin(CreateWorldScreen.class)
public abstract class CreateWorldScreenMixin extends Screen {
    @Unique
    private static final int SIMPLE_WORLD_CREATION_PREFERRED_WIDTH = 206;
    @Unique
    private static final int SIMPLE_WORLD_CREATION_MIN_WIDTH = 150;
    @Unique
    private static final int SIMPLE_WORLD_CREATION_SIDE_MARGIN = 20;
    @Unique
    private static final int SIMPLE_WORLD_CREATION_BUTTON_HEIGHT = 20;
    @Unique
    private static final List<String> SIMPLE_WORLD_CREATION_ADJECTIVES = List.of(
        "Ancient",
        "Silent",
        "Golden",
        "Hidden",
        "Frozen",
        "Wild",
        "Lonely",
        "Crimson",
        "Misty",
        "Endless"
    );
    @Unique
    private static final List<String> SIMPLE_WORLD_CREATION_FIRST_NOUNS = List.of(
        "Kingdom",
        "Valley",
        "Forest",
        "Harbor",
        "Mountain",
        "River",
        "Desert",
        "Island",
        "Cavern",
        "Meadow"
    );
    @Unique
    private static final List<String> SIMPLE_WORLD_CREATION_SECOND_NOUNS = List.of(
        "Stars",
        "Ashes",
        "Storms",
        "Dawn",
        "Echoes",
        "Dreams",
        "Shadows",
        "Flames",
        "Frost",
        "Stone"
    );

    @Shadow
    @Final
    private WorldCreationUiState uiState;

    @Unique
    private EditBox simpleWorldCreation$nameEdit;
    @Unique
    private EditBox simpleWorldCreation$seedEdit;
    @Unique
    private Button simpleWorldCreation$gameModeButton;
    @Unique
    private Button simpleWorldCreation$cheatsButton;
    @Unique
    private Button simpleWorldCreation$worldTypeButton;
    @Unique
    private Button simpleWorldCreation$createButton;

    protected CreateWorldScreenMixin(Component title) {
        super(title);
    }

    @Shadow
    private void onCreate() {
    }

    @Shadow
    public abstract void popScreen();

    /**
     * @author ddd
     * @reason Replaces the 1.21 tabbed create-world UI with a compact classic-style single-page layout.
     */
    @Overwrite
    protected void init() {
        SimpleWorldCreationLayout layout = this.simpleWorldCreation$layout();

        this.simpleWorldCreation$nameEdit = new EditBox(
            this.font,
            layout.left(),
            layout.nameY(),
            layout.editWidth(),
            layout.buttonHeight(),
            Component.translatable("selectWorld.enterName")
        );
        this.simpleWorldCreation$nameEdit.setValue(this.uiState.getName());
        this.simpleWorldCreation$nameEdit.setResponder(this.uiState::setName);
        this.addRenderableWidget(this.simpleWorldCreation$nameEdit);
        this.addRenderableWidget(Button.builder(Component.literal("*"), button -> {
            this.simpleWorldCreation$nameEdit.setValue(this.simpleWorldCreation$randomWorldName());
        }).bounds(layout.iconX(), layout.nameY(), layout.iconWidth(), layout.buttonHeight()).build());

        this.simpleWorldCreation$seedEdit = new EditBox(
            this.font,
            layout.left(),
            layout.seedY(),
            layout.editWidth(),
            layout.buttonHeight(),
            Component.translatable("selectWorld.enterSeed")
        );
        this.simpleWorldCreation$seedEdit.setHint(Component.literal(Long.toString(RandomSource.create().nextLong())).withStyle(ChatFormatting.DARK_GRAY));
        this.simpleWorldCreation$seedEdit.setValue(this.uiState.getSeed());
        this.simpleWorldCreation$seedEdit.setResponder(this.uiState::setSeed);
        this.addRenderableWidget(this.simpleWorldCreation$seedEdit);
        this.addRenderableWidget(Button.builder(Component.literal("*"), button -> {
            this.simpleWorldCreation$seedEdit.setValue(Long.toString(RandomSource.create().nextLong()));
        }).bounds(layout.iconX(), layout.seedY(), layout.iconWidth(), layout.buttonHeight()).build());

        this.simpleWorldCreation$gameModeButton = Button.builder(CommonComponents.EMPTY, button -> {
            this.uiState.setGameMode(switch (this.uiState.getGameMode()) {
                case SURVIVAL -> WorldCreationUiState.SelectedGameMode.HARDCORE;
                case HARDCORE -> WorldCreationUiState.SelectedGameMode.CREATIVE;
                case CREATIVE, DEBUG -> WorldCreationUiState.SelectedGameMode.SURVIVAL;
            });
        }).bounds(layout.left(), layout.optionsY(), layout.halfWidth(), layout.buttonHeight()).build();
        this.addRenderableWidget(this.simpleWorldCreation$gameModeButton);

        this.simpleWorldCreation$cheatsButton = Button.builder(CommonComponents.EMPTY, button -> this.uiState.setAllowCommands(!this.uiState.isAllowCommands()))
                .bounds(layout.secondColumnX(), layout.optionsY(), layout.halfWidth(), layout.buttonHeight())
                .build();
        this.addRenderableWidget(this.simpleWorldCreation$cheatsButton);

        this.simpleWorldCreation$worldTypeButton = Button.builder(CommonComponents.EMPTY, button -> this.simpleWorldCreation$cycleWorldType())
                .bounds(layout.left(), layout.worldTypeY(), layout.totalWidth(), layout.buttonHeight())
                .build();
        this.addRenderableWidget(this.simpleWorldCreation$worldTypeButton);

        this.simpleWorldCreation$createButton = Button.builder(Component.translatable("selectWorld.create"), button -> this.onCreate())
                .bounds(layout.left(), layout.createY(), layout.totalWidth(), layout.buttonHeight())
                .build();
        this.addRenderableWidget(this.simpleWorldCreation$createButton);

        this.addRenderableWidget(Button.builder(CommonComponents.GUI_CANCEL, button -> this.popScreen())
                .bounds(layout.left(), layout.cancelY(), layout.totalWidth(), layout.buttonHeight())
                .build());

        this.uiState.addListener(state -> this.simpleWorldCreation$refreshButtons());
        this.simpleWorldCreation$refreshButtons();
        this.setInitialFocus(this.simpleWorldCreation$nameEdit);
    }

    /**
     * @author ddd
     * @reason Keeps resizing simple by rebuilding the compact layout instead of arranging tab content.
     */
    @Overwrite
    public void repositionElements() {
        this.rebuildWidgets();
    }

    /**
     * @author ddd
     * @reason Removes tab navigation handling while preserving Enter-to-create behavior.
     */
    @Overwrite
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if (super.keyPressed(keyCode, scanCode, modifiers)) {
            return true;
        }

        if (keyCode == 257 || keyCode == 335) {
            this.onCreate();
            return true;
        }

        return false;
    }

    /**
     * @author ddd
     * @reason Renders labels and title for the classic single-page layout.
     */
    @Overwrite
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        super.render(guiGraphics, mouseX, mouseY, partialTick);

        SimpleWorldCreationLayout layout = this.simpleWorldCreation$layout();

        guiGraphics.drawString(this.font, Component.translatable("selectWorld.enterName"), layout.left(), layout.nameLabelY(), 0xA0A0A0);
        guiGraphics.drawString(this.font, Component.translatable("selectWorld.enterSeed"), layout.left(), layout.seedLabelY(), 0xA0A0A0);
    }

    /**
     * @author ddd
     * @reason Uses the normal menu background instead of the tabbed-screen header.
     */
    @Overwrite
    protected void renderMenuBackground(GuiGraphics guiGraphics) {
        this.renderMenuBackground(guiGraphics, 0, 0, this.width, this.height);
    }

    @Unique
    private void simpleWorldCreation$cycleWorldType() {
        List<WorldCreationUiState.WorldTypeEntry> presets = Screen.hasShiftDown() ? this.uiState.getAltPresetList() : this.uiState.getNormalPresetList();
        if (presets.isEmpty()) {
            return;
        }

        int nextIndex = presets.indexOf(this.uiState.getWorldType()) + 1;
        if (nextIndex <= 0 || nextIndex >= presets.size()) {
            nextIndex = 0;
        }

        this.uiState.setWorldType(presets.get(nextIndex));
    }

    @Unique
    private void simpleWorldCreation$refreshButtons() {
        if (this.simpleWorldCreation$gameModeButton != null) {
            this.simpleWorldCreation$gameModeButton.setMessage(Component.literal("Mode: ").append(this.uiState.getGameMode().displayName));
            this.simpleWorldCreation$gameModeButton.active = !this.uiState.isDebug();
        }

        if (this.simpleWorldCreation$cheatsButton != null) {
            this.simpleWorldCreation$cheatsButton.setMessage(Component.literal("Cheats: ").append(this.uiState.isAllowCommands() ? CommonComponents.OPTION_ON : CommonComponents.OPTION_OFF));
            this.simpleWorldCreation$cheatsButton.active = !this.uiState.isDebug() && !this.uiState.isHardcore();
        }

        if (this.simpleWorldCreation$worldTypeButton != null) {
            this.simpleWorldCreation$worldTypeButton.setMessage(Component.literal("World Type: ").append(this.uiState.getWorldType().describePreset()));
            this.simpleWorldCreation$worldTypeButton.active = !this.uiState.isDebug() && this.uiState.getWorldType().preset() != null;
        }

        if (this.simpleWorldCreation$createButton != null) {
            this.simpleWorldCreation$createButton.active = !this.uiState.getName().trim().isEmpty();
        }
    }

    @Unique
    private String simpleWorldCreation$randomWorldName() {
        Random random = new Random();
        String adjective = SIMPLE_WORLD_CREATION_ADJECTIVES.get(random.nextInt(SIMPLE_WORLD_CREATION_ADJECTIVES.size()));
        String firstNoun = SIMPLE_WORLD_CREATION_FIRST_NOUNS.get(random.nextInt(SIMPLE_WORLD_CREATION_FIRST_NOUNS.size()));
        String joiner = random.nextBoolean() ? "to the" : "of the";
        String secondNoun = SIMPLE_WORLD_CREATION_SECOND_NOUNS.get(random.nextInt(SIMPLE_WORLD_CREATION_SECOND_NOUNS.size()));
        return adjective + " " + firstNoun + " " + joiner + " " + secondNoun;
    }

    @Unique
    private SimpleWorldCreationLayout simpleWorldCreation$layout() {
        int maxWidth = Math.max(SIMPLE_WORLD_CREATION_MIN_WIDTH, this.width - SIMPLE_WORLD_CREATION_SIDE_MARGIN * 2);
        int totalWidth = Math.min(SIMPLE_WORLD_CREATION_PREFERRED_WIDTH, maxWidth);
        if (this.width >= 520) {
            totalWidth = Math.min(230, Math.max(totalWidth, this.width / 4));
        }

        boolean compactHeight = this.height < 280;
        int optionRowGap = compactHeight ? 22 : 24;
        int gap = optionRowGap - SIMPLE_WORLD_CREATION_BUTTON_HEIGHT;
        int iconWidth = totalWidth < 160 ? 18 : 22;
        int editWidth = Math.max(90, totalWidth - gap - iconWidth);
        int halfWidth = (totalWidth - gap) / 2;
        int titleToNameLabel = 0;
        int labelToInput = compactHeight ? 10 : 12;
        int fieldGap = compactHeight ? 36 : 50;
        int groupGap = compactHeight ? 28 : 32;
        int actionRowGap = compactHeight ? 22 : 24;

        int contentHeight = titleToNameLabel + labelToInput
                + fieldGap
                + optionRowGap
                + optionRowGap
                + groupGap
                + actionRowGap
                + SIMPLE_WORLD_CREATION_BUTTON_HEIGHT;
        int top = Math.max(10, (this.height - contentHeight) / 2);

        int nameLabelY = top + titleToNameLabel;
        int nameY = nameLabelY + labelToInput;
        int seedY = nameY + fieldGap;
        int optionsY = seedY + optionRowGap;
        int worldTypeY = optionsY + optionRowGap;
        int createY = worldTypeY + groupGap;
        int cancelY = createY + actionRowGap;
        int left = Math.max(4, (this.width - totalWidth) / 2);

        return new SimpleWorldCreationLayout(
                left,
                top,
                nameLabelY,
                nameY,
                seedY - labelToInput,
                seedY,
                optionsY,
                worldTypeY,
                createY,
                cancelY,
                totalWidth,
                editWidth,
                iconWidth,
                gap,
                halfWidth,
                SIMPLE_WORLD_CREATION_BUTTON_HEIGHT
        );
    }

    @Unique
    private record SimpleWorldCreationLayout(
            int left,
            int titleY,
            int nameLabelY,
            int nameY,
            int seedLabelY,
            int seedY,
            int optionsY,
            int worldTypeY,
            int createY,
            int cancelY,
            int totalWidth,
            int editWidth,
            int iconWidth,
            int gap,
            int halfWidth,
            int buttonHeight
    ) {
        int iconX() {
            return this.left + this.editWidth + this.gap;
        }

        int secondColumnX() {
            return this.left + this.halfWidth + this.gap;
        }
    }
}
