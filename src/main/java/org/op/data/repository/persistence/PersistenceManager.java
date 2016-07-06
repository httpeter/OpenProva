package org.op.data.repository.persistence;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class PersistenceManager
{

    public static final boolean DEBUG = true;

    private static final PersistenceManager singleton = new PersistenceManager();

    protected EntityManagerFactory emf;



    public static PersistenceManager getInstance()
    {
        return singleton;
    }



    public EntityManagerFactory getEntityManagerFactory(String puName)
    {
        if (emf == null)
        {
            createEntityManagerFactory(puName);
        }
        return emf;
    }



    public void closeEntityManagerFactory()
    {

        if (emf != null)
        {
            emf.close();
            emf = null;
            if (DEBUG)
            {
                System.out.println("\n*** Persistence finished at "
                        + new java.util.Date()
                        + " ***\n");
            }
        }
    }



    protected void createEntityManagerFactory(String puName)
    {

        this.emf = Persistence.createEntityManagerFactory(puName);
        if (DEBUG)
        {
            System.out.println("\n*** Persistence started at: "
                    + new java.util.Date()
                    + " ***\n");
        }
    }
}
