package com.tjhx.common.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tjhx.entity.member.Function;
import com.tjhx.entity.member.User;
import com.tjhx.globals.Constants;

public class SecurityFilter implements Filter {
	private static Logger logger = LoggerFactory.getLogger(SecurityFilter.class);

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest _request, ServletResponse _response, FilterChain chain) throws IOException,
			ServletException {
		HttpServletRequest request = (HttpServletRequest) _request;
		HttpServletResponse response = (HttpServletResponse) _response;

		// 获取当前请求的URI
		String url = request.getRequestURI();
		logger.debug(url);

		if (url.endsWith("index.html") || url.endsWith(Constants.PAGE_REQUEST_PREFIX + "/index")
				|| url.endsWith(Constants.PAGE_REQUEST_PREFIX + "/member/login")
				|| url.endsWith(Constants.PAGE_REQUEST_PREFIX + "/encrypt")) {// 对URL地址为此结尾的文件不过滤
			chain.doFilter(request, _response);
		} else {
			User user = (User) request.getSession().getAttribute(Constants.SESSION_USER_INFO);
			if (null != user) {
				boolean _result = permCheck(url, user.getRole().getNoPermList());
				if (_result) {
					chain.doFilter(request, _response);
				} else {
					response.sendRedirect(request.getContextPath() + "/index.html");
				}
			} else {
				response.sendRedirect(request.getContextPath() + "/index.html");
			}
		}

	}

	private boolean permCheck(String url, List<Function> funList) {
		for (Function function : funList) {

			String _tmpUrl = "/" + Constants.PAGE_REQUEST_PREFIX + "/" + function.getFunUrl();

			if (url.startsWith(_tmpUrl)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}
