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

    private DefaultRepository repository;



    public AdminRepository(String persistenceUnitName)
    {
        repository = DefaultRepository.getInstance(persistenceUnitName);
    }



    public SystemUser getSystemUser(String username, String password, String uRole)
    {
        if (repository.emIsOpen())
        {
            try
            {
                return (SystemUser) repository.getEm()
                        .createQuery("select u from SystemUser u "
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
        if (repository.emIsOpen())
        {
            return repository.getEm()
                    .createQuery("select a from Activity a "
                            + "where a.project.id = :projectId")
                    .setParameter("projectId", projectId)
                    .setParameter("isMasterActivity", isMasterActivity)
                    .getResultList();
        }
        return null;
    }



    public List<Subscription> getContactSubscriptions(Contact c)
    {
        if (repository.emIsOpen())
        {
            return repository.getEm()
                    .createQuery("select s from Subscription s "
                            + "where s.contact.id = :contactId")
                    .setParameter("contactId", c.getId())
                    .getResultList();
        }
        return null;
    }



    public boolean subscriptionRemoved(Subscription s)
    {
        if (repository.emIsOpen())
        {
            repository.getEm().getTransaction().begin();
            s = repository.getEm().merge(s);
            repository.getEm().remove(s);
            repository.getEm().getTransaction().commit();
            repository.getEm().clear();
            return true;
        } else
        {
            repository.getEm().getTransaction().rollback();
            return false;
        }
    }

}
