package net.notfab.lindsey.shared.rpc.services;

import net.lindseybot.discord.Message;

public interface FollowUpService {

    default String getRabbitName() {
        return "FollowUpService";
    }

    long sendMessage(long guildId, long channelId, Message message);

    long editMessage(long guildId, long channelId, long messageId, Message message);

    long editMessage(long interaction, Message message);

    void deleteMessage(long interaction);

    long reply(long interaction, Message message);

}
