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
package org.qifu.common.controller;

import javax.servlet.http.HttpServletRequest;

import org.qifu.base.controller.BaseController;
import org.qifu.base.exception.AuthorityException;
import org.qifu.base.exception.ControllerException;
import org.qifu.base.exception.ServiceException;
import org.qifu.base.model.ControllerMethodAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@Controller
public class TestAction extends BaseController {
	
	private void init(String type, HttpServletRequest request, ModelAndView mv) throws ServiceException, ControllerException, Exception {
		
	}
	
	@ControllerMethodAuthority(check = true, programId = "COMMON_PROG001D0001Q")
	@RequestMapping(value = "/common.test.do")	
	public ModelAndView queryPage(HttpServletRequest request) {
		String viewName = PAGE_SYS_ERROR;
		ModelAndView mv = this.getDefaultModelAndView("COMMON_PROG001D0001Q");
		try {
			this.init("queryPage", request, mv);
			viewName = "test/hello";
		} catch (AuthorityException e) {
			viewName = this.getAuthorityExceptionPage(e, request);
		} catch (ServiceException | ControllerException e) {
			viewName = this.getServiceOrControllerExceptionPage(e, request);
		} catch (Exception e) {
			this.getExceptionPage(e, request);
		}
		mv.setViewName(viewName);
		return mv;		
	}	

}
