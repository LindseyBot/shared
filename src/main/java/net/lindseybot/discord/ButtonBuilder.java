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
     * Adds a small bit of custom data to a button.
     *
     * @param data Data to add.
     * @return Builder for chaining.
     */
    public ButtonBuilder withData(String data) {
        this.button.setData(data);
        return this;
    }

    /**
     * Sets an emote for this button.
     *
     * @param emote Emote.
     * @return Builder for chaining.
     */
    public ButtonBuilder withEmote(Emote emote) {
        this.button.setEmote(emote);
        return this;
    }

    /**
     * Forces this button to only work for a specific user.
     *
     * @param userId The user's id.
     * @return Builder for chaining.
     */
    public ButtonBuilder filter(long userId) {
        this.button.setUserFilter(userId);
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
     * @param url   Button's link.
     * @param label Button label.
     * @return Builder for chaining.
     */
    public ButtonBuilder link(String url, Message label) {
        this.button.setIdOrUrl(url);
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
