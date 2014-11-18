package shop.common.action;

import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.colormefun.entity.MfUser;
import shop.common.context.ApplicationContext;
import shop.company.entity.AuthUser;
import shop.company.entity.SystemLoginLog;


public class OnLineListener implements HttpSessionListener,
		HttpSessionBindingListener, HttpSessionAttributeListener {

	private static final List<SystemLoginLog> memList = new Vector<SystemLoginLog>();

	private ApplicationContext context = ApplicationContext.getContext();

	public OnLineListener() {
	}

	public void valueUnbound(HttpSessionBindingEvent arg0) {
		/*System.out
				.println("--------------------------------------valueUnbound");*/
		logout(arg0);

	}

	public void valueBound(HttpSessionBindingEvent arg0) {
		/*System.out.println("--------------------------------------valueBound");*/
		login(arg0);

	}

	public void sessionCreated(HttpSessionEvent arg0) {
		//System.out.println("--------------------------------------Create");
	}

	public void sessionDestroyed(HttpSessionEvent arg0) {
		Object obj = arg0.getSession().getAttribute(
				context.getConfiguration(shop.Constants.SESSION_MF_USER_NAME));
		if (null != obj) {
			memList.remove(obj);
		}

		obj = arg0.getSession().getAttribute(
				context.getConfiguration(shop.Constants.SESSION_AUTH_USER_NAME));
		if (null != obj) {
			memList.remove(obj);
		}
	}

	public static List<SystemLoginLog> getMemList() {
		return memList;
	}

	@Override
	public void attributeAdded(HttpSessionBindingEvent arg0) {
		/*System.out
				.println("--------------------------------------attributeAdded");*/
		login(arg0);

	}

	private void login(HttpSessionBindingEvent arg0) {
		if (arg0.getName().equals(
				context.getConfiguration(shop.Constants.SESSION_AUTH_USER_NAME))) {
			AuthUser usr = (AuthUser) arg0.getValue();

		}
        if (arg0.getName().equals(
                context.getConfiguration(shop.Constants.SESSION_MF_USER_NAME))) {
            MfUser usr = (MfUser) arg0.getValue();

        }
	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent arg0) {
		/*System.out
				.println("--------------------------------------attributeRemoved");*/
		logout(arg0);
	}

	private void logout(HttpSessionBindingEvent arg0) {
		if (arg0.getName().equals(
				context.getConfiguration(shop.Constants.SESSION_MF_USER_NAME))
				|| arg0.getName().equals(
						context.getConfiguration(shop.Constants.SESSION_AUTH_USER_NAME))) {
			SystemLoginLog log = null;
			if(null != context.getSession()){
				log = (SystemLoginLog) context.getSession()
				.getAttribute(context.getConfiguration("Session.LoginLog"));
				memList.remove(log);
			}			
		}
	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent arg0) {

	}

}
