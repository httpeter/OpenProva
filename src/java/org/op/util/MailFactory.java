package org.op.util;

import java.util.List;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.op.model.Activity;
import org.op.model.Contact;
import org.op.model.Labels;
import org.op.model.Project;
import org.op.model.User;
import org.op.util.gmail.GMailSSLSender;

/**
 *
 * @author PeterH
 */
public class MailFactory
{

    //getSession(false) attaches to existing session, getSession(true) creates a new one
    private final HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
            .getExternalContext()
            .getSession(false);

    private final FMessage msg = new FMessage();

    private GMailSSLSender gMailClient;

    public final boolean addressValid(String emailAddress)
    {
        return !emailAddress.isEmpty()
                && !emailAddress.contains(" ")
                && emailAddress.contains("@")
                && emailAddress.contains(".");
    }

    public void sendNewMemberSubscriptionMail(Contact contact,
            Project project,
            List<Activity> activities,
            String additionalMessage) throws Exception
    {

        Labels labels = (Labels) session.getAttribute("labels");

        AESEncryptor aESEncryptor = new AESEncryptor(
                labels.getSixteenCharsEncryptionPassword(),
                labels.getSixteenCharsEncryptionSalt());

        new Thread(() ->
        {
            while (true)
            {
                try
                {
                    TagReplacer tr = new TagReplacer();

                    gMailClient = new GMailSSLSender(labels.getDefaultEmailStringsCoordinator(),
                            labels.getDefaultEmailStringsCoordinatorPWD());

                    // Send msg to new member
                    gMailClient.send(contact.getEmail(),
                            tr.getReplacedString(labels.getMailMSGNewMemberSubscriptionSubject(),
                                    project, contact, labels),
                            tr.getReplacedString(labels.getMailMSGNewMemberSubscriptionBody(),
                                    project, contact, labels));

                    // Send msg to admin about new subscription
                    gMailClient.send(labels.getDefaultEmailStringsCoordinator(),
                            tr.getReplacedString(labels.getMailMSGToAdminNewMemberSubscriptionSubject(),
                                    project, contact, labels),
                            tr.getReplacedString(labels.getMailMSGToAdminNewMemberSubscriptionBody(),
                                    project, contact, labels)
                            + "\n\n___________________________________________\n\n"
                            + additionalMessage);

                } catch (Exception e)
                {
                    e.printStackTrace();
                    msg.error("Email problem:\n\n"
                            + e.getMessage());
                }
                break;
            }
        }).start();
    }
}
