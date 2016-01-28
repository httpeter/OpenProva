// This code covered by the Apache2 License: http://www.apache.org/licenses/LICENSE-2.0
// You are free to use it for your own good as long as it doesn't hurt anybody.
// For questions or suggestions please contact me at httpeter@gmail.com
package org.op.data.repository;

import java.io.Serializable;
import java.util.List;
import org.op.data.model.Activity;
import org.op.data.model.Contact;
import org.op.data.model.SystemUser;

public class AdminRepository extends DefaultRepository implements Serializable
{

    public AdminRepository(String persistenceUnitName)
    {
        super(persistenceUnitName);
    }



    public SystemUser getSystemUser(String username, String password, String uRole)
    {
        if (emIsOpen())
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
                return null;
            }

        }
        return null;
    }


//    Methods below should be changed so they fit the new datastructure

    public List<Activity> getProjectActivities(long projectId, boolean isMasterActivity)
    {
        if (emIsOpen())
        {
            return this.getEm()
                    .createQuery("select a from Activity a "
                            + "where a.project.id = :projectId")
                    .setParameter("projectId", projectId)
                    .setParameter("isMasterActivity", isMasterActivity)
                    .getResultList();
        }
        return null;
    }



    public List<Activity> getContactActivities(Contact c, boolean isMasterActivity)
    {
        if (emIsOpen())
        {
            return this.getEm()
                    .createQuery("select a from Activity a "
                            + "where a.contactId = :contactId "
                            + "and a.isMasterActivity = :isMasterActivity")
                    .setParameter("contactId", c.getId())
                    .setParameter("isMasterActivity", isMasterActivity)
                    .getResultList();
        }
        return null;
    }



    public boolean subscriptionRemoved(Activity a)
    {
        if (emIsOpen())
        {
            this.getEm().getTransaction().begin();
            a = this.getEm().merge(a);
            this.getEm().remove(a);
            this.getEm().getTransaction().commit();
            this.getEm().clear();
            return true;
        } else
        {
            this.getEm().getTransaction().rollback();
            return false;
        }
    }

}
