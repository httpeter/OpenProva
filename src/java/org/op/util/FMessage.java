package org.op.util;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class FMessage implements Serializable
{

    private static final Logger logger = Logger.getLogger(FMessage.class.getName());



    public final void warn(String msg)
    {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_WARN,
                        msg, null));
        logger.log(Level.WARNING, msg);
    }



    public final void error(String msg)
    {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        msg, null));
        logger.log(Level.WARNING, msg);
    }



    public final void fatal(String msg)
    {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_FATAL,
                        msg, null));
        logger.log(Level.WARNING, msg);
    }



    public final void info(String msg)
    {
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO,
                        msg, null));
        logger.log(Level.INFO, msg);
    }

}
