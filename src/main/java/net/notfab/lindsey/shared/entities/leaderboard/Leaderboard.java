package net.notfab.lindsey.shared.entities.leaderboard;

import lombok.Data;
import net.notfab.lindsey.shared.enums.LeaderboardType;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("Leaderboards")
public class Leaderboard {

    @Id
    private String id;

    private long user;
    private double count;
    private LeaderboardType type;

}
