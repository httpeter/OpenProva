package org.om.util;

import static java.lang.Math.log;
import java.util.List;
import java.util.logging.Level;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.om.model.Activity;
import org.om.model.Contact;
import org.om.model.Labels;
import org.om.model.Project;
import org.om.model.User;
import org.om.util.gmail.GMailSSLSender;

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

    private GMailSSLSender mail;

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
        User currentUser = (User) session.getAttribute("currentUser");

        AESEncryptor aESEncryptor = new AESEncryptor(
                labels.getSixteenCharsEncryptionPassword(),
                labels.getSixteenCharsEncryptionSalt());

        new Thread(() ->
        {
            while (true)
            {
                try
                {

                    mail = new GMailSSLSender(currentUser.getEmail(),
                            currentUser.getEmailPassword());

                    mail.send(contact.getEmail(),
                            labels.getMailMSGNewMemberSubscriptionSubject(),
                            labels.getMailMSGNewMemberSubscriptionBody()//replace tags....(aparte class maken?!)
                    );

                    senderCheck(contactInstrument);
                    mail = new SendGMailSSL(mailSender, mailSenderPwd);
                    mail.Send(mailSender, mailSender, "Inschrijving nieuwe "
                            + contactInstrument, "Ingeschreven: "
                            + contactFirstName
                            + " "
                            + contactLastName
                            + "\nSpeelt: "
                            + contactInstrument
                            + "\nEmail: "
                            + contactEmail
                            + "\nTelefoon: "
                            + contactPhone
                            + "\nBeschikbaarheid: "
                            + contextURL.toString()
                            + "/index.xhtml?u=vuko&p=keuris&name="
                            + contactFirstName.replace(" ", "_")
                            + "%20"
                            + contactLastName.replace(" ", "_")
                            + "\n\n Controleer auditiebeleid!\n(dit bericht werd automatisch gegenereerd)");
                } catch (Exception e)
                {
                    msg.error("Error sending mail: \n" + e.getMessage());
                    throw new RuntimeException(e);
                }
                break;
            }
        }).start();
    }
}

}
