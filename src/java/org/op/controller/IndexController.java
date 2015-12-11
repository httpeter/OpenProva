package org.op.controller;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.op.util.FMessage;

@ManagedBean
@SessionScoped
public class IndexController implements Serializable
{

    private final FMessage msg;

    private String labelFile,
            currentFragment;



    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public String getCurrentFragment()
    {
        return currentFragment;
    }



    public void setCurrentFragment(String currentFragment)
    {
        this.currentFragment = currentFragment;
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


    public IndexController()
    {
        msg = new FMessage();

        ExternalContext externalContext = FacesContext.getCurrentInstance()
                .getExternalContext();

        labelFile = externalContext.getInitParameter("labelFile");

        currentFragment = "resources/fragments/home.xhtml";

        //Calling db schaffolding when in dev. 
        if (FacesContext
                .getCurrentInstance()
                .getApplication()
                .getProjectStage()
                .toString()
                .equalsIgnoreCase("DEVELOPMENT"))
        {
            new org.op.util.DBScaffolder().restore();
        }
    }

}
