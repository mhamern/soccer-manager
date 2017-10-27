package cz.muni.fi.pa165.soccermanager.dao;

import cz.muni.fi.pa165.soccermanager.entity.Manager;

import java.util.List;

public interface ManagerDao {
    Manager fetchById(long managerId);
    List<Manager> fetchAll();
    void insert(Manager manager);
    void update(Manager manager);
    void delete(long managerId);
}
