package se.lexicon.entityexercise.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity(name = "app_users")
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;
    @Column(length = 100, nullable = false, unique = true)
    private String useName;
    @Column(length = 100, nullable = false)
    private String firstname;
    @Column(length = 100, nullable = false)
    private String lastName;
    @Column(nullable = false)
    private LocalDate birthDate;
    private boolean active;

    @Column(length = 100, nullable = false)
    private String passWord;

    public AppUser() {
    }

    public AppUser(String useName, String firstname, String lastName, LocalDate birthDate, String passWord) {
        this.useName = useName;
        this.firstname = firstname;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.passWord = passWord;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUseName() {
        return useName;
    }

    public void setUseName(String useName) {
        this.useName = useName;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public boolean isActive() {
        return active;
    }

    public void toggleActive(boolean active) {
        this.active = active;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppUser appUser = (AppUser) o;
        return getUserId() == appUser.getUserId() && isActive() == appUser.isActive() && Objects.equals(getUseName(), appUser.getUseName()) && Objects.equals(getFirstname(), appUser.getFirstname()) && Objects.equals(getLastName(), appUser.getLastName()) && Objects.equals(getBirthDate(), appUser.getBirthDate()) && Objects.equals(getPassWord(), appUser.getPassWord());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserId(), getUseName(), getFirstname(), getLastName(), getBirthDate(), isActive(), getPassWord());
    }

    @Override
    public String toString() {
        return "AppUser{" +
                "userId=" + userId +
                ", useName='" + useName + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", active=" + active +
                ", passWord='" + passWord + '\'' +
                '}';
    }
}
