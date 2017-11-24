package cz.muni.fi.pa165.soccermanager.service;

import cz.muni.fi.pa165.soccermanager.dao.LeagueDao;
import cz.muni.fi.pa165.soccermanager.entity.League;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Implementation of the LeagueService. The class is the part of implementation of
 * the business logic of the application.
 * @author 476368 Iman Mehmandoust
 * @version 11/24/2017.
 */

@Service
public class LeagueServiceImpl implements LeagueService {


    @Inject
    private LeagueDao leagueDao;


    @Override
    public League fetchById(long leagueId) {
        return leagueDao.fetchById(leagueId);
    }

    @Override
    public List<League> fetchAll() {
        return leagueDao.fetchAll();
    }

    @Override
    public void insert(League league) {
        leagueDao.insert(league);
    }

    @Override
    public void update(League league) {
        leagueDao.update(league);
    }

    @Override
    public void delete(long leagueId) {
        leagueDao.delete(leagueId);
    }
}
