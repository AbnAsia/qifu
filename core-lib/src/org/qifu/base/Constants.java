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
package org.qifu.base;

import org.qifu.base.model.YesNo;

public class Constants {
	/**
	 * 不要更改這個設定
	 */
	public static final String BASE_ENCODING = "utf-8";
	
	public static final String HTML_SELECT_NO_SELECT_ID="all";
	public static final String HTML_SELECT_NO_SELECT_NAME=" - please select - ";		
	
	/**
	 * 保留查詢參數名稱 for PageOf , BaseDAO
	 */
	public static final String _RESERVED_PARAM_NAME_QUERY_SORT_TYPE = "sortType";
	
	/**
	 * 保留查詢參數名稱 for PageOf , BaseDAO
	 */
	public static final String _RESERVED_PARAM_NAME_QUERY_ORDER_BY = "orderBy";	
	
	public static final String QUERY_TYPE_OF_SELECT="select"; // BaseService 查詢 grid 要用
	public static final String QUERY_TYPE_OF_COUNT="count"; // BaseService 查詢 grid 要用	
	
	public static final String SUPER_ROLE_ALL = "*";
	public static final String SUPER_ROLE_ADMIN = "admin";
	public static final String SUPER_PERMISSION = "*";
	public static final String SYSTEM_BACKGROUND_USER = "system"; // 背景程式要用 , 配 SubjectBuilderForBackground.java 與 shiro.ini
	public static final String SYSTEM_BACKGROUND_PASSWORD = "password99"; // 背景程式要用 , 配 SubjectBuilderForBackground.java 與 shiro.ini
	
	public static final String SESS_ACCOUNT="SESSION_PINE_ACCOUNT"; // 登入 account id 放到 session 變數名
		
	/**
	 * GreenStepBaseFormAuthenticationFilter 要用的
	 */
	public static final String NO_LOGIN_JSON_DATA = "{ \"success\":\"" + YesNo.NO + "\",\"message\":\"Please login!\",\"login\":\"" + YesNo.NO + "\",\"isAuthorize\":\"" + YesNo.NO + "\" }";
	/**
	 * GreenStepBaseFormAuthenticationFilter 要用的
	 */	
	public static final String NO_AUTHZ_JSON_DATA = "{ \"success\":\"" + YesNo.NO + "\",\"message\":\"no authorize!\",\"login\":\"" + YesNo.YES + "\",\"isAuthorize\":\"" + YesNo.NO + "\" }";
	
	public static final String PAGE_MESSAGE="pageMessage";
}
