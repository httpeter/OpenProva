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

    private static final long serialVersionUID = 704529302348156052L;

    private DefaultRepository repository;



    /**
     * Extending the DefaultRepository, this class is meant to deal with
     * requests from and to the DB that involve user subscriptions or
     * re-subscriptions.
     *
     * @param puName
     */
    public SubscribersRepository(String puName)
    {

        repository = DefaultRepository.getInstance(puName);
    }



    public boolean isExistingContact(Contact c)
    {
        if (repository.emIsOpen())
        {
            Contact con = (Contact) repository.getEm()
                    .createQuery("select c from Contact c "
                            + "where c.email = :cEmail")
                    .setParameter("cEmail", c.getEmail())
                    .getSingleResult();
            return con != null;
        }
        return false;
    }



    /**
     *
     * @param project
     * @return
     */
    public List<Activity> getActivities(Project project)
    {
        if (repository.emIsOpen())
        {
            return repository.getEm()
                    .createQuery("select a from Activity a "
                            + "where a.project = :project")
                    .setParameter("project", project)
                    .getResultList();
        }
        return null;
    }

}
