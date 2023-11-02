package com.addy.rest.app.dto;


import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserDTO extends BaseDTO implements Serializable {
    private String username;
    private String email;
    private String password;
    private String contactNo;
    private String industry;
    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;
    private boolean isEnabled;
    private AddressDTO address;
    private Set<String> authorities = new HashSet<>();
    private List<String> roles;

    private boolean emailVerified;

    private List<SocialAccountDTO> socialAccounts;

    public UserDTO() {
    }

    public UserDTO(String id, Date creationDate, Date modificationDate, boolean deleted, String username, String email, String password, String contactNo, String industry, boolean isAccountNonExpired, boolean isAccountNonLocked, boolean isCredentialsNonExpired, boolean isEnabled, AddressDTO address, Set<String> authorities, List<String> roles, boolean emailVerified, List<SocialAccountDTO> socialAccounts) {
        super(id, creationDate, modificationDate, deleted);
        this.username = username;
        this.email = email;
        this.password = password;
        this.contactNo = contactNo;
        this.industry = industry;
        this.isAccountNonExpired = isAccountNonExpired;
        this.isAccountNonLocked = isAccountNonLocked;
        this.isCredentialsNonExpired = isCredentialsNonExpired;
        this.isEnabled = isEnabled;
        this.address = address;
        this.authorities = authorities;
        this.roles = roles;
        this.emailVerified = emailVerified;
        this.socialAccounts = socialAccounts;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        isAccountNonExpired = accountNonExpired;
    }

    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        isAccountNonLocked = accountNonLocked;
    }

    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        isCredentialsNonExpired = credentialsNonExpired;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }


    public AddressDTO getAddress() {
        return address;
    }

    public void setAddress(AddressDTO address) {
        this.address = address;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public boolean isEmailVerified() {
        return emailVerified;
    }

    public void setEmailVerified(boolean emailVerified) {
        this.emailVerified = emailVerified;
    }

    public Set<String> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<String> authorities) {
        this.authorities = authorities;
    }

    public List<SocialAccountDTO> getSocialAccounts() {
        return socialAccounts;
    }

    public void setSocialAccounts(List<SocialAccountDTO> socialAccounts) {
        this.socialAccounts = socialAccounts;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", contactNo='" + contactNo + '\'' +
                ", industry='" + industry + '\'' +
                ", isAccountNonExpired=" + isAccountNonExpired +
                ", isAccountNonLocked=" + isAccountNonLocked +
                ", isCredentialsNonExpired=" + isCredentialsNonExpired +
                ", isEnabled=" + isEnabled +
                ", address=" + address +
                ", authorities=" + authorities +
                ", roles=" + roles +
                ", id='" + id + '\'' +
                ", creationDate=" + creationDate +
                ", modificationDate=" + modificationDate +
                ", deleted=" + deleted +
                ", createdBy='" + createdBy + '\'' +
                ", modifiedBy='" + modifiedBy + '\'' +
                ", emailVerified='" + emailVerified + '\'' +
                ", socialAccounts='" + socialAccounts + '\'' +
                '}';
    }
}
