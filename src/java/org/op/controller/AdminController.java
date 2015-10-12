package org.op.controller;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.List;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.op.model.Activity;
import org.op.model.Contact;
import org.op.model.Labels;
import org.op.model.User;
import org.op.repositories.AdminRepository;
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

    private final AdminRepository adminRepository = new AdminRepository("OpenProvaPU");

    private final MailFactory mail = new MailFactory();

    private final FMessage msg = new FMessage();

    private boolean currentUserIsAdmin;

    private List<Contact> contacts;

    private User currentUser = new User();

    private Contact selectedContact = new Contact();

    private Contact newContact = new Contact();

    private AESEncryptor aESEncryptor;

    private final Labels labels = (Labels) session.getAttribute("labels");

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

    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
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
            aESEncryptor = new AESEncryptor(labels.getSixteenCharsEncryptionPassword(),
                    labels.getSixteenCharsEncryptionSalt());

            User u = adminRepository.getUser(currentUser.getUsername().toLowerCase(),
                    aESEncryptor.encrypt(currentUser.getPassword().toLowerCase()),
                    "admin");

            if (u != null)
            {
                currentUser = u;
                currentUserIsAdmin = true;
                session.setAttribute("currentUser", currentUser);
            } else
            {
                msg.warn("wrong login...");
            }
        } catch (UnsupportedEncodingException | IllegalBlockSizeException | BadPaddingException e)
        {
            e.printStackTrace();
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

    public void deleteContact()
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

        selectedContactActivities.forEach((activity) ->
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
