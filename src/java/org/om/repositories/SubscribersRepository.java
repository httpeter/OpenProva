package org.om.repositories;

import java.io.Serializable;
import java.util.List;
import org.om.model.Activity;
import org.om.model.Contact;

public class SubscribersRepository extends DefaultRepository implements Serializable
{

    public SubscribersRepository(String persistenceUnitName)
    {
        super(persistenceUnitName);
    }

    public boolean isExistingContact(Contact c)
    {
        try
        {
            if (this.getEmf().isOpen() && this.getEm().isOpen())
            {
                Contact con = (Contact) this.getEm()
                        .createQuery("select c from Contact c where c.email = :cEmail")
                        .setParameter("cEmail", c.getEmail())
                        .getSingleResult();

                if (con != null)
                {
                    return true;
                }
            }

        } catch (Exception e)
        {
            System.out.println("No entries found yet for Contact email address: "
                    .concat(c.getEmail()));
        }
        return false;
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

}
