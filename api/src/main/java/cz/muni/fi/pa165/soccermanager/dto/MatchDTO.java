package cz.muni.fi.pa165.soccermanager.dto;

import cz.muni.fi.pa165.soccermanager.entity.Match;
import cz.muni.fi.pa165.soccermanager.entity.Team;
import cz.muni.fi.pa165.soccermanager.enums.StadiumEnum;

import java.time.LocalDate;

/**
 * @author 456519 Filip Lux
 * @version 11/23/2017.
 */
public class MatchDTO {

    private long id;

    private LocalDate date;

    private StadiumEnum stadium;

    private Team homeTeam;

    private Team awayTeam;

    private boolean finished;

    private int homeTeamGoals;

    private int awayTeamGoals;

    public long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public int getAwayTeamGoals() { return awayTeamGoals; }

    public void setAwayTeamGoals(int awayTeamGoals) { this.awayTeamGoals = awayTeamGoals; }

    public int getHomeTeamGoals() { return homeTeamGoals; }

    public void setHomeTeamGoals(int homeTeamGoals) { this.homeTeamGoals = homeTeamGoals; }

    public Team getAwayTeam() { return awayTeam; }

    public void setAwayTeam(Team awayTeam) { this.awayTeam = awayTeam; }

    public Team getHomeTeam() { return homeTeam; }

    public void setHomeTeam(Team homeTeam) { this.homeTeam = homeTeam; }

    public LocalDate getDate() { return date; }

    public void setDate(LocalDate date) { this.date = date; }

    public StadiumEnum getStadium() { return stadium; }

    public void setStadium(StadiumEnum stadium) { this.stadium = stadium; }

    public boolean isFinished() { return finished; }

    public void setFinished(boolean finished) { this.finished = finished; }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Match)) return false;

        Match match= (Match) o;

        if (getHomeTeam() != match.getHomeTeam()) return false;
        if (getAwayTeam() != match.getAwayTeam()) return false;
        if (getDate() != match.getDate()) return false;
        if (isFinished() != match.isFinished()) return false;
        if (getStadium() != null ? !getStadium().equals(match.getStadium()) : match.getStadium() != null) return false;
        if (getHomeTeamGoals() != match.getHomeTeamGoals()) return false;
        return (getAwayTeamGoals() != match.getAwayTeamGoals());
    }

    @Override
    public int hashCode() {
        int result = getHomeTeam() != null ? getHomeTeam().hashCode() : 0;
        result = 51 * result + (getAwayTeam() != null ? getAwayTeam().hashCode() : 0);
        result = 51 * result + (isFinished() ? 1 : 0);
        result = 51 * result + getHomeTeamGoals();
        result = 51 * result + getAwayTeamGoals();
        result = 51 * result + (getDate() != null ? getDate().hashCode() : 0);
        result = 51 * result + (getStadium() != null ? getStadium().hashCode() : 0);
        return result;
    }

}
