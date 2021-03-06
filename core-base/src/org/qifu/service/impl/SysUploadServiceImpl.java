/* 
 * Copyright 2012-2016 bambooCORE, greenstep of copyright Chen Xin Nien
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
package org.qifu.service.impl;

import java.io.IOException;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.qifu.base.SysMessageUtil;
import org.qifu.base.SysMsgConstants;
import org.qifu.base.dao.IBaseDAO;
import org.qifu.base.exception.ServiceException;
import org.qifu.base.model.DefaultResult;
import org.qifu.base.model.SystemMessage;
import org.qifu.base.service.BaseService;
import org.qifu.dao.ISysUploadDAO;
import org.qifu.po.TbSysUpload;
import org.qifu.service.ISysUploadService;
import org.qifu.vo.SysUploadVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("core.service.SysUploadService")
@Transactional(propagation=Propagation.REQUIRED, readOnly=true)
public class SysUploadServiceImpl extends BaseService<SysUploadVO, TbSysUpload, String> implements ISysUploadService<SysUploadVO, TbSysUpload, String> {
	protected Logger logger=Logger.getLogger(SysUploadServiceImpl.class);
	private ISysUploadDAO<TbSysUpload, String> sysUploadDAO;
	
	public SysUploadServiceImpl() {
		super();
	}

	public ISysUploadDAO<TbSysUpload, String> getSysUploadDAO() {
		return sysUploadDAO;
	}

	@Autowired
	@Resource(name="core.dao.SysUploadDAO")
	@Required		
	public void setSysUploadDAO(ISysUploadDAO<TbSysUpload, String> sysUploadDAO) {
		this.sysUploadDAO = sysUploadDAO;
	}

	@Override
	protected IBaseDAO<TbSysUpload, String> getBaseDataAccessObject() {
		return sysUploadDAO;
	}

	@Override
	public String getMapperIdPo2Vo() {		
		return MAPPER_ID_PO2VO;
	}

	@Override
	public String getMapperIdVo2Po() {
		return MAPPER_ID_VO2PO;
	}

	@Transactional(
			propagation=Propagation.REQUIRED, 
			readOnly=false,
			rollbackFor={RuntimeException.class, IOException.class, Exception.class} )	
	@Override
	public int deleteTmpContent(String system) throws ServiceException, Exception {
		if (StringUtils.isBlank(system)) {
			throw new ServiceException(SysMessageUtil.get(SysMsgConstants.PARAMS_BLANK));
		}
		return this.sysUploadDAO.deleteTmpContent(system);
	}
	
	@Override
	public DefaultResult<SysUploadVO> findForNoByteContent(String oid) throws ServiceException, Exception {
		if (StringUtils.isBlank(oid)) {
			throw new ServiceException(SysMessageUtil.get(SysMsgConstants.PARAMS_BLANK)); 
		}
		SysUploadVO upload = this.sysUploadDAO.findForNoByteContent(oid);
		DefaultResult<SysUploadVO> result = new DefaultResult<SysUploadVO>();
		if (upload!=null && !StringUtils.isBlank(upload.getOid())) {
			result.setValue(upload);
		} else {
			result.setSystemMessage( new SystemMessage(SysMessageUtil.get(SysMsgConstants.DATA_NO_EXIST)) );
		}
		return result;
	}

	@Transactional(
			propagation=Propagation.REQUIRED, 
			readOnly=false,
			rollbackFor={RuntimeException.class, IOException.class, Exception.class} )		
	@Override
	public DefaultResult<Boolean> updateTypeOnly(String oid, String type) throws ServiceException, Exception {
		if (StringUtils.isBlank(oid) || StringUtils.isBlank(type)) {
			throw new ServiceException(SysMessageUtil.get(SysMsgConstants.PARAMS_BLANK)); 
		}		
		DefaultResult<Boolean> result = new DefaultResult<Boolean>();
		result.setValue(Boolean.FALSE);
		if (this.sysUploadDAO.updateTypeOnly(oid, type, this.getAccountId())==1) {
			result.setValue(Boolean.TRUE);
			result.setSystemMessage( new SystemMessage(SysMessageUtil.get(SysMsgConstants.UPDATE_SUCCESS)) );
		} else {
			throw new ServiceException(SysMessageUtil.get(SysMsgConstants.DELETE_FAIL));
		}
		return result;
	}

}
