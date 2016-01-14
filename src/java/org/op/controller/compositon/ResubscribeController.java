//This webapp was created to enable the managers of amateur orchestras/ensembles 
//to have a basic database with contact information and to enable them to send 
//subscription requests to a large amount of contacts for a specific musical project.
//Contacts receive a personalized email containing a URL with which they can subscribe
//to a project and enter their presence on given rehearsal dates. 
//
//A management-login enables the orchestra managers/coordinators to see who will
//be present at a given date, view contact information and modify projects and 
//subscriptions.
//
//You're free to use the code as you please as long as it does not hurt anyone 
//(Apache2 lisence: http://www.apache.org/licenses/LICENSE-2.0 ). 
//
//For questions, feel free to contact me at httpeter@gmail.com
package org.op.controller.compositon;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.op.data.model.Activity;
import org.op.data.model.Contact;
import org.op.data.model.Project;
import org.op.data.model.Subscription;
import org.op.data.repository.SubscribersRepository;
import org.op.util.FMessage;
import org.op.util.MailFactory;

@ManagedBean
@ViewScoped
public class ResubscribeController implements Serializable
{

    private final SubscribersRepository subscribersRepository;

    private List<Project> activeProjects;

    private final MailFactory mailFactory;

    private final FMessage msg;

    private Project selectedProject;

    private List<Subscription> subscriptions;

    //Moet weg. alleen nog bestaande contanten
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



    public List<Project> getActiveProjects()
    {
        return activeProjects;
    }



    public void setActiveProjects(List<Project> activeProjects)
    {
        this.activeProjects = activeProjects;
    }



    public Project getSelectedProject()
    {
        return selectedProject;
    }



    public void setSelectedProject(Project selectedProject)
    {
        this.selectedProject = selectedProject;
    }



    public List<Subscription> getSubscriptions()
    {
        return subscriptions;
    }



    public void setSubscriptions(List<Subscription> subscriptions)
    {
        this.subscriptions = subscriptions;
    }



    //</editor-fold>
    public ResubscribeController()
    {
        msg = new FMessage();
        subscribersRepository = new SubscribersRepository("OpenProvaPU");

        //retrieving all projects
        List<Project> allProjects = subscribersRepository
                .getResultList(Project.class);
        
        //then only adding the active ones to the list
        activeProjects = allProjects.stream()
                .filter(p -> p.isActive())
                .collect(Collectors.toList());

        mailFactory = new MailFactory();
        selectedProject = new Project();
    }



    /**
     * Loading project data and cloning dates Code below is a bad idea and
     * should be improved!!!!!! But I really hate JSF converters.... :(
     */
    public void selectProject()
    {
        activeProjects.forEach((ap)
                -> 
                {
                    if (selectedProject.getId() != null
                            && Objects.equals(ap.getId(),
                                    selectedProject.getId()))
                    {
                        selectedProject = ap;
                    }
        });

        if (selectedProject.getId() != null)
        {
            List<Activity> activities = subscribersRepository
                    .getActivities(selectedProject);

            subscriptions = new ArrayList(activities.size());

            activities.forEach((a)
                    -> 
                    {
                        Subscription s = new Subscription();
                        s.setActivityDate(a.getActivityDate());
                        s.setDescription(a.getDescription());
                        s.setEndTime(a.getEndTime());
                        s.setLocation(a.getLocation());
//                        s.setProjectId(ma.getProjectId());
                        s.setStartTime(a.getStartTime());
                        subscriptions.add(s);
            });
        } else
        {
//            selectedProject = new Project();

        }
    }



    /**
     * Simple method for selecting all dates of a project.
     */
    public void selectAllDates()
    {
        if (subscriptions != null)
        {
            subscriptions.forEach((activity)
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

    /**
     * This should be changed to facilitate resubscriptions only.
     */
//    public void saveAndMailNewSubscription()
//    {
//
//        //Saving the new Contact
//        if (mailFactory.addressValid(newContact.getEmail())
//                && !subscribersRepository.isExistingContact(newContact)
//                && subscribersRepository.persisted(newContact))
//        {
//            msg.info("Saved "
//                    + newContact.getFirstName()
//                    + " "
//                    + newContact.getLastName());
//
//            //now saving the projectdata
//            StringBuilder resultLog = new StringBuilder();
//            subscriptions.forEach((newActivity)
//                    -> 
//                    {
//                        newActivity.setContactId(newContact.getId());
//                        if (!subscribersRepository.persisted(newActivity))
//                        {
//                            resultLog.append(newActivity.getActivityDate());
//                        }
//            });
//
//            if (resultLog.length() == 0)
//            {
//                msg.info("Subscribed "
//                        + newContact.getFirstName()
//                        + " "
//                        + newContact.getLastName()
//                        + "\nTo project: "
//                        + selectedProject.getProjectName());
//
//                try
//                {
//                    mailFactory.sendNewMemberSubscriptionMail(newContact,
//                            selectedProject,
//                            subscriptions,
//                            additionalMessage);
//
//                    subscribersRepository.close();
//
//                } catch (Exception e)
//                {
//                    msg.fatal("Sending email failed\n\n"
//                            + e.getMessage());
//                    e.printStackTrace(System.out);
//                }
//
//                //Here we go sending an email...
//                newContact = new Contact();
//            } else
//            {
//                msg.error("Error saving " + selectedProject.getProjectName()
//                        + "\n\n" + resultLog);
//            }
//        } else
//        {
//            msg.error("Error saving "
//                    + newContact.getFirstName()
//                    + " "
//                    + newContact.getLastName());
//            msg.info("Contact email already exists, or is invalid");
//        }
//
//    }
}
