package com.huhuo.carservicecore.sys.dictionary;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.huhuo.carservicecore.constant.Dictionary.ModelDict;
import com.huhuo.carservicecore.constant.Dictionary.ModelDictGroup;
import com.huhuo.carservicecore.db.GenericBaseExtenseDao;

@Repository("carservicecoreDaoDictionary")
public class DaoDictionary extends GenericBaseExtenseDao<ModelDictionary> implements IDaoDictionary {

	@Override
	public String getTableName() {
		return "sys_dictionary";
	}

	@Override
	public Class<ModelDictionary> getModelClazz() {
		return ModelDictionary.class;
	}

	@Override
	public List<ModelDictionary> getGroupsBy(ModelDictGroup dictGroup) {
		String sql = String.format("SELECT * FROM %s WHERE groupName=? ORDER BY orderNo ASC, id ASC", getTableName());
		List<ModelDictionary> list = findList(sql, dictGroup.getGroupName());
		return list;
	}
	
	@Override
	public ModelDictionary getBy(ModelDictGroup dictGroup, Integer dictKey) {
		List<ModelDictionary> list = getGroupsBy(dictGroup);
		if(list != null) {
			for(ModelDictionary dict : list) {
				if(dict!=null && dict.getDictKey()==dictKey) {
					return dict;
				}
			}
		}
		return null;
	}
	
	@Override
	public ModelDictionary getBy(ModelDict dict) {
		if(dict !=null) {
			ModelDictionary ret = getBy(dict.getGroup(), dict.getDicKey());
			return ret;
		}
		return null;
	}
	
	

}
