package at.kaindorf.intro.pojos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Team {
    @Id
    private Long teamId;

    @ManyToMany
    @JoinTable(name = "player_taam",
        joinColumns = { @JoinColumn(name = "team_id")},
        inverseJoinColumns = { @JoinColumn(name = "player_id" )})
    private List<Player> players = new ArrayList<>();
}
