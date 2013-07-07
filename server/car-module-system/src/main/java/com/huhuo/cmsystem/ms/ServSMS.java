package com.huhuo.cmsystem.ms;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.apache.commons.httpclient.Header;
import org.springframework.stereotype.Service;

import com.huhuo.carservicecore.csm.consumer.IDaoConsumer;
import com.huhuo.carservicecore.csm.consumer.ModelConsumer;
import com.huhuo.carservicecore.cust.ms.IDaoSMS;
import com.huhuo.carservicecore.cust.ms.ModelSMS;
import com.huhuo.carservicecore.db.GenericBaseExtenseServ;
import com.huhuo.carservicecore.sys.user.IDaoUser;
import com.huhuo.carservicecore.sys.user.ModelUser;
import com.huhuo.componentweb.core.IServHcwHttpClient;
import com.huhuo.integration.algorithm.MD5Utils;
import com.huhuo.integration.base.IBaseExtenseDao;
import com.huhuo.integration.exception.ServException;
import com.huhuo.integration.util.TimeUtils;
import com.huhuo.integration.web.Message;

@Service("cmsystemServSMS")
public class ServSMS extends GenericBaseExtenseServ<ModelSMS> implements
		IServSMS {

	private static String sn = "SDK-WKS-010-00887";
	private static String password = "518250";
	private static String pwd = MD5Utils.encodeHex(sn + password);
	// webservice服务器地址
//	private String serviceURL = "http://sdk2.entinfo.cn/webservice.asmx";
	private String serviceURL = "http://sdk2.zucp.net:8060/webservice.asmx";
	@Resource(name = "carservicecoreDaoSMS")
	private IDaoSMS iDaoSMS;

	@Resource(name = "carservicecoreDaoUser")
	private IDaoUser iDaoUser;

	@Resource(name = "carservicecoreDaoConsumer")
	private IDaoConsumer iDaoConsumer;
	
	@Resource(name = "componentwebCoreHttpClientServ")
	private IServHcwHttpClient iServHcwHttpClient;

	@Override
	public IBaseExtenseDao<ModelSMS> getDao() {
		return iDaoSMS;
	}
	
	@Override
	public void inject(ModelSMS t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String send(Long senderId, Long recieverId, String msg, String rrid) {
		ModelUser user = iDaoUser.find(senderId);
		ModelConsumer consumer = iDaoConsumer.find(recieverId);
		if (senderId == null || recieverId == null) {
			throw new ServException("senderId is null or recieverId is null");
		}
		
		return sendSMS(user, consumer, msg, null, rrid);
	}

	@Override
	public String send(ModelUser sender, ModelConsumer consumer, String msg,
			String rrid) {
		return sendSMS(sender, consumer, msg, null, rrid);
	}

	@Override
	public String send(ModelUser sender, ModelConsumer consumer, String msg,
			Date date, String rrid) {
		return sendSMS(sender, consumer, msg, date, rrid);
	}

	private String sendSMS(ModelUser sender, ModelConsumer consumer,
			String msg, Date date, String rrid) {
		
		if (sender != null && consumer != null) {
			
			String stime = "";
			String mobile = consumer.getMobileNumber();
			if (date != null) {
				stime = TimeUtils.format(date);
			}
//			String regex = "<mtResult>(.*)</mtResult>";
			String regex2 = "<mdSmsSend_uResult>(.*)</mdSmsSend_uResult>";
//			String sendSoapAction = "http://tempuri.org/mt";
			String sendSoapAction2 = "http://tempuri.org/mdSmsSend_u";
			
			String result = "";
			try {
				String xml2 = createSendXML2(msg, rrid, "", mobile, stime);
				result = wirte(sendSoapAction2, xml2, regex2);
				// if result equals rrid send successfully,save log to database,otherwise send failure
				if (result.equals(rrid)) {
					// save to db
					saveDB(sender, consumer, msg);
				} else {
					logger.error("send failure result:" + result);
				}
				return result;
			} catch (Exception e) {
				logger.error("error occur while sending message", e);
				throw new ServException(e);
			}
		} else {
			throw new ServException("ModelUser is null or ModelConsumer is null");
		}
	}

	@Override
	public String balance() {
		try {
			String result = "";
			String xml = createBalanceXML();
			String regex = "<balanceResult>(.*)</balanceResult>";
			String balanceSoapAction = "http://tempuri.org/balance";
			result = wirte(balanceSoapAction, xml, regex);
			return result;
		} catch (Exception e) {
			logger.error("error occur while sending message", e);
			throw new ServException(e);
		}
	}

	@Override
	public String recharge(String cardno, String cardpwd) {
		String result = "";
		String xml = createRechargeXML(cardno, cardpwd);
		String regex = "<ChargUpResult>(.*)</ChargUpResult>";
		String recharegeSoapAction = "http://tempuri.org/ChargUp";
		try {
			result = wirte(recharegeSoapAction, xml, regex);
			return result;
		} catch (Exception e) {
			logger.error("error occur while sending message", e);
			throw new ServException(e);
		}

	}

	private void saveDB(ModelUser sender, ModelConsumer consumer, String msg) {
		ModelSMS t = new ModelSMS();
		t.setSenderId(sender.getId());
		t.setRecieverId(consumer.getId());
		t.setPhoneNum(consumer.getMobileNumber());
		t.setContent(msg);
		t.setCreateTime(new Date());
		t.setUpdateTime(null);
		t.setStatus(0);
		add(t);
	}

	private String wirte(String soapAction, String xml, String regex)
			throws MalformedURLException, IOException, ProtocolException {
		
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		bout.write(xml.getBytes("GBK"));
		byte[] b = bout.toByteArray();
		
		Message<String> stream = iServHcwHttpClient.postAsStream(
				serviceURL, 
				b,
				false, 
				"gb2312",
				new Header("Content-Length", String.valueOf(b.length)),
				new Header("Content-Type", "text/xml; charset=gb2312"),
				new Header("SOAPAction", soapAction));
		
		String data = stream.getData();
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(data);
		String result = "";
		while (matcher.find()) {
			result = matcher.group(1);
		}
		return result;
	}

	private String createRechargeXML(String cardno, String cardpwd) {
		String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
		xml += "<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">";
		xml += "<soap12:Body>";
		xml += "<ChargUp xmlns=\"http://tempuri.org/\">";
		xml += "<sn>" + sn + "</sn>";
		xml += "<pwd>" + password + "</pwd>";
		xml += "<cardno>" + cardno + "</cardno>";
		xml += "<cardpwd>" + cardpwd + "</cardpwd>";
		xml += "</ChargUp>";
		xml += "</soap12:Body>";
		xml += "</soap12:Envelope>";
		return xml;
	}

	private String createSendXML2(String msg, String rrid, String ext,
			String mobile, String stime) throws UnsupportedEncodingException {
		String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
		xml += "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">";
		xml += "<soap:Body>";
		xml += "<mdSmsSend_u xmlns=\"http://tempuri.org/\">";
		xml += "<sn>" + sn + "</sn>";
		xml += "<pwd>" + pwd.toUpperCase() + "</pwd>";
		xml += "<mobile>" + mobile + "</mobile>";
		xml += "<content>" + msg + "</content>";
		xml += "<ext>" + ext + "</ext>";
		xml += "<stime>" + stime + "</stime>";
		xml += "<rrid>" + rrid + "</rrid>";
		xml += "</mdSmsSend_u>";
		xml += "</soap:Body>";
		xml += "</soap:Envelope>";
		return xml;
	}
	@SuppressWarnings("unused")
	@Deprecated
	private String createSendXML(String msg, String rrid, String ext,
			String mobile, String stime) throws UnsupportedEncodingException {
//		String content = URLEncoder.encode(msg, "UTF-8");
		String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
		xml += "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">";
		xml += "<soap:Body>";
		xml += "<mt xmlns=\"http://tempuri.org/\">";
		xml += "<sn>" + sn + "</sn>";
		xml += "<pwd>" + pwd.toUpperCase() + "</pwd>";
		xml += "<mobile>" + mobile + "</mobile>";
		xml += "<content>" + msg + "</content>";
		xml += "<ext>" + ext + "</ext>";
		xml += "<stime>" + stime + "</stime>";
		xml += "<rrid>" + rrid + "</rrid>";
		xml += "</mt>";
		xml += "</soap:Body>";
		xml += "</soap:Envelope>";
		return xml;
	}

	private String createBalanceXML() {
		String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";
		xml += "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">";
		xml += "<soap:Body>";
		xml += "<balance xmlns=\"http://tempuri.org/\">";
		xml += "<sn>" + sn + "</sn>";
		xml += "<pwd>" + pwd.toUpperCase() + "</pwd>";
		xml += "</balance>";
		xml += "</soap:Body>";
		xml += "</soap:Envelope>";
		return xml;
	}

	@Override
	public List<String> send(ModelUser sender, List<ModelConsumer> consumers,
			String msg, String rrid) {
		List<String> rets = new ArrayList<String>();
		for(ModelConsumer consumer : consumers) {
			String ret = send(sender, consumer, msg, rrid);
			rets.add(ret);
		}
		return rets;
	}

	@Override
	public String send(ModelSMS msg, String rrid) {
		return send(msg.getSenderId(), msg.getRecieverId(), msg.getContent(), rrid);
	}

}
