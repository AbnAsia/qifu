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

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.qifu.base.controller.BaseController;
import org.qifu.base.model.ControllerMethodAuthority;
import org.qifu.base.model.PageOf;
import org.qifu.base.model.QueryControllerJsonResultObj;
import org.qifu.base.model.QueryResult;
import org.qifu.base.model.SearchValue;
import org.qifu.base.model.YesNo;
import org.qifu.po.TbSys;
import org.qifu.service.ISysService;
import org.qifu.vo.SysVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@Controller
public class SystemSiteAction extends BaseController {
	
	private ISysService<SysVO, TbSys, String> sysService;
	
	public ISysService<SysVO, TbSys, String> getSysService() {
		return sysService;
	}

	@Autowired
	@Resource(name="core.service.SysService")
	@Required
	public void setSysService(ISysService<SysVO, TbSys, String> sysService) {
		this.sysService = sysService;
	}

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
	public @ResponseBody QueryControllerJsonResultObj<List<SysVO>> queryGrid(SearchValue searchValue, PageOf pageOf) {
		QueryControllerJsonResultObj<List<SysVO>> result = this.getQueryJsonResult("CORE_PROG001D0001Q");
		try {
			QueryResult<List<SysVO>> queryResult = this.sysService.findGridResult(searchValue, pageOf);
			this.setQueryGridJsonResult(result, queryResult, pageOf);
		} catch (Exception e) {
			e.printStackTrace();
			result.setSuccess( YesNo.NO );
			result.setMessage( e.getMessage().toString() );
		}
		return result;
	}	
	
	@ControllerMethodAuthority(check = true, programId = "CORE_PROG001D0001A")
	@RequestMapping(value = "/core.sysSiteCreate.do", method = RequestMethod.GET)
	public ModelAndView sysSiteCreate(HttpServletRequest request) {
		String viewName = PAGE_SYS_ERROR;
		ModelAndView mv = this.getDefaultModelAndView("CORE_PROG001D0001A");
		try {
			
			viewName = "syssite/syssite-create";
		} catch (Exception e) {
			e.printStackTrace();
			this.setPageMessage(request, e.getMessage().toString());
		}
		mv.setViewName(viewName);
		return mv;
	}
	
}
