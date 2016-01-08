// This code covered by the Apache2 License: http://www.apache.org/licenses/LICENSE-2.0
// You are free to use it for your own good as long as it doesn't hurt anybody.
// For questions or suggestions please contact me at httpeter@gmail.com
package org.op.data.repository;

import java.io.Serializable;
import java.util.List;
import org.op.data.model.Activity;
import org.op.data.model.Contact;
import org.op.data.model.Project;

public class SubscribersRepository extends DefaultRepository implements Serializable
{

    public SubscribersRepository(String persistenceUnitName)
    {
        super(persistenceUnitName);
    }



    public boolean isExistingContact(Contact c)
    {
        if (emIsOpen())
        {
            Contact con = (Contact) this.getEm()
                    .createQuery("select c from Contact c "
                            + "where c.email = :cEmail")
                    .setParameter("cEmail", c.getEmail())
                    .getSingleResult();
            return con != null;
        }
        return false;
    }



    public List<Activity> getProjectActivities(long projectId, boolean isMasterActivity)
    {
        if (emIsOpen())
        {
            return this.getEm()
                    .createQuery("select a from Activity a "
                            + "where a.projectId = :projectId "
                            + "and a.isMasterActivity = :isMasterActivity")
                    .setParameter("projectId", projectId)
                    .setParameter("isMasterActivity", isMasterActivity).getResultList();
        }
        return null;
    }



    public List<Project> getActiveProjects(boolean active)
    {
        if (emIsOpen())
        {
            return this.getEm()
                    .createQuery("select p from Project p "
                            + "where p.active = :active")
                    .setParameter("active", active).getResultList();
        }
        return null;
    }

}