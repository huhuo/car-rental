package com.huhuo.cmsystem.store;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.huhuo.carservicecore.cust.car.IDaoCar;
import com.huhuo.carservicecore.cust.car.ModelCar;
import com.huhuo.carservicecore.cust.store.IDaoStore;
import com.huhuo.carservicecore.cust.store.ModelStore;
import com.huhuo.carservicecore.db.GenericBaseExtenseServ;
import com.huhuo.integration.base.IBaseExtenseDao;
import com.huhuo.integration.db.mysql.Condition;
import com.huhuo.integration.db.mysql.Page;
import com.huhuo.integration.exception.ServException;
import com.huhuo.integration.util.BeanUtils;

@Service("cmsystemServStore")
public class ServStore extends GenericBaseExtenseServ<ModelStore> implements IServStore {

	@Resource(name = "carservicecoreDaoStore")
	private IDaoStore idaoStore;
	
	@Resource(name = "carservicecoreDaoCar")
	private IDaoCar iDaoCar;
	
	
//	@Resource(name = "cmsystemServDictionary")
//	private IServDictionary iServDictionary;

	@Override
	public IBaseExtenseDao<ModelStore> getDao() {
		return idaoStore;
	}
	
	@Override
	public void inject(ModelStore t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Map<String,Object>> multiQuery(ModelStore t, Page<ModelStore> page) {
		String sql="SELECT cs.*,su.username,IF(TABLE2.rentNum IS NULL,0,TABLE2.rentNum) AS rentNum ,IF((TABLE1.totalNum-TABLE2.rentNum) IS NULL,0,(TABLE1.totalNum-TABLE2.rentNum)) AS freeNum,TABLE1.totalNum FROM  cust_store cs LEFT JOIN sys_user su  ON (su.id=cs.managerId) LEFT JOIN (SELECT cc.storeId AS storeId,   COUNT(cc.id) AS totalNum FROM cust_car cc GROUP BY cc.storeId) AS TABLE1 ON cs.id =TABLE1.storeId  LEFT JOIN ( SELECT cc.storeId ,COUNT(cc.id) AS rentNum FROM cust_car cc WHERE cc.status=2 GROUP BY cc.storeId) AS TABLE2 ON cs.id=TABLE2.storeId WHERE cs.status=1 %s ORDER BY cs.createTime DESC, cs.updateTime DESC, cs.id DESC LIMIT ?, ?;";
		StringBuilder sb = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		if(t.getName() !=null ) {
			sb.append(" AND cs.name LIKE ? ");
			params.add("%" + t.getName() + "%");
		}
		if(t.getAddress() !=null ) {
			sb.append(" AND cs.address LIKE ? ");
			params.add("%" + t.getAddress() + "%");
		}
		sql = String.format(sql, sb.toString());
		// select * from status=2
		params.add(page.getStart());
		params.add(page.getLimit());
		List<Map<String, Object>> list = idaoStore.queryForMapList(sql, params.toArray());
		return list;
	}
	
	public Map<String,Object> detailQuery(ModelStore t) {
		String sql="SELECT cc.* ,cct.* FROM cust_car_type cct LEFT JOIN (cust_car cc LEFT JOIN cust_store cs ON cc.storeId=cs.id ) ON cct.id =cc.carTypeId WHERE cc.status=1 AND cs.id=?;";
		List<Object> params = new ArrayList<Object>();
		// select * from status=2
		params.add(t.getId());
		Map<String,Object> store = idaoStore.queryForMap(sql, params.toArray());
		return store;
	}
	@Override
	public Long countMultiQuery(ModelStore t, Page<ModelStore> page) {
		String sql="SELECT count(*) FROM  cust_store cs LEFT JOIN sys_user su  ON (su.id=cs.managerId) LEFT JOIN (SELECT cc.storeId AS storeId,   COUNT(cc.id) AS totallNum FROM cust_car cc GROUP BY cc.storeId) AS TABLE1 ON cs.id =TABLE1.storeId  LEFT JOIN ( SELECT cc.storeId ,COUNT(cc.id) AS rentNum FROM cust_car cc WHERE cc.status=2 GROUP BY cc.storeId) AS TABLE2 ON cs.id=TABLE2.storeId WHERE cs.status=1 %s ;";
		StringBuilder sb = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		if(t.getName() !=null ) {
			sb.append(" AND cs.name LIKE ? ");
			params.add("%" + t.getName() + "%");
		}
		if(t.getAddress() !=null ) {
			sb.append(" AND cs.address LIKE ? ");
			params.add("%" + t.getAddress() + "%");
		}
		sql = String.format(sql, sb.toString());
		return idaoStore.queryForSingleColVal(sql, Long.class, params.toArray());
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
	public List<ModelCar> getCarByCondition(Condition<ModelCar> condition) {
		// TODO Auto-generated method stub
		return iDaoCar.findByCondition(condition);
	}

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
	
	@Override
	public Integer update(ModelStore t) {
		try {
			// validation
			if(t == null) {
				return null;
			}
			// persist object in security mode
			ModelStore storeDB = find(t.getId());
			BeanUtils.copyProperties(storeDB, t, false);
			storeDB.setUpdateTime(new Date());
			return super.update(storeDB);
		} catch (Exception e) {
			throw new ServException(e);
		}
	}

}
