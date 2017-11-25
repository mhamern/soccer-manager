package cz.muni.fi.pa165.soccermanager.service.facade;

import cz.muni.fi.pa165.soccermanager.dto.ManagerDTO;
import cz.muni.fi.pa165.soccermanager.enums.NationalityEnum;
import cz.muni.fi.pa165.soccermanager.facade.ManagerFacade;

import java.util.List;

/**
 * @author 456519 Filip Lux
 * @version 11/20/2017
 */
public class ManagerFacadeImpl implements ManagerFacade {
    @Override
    public List<ManagerDTO> getManagersByNationality(NationalityEnum nationality) {
        return null;
    }

    @Override
    public List<ManagerDTO> getManagersByLeague(Long leagueId) {
        return null;
    }

    @Override
    public ManagerDTO getManagerById(Long id) {
        return null;
    }

    @Override
    public ManagerDTO getManagerByName(String name) {
        return null;
    }

    @Override
    public ManagerDTO getManagerByEmail(String email) {
        return null;
    }

    @Override
    public ManagerDTO getManagerByTeam(Long teamId) {
        return null;
    }
}
