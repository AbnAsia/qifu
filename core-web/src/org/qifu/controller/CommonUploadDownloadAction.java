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
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.qifu.base.Constants;
import org.qifu.base.SysMessageUtil;
import org.qifu.base.SysMsgConstants;
import org.qifu.base.controller.BaseController;
import org.qifu.base.exception.AuthorityException;
import org.qifu.base.exception.ControllerException;
import org.qifu.base.exception.ServiceException;
import org.qifu.base.model.ControllerMethodAuthority;
import org.qifu.base.model.DefaultControllerJsonResultObj;
import org.qifu.base.model.DefaultResult;
import org.qifu.base.model.YesNo;
import org.qifu.model.UploadTypes;
import org.qifu.po.TbSysUpload;
import org.qifu.service.ISysUploadService;
import org.qifu.util.UploadSupportUtils;
import org.qifu.vo.SysUploadVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@Controller
public class CommonUploadDownloadAction extends BaseController {
	private static final long UPLOAD_MAX_SIZE = UploadSupportUtils.UPLOAD_MAX_SIZE;
	private ISysUploadService<SysUploadVO, TbSysUpload, String> sysUploadService;
	
	public ISysUploadService<SysUploadVO, TbSysUpload, String> getSysUploadService() {
		return sysUploadService;
	}

	@Autowired
	@Resource(name="core.service.SysUploadService")
	@Required		
	public void setSysUploadService(ISysUploadService<SysUploadVO, TbSysUpload, String> sysUploadService) {
		this.sysUploadService = sysUploadService;
	}

	@ControllerMethodAuthority(check = true, programId = "CORE_PROGCOMM0003Q")
	@RequestMapping(value = "/core.commonDownloadFileJson.do")
	public void downloadFile(HttpServletResponse response, @RequestParam("oid") String oid) throws UnsupportedEncodingException, IOException {
		TbSysUpload uploadData = new TbSysUpload();
		uploadData.setOid(oid);
		try {
			DefaultResult<TbSysUpload> result = sysUploadService.findEntityByOid(uploadData);
			if (result.getValue() != null) {
				uploadData = result.getValue();
			}
		} catch (AuthorityException | ServiceException | ControllerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (uploadData.getContent() == null) {
            OutputStream outputStream = response.getOutputStream();
            outputStream.write( SysMessageUtil.get(SysMsgConstants.DATA_NO_EXIST).getBytes(Constants.BASE_ENCODING) );
            outputStream.close();
            return;	
		}
		response.setContentType( "application/octet-stream" );
		response.setHeader("Content-Disposition", String.format("inline; filename=\"" + uploadData.getFileName() +"\""));
		response.setContentLength( uploadData.getContent().length );
		FileCopyUtils.copy(uploadData.getContent(), response.getOutputStream());
	}
	
	@ControllerMethodAuthority(check = true, programId = "CORE_PROGCOMM0003Q")
	@RequestMapping(value = "/core.commonUploadFileJson.do", method = { RequestMethod.POST }, headers = "content-type=multipart/*" )		
	public @ResponseBody DefaultControllerJsonResultObj<String> uploadFile(@RequestParam("commonUploadFile") MultipartFile file, @RequestParam("commonUploadFileType") String type, @RequestParam("commonUploadFileIsFileMode") String isFile) {
		DefaultControllerJsonResultObj<String> result = this.getDefaultJsonResult("CORE_PROGCOMM0003Q");
		if (!this.isAuthorizeAndLoginFromControllerJsonResult(result)) {
			return result;
		}	
		if (null == file || file.getSize() < 1) {
			result.setMessage( SysMessageUtil.get(SysMsgConstants.UPLOAD_FILE_NO_SELECT) );
			return result;			
		}
		if (file.getSize() > UPLOAD_MAX_SIZE) {
			result.setMessage( "File max size only " + UPLOAD_MAX_SIZE + " bytes!"  );
			return result;
		}
		if (!UploadTypes.check(type)) {
			result.setMessage( SysMessageUtil.get(SysMsgConstants.UPLOAD_FILE_TYPE_ERROR) );
			return result;
		}
		try {
			String uploadOid = UploadSupportUtils.create(Constants.getSystem(), type, ( YesNo.YES.equals(isFile) ? true : false ), file.getBytes(), file.getOriginalFilename());
			if (!StringUtils.isBlank(uploadOid)) {
				result.setSuccess( YesNo.YES );
				result.setValue(uploadOid);
				result.setMessage( SysMessageUtil.get(SysMsgConstants.UPDATE_SUCCESS) );
			} else {
				result.setMessage( SysMessageUtil.get(SysMsgConstants.UPDATE_FAIL) );
			}
		} catch (AuthorityException | ServiceException | ControllerException e) {
			result.setMessage( e.getMessage().toString() );			
		} catch (Exception e) {
			e.printStackTrace();
			result.setMessage( e.getMessage().toString() );
		}		
		return result;
	}

}
