package pl.szymonwrobel.tms.dtos;


import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

public class StudentUserDTO {

    private Long id;
    private String login;
    @JsonIgnore
    private String password;
    private String firstName;
    private String lastName;
    private Boolean isActive;
    private String userTypeDescription;
    private List<String> appliedTrainingNames;
    private List<Long> appliedTrainingIds;

    public StudentUserDTO() {
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

    public Boolean getIsActive() {
        return isActive;
    }

    public StudentUserDTO setIsActive(Boolean isActive) {
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

    public List<String> getAppliedTrainingNames() {
        return appliedTrainingNames;
    }

    public StudentUserDTO setAppliedTrainingNames(List<String> appliedTrainingNames) {
        this.appliedTrainingNames = appliedTrainingNames;
        return this;
    }

    public List<Long> getAppliedTrainingIds() {
        return appliedTrainingIds;
    }

    public StudentUserDTO setAppliedTrainingIds(List<Long> appliedTrainingIds) {
        this.appliedTrainingIds = appliedTrainingIds;
        return this;
    }
}
