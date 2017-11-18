package cz.muni.fi.pa165.soccermanager.entity;
import cz.muni.fi.pa165.soccermanager.enums.NationalityEnum;
import cz.muni.fi.pa165.soccermanager.enums.StadiumEnum;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author 445720 Martin Hamernik
 * @version 10/24/2017.
 * Class representing playable team. Each team has exactly one unique manager,
 * set of players playing for the team, nationality and name. Points, goals scored
 * and goals conceded which are valid for current league season and changed after every matchday.
 * You should use TeamBuilder to create instances of Team.
 */

@Entity
public class Team {

    public static class TeamBuilder {
        private NationalityEnum origin;
        private String name;
        private League league;
        private int points = 0;
        private int goalsScored = 0;
        private int goalsConceded = 0;
        private Manager manager;
        private StadiumEnum stadium;
        private Set<Player> players = new HashSet<>();

        public TeamBuilder(String name, NationalityEnum origin, StadiumEnum stadium, League league) {
            this.origin = origin;
            this.name = name;
            this.league = league;
            this.stadium = stadium;
        }

        public TeamBuilder points(int points) {
            this.points = points;
            return this;
        }

        public TeamBuilder goalsScored(int goalsScored) {
            this.goalsScored = goalsScored;
            return this;
        }

        public TeamBuilder goalsConceded(int goalsConceded) {
            this.goalsConceded = goalsConceded;
            return this;
        }

        public TeamBuilder manager(Manager manager) {
            this.manager = manager;
            return this;
        }

        public TeamBuilder players(Set<Player> players) {
            this.players = players;
            return this;
        }

        public TeamBuilder league(League league) {
            this.league = league;
            return this;
        }

        public TeamBuilder origin(NationalityEnum origin) {
            this.origin = origin;
            return this;
        }

        public TeamBuilder name(String name) {
            this.name = name;
            return this;
        }

        public TeamBuilder stadium(StadiumEnum stadium) {
            this.stadium = stadium;
            return this;
        }

        public Team build() {
            return new Team(this);
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private NationalityEnum origin;

    @Column(nullable = false,unique = true)
    private String name;

    @Column(nullable = false)
    private StadiumEnum stadium;


    private int points;
    private int goalsScored;
    private int goalsConceded;

    @OneToOne
    private Manager manager;

    @OneToMany
    private Set<Player> players;

    @ManyToOne
    private League league;


    public Team() {
    }

    private Team(TeamBuilder builder) {
        name = builder.name;
        players = builder.players;
        origin = builder.origin;
        manager = builder.manager;
        league = builder.league;
        points = builder.points;
        stadium = builder.stadium;
        goalsConceded = builder.goalsConceded;
        goalsScored = builder.goalsScored;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) { this.id = id; }

    public Set<Player> getPlayers() {
        return Collections.unmodifiableSet(players);
    }

    public void setPlayers(Set<Player> players) {
        this.players = players;
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

    public StadiumEnum getStadium() {
        return this.stadium;
    }

    public void setStaduim(StadiumEnum stadium) {
        this.stadium = stadium;
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

    public League getLeague() {
        return league;
    }

    public void setLeague(League league) {
        this.league = league;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public boolean removePlayer(Player player) {
        return players.remove(player);
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
                ", name='" + name + '\'' +
                "origin='" + origin + '\'' +
                ", manager=" + manager +
                '}';
    }
}
