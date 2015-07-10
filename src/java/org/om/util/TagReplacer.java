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
package org.om.util;

import java.text.SimpleDateFormat;
import org.om.model.Contact;
import org.om.model.Project;

/**
 *
 * @author PeterH
 */
public class TagReplacer
{

    public String getReplacedString(String inputString, Project p, Contact c)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        UrlGenerator urlGenerator = new UrlGenerator();
        return inputString
                .replace("#!#contactFirstName#!#".toLowerCase(), c.getFirstName())
                .replace("#!#contactLastName#!#".toLowerCase(), c.getLastName())
                .replace("#!#contactInstrument#!#".toLowerCase(), c.getInstrument())
                .replace("#!#selectedProjectName#!#".toLowerCase(), p.getProjectName())
                .replace("#!#selectedProjectRepertoire#!#".toLowerCase(), p.getProjectRepertoire())
                .replace("#!#selectedProjectStartDate#!#".toLowerCase(), sdf.format(p.getProjectStartDate()))
                .replace("#!#selectedProjectEndDate#!#".toLowerCase(), sdf.format(p.getProjectEndDate()))
                .replace("#!#personalURL#!#".toLowerCase(),urlGenerator.getPersonalURL(c));

    }

}
