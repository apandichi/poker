package scoring;

import player.Player;

import java.util.Collection;

public interface Scoring {
    Result getResult(Collection<? extends Player> players);
}
