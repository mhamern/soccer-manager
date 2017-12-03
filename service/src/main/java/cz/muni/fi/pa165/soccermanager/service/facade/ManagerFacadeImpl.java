package cz.muni.fi.pa165.soccermanager.service.facade;

import cz.muni.fi.pa165.soccermanager.dto.AuthenticateManagerDTO;
import cz.muni.fi.pa165.soccermanager.dto.CreateManagerDTO;
import cz.muni.fi.pa165.soccermanager.dto.ManagerDTO;
import cz.muni.fi.pa165.soccermanager.entity.Manager;
import cz.muni.fi.pa165.soccermanager.entity.Player;
import cz.muni.fi.pa165.soccermanager.entity.Team;
import cz.muni.fi.pa165.soccermanager.enums.NationalityEnum;
import cz.muni.fi.pa165.soccermanager.facade.ManagerFacade;
import cz.muni.fi.pa165.soccermanager.service.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

/**
 * @author 456519 Filip Lux
 * @version 11/20/2017
 */
@Service
@Transactional
public class ManagerFacadeImpl implements ManagerFacade {

    private ManagerService managerService;

    private TeamService teamService;

    private BeanMappingService beanMappingService;

    @Inject
    public ManagerFacadeImpl(
            ManagerService managerService,
            TeamService teamService,
            BeanMappingService beanMappingService
    ) {
        this.managerService = managerService;
        this.teamService = teamService;
        this.beanMappingService = beanMappingService;
    }


    @Override
    public List<ManagerDTO> findManagersByNationality(NationalityEnum nationality) {

        List<Manager> managerList = managerService.fetchByNationality(nationality);
        return beanMappingService.mapTo(managerList, ManagerDTO.class);
    }

    @Override
    public ManagerDTO findManagerById(Long id) {

        Manager manager = managerService.fetchById(id);
        return beanMappingService.mapTo(manager, ManagerDTO.class);
    }

    @Override
    public ManagerDTO findManagerByName(String name) {

        Manager manager = managerService.fetchByName(name);
        return beanMappingService.mapTo(manager, ManagerDTO.class);
    }

    @Override
    public ManagerDTO findManagerByEmail(String email) {

        Manager manager = managerService.fetchByEmail(email);
        return (manager == null) ? null : beanMappingService.mapTo(manager, ManagerDTO.class);
    }

    @Override
    public ManagerDTO findManagerByTeam(Long teamId) {

        Team team = teamService.fetchById(teamId);
        Manager manager = managerService.fetchByTeam(team);
        return beanMappingService.mapTo(manager, ManagerDTO.class);
    }


    @Override
    public List<ManagerDTO> getAdmins() {

        List<Manager> managerList = managerService.fetchAdmins();
        return beanMappingService.mapTo(managerList, ManagerDTO.class);
    }

    @Override
    public void registerManager(CreateManagerDTO managerDTO, String unencryptedPassword) {
        Manager managerEntity = beanMappingService.mapTo(managerDTO, Manager.class);
        managerService.create(managerEntity, unencryptedPassword);

    }

    @Override
    public boolean authenticate(AuthenticateManagerDTO manager) {
        return managerService.authenticate(managerService.fetchByEmail(manager.getMail()), manager.getPassword());
    }

    @Override
    public boolean isAdmin(ManagerDTO manager) {
        return managerService.isAdmin(beanMappingService.mapTo(manager, Manager.class));
    }
}
