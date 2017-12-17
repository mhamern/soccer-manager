package cz.muni.fi.pa165.soccermanager.dto;
import java.time.LocalDate;

/**
 * @author 456519 Filip Lux
 * @version 11/23/2017.
 */
public class CreateMatchDTO {

    private LocalDate date;

    private String homeTeamName;

    private String awayTeamName;

    public String getHomeTeamName() {
        return homeTeamName;
    }

    public void setHomeTeamName(String homeTeamName) {
        this.homeTeamName = homeTeamName;
    }

    public String getAwayTeamName() {
        return awayTeamName;
    }

    public void setAwayTeamName(String awayTeamName) {
        this.awayTeamName = awayTeamName;
    }

    public LocalDate getDate() { return date; }

    public void setDate(LocalDate date) { this.date = date; }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CreateMatchDTO)) return false;

        CreateMatchDTO match= (CreateMatchDTO) o;

        if (getHomeTeamName() != match.getHomeTeamName()) return false;
        if (getAwayTeamName() != match.getAwayTeamName()) return false;
        return (getDate() != match.getDate());
    }

    @Override
    public int hashCode() {
        int result = getHomeTeamName() != null ? getHomeTeamName().hashCode() : 0;
        result = 51 * result + (getAwayTeamName() != null ? getAwayTeamName().hashCode() : 0);
        result = 51 * result + (getDate() != null ? getDate().hashCode() : 0);
        return result;
    }

    public String getBasicDescription() {
        return homeTeamName + " vs. " + awayTeamName + "; " + date.toString();
    }

}
