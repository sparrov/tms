package pl.szymonwrobel.tms.dtos;


import com.fasterxml.jackson.annotation.JsonIgnore;

public class TrainerUserDTO {

    private Long id;
    private String login;
    @JsonIgnore
    private String password;
    private String firstName;
    private String lastName;
    private String isActive;
    private String userTypeDescription;

    public TrainerUserDTO() {
    }

    public TrainerUserDTO(Long id, String login, String password, String firstName, String lastName, String isActive, String userTypeDescription) {
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

    public TrainerUserDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getLogin() {
        return login;
    }

    public TrainerUserDTO setLogin(String login) {
        this.login = login;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public TrainerUserDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public TrainerUserDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public TrainerUserDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getIsActive() {
        return isActive;
    }

    public TrainerUserDTO setIsActive(String isActive) {
        this.isActive = isActive;
        return this;
    }

    public String getUserType() {
        return userTypeDescription;
    }

    public TrainerUserDTO setUserType(String userTypeDescription) {
        this.userTypeDescription = userTypeDescription;
        return this;
    }
}
