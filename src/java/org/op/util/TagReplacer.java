/**
 *
 * This class can be used to replace the
 *
 * #!#contactFirstName#!#
 *
 * #!#contactLastName#!#
 *
 * #!#contactInstrument#!#
 *
 * #!#organizationName#!#
 *
 * #!#membersLoginCode#!#
 *
 * #!#selectedProjectName#!#
 *
 * #!#selectedProjectRepertoire#!#
 *
 * #!#selectedProjectStartDate#!#
 *
 * #!#selectedProjectEndDate#!#
 *
 * #!#organizationName#!#
 *
 * #!#personalURL#!# (doe iets met een contactID ipv een naam!)
 *
 *
 */
package org.op.util;

import java.text.SimpleDateFormat;
import org.op.model.Contact;
import org.op.model.Labels;
import org.op.model.Project;

/**
 *
 * @author PeterH
 */
public class TagReplacer
{

    public String getReplacedString(String inputString, Project p, Contact c, Labels l)
            throws Exception
    {

        String encryptedEmail = new AESEncryptor(l.getSixteenCharsEncryptionPassword(),
                l.getSixteenCharsEncryptionSalt()).encrypt(c.getEmail());
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        UrlGenerator urlGenerator = new UrlGenerator();

        return inputString
                .replace("#!#contactFirstName#!#", c.getFirstName())
                .replace("#!#contactLastName#!#", c.getLastName())
                .replace("#!#contactInstrument#!#", c.getInstrument())
                .replace("#!#organizationName#!#", l.getOrganizationName())
                .replace("#!#selectedProjectName#!#", p.getProjectName())
                .replace("#!#selectedProjectRepertoire#!#", p.getProjectRepertoire())
                .replace("#!#selectedProjectStartDate#!#", sdf.format(p.getProjectStartDate()))
                .replace("#!#selectedProjectEndDate#!#", sdf.format(p.getProjectEndDate()))
                .replace("#!#membersLoginCode#!#", encryptedEmail)
                .replace("#!#personalURL#!#", urlGenerator.getPersonalURL(c));

    }

}
