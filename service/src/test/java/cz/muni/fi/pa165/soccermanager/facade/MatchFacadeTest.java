package cz.muni.fi.pa165.soccermanager.facade;

import cz.muni.fi.pa165.soccermanager.dto.CreateMatchDTO;
import cz.muni.fi.pa165.soccermanager.dto.CreateTeamDTO;
import cz.muni.fi.pa165.soccermanager.dto.MatchDTO;
import cz.muni.fi.pa165.soccermanager.dto.TeamDTO;
import cz.muni.fi.pa165.soccermanager.entity.League;
import cz.muni.fi.pa165.soccermanager.entity.Match;
import cz.muni.fi.pa165.soccermanager.entity.Player;
import cz.muni.fi.pa165.soccermanager.entity.Team;
import cz.muni.fi.pa165.soccermanager.enums.NationalityEnum;
import cz.muni.fi.pa165.soccermanager.enums.PositionEnum;
import cz.muni.fi.pa165.soccermanager.enums.StadiumEnum;
import cz.muni.fi.pa165.soccermanager.service.*;
import cz.muni.fi.pa165.soccermanager.service.config.ServiceConfiguration;
import cz.muni.fi.pa165.soccermanager.service.facade.MatchFacadeImpl;
import cz.muni.fi.pa165.soccermanager.service.facade.TeamFacadeImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author 456519 Filip Lux
 * @version 11/27/2017.
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class MatchFacadeTest {


    private TeamService teamService = mock(TeamService.class);
    private LeagueService leagueService = mock(LeagueService.class);
    private MatchService matchService = mock(MatchService.class);
    private BeanMappingService beanMappingService = mock(BeanMappingService.class);

    @InjectMocks
    private MatchFacade matchFacade = new MatchFacadeImpl( leagueService,
                                                            matchService,
                                                            teamService,
                                                            beanMappingService);

    private Team team1;
    private Team team2;
    private Team team3;
    private Team team4;
    private League league;
    private Match match1;
    private Match match2;
    private MatchDTO matchDTO1;
    private MatchDTO matchDTO2;
    private CreateMatchDTO createMatchDTO;

    private LocalDate pastDate;
    private LocalDate futureDate;

    @Before
    public void setup() {
        pastDate = LocalDate.now().minusMonths(1);
        futureDate = LocalDate.now().plusMonths(3);

        league = new League.LeagueBuilder(
                "Premier League",
                NationalityEnum.England
        ).build();

        league.setId(1L);

        team1 = new Team.TeamBuilder(
                "Paris Saint Germain",
                NationalityEnum.France,
                StadiumEnum.Parc_des_Princes,
                league
        ).build();

        team2 = new Team.TeamBuilder(
                "Manchester United",
                NationalityEnum.England,
                StadiumEnum.Old_Trafford,
                league
        ).build();

        team3 = new Team.TeamBuilder(
                "Zbrojovka Brno",
                NationalityEnum.CzechRepublic,
                StadiumEnum.Za_Luzankami,
                league
        ).build();

        team4 = new Team.TeamBuilder(
                "FC Barcelona",
                NationalityEnum.Spain,
                StadiumEnum.Camp_Nou,
                league
        ).build();

        match1 = new Match.MatchBuilder(
                team1,
                team2,
                futureDate,
                league
        ).build();

        match2 = new Match.MatchBuilder(
                team3,
                team4,
                futureDate,
                league
        ).build();

        team1.setId(1L);
        team2.setId(2L);
        team3.setId(3L);
        team4.setId(4L);

        match1.setId(1L);
        match2.setId(2L);

        createMatchDTO = new CreateMatchDTO();
        createMatchDTO.setHomeTeamName(match1.getHomeTeam().getName());
        createMatchDTO.setAwayTeamName(match1.getAwayTeam().getName());
        createMatchDTO.setDate(match1.getDate());

        matchDTO1 = new MatchDTO();
        matchDTO1.setHomeTeam(beanMappingService.mapTo(match1.getHomeTeam(), TeamDTO.class));
        matchDTO1.setAwayTeam(beanMappingService.mapTo(match1.getAwayTeam(), TeamDTO.class));
        matchDTO1.setDate(match1.getDate());

        matchDTO2 = new MatchDTO();
        matchDTO2.setHomeTeam(beanMappingService.mapTo(match2.getHomeTeam(), TeamDTO.class));
        matchDTO2.setAwayTeam(beanMappingService.mapTo(match2.getAwayTeam(), TeamDTO.class));
        matchDTO2.setDate(match2.getDate());
    }

    @Test
    public void findByIdMatch() {
        when(matchService.fetchById(match1.getId())).thenReturn(match1);
        when(matchService.fetchById(match2.getId())).thenReturn(match2);
        when(beanMappingService.mapTo(match1, MatchDTO.class)).thenReturn(matchDTO1);
        when(beanMappingService.mapTo(match2, MatchDTO.class)).thenReturn(matchDTO2);

        MatchDTO firstFound = matchFacade.getMatchById(match1.getId());
        MatchDTO secondFound = matchFacade.getMatchById(match2.getId());

        assertEquals(matchDTO1, firstFound, "First match DTO does not match expected result");
        assertEquals(matchDTO2, secondFound, "Second match DTO does not match expected result");
    }

    @Test
    public void findMatchesByTeam() {
        List<Match> matches = new ArrayList<>();
        matches.add(match1);
        matches.add(match2);
        List<MatchDTO> dtos = new ArrayList<>();
        dtos.add(matchDTO1);
        dtos.add(matchDTO2);

        when(matchService.fetchByTeam(team1)).thenReturn(matches);
        when(beanMappingService.mapTo(matches, MatchDTO.class)).thenReturn(dtos);
        when(teamService.fetchById(team1.getId())).thenReturn(team1);

        List<MatchDTO> matchDTOList = matchFacade.getMatchesByTeam(team1.getId());

        assertTrue(matchDTOList.size() == 2, "List should have size exactly 2");
        assertTrue(matchDTOList.contains(matchDTO1), "List does not contain first match");
        assertTrue(matchDTOList.contains(matchDTO2), "List does not contain second match");
    }

    @Test
    public void createMatch() {
        Match newMatch= new Match.MatchBuilder(
                match1.getHomeTeam(),
                match1.getAwayTeam(),
                match1.getDate(),
                league
        ).build();
        newMatch.setId(10L);

        when(matchService.createMatch(newMatch)).thenReturn(newMatch);
        when(matchService.fetchById(10L)).thenReturn(newMatch);
        when(beanMappingService.mapTo(createMatchDTO, Match.class)).thenReturn(newMatch);

        Long matchId = matchFacade.createMatch(createMatchDTO);
        Match created = matchService.fetchById(matchId);

        assertEquals(newMatch, created);
    }

    @Test
    public void deleteMatch() {
        doNothing().when(matchService).deleteMatch(10L);
        matchFacade.deleteMatch(10L);

    }

}
