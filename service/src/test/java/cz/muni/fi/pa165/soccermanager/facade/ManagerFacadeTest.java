package cz.muni.fi.pa165.soccermanager.facade;

import cz.muni.fi.pa165.soccermanager.dto.*;
import cz.muni.fi.pa165.soccermanager.entity.*;
import cz.muni.fi.pa165.soccermanager.enums.NationalityEnum;
import cz.muni.fi.pa165.soccermanager.enums.PositionEnum;
import cz.muni.fi.pa165.soccermanager.enums.StadiumEnum;
import cz.muni.fi.pa165.soccermanager.service.*;
import cz.muni.fi.pa165.soccermanager.service.config.ServiceConfiguration;
import cz.muni.fi.pa165.soccermanager.service.facade.ManagerFacadeImpl;
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
public class ManagerFacadeTest {


    private ManagerService managerService = mock(ManagerService.class);
    private TeamService teamService = mock(TeamService.class);
    private BeanMappingService beanMappingService = mock(BeanMappingService.class);

    @InjectMocks
    private ManagerFacade managerFacade = new ManagerFacadeImpl( managerService,
            teamService,
            beanMappingService);

    private Team team1;
    private Team team2;
    private League league;
    private Manager manager;
    private Manager admin;
    private ManagerDTO managerDTO;
    private ManagerDTO adminDTO;
    private CreateManagerDTO createManagerDTO;


    @Before
    public void setup() {

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

        manager = new Manager.ManagerBuilder(
                "Josef Kane",
                NationalityEnum.CzechRepublic,
                "kane@seznam.cz",
                false
        ).build();

        admin = new Manager.ManagerBuilder(
                "Veronica Kapuzi",
                NationalityEnum.Estonia,
                "kapuzi@yahoo.com",
                true
        ).build();

        team1.setId(1L);
        team2.setId(2L);
        manager.setId(10L);
        admin.setId(11L);

        createManagerDTO = new CreateManagerDTO();
        createManagerDTO.setName(manager.getName());
        createManagerDTO.setNationality(manager.getNationality());
        createManagerDTO.setAdmin(manager.isAdmin());

        managerDTO = new ManagerDTO();
        managerDTO.setEmail(manager.getEmail());
        managerDTO.setName(manager.getName());
        managerDTO.setNationality(manager.getNationality());
        managerDTO.setAdmin(manager.isAdmin());

        adminDTO = new ManagerDTO();
        adminDTO.setEmail(admin.getEmail());
        adminDTO.setName(admin.getName());
        adminDTO.setNationality(admin.getNationality());
        adminDTO.setAdmin(admin.isAdmin());
    }

    @Test
    public void findByIdManager() {
        when(managerService.fetchById(manager.getId())).thenReturn(manager);
        when(managerService.fetchById(admin.getId())).thenReturn(admin);
        when(beanMappingService.mapTo(manager, ManagerDTO.class)).thenReturn(managerDTO);
        when(beanMappingService.mapTo(admin, ManagerDTO.class)).thenReturn(adminDTO);

        ManagerDTO firstFound = managerFacade.findManagerById(manager.getId());
        ManagerDTO secondFound = managerFacade.findManagerById(admin.getId());

        assertEquals(managerDTO, firstFound, "First DTO does not match expected result");
        assertEquals(adminDTO, secondFound, "Second DTO does not match expected result");
    }

    @Test
    public void findManagersByTeam() {

        when(managerService.fetchByTeam(team1)).thenReturn(manager);
        when(beanMappingService.mapTo(manager, ManagerDTO.class)).thenReturn(managerDTO);
        when(teamService.fetchById(team1.getId())).thenReturn(team1);

        ManagerDTO result = managerFacade.findManagerByTeam(team1.getId());

        assertTrue(result == managerDTO, "Manager was not found.");

    }

    @Test
    public void findManagersByName() {

        String name = manager.getName();

        when(managerService.fetchByName(name)).thenReturn(manager);
        when(beanMappingService.mapTo(manager, ManagerDTO.class)).thenReturn(managerDTO);

        ManagerDTO result = managerFacade.findManagerByName(name);

        assertTrue(result == managerDTO, "Manager was not found.");

    }

    @Test
    public void findManagersByNationality() {
        List<Manager> managers = new ArrayList<>();
        managers.add(manager);
        managers.add(admin);
        List<ManagerDTO> dtos = new ArrayList<>();
        dtos.add(managerDTO);
        dtos.add(adminDTO);

        NationalityEnum nationality = manager.getNationality();

        when(managerService.fetchByNationality(nationality)).thenReturn(managers);
        when(beanMappingService.mapTo(managers, ManagerDTO.class)).thenReturn(dtos);
        when(teamService.fetchById(team1.getId())).thenReturn(team1);

        List<ManagerDTO> resultList = managerFacade.findManagersByNationality(nationality);

        assertTrue(resultList.size() == 2, "List should have size exactly 2");
        assertTrue(resultList.contains(managerDTO), "List does not contain manager");
        assertTrue(resultList.contains(adminDTO), "List does not contain admin");

    }

    @Test
    public void testGetAdmins() {
        List<Manager> admins = new ArrayList<>();
        admins.add(admin);
        List<ManagerDTO> dtos = new ArrayList<>();
        dtos.add(adminDTO);

        when(managerService.fetchAdmins()).thenReturn(admins);
        when(beanMappingService.mapTo(admins, ManagerDTO.class)).thenReturn(dtos);

        List<ManagerDTO> resultList = managerFacade.getAdmins();

        assertTrue(resultList.size() == 1, "List should have size exactly 1");
        assertTrue(!resultList.contains(managerDTO), "List does contain manager");
        assertTrue(resultList.contains(adminDTO), "List does not contain admin");

    }


    @Test
    public void registerManager() {
        Manager newManager= new Manager.ManagerBuilder(
                manager.getName(),
                manager.getNationality(),
                manager.getEmail(),
                manager.isAdmin()
        ).build();
        newManager.setId(10L);

        String password = "123456";

        when(managerService.create(newManager, password)).thenReturn(newManager);
        when(managerService.fetchById(10L)).thenReturn(newManager);
        when(beanMappingService.mapTo(createManagerDTO, Manager.class)).thenReturn(newManager);

        managerFacade.registerManager(createManagerDTO, password);

    }


}
