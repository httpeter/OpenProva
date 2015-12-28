package org.op.controller;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.DashboardColumn;
import org.primefaces.model.DashboardModel;
import org.primefaces.model.DefaultDashboardColumn;
import org.primefaces.model.DefaultDashboardModel;

/**
 *
 * @author PeterH
 */
@ManagedBean
@ViewScoped
public class HomeController implements Serializable
{

    private DashboardModel dashboardModel;



    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public DashboardModel getDashboardModel()
    {
        return dashboardModel;
    }



    public void setDashboardModel(DashboardModel dashboardModel)
    {
        this.dashboardModel = dashboardModel;
    }
//</editor-fold>



    public HomeController()
    {
        dashboardModel = new DefaultDashboardModel();

        DashboardColumn column1 = new DefaultDashboardColumn();
        DashboardColumn column2 = new DefaultDashboardColumn();

        column1.addWidget("dashboardInfoPanel");
        column2.addWidget("dashboardSubscriptionPanel");

        dashboardModel.addColumn(column1);
        dashboardModel.addColumn(column2);

    }

}
