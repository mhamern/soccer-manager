package cz.muni.fi.pa165.soccermanager.dto;
import java.time.LocalDate;

/**
 * @author 456519 Filip Lux
 * @version 11/23/2017.
 */
public class CreateMatchDTO {

    private LocalDate date;

    private Long homeTeamId;

    private Long awayTeamId;

    private Long leagueId;

    public Long getHomeTeamId() {
        return homeTeamId;
    }

    public void setHomeTeamId(Long homeTeamId) {
        this.homeTeamId = homeTeamId;
    }

    public Long getAwayTeamId() {
        return awayTeamId;
    }

    public void setAwayTeamId(Long awayTeamId) {
        this.awayTeamId = awayTeamId;
    }

    public LocalDate getDate() { return date; }

    public void setDate(LocalDate date) { this.date = date; }

    public Long getLeagueId() { return leagueId;}

    public void setLeagueId(Long leagueId) { this.leagueId = leagueId; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CreateMatchDTO)) return false;

        CreateMatchDTO match= (CreateMatchDTO) o;

        if (getHomeTeamId() != match.getHomeTeamId()) return false;
        if (getAwayTeamId() != match.getAwayTeamId()) return false;
        return (getDate() != match.getDate());
    }

    @Override
    public int hashCode() {
        int result = getHomeTeamId() != null ? getHomeTeamId().hashCode() : 0;
        result = 51 * result + (getAwayTeamId() != null ? getAwayTeamId().hashCode() : 0);
        result = 51 * result + (getDate() != null ? getDate().hashCode() : 0);
        return result;
    }

}
