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
package org.qifu.controller;

import javax.servlet.http.HttpServletRequest;

import org.qifu.base.controller.BaseController;
import org.qifu.base.model.ControllerMethodAuthority;
import org.qifu.base.model.PageOf;
import org.qifu.base.model.QueryControllerJsonResultObj;
import org.qifu.base.model.SearchValue;
import org.qifu.base.model.YesNo;
import org.qifu.vo.SysVO;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@Controller
public class SystemSiteAction extends BaseController {
	
	@ControllerMethodAuthority(check = true, programId = "CORE_PROG001D0001Q")
	@RequestMapping(value = "/core.sysSiteManagement.do", method = RequestMethod.GET)
	public ModelAndView sysSiteManagement(HttpServletRequest request) {
		String viewName = PAGE_SYS_ERROR;
		ModelAndView mv = this.getDefaultModelAndView("CORE_PROG001D0001Q");
		try {
			
			viewName = "syssite/syssite-management";
		} catch (Exception e) {
			e.printStackTrace();
			this.setPageMessage(request, e.getMessage().toString());
		}
		mv.setViewName(viewName);
		return mv;
	}
	
	@ControllerMethodAuthority(check = true, programId = "CORE_PROG001D0001Q")
	@RequestMapping(value = "/core.sysSiteQueryGridJson.do", produces = "application/json")	
	public @ResponseBody QueryControllerJsonResultObj<SysVO> queryGrid(SearchValue searchValue, PageOf pageOf) {
		QueryControllerJsonResultObj<SysVO> result = this.getQueryJsonResult("CORE_PROG001D0001Q");
		try {
			//http://127.0.0.1:8080/core-web/core.sysSiteQueryGridJson.do?parameter[name]=BBB&parameter[id]=123&&select=1&showRow=10&sortType=ASC&orderBy=NAME
			result.setSuccess(YesNo.YES);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess( YesNo.NO );
			result.setMessage( e.getMessage().toString() );
		}
		return result;
	}	
	
	/*
	@ControllerMethodAuthority(check = true, programId = "CORE_PROG001D0001Q")
	@RequestMapping(value = "/core.sysSiteQueryGridTestJson.do", produces = "application/json")	
	public @ResponseBody QueryControllerJsonResultObj<SysVO> queryGrid2(HttpServletRequest request) {
		QueryControllerJsonResultObj<SysVO> result = this.getQueryJsonResult("CORE_PROG001D0001Q");
		SearchValue searchValue = this.getSearchValue(request);
		PageOf pageOf = this.getPageOf(request);
		try {
			//http://127.0.0.1:8080/core-web/core.sysSiteQueryGridTestJson.do?parameter.name=BBB&parameter.id=123&&select=1&showRow=10&sortType=ASC&orderBy=NAME
			result.setSuccess(YesNo.YES);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess( YesNo.NO );
			result.setMessage( e.getMessage().toString() );
		}
		return result;
	}
	*/
	
}
