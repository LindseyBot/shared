package net.lindseybot.discord;

import net.lindseybot.enums.DiscordButtonStyle;

public class ButtonBuilder {

    private final Button button;

    public ButtonBuilder() {
        this.button = new Button();
    }

    /**
     * Sets this button as disabled.
     *
     * @return Builder for chaining.
     */
    public ButtonBuilder disabled() {
        this.button.setDisabled(true);
        return this;
    }

    /**
     * Updates this button to be a primary button.
     *
     * @param id    Button ID.
     * @param label Button label.
     * @return Builder for chaining.
     */
    public ButtonBuilder primary(String id, Message label) {
        this.button.setIdOrUrl(id);
        this.button.setStyle(DiscordButtonStyle.PRIMARY);
        this.button.setLabel(label);
        return this;
    }

    /**
     * Updates this button to be a secondary button.
     *
     * @param id    Button ID.
     * @param label Button label.
     * @return Builder for chaining.
     */
    public ButtonBuilder secondary(String id, Message label) {
        this.button.setIdOrUrl(id);
        this.button.setStyle(DiscordButtonStyle.SECONDARY);
        this.button.setLabel(label);
        return this;
    }

    /**
     * Updates this button to be a success button.
     *
     * @param id    Button ID.
     * @param label Button label.
     * @return Builder for chaining.
     */
    public ButtonBuilder success(String id, Message label) {
        this.button.setIdOrUrl(id);
        this.button.setStyle(DiscordButtonStyle.SUCCESS);
        this.button.setLabel(label);
        return this;
    }

    /**
     * Updates this button to be a danger button.
     *
     * @param id    Button ID.
     * @param label Button label.
     * @return Builder for chaining.
     */
    public ButtonBuilder danger(String id, Message label) {
        this.button.setIdOrUrl(id);
        this.button.setStyle(DiscordButtonStyle.DANGER);
        this.button.setLabel(label);
        return this;
    }

    /**
     * Updates this button to be a link button.
     *
     * @param id    Button's link.
     * @param label Button label.
     * @return Builder for chaining.
     */
    public ButtonBuilder link(String id, Message label) {
        this.button.setIdOrUrl(id);
        this.button.setStyle(DiscordButtonStyle.LINK);
        this.button.setLabel(label);
        return this;
    }

    /**
     * Builds this button.
     *
     * @return Button.
     */
    public Button build() {
        if (this.button.getStyle() == null) {
            throw new IllegalArgumentException("Missing button style");
        }
        return this.button;
    }

}
