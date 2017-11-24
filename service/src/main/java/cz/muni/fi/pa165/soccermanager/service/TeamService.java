package cz.muni.fi.pa165.soccermanager.service;

import cz.muni.fi.pa165.soccermanager.entity.Team;

import java.util.List;

/**
 * @author 445720 Martin Hamernik
 * @version 11/16/2017.
 */
public interface TeamService {

    Team fetchById(long teamId);
    Team fetchByName(String name);
    List<Team> fetchAll();
    void insert(Team team);
    void update(Team team);
    void delete(long teamId);
}
