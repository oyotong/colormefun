package shop.common.action.auto;

import com.colormefun.entity.MfUser;
import shop.common.context.ApplicationContext;
import shop.company.entity.AuthUser;

import com.opensymphony.xwork2.ActionSupport;

public class AbstractAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected ApplicationContext context = ApplicationContext.getContext();

	public AuthUser getCurrentAuthUser() {
		return getContext().getCurrentAuthUser();
	}

    public MfUser getCurrentMfUser() {
        return getContext().getCurrentMfUser();
    }
	
	public ApplicationContext getContext() {
		return context;
	}

	public void setContext(ApplicationContext context) {
		this.context = context;
	}

	public void prepare() throws Exception {
		
	}
}
