package cz.muni.fi.pa165.soccermanager.dto;
import cz.muni.fi.pa165.soccermanager.entity.League;
import cz.muni.fi.pa165.soccermanager.entity.Manager;
import cz.muni.fi.pa165.soccermanager.entity.Player;
import cz.muni.fi.pa165.soccermanager.enums.NationalityEnum;
import cz.muni.fi.pa165.soccermanager.enums.StadiumEnum;
import java.util.Set;

/**
 * @author 445720 Martin Hamernik
 * @version 11/16/2017.
 */
public class TeamDTO {

    private Long id;
    private NationalityEnum origin;
    private String name;
    private StadiumEnum stadiumEnum;
    private int points;
    private int goalsScored;
    private int goalsConceded;
    private League league;
    private Manager manager;
    private Set<Player> players;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public NationalityEnum getOrigin() {
        return origin;
    }

    public void setOrigin(NationalityEnum origin) {
        this.origin = origin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getGoalsScored() {
        return goalsScored;
    }

    public void setGoalsScored(int goalsScored) {
        this.goalsScored = goalsScored;
    }

    public int getGoalsConceded() {
        return goalsConceded;
    }

    public void setGoalsConceded(int goalsConceded) {
        this.goalsConceded = goalsConceded;
    }

    public StadiumEnum getStadiumEnum() {
        return stadiumEnum;
    }

    public void setStadiumEnum(StadiumEnum stadiumEnum) {
        this.stadiumEnum = stadiumEnum;
    }

    public League getLeague() {
        return league;
    }

    public void setLeague(League league) {
        this.league = league;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public Set<Player> getPlayers() {
        return players;
    }

    public void setPlayers(Set<Player> players) {
        this.players = players;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TeamDTO)) return false;

        TeamDTO teamDTO = (TeamDTO) o;

        if (origin != teamDTO.origin) return false;
        if (!name.equals(teamDTO.name)) return false;
        return stadiumEnum == teamDTO.stadiumEnum;
    }

    @Override
    public int hashCode() {
        int result = origin.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + stadiumEnum.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "TeamDTO{" +
                "origin=" + origin +
                ", name='" + name + '\'' +
                ", stadiumEnum=" + stadiumEnum +
                '}';
    }
}
