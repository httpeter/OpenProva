//
// This file was generated by the JPA Modeler
//
package org.op.data.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity

public class Activity implements Serializable
{

    @Column(unique = false, updatable = true, insertable = true, nullable = true, length = 255, scale = 0, precision = 0)
    @Basic
    private Date activityDate;

    @Column(unique = false, updatable = true, insertable = true, nullable = true, length = 255, scale = 0, precision = 0)
    @Basic
    private String description;

    @OneToOne(optional = true, targetEntity = Project.class)
    private Project project;

    @Column(unique = false, updatable = true, insertable = true, nullable = true, length = 255, scale = 0, precision = 0)
    @Basic
    private Time startTime;

    @Column(unique = false, updatable = true, insertable = true, nullable = true, length = 255, scale = 0, precision = 0)
    @Basic
    private String location;

    @Column(unique = true, updatable = true, insertable = true, nullable = false, length = 255, scale = 0, precision = 0)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = false, updatable = true, insertable = true, nullable = true, length = 255, scale = 0, precision = 0)
    @Basic
    private Time endTime;



    public Activity()
    {

    }



    public Date getActivityDate()
    {
        return this.activityDate;
    }



    public void setActivityDate(Date activityDate)
    {
        this.activityDate = activityDate;
    }



    public String getDescription()
    {
        return this.description;
    }



    public void setDescription(String description)
    {
        this.description = description;
    }



    public Project getProject()
    {
        return this.project;
    }



    public void setProject(Project project)
    {
        this.project = project;
    }



    public Time getStartTime()
    {
        return this.startTime;
    }



    public void setStartTime(Time startTime)
    {
        this.startTime = startTime;
    }



    public String getLocation()
    {
        return this.location;
    }



    public void setLocation(String location)
    {
        this.location = location;
    }



    public Long getId()
    {
        return this.id;
    }



    public void setId(Long id)
    {
        this.id = id;
    }



    public Time getEndTime()
    {
        return this.endTime;
    }



    public void setEndTime(Time endTime)
    {
        this.endTime = endTime;
    }
}
