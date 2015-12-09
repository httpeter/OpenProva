package org.op.data.repositories;

import java.io.Serializable;
import java.util.List;
import org.op.data.model.Activity;
import org.op.data.model.Contact;

public class SubscribersRepository extends DefaultRepository implements Serializable
{

    public SubscribersRepository(String persistenceUnitName)
    {
        super(persistenceUnitName);
    }



    public boolean isExistingContact(Contact c) throws Exception
    {
        Contact con = (Contact) this.getEm()
                .createQuery("select c from Contact c "
                        + "where c.email = :cEmail")
                .setParameter("cEmail", c.getEmail())
                .getSingleResult();

        return con != null;
    }



    public List<Activity> getProjectActivities(long projectId, boolean isMasterActivity) throws Exception
    {
        return this.getEm().createQuery("select a from Activity a "
                + "where a.projectId = :projectId "
                + "and a.isMasterActivity = :isMasterActivity")
                .setParameter("projectId", projectId)
                .setParameter("isMasterActivity", isMasterActivity).getResultList();
    }



    public List getActiveProjects(boolean active) throws Exception
    {
        return this.getEm().createQuery("select p from Project p "
                + "where p.active = :active")
                .setParameter("active", active).getResultList();
    }

}
