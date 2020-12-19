package viceCity.core.interfaces;

import viceCity.common.ConstantMessages;
import viceCity.models.guns.Gun;
import viceCity.models.guns.Pistol;
import viceCity.models.guns.Rifle;
import viceCity.models.neighbourhood.GangNeighbourhood;
import viceCity.models.neighbourhood.Neighbourhood;
import viceCity.models.players.CivilPlayer;
import viceCity.models.players.MainPlayer;
import viceCity.models.players.Player;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;

public class ControllerImpl implements Controller {
    private Collection<Player> civilPlayers;
    private Deque<Gun> guns;
    private MainPlayer mainPlayer;
    private Neighbourhood neighbourhood;

    public ControllerImpl() {
        this.civilPlayers = new ArrayList<>();
        this.guns = new ArrayDeque<>();
        this.mainPlayer = new MainPlayer();
        this.neighbourhood = new GangNeighbourhood();
    }

    @Override
    public String addPlayer(String name) {
        Player player = new CivilPlayer(name);
        this.civilPlayers.add(player);

        return String.format(ConstantMessages.PLAYER_ADDED, name);
    }

    @Override
    public String addGun(String type, String name) {
        Gun gun = null;
        String result;

        if ("Pistol".equals(type)) {
            gun = new Pistol(name);
        } else if ("Rifle".equals(type)) {
            gun = new Rifle(name);
        }

        if (gun == null) {
            result = ConstantMessages.GUN_TYPE_INVALID;
        } else {
            result = String.format(ConstantMessages.GUN_ADDED, name, type);
            this.guns.offer(gun);
        }
        return result;
    }

    @Override
    public String addGunToPlayer(String name) {
        Gun gun = guns.peek();

        if (gun == null) {
            return ConstantMessages.GUN_QUEUE_IS_EMPTY;
        }
        if ("Vercetti".equals(name)) {
            gun = this.guns.poll();
            this.mainPlayer.getGunRepository().add(gun);

            return String.format(ConstantMessages.GUN_ADDED_TO_MAIN_PLAYER, gun.getName(), "Tommy Vercetti");
        }
        Player player = this.civilPlayers
                .stream()
                .filter(p -> p.getName().equals(name)).findFirst().orElse(null);

        if (player == null) {
            return ConstantMessages.CIVIL_PLAYER_DOES_NOT_EXIST;
        }

        gun = this.guns.poll();
        player.getGunRepository().add(gun);
        return String.format(ConstantMessages.GUN_ADDED_TO_CIVIL_PLAYER, gun.getName(), player.getName());
    }

    @Override
    public String fight() {
        this.neighbourhood.action(this.mainPlayer, this.civilPlayers);

        long count = civilPlayers.stream()
                .filter(p -> !p.isAlive()).count();

        StringBuilder sb = new StringBuilder();
        String line=System.lineSeparator();

        boolean allMatch = this.civilPlayers.stream()
                .allMatch(p -> p.getLifePoints() == 50);

        if (this.mainPlayer.getLifePoints() == 100 && allMatch) {
            sb.append(ConstantMessages.FIGHT_HOT_HAPPENED);
        } else {
            sb.append(ConstantMessages.FIGHT_HAPPENED).append(line);
            sb.append(String.format(ConstantMessages.MAIN_PLAYER_LIVE_POINTS_MESSAGE, this.mainPlayer.getLifePoints())).append(line);
            sb.append(String.format(ConstantMessages.MAIN_PLAYER_KILLED_CIVIL_PLAYERS_MESSAGE, count)).append(line);
            sb.append(String.format(ConstantMessages.CIVIL_PLAYERS_LEFT_MESSAGE, this.civilPlayers.size() - count));
        }

        return sb.toString().trim();
    }
}
