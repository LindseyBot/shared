package net.lindseybot.discord.bridge;

import net.lindseybot.discord.Message;
import net.lindseybot.discord.bridge.actions.*;

import java.util.concurrent.TimeUnit;

public class InteractionResponseBuilder {

    private final InteractionResponse response;

    public InteractionResponseBuilder(InteractionData data) {
        this.response = new InteractionResponse();
        this.response.setData(data);
    }

    /**
     * Bans the specified user.
     *
     * @param user    Target user.
     * @param reason  Ban reason.
     * @param delDays Amount of days to delete messages (max 7).
     * @return Builder for chaining.
     */
    public InteractionResponseBuilder ban(long user, String reason, int delDays) {
        BanAction action = new BanAction();
        action.setTargetId(user);
        action.setReason(reason);
        action.setDelDays(delDays);
        this.response.addAction(action);
        return this;
    }

    /**
     * Kicks the specified user from the guild.
     *
     * @param user   Target user.
     * @param reason Kick reason.
     * @return Builder for chaining.
     */
    public InteractionResponseBuilder kick(long user, String reason) {
        KickAction action = new KickAction();
        action.setTargetId(user);
        action.setReason(reason);
        this.response.addAction(action);
        return this;
    }

    /**
     * Unbans the specified user from the guild.
     *
     * @param user Target user.
     * @return Builder for chaining.
     */
    public InteractionResponseBuilder unban(long user) {
        UnbanAction action = new UnbanAction();
        action.setTargetId(user);
        this.response.addAction(action);
        return this;
    }

    /**
     * Sends a message to the interaction channel.
     *
     * @param message Message to send.
     * @return Builder for chaining.
     */
    public InteractionResponseBuilder sendMessage(Message message) {
        MessageAction action = new MessageAction();
        action.setMessage(message);
        action.setChannelId(this.response.getData().getChannelId());
        this.response.addAction(action);
        return this;
    }

    /**
     * Sends a message to a specific channel.
     *
     * @param channelId Channel id.
     * @param message   Message to send.
     * @return Builder for chaining.
     */
    public InteractionResponseBuilder sendMessage(long channelId, Message message) {
        MessageAction action = new MessageAction();
        action.setMessage(message);
        action.setChannelId(channelId);
        this.response.addAction(action);
        return this;
    }

    /**
     * Edits a specific message from a channel.
     *
     * @param channelId Channel id.
     * @param targetId  Message id.
     * @param message   Message.
     * @return Builder for chaining.
     */
    public InteractionResponseBuilder editMessage(long channelId, long targetId, Message message) {
        MessageAction action = new MessageAction();
        action.setMessage(message);
        action.setEdit(true);
        action.setChannelId(channelId);
        action.setTargetId(targetId);
        this.response.addAction(action);
        return this;
    }

    /**
     * Edits the original message, only available for components.
     *
     * @param message Message content.
     * @return Builder for chaining.
     */
    public InteractionResponseBuilder editOriginal(Message message) {
        return this.editMessage(this.response.getData().getChannelId(), this.response.getData().getMessageId(), message);
    }

    /**
     * Deletes a message from a channel by id.
     *
     * @param channelId Id of the channel.
     * @param messageId Message id.
     * @return Builder for chaining.
     */
    public InteractionResponseBuilder deleteMessage(long channelId, long messageId) {
        DeleteMessageAction action = new DeleteMessageAction();
        action.setChannelId(channelId);
        action.setMessageId(messageId);
        this.response.addAction(action);
        return this;
    }

    /**
     * Deletes the original message, only available for components.
     *
     * @return Builder for chaining.
     */
    public InteractionResponseBuilder deleteOriginal() {
        return this.deleteMessage(this.response.getData().getChannelId(), this.response.getData().getMessageId());
    }

    /**
     * Waits a specified amount of time before the next action.
     *
     * @param time Time to wait.
     * @param unit Unit of time.
     * @return Builder for chaining.
     */
    public InteractionResponseBuilder wait(long time, TimeUnit unit) {
        WaitAction action = new WaitAction();
        action.setTime(unit.toMillis(time));
        this.response.addAction(action);
        return this;
    }

    /**
     * Adds a role to a user.
     *
     * @param roleId Id of the role.
     * @param userId Id of the target user.
     * @return Builder for chaining.
     */
    public InteractionResponseBuilder addRole(long roleId, long userId) {
        AddRoleAction action = new AddRoleAction();
        action.setRoleId(roleId);
        action.setUserId(userId);
        this.response.addAction(action);
        return this;
    }

    /**
     * Removes a role from a user.
     *
     * @param roleId Id of the role.
     * @param userId Id of the target user.
     * @return Builder for chaining.
     */
    public InteractionResponseBuilder removeRole(long roleId, long userId) {
        RemoveRoleAction action = new RemoveRoleAction();
        action.setRoleId(roleId);
        action.setUserId(userId);
        this.response.addAction(action);
        return this;
    }

    /**
     * @return InteractionResponse
     */
    public InteractionResponse build() {
        return this.response;
    }

}
