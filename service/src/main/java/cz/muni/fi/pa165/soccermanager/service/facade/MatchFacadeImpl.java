package cz.muni.fi.pa165.soccermanager.service.facade;

import cz.muni.fi.pa165.soccermanager.dto.MatchDTO;
import cz.muni.fi.pa165.soccermanager.facade.MatchFacade;

import java.util.List;

/**
 * @author 456519 Filip Lux
 * @version 11/20/2017
 */
public class MatchFacadeImpl implements MatchFacade {
    @Override
    public List<MatchDTO> getMatchesByTeam(Long teamId) {
        return null;
    }

    @Override
    public List<MatchDTO> getMatchesByLeague(Long leagueId) {
        return null;
    }

    @Override
    public List<MatchDTO> getFinishedMatches() {
        return null;
    }

    @Override
    public List<MatchDTO> getUnfinishedMatches() {
        return null;
    }

    @Override
    public MatchDTO getMatchById(Long id) {
        return null;
    }

    @Override
    public void addHomeTeam(Long matchId, Long teamId) {

    }

    @Override
    public void addAwayTeam(Long matchId, Long teamId) {

    }

    @Override
    public boolean isFinished(Long matchId) {
        return false;
    }

    @Override
    public void play() {

    }
}
