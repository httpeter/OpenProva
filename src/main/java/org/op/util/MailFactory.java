// This code covered by the Apache2 License: http://www.apache.org/licenses/LICENSE-2.0
// You are free to use it for your own good as long as it doesn't hurt anybody.
// For questions or suggestions please contact me at httpeter@gmail.com
package org.op.util;

import java.io.Serializable;
import java.util.List;
import java.util.Properties;
import org.op.data.model.Contact;
import org.op.data.model.Project;
import org.op.data.model.Subscription;
import org.op.util.gmail.GMailSSLSender;

/**
 *
 * @author PeterH
 */
public class MailFactory implements Serializable
{

    private final FMessage msg = new FMessage();

    private GMailSSLSender gMailClient;



    public final boolean addressValid(String emailAddress)
    {
        return !emailAddress.isEmpty()
                && !emailAddress.contains(" ")
                && emailAddress.contains("@")
                && emailAddress.contains(".");
    }



    /*
    * Dit moet worden omgebouwd naar bestaande inschrijvingen.
    *
    */
    public void sendNewMemberSubscriptionMail(Contact contact,
            Project project,
            List<Subscription> subscriptions,
            String additionalMessage) throws Exception
    {

        Properties labels = new Properties();

        labels.load(this.getClass()
                .getClassLoader()
                .getResourceAsStream("org.op.util.properties.labelsNL.properties"));

//        For new member subscriptions, this is BS
        new Thread(()
                -> 
                {
                    while (true)
                    {
                        try
                        {
                            TagReplacer tr = new TagReplacer();

                            gMailClient = new GMailSSLSender(contact.getSystemUser().getEmail(),
                                    contact.getSystemUser().getEmailPassword());

                            // Send msg to new member using prefixt tags
                            gMailClient.send(contact.getEmail(),
                                    tr.getReplacedString(labels.getProperty("mailMSGNewMemberSubscriptionSubject"),
                                            project, contact, labels.getProperty("organizationName")),
                                    tr.getReplacedString(labels.getProperty("mailMSGNewMemberSubscriptionBody"),
                                            project, contact, labels.getProperty("organizationName")));

                            // Send msg to admin about new subscription
                            gMailClient.send(labels.getProperty("defaultEmailStringsCoordinator"),
                                    tr.getReplacedString(labels.getProperty("mailMSGToAdminNewMemberSubscriptionSubject"),
                                            project, contact, labels.getProperty("organizationName")),
                                    tr.getReplacedString(labels.getProperty("mailMSGToAdminNewMemberSubscriptionBody"),
                                            project, contact, labels.getProperty("organizationName"))
                                    + "\n\n___________________________________________\n\n"
                                    + additionalMessage);

                        } catch (Exception e)
                        {
                            e.printStackTrace(System.out);
                            msg.error("Email problem:\n\n"
                                    + e.getMessage());
                        }
                        break;
                    }
        }).start();
    }
}
