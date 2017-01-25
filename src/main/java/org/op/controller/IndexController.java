//This webapp was created to enable the managers of amateur orchestras/ensembles 
//to have a basic database with contact information and to enable them to send 
//subscription requests to a large amount of contacts for a specific musical project.
//Contacts receive a personalized email containing a URL with which they can subscribe
//to a project and enter their presence on given rehearsal dates. 
//
//A management-login enables the orchestra managers/coordinators to see who will
//be present at a given date, view contact information and modify projects and 
//subscriptions.
//
//You're free to use the code as you please as long as it does not hurt anyone 
//(Apache2 lisence: http://www.apache.org/licenses/LICENSE-2.0 ). 
//
//For questions, feel free to contact me at httpeter@gmail.com
package org.op.controller;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@ViewScoped
public class IndexController implements Serializable
{

    private final FacesContext facesContext;

    private final String compositionsDir;

    private final boolean developmentStage;

    private String labelFile,
            currentComposition;



    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public boolean isDevelopmentStage()
    {
        return developmentStage;
    }



    public String getCompositionsDir()
    {
        return compositionsDir;
    }



    public String getCurrentComposition()
    {
        return currentComposition;
    }



    public void setCurrentComposition(String currentComposition)
    {
        this.currentComposition = currentComposition;
    }



    public String getLabelFile()
    {
        return labelFile;
    }



    public void setLabelFile(String labelFile)
    {
        this.labelFile = labelFile;
    }

//</editor-fold>


    /**
     * JSF managed bean responsible for page composition and property files such
     * as labels.
     *
     * Pages are composed of facelet page fragments located in the composition
     * folder.
     *
     * Constructor checks if the project stage is 'Development' or 'Production'.
     * in the 'web.xml' file.
     *
     * In 'Development' mode the DBSchaffolder class is called to populate the
     * database when it is empty. It is therefore ESSENTIAL to set the parameter
     * to 'Production' when using ths sytem in production to prevent data loss.
     *
     * Only in production mode Google Analytics is loaded.
     */
    public IndexController()
    {
        facesContext = FacesContext.getCurrentInstance();

        developmentStage = facesContext.getApplication()
                .getProjectStage()
                .toString()
                .equalsIgnoreCase("development");

        labelFile = facesContext.getExternalContext()
                .getInitParameter("labelFile");

        compositionsDir = facesContext.getExternalContext()
                .getInitParameter("compositionsDir");

        //Calling db schaffolding when in dev. mode 
        if (developmentStage)
        {
            new org.op.util.DBScaffolder().restore();
        }
        this.navigate();
    }



    /**
     * In order to facilitate SEO, page composition is done based on the request
     * parameter 'p'. When p is not found a HTTP 500 error is thrown. Kept
     * private for now. Might be useful to make public later..
     */
    private void navigate()
    {
        String p = facesContext.getExternalContext()
                .getRequestParameterMap()
                .get("p");

        if (p == null)
        {
            p = "home";
        }

        this.currentComposition = compositionsDir
                + p
                + ".xhtml";
    }

}
