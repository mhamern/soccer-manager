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
    Match fetchById(long matchId);
    List<Match> fetchAll();
    List<Match> fetchByDate(LocalDate date);
    List<Match> fetchByTeam(Team team);
    List<Match> fetchByStadium(StadiumEnum stadium);
    List<Match> fetchFinished();
    Match createMatch(Match match);
    boolean isFinished(Match match);
    void updateMatch(Match match);
    void deleteMatch(Long matchId);
}
