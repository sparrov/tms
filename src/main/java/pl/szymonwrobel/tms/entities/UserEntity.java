package pl.szymonwrobel.tms.entities;

import pl.szymonwrobel.tms.enums.UserType;

import javax.persistence.*;

@Entity
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String password;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    private Boolean isActive;
    private String firstName;
    private String lastName;

    public UserEntity() {
    }

    public UserEntity(Long id, String login, String password, UserType userType, Boolean isActive, String firstName, String lastName) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.userType = userType;
        this.isActive = isActive;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public UserEntity(String login, String password, UserType userType, Boolean isActive, String firstName, String lastName) {
        this.login = login;
        this.password = password;
        this.userType = userType;
        this.isActive = isActive;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Long getId() {
        return id;
    }

    public UserEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getLogin() {
        return login;
    }

    public UserEntity setLogin(String login) {
        this.login = login;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserEntity setPassword(String password) {
        this.password = password;
        return this;
    }

    public UserType getUsertype() {
        return userType;
    }

    public UserEntity setUsertype(UserType usertype) {
        this.userType = usertype;
        return this;
    }

    public Boolean getActive() {
        return isActive;
    }

    public UserEntity setActive(Boolean active) {
        isActive = active;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserEntity setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserEntity setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }
}
