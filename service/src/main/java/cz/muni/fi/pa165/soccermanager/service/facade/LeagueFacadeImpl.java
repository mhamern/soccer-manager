package cz.muni.fi.pa165.soccermanager.service.facade;

import cz.muni.fi.pa165.soccermanager.dto.LeagueDTO;
import cz.muni.fi.pa165.soccermanager.enums.NationalityEnum;
import cz.muni.fi.pa165.soccermanager.facade.LeagueFacade;

import java.util.List;

/**
 * @author 476368 Iman Mehmandoust
 * @version 11/24/2017.
 */
public class LeagueFacadeImpl implements LeagueFacade {
    @Override
    public List<LeagueDTO> getLeaguesByCountry(NationalityEnum country) {
        return null;
    }

    @Override
    public LeagueDTO getLeagueById(Long id) {
        return null;
    }

    @Override
    public LeagueDTO getLeagueByName(String name) {
        return null;
    }

    @Override
    public void addMatch(Long leagueId, Long matchId) {

    }

    @Override
    public void simulateMatchday() {

    }
}
