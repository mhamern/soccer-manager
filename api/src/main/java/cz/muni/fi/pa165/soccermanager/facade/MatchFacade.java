package cz.muni.fi.pa165.soccermanager.facade;

import cz.muni.fi.pa165.soccermanager.dto.MatchDTO;

import java.util.List;

/**
 * @author 445720 Martin Hamernik
 * @version 11/16/2017.
 */
public interface MatchFacade {
    public List<MatchDTO> getMatchesByTeam(Long teamId);
    public List<MatchDTO> getMatchesByLeague(Long leagueId);
    public List<MatchDTO> getFinishedMatches();
    public List<MatchDTO> getUnfinishedMatches();
    public MatchDTO getMatchById(Long id);
    public void addHomeTeam(Long matchId, Long teamId);
    public void addAwayTeam(Long matchId, Long teamId);
    public void play();

}
