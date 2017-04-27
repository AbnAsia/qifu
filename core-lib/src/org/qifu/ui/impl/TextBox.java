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
package org.qifu.ui.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.PageContext;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.ognl.Ognl;
import org.apache.ibatis.ognl.OgnlException;
import org.qifu.ui.ComponentResourceUtils;
import org.qifu.ui.UIComponent;

public class TextBox implements UIComponent {
	private static final String _HTML_RES = "META-INF/resource/textbox/ui.textbox.htm.ftl";
	private PageContext pageContext = null;	
	private String id = "";
	private String name = "";
	private String value  = "";
	private String readonly = "";
	private String placeholder = "";
	private String label = "";
	private String cssClass = "";
	private String requiredFlag = "";
	private StringBuilder htmlOut=new StringBuilder();	
	
	private Map<String, Object> getParameters(String type) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", this.id);
		paramMap.put("name", this.name);
		paramMap.put("readonly", this.readonly);
		paramMap.put("placeholder", this.placeholder);
		paramMap.put("label", this.label);
		paramMap.put("value", this.value);
		paramMap.put("cssClass", this.cssClass);
		paramMap.put("requiredFlag", this.requiredFlag);
		if (!StringUtils.isBlank(this.value) && StringUtils.defaultString(this.value).indexOf(".") == -1) {
			HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
			Object val = ( request.getParameter(this.value) != null ? request.getParameter(this.value) : request.getAttribute(this.value) ); // 以 getParameter 為主
			if (val != null) {
				paramMap.put("value", val);
			}
		}
		if ( StringUtils.defaultString(this.value).indexOf(".") >= 1 ) { // 如 policy.no , policy.amount
			HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
			String kName = this.value.split("[.]")[0];
			Object valObj = ( request.getParameter(kName) != null ? request.getParameter(kName) : request.getAttribute(kName) );
			Object val = null;
			if (valObj != null) {
				try {
					val = Ognl.getValue(this.value, valObj);
				} catch (OgnlException e) {	
					//e.printStackTrace();
				}				
			}
			if (val != null) {
				this.putValue(paramMap, val);
			}			
		}
		return paramMap;
	}
	
	private void putValue(Map<String, Object> params, Object val) {
		if (val instanceof java.lang.String) {
			params.put("value", StringEscapeUtils.escapeHtml4( (String)val ) );
			return;
		}		
		if (val instanceof java.lang.Integer) {
			params.put("value", String.valueOf( (Integer)val ) );
			return;			
		}
		if (val instanceof java.lang.Long) {
			params.put("value", String.valueOf( (Long)val ) );
			return;						
		}
		if (val instanceof java.math.BigDecimal) {
			params.put("value", ((java.math.BigDecimal)val).toString() );
			return;					
		}
		if (val instanceof java.math.BigInteger) {
			params.put("value", ((java.math.BigInteger)val).toString() );
			return;							
		}
		if (val instanceof java.lang.Float) {
			params.put("value", String.valueOf( (Float)val ) );
			return;						
		}
		if (val instanceof java.lang.Double) {
			params.put("value", String.valueOf( (Double)val ) );
			return;						
		}			
	}	
	
	private void generateHtml() {
		try {
			htmlOut.append( ComponentResourceUtils.generatorResource(TextBox.class, IS_HTML, _HTML_RES, this.getParameters(IS_HTML)) );
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}	
	
	@Override
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String getId() {
		return this.id;
	}

	@Override
	public void setName(String name) {
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public String getScript() throws Exception {
		return "";
	}

	@Override
	public String getHtml() throws Exception {
		this.generateHtml();
		return this.htmlOut.toString();
	}

	@Override
	public void setPageContext(PageContext pageContext) {
		this.pageContext = pageContext;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getReadonly() {
		return readonly;
	}

	public void setReadonly(String readonly) {
		this.readonly = readonly;
	}

	public String getPlaceholder() {
		return placeholder;
	}

	public void setPlaceholder(String placeholder) {
		this.placeholder = placeholder;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getCssClass() {
		return cssClass;
	}

	public void setCssClass(String cssClass) {
		this.cssClass = cssClass;
	}

	public String getRequiredFlag() {
		return requiredFlag;
	}

	public void setRequiredFlag(String requiredFlag) {
		this.requiredFlag = requiredFlag;
	}

}
