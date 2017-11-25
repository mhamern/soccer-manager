package cz.muni.fi.pa165.soccermanager.facade;

import cz.muni.fi.pa165.soccermanager.dto.CreateMatchDTO;
import cz.muni.fi.pa165.soccermanager.dto.MatchDTO;

import java.util.List;

/**
 * @author 445720 Martin Hamernik
 * @version 11/16/2017.
 */
public interface MatchFacade {
    public Long createMatch(CreateMatchDTO match);

    public List<MatchDTO> getMatchesByTeam(Long teamId);

    public List<MatchDTO> getMatchesByLeague(Long leagueId);

    public List<MatchDTO> getFinishedMatches();

    public MatchDTO getMatchById(Long id);

    public void addHomeTeam(Long matchId, Long teamId);

    public void addAwayTeam(Long matchId, Long teamId);

    public boolean isFinished(Long matchId);

    public void play(Long matchId);

}
