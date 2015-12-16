package org.op.data.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;

@Entity

public class SystemUser extends Person implements Serializable
{

    @Column(unique = false, updatable = true, insertable = true, nullable = true, length = 255, scale = 0, precision = 0)
    @Basic
    private String password;

    @Column(unique = false, updatable = true, insertable = true, nullable = true, length = 255, scale = 0, precision = 0)
    @Basic
    private String emailPassword;

    @Column(unique = false, updatable = true, insertable = true, nullable = true, length = 255, scale = 0, precision = 0)
    @Basic
    private String userRole;

    @Column(unique = false, updatable = true, insertable = true, nullable = true, length = 255, scale = 0, precision = 0)
    @Basic
    private String username;



    public SystemUser()
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



    public String getEmailPassword()
    {
        return this.emailPassword;
    }



    public void setEmailPassword(String emailPassword)
    {
        this.emailPassword = emailPassword;
    }



    public String getUserRole()
    {
        return this.userRole;
    }



    public void setUserRole(String userRole)
    {
        this.userRole = userRole;
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
