package cz.muni.fi.pa165.soccermanager.dao;

import cz.muni.fi.pa165.soccermanager.entity.Admin;

import java.util.List;

/**
 * @author 445720 Martin Hamernik
 * @version 11/1/2017.
 */

public interface AdminDao {
    Admin fetchById(long adminId);
    List<Admin> fetchAll();
    void insert(Admin admin);
    void update(Admin admin);
    void delete(long adminId);
}