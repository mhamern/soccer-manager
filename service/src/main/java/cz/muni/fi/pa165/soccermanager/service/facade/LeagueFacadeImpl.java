package cz.muni.fi.pa165.soccermanager.service.facade;
import cz.muni.fi.pa165.soccermanager.dto.LeagueDTO;
import cz.muni.fi.pa165.soccermanager.entity.League;
import cz.muni.fi.pa165.soccermanager.service.*;
import cz.muni.fi.pa165.soccermanager.dto.CreateLeagueDTO;
import cz.muni.fi.pa165.soccermanager.enums.NationalityEnum;
import cz.muni.fi.pa165.soccermanager.facade.LeagueFacade;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;



/**
 * @author 476368 Iman Mehmandoust
 * @version 11/24/2017.
 */
@Service
@Transactional
public class LeagueFacadeImpl implements LeagueFacade {


    private final LeagueService leagueService;
    private final BeanMappingService beanMappingService;
    private final MatchService matchService;

    @Inject
    public LeagueFacadeImpl(
                          LeagueService leagueService,
                         MatchService matchService,
                          BeanMappingService beanMappingService) {
        this.leagueService = leagueService;
        this.matchService = matchService;

        this.beanMappingService = beanMappingService;
    }



    @Override
    public Long CreateLeague(CreateLeagueDTO league) {
        League mappedLeague = beanMappingService.mapTo(league, League.class);

        mappedLeague.setName(league.getName());
        mappedLeague.setCountry(league.getCountry());

        League newLeague = leagueService.insert(mappedLeague);

        return newLeague.getId();
    }

    @Override
    public void deleteLeague(Long leagueId) {
        leagueService.delete(leagueId);

    }

    @Override
    public List<LeagueDTO> getAllLeagues() {
        return beanMappingService.mapTo(leagueService.fetchAll(), LeagueDTO.class);
    }


    @Override
    public List<LeagueDTO> getLeaguesByCountry(NationalityEnum country) {
        return beanMappingService.mapTo(leagueService.fetchByCountry(country), LeagueDTO.class);
    }

    @Override
    public LeagueDTO getLeagueById(Long id) {

        League league = leagueService.fetchById(id);
        return  (league == null) ? null : beanMappingService.mapTo(league, LeagueDTO.class);    }

    @Override
    public LeagueDTO getLeagueByName(String name) {
        return beanMappingService.mapTo(leagueService.fetchByName(name), LeagueDTO.class);
    }

    @Override
    public void addMatch(Long leagueId, Long matchId) {
        leagueService.addMatch(matchService.fetchById(matchId), leagueService.fetchById(matchId));

    }

    @Override
    public void removeMatch(Long leagueId, Long matchId) {
        leagueService.removeMatch(matchService.fetchById(matchId), leagueService.fetchById(matchId));

    }



}
