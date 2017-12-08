package cz.muni.fi.pa165.soccermanager.dto;
import cz.muni.fi.pa165.soccermanager.entity.Match;

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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MatchDTO)) return false;

        MatchDTO matchDTO = (MatchDTO) o;

        if (date != null ? !date.equals(matchDTO.date) : matchDTO.date != null) return false;
        if (homeTeam != null ? !homeTeam.equals(matchDTO.homeTeam) : matchDTO.homeTeam != null) return false;
        return awayTeam != null ? awayTeam.equals(matchDTO.awayTeam) : matchDTO.awayTeam == null;
    }

    @Override
    public int hashCode() {
        int result = date != null ? date.hashCode() : 0;
        result = 31 * result + (homeTeam != null ? homeTeam.hashCode() : 0);
        result = 31 * result + (awayTeam != null ? awayTeam.hashCode() : 0);
        return result;
    }
}
