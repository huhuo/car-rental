package com.huhuo.cmsystem.ms;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.huhuo.carservicecore.csm.consumer.IDaoConsumer;
import com.huhuo.carservicecore.csm.consumer.ModelConsumer;
import com.huhuo.carservicecore.cust.ms.IDaoSMS;
import com.huhuo.carservicecore.cust.ms.ModelSMS;
import com.huhuo.carservicecore.db.GenericBaseExtenseServ;
import com.huhuo.carservicecore.sys.user.IDaoUser;
import com.huhuo.carservicecore.sys.user.ModelUser;
import com.huhuo.componentweb.core.IServHcwHttpClient;
import com.huhuo.integration.base.IBaseExtenseDao;
import com.huhuo.integration.config.GlobalConstant.Encoding;
import com.huhuo.integration.exception.ServException;
import com.huhuo.integration.web.Message;


/**
 * test class
 * @author wuyuxuan
 *
 */
@Service("cmsystemServSMS2")
public class ServSMS2  extends GenericBaseExtenseServ<ModelSMS> implements
IServSMS  {
	
	@Resource(name = "carservicecoreDaoSMS")
	private IDaoSMS iDaoSMS;

	@Resource(name = "carservicecoreDaoUser")
	private IDaoUser iDaoUser;

	@Resource(name = "carservicecoreDaoConsumer")
	private IDaoConsumer iDaoConsumer;
	
	@Resource(name = "componentwebCoreHttpClientServ")
	private IServHcwHttpClient iServHcwHttpClient;
	
	@Override
	public String send(Long senderId, Long recieverId, String msg, String rrid) {
		ModelUser user = iDaoUser.find(senderId);
		ModelConsumer consumer = iDaoConsumer.find(recieverId);
		if (senderId == null || recieverId == null) {
			throw new ServException("senderId is null or recieverId is null");
		}
		
		String zh = encodingStr("欲罢不能");
		String pwd = encodingStr("qw123456");
		String hm = encodingStr(consumer.getMobileNumber());
		String dxlbid = encodingStr("49");
		msg = encodingStr(msg);
		
		String url = "http://www.6610086.cn/smsComputer/smsComputersend.asp?zh="+ zh +"&mm="+ pwd +"&hm="+ hm +"&nr="+ msg +"&dxlbid="+ dxlbid +"";
		
		Message<String> message = iServHcwHttpClient.get(url);
		logger.debug(message.toString());
		String data = message.getData();
		if (data.equals("0")) {
			//success send sms,save log to db
			
		}
		return null;
	}
	
	String encodingStr(String str) {
		String result = "";
		if (str != null) {
			try {
				result = URLEncoder.encode(str, "GB2312");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}

	@Override
	public String send(ModelUser sender, ModelConsumer consumer, String msg,
			String rrid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String send(ModelUser sender, ModelConsumer consumer, String msg,
			Date stime, String rrid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String balance() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String recharge(String cardno, String cardpwd) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IBaseExtenseDao<ModelSMS> getDao() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void inject(ModelSMS t) {
		// TODO Auto-generated method stub
		
	}

}
