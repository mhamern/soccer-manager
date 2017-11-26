package cz.muni.fi.pa165.soccermanager.dto;

import cz.muni.fi.pa165.soccermanager.entity.Manager;
import cz.muni.fi.pa165.soccermanager.enums.NationalityEnum;
import org.jetbrains.annotations.NotNull;

import javax.validation.constraints.Size;


/**
 * @author 456519  Filip Lux
 * @version 11/23/2017.
 */
public class ManagerDTO {

    @NotNull
    @Size(min = 3, max = 50)
    private String name;

    @NotNull
    private NationalityEnum nationality;

    @NotNull
    @Size(min = 5, max = 254)
    private String email;

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public NationalityEnum getNationality() {
        return nationality;
    }

    public void setNationality(NationalityEnum nationality) {
        this.nationality = nationality;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ManagerDTO)) return false;

        ManagerDTO manager = (ManagerDTO) o;

        if (!name.equals(manager.getName())) return false;
        if (!nationality.equals(manager.getNationality())) return false;
        return email.equals(manager.getEmail());
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + nationality.hashCode();
        result = 31 * result + email.hashCode();
        return result;
    }
}
