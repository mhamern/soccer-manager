package cz.muni.fi.pa165.soccermanager.facade;

import cz.muni.fi.pa165.soccermanager.dto.AuthenticateManagerDTO;
import cz.muni.fi.pa165.soccermanager.dto.CreateManagerDTO;
import cz.muni.fi.pa165.soccermanager.dto.ManagerDTO;
import cz.muni.fi.pa165.soccermanager.enums.NationalityEnum;

import java.util.List;

/**
 * @author 456519 Filip Lux
 * @version 1/12/2017.
 */
public interface ManagerFacade {
    /**
     * get list of all managers of chosen nationality
     * @param nationality nationality from nationality enum
     * @return list of registered managers
     */
    public List<ManagerDTO> findManagersByNationality(NationalityEnum nationality);

    /**
     * find manager by manager ID
     * @param id manager's ID
     * @return data transfer object of Manager
     */
    public ManagerDTO findManagerById(Long id);

    /**
     * find manager by name
     * @param name manager's name
     * @return data transfer object of Manager
     */
    public ManagerDTO findManagerByName(String name);

    /**
     * find manager by registered email
     * @param email manager's email
     * @return data transfer object of Manager
     */
    public ManagerDTO findManagerByEmail(String email);

    /**
     * find manager by team
     * @param teamId manager's email
     * @return data transfer object of Manager
     */
    public ManagerDTO findManagerByTeam(Long teamId);

    /**
     * gets all admins
     * @return list of all registered admins
     */
    public List<ManagerDTO> getAdmins();

    /**
     * register new manager
     * @param manager information about manager
     * @param unencryptedPassword password
     */
    public void registerManager(CreateManagerDTO manager, String unencryptedPassword);

    /**
     * try to authenticate manager
     * @param manager information about manager
     */
    public boolean authenticate(AuthenticateManagerDTO manager);

}
