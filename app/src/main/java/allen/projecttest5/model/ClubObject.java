package allen.projecttest5.model;

import java.util.List;

/**
 * Created by Allen on 04-May-16.
 */
public class ClubObject {


    private String name;
    private int count_member;

    private List<PlayerObject> players;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount_member() {
        return count_member;
    }

    public void setCount_member(int count_member) {
        this.count_member = count_member;
    }

    public List<PlayerObject> getPlayers() {
        return players;
    }

    public void setPlayers(List<PlayerObject> players) {
        this.players = players;
    }

}
