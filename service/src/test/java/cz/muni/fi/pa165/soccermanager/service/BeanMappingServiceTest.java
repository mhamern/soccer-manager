package cz.muni.fi.pa165.soccermanager.service;

import cz.muni.fi.pa165.soccermanager.dto.PlayerDTO;
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
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * @author 445720 Martin Hamernik
 * @version 11/26/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ServiceConfiguration.class)
public class BeanMappingServiceTest {

    @Autowired
    private BeanMappingService beanMappingService;

    private List<Team>  teams = new ArrayList<>();

    @Before
    public void setUp() {
        League league = new League.LeagueBuilder(
                "Ligue 1",
                NationalityEnum.France
        ).build();



        Team team1 = new Team.TeamBuilder(
                "Paris Saint Germain",
                NationalityEnum.France,
                StadiumEnum.Parc_des_Princes,
                league
        ).build();
        teams.add(team1);
    }

    @Test
    public void playerMap() {

        List<TeamDTO> result = beanMappingService.mapTo(teams, TeamDTO.class);

        assertTrue(result.size() == 1);
    }
}
