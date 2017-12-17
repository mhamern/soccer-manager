package cz.muni.fi.pa165.soccermanager.facade;

import cz.muni.fi.pa165.soccermanager.dto.CreateLeagueDTO;
import cz.muni.fi.pa165.soccermanager.dto.LeagueDTO;
import cz.muni.fi.pa165.soccermanager.dto.TeamDTO;
import cz.muni.fi.pa165.soccermanager.enums.NationalityEnum;


import java.util.List;

/**
 * @author 476368 Iman Mehmandoust
 * @version 12/14/2017.
 */
public interface LeagueFacade {

    public List<LeagueDTO> getLeaguesByCountry(NationalityEnum country);

    public LeagueDTO getLeagueById(Long id);

    public LeagueDTO getLeagueByName(String name);

    public void addMatch(Long leagueId, Long matchId);

    public void removeMatch(Long leagueId, Long matchId);

    public List<LeagueDTO> getAllLeagues();

    public Long CreateLeague(CreateLeagueDTO league);

    public void deleteLeague(Long leagueId);

    public List<TeamDTO> calculateLeagues(Long id);

}
