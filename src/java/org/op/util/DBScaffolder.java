/**
 *Adapt the strings in the 'restore' method below
 *to change the values of the labels that your see in the system
 *
 */
package org.op.util;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import org.op.data.model.Contact;
import org.op.data.model.Activity;
import org.op.data.model.Project;
import org.op.data.model.SystemUser;
import org.op.data.repository.DefaultRepository;

public class DBScaffolder implements Serializable
{

    private final FMessage msg;



    public DBScaffolder()
    {
        msg = new FMessage();
        msg.warn("WARNING:\n\nRunning in 'Development' mode, DBScaffolding is active.\n\n"
                + "Set to 'PRODUCTION' for use in...production.");
    }

    private final DefaultRepository defaultRepository = new DefaultRepository("OpenProvaPU");



    public void restore()
    {
        try
        {

            AESEncryptor aESEncryptor = new AESEncryptor();

            if (defaultRepository.getResultList(SystemUser.class).isEmpty())
            {

                //Users
                SystemUser u1 = new SystemUser();

                u1.setUsername("test");
                u1.setPassword(aESEncryptor.encrypt("user"));

//            For the strings
                u1.setFirstName("Peter");
                u1.setLastName("Hendriks");
                u1.setUserRole("admin");
                u1.setEmail("vuko.strijkerschef@gmail.com");
                u1.setEmailPassword("strijkerschef!");
                if (defaultRepository.persisted(u1))
                {
                    msg.info("Standard strings admin user restored");
                } else
                {
                    msg.warn("Error while trying to restore standard users...");
                }

                SystemUser u2 = new SystemUser();

                u2.setUsername("test2");
                u2.setPassword(aESEncryptor.encrypt("user2"));

//            For the winds
                u2.setFirstName("Dirk");
                u2.setLastName("Banaan");
                u2.setUserRole("admin");
                u2.setEmail("vuko.blazerschef@gmail.com");
                u2.setEmailPassword("unknown");
                if (defaultRepository.persisted(u2))
                {
                    msg.info("Standard winds user restored");
                } else
                {
                    msg.warn("Error while trying to restore standard users...");
                }

                for (int i = 0; i < 100; i++)
                {
                    //Contacts
                    Contact c1 = new Contact();
                    c1.setSystemUser(u1);
                    c1.setFirstName("Pieter" + Math.round(Math.random() * 1000));
                    c1.setLastName("Paukeman" + Math.round(Math.random() * 1000));
                    c1.setInstrument("slagwerk");
                    c1.setNotes("Geweldige speler");
                    c1.setPhone("06123123123" + Math.round(Math.random() * 100));
                    c1.setEmail("p.paukeman@gmail.com" + Math.round(Math.random() * 100));

                    //Contact2
                    Contact c2 = new Contact();
                    c2.setSystemUser(u2);
                    c2.setFirstName("Daphne" + Math.round(Math.random() * 1000));
                    c2.setLastName("Dinges" + Math.round(Math.random() * 1000));
                    c2.setInstrument("viool");
                    c2.setNotes("via Ellis");
                    c2.setPhone("063423423" + Math.round(Math.random() * 10));
                    c2.setEmail("e.dinges@gmail.com" + Math.round(Math.random() * 100));

                    if (defaultRepository.persisted(c1) && defaultRepository.persisted(c2))
                    {
                        msg.info("Standard contacts restored");
                    } else
                    {
                        msg.warn("Error while trying to restore standard contacts...");
                    }
                }
            }

            //Projects
            Project p = new Project();
            if (defaultRepository.getResultList(Project.class).isEmpty())
            {
                p.setProjectName("Voorjaar 2015");
                p.setActive(true);
                p.setProjectStartDate(new Date(115, 6, 1));
                p.setProjectEndDate(new Date(115, 6, 28));
                p.setProjectRepertoire("Bach, Beethoven etc.");
                p.setRequiredPresence(99);
                if (defaultRepository.persisted(p))
                {
                    msg.info("Standard projects restored");
                } else
                {
                    msg.warn("Error while trying to restore standard projects...");
                }
            }

            if (defaultRepository.getResultList(Activity.class).isEmpty())
            {
                StringBuilder msgBuffer = new StringBuilder();
                //Activities
                for (int i = 10; i < 20; i++)
                {
                    Activity a = new Activity();
                    a.setIsMasterActivity(true);
                    a.setActivityDate(new java.sql.Date(115, 6, i));
                    a.setStartTime(new Time((int) Math.round(Math.random() * 10), 00, 0));
                    a.setEndTime(new Time((int) Math.round(Math.random() * 10), 00, 0));
                    a.setDescription("bla " + String.valueOf(Math.round(Math.random() * 100)));
                    a.setLocation("Griffioen, Amstelveen");
                    a.setProjectId(p.getId());
                    a.setContactId(0L);
                    if (defaultRepository.persisted(a))
                    {
                        msgBuffer.append(a.getActivityDate());
                        msgBuffer.append("\n");
                    }
                }
                msg.info("Standard master activities added: \n"
                        .concat(msgBuffer.toString()));

            }
        } catch (Exception e)
        {
            e.printStackTrace(System.out);
        }
    }

}
