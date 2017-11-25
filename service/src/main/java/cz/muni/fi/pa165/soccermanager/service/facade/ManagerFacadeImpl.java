package cz.muni.fi.pa165.soccermanager.service.facade;

import cz.muni.fi.pa165.soccermanager.dto.ManagerDTO;
import cz.muni.fi.pa165.soccermanager.entity.Manager;
import cz.muni.fi.pa165.soccermanager.entity.Player;
import cz.muni.fi.pa165.soccermanager.entity.Team;
import cz.muni.fi.pa165.soccermanager.enums.NationalityEnum;
import cz.muni.fi.pa165.soccermanager.facade.ManagerFacade;
import cz.muni.fi.pa165.soccermanager.service.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.inject.Inject;
import java.util.List;

/**
 * @author 456519 Filip Lux
 * @version 11/20/2017
 */
public class ManagerFacadeImpl implements ManagerFacade {

    @Inject
    private MatchService matchService;

    @Inject
    private TeamService teamService;

    @Inject
    private LeagueService leagueService;

    @Inject
    private ManagerService managerService;

    @Autowired
    private BeanMappingService beanMappingService;

    @Override
    public List<ManagerDTO> getManagersByNationality(NationalityEnum nationality) {

        List<Manager> managerList = managerService.fetchByNationality(nationality);
        return beanMappingService.mapTo(managerList, ManagerDTO.class);
    }

    @Override
    public ManagerDTO getManagerById(Long id) {

        Manager manager = managerService.fetchById(id);
        return beanMappingService.mapTo(manager, ManagerDTO.class);
    }

    @Override
    public ManagerDTO getManagerByName(String name) {

        Manager manager = managerService.fetchByName(name);
        return beanMappingService.mapTo(manager, ManagerDTO.class);
    }

    @Override
    public ManagerDTO getManagerByEmail(String email) {

        Manager manager = managerService.fetchByName(email);
        return beanMappingService.mapTo(manager, ManagerDTO.class);
    }

    @Override
    public ManagerDTO getManagerByTeam(Long teamId) {

        Team team = teamService.fetchById(teamId);
        Manager manager = managerService.fetchByTeam(team);
        return beanMappingService.mapTo(manager, ManagerDTO.class);
    }
}
