package org.op.data.repositories;

import java.io.Serializable;
import java.util.List;
import org.op.data.model.Activity;
import org.op.data.model.Contact;
import org.op.data.model.User;

public class AdminRepository extends DefaultRepository implements Serializable
{

    public AdminRepository(String persistenceUnitName)
    {
        super(persistenceUnitName);
    }



    public User getUser(String username, String password, String role) throws Exception
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
    }



    public List<Activity> getProjectActivities(long projectId, boolean isMasterActivity) throws Exception
    {
        return this.getEm()
                .createQuery("select a from Activity a "
                        + "where a.projectId = :projectId "
                        + "and a.isMasterActivity = :isMasterActivity")
                .setParameter("projectId", projectId)
                .setParameter("isMasterActivity", isMasterActivity).getResultList();
    }



    public List<Activity> getContactActivities(Contact c, boolean isMasterActivity) throws Exception
    {
        return this.getEm()
                .createQuery("select a from Activity a "
                        + "where a.contactId = :contactId "
                        + "and a.isMasterActivity = :isMasterActivity")
                .setParameter("contactId", c.getId())
                .setParameter("isMasterActivity", isMasterActivity).getResultList();
    }



    public boolean subscriptionRemoved(Activity a) throws Exception
    {
        if (this.getEmf().isOpen() && this.getEm().isOpen())
        {
            this.getEm().getTransaction().begin();
            a = this.getEm().merge(a);
            this.getEm().remove(a);
            this.getEm().getTransaction().commit();
            this.getEm().clear();
            return true;
        } else
        {
            System.out.println("EntityManagerFactor or EntityManager are closed");
            this.getEm().getTransaction().rollback();
            return false;
        }
    }

}
