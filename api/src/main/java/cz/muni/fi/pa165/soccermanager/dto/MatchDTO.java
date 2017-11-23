package cz.muni.fi.pa165.soccermanager.dto;

import cz.muni.fi.pa165.soccermanager.entity.Match;
import cz.muni.fi.pa165.soccermanager.entity.Team;
import cz.muni.fi.pa165.soccermanager.enums.StadiumEnum;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

/**
 * @author 456519 Filip Lux
 * @version 11/23/2017.
 */
public class MatchDTO {

    private Long id;

    private LocalDate date;

    private TeamDTO homeTeam;

    private TeamDTO awayTeam;

    public TeamDTO getAwayTeam() { return awayTeam; }

    public void setAwayTeam(TeamDTO awayTeam) { this.awayTeam = awayTeam; }

    public TeamDTO getHomeTeam() { return homeTeam; }

    public void setHomeTeam(TeamDTO homeTeam) { this.homeTeam = homeTeam; }

    public LocalDate getDate() { return date; }

    public void setDate(LocalDate date) { this.date = date; }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MatchCreateDTO)) return false;

        MatchDTO match = (MatchDTO) o;

        if (getHomeTeam() != match.getHomeTeam()) return false;
        if (getAwayTeam() != match.getAwayTeam()) return false;
        return (getDate() != match.getDate());
    }


    @Override
    public int hashCode() {
        int result = getHomeTeam() != null ? getHomeTeam().hashCode() : 0;
        result = 51 * result + (getAwayTeam() != null ? getAwayTeam().hashCode() : 0);
        result = 51 * result + (getDate() != null ? getDate().hashCode() : 0);
        return result;
    }

}
