package org.op.data.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;

@Entity

public class User extends Person implements Serializable
{

    @Column(unique = false, updatable = true, insertable = true, nullable = true, length = 255, scale = 0, precision = 0)
    @Basic
    private String password;

    @Column(unique = false, updatable = true, insertable = true, nullable = true, length = 255, scale = 0, precision = 0)
    @Basic
    private String role;

    @Column(unique = false, updatable = true, insertable = true, nullable = true, length = 255, scale = 0, precision = 0)
    @Basic
    private String emailPassword;

    @Column(unique = false, updatable = true, insertable = true, nullable = true, length = 255, scale = 0, precision = 0)
    @Basic
    private String username;



    public User()
    {

    }



    public String getPassword()
    {
        return this.password;
    }



    public void setPassword(String password)
    {
        this.password = password;
    }



    public String getRole()
    {
        return this.role;
    }



    public void setRole(String role)
    {
        this.role = role;
    }



    public String getEmailPassword()
    {
        return this.emailPassword;
    }



    public void setEmailPassword(String emailPassword)
    {
        this.emailPassword = emailPassword;
    }



    public String getUsername()
    {
        return this.username;
    }



    public void setUsername(String username)
    {
        this.username = username;
    }
}
