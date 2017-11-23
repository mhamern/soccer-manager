package cz.muni.fi.pa165.soccermanager.service;

import cz.muni.fi.pa165.soccermanager.entity.Match;
import cz.muni.fi.pa165.soccermanager.entity.Team;
import cz.muni.fi.pa165.soccermanager.enums.StadiumEnum;

import java.time.LocalDate;
import java.util.List;

/**
 * An interface that defines a service access to the Match entity
 * @author 456519 Filip Lux
 * @version 11/23/2017.
 */
public interface MatchService {
    Match findById(long matchId);
    List<Match> findAll();
    List<Match> findByDate(LocalDate date);
    List<Match> findByTeam(Team team);
    List<Match> findByStadium(StadiumEnum stadium);
    void create(Match match);
    void update(Match match);
    void remove(Long matchId);
}
