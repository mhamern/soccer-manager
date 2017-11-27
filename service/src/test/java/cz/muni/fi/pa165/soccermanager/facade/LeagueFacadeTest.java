package cz.muni.fi.pa165.soccermanager.facade;

import cz.muni.fi.pa165.soccermanager.dto.CreateLeagueDTO;
import cz.muni.fi.pa165.soccermanager.dto.LeagueDTO;
import cz.muni.fi.pa165.soccermanager.dto.TeamDTO;
import cz.muni.fi.pa165.soccermanager.entity.League;
import cz.muni.fi.pa165.soccermanager.entity.Match;
import cz.muni.fi.pa165.soccermanager.entity.Team;
import cz.muni.fi.pa165.soccermanager.enums.NationalityEnum;
import cz.muni.fi.pa165.soccermanager.enums.StadiumEnum;
import cz.muni.fi.pa165.soccermanager.service.*;
import cz.muni.fi.pa165.soccermanager.service.config.ServiceConfiguration;
import cz.muni.fi.pa165.soccermanager.service.facade.LeagueFacadeImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author 476368 Iman Mehmandoust
 * @version 11/27/2017.
 */

@ContextConfiguration(classes = ServiceConfiguration.class)
public class LeagueFacadeTest {


    private LeagueService leagueService = mock(LeagueService.class);
    private MatchService matchService = mock(MatchService.class);
    private BeanMappingService mappingService = mock(BeanMappingService.class);
    private BeanMappingService beanMappingService = mock(BeanMappingService.class);

    @InjectMocks
    private LeagueFacade leagueFacade = new LeagueFacadeImpl(leagueService, matchService, mappingService);



    private LeagueDTO leagueDTO1;
    private LeagueDTO leagueDTO2;

    private CreateLeagueDTO createLeagueDTO;




    private League league1;
    private League league2;

    private Match match1;
    private Match match2;
    private Date date;

    private List<Match> matches;

    private Team team1;
    private Team team2;
    private Team team3;
    private Team team4;

    LocalDate futureDate;
    @Before
    public void setup() {
        futureDate = LocalDate.now().plusMonths(1);

        league1 = new League.LeagueBuilder(
                "Premier League",
                NationalityEnum.England
        ).build();

        team1 = new Team.TeamBuilder(
                "Real Madrid",
                NationalityEnum.Spain,
                StadiumEnum.Millennium_Stadium,
                league1
        ).build();

        team2 = new Team.TeamBuilder(
                "Chelsea FC",
                NationalityEnum.England,
                StadiumEnum.Stamford_Bridge,
                league1
        ).build();



        league2 = new League.LeagueBuilder(
                "Super League",
                NationalityEnum.Spain
        ).build();

        team3 = new Team.TeamBuilder(
                "FC Barcelona",
                NationalityEnum.Spain,
                StadiumEnum.Millennium_Stadium,
                league2
        ).build();

        team4 = new Team.TeamBuilder(
                "Manchester United",
                NationalityEnum.England,
                StadiumEnum.Old_Trafford,
                league2
        ).build();



        match1 = new Match.MatchBuilder(team1,team2,futureDate).build();
        match2 = new Match.MatchBuilder(team3,team4, futureDate).build();


        league1.setMatches(matches);
        league2.setMatches(matches);



        createLeagueDTO = new CreateLeagueDTO();
        createLeagueDTO.setName("");
        createLeagueDTO.setCountry(NationalityEnum.England);

        leagueDTO1 = new LeagueDTO();
        leagueDTO1.setName(league1.getName());
        leagueDTO1.setCountry(league1.getCountry());
        leagueDTO1.setMatches(league1.getMatches());

        leagueDTO2 = new LeagueDTO();
        leagueDTO2.setName(league2.getName());
        leagueDTO2.setCountry(league2.getCountry());
        leagueDTO2.setMatches(league2.getMatches());
    }

    @Test
    public void findByIdLeague() {
        when(leagueService.fetchById(league1.getId())).thenReturn(league1);
        when(leagueService.fetchById(league2.getId())).thenReturn(league2);
        when(beanMappingService.mapTo(league1, LeagueDTO.class)).thenReturn(leagueDTO1);
        when(beanMappingService.mapTo(league2, LeagueDTO.class)).thenReturn(leagueDTO2);

        LeagueDTO firstFound = leagueFacade.getLeagueById(league1.getId());
        LeagueDTO secondFound = leagueFacade.getLeagueById(league2.getId());

        assertEquals(leagueDTO1, firstFound, "First league DTO does not match expected result");
        assertEquals(leagueDTO2, secondFound, "Second league DTO does not match expected result");
    }

    @Test
    public void findAllTeams() {
        List<League> leagues = new ArrayList<>();
        leagues.add(league1);
        leagues.add(league2);
        List<LeagueDTO> dtos = new ArrayList<>();
        dtos.add(leagueDTO1);
        dtos.add(leagueDTO2);

        when(leagueService.fetchAll()).thenReturn(leagues);
        when(beanMappingService.mapTo(leagues, LeagueDTO.class)).thenReturn(dtos);


        List<LeagueDTO> leagueDTOList = leagueFacade.getAllLeagues();

        assertTrue(leagueDTOList.size() == 2, "List should have size exactly 2");
        assertTrue(leagueDTOList.contains(leagueDTO1), "List does not contain first league");
        assertTrue(leagueDTOList.contains(leagueDTO2), "List does not contain second league");
    }

    @Test
    public void createLeague() {
        List<League> leagues = new ArrayList<>();
        leagues.add(league1);
        leagues.add(league2);

        League toCreate = new League.LeagueBuilder(
                "Championship",
                NationalityEnum.Ukraine
        ).build();

        when(leagueService.insert(league1)).thenReturn(league1);
        when(leagueService.fetchById(10L)).thenReturn(league1);
        when(beanMappingService.mapTo(createLeagueDTO, League.class)).thenReturn(league1);


        Long leagueId = leagueFacade.CreateLeague(createLeagueDTO);
        League created = leagueService.fetchById(leagueId);

        assertEquals(leagues, created);
    }

    @Test
    public void deleteLeague() {
        doNothing().when(leagueService).delete(10L);
        leagueFacade.deleteLeague(10L);

    }

}