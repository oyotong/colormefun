package shop.common.action;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import shop.common.exception.ApplicationException;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Aspect
public class ActionMethodAspect {

	private static final String actionPackage2 = "shop.company.action";

	@Pointcut("execution(* " + actionPackage2 + "..*.*())" + " && !execution(* "
			+ actionPackage2 + ".*.get*())")
	public void allCompMethodPointcut() {

	}

	@Pointcut("allCompMethodPointcut()")
	public void MethodLoggerPointcut() {

	}

	@Around("MethodLoggerPointcut()")
	public Object doLoggerMethodProceeding(ProceedingJoinPoint pjp)
			throws Throwable {
		ActionSupport thiss = (ActionSupport) pjp.getTarget();
		//String methodName = pjp.getSignature().getName();
		String methodName = ActionContext.getContext().getName();
		Object[] args = pjp.getArgs();

		Long logId = null;

		try {
			
//			logId = LogUtils.log(null, thiss, methodName, args);
			
			Object result = pjp.proceed();
			
//			Collection<String> es = thiss.getActionErrors();
//			Collection<String> ms = thiss.getActionMessages();
//			if (es != null && es.size() > 0) {
//				LogUtils.log(logId, thiss, methodName, "����"
//						+ es.iterator().next(), args);
//			} else if (ms != null && ms.size() > 0) {
//				LogUtils.log(logId, thiss, methodName, ms.iterator().next(),
//						args);
//			}
//			LogUtils.log(logId, thiss, methodName, args);
			return result;
		} catch (ApplicationException re) {
//			LogUtils.log(logId, thiss, methodName,
//					"����" + re.getMessage(), args);
			throw re;
		}
	}

}
