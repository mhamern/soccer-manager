package cz.muni.fi.pa165.soccermanager.facade;

import cz.muni.fi.pa165.soccermanager.dto.ManagerDTO;
import cz.muni.fi.pa165.soccermanager.enums.NationalityEnum;

import java.util.List;

/**
 * @author 445720 Martin Hamernik
 * @version 11/16/2017.
 */
public interface ManagerFacade {
    public List<ManagerDTO> getManagersByNationality(NationalityEnum nationality);

    public ManagerDTO getManagerById(Long id);

    public ManagerDTO getManagerByName(String name);

    public ManagerDTO getManagerByEmail(String email);

    public ManagerDTO getManagerByTeam(Long teamId);
}
