package org.georg.web.impl.model;

import org.hibernate.validator.constraints.Email;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class User implements Serializable, UserDetails {

    @Id
    @Column(unique = true)
    @NotNull
    @Email
    private String email;

    @Column
    @NotNull
    @Size(min = 4)
    private String password;

    @Column
    private String role;

    @Column
    @NotNull
    @Size(min = 2)
    private String firstName;

    @Column
    @NotNull
    @Size(min = 2)
    private String lastName;

    @Column
    @NotNull
    private String activationCode;

    @Column
    @NotNull
    private boolean activated;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
    private Set<Order> orders = new HashSet<>();

    /**
     * Login of user is email.
     *
     * @return
     */
    public String getLogin() {
        return email;
    }

    public void setLogin(String login) {
        this.email = login;
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
        return email;
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

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }
}
