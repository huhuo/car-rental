package com.huhuo.carservicecore.sys.dictionary;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.huhuo.carservicecore.constant.Dictionary.Dict;
import com.huhuo.carservicecore.constant.Dictionary.DictGroup;
import com.huhuo.carservicecore.db.GenericBaseExtenseDao;

@Repository("carservicecoreDaoDictionary")
public class DaoDictionary extends GenericBaseExtenseDao<ModelDictionary> implements IDaoDictionary<ModelDictionary> {

	@Override
	public String getTableName() {
		return "sys_dictionary";
	}

	@Override
	public Class<ModelDictionary> getModelClazz() {
		return ModelDictionary.class;
	}

	@Override
	public List<ModelDictionary> getGroupsBy(DictGroup dictGroup) {
		String sql = String.format("SELECT * FROM %s WHERE groupName=?", getTableName());
		List<ModelDictionary> list = findList(sql, dictGroup.getGroupName());
		return list;
	}
	
	@Override
	public ModelDictionary getBy(DictGroup dictGroup, Integer dictKey) {
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
	public ModelDictionary getBy(Dict dict) {
		if(dict !=null) {
			ModelDictionary ret = getBy(dict.getGroup(), dict.getDicKey());
			return ret;
		}
		return null;
	}
	
	

}
