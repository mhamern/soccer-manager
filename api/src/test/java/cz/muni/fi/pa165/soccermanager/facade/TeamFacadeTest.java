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

import org.junit.Before;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import javax.inject.Inject;
import java.time.LocalDate;

/**
 * @author 445720 Martin Hamernik
 * @version 11/25/2017.
 */

@ContextConfiguration(classes = ServiceConfiguration.class)
public class TeamFacadeTest {

    @Mock
    TeamService teamService;

    @Mock
    PlayerService playerService;

    @Mock
    LeagueService leagueService;

    @Mock
    ManagerService managerService;

    @Autowired
    @InjectMocks
    TeamFacade teamFacade;


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
                LocalDate.of(1997, 10, 20))
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

        league2 = new League.LeagueBuilder(
                "Premier League",
                NationalityEnum.England
        ).build();

        team1 = new Team.TeamBuilder(
                "Paris Saint Germain",
                NationalityEnum.France,
                StadiumEnum.Parc_des_Princes,
                league1
        ).build();

        team2 = new Team.TeamBuilder(
                "Manchester United",
                NationalityEnum.England,
                StadiumEnum.Old_Trafford,
                league2
        ).build();

        team1.addPlayer(player1);
        team2.addPlayer(player2);

        createTeamDTO = new CreateTeamDTO();
        createTeamDTO.setName("Arsenal FC");
        createTeamDTO.setOrigin(NationalityEnum.England);
        createTeamDTO.setStadium(StadiumEnum.Emirates_Stadium);


    }

    @Test
    public void findById() {
        TeamDTO firstFound = teamFacade.getTeamById(team1.getId());
        TeamDTO secondFound = teamFacade.getTeamById(team2.getId());

        assertEquals(firstFound, teamDTO1, "First team DTO does not match expected result");
        assertEquals(secondFound, teamDTO2, "Second team DTO does not match expected result");
    }


}
