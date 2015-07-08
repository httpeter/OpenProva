package org.om.controller;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.om.model.Labels;
import org.om.repositories.LabelRepository;
import org.om.util.FMessage;

@ManagedBean
@SessionScoped
public class IndexController implements Serializable
{

    private final HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
            .getExternalContext()
            .getSession(true);

    private final FMessage msg = new FMessage();

    private Labels labels;

    private String currentFragment;

    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public Labels getLabels()
    {

        return labels;
    }

    public void setLabels(Labels labels)
    {
        this.labels = labels;
    }

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
            new org.om.util.DBScaffolder().restore();
        }
        LabelRepository labelRepository = new LabelRepository("OrchestraManagerPU");
        labels = labelRepository.getLabels();
        session.setAttribute("labels", labels);
        labelRepository.close();
        this.showHomeFragment();
    }

    public void showHomeFragment()
    {
        currentFragment = "resources/fragments/home.xhtml";
    }

    public void showNewFragment()
    {
        currentFragment = "resources/fragments/new.xhtml";
    }

    public void showMembersFragment()
    {
        currentFragment = "resources/fragments/members.xhtml";
    }

    public void showAdminFragment()
    {
        currentFragment = "resources/fragments/admin.xhtml";
    }

}
