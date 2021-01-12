package pl.szymonwrobel.tms.dtos;


import com.fasterxml.jackson.annotation.JsonIgnore;

public class StudentUserDTO {

    private Long id;
    private String login;
    @JsonIgnore
    private String password;
    private String firstName;
    private String lastName;
    private String isActive;
    private String userTypeDescription;

    public StudentUserDTO() {
    }

    public StudentUserDTO(Long id, String login, String password, String firstName, String lastName, String isActive, String userTypeDescription) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isActive = isActive;
        this.userTypeDescription = userTypeDescription;
    }

    public Long getId() {
        return id;
    }

    public StudentUserDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getLogin() {
        return login;
    }

    public StudentUserDTO setLogin(String login) {
        this.login = login;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public StudentUserDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public StudentUserDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public StudentUserDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getIsActive() {
        return isActive;
    }

    public StudentUserDTO setIsActive(String isActive) {
        this.isActive = isActive;
        return this;
    }

    public String getUserTypeDescription() {
        return userTypeDescription;
    }

    public StudentUserDTO setUserTypeDescription(String userTypeDescription) {
        this.userTypeDescription = userTypeDescription;
        return this;
    }
}
