package pl.szymonwrobel.tms.entities;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import pl.szymonwrobel.tms.enums.UserType;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name = "users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(unique = true, nullable = false, length = 24)
    @Size(min = 3, max = 24)
    private String login;

    @NotEmpty
    @Column(nullable = false, length = 60)
    @Size(min = 8, max = 60)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 24)
    private UserType userType;

    @NotNull
    private Boolean isActive;

    @NotEmpty
    @Column(nullable = false, length = 24)
    @Size(min = 3, max = 24)
    private String firstName;

    @NotEmpty
    @Column(nullable = false, length = 24)
    @Size(min = 3, max = 24)
    private String lastName;

    //@NotNull //TODO: do ustalenia
    @ElementCollection
    //automatyczna relacja 1:wielu, któa tworzy dodatkową tabelę  user_entity_authorities - dzięki temu nie muszę tworzyć osobnej encji i relacji
    private List<SimpleGrantedAuthority> authorities;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.ALL})
    private Set<TrainingApplicationEntity> applications = new HashSet<>();

    public UserEntity() {
    }

    public UserEntity(Long id, String login, String password, UserType userType, Boolean isActive, String firstName, String lastName, List<SimpleGrantedAuthority> authorities, Set<TrainingApplicationEntity> applications) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.userType = userType;
        this.isActive = isActive;
        this.firstName = firstName;
        this.lastName = lastName;
        this.applications = applications;
        this.authorities = authorities;
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

    public List<SimpleGrantedAuthority> getAuthorities() {
        return authorities;
    }

    public UserEntity setAuthorities(List<SimpleGrantedAuthority> authorities) {
        this.authorities = authorities;
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