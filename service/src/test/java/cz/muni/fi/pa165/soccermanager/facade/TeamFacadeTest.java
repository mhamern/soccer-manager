package cz.muni.fi.pa165.soccermanager.facade;

import cz.muni.fi.pa165.soccermanager.dto.CreateTeamDTO;
import cz.muni.fi.pa165.soccermanager.dto.TeamDTO;
import cz.muni.fi.pa165.soccermanager.entity.League;
import cz.muni.fi.pa165.soccermanager.entity.Player;
import cz.muni.fi.pa165.soccermanager.entity.Team;
import cz.muni.fi.pa165.soccermanager.enums.NationalityEnum;
import cz.muni.fi.pa165.soccermanager.enums.PositionEnum;
import cz.muni.fi.pa165.soccermanager.enums.StadiumEnum;
import cz.muni.fi.pa165.soccermanager.service.*;
import cz.muni.fi.pa165.soccermanager.service.config.ServiceConfiguration;
import cz.muni.fi.pa165.soccermanager.service.facade.TeamFacadeImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes = ServiceConfiguration.class)
public class TeamFacadeTest {


    private TeamService teamService = mock(TeamService.class);
    private LeagueService leagueService = mock(LeagueService.class);
    private ManagerService managerService = mock(ManagerService.class);
    private PlayerService playerService = mock(PlayerService.class);
    private BeanMappingService beanMappingService = mock(BeanMappingService.class);

    @InjectMocks
    private TeamFacade teamFacade = new TeamFacadeImpl(teamService, leagueService, managerService, playerService, beanMappingService);


    private Team team1;
    private Team team2;
    private TeamDTO teamDTO1;
    private TeamDTO teamDTO2;
    private CreateTeamDTO createTeamDTO;
    private League league1;
    private League league2;
    private Player player1;
    private Player player2;

    @Before
    public void setup() {
        player1 = new Player.PlayerBuilder(
                "Kylian Mbappe",
                PositionEnum.ATTACKER,
                NationalityEnum.France,
                LocalDate.of(1990, 10, 20))
                .build();

        player2 = new Player.PlayerBuilder(
                "Paul Pogba",
                PositionEnum.MIDFIELDER,
                NationalityEnum.France,
                LocalDate.of(1994, 10, 21))
                .build();

        league1 = new League.LeagueBuilder(
                "Ligue 1",
                NationalityEnum.France
        ).build();
        league1.setId(1L);

        league2 = new League.LeagueBuilder(
                "Premier League",
                NationalityEnum.England
        ).build();
        league2.setId(2L);

        team1 = new Team.TeamBuilder(
                "Paris Saint Germain",
                NationalityEnum.France,
                StadiumEnum.Parc_des_Princes,
                league1
        ).build();
        team1.setId(1L);

        team2 = new Team.TeamBuilder(
                "Manchester United",
                NationalityEnum.England,
                StadiumEnum.Old_Trafford,
                league2
        ).build();

        team2.setId(2L);

        createTeamDTO = new CreateTeamDTO();
        createTeamDTO.setName("Arsenal FC");
        createTeamDTO.setOrigin(NationalityEnum.England);
        createTeamDTO.setStadium(StadiumEnum.Emirates_Stadium);
        createTeamDTO.setLeagueId(league2.getId());

        teamDTO1 = new TeamDTO();
        teamDTO1.setName(team1.getName());
        teamDTO1.setLeague(team1.getLeague());
        teamDTO1.setOrigin(team1.getOrigin());
        teamDTO1.setPlayers(team1.getPlayers());
        teamDTO1.setStadium(team1.getStadium());

        teamDTO2 = new TeamDTO();
        teamDTO2.setName(team2.getName());
        teamDTO2.setLeague(team2.getLeague());
        teamDTO2.setOrigin(team2.getOrigin());
        teamDTO2.setPlayers(team2.getPlayers());
        teamDTO2.setStadium(team2.getStadium());
    }

    @Test
    public void findByIdTeam() {
        when(teamService.fetchById(team1.getId())).thenReturn(team1);
        when(teamService.fetchById(team2.getId())).thenReturn(team2);
        when(beanMappingService.mapTo(team1, TeamDTO.class)).thenReturn(teamDTO1);
        when(beanMappingService.mapTo(team2, TeamDTO.class)).thenReturn(teamDTO2);

        TeamDTO firstFound = teamFacade.getTeamById(team1.getId());
        TeamDTO secondFound = teamFacade.getTeamById(team2.getId());

        assertEquals(teamDTO1, firstFound, "First team DTO does not match expected result");
        assertEquals(teamDTO2, secondFound, "Second team DTO does not match expected result");
    }

    @Test
    public void findAllTeams() {
        List<Team> teams = new ArrayList<>();
        teams.add(team1);
        teams.add(team2);
        List<TeamDTO> dtos = new ArrayList<>();
        dtos.add(teamDTO1);
        dtos.add(teamDTO2);

        when(teamService.fetchAll()).thenReturn(teams);
        when(beanMappingService.mapTo(teams, TeamDTO.class)).thenReturn(dtos);

        List<TeamDTO> teamDTOList = teamFacade.getAllTeams();

        assertTrue(teamDTOList.size() == 2, "List should have size exactly 2");
        assertTrue(teamDTOList.contains(teamDTO1), "List does not contain first team");
        assertTrue(teamDTOList.contains(teamDTO2), "List does not contain second team");
    }

    @Test
    public void createTeam() {
        Team team = new Team.TeamBuilder(
                "Arsenal FC",
                NationalityEnum.England,
                StadiumEnum.Emirates_Stadium,
                league2
        ).build();
        team.setId(10L);

        when(teamService.create(team)).thenReturn(team);
        when(teamService.fetchById(10L)).thenReturn(team);
        when(leagueService.fetchById(2L)).thenReturn(league2);
        when(beanMappingService.mapTo(createTeamDTO, Team.class)).thenReturn(team);

        Long teamId = teamFacade.createTeam(createTeamDTO);
        Team created = teamService.fetchById(teamId);

        assertEquals(team, created);
    }

    @Test
    public void deleteTeam() {
        doNothing().when(teamService).delete(10L);
        teamFacade.deleteTeam(10L);

    }

}