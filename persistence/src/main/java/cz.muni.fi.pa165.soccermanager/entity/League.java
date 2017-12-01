package cz.muni.fi.pa165.soccermanager.entity;

import cz.muni.fi.pa165.soccermanager.enums.NationalityEnum;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * @author 476368 Iman Mehmandoust
 * @version 10/27/2017.
 * Class representing leagues.
 * set of teams, country and name of the league
 * You should use LeagueBuilder to create instances of League.
 */
@Entity
public class League {

    public static class LeagueBuilder {


        private  String name;
        private NationalityEnum country;

        List matches = new ArrayList<>();


        public LeagueBuilder(String name, NationalityEnum country,List<Match> matches) {
            this.name = name;
            this.country = country;
            this.matches = matches;
        }

        public LeagueBuilder(String name, NationalityEnum country) {
            this.name = name;
            this.country = country;
        }

        public LeagueBuilder players(List<League> matches) {
            this.matches = matches;
            return this;
        }

        public LeagueBuilder name(String name) {
            this.name = name;
            return this;
        }

        public LeagueBuilder country(NationalityEnum country){
            this.country = country;
            return this;
        }

        public League build() {
            return new League(this);
        }

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column (nullable = false, unique = true)
    private String name;

    @Enumerated(EnumType.STRING)
    private NationalityEnum country;


    @OneToMany
    private List<Match> matches;


    public League() {
    }

    private League(LeagueBuilder builder){
        name = builder.name;
        country = builder.country;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) { this.id = id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public NationalityEnum getCountry() {
        return country;
    }

    public void setCountry(NationalityEnum country) {
        this.country = country;
    }

    public List<Match> getMatches() {
        return matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }

    public void addMatch(Match match) { matches.add(match); }

    public void removeMatch(Match match) { matches.remove(match); }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof League)) return false;

        League league = (League) o;


        if (matches != null ? !matches.equals(league.matches) : league.matches != null) return false;
        if (getName() != null ? !getName().equals(league.getName()) : league.getName() != null) return false;
        return getCountry() != null ? getCountry().equals(league.getCountry()) : league.getCountry() == null;
    }




    @Override
    public int hashCode() {
        int result = matches != null ? matches.hashCode() : 0;
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getCountry() != null ? getCountry().hashCode() : 0);
        return result;
    }


    @Override
    public String toString() {
        return "league {" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}