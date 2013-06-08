package com.huhuo.cmsystem.file;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.huhuo.carservicecore.db.GenericBaseExtenseServ;
import com.huhuo.carservicecore.sys.file.IDaoFileUpload;
import com.huhuo.carservicecore.sys.file.ModelFileUpload;
import com.huhuo.cmsystem.constant.Constant;
import com.huhuo.cmsystem.constant.Constant.Suffix;
import com.huhuo.integration.algorithm.MD5Utils;
import com.huhuo.integration.base.IBaseExtenseDao;
import com.huhuo.integration.exception.ServException;
import com.huhuo.integration.util.FileUtils;
import com.huhuo.integration.util.StringUtils;
import com.huhuo.integration.util.TimeUtils;

@Service("cmsystemServFileUpload")
public class ServFileUpload extends GenericBaseExtenseServ<ModelFileUpload> implements IServFileUpload {

	@Resource(name = "carservicecoreDaoFileUpload")
	private IDaoFileUpload<ModelFileUpload> iDaoFileUpload;
	
	private static ServletContextEvent sce;
	
	/** servlet context **/
	private static ServletContext ctx;
	/** file upload cache directory */
	private static String cachedPath = "cached";
	/** default upload file store directory */
	private String persistPath = Constant.FILE_UPLOAD_PERSIST_PATH;
	/** url mapping of file server */
	private String fileServerUrlPref = Constant.FILE_UPLOAD_FILE_SERVER_URL;
	/** default file separator */
	private String fs = "/";
	
	
	public static void setSce(ServletContextEvent sce) {
		ServFileUpload.sce = sce;
		ctx = ServFileUpload.sce.getServletContext();
	}

	@Override
	public IBaseExtenseDao<ModelFileUpload> getDao() {
		// TODO Auto-generated method stub
		return iDaoFileUpload;
	}
	
	@Override
	public void inject(ModelFileUpload t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public <V> ModelFileUpload find(V id) {
		// TODO Auto-generated method stub
		ModelFileUpload t = super.find(id);
		if(t != null) {
			t.setUrl(new StringBuilder().append(fileServerUrlPref)
					.append(fs).append(t.getPath()).append(fs).append(t.getMd5()).toString());
		}
		return t;
	}

	@Override
	public ModelFileUpload uploadCacheFile(MultipartFile cachedFile) {
		// TODO Auto-generated method stub
		ModelFileUpload ret = new ModelFileUpload();
		try {
			byte[] bytes = cachedFile.getBytes();
			// get md5 value by file's input stream
			String fileName = MD5Utils.encodeHex(bytes) + FileUtils.DEFFAULT_MARKER + 
					FileUtils.getSuffix(cachedFile.getOriginalFilename());
			ret.setName(cachedFile.getOriginalFilename());
			ret.setPath(cachedPath);
			ret.setMd5(fileName);
			ret.setUrl(new StringBuilder().append(fileServerUrlPref)
					.append(fs).append(ret.getPath()).append(fs).append(ret.getMd5()).toString());
			File file = new File(new StringBuilder().append(persistPath)
					.append(fs).append(cachedPath).append(fs).append(fileName).toString());
			if(!file.exists()) {
				logger.info("upload file --> {}", file);
				FileUtils.writeByteArrayToFile(file, bytes);
			}
		} catch (IOException e) {
			logger.warn(ExceptionUtils.getStackTrace(e));
			throw new ServException(e.getMessage() + " --> get upload file byte[] error", e);
		}
		return ret;
	}

	@Override
	public ModelFileUpload uploadFile(ModelFileUpload t) {
		try {
			File cachedFile = new File(new StringBuilder().append(persistPath)
					.append(fs).append(cachedPath).append(fs).append(t.getMd5()).toString());
			
			if(cachedFile.exists() && cachedFile.isFile()) {
				// copy file in cached directory to persist directory
				String firstLevel = TimeUtils.format(new Date(), false);
				File destDir = new File(new StringBuilder().append(persistPath)
						.append(fs).append(firstLevel).toString());
				File destFile = new File(destDir, t.getMd5());
				if(destFile.exists() && destFile.isFile()) {
					cachedFile.delete();
				} else {
					FileUtils.moveFileToDirectory(cachedFile, destDir, true);
				}
				// update DB
				t.setPath(firstLevel);
				String suffix = FileUtils.getSuffix(t.getMd5());
				Suffix type = Suffix.valueOf(suffix.toUpperCase());
				t.setType(type.getValue());
				t.setUrl(new StringBuilder().append(fileServerUrlPref)
						.append(fs).append(t.getPath()).append(fs).append(t.getMd5()).toString());
				save(t);
			}
		} catch (IOException e) {
			logger.warn(ExceptionUtils.getStackTrace(e));
			throw new ServException(e.getMessage() + " --> error while copy file");
		} catch (IllegalArgumentException e) {
			logger.warn(ExceptionUtils.getStackTrace(e));
			throw new ServException(e.getMessage() + " --> unsupported suffix type");
		}
		return t;
	}

	@Override
	public Boolean save(ModelFileUpload t) {
		if(t == null)
			throw new ServException("==> model t can't be null");
		
		ModelFileUpload tDB = find(t.getId());
		if(tDB != null) {
			if(!StringUtils.equals(t.getPath()+t.getMd5(), tDB.getPath()+t.getMd5())) {
				String relatePath = tDB.getPath() + fs + tDB.getMd5();
				File obsoleteFileInWebapp = new File(ctx.getRealPath(relatePath));
				boolean status = obsoleteFileInWebapp.delete();
				File bosoleteFileInPersist = new File(persistPath + fs + relatePath);
				boolean status2 = bosoleteFileInPersist.delete();
				logger.info("==> delete obsolete file in webapp {} --> {}", 
						status ? "success!" : "failure!", obsoleteFileInWebapp);
				logger.info("==> delete obsolete file in webapp {} --> {}", 
						status2 ? "success!" : "failure!", bosoleteFileInPersist);
			}
			update(t);
		} else {
			add(t);
		}
		return true;
	}

}
