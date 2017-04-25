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
package org.qifu.base.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.qifu.base.Constants;
import org.qifu.base.model.DefaultControllerJsonResultObj;
import org.qifu.base.model.PageOf;
import org.qifu.base.model.QueryControllerJsonResultObj;
import org.qifu.base.model.SearchValue;
import org.qifu.base.model.YesNo;
import org.qifu.util.MenuSupportUtils;
import org.qifu.util.SimpleUtils;
import org.springframework.web.servlet.ModelAndView;

public abstract class BaseController {
	protected static final String PAGE_SYS_LOGIN = "system/login";
	protected static final String PAGE_SYS_SEARCH_NO_DATA = "system/searchNoData";
	protected static final String PAGE_SYS_LOGIN_AGAIN = "system/login_again";
	protected static final String PAGE_SYS_NO_AUTH = "system/auth1";
	protected static final String PAGE_SYS_ERROR = "system/error";
	
	protected static final String REDIRECT_INDEX = "index.do";
	
	public String getPageRedirect(String url) {
		return "redirect:/" + url;
	}
	
	public ModelAndView getDefaultModelAndView() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("errorContact", this.getErrorContact());
		mv.addObject("verMsg", this.getVerMsg());
		mv.addObject("jsVerBuild", this.getJsVerBuild());
		mv.addObject("loginCaptchaCodeEnable", this.getLoginCaptchaCodeEnable());
		mv.addObject("googleMapEnable", this.getGoogleMapEnable());
		mv.addObject("googleMapUrl", this.getGoogleMapUrl());
		mv.addObject("googleMapKey", this.getGoogleMapKey());
		mv.addObject("googleMapDefaultLat", this.getGoogleMapDefaultLat());
		mv.addObject("googleMapDefaultLng", this.getGoogleMapDefaultLng());
		mv.addObject("googleMapLanguage", this.getGoogleMapLanguage());
		mv.addObject("googleMapClientLocationEnable", this.getGoogleMapClientLocationEnable());
		mv.addObject("twitterEnable", this.getTwitterEnable());
		mv.addObject("isSuperRole", this.isSuperRole());
		return mv;
	}
	
	public ModelAndView getDefaultModelAndView(String progId) {
		ModelAndView mv = this.getDefaultModelAndView();
		if (StringUtils.isBlank(progId)) {
			return mv;
		}
		mv.addObject("programId", progId);
		mv.addObject("programName", MenuSupportUtils.getProgramName(progId));
		return mv;
	}	
	
	public String getErrorContact() {
		return String.valueOf( Constants.getSettingsMap().get("basePage.errorContact") );
	}
	
	public String getVerMsg() {
		return String.valueOf( Constants.getSettingsMap().get("basePage.verMsg") );
	}
	
	public String getJsVerBuild() {
		return String.valueOf( Constants.getSettingsMap().get("basePage.jsVerBuild") );
	}
	
	public String getLoginCaptchaCodeEnable() {
		return Constants.getLoginCaptchaCodeEnable();
	}
	
	public String getGoogleMapEnable() {
		return String.valueOf( Constants.getSettingsMap().get("googleMap.enable") );
	}	
	
	public String getGoogleMapUrl() {
		return String.valueOf( Constants.getSettingsMap().get("googleMap.url") );
	}
	
	public String getGoogleMapKey() {
		return String.valueOf( Constants.getSettingsMap().get("googleMap.key") );
	}	
	
	public String getGoogleMapDefaultLat() {
		return String.valueOf( Constants.getSettingsMap().get("googleMap.defaultLat") );
	}	
	
	public String getGoogleMapDefaultLng() {
		return String.valueOf( Constants.getSettingsMap().get("googleMap.defaultLng") );
	}		
	
	public String getGoogleMapLanguage() {
		return String.valueOf( Constants.getSettingsMap().get("googleMap.language") );
	}	
	
	public String getGoogleMapClientLocationEnable() {
		return String.valueOf( Constants.getSettingsMap().get("googleMap.clientLocationEnable") );
	}
	
	public String getTwitterEnable() {
		return String.valueOf( Constants.getSettingsMap().get("twitter.enable") );
	}
	
	public boolean isSuperRole() {
		Subject subject = SecurityUtils.getSubject();
		if (subject.hasRole(Constants.SUPER_ROLE_ADMIN) || subject.hasRole(Constants.SUPER_ROLE_ALL)) {
			return true;
		}
		return false;
	}
	
	public Subject getSubject() {
		return SecurityUtils.getSubject();
	}
	
	public String getAccountId() {		
		Subject subject = SecurityUtils.getSubject();		
		return this.defaultString((String)subject.getPrincipal());		
	}	
	
	public String generateOid() {
		return SimpleUtils.getUUIDStr();
	}	
	
	public String getBasePath(HttpServletRequest request) {
		return request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
	}
	
	protected String defaultString(String str) {
		return StringUtils.defaultString(str);
	}
	
	protected void setPageMessage(HttpServletRequest request, String pageMessage) {
		if (null!=pageMessage && pageMessage.length()>=500) {
			pageMessage=pageMessage.substring(0, 500);
		}
		request.setAttribute(Constants.PAGE_MESSAGE, pageMessage);
	}	
	
	protected void setPageErrorContact(HttpServletRequest request) {
		request.setAttribute("errorContact", this.getErrorContact());
	}
	
	protected String getNowDate() {
		return SimpleUtils.getStrYMD("/");
	}
	
	protected String getNowDate2() {
		return SimpleUtils.getStrYMD("-");
	}		
	
	protected <T> DefaultControllerJsonResultObj<T> getDefaultJsonResult(String progId) {
		DefaultControllerJsonResultObj<T> result = DefaultControllerJsonResultObj.build();
		if (!StringUtils.isBlank(this.getAccountId())) {
			result.setLogin( YesNo.YES );
			Subject subject = this.getSubject();
			if (subject.hasRole(Constants.SUPER_ROLE_ALL) || subject.hasRole(Constants.SUPER_ROLE_ADMIN)) {
				result.setIsAuthorize( YesNo.YES );
			}
			if (subject.isPermitted(progId)) {
				result.setIsAuthorize( YesNo.YES );
			}
			if (!YesNo.YES.equals(result.getIsAuthorize())) {
				result.setMessage( "no authorize!" );
			}
		} else {
			result.setMessage( "Please login!" );
		}
		return result;
	}
	
	protected <T> QueryControllerJsonResultObj<T> getQueryJsonResult(String progId) {
		QueryControllerJsonResultObj<T> result = QueryControllerJsonResultObj.build();
		if (!StringUtils.isBlank(this.getAccountId())) {
			result.setLogin( YesNo.YES );
			Subject subject = this.getSubject();
			if (subject.hasRole(Constants.SUPER_ROLE_ALL) || subject.hasRole(Constants.SUPER_ROLE_ADMIN)) {
				result.setIsAuthorize( YesNo.YES );
			}
			if (subject.isPermitted(progId)) {
				result.setIsAuthorize( YesNo.YES );
			}
			if (!YesNo.YES.equals(result.getIsAuthorize())) {
				result.setMessage( "no authorize!" );
			}
		} else {
			result.setMessage( "Please login!" );
		}
		return result;
	}	
	
	protected PageOf getPageOf() {
		PageOf pageOf = new PageOf();
		return pageOf;
	}
	
	protected SearchValue getSearchValue() {
		SearchValue searchValue = new SearchValue();
		return searchValue;
	}
	
}
