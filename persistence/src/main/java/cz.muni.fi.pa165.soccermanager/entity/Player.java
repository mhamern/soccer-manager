package cz.muni.fi.pa165.soccermanager.entity;

import cz.muni.fi.pa165.soccermanager.enums.NationalityEnum;
import cz.muni.fi.pa165.soccermanager.enums.PositionEnum;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.Year;
import java.util.Date;

/**
 * @author Jan Novak
 */
@Entity
public class Player {

    public static class PlayerBuilder {

        private String name;
        private PositionEnum position;
        private NationalityEnum nationality;
        private int number;
        private LocalDate birthDate;
        private int shooting;
        private int passing;
        private int speed;
        private int defence;
        private int strength;
        private int goalkeeping;

        public PlayerBuilder(String name, PositionEnum position, NationalityEnum nationality, LocalDate birthDate) {
            this.name = name;
            this.position = position;
            this.nationality = nationality;
            this.birthDate = birthDate;
        }

        public PlayerBuilder name(String name) {
            this.name = name;
            return this;
        }

        public PlayerBuilder position(PositionEnum position) {
            this.position = position;
            return this;
        }

        public PlayerBuilder nationality(NationalityEnum nationality) {
            this.nationality = nationality;
            return this;
        }

        public PlayerBuilder birthDate(LocalDate birthDate) {
            this.birthDate = birthDate;
            return this;
        }

        public PlayerBuilder number(int number) {
            this.number = number;
            return this;
        }

        public PlayerBuilder shooting(int shooting) {
            this.shooting = shooting;
            return this;
        }

        public PlayerBuilder passing(int passing) {
            this.passing = passing;
            return this;
        }

        public PlayerBuilder speed(int speed) {
            this.speed = speed;
            return this;
        }

        public PlayerBuilder defence(int defence) {
            this.defence = defence;
            return this;
        }

        public PlayerBuilder strength(int strength) {
            this.strength = strength;
            return this;
        }

        public PlayerBuilder goalkeeping(int goalkeeping) {
            this.goalkeeping = goalkeeping;
            return this;
        }

        public Player build() {
            return new Player(this);
        }
    }



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    private PositionEnum position;

    @Enumerated(EnumType.STRING)
    private NationalityEnum nationality;

    @Column(nullable = false)
    private int number;

    @Column(nullable = false)
    private int shooting;

    @Column(nullable = false)
    private int passing;

    @Column(nullable = false)
    private int speed;

    @Column(nullable = false)
    private int defence;

    @Column(nullable = false)
    private int strength;

    @Column(nullable = false)
    private int goalkeeping;

    public Player() {
    }

    public Player(PlayerBuilder builder) {
        this.name = builder.name;
        this.position = builder.position;
        this.nationality = builder.nationality;
        this.number = builder.number;
        this.birthDate = builder.birthDate;
        this.shooting = builder.shooting;
        this.passing = builder.passing;
        this.speed = builder.speed;
        this.defence = builder.defence;
        this.strength = builder.strength;
        this.goalkeeping = builder.goalkeeping;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) { this.id = id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate born) {
        this.birthDate = born;
    }

    public PositionEnum getPosition() {
        return position;
    }

    public void setPosition(PositionEnum position) {
        this.position = position;
    }

    public NationalityEnum getNationality() {
        return nationality;
    }

    public void setNationality(NationalityEnum nationality) {
        this.nationality = nationality;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getShooting() {
        return shooting;
    }

    public void setShooting(int shooting) {
        this.shooting = shooting;
    }

    public int getPassing() {
        return passing;
    }

    public void setPassing(int passing) {
        this.passing = passing;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getDefence() {
        return defence;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getGoalkeeping() {
        return goalkeeping;
    }

    public void setGoalkeeping(int goalkeeping) {
        this.goalkeeping = goalkeeping;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player)) return false;

        Player player = (Player) o;

        if (getPosition() != player.getPosition()) return false;
        if (getNumber() != player.getNumber()) return false;
        if (getName() != null ? !getName().equals(player.getName()) : player.getName() != null) return false;
        if (getBirthDate() != null ? !getBirthDate().equals(player.getBirthDate()) : player.getBirthDate() != null) return false;
        return getNationality() != null ? getNationality().equals(player.getNationality()) : player.getNationality() == null;
    }

    @Override
    public int hashCode() {
        int result = 7;
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (getBirthDate() != null ? getBirthDate().hashCode() : 0);
        result = 31 * result + getPosition().hashCode();
        result = 31 * result + (getNationality() != null ? getNationality().hashCode() : 0);
        result = 31 * result + getNumber();
        return result;
    }

    @Override
    public String toString() {
        return "Player {" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", born=" + birthDate +
                ", position=" + position +
                ", nationality='" + nationality + '\'' +
                ", number=" + number +
                '}';
    }
}
