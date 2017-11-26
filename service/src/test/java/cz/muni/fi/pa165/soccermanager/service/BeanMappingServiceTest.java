package cz.muni.fi.pa165.soccermanager.service;

import cz.muni.fi.pa165.soccermanager.dto.PlayerDTO;
import cz.muni.fi.pa165.soccermanager.entity.Player;
import cz.muni.fi.pa165.soccermanager.enums.NationalityEnum;
import cz.muni.fi.pa165.soccermanager.enums.PositionEnum;
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

        players.add(player1);
        players.add(player2);
    }

    @Test
    public void playerMap() {
        List<PlayerDTO> result = beanMappingService.mapTo(players, PlayerDTO.class);

        assertTrue(result.size() == 2);
    }
}
