package com.huhuo.carservicecore.csm.consumer;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.huhuo.carservicecore.constant.Dictionary.DictGroup;
import com.huhuo.carservicecore.db.GenericBaseExtenseDao;
import com.huhuo.carservicecore.sys.dictionary.IDaoDictionary;
import com.huhuo.carservicecore.sys.dictionary.ModelDictionary;
import com.huhuo.integration.db.mysql.Condition;

@Repository("carservicecoreDaoConsumer")
public class DaoConsumer extends GenericBaseExtenseDao<ModelConsumer> implements IDaoConsumer {

	@Resource(name = "carservicecoreDaoDictionary")
	private IDaoDictionary<ModelDictionary> iDaoDictionary;
	
	@Override
	public String getTableName() {
		return "csm_consumer";
	}

	@Override
	public Class<ModelConsumer> getModelClazz() {
		return ModelConsumer.class;
	}

	@Override
	public List<ModelConsumer> findByCondition(
			Condition<ModelConsumer> condition) {
		// TODO Auto-generated method stub
		List<ModelConsumer> list = super.findByCondition(condition);
		for(ModelConsumer consumer : list) {
			ModelDictionary genderDic = iDaoDictionary.getBy(DictGroup.GENERAL_GENDER, consumer.getGender());
			consumer.setGenderDic(genderDic);
		}
		return list;
	}
	

}
