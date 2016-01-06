package org.op.data.repository;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class DefaultRepository implements Serializable
{

    private static final Logger logger = Logger.getLogger(DefaultRepository.class.getName());
    private EntityManagerFactory emf;
    private EntityManager em;



    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public EntityManagerFactory getEmf()
    {
        return emf;
    }



    public void setEmf(EntityManagerFactory emf)
    {
        this.emf = emf;
    }



    public EntityManager getEm()
    {
        return em;
    }



    public void setEm(EntityManager em)
    {
        this.em = em;
    }
//</editor-fold>



    public DefaultRepository(String persistenceUnitName)
    {
        emf = Persistence.createEntityManagerFactory(persistenceUnitName);
        em = emf.createEntityManager();
    }



    public boolean emIsOpen()
    {
        if (!emf.isOpen())
        {
            logger.warning("EMF is closed!");
        }
        if (!em.isOpen())
        {
            logger.warning("EM is closed!");
        }
        return emf.isOpen() && em.isOpen();
    }



    public boolean persisted(Object object)
    {
        if (emIsOpen())
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
                logger.log(Level.WARNING, e.getCause().getMessage());
                try
                {
                    em.getTransaction().rollback();
                } catch (Exception e1)
                {
                    logger.log(Level.WARNING, e1.getCause().getMessage());
                }
                return false;
            }
        } else
        {
            System.out.println("EntityManagerFactor or EntityManager are closed");
            return false;
        }
    }



    public List getResultList(Class c)
    {
        if (emIsOpen())
        {
            TypedQuery q = em.createQuery("select o from "
                    + c.getSimpleName()
                    + " o", c);
            return q.getResultList();
        }
        return null;
    }



    public boolean deleted(Object object)
    {
        if (emIsOpen())
        {
            em.getTransaction().begin();
            em.remove(object);
            em.getTransaction()
                    .commit();
            em.clear();
            return true;
        } else
        {
            System.out.println("EntityManagerFactor or EntityManager are closed");
            em.getTransaction().rollback();
            return false;
        }

    }



    public void close()
    {
        if (emIsOpen())
        {
            em.clear();
            em.close();
            emf.close();
        }
    }

}
