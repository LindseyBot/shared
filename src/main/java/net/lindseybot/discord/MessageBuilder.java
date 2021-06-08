package net.lindseybot.discord;

import net.lindseybot.enums.MentionType;

import java.util.Arrays;

public class MessageBuilder {

    private final Message message;

    public MessageBuilder() {
        this.message = new Message();
    }

    /**
     * Creates a MessageBuilder with a translated message as base.
     *
     * @param i18n I18N key.
     * @param args Arguments if any.
     */
    public MessageBuilder(String i18n, Object... args) {
        this.message = new Message();
        this.content(i18n);
        this.args(args);
    }

    /**
     * Updates the i18n key (or raw content) of this message.
     *
     * @param i18n I18N key or raw content.
     * @return Builder for chaining.
     */
    public MessageBuilder content(String i18n) {
        this.message.setName(i18n);
        return this;
    }

    /**
     * Updates the arguments for this message (only useful for translated messages).
     *
     * @param args List of arguments.
     * @return Builder for chaining.
     */
    public MessageBuilder args(Object... args) {
        this.message.setArgs(args);
        return this;
    }

    /**
     * Specifies the list of allowed mentions, used for allowing everyone/here mentions.
     *
     * @param types List of allowed mention types.
     * @return Builder for chaining.
     */
    public MessageBuilder allowedMentions(MentionType... types) {
        this.message.setAllowedMentions(Arrays.asList(types));
        return this;
    }

    /**
     * Updates the embed of this message. A message may contain an embed, or be just an embed.
     *
     * @param embed Embed. See {@link EmbedBuilder}.
     * @return Builder for chaining.
     */
    public MessageBuilder embed(Embed embed) {
        this.message.setEmbed(embed);
        return this;
    }

    /**
     * Specifies the list of components for this message.
     * See {@link ButtonBuilder}.
     *
     * @param components List of components.
     * @return Builder for chaining.
     */
    public MessageBuilder components(MessageComponent... components) {
        this.message.setComponents(Arrays.asList(components));
        return this;
    }

    /**
     * Adds a component to this message.
     * See {@link ButtonBuilder}.
     *
     * @param component The component to add.
     * @return Builder for chaining.
     */
    public MessageBuilder addComponent(MessageComponent component) {
        this.message.getComponents().add(component);
        return this;
    }

    /**
     * Marks this message as ephemeral, meaning it will only appear to the target user
     * and cannot be updated or deleted. This can only be used on interactions and will
     * be ignored if the meta does not support ephemeral messages.
     *
     * @return Builder for chaining.
     */
    public MessageBuilder ephemeral() {
        this.message.setEphemeral(true);
        return this;
    }

    /**
     * Builds this message.
     *
     * @return Message.
     */
    public Message build() {
        return this.message;
    }

}
