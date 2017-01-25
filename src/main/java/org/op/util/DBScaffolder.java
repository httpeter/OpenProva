// This code covered by the Apache2 License: http://www.apache.org/licenses/LICENSE-2.0
// You are free to use it for your own good as long as it doesn't hurt anybody.
// For questions or suggestions please contact me at httpeter@gmail.com
package org.op.util;

import java.io.Serializable;
import java.sql.Time;
import org.httpeter.repository.DefaultRepository;
import org.op.data.model.Contact;
import org.op.data.model.Activity;
import org.op.data.model.Project;
import org.op.data.model.SystemUser;

public class DBScaffolder implements Serializable
{

    private final FMessage msg;



    public DBScaffolder()
    {
        msg = new FMessage();
        msg.warn("WARNING:\n\nRunning in 'Development' mode, DBScaffolding is active.\n\n"
                + "Set to 'PRODUCTION' for use in...production.");
    }



    public void restore()
    {
        try
        {
            DefaultRepository repository = new DefaultRepository();

            AESEncryptor aESEncryptor = new AESEncryptor();

            if (repository.getResultList(SystemUser.class) == null
                    || repository.getResultList(SystemUser.class).isEmpty())
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
                u1.setEmailPassword("****!");
                if (repository.persisted(u1))
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
                if (repository.persisted(u2))
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

                    if (repository.persisted(c1)
                            && repository.persisted(c2))
                    {
                        msg.info("Standard contacts restored");
                    } else
                    {
                        msg.warn("Error while trying to restore standard contacts...");
                    }
                }
            }

            //Projects
            Project p1 = new Project();
            Project p2 = new Project();
            if (repository.getResultList(Project.class) == null
                    || repository.getResultList(Project.class).isEmpty())
            {
                p1.setProjectName("Voorjaar 2015");
                p1.setActive(true);
                p1.setProjectStartDate(java.sql.Date.valueOf("2015-05-10"));
                p1.setProjectEndDate(java.sql.Date.valueOf("2015-06-12"));
                p1.setProjectRepertoire("Bach, Beethoven etc.");
                p1.setRequiredPresence(99);
                if (repository.persisted(p1))
                {
                    msg.info("Standard project p1 restored");
                } else
                {
                    msg.warn("Error while trying to restore standard projects...");
                }

                p2.setProjectName("Najaar 2015");
                p2.setActive(true);
                p2.setProjectStartDate(java.sql.Date.valueOf("2015-09-10"));
                p2.setProjectEndDate(java.sql.Date.valueOf("2015-09-14"));
                p2.setProjectRepertoire("Schubert, Mozart etc.");
                p2.setRequiredPresence(99);
                if (repository.persisted(p2))
                {
                    msg.info("Standard project p2 restored");
                } else
                {
                    msg.warn("Error while trying to restore standard projects...");
                }
            }

            if (repository.getResultList(Project.class) == null
                    || repository.getResultList(Activity.class).isEmpty())
            {
                StringBuilder msgBuffer = new StringBuilder();
                //Activities
                for (int i = 10; i < 20; i++)
                {
                    Activity a1 = new Activity();
                    a1.setActivityDate(java.sql.Date.valueOf("2015-05-" + i));
                    a1.setStartTime(new Time((int) Math.round(Math.random() * 10), 00, 0));
                    a1.setEndTime(new Time((int) Math.round(Math.random() * 10), 00, 0));
                    a1.setDescription("bla " + String.valueOf(Math.round(Math.random() * 100)));
                    a1.setLocation("Griffioen, Amstelveen");
                    a1.setProject(p1);
                    if (repository.persisted(a1))
                    {
                        msgBuffer.append(a1.getActivityDate());
                        msgBuffer.append("\n");
                    }
                }
                for (int i = 10; i < 15; i++)
                {
                    Activity a2 = new Activity();
                    a2.setActivityDate(java.sql.Date.valueOf("2015-09-" + i));
                    a2.setStartTime(new Time((int) Math.round(Math.random() * 10), 00, 0));
                    a2.setEndTime(new Time((int) Math.round(Math.random() * 10), 00, 0));
                    a2.setDescription("bla " + String.valueOf(Math.round(Math.random() * 100)));
                    a2.setLocation("Griffioen, Amstelveen");
                    a2.setProject(p2);
                    if (repository.persisted(a2))
                    {
                        msgBuffer.append(a2.getActivityDate());
                        msgBuffer.append("\n");
                    }
                }

                msg.info("Standard master activities added: \n"
                        .concat(msgBuffer.toString()));

            }
        } catch (Exception e)
        {
            msg.fatal(e.getMessage());
            e.printStackTrace(System.out);
        }
    }

}
