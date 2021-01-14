package pl.szymonwrobel.tms.entities;

import pl.szymonwrobel.tms.enums.UserType;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<TrainingApplicationEntity> applications = new HashSet<>();

    public UserEntity() {
    }

    public UserEntity(Long id, String login, String password, UserType userType, Boolean isActive, String firstName, String lastName, Set<TrainingApplicationEntity> applications) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.userType = userType;
        this.isActive = isActive;
        this.firstName = firstName;
        this.lastName = lastName;
        this.applications = applications;
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

    public UserType getUserType() {
        return userType;
    }

    public UserEntity setUserType(UserType usertype) {
        this.userType = usertype;
        return this;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public UserEntity setIsActive(Boolean isActive) {
        this.isActive = isActive;
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

    public Set<TrainingApplicationEntity> getApplications() {
        return applications;
    }

    public UserEntity setApplications(Set<TrainingApplicationEntity> applications) {
        this.applications = applications;
        return this;
    }
}
