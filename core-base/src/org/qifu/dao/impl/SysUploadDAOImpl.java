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
package org.qifu.dao.impl;

import java.util.Date;

import org.qifu.base.dao.BaseDAO;
import org.qifu.base.model.YesNo;
import org.qifu.dao.ISysUploadDAO;
import org.qifu.model.UploadTypes;
import org.qifu.po.TbSysUpload;
import org.qifu.vo.SysUploadVO;
import org.springframework.stereotype.Repository;

@Repository("core.dao.SysUploadDAO")
public class SysUploadDAOImpl extends BaseDAO<TbSysUpload, String> implements ISysUploadDAO<TbSysUpload, String> {
	
	public SysUploadDAOImpl() {
		super();
	}

	@Override
	public int deleteTmpContent(String system) throws Exception {		
		return this.getCurrentSession()
				.createQuery("DELETE TbSysUpload WHERE system = :system AND type = :type AND isFile = :isFile ")
				.setString("system", system)
				.setString("type", UploadTypes.IS_TEMP)
				.setString("isFile", YesNo.NO)				
				.executeUpdate();
	}

	@Override
	public SysUploadVO findForNoByteContent(String oid) throws Exception {
		return (SysUploadVO)this.getCurrentSession()
				.createQuery("SELECT new org.qifu.vo.SysUploadVO(m.oid, m.system, m.subDir, m.type, m.fileName, m.showName, m.isFile) FROM TbSysUpload m WHERE m.oid = :oid")
				.setString("oid", oid)
				.uniqueResult();		
	}

	@Override
	public int updateTypeOnly(String oid, String type, String uuserid) throws Exception {		
		return this.getCurrentSession()
				.createQuery("UPDATE TbSysUpload SET type = :type, uuserid = :uuserid, udate = :udate WHERE oid = :oid")
				.setString("type", type)
				.setString("uuserid", uuserid)
				.setTimestamp("udate", new Date())
				.setString("oid", oid)
				.executeUpdate();
	}
	
}
