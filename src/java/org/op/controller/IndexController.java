package org.op.controller;

import java.io.File;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@ViewScoped
public class IndexController implements Serializable
{

    private final FacesContext facesContext;

    private final String pageFragmentsDir;

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
        facesContext = FacesContext.getCurrentInstance();

        labelFile = facesContext.getExternalContext()
                .getInitParameter("labelFile");

        pageFragmentsDir = facesContext.getExternalContext()
                .getInitParameter("pageFragmentsDir");

        //Calling db schaffolding when in dev. 
        if (facesContext.getApplication()
                .getProjectStage()
                .toString()
                .equalsIgnoreCase("development"))
        {
            new org.op.util.DBScaffolder().restore();
        }

        this.navigate();
    }



    private void navigate()
    {
        String p = (String) facesContext.getExternalContext()
                .getRequestParameterMap()
                .get("p");

        if (p == null)
        {
            p = "home";
        }

        this.currentFragment = pageFragmentsDir
                + p
                + ".xhtml";
    }

}
