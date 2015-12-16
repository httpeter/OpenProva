package org.op.data.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity

public class Contact extends Person implements Serializable
{

    @Column(unique = false, updatable = true, insertable = true, nullable = true, length = 500, scale = 0, precision = 0)
    @Basic
    private String notes;

    @ManyToOne(optional = false, targetEntity = SystemUser.class)
    private SystemUser systemUser;

    @Column(unique = false, updatable = true, insertable = true, nullable = true, length = 255, scale = 0, precision = 0)
    @Basic
    private String instrument;



    public Contact()
    {

    }



    public String getNotes()
    {
        return this.notes;
    }



    public void setNotes(String notes)
    {
        this.notes = notes;
    }



    public SystemUser getSystemUser()
    {
        return this.systemUser;
    }



    public void setSystemUser(SystemUser systemUser)
    {
        this.systemUser = systemUser;
    }



    public String getInstrument()
    {
        return this.instrument;
    }



    public void setInstrument(String instrument)
    {
        this.instrument = instrument;
    }
}
