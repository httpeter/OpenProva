package org.op.data.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@DiscriminatorColumn(length = 31)

public class Person implements Serializable
{

    @Column(unique = false, updatable = true, insertable = true, nullable = true, length = 255, scale = 0, precision = 0)
    @Basic
    private String firstName;

    @Column(unique = false, updatable = true, insertable = true, nullable = true, length = 255, scale = 0, precision = 0)
    @Basic
    private String lastName;

    @Column(unique = false, updatable = true, insertable = true, nullable = true, length = 255, scale = 0, precision = 0)
    @Basic
    private String phone;

    @Column(unique = true, updatable = true, insertable = true, nullable = false, length = 255, scale = 0, precision = 0)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = false, updatable = true, insertable = true, nullable = true, length = 255, scale = 0, precision = 0)
    @Basic
    private String email;



    public Person()
    {

    }



    public String getFirstName()
    {
        return this.firstName;
    }



    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }



    public String getLastName()
    {
        return this.lastName;
    }



    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }



    public String getPhone()
    {
        return this.phone;
    }



    public void setPhone(String phone)
    {
        this.phone = phone;
    }



    public Long getId()
    {
        return this.id;
    }



    public void setId(Long id)
    {
        this.id = id;
    }



    public String getEmail()
    {
        return this.email;
    }



    public void setEmail(String email)
    {
        this.email = email;
    }
}
