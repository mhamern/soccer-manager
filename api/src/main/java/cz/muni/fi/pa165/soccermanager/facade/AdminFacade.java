package cz.muni.fi.pa165.soccermanager.facade;

import cz.muni.fi.pa165.soccermanager.dto.AdminDTO;

/**
 * @author 445720 Martin Hamernik
 * @version 11/16/2017.
 */
public interface AdminFacade {
    public AdminDTO getAdminById(Long id);
}
