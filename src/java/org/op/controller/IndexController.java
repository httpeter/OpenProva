package org.op.controller;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.op.util.FMessage;

@ManagedBean
@SessionScoped
public class IndexController implements Serializable
{

    private HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
            .getExternalContext()
            .getSession(true);

    private final FMessage msg = new FMessage();

    private String currentFragment;



    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public String getCurrentFragment()
    {
        return currentFragment;
    }



    public void setCurrentFragment(String currentFragment)
    {
        this.currentFragment = currentFragment;
    }

//</editor-fold>


    public IndexController()
    {
        if (FacesContext
                .getCurrentInstance()
                .getApplication()
                .getProjectStage()
                .toString()
                .equalsIgnoreCase("DEVELOPMENT"))
        {
            new org.op.util.DBScaffolder().restore();
        }
        currentFragment = "resources/fragments/home.xhtml";
    }

}
