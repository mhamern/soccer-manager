package cz.muni.fi.pa165.soccermanager.service;

import cz.muni.fi.pa165.soccermanager.entity.Manager;
import cz.muni.fi.pa165.soccermanager.entity.Team;
import cz.muni.fi.pa165.soccermanager.enums.NationalityEnum;

import java.util.List;

/**
 * An interface that defines a service access to the Manger entity
 * @author 456519 Filip Lux
 * @version 11/23/2017.
 */
public interface ManagerService {
    Manager fetchById(long managerId);
    Manager fetchByName(String name);
    Manager fetchByEmail(String email);
    Manager fetchByTeam(Team team);
    List<Manager> fetchByNationality(NationalityEnum nationalityEnum);
    List<Manager> fetchManagersWithoutTeam();
    List<Manager> fetchAdmins();
    List<Manager> fetchAll();
    boolean isAdmin(Manager manager);
    Manager create(Manager manager, String unecryptedPassword);
    void update(Manager manager);
    void remove(long managerId);
}
