package org.om.model;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Project implements Serializable {

    @Column(unique=false,updatable=true,insertable=true,nullable=true,length=512,scale=0,precision=0)
    @Basic
    private String projectRepertoire;
    @Column(unique=false,updatable=true,insertable=true,nullable=true,length=255,scale=0,precision=0)
    @Basic
    private int requiredPresence;
    @Column(unique=false,updatable=true,insertable=true,nullable=true,length=255,scale=0,precision=0)
    @Basic
    private Date projectStartDate;
    @Column(unique=false,updatable=true,insertable=true,nullable=true,length=255,scale=0,precision=0)
    @Basic
    private Date projectEndDate;
    @Column(unique=false,updatable=true,insertable=true,nullable=true,length=255,scale=0,precision=0)
    @Basic
    private boolean active;
    @Column(unique=true,updatable=true,insertable=true,nullable=false,length=255,scale=0,precision=0)
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    @Column(unique=false,updatable=true,insertable=true,nullable=true,length=255,scale=0,precision=0)
    @Basic
    private String projectName;

    public Project() {

    }
   
    public String getProjectRepertoire() {
        return this.projectRepertoire;
    }

    public void setProjectRepertoire(String projectRepertoire) {
        this.projectRepertoire = projectRepertoire;
    }
   
    public int getRequiredPresence() {
        return this.requiredPresence;
    }

    public void setRequiredPresence(int requiredPresence) {
        this.requiredPresence = requiredPresence;
    }
   
    public Date getProjectStartDate() {
        return this.projectStartDate;
    }

    public void setProjectStartDate(Date projectStartDate) {
        this.projectStartDate = projectStartDate;
    }
   
    public Date getProjectEndDate() {
        return this.projectEndDate;
    }

    public void setProjectEndDate(Date projectEndDate) {
        this.projectEndDate = projectEndDate;
    }
    
    public boolean isActive() {
        return this.active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
   
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
   
    public String getProjectName() {
        return this.projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
}
