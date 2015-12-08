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
import org.op.data.model.Contact;
import org.op.data.model.Project;

/**
 *
 * @author PeterH
 */
public class TagReplacer
{

    public String getReplacedString(String inputString, Project p, Contact c, String organizationName)
            throws Exception
    {

        String encryptedEmail = new AESEncryptor().encrypt(c.getEmail());
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        UrlGenerator urlGenerator = new UrlGenerator();

        return inputString
                .replace("#!#contactFirstName#!#", c.getFirstName())
                .replace("#!#contactLastName#!#", c.getLastName())
                .replace("#!#contactInstrument#!#", c.getInstrument())
                .replace("#!#organizationName#!#", organizationName)
                .replace("#!#selectedProjectName#!#", p.getProjectName())
                .replace("#!#selectedProjectRepertoire#!#", p.getProjectRepertoire())
                .replace("#!#selectedProjectStartDate#!#", sdf.format(p.getProjectStartDate()))
                .replace("#!#selectedProjectEndDate#!#", sdf.format(p.getProjectEndDate()))
                .replace("#!#membersLoginCode#!#", encryptedEmail)
                .replace("#!#personalURL#!#", urlGenerator.getPersonalURL(c));

    }

}
