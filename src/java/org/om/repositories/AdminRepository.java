package org.om.repositories;

import java.io.Serializable;
import org.om.model.User;

public class AdminRepository extends DefaultRepository implements Serializable
{

    public AdminRepository(String persistenceUnitName)
    {
        super(persistenceUnitName);
    }

    public User getUser(String username, String password, String role)
    {
        try
        {
            return (User) this.getEm()
                    .createQuery("select u from User u "
                            + "where u.username = :uName "
                            + "and :pWord = u.password "
                            + "and u.role = :uRole")
                    .setParameter("uName", username)
                    .setParameter("pWord", password)
                    .setParameter("uRole", role)
                    .getSingleResult();
        } catch (Exception e)
        {
            System.out.println("Error: EMF or EM closed or username, password or role empty...");
        }
        return null;
    }
}
