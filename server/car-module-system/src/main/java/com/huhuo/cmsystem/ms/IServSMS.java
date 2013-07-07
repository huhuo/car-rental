package com.huhuo.cmsystem.ms;

import java.util.Date;
import java.util.List;

import com.huhuo.carservicecore.csm.consumer.ModelConsumer;
import com.huhuo.carservicecore.cust.ms.ModelSMS;
import com.huhuo.carservicecore.sys.user.ModelUser;
import com.huhuo.integration.base.IBaseExtenseServ;


public interface IServSMS extends IBaseExtenseServ<ModelSMS> {
	
	/**
	 * unrealized<br>
	 * send SMS to phoneNum
	 * @param senderId sender's id relative to user defined in {@link ModelUser}
	 * @param recieverId reviever's id relative to user defined in {@link ModelUser}
	 * @param phoneNum destination phone number
	 * @param msg message content
	 * @param rrid the only sign
	 * @return rrid if send successfully,otherwise send failure
	 */
	String send(Long senderId, Long recieverId, String msg, String rrid);
	/**
	 * immediately send sms
	 * 
	 * @param sender
	 * @param consumer
	 * @param msg message content
	 * @param rrid the only sign
	 * @return rrid if send successfully,otherwise send failure
	 */
	String send(ModelUser sender, ModelConsumer consumer, String msg,
			String rrid);
	/**
	 * send an sms message
	 * @param msg
	 * @param rrid
	 * @return
	 */
	String send(ModelSMS msg, String rrid);
	/**
	 * mass SMS send
	 * @param sender
	 * @param consumers
	 * @param msg
	 * @param rrid
	 * @return
	 */
	List<String> send(ModelUser sender, List<ModelConsumer> consumers, String msg,
			String rrid);
	/**
	 * regularly send SMS
	 * 
	 * @param sender
	 * @param consumer
	 * @param msg message content
	 * @param stime regularly send SMS,format yyyy-MM-dd HH:mm:ss
	 * @param rrid the only sign
	 * @return rrid if send successfully,otherwise send failure
	 */
	String send(ModelUser sender, ModelConsumer consumer, String msg,
			Date stime, String rrid);
	/**
	 * 
	 * @return remaining SMS count
	 */
	String balance();
	/**
	 * 
	 * @param cardno recharege card number
	 * @param cardpwd recharege card password
	 * @return 0 if recharge successfully,otherwise failure
	 */
	String recharge(String cardno, String cardpwd);
	
}
