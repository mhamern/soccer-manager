package cz.muni.fi.pa165.soccermanager.dao;

import cz.muni.fi.pa165.soccermanager.entity.Manager;

import java.util.List;

public interface ManagerDao {
    /**
     * Finds manager in db by id.
     * @param managerId id of desired manager
     * @return manager retrieved from db
     */
    Manager fetchById(long managerId);

    /**
     * Retrieves all manager from db
     * @return List of managers retrieved from db
     */
    List<Manager> fetchAll();

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
