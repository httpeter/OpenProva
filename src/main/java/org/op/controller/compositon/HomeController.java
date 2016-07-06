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
package org.op.controller.compositon;

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
