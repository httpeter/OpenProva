package org.om.util;

import java.util.List;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.om.model.Activity;
import org.om.model.Contact;
import org.om.model.Labels;
import org.om.model.Project;

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
        AESEncryptor aESEncryptor = new AESEncryptor(labels.getSixteenCharsEncryptionPassword(),
                labels.getSixteenCharsEncryptionSalt());

        String a = aESEncryptor.encrypt("Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit.");
        System.out.println("Testmsg!: \n\nEncrypted: "
                + a
                + "\nDecrypted:" + aESEncryptor.decrypt(a));

//  #!#contactFirstName#!#
//  #!#selectedProjectName#!#
//  #!#selectedProjectRepertoire#!#
//  #!#selectedProjectStartDate#!#
//  #!#selectedProjectEndDate#!#
//  #!#contactInstrument#!#
// #!#organizationName#!#
// #!#instrument#!#     
        //was hiervoor contextURL werk aan het vervangen van de spaties en moeilijke karakters
// #!#personalURL#!# (doe iets met een contactID ipv een naam!)
// #!#membersUsername#!#
// #!#membersPassword#!#
//            inputNewRendered = false;
//            new Thread(() ->
//            {
//                while (true)
//                {
//                    try
//                    {
//
//                        senderCheck(contactInstrument);
//                        mail = new SendGMailSSL(mailSender, mailSenderPwd);
//                        mail.Send(contactEmail, mailSender, "Inschrijving VUKO "
//                                + selectedProjectName, msg.toString());
//
//                        senderCheck(contactInstrument);
//                        mail = new SendGMailSSL(mailSender, mailSenderPwd);
//                        mail.Send(mailSender, mailSender, "Inschrijving nieuwe "
//                                + contactInstrument, "Ingeschreven: "
//                                + contactFirstName
//                                + " "
//                                + contactLastName
//                                + "\nSpeelt: "
//                                + contactInstrument
//                                + "\nEmail: "
//                                + contactEmail
//                                + "\nTelefoon: "
//                                + contactPhone
//                                + "\nBeschikbaarheid: "
//                                + contextURL.toString()
//                                + "/index.xhtml?u=vuko&p=keuris&name="
//                                + contactFirstName.replace(" ", "_")
//                                + "%20"
//                                + contactLastName.replace(" ", "_")
//                                + "\n\n Controleer auditiebeleid!\n(dit bericht werd automatisch gegenereerd)");
//                    } catch (Exception e)
//                    {
//                        log.log(Level.WARNING, e.getMessage());
//                        throw new RuntimeException(e);
//                    }
//                    break;
//                }
//            }).start();
//        } 
    }

}
