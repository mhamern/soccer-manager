package cz.muni.fi.pa165.soccermanager.facade;

import cz.muni.fi.pa165.soccermanager.dto.CreateMatchDTO;
import cz.muni.fi.pa165.soccermanager.dto.MatchDTO;

import java.util.List;

/**
 * @author 456519 Filip Lux
 * @version 1/12/2017.
 */
public interface MatchFacade {
    /**
     * creates match
     * @param match instance od CreateMatchDTO
     * @return id of created Match
     */
    public Long createMatch(CreateMatchDTO match);

    /**
     * gets all matches played by specific team
     * @param teamId id of team
     * @return list of all matches played by the specified team
     */
    public List<MatchDTO> getMatchesByTeam(Long teamId);

    /**
     * gets all matches from a league
     * @param leagueId id of the league
     * @return list of all matches of the league
     */
    public List<MatchDTO> getMatchesByLeague(Long leagueId);

    /**
     * gets all finished matches
     * @return all matches already finished
     */
    public List<MatchDTO> getFinishedMatches();

    /**
     * finds match by match ID
     * @param id id of the match
     * @return match witch specified id
     */
    public MatchDTO getMatchById(Long id);

    /**
     * checks if the match was finished
     * @param matchId id of finished match
     * @return tru id the match was finished
     */
    public boolean isFinished(Long matchId);

    /**
     * send a request to play a match
     * @param matchId id of the requested match
     */
    public void play(Long matchId);

    /**
     * request to delete one match
     * @param matchId id of the requested match
     */
    public void deleteMatch(Long matchId);

}
