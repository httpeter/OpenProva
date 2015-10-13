package org.op.model;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity



public class Contact extends Person implements Serializable {

    @Column(unique=false,updatable=true,insertable=true,nullable=true,length=255,scale=0,precision=0)
    @Basic
    private String notes;

    @Column(unique=false,updatable=true,insertable=true,nullable=true,length=255,scale=0,precision=0)
    @Basic
    private String instrument;

    @ManyToOne(optional=true,targetEntity = User.class)
    private User user;

    public Contact() {

    }
   
    public String getNotes() {
        return this.notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
   
    public String getInstrument() {
        return this.instrument;
    }

    public void setInstrument(String instrument) {
        this.instrument = instrument;
    }
   
    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
