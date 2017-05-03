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

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.qifu.base.controller.BaseController;
import org.qifu.base.exception.AuthorityException;
import org.qifu.base.exception.ControllerException;
import org.qifu.base.exception.ServiceException;
import org.qifu.base.model.ControllerMethodAuthority;
import org.qifu.base.model.DefaultControllerJsonResultObj;
import org.qifu.base.model.DefaultResult;
import org.qifu.base.model.PageOf;
import org.qifu.base.model.QueryControllerJsonResultObj;
import org.qifu.base.model.QueryResult;
import org.qifu.base.model.SearchValue;
import org.qifu.base.model.YesNo;
import org.qifu.model.MenuItemType;
import org.qifu.po.TbSys;
import org.qifu.po.TbSysIcon;
import org.qifu.po.TbSysProg;
import org.qifu.service.ISysIconService;
import org.qifu.service.ISysProgService;
import org.qifu.service.ISysService;
import org.qifu.service.logic.ISystemProgramLogicService;
import org.qifu.util.IconUtils;
import org.qifu.util.SimpleUtils;
import org.qifu.vo.SysIconVO;
import org.qifu.vo.SysProgVO;
import org.qifu.vo.SysVO;
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
	private ISysService<SysVO, TbSys, String> sysService;
	private ISysIconService<SysIconVO, TbSysIcon, String> sysIconService;
	private ISystemProgramLogicService systemProgramLogicService;
	
	public ISysProgService<SysProgVO, TbSysProg, String> getSysProgService() {
		return sysProgService;
	}

	@Autowired
	@Resource(name="core.service.SysProgService")
	@Required	
	public void setSysProgService(ISysProgService<SysProgVO, TbSysProg, String> sysProgService) {
		this.sysProgService = sysProgService;
	}
	
	public ISysService<SysVO, TbSys, String> getSysService() {
		return sysService;
	}

	@Autowired
	@Resource(name="core.service.SysService")
	@Required
	public void setSysService(ISysService<SysVO, TbSys, String> sysService) {
		this.sysService = sysService;
	}
	
	public ISysIconService<SysIconVO, TbSysIcon, String> getSysIconService() {
		return sysIconService;
	}

	@Autowired
	@Resource(name="core.service.SysIconService")
	@Required	
	public void setSysIconService(ISysIconService<SysIconVO, TbSysIcon, String> sysIconService) {
		this.sysIconService = sysIconService;
	}	

	public ISystemProgramLogicService getSystemProgramLogicService() {
		return systemProgramLogicService;
	}

	@Autowired
	@Resource(name="core.service.logic.SystemProgramLogicService")
	@Required	
	public void setSystemProgramLogicService(ISystemProgramLogicService systemProgramLogicService) {
		this.systemProgramLogicService = systemProgramLogicService;
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
	
	private void init(String type, HttpServletRequest request, ModelAndView mv) throws ServiceException, ControllerException, Exception {
		mv.addObject( "sysMap", this.sysService.findSysMap(super.getBasePath(request), true) );
		mv.addObject( "iconMap", IconUtils.getIconsSelectData() );
	}
	
	@ControllerMethodAuthority(check = true, programId = "CORE_PROG001D0002A")
	@RequestMapping(value = "/core.sysProgramCreate.do")
	public ModelAndView createPage(HttpServletRequest request) {
		String viewName = PAGE_SYS_ERROR;
		ModelAndView mv = this.getDefaultModelAndView("CORE_PROG001D0002A");
		try {
			this.init("createPage", request, mv);
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
	
	private void checkFields(DefaultControllerJsonResultObj<SysProgVO> result, SysProgVO sysProg, String sysOid, String iconOid, String w, String h) throws ControllerException, Exception {
		this.getCheckControllerFieldHandler(result)
		.testField("progSystemOid", ( this.noSelect(sysOid) ), "Please select system!")
		.testField("progId", sysProg, "@org.apache.commons.lang3.StringUtils@isBlank(progId)", "Id is blank!")
		.testField("progId", ( this.noSelect(sysProg.getProgId()) ), "Please change Id value!") // PROG-ID 不能用  "all" 這個下拉值
		.testField("progId", ( !SimpleUtils.checkBeTrueOf_azAZ09(super.defaultString(sysProg.getProgId()).replaceAll("-", "").replaceAll("_", "")) ), "Id only normal character!")
		.testField("name", sysProg, "@org.apache.commons.lang3.StringUtils@isBlank(name)", "Name is blank!")
		.testField("url", ( (MenuItemType.ITEM.equals(sysProg.getItemType()) && StringUtils.isBlank(sysProg.getUrl())) ), "URL is blank!")
		.testField("itemType", ( this.noSelect(sysProg.getItemType()) ), "Please select item-type!")
		.testField("iconOid", ( this.noSelect(iconOid) ), "Please select icon!")
		.testField("dialogWidth", ( (YesNo.YES.equals(sysProg.getIsDialog()) && !NumberUtils.isNumber(w)) ), "Please input dialog width!")
		.testField("dialogHeight", ( (YesNo.YES.equals(sysProg.getIsDialog()) && !NumberUtils.isNumber(h)) ), "Please input dialog height!")
		.throwMessage();		
	}
	
	private void save(DefaultControllerJsonResultObj<SysProgVO> result, SysProgVO sysProg, String sysOid, String iconOid, String w, String h) throws AuthorityException, ControllerException, ServiceException, Exception {
		this.checkFields(result, sysProg, sysOid, iconOid, w, h);
		sysProg.setDialogW( NumberUtils.toInt(w) );
		sysProg.setDialogH( NumberUtils.toInt(h) );
		DefaultResult<SysProgVO> progResult = this.systemProgramLogicService.create(sysProg, sysOid, iconOid);
		if (progResult.getValue() != null) {
			result.setValue( progResult.getValue() );
			result.setSuccess( YesNo.YES );
		}
		result.setMessage( progResult.getSystemMessage().getValue() );
	}
	
	@ControllerMethodAuthority(check = true, programId = "CORE_PROG001D0002A")
	@RequestMapping(value = "/core.sysProgramSaveJson.do", produces = "application/json")		
	public @ResponseBody DefaultControllerJsonResultObj<SysProgVO> doSave(HttpServletRequest request, SysProgVO sysProg) {
		DefaultControllerJsonResultObj<SysProgVO> result = this.getDefaultJsonResult("CORE_PROG001D0002A");
		if (!this.isAuthorizeAndLoginFromControllerJsonResult(result)) {
			return result;
		}
		try {
			this.save(
					result, 
					sysProg, 
					request.getParameter("progSystemOid"),
					request.getParameter("iconOid"),
					request.getParameter("dialogWidth"), 
					request.getParameter("dialogHeight"));
		} catch (AuthorityException | ServiceException | ControllerException e) {
			result.setMessage( e.getMessage().toString() );			
		} catch (Exception e) {
			e.printStackTrace();
			result.setMessage( e.getMessage().toString() );
		}
		return result;		
	}
	
}
