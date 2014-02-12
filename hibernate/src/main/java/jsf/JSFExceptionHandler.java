package jsf;

import java.util.Iterator;

import javax.faces.FacesException;
import javax.faces.application.ViewExpiredException;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.servlet.http.HttpServletRequest;


public class JSFExceptionHandler extends ExceptionHandlerWrapper {

    private ExceptionHandler wrapped;
    JSFExceptionHandlerFactory aa=null;
    public JSFExceptionHandler(ExceptionHandler wrapped) {
        this.wrapped = wrapped;
    }

    @Override
    public void handle() throws FacesException {
        FacesContext facesContext = FacesContext.getCurrentInstance();

        for (Iterator<ExceptionQueuedEvent> iter = getUnhandledExceptionQueuedEvents().iterator(); iter.hasNext();) {
            Throwable exception = iter.next().getContext().getException();
            HttpServletRequest request=(HttpServletRequest) facesContext.getExternalContext().getRequest();
            request.getSession().setAttribute("JSF_ERROR", exception);
            if (exception instanceof ViewExpiredException) {
                facesContext.getApplication().getNavigationHandler().handleNavigation(facesContext, null, "exception");
                facesContext.renderResponse();
                iter.remove();
            }
            if (exception instanceof org.jboss.weld.context.ContextNotActiveException) {
                facesContext.getApplication().getNavigationHandler().handleNavigation(facesContext, null, "exception");
                facesContext.renderResponse();
                iter.remove();
            }
            if (exception instanceof org.jboss.weld.context.NonexistentConversationException) {
                facesContext.getApplication().getNavigationHandler().handleNavigation(facesContext, null, "exception");
                facesContext.renderResponse();
                iter.remove();
            }
            
            
            facesContext.getApplication().getNavigationHandler().handleNavigation(facesContext, null, "exception.xhtml");
            facesContext.renderResponse();
            getWrapped().handle();
            return;
            //iter.remove();
        }

        getWrapped().handle();
    }

    @Override
    public ExceptionHandler getWrapped() {
        return wrapped;
    }

}
