package cz.muni.fi.pa165.soccermanager.facade;

import cz.muni.fi.pa165.soccermanager.dto.CreatePlayerDTO;
import cz.muni.fi.pa165.soccermanager.dto.PlayerDTO;
import cz.muni.fi.pa165.soccermanager.entity.Player;
import cz.muni.fi.pa165.soccermanager.enums.NationalityEnum;
import cz.muni.fi.pa165.soccermanager.enums.PositionEnum;
import cz.muni.fi.pa165.soccermanager.service.*;
import cz.muni.fi.pa165.soccermanager.service.config.ServiceConfiguration;
import cz.muni.fi.pa165.soccermanager.service.facade.PlayerFacadeImpl;
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

/**
 * @author 445720 Martin Hamernik
 * @version 11/25/2017.
 */
@ContextConfiguration(classes = ServiceConfiguration.class)
public class PlayerFacadeTest {

    private PlayerService playerService = mock(PlayerService.class);

    private TeamService teamService = mock(TeamService.class);

    private BeanMappingService beanMappingService = mock(BeanMappingService.class);

    @InjectMocks
    PlayerFacade playerFacade = new PlayerFacadeImpl(playerService, teamService, beanMappingService);

    private PlayerDTO playerDTO1;
    private PlayerDTO playerDTO2;
    private CreatePlayerDTO createPlayerDTO;
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
        player1.setId(1L);

        player2 = new Player.PlayerBuilder(
                "Paul Pogba",
                PositionEnum.MIDFIELDER,
                NationalityEnum.France,
                LocalDate.of(1994, 10, 21))
                .build();
        player2.setId(2L);

        playerDTO1 = new PlayerDTO();
        playerDTO1.setId(player1.getId());
        playerDTO1.setName(player1.getName());
        playerDTO1.setPosition(player1.getPosition());
        playerDTO1.setNationality(player1.getNationality());
        playerDTO1.setBirthDate(player1.getBirthDate());

        playerDTO2 = new PlayerDTO();
        playerDTO2.setId(player2.getId());
        playerDTO2.setName(player2.getName());
        playerDTO2.setPosition(player2.getPosition());
        playerDTO2.setNationality(player2.getNationality());
        playerDTO2.setBirthDate(player2.getBirthDate());

        createPlayerDTO = new CreatePlayerDTO();
        createPlayerDTO.setName("Cristiano Ronaldo");
        createPlayerDTO.setPosition(PositionEnum.ATTACKER);
        createPlayerDTO.setNationality(NationalityEnum.Portugal);
        createPlayerDTO.setBirthDate(LocalDate.of(1986, 10, 10));

    }

    @Test
    public void findByIdPlayer() {
        when(playerService.fetchById(player1.getId())).thenReturn(player1);
        when(playerService.fetchById(player2.getId())).thenReturn(player2);
        when(beanMappingService.mapTo(player1, PlayerDTO.class)).thenReturn(playerDTO1);
        when(beanMappingService.mapTo(player2, PlayerDTO.class)).thenReturn(playerDTO2);

        PlayerDTO firstFound = playerFacade.getPlayerById(player1.getId());
        PlayerDTO secondFound = playerFacade.getPlayerById(player2.getId());

        assertEquals(playerDTO1, firstFound, "First player DTO does not match expected result");
        assertEquals(playerDTO2, secondFound, "Second player DTO does not match expected result");
    }

    @Test
    public void findAllPlayers() {
        List<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        List<PlayerDTO> dtos = new ArrayList<>();
        dtos.add(playerDTO1);
        dtos.add(playerDTO2);

        when(playerService.fetchAll()).thenReturn(players);
        when(beanMappingService.mapTo(players, PlayerDTO.class)).thenReturn(dtos);
        List<PlayerDTO> playerDTOList = playerFacade.getAllPlayers();

        assertTrue(playerDTOList.size() == 2, "List should have size exactly 2");
        assertTrue(playerDTOList.contains(playerDTO1), "List does not contain first player");
        assertTrue(playerDTOList.contains(playerDTO2), "List does not contain second player");
    }

    @Test
    public void createPlayer() {
        Player player = new Player.PlayerBuilder(
                "Cristiano Ronaldo",
                PositionEnum.ATTACKER,
                NationalityEnum.Portugal,
                LocalDate.of(1986, 10, 10)
        ).build();
        player.setId(10L);

        when(playerService.create(player)).thenReturn(player);
        when(playerService.fetchById(10L)).thenReturn(player);
        when(beanMappingService.mapTo(createPlayerDTO, Player.class)).thenReturn(player);

        Long playerId = playerFacade.createPlayer(createPlayerDTO);
        Player created = playerService.fetchById(playerId);

        assertEquals(player, created);
    }

    @Test
    public void deletePlayer() {
        doNothing().when(playerService).delete(10L);
        playerFacade.deletePlayer(10L);
    }
}
