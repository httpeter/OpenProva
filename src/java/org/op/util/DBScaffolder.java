//Adapt the strings in the 'restore' method below 
//to change the values of the labels that your see in the system
package org.op.util;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import org.op.model.Contact;
import org.op.model.Labels;
import org.op.model.Activity;
import org.op.model.Project;
import org.op.model.User;
import org.op.repositories.DefaultRepository;

public class DBScaffolder implements Serializable
{

    private final DefaultRepository defaultRepository = new DefaultRepository("OpenProvaPU");

    public void restore()
    {
        System.out.println("Restoring standard DB items...");
        FMessage msg = new FMessage();
        Labels l = new Labels();

        if (defaultRepository.getResultList(Labels.class).isEmpty())
        {
            //locale
            l.setLocale("nl");

            //Dummy password and salt. Change for production or be vulnerable...
            l.setSixteenCharsEncryptionPassword("secret0000000000");
            l.setSixteenCharsEncryptionSalt("confusing0000000");

            l.setAppTitle("OpenProva");
            l.setOrganizationName("VU-Kamerorkest");
            l.setWelcomeMSG("welkom...");
            l.setMSGTimeoutHeader("Sessie Verlopen!");
            l.setMSGTimeoutBody("De sessie is verlopen.<br/>Klik hieronder om verder te gaan.");

            l.setMenuItemHome("Home");
            l.setMenuItemAdmin("Bestuur");
            l.setMenuItemProjects("Deelname");
            l.setMenuItemExistingProject("Heraanmelden");
            l.setMenuItemNewProject("Aanmelden");
            l.setMenuItemLogout("Uitloggen");

            //New panel
            l.setProjectSubscriptionNewMSG(
                    "<table>"
                    + "<tr>"
                    + "<td>"
                    + "<strong>Inschrijven nieuw lid " + l.getOrganizationName() + "</strong> \n"
                    + "<br/> \n"
                    + "<br/> Welkom! Via dit systeem kun je je inschrijven voor projecten van het VU-Kamerorkest. <div style=\"font-size: 10px;\"> \n"
                    + "<ul> \n"
                    + "<li> Ben je van plan voor het eerst mee te doen? Vul dan je gegevens in bij <i>Nieuwe leden</i>. <br/> (na je inschrijving ontvang je een e-mail van de strijkers,- of blazerschef) </li> \n"
                    + "<br/> \n"
                    + "<li> Heb je al eens meegespeeld en wil je weer meedoen of wil je je afmelden voor een repetitie? klik dan op <i>Bestaande leden</i> en gebruik de vooraf verstrekte gebruikersnaam/wachtwoord combinatie of de inschrijflink. <br/> (de blazers-, of strijkerschef krijgt een e-mail over de door jou ingevulde aanwezigheidsdata en zal dit per repetitie controleren) </li> \n"
                    + "</ul> \n"
                    + "</div> \n"
                    + "<b>\n"
                    + "<p style=\"font-size:9px\">Door je (her)in te schrijven voor een project verklaar je akkoord te gaan met het reglement van het VU-Kamerorkest zoals het op dat moment op de website te vinden is.</p>\n"
                    + "</b> \n"
                    + "</td> \n"
                    + "<td> <!--Hier kan evt informatie --></td>\n"
                    + "</tr>\n"
                    + "</table>");
            l.setProject("Project");
            l.setProjectSelect("selecteer...");
            l.setProjectFrom("Van");
            l.setProjectUntil("Tot");
            l.setProjectRepertoire("Repertoire");
            l.setProjectSubscriptionPanelHeader("Vul hieronder de benodigde gegevens en je beschikbaarheid in en klik op 'Opslaan'.");
            l.setActivityPresent("Aanwezig");
            l.setActivityDate("Datum");
            l.setActivityTime("Tijd");
            l.setActivityDescription("Beschrijving");
            l.setActivityLocation("Waar");
            l.setActivityCommentsByContact("Commentaar");
            l.setSave("Opslaan");
            l.setActivityAdditionalMessage("Optioneel: bericht aan de blazers- dan wel strijkerschef...");

            //Email addresses winds- and strings coordinators
            l.setDefaultEmailStringsCoordinator("vuko.strijkerschef@gmail.com");
            //           
//This has to be encrypted!
            //
            l.setDefaultEmailStringsCoordinatorPWD("strijkerschef!");
            //
            //
            l.setDefaultEmailWindsCoordinator("vuko.blazerschef@gmail.com");
            l.setDefaultEmailWindsCoordinatorPWD("onbekend");

            //Mail messages subscription page
            l.setMailMSGNewMemberSubscriptionSubject("Inschrijving VU-Kamerorkest");
            l.setMailMSGNewMemberSubscriptionBody("Beste #!#contactFirstName#!#,\n"
                    + "\n\n"
                    + "Goed dat je je hebt aangemeld voor het project #!#selectedProjectName#!# van het #!#organizationName#!#.\n\n"
                    + "We zijn blij dat je bij ons #!#contactInstrument#!# wil komen spelen. Het kan zijn dat we je vragen om auditie te doen. Dit zal informeel gebeuren hier zullen o.a. de dirigent en de concertmeester bij aanwezig zijn.\n\n"
                    + "Wees hier niet zenuwachtig voor, het is slechts een formaliteit en we hebben het allemaal gedaan.\n"
                    + "We nemen contact met je op over eventuele auditietijden, tot die tijd vind je meer informatie op www.vuko.nl .\n\n\n"
                    + "Wil je je in de toekomst afmelden voor repetities dan kan dat via de volgende link:\n\n\n"
                    + "#!#personalURL#!#\n"
                    + "\n\n"
                    + "Mocht de link niet werken dan kun je ook inloggen met je inlogcode:\n\n#!#membersLoginCode#!#"
                    + "\n\n"
                    + "Het wordt zeker een mooi project en we zijn blij dat jij er bij wil zijn!\n"
                    + "\n\n"
                    + "Tot snel,\n"
                    + "\n\n\n"
                    + "Bestuur VU-Kamerorkest\n\n"
                    + "(dit bericht werd automatisch gegenereerd)");

            //Administrator subscription confirmation mail
            l.setMailMSGToAdminNewMemberSubscriptionSubject("#!#contactFirstName#!# ingeschreven");
            l.setMailMSGToAdminNewMemberSubscriptionBody("hier moet nog wat...");

            //Admin panel
            l.setAdminUsername("Gebruikersnaam");
            l.setAdminPassword("Password");
            l.setAdminTabContacts("Contactpersonen");
            l.setAdminTabContactsAddContact("Contactpersoon Toevoegen");
            l.setAdminTabContactsTableFirstname("Voornaam");
            l.setAdminTabContactsTableLastname("Achternaam");
            l.setAdminTabContactsTableInstrument("Instrument");
            l.setAdminTabContactsTablePhone("Telefoon");
            l.setAdminTabContactsTableEmail("Email");
            l.setAdminTabContactsTableNotes("Notitie");

            if (defaultRepository.persisted(l))
            {
                msg.info("Standard labels restored");
            } else
            {
                msg.warn("Error while trying to restore standard labels...");
            }
        }
        if (defaultRepository.getResultList(User.class).isEmpty())
        {
            //Users
            AESEncryptor aESEncryptor = new AESEncryptor(
                    l.getSixteenCharsEncryptionPassword(),
                    l.getSixteenCharsEncryptionSalt());

            User u1 = new User();

            try
            {
                u1.setUsername("test");
                u1.setPassword(aESEncryptor.encrypt("user"));
            } catch (Exception e)
            {
                e.printStackTrace();
            }

//            For the strings
            u1.setFirstName("Peter");
            u1.setLastName("Hendriks");
            u1.setRole("admin");
            u1.setEmail("vuko.strijkerschef@gmail.com");
            u1.setEmailPassword("strijkerschef!");
            if (defaultRepository.persisted(u1))
            {
                msg.info("Standard strings admin user restored");
            } else
            {
                msg.warn("Error while trying to restore standard users...");
            }

            User u2 = new User();

            try
            {
                u2.setUsername("test2");
                u2.setPassword(aESEncryptor.encrypt("user2"));
            } catch (Exception e)
            {
                e.printStackTrace();
            }

//            For the winds
            u2.setFirstName("Dirk");
            u2.setLastName("Banaan");
            u2.setRole("admin");
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
                c1.setUser(u1);
                c1.setFirstName("Pieter" + Math.round(Math.random() * 1000));
                c1.setLastName("Paukeman" + Math.round(Math.random() * 1000));
                c1.setInstrument("slagwerk");
                c1.setNotes("Geweldige speler");
                c1.setPhone("06123123123" + Math.round(Math.random() * 100));
                c1.setEmail("p.paukeman@gmail.com" + Math.round(Math.random() * 100));

                //Contact2
                Contact c2 = new Contact();
                c2.setUser(u2);
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
                a.setDate(new java.sql.Date(115, 6, i));
                a.setStartTime(new Time((int) Math.round(Math.random() * 10), 00, 0));
                a.setEndTime(new Time((int) Math.round(Math.random() * 10), 00, 0));
                a.setDescription("bla " + String.valueOf(Math.round(Math.random() * 100)));
                a.setLocation("Griffioen, Amstelveen");
                a.setProjectId(p.getId());
                a.setContactId(0L);
                if (defaultRepository.persisted(a))
                {
                    msgBuffer.append(a.getDate());
                    msgBuffer.append("\n");
                }
            }
            msg.info("Standard master activities added: \n"
                    .concat(msgBuffer.toString()));

        }
    }

}
