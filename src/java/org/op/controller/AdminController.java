package org.op.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.op.data.model.Activity;
import org.op.data.model.Contact;
import org.op.data.model.SystemUser;
import org.op.data.repositories.AdminRepository;
import org.op.util.AESEncryptor;
import org.op.util.FMessage;
import org.op.util.MailFactory;

@ManagedBean
@SessionScoped
public class AdminController implements Serializable
{

    private final HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
            .getExternalContext()
            .getSession(false);

    private AdminRepository adminRepository;

    private MailFactory mail;

    private final FMessage msg;

    private boolean currentUserIsAdmin;

    private List<Contact> contacts;

    private List<SystemUser> allUsers;

    private SystemUser currentUser;

    private Contact selectedContact,
            newContact;

    private int activeAdminTab;



    //Private so kept here...
    private void loadContacts()
    {
        try
        {
            contacts = adminRepository.getResultList(Contact.class);
        } catch (Exception e)
        {
            msg.error("Error loading contacts\n\n"
                    .concat(e.getCause().getMessage()));
        }
    }



    public AdminController()
    {
        currentUser = new SystemUser();
        newContact = new Contact();
        selectedContact = new Contact();
        msg = new FMessage();
    }



    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public List<SystemUser> getAllUsers()
    {
        return allUsers;
    }



    public void setAllUsers(List<SystemUser> allUsers)
    {
        this.allUsers = allUsers;
    }



    public int getActiveAdminTab()
    {
        return activeAdminTab;
    }



    public void setActiveAdminTab(int activeAdminTab)
    {
        this.activeAdminTab = activeAdminTab;
    }



    public Contact getNewContact()
    {
        return newContact;
    }



    public void setNewContact(Contact newContact)
    {
        this.newContact = newContact;
    }



    public boolean isCurrentUserIsAdmin()
    {
        return currentUserIsAdmin;
    }



    public void setCurrentUserIsAdmin(boolean currentUserIsAdmin)
    {
        this.currentUserIsAdmin = currentUserIsAdmin;
    }



    public SystemUser getCurrentUser()
    {
        return currentUser;
    }



    public void setCurrentUser(SystemUser currentUser)
    {
        this.currentUser = currentUser;
    }



    public List<Contact> getContacts()
    {
        return contacts;
    }



    public void setContacts(List<Contact> contacts)
    {
        this.contacts = contacts;
    }



    public Contact getSelectedContact()
    {
        return selectedContact;
    }



    public void setSelectedContact(Contact selectedContact)
    {
        this.selectedContact = selectedContact;
    }

//</editor-fold>


    public void login()
    {

        adminRepository = new AdminRepository("OpenProvaPU");
        String userPwdEncrypted = null;
        try
        {
            userPwdEncrypted = new AESEncryptor().encrypt(currentUser.getPassword().toLowerCase());
        } catch (Exception e)
        {
            msg.fatal("Fatal Error\n\n" + e.getMessage());
        }
        SystemUser u = adminRepository.getSystemUser(currentUser.getUsername().toLowerCase(),
                userPwdEncrypted,
                "admin");

        if (u != null)
        {
            currentUser = u;
            currentUserIsAdmin = currentUser.getUserRole().equalsIgnoreCase("admin");
            List<SystemUser> au = adminRepository.getResultList(SystemUser.class);
            allUsers = new ArrayList<>(au.size());
            au.forEach((usr)
                    -> 
                    {
                        allUsers.add(usr);
            });
        } else
        {
            msg.warn("wrong login...");
        }

    }



    public void adminTabChange()
    {
        if (activeAdminTab == 1)
        {
            loadContacts();
        }
    }



    public void logout()
    {
        adminRepository.close();
        currentUserIsAdmin = false;
        currentUser = new SystemUser();
        selectedContact = new Contact();
        newContact = new Contact();

    }



    public void addNewContact()
    {
        newContact = new Contact();
    }



    public void saveNewContact()
    {
        if (newContact != null
                && !newContact.getFirstName().isEmpty()
                && mail.addressValid(newContact.getEmail())
                && adminRepository.persisted(newContact))
        {
            msg.info(newContact.getFirstName()
                    + " "
                    + newContact.getLastName()
                    + " saved");
        } else
        {
            msg.error("new contact not saved");
        }
        newContact = new Contact();
        loadContacts();
    }



    public void saveExistingContact()
    {
        mail = new MailFactory();

        if (selectedContact != null
                && !selectedContact.getFirstName().isEmpty()
                && mail.addressValid(selectedContact.getEmail())
                && adminRepository.persisted(selectedContact))
        {
            msg.info("Contact "
                    + selectedContact.getFirstName()
                    + " "
                    + selectedContact.getLastName()
                    + " saved");
        } else
        {
            msg.error("existing contact not saved");
        }
        loadContacts();
    }



    public void deleteContactAndActivities()
    {
        if (selectedContact != null
                && adminRepository.deleted(selectedContact))
        {
            msg.info("Contact '"
                    + selectedContact.getFirstName()
                    + " "
                    + selectedContact.getLastName()
                    + "' deleted");
        } else
        {
            msg.error("Contact '"
                    + selectedContact.getFirstName()
                    + " "
                    + selectedContact.getLastName()
                    + "' not deleted");
        }

        List<Activity> selectedContactActivities = adminRepository
                .getContactActivities(selectedContact, false);

        selectedContactActivities.forEach((activity)
                -> 
                {
                    if (adminRepository.subscriptionRemoved(activity))
                    {
                        msg.info("Subscription id: "
                                + activity.getId()
                                + ", "
                                + activity.getDescription()
                                + ", " + activity.getActivityDate()
                                + " removed");
                    } else
                    {
                        msg.error("Subscription id: "
                                + activity.getId()
                                + ", "
                                + activity.getDescription()
                                + ", " + activity.getActivityDate()
                                + " could not be removed");
                    }
        });

        loadContacts();
    }

}
