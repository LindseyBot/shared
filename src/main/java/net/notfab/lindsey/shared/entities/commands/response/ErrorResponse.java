package net.notfab.lindsey.shared.entities.commands.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.notfab.lindsey.shared.entities.commands.CommandResponse;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse implements CommandResponse {

    private String message;

}
