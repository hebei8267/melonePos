package com.tjhx.web.aspect;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;

import com.tjhx.globals.Constants;
import com.tjhx.web.BaseController;

/**
 * Controller层切面、追加消息信息列表至页面模型
 */
@Aspect
public class ControllerAdvice {
	private static Logger logger = LoggerFactory.getLogger(ControllerAdvice.class);

	@Pointcut("execution(public * com.tjhx.web..*.*(..))")
	public void inControllerLayer() {
	}

	@Before(value = "inControllerLayer()")
	public void before(JoinPoint joinPoint) {
		// 连接点所在的目标对象
		if (joinPoint.getTarget() instanceof BaseController) {
			BaseController _controller = (BaseController) joinPoint.getTarget();
			// 初始化消息列表
			_controller.initMsgList();
		}
	}

	@After(value = "inControllerLayer()")
	public void after(JoinPoint joinPoint) throws UnsupportedEncodingException {
		// 连接点所在的目标对象
		if (joinPoint.getTarget() instanceof BaseController) {
			BaseController _controller = (BaseController) joinPoint.getTarget();
			// 页面重定向(Redirect）跳转时取得地址中的消息
			addRedirectMsg(_controller, joinPoint.getArgs());
			// 将消息保存至表单中
			_controller.insertMsgListToPageMode();
		}
	}

	/**
	 * 页面重定向(Redirect）跳转时取得地址中的消息
	 * 
	 * @param _controller
	 * @param args
	 * @param request
	 * @throws UnsupportedEncodingException
	 */
	private void addRedirectMsg(BaseController _controller, Object[] args) throws UnsupportedEncodingException {

		Model model = getModel(args);// 取得页面模型

		HttpServletRequest request = getRequest(args);// 取得HttpServletRequest
		if (null != request) {
			// 提示消息
			String tipMsg = request.getParameter(Constants.SESSION_TIP_MSG_LIST);
			if (null != tipMsg) {
				tipMsg = new String(tipMsg.getBytes("ISO8859_1"), "UTF-8");
				_controller.addTipMsgStr(model, tipMsg);
			}
			// 警告消息
			String warnMsg = request.getParameter(Constants.SESSION_WARN_MSG_LIST);
			if (null != warnMsg) {
				warnMsg = new String(warnMsg.getBytes("ISO8859_1"), "UTF-8");
				_controller.addWarnMsgStr(model, warnMsg);
			}
			// 错误消息
			String errMsg = request.getParameter(Constants.SESSION_ERR_MSG_LIST);
			if (null != errMsg) {
				errMsg = new String(errMsg.getBytes("ISO8859_1"), "UTF-8");
				_controller.addErrMsgStr(model, errMsg);
			}
		}

	}

	@AfterThrowing(pointcut = "com.tjhx.web.aspect.ControllerAdvice.inControllerLayer()", throwing = "ex")
	public void throwException(JoinPoint joinPoint, Throwable ex) {
		logger.info(ex.getMessage(), ex);
	}

	/**
	 * 取得页面模型(Model)
	 * 
	 * @param args
	 * @return
	 */
	private Model getModel(Object[] args) {
		for (int i = 0; i < args.length; i++) {
			if (args[i] instanceof Model) {
				return (Model) args[i];
			}
		}
		return null;
	}

	/**
	 * 取得HttpServletRequest
	 * 
	 * @param args
	 * @return
	 */
	private HttpServletRequest getRequest(Object[] args) {
		for (int i = 0; i < args.length; i++) {
			if (args[i] instanceof HttpServletRequest) {
				return (HttpServletRequest) args[i];
			}
		}
		return null;
	}
}
