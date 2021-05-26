package net.lindseybot.discord;

import lombok.Data;
import net.lindseybot.enums.MentionType;

import java.util.ArrayList;
import java.util.List;

@Data
public class Message {

    private String name;
    private Object[] args;
    private boolean raw = false;

    private Embed embed;
    private List<MentionType> allowedMentions = new ArrayList<>();
    private List<MessageComponent> components = new ArrayList<>();

    /**
     * Starts a builder to create a message.
     *
     * @return A new MessageBuilder.
     */
    public static MessageBuilder builder() {
        return new MessageBuilder();
    }

    /**
     * Creates a translated message directly.
     *
     * @param i18n I18N key.
     * @param args Arguments if any.
     * @return Message.
     */
    public static Message of(String i18n, Object... args) {
        return new MessageBuilder(i18n, args).build();
    }

    /**
     * Creates a raw message (bypass i18n).
     *
     * @param msg Message content.
     * @return Message.
     */
    public static Message raw(String msg) {
        Message message = new Message();
        message.setName(msg);
        message.setRaw(true);
        return message;
    }

    /**
     * Creates a message from an embed.
     *
     * @param embed Embed.
     * @return Message.
     */
    public static Message of(Embed embed) {
        Message message = new Message();
        message.setEmbed(embed);
        return message;
    }

}
