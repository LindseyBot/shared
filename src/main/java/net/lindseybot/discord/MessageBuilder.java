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
     * Builds this message.
     *
     * @return Message.
     */
    public Message build() {
        return this.message;
    }

}
