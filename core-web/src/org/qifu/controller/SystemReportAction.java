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
import org.qifu.base.model.DefaultControllerJsonResultObj;
import org.qifu.base.model.PageOf;
import org.qifu.base.model.QueryControllerJsonResultObj;
import org.qifu.base.model.QueryResult;
import org.qifu.base.model.SearchValue;
import org.qifu.po.TbSysJreport;
import org.qifu.service.ISysJreportService;
import org.qifu.service.logic.ISystemJreportLogicService;
import org.qifu.util.JReportUtils;
import org.qifu.util.SimpleUtils;
import org.qifu.vo.SysJreportVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@Controller
public class SystemReportAction extends BaseController {
	
	private ISysJreportService<SysJreportVO, TbSysJreport, String> sysJreportService;
	private ISystemJreportLogicService systemJreportLogicService;
	
	public ISysJreportService<SysJreportVO, TbSysJreport, String> getSysJreportService() {
		return sysJreportService;
	}
	
	@Autowired
	@Resource(name="core.service.SysJreportService")
	@Required	
	public void setSysJreportService(ISysJreportService<SysJreportVO, TbSysJreport, String> sysJreportService) {
		this.sysJreportService = sysJreportService;
	}
	
	public ISystemJreportLogicService getSystemJreportLogicService() {
		return systemJreportLogicService;
	}
	
	@Autowired
	@Resource(name="core.service.logic.SystemJreportLogicService")
	@Required	
	public void setSystemJreportLogicService(ISystemJreportLogicService systemJreportLogicService) {
		this.systemJreportLogicService = systemJreportLogicService;
	}
	
	private void init(String type, HttpServletRequest request, ModelAndView mv) throws ServiceException, ControllerException, Exception {
		
	}
	
	@ControllerMethodAuthority(check = true, programId = "CORE_PROG001D0005Q")
	@RequestMapping(value = "/core.sysReportManagement.do")	
	public ModelAndView queryPage(HttpServletRequest request) {
		String viewName = PAGE_SYS_ERROR;
		ModelAndView mv = this.getDefaultModelAndView("CORE_PROG001D0005Q");
		try {
			this.init("queryPage", request, mv);
			viewName = "sys-report/sys-report-management";
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
	
	@ControllerMethodAuthority(check = true, programId = "CORE_PROG001D0005Q")
	@RequestMapping(value = "/core.sysReportQueryGridJson.do", produces = "application/json")	
	public @ResponseBody QueryControllerJsonResultObj< List<SysJreportVO>>  queryGrid(SearchValue searchValue, PageOf pageOf) {
		QueryControllerJsonResultObj< List<SysJreportVO> > result = this.getQueryJsonResult("CORE_PROG001D0005Q");
		if (!this.isAuthorizeAndLoginFromControllerJsonResult(result)) {
			return result;
		}
		try {
			QueryResult< List<SysJreportVO> > queryResult = this.sysJreportService.findGridResult(searchValue, pageOf);
			this.setQueryGridJsonResult(result, queryResult, pageOf);
		} catch (AuthorityException | ServiceException | ControllerException e) {
			result.setMessage( e.getMessage().toString() );			
		} catch (Exception e) {
			e.printStackTrace();
			result.setMessage( e.getMessage().toString() );
		}
		return result;
	}	
	
	@ControllerMethodAuthority(check = true, programId = "CORE_PROG001D0005A")
	@RequestMapping(value = "/core.sysReportCreate.do")
	public ModelAndView createPage(HttpServletRequest request) {
		String viewName = PAGE_SYS_ERROR;
		ModelAndView mv = this.getDefaultModelAndView("CORE_PROG001D0005A");
		try {
			this.init("createPage", request, mv);
			viewName = "sys-report/sys-report-create";
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
	
	private void testReportPackage(String uploadOid) throws ServiceException, ControllerException, Exception {
		JReportUtils.selfTestDecompress4Upload(uploadOid);
	}	
	
	private void checkFields(DefaultControllerJsonResultObj<SysJreportVO> result, SysJreportVO sysJreport) throws ControllerException, Exception {
		this.getCheckControllerFieldHandler(result)
		.testField("reportId", sysJreport, "@org.apache.commons.lang3.StringUtils@isBlank(templateId)", "Id is blank!")
		.testField("reportId", ( !SimpleUtils.checkBeTrueOf_azAZ09(super.defaultString(sysJreport.getReportId()).replaceAll("-", "").replaceAll("_", "")) ), "Id only normal character!")
		.testField("templateId", ( this.noSelect(sysJreport.getReportId()) ), "Please change Id value!") // 不能用  "all" 這個下拉值
		.throwMessage();
	}
	
}
