package org.op.repositories;

import java.io.Serializable;
import org.op.model.Labels;

public class LabelRepository extends DefaultRepository implements Serializable
{

    public LabelRepository(String persistenceUnitName)
    {
        super(persistenceUnitName);
    }

    public Labels getLabels()
    {
        try
        {
            if (this.getEmf().isOpen() && this.getEm().isOpen())
            {
                Labels lab = (Labels) this.getEm()
                        .createQuery("select l from Labels l")
                        .getSingleResult();

                if (lab != null)
                {
                    return lab;
                }
            }

        } catch (Exception e)
        {
            System.out.println("Error: Could not load labels...");
            throw new RuntimeException(e);
        }
        return null;
    }

}
