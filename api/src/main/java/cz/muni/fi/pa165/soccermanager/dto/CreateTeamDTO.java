package cz.muni.fi.pa165.soccermanager.dto;
import cz.muni.fi.pa165.soccermanager.entity.League;
import cz.muni.fi.pa165.soccermanager.entity.Team;
import cz.muni.fi.pa165.soccermanager.enums.NationalityEnum;
import cz.muni.fi.pa165.soccermanager.enums.StadiumEnum;

/**
 * @author 445720 Martin Hamernik
 * @version 11/24/2017.
 */
public class CreateTeamDTO {

    private NationalityEnum origin;
    private String name;
    private StadiumEnum stadium;
    private League league;

    public League getLeague() {
        return league;
    }

    public void setLeague(League league) {
        this.league = league;
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

    public StadiumEnum getStadium() {
        return stadium;
    }

    public void setStadium(StadiumEnum stadium) {
        this.stadium = stadium;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TeamDTO)) return false;

        CreateTeamDTO that = (CreateTeamDTO) o;

        if (origin != that.origin) return false;
        if (!name.equals(that.name)) return false;
        return stadium == that.stadium;
    }

    @Override
    public int hashCode() {
        int result = origin.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + stadium.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "CreateTeamDTO{" +
                "origin=" + origin +
                ", name='" + name + '\'' +
                ", stadium=" + stadium +
                '}';
    }
}
