package cz.muni.fi.pa165.soccermanager.service;

import cz.muni.fi.pa165.soccermanager.dao.ManagerDao;
import cz.muni.fi.pa165.soccermanager.entity.Manager;
import cz.muni.fi.pa165.soccermanager.entity.Team;
import cz.muni.fi.pa165.soccermanager.enums.NationalityEnum;
import cz.muni.fi.pa165.soccermanager.service.exceptions.SoccerManagerServiceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

/**
 * Implementation of the ManagerService. The class is the part of implementation of
 * the business logic of the application.
 * @author 456519 Filip Lux
 * @version 11/16/2017.
 */
@Service
public class ManagerServiceImpl implements ManagerService {

    @Inject
    private ManagerDao managerDao;

    @Override
    public Manager fetchById(long managerId) {
        return managerDao.fetchById(managerId);
    }

    @Override
    public Manager fetchByName(String name) {
        return managerDao.fetchByName(name);
    }

    @Override
    public Manager fetchByEmail(String email) {
        return managerDao.fetchByEmail(email);
    }

    @Override
    public Manager fetchByTeam(Team team) {
        return managerDao.fetchByTeam(team);
    }

    @Override
    public List<Manager> fetchByNationality(NationalityEnum nationality) {
        return managerDao.fetchByNationality(nationality);
    }

    @Override
    public List<Manager> fetchManagersWithoutTeam() {
        return managerDao.fetchManagersWithoutTeam();
    }

    @Override
    public List<Manager> fetchAll() {
        return managerDao.fetchAll();
    }

    @Override
    public Manager createManager(Manager manager) throws SoccerManagerServiceException {

        if (managerDao.fetchAll().contains(manager)) {
            throw new SoccerManagerServiceException(
                    "Match " + manager.getName()  + " already exists");
        } else {
            managerDao.insert(manager);
            return manager;
        }
    }

    @Override
    public void updateManager(Manager manager) throws SoccerManagerServiceException {

        if (!managerDao.fetchAll().contains(manager)) {
            throw new SoccerManagerServiceException(
                    "Manager " + manager.getName() + " do not exists.");
        }
        managerDao.update(manager);
    }

    @Override
    public void removeManager(long managerId) {
        managerDao.delete(managerId);

    }
}
