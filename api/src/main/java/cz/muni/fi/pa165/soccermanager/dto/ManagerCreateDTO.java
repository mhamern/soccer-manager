package cz.muni.fi.pa165.soccermanager.dto;

import cz.muni.fi.pa165.soccermanager.enums.NationalityEnum;


/**
 * @author 456519  Filip Lux
 * @version 11/23/2017.
 */
public class ManagerCreateDTO {

    private String name;

    private NationalityEnum nationality;

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
        if (!(o instanceof ManagerCreateDTO)) return false;

        ManagerCreateDTO manager = (ManagerCreateDTO) o;

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
