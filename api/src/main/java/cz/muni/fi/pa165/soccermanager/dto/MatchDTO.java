package cz.muni.fi.pa165.soccermanager.dto;
import cz.muni.fi.pa165.soccermanager.entity.Match;
import cz.muni.fi.pa165.soccermanager.enums.StadiumEnum;

import java.time.LocalDate;

/**
 * @author 456519 Filip Lux
 * @version 11/23/2017.
 */
public class MatchDTO {

    private Long id;

    private LocalDate date;

    private StadiumEnum stadium;

    private TeamDTO homeTeam;

    private TeamDTO awayTeam;

    private LeagueDTO league;

    private int homeTeamGoals;

    private int awayTeamGoals;

    private boolean finished;

    private boolean inPast;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TeamDTO getAwayTeam() { return awayTeam; }

    public void setAwayTeam(TeamDTO awayTeam) { this.awayTeam = awayTeam; }

    public TeamDTO getHomeTeam() { return homeTeam; }

    public void setHomeTeam(TeamDTO homeTeam) { this.homeTeam = homeTeam; }

    public LocalDate getDate() { return date; }

    public void setDate(LocalDate date) { this.date = date; }

    public int getHomeTeamGoals() { return homeTeamGoals; }

    public void setHomeTeamGoals(int homeTeamGoals) { this.homeTeamGoals = homeTeamGoals; }

    public int getAwayTeamGoals() { return awayTeamGoals; }

    public void setAwayTeamGoals(int awayTeamGoals) { this.awayTeamGoals = awayTeamGoals; }

    public boolean isFinished() { return finished; }

    public void setFinished(boolean finished) { this.finished = finished; }

    public StadiumEnum getStadium() { return stadium; }

    public void setStadium(StadiumEnum stadium) { this.stadium = stadium; }

    public LeagueDTO getLeague() { return league; }

    public void setLeague(LeagueDTO league) { this.league = league; }

    public boolean isInPast() { return date.isBefore(LocalDate.now()); }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MatchDTO)) return false;

        MatchDTO matchDTO = (MatchDTO) o;

        if (homeTeamGoals != matchDTO.homeTeamGoals) return false;
        if (awayTeamGoals != matchDTO.awayTeamGoals) return false;
        if (finished != matchDTO.finished) return false;
        if (date != null ? !date.equals(matchDTO.date) : matchDTO.date != null) return false;
        if (homeTeam != null ? !homeTeam.equals(matchDTO.homeTeam) : matchDTO.homeTeam != null) return false;
        return awayTeam != null ? awayTeam.equals(matchDTO.awayTeam) : matchDTO.awayTeam == null;
    }

    @Override
    public int hashCode() {
        int result = date != null ? date.hashCode() : 0;
        result = 31 * result + (homeTeam != null ? homeTeam.hashCode() : 0);
        result = 31 * result + (awayTeam != null ? awayTeam.hashCode() : 0);
        result = 31 * result + homeTeamGoals;
        result = 31 * result + awayTeamGoals;
        result = 31 * result + (finished ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "MatchDTO{" +
                "date=" + date +
                ", homeTeam=" + homeTeam +
                ", awayTeam=" + awayTeam +
                ", homeTeamGoals=" + homeTeamGoals +
                ", awayTeamGoals=" + awayTeamGoals +
                ", finished=" + finished +
                '}';
    }

    public String getBasicDescription() {
        return homeTeam.getName() + " vs. " + awayTeam.getName() + "; " + date.toString();
    }
}
