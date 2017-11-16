package cz.muni.fi.pa165.soccermanager.service;

import cz.muni.fi.pa165.soccermanager.entity.Admin;

import java.util.List;

/**
 * @author 445720 Martin Hamernik
 * @version 11/16/2017.
 */
public interface AdminService {
    Admin fetchById(long adminId);
    List<Admin> fetchAll();
    void insert(Admin admin);
    void update(Admin admin);
    void delete(long adminId);
}
