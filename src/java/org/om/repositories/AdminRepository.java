package org.om.repositories;

import java.io.Serializable;
import java.util.List;
import org.om.model.Activity;
import org.om.model.Contact;
import org.om.model.User;

public class AdminRepository extends DefaultRepository implements Serializable
{

    public AdminRepository(String persistenceUnitName)
    {
        super(persistenceUnitName);
    }

    public User getUser(String username, String password, String role)
    {
        try
        {
            return (User) this.getEm()
                    .createQuery("select u from User u "
                            + "where u.username = :uName "
                            + "and :pWord = u.password "
                            + "and u.role = :uRole")
                    .setParameter("uName", username)
                    .setParameter("pWord", password)
                    .setParameter("uRole", role)
                    .getSingleResult();
        } catch (Exception e)
        {
            System.out.println("Error: EMF or EM closed or username, password or role empty...");
        }
        return null;
    }

    public List<Activity> getProjectActivities(long projectId, boolean isMasterActivity)
    {
        if (this.getEmf().isOpen() && this.getEm().isOpen())
        {
            return this.getEm().createQuery("select a from Activity a where a.projectId = :projectId and a.isMasterActivity = :isMasterActivity")
                    .setParameter("projectId", projectId)
                    .setParameter("isMasterActivity", isMasterActivity).getResultList();
        }
        return null;
    }

    public List<Activity> getContactActivities(Contact c, boolean isMasterActivity)
    {
        if (this.getEmf().isOpen() && this.getEm().isOpen())
        {
            return this.getEm().createQuery("select a from Activity a where a.contactId = :contactId and a.isMasterActivity = :isMasterActivity")
                    .setParameter("contactId", c.getId())
                    .setParameter("isMasterActivity", isMasterActivity).getResultList();
        }
        return null;
    }

    public boolean subscriptionRemoved(Activity a)
    {
        if (this.getEmf().isOpen() && this.getEm().isOpen())
        {
            this.getEm().getTransaction().begin();
            a = this.getEm().merge(a);
            this.getEm().remove(a);
            this.getEm().getTransaction().commit();
            this.getEm().clear();
            return true;
        }
        return false;
    }

}
