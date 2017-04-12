/* 
 * Copyright 2012-2017 qifu of copyright Chen Xin Nien
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 * -----------------------------------------------------------------------
 * 
 * author: 	Chen Xin Nien
 * contact: chen.xin.nien@gmail.com
 * 
 */
package org.qifu.base.interceptor;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.qifu.base.Constants;
import org.qifu.base.model.ControllerMethodAuthority;
import org.qifu.base.model.YesNo;
import org.qifu.sys.SysEventLogSupport;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class ControllerAuthorityCheckInterceptor implements HandlerInterceptor {
	protected static Logger logger = Logger.getLogger(ControllerAuthorityCheckInterceptor.class);

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelView) throws Exception {
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String url = request.getServletPath();
		Subject subject = SecurityUtils.getSubject();
		
		if (subject.hasRole(Constants.SUPER_ROLE_ALL) || subject.hasRole(Constants.SUPER_ROLE_ADMIN)) {
			SysEventLogSupport.log( (String)subject.getPrincipal(), Constants.getSystem(), url, true );
			return true;
		}		
		Method method = ((HandlerMethod) handler).getMethod();
		Annotation[] actionMethodAnnotations = method.getAnnotations();
		
		if (this.isControllerAuthority(actionMethodAnnotations, subject)) {
			SysEventLogSupport.log( (String)subject.getPrincipal(), Constants.getSystem(), url, true );
			return true;
		}		
		if (subject.isPermitted(url) || subject.isPermitted("/"+url)) {
			SysEventLogSupport.log( (String)subject.getPrincipal(), Constants.getSystem(), url, true );
			return true;
		}
		logger.warn("[decline] user=" + subject.getPrincipal() + " url=" + url);
		String isQifuPageChange = request.getParameter(Constants.QIFU_PAGE_IN_TAB_IFRAME);
		if (YesNo.YES.equals(isQifuPageChange)) { // dojox.layout.ContentPane 它的 X-Requested-With 是 XMLHttpRequest
			SysEventLogSupport.log( (String)subject.getPrincipal(), Constants.getSystem(), url, false );
			response.sendRedirect("/pages/system/auth1.jsp");
			return false;
		}
		String header = request.getHeader("X-Requested-With");
		if ("XMLHttpRequest".equalsIgnoreCase(header)) {
			response.getWriter().print(Constants.NO_AUTHZ_JSON_DATA);
			response.getWriter().flush();
			response.getWriter().close();
            SysEventLogSupport.log( (String)subject.getPrincipal(), Constants.getSystem(), url, false );
			return false;
		}
		SysEventLogSupport.log( (String)subject.getPrincipal(), Constants.getSystem(), url, false );
		response.sendRedirect("/pages/system/auth1.jsp");
		return false;
	}
	
	private boolean isControllerAuthority(Annotation[] actionMethodAnnotations, Subject subject) {
		if (actionMethodAnnotations!=null && actionMethodAnnotations.length>0) {
			for (Annotation anno : actionMethodAnnotations) {
				if (anno instanceof ControllerMethodAuthority) {
					if (!((ControllerMethodAuthority)anno).check()) { // check=false , 表示不要檢查權限
						return true;
					}
					String progId = ((ControllerMethodAuthority)anno).programId();
					if (StringUtils.isBlank(progId)) {
						return false;	
					}
					if (subject.isPermitted(progId)) {
						return true;
					}							
				}
			}
		}
		return true;
	}	

}
