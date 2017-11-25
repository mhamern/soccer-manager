package cz.muni.fi.pa165.soccermanager.service;

import cz.muni.fi.pa165.soccermanager.dto.TeamDTO;
import cz.muni.fi.pa165.soccermanager.entity.League;
import cz.muni.fi.pa165.soccermanager.entity.Player;
import cz.muni.fi.pa165.soccermanager.entity.Team;
import cz.muni.fi.pa165.soccermanager.enums.NationalityEnum;
import cz.muni.fi.pa165.soccermanager.enums.PositionEnum;
import cz.muni.fi.pa165.soccermanager.enums.StadiumEnum;
import cz.muni.fi.pa165.soccermanager.service.config.ServiceConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@ContextConfiguration(classes = ServiceConfiguration.class)
public class BeanMappingServiceTest {

    @Autowired
    private BeanMappingService beanMappingService;

    private List<Team> teams = new ArrayList<>();
    private List<Player> players = new ArrayList<>();

    @Before
    public void setUp() {
        Player player1 = new Player.PlayerBuilder(
                "Kylian Mbappe",
                PositionEnum.ATTACKER,
                NationalityEnum.France,
                LocalDate.of(1997, 10, 20))
                .build();

        Player player2 = new Player.PlayerBuilder(
                "Paul Pogba",
                PositionEnum.MIDFIELDER,
                NationalityEnum.France,
                LocalDate.of(1994, 10, 21))
                .build();

        League league = new League.LeagueBuilder(
                "Ligue 1",
                NationalityEnum.France
                ).build();

        Team team = new Team.TeamBuilder(
                "Paris Saint Germain",
                NationalityEnum.France,
                StadiumEnum.Parc_des_Princes,
                league
        ).build();

        team.addPlayer(player1);
        team.addPlayer(player2);

        teams.add(team);

        players.add(player1);
        players.add(player2);
    }

    @Test
    public void shouldMapPlayers() {
        List<TeamDTO> teamDtos = beanMappingService.mapTo(teams, TeamDTO.class);
        assert(teamDtos.get(0).getPlayers().size() == 2);
    }
}
