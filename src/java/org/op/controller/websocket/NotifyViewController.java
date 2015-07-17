package org.op.controller.websocket;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.primefaces.push.EventBus;
import org.primefaces.push.EventBusFactory;

@ManagedBean
@RequestScoped
public class NotifyViewController
{

    private final static String CHANNEL = "/notify";

    private String name;

    private String detail;

    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public String getDetail()
    {
        return detail;
    }
    
    public void setDetail(String detail)
    {
        this.detail = detail;
    }
//</editor-fold>

    public void send()
    {
        EventBus eventBus = EventBusFactory.getDefault().eventBus();
        eventBus.publish(CHANNEL, new FacesMessage(name, detail));
    }
}
