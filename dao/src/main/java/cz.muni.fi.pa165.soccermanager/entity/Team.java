package cz.muni.fi.pa165.soccermanager.entity;

import java.util.Collections;
import java.util.Set;

/**
 * @author 445720 Martin Hamernik
 * @version 10/24/2017.
 * Class representing playable team. Each team has exactly one unique manager,
 * set of players playing for the team, nationality and name. Points, goals scored
 * and goals conceded which are valid for current league season and changed after every matchday.
 */

public class Team {
    private Long id;
    private Set<Player> players;
    private String origin;
    private String name;
    private Manager manager;
    private int points;
    private int goalsScored;
    private int goalsConceded;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Player> getPlayers() {
        return Collections.unmodifiableSet(players);
    }

    public void setPlayers(Set<Player> players) {
        this.players = players;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Team team = (Team) o;

        if (players != null ? !players.equals(team.players) : team.players != null) return false;
        if (origin != null ? !origin.equals(team.origin) : team.origin != null) return false;
        if (name != null ? !name.equals(team.name) : team.name != null) return false;
        return manager != null ? manager.equals(team.manager) : team.manager == null;
    }

    @Override
    public int hashCode() {
        int result = players != null ? players.hashCode() : 0;
        result = 31 * result + (origin != null ? origin.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (manager != null ? manager.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Team{" +
                "origin='" + origin + '\'' +
                ", name='" + name + '\'' +
                ", manager=" + manager +
                '}';
    }
}
