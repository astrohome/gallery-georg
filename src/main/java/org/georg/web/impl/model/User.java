package org.georg.web.impl.model;

import com.sun.istack.internal.NotNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "users")
public class User implements Serializable, UserDetails {

    @Id
    @Column
    @NotNull
    private String login;
    @Column
    @NotNull
    private String password;
    @Column
    private String role;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column
    private String activationCode;
    @Column
    @NotNull
    private boolean activated;

    /**
     * Login of user is email.
     *
     * @return
     */
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Returns collection of all roles.
     *
     * @return
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.commaSeparatedStringToAuthorityList(role);
    }

    /**
     * Hashed password (md5). No salt yet.
     *
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     * Internal usage (equvalent to {@link #getLogin()}).
     *
     * @return
     */
    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return activated;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * List of roles, separated by comma. Usually just one role.
     *
     * @return
     */
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    /**
     * First name of client.
     *
     * @return
     */
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Last (family) name of client.
     *
     * @return
     */
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Stored in DB for first-time email activation.
     *
     * @return
     */
    public String getActivationCode() {
        return activationCode;
    }

    public void setActivationCode(String activationCode) {
        this.activationCode = activationCode;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }
}
