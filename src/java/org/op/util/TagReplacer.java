// This code covered by the Apache2 License: http://www.apache.org/licenses/LICENSE-2.0
// You are free to use it for your own good as long as it doesn't hurt anybody.
// For questions or suggestions please contact me at httpeter@gmail.com

package org.op.util;

import java.text.SimpleDateFormat;
import org.op.data.model.Contact;
import org.op.data.model.Project;

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
