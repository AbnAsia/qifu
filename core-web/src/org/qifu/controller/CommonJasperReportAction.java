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

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.qifu.base.controller.BaseController;
import org.qifu.base.exception.AuthorityException;
import org.qifu.base.exception.ControllerException;
import org.qifu.base.exception.ServiceException;
import org.qifu.base.model.ControllerMethodAuthority;
import org.qifu.util.JReportUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@Controller
public class CommonJasperReportAction extends BaseController {
	
	@ControllerMethodAuthority(check = true, programId = "CORE_PROGCOMM0004Q")
	@RequestMapping(value = "/core.commonOpenJasperReport.do")
	public String processReport(HttpServletRequest request, HttpServletResponse response, @RequestParam("jreportId") String jreportId) throws UnsupportedEncodingException, IOException {
		String viewName = "";
		try {
			JReportUtils.fillReportToResponse(
					jreportId, 
					JReportUtils.getParameter(jreportId, request), 
					response);
		} catch (AuthorityException | ServiceException | ControllerException e) {
			viewName = PAGE_SYS_SEARCH_NO_DATA;
			e.printStackTrace();
		} catch (Exception e) {
			viewName = PAGE_SYS_ERROR;
			e.printStackTrace();
			this.setPageMessage(request, e.getMessage().toString());
		}
		if (!StringUtils.isBlank(viewName)) {
			return viewName;
		}
		return null;
	}
	
}
