// This code covered by the Apache2 License: http://www.apache.org/licenses/LICENSE-2.0
// You are free to use it for your own good as long as it doesn't hurt anybody.
// For questions or suggestions please contact me at httpeter@gmail.com
package org.op.data.repository;

import java.io.Serializable;
import java.util.List;
import org.httpeter.repository.DefaultRepository;
import org.op.data.model.Activity;
import org.op.data.model.Contact;
import org.op.data.model.Project;

public class SubscribersRepository extends DefaultRepository implements Serializable
{

    private static final long serialVersionUID = 704529302348156052L;



    public boolean isExistingContact(Contact c)
    {
        Contact con = (Contact) this.getEm()
                .createQuery("select c from Contact c "
                        + "where c.email = :cEmail")
                .setParameter("cEmail", c.getEmail())
                .getSingleResult();
        return con != null;
    }



    /**
     *
     * @param project
     * @return
     */
    public List<Activity> getActivities(Project project)
    {
        return this.getEm()
                .createQuery("select a from Activity a "
                        + "where a.project = :project")
                .setParameter("project", project)
                .getResultList();
    }

}
