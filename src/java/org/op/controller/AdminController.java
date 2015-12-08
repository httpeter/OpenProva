package org.op.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.op.data.model.Activity;
import org.op.data.model.Contact;
import org.op.data.model.User;
import org.op.data.repositories.AdminRepository;
import org.op.util.AESEncryptor;
import org.op.util.FMessage;
import org.op.util.MailFactory;

@ManagedBean
@SessionScoped
public class AdminController implements Serializable
{

    private HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
            .getExternalContext()
            .getSession(false);

    private AdminRepository adminRepository;
    private MailFactory mail;

    private final FMessage msg;

    private boolean currentUserIsAdmin;

    private List<Contact> contacts;

    private List<User> allUsers;

    private User currentUser;

    private Contact selectedContact,
            newContact;

    private AESEncryptor aESEncryptor;

    private int activeAdminTab;



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
        currentUser = new User();

        msg = new FMessage();

        try
        {
            aESEncryptor = new AESEncryptor();
        } catch (Exception e)
        {
            e.printStackTrace(System.out);
        }
    }



    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public List<User> getAllUsers()
    {
        return allUsers;
    }



    public void setAllUsers(List<User> allUsers)
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



    public User getCurrentUser()
    {
        return currentUser;
    }



    public void setCurrentUser(User currentUser)
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
        try
        {
            adminRepository = new AdminRepository("OpenProvaPU");

            User u = adminRepository.getUser(currentUser.getUsername().toLowerCase(),
                    aESEncryptor.encrypt(currentUser.getPassword().toLowerCase()),
                    "admin");

            if (u != null)
            {
                currentUser = u;
                currentUserIsAdmin = currentUser.getRole().equalsIgnoreCase("admin");
                session.setAttribute("currentUser", currentUser);
                List<User> au = adminRepository.getResultList(User.class);
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
        } catch (Exception e)
        {
            e.printStackTrace(System.out);
            msg.error("Login Error: " + e.getMessage());
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
        currentUserIsAdmin = false;
        currentUser = new User();
        session.invalidate();
    }



    public void saveNewContact()
    {
        if (!newContact.getFirstName().isEmpty()
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



    public void saveContact()
    {
        mail = new MailFactory();

        if (!selectedContact.getFirstName().isEmpty()
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
        if (adminRepository.deleted(selectedContact))
        {
            msg.info("Contact '"
                    + selectedContact.getFirstName()
                    + " "
                    + selectedContact.getLastName()
                    + "' deleted");
        } else
        {
            msg.error("existing contact not deleted");
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
                                + ", " + activity.getDate()
                                + " removed");
                    } else
                    {
                        msg.error("Subscription id: "
                                + activity.getId()
                                + ", "
                                + activity.getDescription()
                                + ", " + activity.getDate()
                                + " could not be removed");
                    }
        });
        loadContacts();
    }

}
