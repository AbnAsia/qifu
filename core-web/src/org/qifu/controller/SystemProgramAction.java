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
import org.qifu.base.exception.AuthorityException;
import org.qifu.base.exception.ControllerException;
import org.qifu.base.exception.ServiceException;
import org.qifu.base.model.ControllerMethodAuthority;
import org.qifu.base.model.PageOf;
import org.qifu.base.model.QueryControllerJsonResultObj;
import org.qifu.base.model.QueryResult;
import org.qifu.base.model.SearchValue;
import org.qifu.po.TbSysProg;
import org.qifu.service.ISysProgService;
import org.qifu.vo.SysProgVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@Controller
public class SystemProgramAction extends BaseController {
	
	private ISysProgService<SysProgVO, TbSysProg, String> sysProgService;
	
	public ISysProgService<SysProgVO, TbSysProg, String> getSysProgService() {
		return sysProgService;
	}

	@Autowired
	@Resource(name="core.service.SysProgService")
	@Required	
	public void setSysProgService(ISysProgService<SysProgVO, TbSysProg, String> sysProgService) {
		this.sysProgService = sysProgService;
	}

	@ControllerMethodAuthority(check = true, programId = "CORE_PROG001D0002Q")
	@RequestMapping(value = "/core.sysProgramManagement.do")
	public ModelAndView queryPage(HttpServletRequest request) {
		String viewName = PAGE_SYS_ERROR;
		ModelAndView mv = this.getDefaultModelAndView("CORE_PROG001D0002Q");
		try {
			// do some...
			viewName = "sys-program/sys-program-management";
		} catch (AuthorityException e) {
			viewName = PAGE_SYS_NO_AUTH;
		} catch (ServiceException | ControllerException e) {
			viewName = PAGE_SYS_SEARCH_NO_DATA;
		} catch (Exception e) {
			e.printStackTrace();
			this.setPageMessage(request, e.getMessage().toString());
		}
		mv.setViewName(viewName);
		return mv;
	}	
	
	@ControllerMethodAuthority(check = true, programId = "CORE_PROG001D0002Q")
	@RequestMapping(value = "/core.sysProgramQueryGridJson.do", produces = "application/json")	
	public @ResponseBody QueryControllerJsonResultObj<List<SysProgVO>> queryGrid(SearchValue searchValue, PageOf pageOf) {
		QueryControllerJsonResultObj<List<SysProgVO>> result = this.getQueryJsonResult("CORE_PROG001D0002Q");
		if (!this.isAuthorizeAndLoginFromControllerJsonResult(result)) {
			return result;
		}
		try {
			QueryResult<List<SysProgVO>> queryResult = this.sysProgService.findGridResult(searchValue, pageOf);
			this.setQueryGridJsonResult(result, queryResult, pageOf);
		} catch (AuthorityException | ServiceException | ControllerException e) {
			result.setMessage( e.getMessage().toString() );			
		} catch (Exception e) {
			e.printStackTrace();
			result.setMessage( e.getMessage().toString() );
		}
		return result;
	}	
	
	@ControllerMethodAuthority(check = true, programId = "CORE_PROG001D0002A")
	@RequestMapping(value = "/core.sysProgramCreate.do")
	public ModelAndView createPage(HttpServletRequest request) {
		String viewName = PAGE_SYS_ERROR;
		ModelAndView mv = this.getDefaultModelAndView("CORE_PROG001D0002A");
		try {
			// do some...
			viewName = "sys-program/sys-program-create";
		} catch (AuthorityException e) {
			viewName = PAGE_SYS_NO_AUTH;
		} catch (ServiceException | ControllerException e) {
			viewName = PAGE_SYS_SEARCH_NO_DATA;
		} catch (Exception e) {
			e.printStackTrace();
			this.setPageMessage(request, e.getMessage().toString());
		}
		mv.setViewName(viewName);
		return mv;
	}	
	
	@ControllerMethodAuthority(check = true, programId = "CORE_PROG001D0002E")
	@RequestMapping(value = "/core.sysProgramEdit.do")
	public ModelAndView editPage(HttpServletRequest request, @RequestParam(name="oid") String oid) {
		String viewName = PAGE_SYS_ERROR;
		ModelAndView mv = this.getDefaultModelAndView("CORE_PROG001D0002E");
		try {
			// do some...
			viewName = "sys-program/sys-program-edit";
		} catch (AuthorityException e) {
			viewName = PAGE_SYS_NO_AUTH;
		} catch (ServiceException | ControllerException e) {
			viewName = PAGE_SYS_SEARCH_NO_DATA;
		} catch (Exception e) {
			e.printStackTrace();
			this.setPageMessage(request, e.getMessage().toString());
		}
		mv.setViewName(viewName);
		return mv;
	}		
	
}
