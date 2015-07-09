package org.om.controller;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.List;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.om.model.Activity;
import org.om.model.Contact;
import org.om.model.Labels;
import org.om.model.User;
import org.om.repositories.AdminRepository;
import org.om.util.AESEncryptor;
import org.om.util.FMessage;
import org.om.util.MailFactory;

@ManagedBean
@SessionScoped
public class AdminController implements Serializable
{

    HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
            .getExternalContext()
            .getSession(false);

    private final AdminRepository adminRepository = new AdminRepository("OrchestraManagerPU");

    private final MailFactory mail = new MailFactory();

    private final FMessage msg = new FMessage();

    private boolean currentUserIsAdmin;

    private List<Contact> contacts;

    private User currentUser = new User();

    private Contact selectedContact = new Contact();

    private Contact newContact = new Contact();

    private AESEncryptor aESEncryptor;

    private Labels labels = (Labels) session.getAttribute("labels");

    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
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
        return adminRepository.getResultList(Contact.class);

    }

    public void setContacts(List<Contact> contacts)
    {
        this.contacts = contacts;
        if (adminRepository.persisted(contacts))
        {
            msg.info("Contact saved");
        } else
        {
            msg.warn("Could not save");
        }
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
                //Optional for now. should be triggered in another way..
                loadContacts();
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

    public void logout()
    {
        currentUserIsAdmin = false;
        currentUser = new User();
        session.invalidate();
    }

    public void saveNewContact()
    {
        if (!newContact.getFirstName().isEmpty()
                && mail.addressValid(selectedContact.getEmail())
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
    }

}
