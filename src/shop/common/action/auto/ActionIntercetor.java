package shop.common.action.auto;


import org.apache.log4j.Logger;

import shop.common.exception.ApplicationException;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.Interceptor;

public class ActionIntercetor implements Interceptor {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger("[ACTION]");

	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		log.debug("---  Action Method Starting  ---\n");
		ActionSupport action = (ActionSupport) arg0.getAction();
		
		String methodName = arg0.getInvocationContext().getName();

		log.debug("Action Class:" + action.getClass().getSimpleName()
				+ "; Method:" + methodName + " ");
		try {

			if (action.getFieldErrors().size() > 0) {
				log.error("Method: " + methodName + " error: "
						+ action.getFieldErrors());
				return ActionSupport.INPUT;
			}

			String result = arg0.invoke();

			if (action.getActionErrors().size() > 0) {
				log.error("Method: " + methodName + " error: "
						+ action.getActionErrors());
				return ActionSupport.INPUT;
			}

			log.debug("Method: " + methodName + " successed.");
			
			if(action.getActionMessages().size() == 0){
				action.addActionMessage("操作成功。");
			}

			return result;
		} catch (ApplicationException re) {
			log.error("Method: " + methodName + " failed", re);

			action.addActionError(re.getMessage());
			return ActionSupport.INPUT;
		}catch (Exception e) {
			e.printStackTrace();
			action.addActionError("500系统错误。");
			return ActionSupport.INPUT;
		}
	}

	@Override
	public void destroy() {

	}

	@Override
	public void init() {

	}
}
