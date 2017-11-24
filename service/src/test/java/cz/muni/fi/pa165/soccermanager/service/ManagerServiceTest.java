package cz.muni.fi.pa165.soccermanager.service;

import cz.muni.fi.pa165.soccermanager.entity.Match;
import cz.muni.fi.pa165.soccermanager.entity.Team;
import cz.muni.fi.pa165.soccermanager.service.config.ServiceConfiguration;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * class contains tests of manager service
 * @author 456519 Filip Lux
 * @version 11/24/2017
 */

@ContextConfiguration(classes = ServiceConfiguration.class)
public class ManagerServiceTest {

    @Autowired
    @InjectMocks
    private MatchService managerService;


    @Test
    public void testFetchByTeam() {
    }

    @Test
    public void testFetchByEmail() {
    }

    @Test
    public void testMatch() {
    }

    @Test
    public void testUpdate() {
    }

    @Test
    public void testRemove() {
    }

}

