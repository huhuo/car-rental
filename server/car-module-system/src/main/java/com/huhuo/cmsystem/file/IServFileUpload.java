package com.huhuo.cmsystem.file;

import org.springframework.web.multipart.MultipartFile;

import com.huhuo.carservicecore.sys.file.ModelFileUpload;
import com.huhuo.integration.base.IBaseExtenseServ;


public interface IServFileUpload extends IBaseExtenseServ<ModelFileUpload> {
	
	/**
	 * update if t.id != null, or add t
	 * @param t 
	 * @return t
	 */
	Boolean save(ModelFileUpload t);
	/**
	 * upload file to cache directory, and return file relative path
	 * @param cachedFile
	 * @return new file entity
	 */
	ModelFileUpload uploadCacheFile(MultipartFile cachedFile);
	/**
	 * upload file to persist directory <br> field name and md5 is required
	 * @return new file entity
	 */
	ModelFileUpload uploadFile(ModelFileUpload t);
	
}
