package pl.szymonwrobel.tms.dtos;


import com.fasterxml.jackson.annotation.JsonIgnore;
import pl.szymonwrobel.tms.enums.UserType;

public class UserDTO {

    private Long id;
    private String login;
    @JsonIgnore
    private String password;
    private String firstName;
    private String lastName;
    private String isActive;
    private String userTypeDescription;

    public UserDTO() {
    }

    public UserDTO(Long id, String login, String password, String firstName, String lastName, String isActive, String userTypeDescription) {
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

    public UserDTO setId(Long id) {
        this.id = id;
        return this;
    }

    public String getLogin() {
        return login;
    }

    public UserDTO setLogin(String login) {
        this.login = login;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserDTO setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getIsActive() {
        return isActive;
    }

    public UserDTO setIsActive(String isActive) {
        this.isActive = isActive;
        return this;
    }

    public String getUserType() {
        return userTypeDescription;
    }

    public UserDTO setUserType(String userTypeDescription) {
        this.userTypeDescription = userTypeDescription;
        return this;
    }
}
