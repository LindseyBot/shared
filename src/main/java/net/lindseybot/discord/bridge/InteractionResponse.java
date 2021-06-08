package net.lindseybot.discord.bridge;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class InteractionResponse {

    private InteractionData data;
    private List<Action> actions = new ArrayList<>();

    public void addAction(Action action) {
        this.actions.add(action);
    }

}
