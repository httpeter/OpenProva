// This code covered by the Apache2 License: http://www.apache.org/licenses/LICENSE-2.0
// You are free to use it for your own good as long as it doesn't hurt anybody.
// For questions or suggestions please contact me at httpeter@gmail.com
package org.op.data.repository;

import java.io.Serializable;
import java.util.List;
import org.op.data.model.Activity;
import org.op.data.model.Contact;
import org.op.data.model.Subscription;
import org.op.data.model.SystemUser;

public class AdminRepository extends DefaultRepository implements Serializable
{

    private static final long serialVersionUID = -7610678289904424970L;



    public SystemUser getSystemUser(String username, String password, String uRole)
    {

        try
        {
            return (SystemUser) this.getEm().createQuery("select u from SystemUser u "
                    + "where u.username = :uName "
                    + "and :pWord = u.password "
                    + "and u.userRole = :uRole")
                    .setParameter("uName", username)
                    .setParameter("pWord", password)
                    .setParameter("uRole", uRole)
                    .getSingleResult();
        } catch (Exception e)
        {
            e.printStackTrace(System.out);
            return null;
        }
    }



    // Methods below should be changed so they fit the new datastructure
    public List<Activity> getProjectActivities(long projectId, boolean isMasterActivity)
    {
        return this.getEm()
                .createQuery("select a from Activity a "
                        + "where a.project.id = :projectId")
                .setParameter("projectId", projectId)
                .setParameter("isMasterActivity", isMasterActivity)
                .getResultList();
    }



    public List<Subscription> getContactSubscriptions(Contact c)
    {

        return this.getEm()
                .createQuery("select s from Subscription s "
                        + "where s.contact.id = :contactId")
                .setParameter("contactId", c.getId())
                .getResultList();

    }



    public boolean subscriptionRemoved(Subscription s)
    {
        try
        {
            this.getEm().getTransaction().begin();
            s = this.getEm().merge(s);
            this.getEm().remove(s);
            this.getEm().getTransaction().commit();
            this.getEm().clear();
            return true;
        } catch (Exception e)
        {
            e.printStackTrace(System.out);
            this.getEm().getTransaction().rollback();
            return false;
        }
    }

}
