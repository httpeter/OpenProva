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

@Entity

public class Activity implements Serializable
{

    @Column(unique = false, updatable = true, insertable = true, nullable = true, length = 255, scale = 0, precision = 0)
    @Basic
    private Date activityDate;

    @Column(unique = false, updatable = true, insertable = true, nullable = true, length = 255, scale = 0, precision = 0)
    @Id
    private Long contactId;

    @Column(unique = false, updatable = true, insertable = true, nullable = true, length = 255, scale = 0, precision = 0)
    @Basic
    private String commentsByContact;

    @Column(unique = false, updatable = true, insertable = true, nullable = true, length = 255, scale = 0, precision = 0)
    @Basic
    private String description;

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

    @Column(unique = false, updatable = true, insertable = true, nullable = true, length = 255, scale = 0, precision = 0)
    @Basic
    private boolean present;

    @Column(unique = false, updatable = true, insertable = true, nullable = true, length = 255, scale = 0, precision = 0)
    @Basic
    private boolean isMasterActivity;

    @Column(unique = false, updatable = true, insertable = true, nullable = true, length = 255, scale = 0, precision = 0)
    @Id
    private Long projectId;



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



    public Long getContactId()
    {
        return this.contactId;
    }



    public void setContactId(Long contactId)
    {
        this.contactId = contactId;
    }



    public String getCommentsByContact()
    {
        return this.commentsByContact;
    }



    public void setCommentsByContact(String commentsByContact)
    {
        this.commentsByContact = commentsByContact;
    }



    public String getDescription()
    {
        return this.description;
    }



    public void setDescription(String description)
    {
        this.description = description;
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



    public boolean isPresent()
    {
        return this.present;
    }



    public void setPresent(boolean present)
    {
        this.present = present;
    }



    public boolean isIsMasterActivity()
    {
        return this.isMasterActivity;
    }



    public void setIsMasterActivity(boolean isMasterActivity)
    {
        this.isMasterActivity = isMasterActivity;
    }



    public Long getProjectId()
    {
        return this.projectId;
    }



    public void setProjectId(Long projectId)
    {
        this.projectId = projectId;
    }
}
