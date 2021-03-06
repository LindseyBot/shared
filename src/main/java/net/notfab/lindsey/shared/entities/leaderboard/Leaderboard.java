package net.notfab.lindsey.shared.entities.leaderboard;

import lombok.Data;
import net.notfab.lindsey.shared.enums.LeaderboardType;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Leaderboards")
public class Leaderboard {

    @Id
    private long id;

    private long user;
    private double count;

    @Enumerated(EnumType.STRING)
    private LeaderboardType type;

}
