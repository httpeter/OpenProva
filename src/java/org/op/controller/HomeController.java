/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

    public DashboardModel getDashboardModel()
    {
        return dashboardModel;
    }

    public void setDashboardModel(DashboardModel dashboardModel)
    {
        this.dashboardModel = dashboardModel;
    }

    public HomeController()
    {
        dashboardModel = new DefaultDashboardModel();
        DashboardColumn column1 = new DefaultDashboardColumn();
        DashboardColumn column2 = new DefaultDashboardColumn();
        DashboardColumn column3 = new DefaultDashboardColumn();

        column1.addWidget("a");
        column1.addWidget("b");
        
        column2.addWidget("c");
        column2.addWidget("d");
        
        column3.addWidget("e");

        dashboardModel.addColumn(column1);
        dashboardModel.addColumn(column2);
        dashboardModel.addColumn(column3);

    }

}
