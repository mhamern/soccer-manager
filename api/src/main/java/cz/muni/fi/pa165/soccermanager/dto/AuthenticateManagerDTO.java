package cz.muni.fi.pa165.soccermanager.dto;

/**
 * @author 445720 Martin Hamernik
 * @version 12/3/2017.
 */
public class AuthenticateManagerDTO {

    private String mail;
    private String password;

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
