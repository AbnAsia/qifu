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

import org.qifu.base.controller.BaseController;
import org.qifu.base.exception.ControllerException;
import org.qifu.base.exception.ServiceException;
import org.qifu.po.TbSysExpression;
import org.qifu.service.ISysExpressionService;
import org.qifu.service.logic.ISystemExpressionLogicService;
import org.qifu.vo.SysExpressionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@Controller
public class SystemExpressionAction extends BaseController {
	
	private ISysExpressionService<SysExpressionVO, TbSysExpression, String> sysExpressionService;
	private ISystemExpressionLogicService systemExpressionLogicService;
	
	public ISysExpressionService<SysExpressionVO, TbSysExpression, String> getSysExpressionService() {
		return sysExpressionService;
	}
	
	@Autowired
	@Resource(name="core.service.SysExpressionService")
	@Required
	public void setSysExpressionService(ISysExpressionService<SysExpressionVO, TbSysExpression, String> sysExpressionService) {
		this.sysExpressionService = sysExpressionService;
	}
	
	public ISystemExpressionLogicService getSystemExpressionLogicService() {
		return systemExpressionLogicService;
	}
	
	@Autowired
	@Resource(name="core.service.logic.SystemExpressionLogicService")
	@Required
	public void setSystemExpressionLogicService(ISystemExpressionLogicService systemExpressionLogicService) {
		this.systemExpressionLogicService = systemExpressionLogicService;
	}
	
	private void fetchData(SysExpressionVO sysExpression, ModelAndView mv) throws ServiceException, ControllerException, Exception {
		
	}
	
	
	
}
