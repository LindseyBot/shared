package net.lindseybot.commands.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.lindseybot.discord.Message;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageResponse implements CommandResponse {

    private Message message;

}
