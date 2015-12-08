package org.op.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.op.data.model.Activity;
import org.op.data.model.Contact;
import org.op.data.model.Project;

import org.op.data.repositories.SubscribersRepository;
import org.op.util.FMessage;
import org.op.util.MailFactory;

@ManagedBean
@ViewScoped
public class ResubscribeController implements Serializable
{

    private final SubscribersRepository subscribersRepository = new SubscribersRepository("OpenProvaPU");

    private List<Project> projects = subscribersRepository.getResultList(Project.class);

    private final MailFactory mailFactory = new MailFactory();

    private final FMessage msg = new FMessage();

    private Project selectedProject = new Project();

    private List<Activity> projectActivities;

    private Contact newContact = new Contact();

    private boolean allDatesSelected;

    private String additionalMessage;



    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public String getAdditionalMessage()
    {
        return additionalMessage;
    }



    public void setAdditionalMessage(String additionalMessage)
    {
        this.additionalMessage = additionalMessage;
    }



    public boolean isAllDatesSelected()
    {
        return allDatesSelected;
    }



    public void setAllDatesSelected(boolean allDatesSelected)
    {
        this.allDatesSelected = allDatesSelected;
    }



    public Contact getNewContact()
    {
        return newContact;
    }



    public void setNewContact(Contact newContact)
    {
        this.newContact = newContact;
    }



    public List<Project> getProjects()
    {
        return projects;
    }



    public void setProjects(List<Project> projects)
    {
        this.projects = projects;
    }



    public Project getSelectedProject()
    {
        return selectedProject;
    }



    public void setSelectedProject(Project selectedProject)
    {
        this.selectedProject = selectedProject;
    }



    public List<Activity> getProjectActivities()
    {
        return projectActivities;
    }



    public void setProjectActivities(List<Activity> projectActivities)
    {
        this.projectActivities = projectActivities;
    }



    //</editor-fold>
    //Loading project data and cloning dates
    public void selectProject()
    {

        projects.forEach((p)
                -> 
                {
                    if (p.getId() != null)
                    {
                        selectedProject = p;
                    } else
                    {
                        selectedProject = new Project();
                    }
        });

        if(selectedProject.getId()!=null)
        {
        
        List<Activity> masterActivities = subscribersRepository
                .getProjectActivities(selectedProject.getId(), true);

        projectActivities = new ArrayList(masterActivities.size());

        //not using the Collections clong constructor option because this
        //is not a master activity
        masterActivities.forEach((ma)
                -> 
                {
                    Activity a = new Activity();
                    a.setCommentsByContact(ma.getCommentsByContact());
                    a.setDate(ma.getDate());
                    a.setDescription(ma.getDescription());
                    a.setEndTime(ma.getEndTime());
                    a.setIsMasterActivity(false);
                    a.setLocation(ma.getLocation());
                    a.setPresent(ma.isPresent());
                    a.setProjectId(ma.getProjectId());
                    a.setStartTime(ma.getStartTime());
                    a.setIsMasterActivity(false);
                    projectActivities.add(a);
        });
        }
    }



    public void selectAllDates()
    {
        if (projectActivities != null)
        {
            projectActivities.parallelStream()
                    .forEach((activity)
                            -> 
                            {
                                if (allDatesSelected)
                                {
                                    activity.setPresent(true);
                                } else
                                {
                                    activity.setPresent(false);
                                }
                    });
        } else
        {
            msg.error("No project activities");
        }
    }



    public void saveAndMailNewSubscription()
    {
        //Saving the new Contact
        if (mailFactory.addressValid(newContact.getEmail())
                && !subscribersRepository.isExistingContact(newContact)
                && subscribersRepository.persisted(newContact))
        {
            msg.info("Saved "
                    + newContact.getFirstName()
                    + " "
                    + newContact.getLastName());

            //now saving the projectdata
            StringBuilder resultLog = new StringBuilder();
            projectActivities.forEach((newActivity)
                    -> 
                    {
                        newActivity.setContactId(newContact.getId());
                        if (!subscribersRepository.persisted(newActivity))
                        {
                            resultLog.append(newActivity.getDate());
                        }
            });

            if (resultLog.length() == 0)
            {
                msg.info("Subscribed "
                        + newContact.getFirstName()
                        + " "
                        + newContact.getLastName()
                        + "\nTo project: "
                        + selectedProject.getProjectName());
                try
                {
                    //Here we go sending an email...
                    mailFactory.sendNewMemberSubscriptionMail(newContact,
                            selectedProject,
                            projectActivities,
                            additionalMessage);
                    subscribersRepository.close();
                } catch (Exception e)
                {
                    msg.error("Subscription email could not be sent...\n\n"
                            + e.getMessage());
                }
                newContact = new Contact();
            } else
            {
                msg.error("Error saving " + selectedProject.getProjectName()
                        + "\n\n" + resultLog);
            }
        } else
        {
            msg.error("Error saving "
                    + newContact.getFirstName()
                    + " "
                    + newContact.getLastName());
            msg.info("Contact email already exists, or is invalid");
        }
    }

}
