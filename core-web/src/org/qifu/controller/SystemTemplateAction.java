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

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.qifu.base.controller.BaseController;
import org.qifu.base.exception.AuthorityException;
import org.qifu.base.exception.ControllerException;
import org.qifu.base.exception.ServiceException;
import org.qifu.base.model.ControllerMethodAuthority;
import org.qifu.po.TbSysTemplate;
import org.qifu.po.TbSysTemplateParam;
import org.qifu.service.ISysTemplateParamService;
import org.qifu.service.ISysTemplateService;
import org.qifu.service.logic.ISystemTemplateLogicService;
import org.qifu.vo.SysTemplateParamVO;
import org.qifu.vo.SysTemplateVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@Controller
public class SystemTemplateAction extends BaseController {
	
	private ISysTemplateService<SysTemplateVO, TbSysTemplate, String> sysTemplateService;
	private ISysTemplateParamService<SysTemplateParamVO, TbSysTemplateParam, String> sysTemplateParamService;
	private ISystemTemplateLogicService systemTemplateLogicService;
	
	public ISysTemplateService<SysTemplateVO, TbSysTemplate, String> getSysTemplateService() {
		return sysTemplateService;
	}
	
	@Autowired
	@Resource(name="core.service.SysTemplateService")
	@Required
	public void setSysTemplateService(ISysTemplateService<SysTemplateVO, TbSysTemplate, String> sysTemplateService) {
		this.sysTemplateService = sysTemplateService;
	}
	
	public ISysTemplateParamService<SysTemplateParamVO, TbSysTemplateParam, String> getSysTemplateParamService() {
		return sysTemplateParamService;
	}

	@Autowired
	@Resource(name="core.service.SysTemplateParamService")
	@Required
	public void setSysTemplateParamService(
			ISysTemplateParamService<SysTemplateParamVO, TbSysTemplateParam, String> sysTemplateParamService) {
		this.sysTemplateParamService = sysTemplateParamService;
	}

	public ISystemTemplateLogicService getSystemTemplateLogicService() {
		return systemTemplateLogicService;
	}

	@Autowired
	@Resource(name="core.service.logic.SystemTemplateLogicService")
	@Required	
	public void setSystemTemplateLogicService(ISystemTemplateLogicService systemTemplateLogicService) {
		this.systemTemplateLogicService = systemTemplateLogicService;
	}
	
	private void init(String type, HttpServletRequest request, ModelAndView mv) throws ServiceException, ControllerException, Exception {
		
	}
	
	@ControllerMethodAuthority(check = true, programId = "CORE_PROG001D0004Q")
	@RequestMapping(value = "/core.templateManagement.do")	
	public ModelAndView queryPage(HttpServletRequest request) {
		String viewName = PAGE_SYS_ERROR;
		ModelAndView mv = this.getDefaultModelAndView("CORE_PROG001D0004Q");
		try {
			this.init("queryPage", request, mv);
			viewName = "sys-template/sys-template-management";
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
