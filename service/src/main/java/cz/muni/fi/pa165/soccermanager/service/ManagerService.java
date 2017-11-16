package cz.muni.fi.pa165.soccermanager.service;

import cz.muni.fi.pa165.soccermanager.entity.Manager;

import java.util.List;

/**
 * @author 445720 Martin Hamernik
 * @version 11/16/2017.
 */
public interface ManagerService {
    Manager fetchById(long managerId);
    List<Manager> fetchAll();
    void insert(Manager manager);
    void update(Manager manager);
    void delete(long managerId);
}
