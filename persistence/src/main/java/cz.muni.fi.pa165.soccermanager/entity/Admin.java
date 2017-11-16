package cz.muni.fi.pa165.soccermanager.entity;

import javax.annotation.Generated;
import javax.persistence.*;

/**
 * @author 445720 Martin Hamernik
 * @version 11/1/2017.
 */
@Entity
public class Admin {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String username;

    protected Admin() {}

    public Admin(String username) {
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) { this.id = id; }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Admin admin = (Admin) o;

        return username.equals(admin.username);
    }

    @Override
    public int hashCode() {
        return username.hashCode();
    }

    @Override
    public String toString() {
        return "Admin{" +
                "username='" + username + '\'' +
                '}';
    }
}
