package cz.muni.fi.pa165.soccermanager.dto;
import cz.muni.fi.pa165.soccermanager.entity.League;
import cz.muni.fi.pa165.soccermanager.enums.NationalityEnum;

/**
 * @author 476368 Iman Mehmandoust
 * @version 11/27/2017.
 */
public class CreateLeagueDTO {

    private NationalityEnum country;
    private String name;



    public NationalityEnum getCountry() {
        return country;
    }

    public void setCountry(NationalityEnum country) {
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LeagueDTO)) return false;

        CreateLeagueDTO that = (CreateLeagueDTO) o;

        if (country != that.country) return false;
        return name == that.name;
    }

    @Override
    public int hashCode() {
        int result = country.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "CreateLeagueDTO{" +
                "country=" + country +
                ", name='" + name + '\'' +
                '}';
    }
}
