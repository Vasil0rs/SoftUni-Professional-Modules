package CounterStriker.repositories;

import CounterStriker.common.ExceptionMessages;
import CounterStriker.models.players.Player;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PlayerRepository implements Repository<Player> {
      private List<Player> players;

    public PlayerRepository() {
        this.players = new ArrayList<>();
    }

    @Override
    public Collection<Player> getModels() {
        return this.players;
    }

    @Override
    public void add(Player model) {
      if (model==null){
          throw new NullPointerException(ExceptionMessages.INVALID_PLAYER_REPOSITORY);
      }

        this.players.add(model);
    }

    @Override
    public boolean remove(Player model) {
        if (players.contains(model)) {
            return players.remove(model);
        }
        return false;
    }

    @Override
    public Player findByName(String name) {
        return players.stream().filter(p->p.getUsername().equals(name)).findFirst().orElse(null);
    }
}
