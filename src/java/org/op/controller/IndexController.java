package org.op.controller;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.op.util.FMessage;

@ManagedBean
@ViewScoped
public class IndexController implements Serializable
{

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

        FacesContext facesContext = FacesContext.getCurrentInstance();

        labelFile = facesContext.getExternalContext()
                .getInitParameter("labelFile");

        currentFragment = "resources/fragments/home.xhtml";

        //Calling db schaffolding when in dev. 
        if (facesContext.getApplication()
                .getProjectStage()
                .toString()
                .equalsIgnoreCase("development"))
        {
            new org.op.util.DBScaffolder().restore();
        }
    }

}
