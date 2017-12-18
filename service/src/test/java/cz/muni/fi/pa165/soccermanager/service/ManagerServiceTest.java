package cz.muni.fi.pa165.soccermanager.service;

import cz.muni.fi.pa165.soccermanager.dao.ManagerDao;
import cz.muni.fi.pa165.soccermanager.dao.TeamDao;
import cz.muni.fi.pa165.soccermanager.entity.League;
import cz.muni.fi.pa165.soccermanager.entity.Manager;
import cz.muni.fi.pa165.soccermanager.entity.Match;
import cz.muni.fi.pa165.soccermanager.entity.Team;
import cz.muni.fi.pa165.soccermanager.enums.NationalityEnum;
import cz.muni.fi.pa165.soccermanager.enums.StadiumEnum;
import cz.muni.fi.pa165.soccermanager.service.config.ServiceConfiguration;
import org.hibernate.service.spi.ServiceException;
import org.junit.BeforeClass;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * class contains tests of manager service
 * @author 456519 Filip Lux
 * @version 11/24/2017
 */

@ContextConfiguration(classes = ServiceConfiguration.class)
public class ManagerServiceTest {

    private ManagerDao managerDao = mock(ManagerDao.class);
    private TeamDao teamDao = mock(TeamDao.class);

    @Autowired
    @InjectMocks
    private ManagerService managerService;

    private Manager manager1;
    private Manager manager2;
    private Manager manager3;

    private League league;
    private Team team;

    @Before
    public void setUp() {

        managerService = new ManagerServiceImpl(managerDao, teamDao);

        manager1 = new Manager.ManagerBuilder(
                "Jose Mourinho",
                NationalityEnum.Portugal,
                "thespecialone@gmail.com",
                false)
                .build();

        manager2 = new Manager.ManagerBuilder(
                "Pep Guardiola",
                NationalityEnum.Spain,
                "tikitaka@gmail.com",
                false)
                .build();

        manager3 = new Manager.ManagerBuilder(
                "admin",
                NationalityEnum.CzechRepublic,
                "admin@gmail.com",
                true)
                .build();

        league = new League.LeagueBuilder(
                "Ligue 1",
                NationalityEnum.France
        ).build();

        team = new Team.TeamBuilder(
                "Paris Saint Germain",
                NationalityEnum.France,
                StadiumEnum.Parc_des_Princes,
                league
        ).build();
    }

    @Test
    public void fetchByIdMatch() {

        when(managerDao.fetchById(1L)).thenReturn(manager1);

        Manager found = managerService.fetchById(1L);

        assertEquals(manager1, found,
                "There was not found correct manager");
    }

    @Test
    public void fetchByIdTeamNotFound() {
        when(managerDao.fetchById(1)).thenReturn(null);

        Manager found = managerService.fetchById(1);

        assertNull(found, "Manager retrieved by service should be null.");
    }

    @Test
    public void fetchByName() {

        String name = manager1.getName();
        when(managerDao.fetchByName(name)).thenReturn(manager1);

        Manager found = managerService.fetchByName(name);

        assertEquals(manager1, found,
                "There was not found correct manager");
    }

    @Test
    public void fetchByEmail() {

        String email = manager1.getEmail();
        when(managerDao.fetchByEmail(email)).thenReturn(manager1);

        Manager found = managerService.fetchByEmail(email);

        assertEquals(manager1, found,
                "There was not found correct manager");
    }

    @Test
    public void fetchAllManagers() {
        List<Manager> managers = new ArrayList<>();
        managers.add(manager1);
        managers.add(manager2);

        when(managerDao.fetchAll()).thenReturn(managers);

        List<Manager> result = managerService.fetchAll();

        assertTrue(result.size() == 2,
                "There should be two matches in the list");
        assertTrue(result.contains(manager1),
                "List should contain manager1");
        assertTrue(result.contains(manager2),
                "List should contain manager2");
        assertTrue(!result.contains(manager3),
                "List should not contain manager3");
    }

    @Test
    public void fetchByNationality() {
        List<Manager> managers = new ArrayList<>();
        managers.add(manager1);
        managers.add(manager2);

        NationalityEnum nationality = NationalityEnum.England;

        when(managerDao.fetchByNationality(nationality)).thenReturn(managers);

        List<Manager> result = managerService.fetchByNationality(nationality);

        assertTrue(result.size() == 2,
                "There should be two matches in the list");
        assertTrue(result.contains(manager1),
                "List should contain manager1");
        assertTrue(result.contains(manager2),
                "List should contain manager2");
        assertTrue(!result.contains(manager3),
                "List should not contain manager3");
    }

    @Test
    public void fetchManagersWithoutTeam() {
        List<Manager> managers = new ArrayList<>();
        managers.add(manager1);
        managers.add(manager2);

        when(managerDao.fetchManagersWithoutTeam()).thenReturn(managers);

        List<Manager> result = managerService.fetchManagersWithoutTeam();
        assertTrue(result.size() == 2,
                "There should be two matches in the list");
        assertTrue(result.contains(manager1),
                "List should contain manager1");
        assertTrue(result.contains(manager2),
                "List should contain manager2");
        assertTrue(!result.contains(manager3),
                "List should not contain manager3");
    }

    @Test
    public void testFetchByTeam() {

        when(managerDao.fetchByTeam(team)).thenReturn(manager1);

        Manager found = managerService.fetchByTeam(team);

        assertEquals(manager1, found,
                "There was not found correct manager");
    }

    @Test
    public void testIsAdmin() {

        List<Manager> admins = new ArrayList<>();
        admins.add(manager1);
        admins.add(manager2);

        when(managerDao.fetchAdmins()).thenReturn(admins);

        boolean adminManager1 = managerService.isAdmin(manager1);
        boolean adminManager3 = managerService.isAdmin(manager3);

        assertTrue(adminManager1, "Manager should be an admin.");
        assertTrue(!adminManager3, "Manager should not be an admin.");
    }

}

