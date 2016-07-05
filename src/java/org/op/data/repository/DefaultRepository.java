// This code covered by the Apache2 License: http://www.apache.org/licenses/LICENSE-2.0
// You are free to use it for your own good as long as it doesn't hurt anybody.
// For questions or suggestions please contact me at httpeter@gmail.com
package org.op.data.repository;

import java.io.Serializable;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import org.op.data.repository.persistence.PersistenceManager;

/**
 * Simple extendable repository for use with JPA2 offering basic list retrieval
 * en persistence functionalities. Requires the name of the used Persistence
 * Unit.
 *
 */
public class DefaultRepository implements Serializable
{

    private static final long serialVersionUID = 7086626098229281352L;

    private EntityManagerFactory emf;

    private EntityManager em;



    /**
     * Exposing the EntityManager for use in class extension
     *
     * @return
     */
    public EntityManager getEm()
    {
        return em;
    }



    public DefaultRepository()
    {
        String puName = FacesContext.getCurrentInstance()
                .getExternalContext()
                .getInitParameter("puName");

        emf = PersistenceManager.getInstance()
                .getEntityManagerFactory(puName);

        em = emf.createEntityManager();
    }



    /**
     * Saving an object to the database. If EntityManager or
     * EntityManagerFactory are closed an error is thrown. The latter can be
     * checked with 'emIsOpen'.
     *
     * @param object
     * @return
     */
    public boolean persisted(Object object)
    {
        try
        {
            em.getTransaction().begin();
            em.persist(object);
            em.getTransaction().commit();
            em.clear();
            return true;
        } catch (Exception e)
        {
            e.printStackTrace();
            try
            {
                em.getTransaction().rollback();
            } catch (Exception e1)
            {
                e1.printStackTrace(System.out);
            }
            return false;
        }
    }



    /**
     * Retrieve a list of objects according to their type from the DB.
     *
     * @param c
     * @return
     */
    public List getResultList(Class c)
    {
        TypedQuery q = em.createQuery("select o from "
                + c.getSimpleName()
                + " o", c);
        return q.getResultList();
    }



    /**
     * Delete a specific object from the DB. Does not work with objects
     * extending Collection! If the EntityManager and the EntityManagerFactory
     * are closed, an error is thrown
     *
     * @param object
     * @return
     */
    public boolean deleted(Object object)
    {
        try
        {
            em.getTransaction().begin();
            em.remove(object);
            em.getTransaction()
                    .commit();
            em.clear();
            return true;
        } catch (Exception e)
        {
            e.printStackTrace(System.out);
            return false;
        }
    }

}
