package cz.muni.fi.pa165.soccermanager.dao;

import cz.muni.fi.pa165.soccermanager.entity.Manager;
import cz.muni.fi.pa165.soccermanager.entity.Team;
import cz.muni.fi.pa165.soccermanager.enums.NationalityEnum;

import java.util.List;

public interface ManagerDao {
    /**
     * Finds manager in db by id.
     * @param managerId id of desired manager
     * @return manager retrieved from db
     */
    Manager fetchById(long managerId);

    /**
     * Retrieves all managers from db
     * @return List of managers retrieved from db
     */
    List<Manager> fetchAll();

    /**
     * Retrieves all managers matching provided nationality from db
     * @param nationalityEnum nationality of manager
     * @return List of managers retrieved from db matching provided nationality, empty list if no such manager is in db
     */
    List<Manager> fetchByNationality(NationalityEnum nationalityEnum);

    /**
     * Retrieves all managers currently without team from db
     * @return List of managers retrieved from db currently without team, empty list if no such manager is in db
     */
    List<Manager> fetchManagersWithoutTeam();

    /**
     * Retrieves all users with admin
     * @return List of managers retrieved from db with admit rights
     */
    List<Manager> fetchAdmins();

    /**
     * Retrieves manager matching provided name
     * @param name name of manager
     * @return Manager retrieved from db matching provided name, null if no such manager is in db
     */
    Manager fetchByName(String name);

    /**
     * Retrieves manager matching provided email
     * @param email email of manager
     * @return Manager retrieved from db matching provided email, null if no such manager is in db
     */
    Manager fetchByEmail(String email);

    /**
     * Retrieves manager of provided team
     * @param team team that manager manages
     * @return Manager retrieved from db matching provided team, null if no such manager is in db
     */
    Manager fetchByTeam(Team team);
    /**
     * Inserts new manager to db
     * @param manager instance of manager which shall be inserted
     *
     */
    void insert(Manager manager);

    /**
     * Finds manager by id and updates it with new attributes.
     * @param manager manager already stored in db which shall be updated
     */
    void update(Manager manager);

    /**
     * Finds manager by id and deletes it from db
     * @param managerId id of manager that shall be deleted
     */
    void delete(long managerId);
}
