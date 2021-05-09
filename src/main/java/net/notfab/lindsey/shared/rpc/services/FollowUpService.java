package net.notfab.lindsey.shared.rpc.services;

import net.lindseybot.discord.Message;

public interface FollowUpService {

    default String getRabbitName() {
        return "FollowUpService";
    }

    /**
     * Sends a message on a specific text channel.
     * <p>
     * This operation is ASYNC, validation errors will throw IllegalArgumentException,
     * execution errors will result in a expired interaction.
     *
     * @param guildId   Id of the guild.
     * @param channelId Id of the TextChannel.
     * @param message   Message contents.
     * @return Interaction id, used for follow-ups.
     */
    long sendMessage(long guildId, long channelId, Message message);

    /**
     * Updates a specific message, creating a new interaction.
     * <p>
     * This operation is ASYNC, validation errors will throw IllegalArgumentException,
     * execution errors will result in a expired interaction.
     *
     * @param guildId   Id of the guild.
     * @param channelId Id of the TextChannel.
     * @param messageId Id of the message.
     * @param message   Message contents.
     * @return Interaction id, used for follow-ups.
     */
    long editMessage(long guildId, long channelId, long messageId, Message message);

    /**
     * Updates a message by referencing the interaction.
     * <p>
     * This operation is ASYNC, validation errors will throw IllegalArgumentException,
     * execution errors will result in a expired interaction.
     * <p>
     * This operation has a max lifetime of 5 minutes after the initial interaction was created.
     *
     * @param interaction Interaction id.
     * @param message     Message contents.
     * @return The same interaction id as passed previously.
     */
    long editMessage(long interaction, Message message);

    /**
     * Deletes a message by referencing the interaction.
     * <p>
     * This operation is ASYNC, validation errors will throw IllegalArgumentException,
     * execution errors will result in a expired interaction.
     * <p>
     * This operation has a max lifetime of 5 minutes after the initial interaction was created.
     *
     * @param interaction Interaction id.
     */
    void deleteMessage(long interaction);

    /**
     * Replies to a message by referencing the interaction.
     * <p>
     * This operation is ASYNC, validation errors will throw IllegalArgumentException,
     * execution errors will result in a expired interaction.
     * <p>
     * This operation has a max lifetime of 5 minutes after the initial interaction was created.
     *
     * @param interaction Interaction id.
     * @param message     Message contents.
     * @param mention     If the original author should be mentioned.
     * @return New interaction id, corresponding to the reply.
     */
    long reply(long interaction, Message message, boolean mention);

}
