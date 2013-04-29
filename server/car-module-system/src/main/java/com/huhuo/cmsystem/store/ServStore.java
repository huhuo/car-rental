package com.huhuo.cmsystem.store;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.huhuo.carservicecore.cust.store.IDaoStore;
import com.huhuo.carservicecore.cust.store.ModelStore;
import com.huhuo.carservicecore.db.GenericBaseExtenseServ;
import com.huhuo.integration.base.IBaseExtenseDao;

@Service("cmcarServStore")
public class ServStore extends GenericBaseExtenseServ<ModelStore> implements IServStore {

	@Resource(name = "carservicecoreDaoStore")
	private IDaoStore idaoStore;
	
	
//	@Resource(name = "cmsystemServDictionary")
//	private IServDictionary iServDictionary;

	@Override
	public IBaseExtenseDao<ModelStore> getDao() {
		return idaoStore;
	}

	@Override
	public Class<ModelStore> getModelClazz() {
		return ModelStore.class;
	}
	
	public List<Map<String,Object>> multiQuery() {
		String sql = "SELECT cs.*,TABLE2.rentNum AS rentNum ,(TABLE1.tatolNum-TABLE2.rentNum) AS freeNum,su.username ";
		sql += "FROM (SELECT cc.* ,COUNT(cc.id) AS tatolNum FROM cust_car cc LEFT JOIN cust_store cs " +
				"ON cc.storeId=cs.id GROUP BY cc.storeId) TABLE1,";
		sql += "(SELECT cc.* ,COUNT(cc.id) AS rentNum FROM cust_car cc LEFT JOIN cust_store cs ON cc.storeId=cc.id " +
				"WHERE cc.status=2 GROUP BY cc.storeId) TABLE2,cust_store cs,sys_user su WHERE su.id=cs.managerId group by su.storeId";
		List<Map<String,Object>> list = idaoStore.queryForMapList(sql);
		return list;
	}
	
	//@Override
	/*public List<ModelStore> findByCondition(
			Condition<ModelStore> condition, boolean injected) {
		List<ModelStore> list = findByCondition(condition);
		if(list!=null && injected) {
			for(ModelStore t : list) {
				ModelDictionary categoryDict = iServDictionary.getBy(
						DictGroup.CUST_CAR_TYPE_CATEGORY, t.getName());
				t.setCategoryDict(categoryDict);
				ModelChargeStandard chargeStandard = iServChargeStandard.find(t.getChargeStandardId());
				t.setChargeStandard(chargeStandard);
			}
		}
		return list;
	}*/
	
	@Override
	public Integer add(ModelStore t) {
		// validation
		if(t == null) {
			return null;
		}
		
		// update t
		t.setCreateTime(new Date());
		t.setUpdateTime(new Date());
		return super.add(t);
	}
	
	/*@Override
	public Integer update(ModelStore t) {
		try {
			// validation
			if(t == null) {
				return null;
			}
			// persist object in security mode
			ModelStore storeDB = find(t.getId());
			BeanUtils.copyProperties(storeDB, t, false);
			// update inner object chargeStandard
			ModelChargeStandard chargeStandard = t.getChargeStandard();
			if (chargeStandard != null) {
				iServChargeStandard.update(chargeStandard);
			}
			return super.update(storeDB);
		} catch (Exception e) {
			throw new ServException(e);
		}
	}
*/
}
