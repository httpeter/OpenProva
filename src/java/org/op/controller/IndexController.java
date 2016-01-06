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

    private String labelFile,
            currentComposition;



    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
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


    public IndexController()
    {
        facesContext = FacesContext.getCurrentInstance();

        labelFile = facesContext.getExternalContext()
                .getInitParameter("labelFile");

        compositionsDir = facesContext.getExternalContext()
                .getInitParameter("compositionsDir");

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
