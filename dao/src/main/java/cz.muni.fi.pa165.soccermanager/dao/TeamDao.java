package cz.muni.fi.pa165.soccermanager.dao;

import cz.muni.fi.pa165.soccermanager.entity.Team;

import java.util.List;

public interface TeamDao {
    Team fetchById(long teamId);
    List<Team> fetchAll();
    void insert(Team player);
    void update(Team player);
    void delete(long teamId);
}
