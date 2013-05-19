package com.huhuo.cmsystem.ms;

import com.huhuo.carservicecore.cust.ms.ModelSMS;
import com.huhuo.carservicecore.sys.user.ModelUser;
import com.huhuo.integration.base.IBaseExtenseServ;


public interface IServSMS extends IBaseExtenseServ<ModelSMS> {
	
	/**
	 * send SMS to phoneNum
	 * @param senderId sender's id relative to user defined in {@link ModelUser}
	 * @param recieverId reviever's id relative to user defined in {@link ModelUser}
	 * @param phoneNum destination phone number
	 * @param msg message content
	 * @return true if send successfully
	 */
	Boolean send(Long senderId, Long recieverId, Long phoneNum, String msg);
	/**
	 * @see {@link #send(Long, Long, Long, String)}
	 */
	Boolean send(ModelUser sender, ModelUser reciever, Long phoneNum, String msg);
	
}
