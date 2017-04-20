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
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.inject.Inject;
import org.op.data.model.Contact;
import org.op.data.model.Subscription;
import org.op.data.model.SystemUser;
import org.op.data.repository.AdminRepository;
import org.op.util.AESEncryptor;
import org.op.util.FMessage;
import org.op.util.MailFactory;

@ManagedBean
@SessionScoped
public class AdminController implements Serializable
{

    @Inject
    private ExternalContext context;

    private static final long serialVersionUID = 932378170497270415L;

    private AdminRepository adminRepository;

    private final MailFactory mail;

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
        mail = new MailFactory();
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
        adminRepository = new AdminRepository();
        String userPwdEncrypted = null;
        try
        {
            userPwdEncrypted = new AESEncryptor().encrypt(currentUser
                    .getPassword()
                    .toLowerCase());

        } catch (Exception e)
        {
            msg.fatal("Fatal Error\n\n" + e.getMessage());
        }
        SystemUser u = adminRepository.getSystemUser(currentUser
                .getUsername().toLowerCase(),
                userPwdEncrypted,
                "admin");

        if (u != null)
        {
            currentUser = u;
            currentUserIsAdmin = currentUser.getUserRole()
                    .equalsIgnoreCase("admin");
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
            this.loadContacts();
        }
    }


    public void logout()
    {
        currentUserIsAdmin = false;
        currentUser = new SystemUser();
        selectedContact = new Contact();
        newContact = new Contact();
        context.invalidateSession();
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
        this.loadContacts();
    }


    public void saveExistingContact()
    {
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
        this.loadContacts();
    }


    public void deleteContactAndSubscriptions()
    {
        if (selectedContact != null
                && selectedContact.getSystemUser()
                        .getUsername()
                        .equals(currentUser.getUsername())
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
                    + "' can only be deleted by it's own admin user.");
        }

        List<Subscription> selectedContactSubscriptions = adminRepository
                .getContactSubscriptions(selectedContact);

        selectedContactSubscriptions.forEach((subscription)
                ->
        {
            if (!adminRepository.subscriptionRemoved(subscription))
            {
                msg.error("Subscription id: "
                        + subscription.getId()
                        + ", "
                        + subscription.getDescription()
                        + ", " + subscription.getActivityDate()
                        + " could not be removed");
            }
        });

        this.loadContacts();
    }

}
