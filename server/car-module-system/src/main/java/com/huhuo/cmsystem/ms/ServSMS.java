package com.huhuo.cmsystem.ms;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.huhuo.carservicecore.csm.consumer.IDaoConsumer;
import com.huhuo.carservicecore.csm.consumer.ModelConsumer;
import com.huhuo.carservicecore.cust.ms.IDaoSMS;
import com.huhuo.carservicecore.cust.ms.ModelSMS;
import com.huhuo.carservicecore.db.GenericBaseExtenseServ;
import com.huhuo.carservicecore.sys.user.IDaoUser;
import com.huhuo.carservicecore.sys.user.ModelUser;
import com.huhuo.integration.algorithm.MD5Utils;
import com.huhuo.integration.base.IBaseExtenseDao;
import com.huhuo.integration.util.TimeUtils;

@Service("cmsystemServSMS")
public class ServSMS extends GenericBaseExtenseServ<ModelSMS> implements IServSMS {

	private static String sn = "SDK-WKS-010-00887";
	private static String password = "518250";
	private static String pwd = MD5Utils.encodeHex(sn + password);
	// webservice服务器地址
	private String serviceURL = "http://sdk2.entinfo.cn/webservice.asmx";
	
	@Resource(name = "carservicecoreDaoSMS")
	private IDaoSMS iDaoSMS;
	
	@Resource(name = "carservicecoreDaoUser")
	private IDaoUser iDaoUser;
	
	@Resource(name = "carservicecoreDaoConsumer")
	private IDaoConsumer iDaoConsumer;
	
	@Override
	public IBaseExtenseDao<ModelSMS> getDao() {
		return iDaoSMS;
	}

	@Override
	public Class<ModelSMS> getModelClazz() {
		return ModelSMS.class;
	}

	@Override
	public String send(Long senderId, Long recieverId,String msg,
			String rrid) {
		// TODO Auto-generated method stub
		ModelUser user = iDaoUser.find(senderId);
		ModelConsumer consumer = iDaoConsumer.find(recieverId);
		
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
		String result = "";
		String stime = "";
		String mobile = consumer.getMobileNumber();
		if (date != null) {
			stime = TimeUtils.format(date);
		}
		String regex = "<mtResult>(.*)</mtResult>";
		String sendSoapAction = "http://tempuri.org/mt";
		
		String xml = creatorSendXML(msg, rrid, "", mobile, stime);
		try {
			result = wirte(sendSoapAction, xml,regex);
			//if reuslt equals rrid send successfully,save log to database,otherwise send failure
			if (result.equals(rrid)) {
				//save to db
				saveDB(sender, consumer, msg, mobile);
			} else {
				logger.error("send failure result:" + result);
			}
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	
	
	@Override
	public String balance() {
		String result = "";
		String xml = createBalanceXML();
		String regex = "<balanceResult>(.*)</balanceResult>";
		String balanceSoapAction = "http://tempuri.org/balance";
		try {
			result = wirte(balanceSoapAction,xml,regex);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	
	@Override
	public String recharge(String cardno, String cardpwd) {
		String result = "";
		String xml = createRechargeXML(cardno,cardpwd);
		String regex = "<ChargUpResult>(.*)</ChargUpResult>";
		String recharegeSoapAction = "http://tempuri.org/ChargUp";
		try {
			result = wirte(recharegeSoapAction,xml,regex);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		
	}
	
	private void saveDB(ModelUser sender, ModelConsumer consumer, String msg,
			String mobile) {
		ModelSMS t = new ModelSMS();
		t.setSenderId(sender.getId());
		t.setRecieverId(consumer.getId());
		t.setPhoneNum(Long.valueOf(mobile));
		t.setContent(msg);
		t.setCreateTime(new Date());
		t.setStatus(0);
		add(t);
	}

	private String wirte(String soapAction, String xml,String regex)
			throws MalformedURLException, IOException, ProtocolException {
		String result = "";
		URL url = new URL(serviceURL);

		URLConnection connection = url.openConnection();
		HttpURLConnection httpconn = (HttpURLConnection) connection;
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		bout.write(xml.getBytes());
		byte[] b = bout.toByteArray();
		httpconn.setRequestProperty("Content-Length", String.valueOf(b.length));
		httpconn.setRequestProperty("Content-Type","text/xml; charset=gb2312");
		httpconn.setRequestProperty("SOAPAction", soapAction);
		httpconn.setRequestMethod("POST");
		httpconn.setDoInput(true);
		httpconn.setDoOutput(true);

		OutputStream out = httpconn.getOutputStream();
		out.write(b);
		out.close();

		InputStreamReader isr = new InputStreamReader(httpconn.getInputStream());
		BufferedReader in = new BufferedReader(isr);
		String inputLine;
		while (null != (inputLine = in.readLine())) {
			Pattern pattern = Pattern.compile(regex);
			Matcher matcher = pattern.matcher(inputLine);
			while (matcher.find()) {
				result = matcher.group(1);
			}
		}
		isr.close();
		in.close();
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


	private String creatorSendXML(String msg, String rrid, String ext,
			String mobile, String stime) {
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
	
	public static String getMD5(String sourceStr) {
		String resultStr = "";
		try {
			byte[] temp = sourceStr.getBytes();
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(temp);
			// resultStr = new String(md5.digest());
			byte[] b = md5.digest();
			for (int i = 0; i < b.length; i++) {
				char[] digit = { '0', '1', '2', '3', '4', '5', '6', '7', '8','9', 'A', 'B', 'C', 'D', 'E', 'F' };
				char[] ob = new char[2];
				ob[0] = digit[(b[i] >>> 4) & 0X0F];
				ob[1] = digit[b[i] & 0X0F];
				resultStr += new String(ob);
			}
			return resultStr;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
