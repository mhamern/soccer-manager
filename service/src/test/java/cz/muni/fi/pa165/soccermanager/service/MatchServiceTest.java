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

/**
 * class contains tests of match service
 * @author 456519 Filip Lux
 * @version 11/24/2017
 */

@ContextConfiguration(classes = ServiceConfiguration.class)
public class MatchServiceTest {

    @Mock
    private Team teamOne;

    @Mock
    private Team teamTwo;

    @Mock
    private Team teamThree;

    @Mock
    private Team teamFour;

    @Autowired
    @InjectMocks
    private MatchService matchService;

    private Match matchOne;
    private Match matchTwo;

    @Before
    public void initializeTeams() {
        // create not mocked entities
    }

    @Before
    public void getCurrentTime() {

    }

    // TODO: complete tests
    // what
    @Test
    public void testMatchPlayMethod() {

    }

    @Test
    public void testCreateMatchHomeAwaySameTeam() {

    }

    @Test
    public void testNullTeam() {
    }

    @Test
    public void testMatchCreatedInPast() {
    }


}
