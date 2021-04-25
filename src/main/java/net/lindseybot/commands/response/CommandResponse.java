package net.lindseybot.commands.response;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import net.lindseybot.commands.response.ErrorResponse;
import net.lindseybot.commands.response.InvalidResponse;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "@class")
public interface CommandResponse {

    default boolean isError() {
        return this instanceof ErrorResponse;
    }

    default boolean isInvalid() {
        return this instanceof InvalidResponse;
    }

}
