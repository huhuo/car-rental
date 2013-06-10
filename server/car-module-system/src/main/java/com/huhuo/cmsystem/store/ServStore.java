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
import com.huhuo.carservicecore.sys.file.ModelFileUpload;
import com.huhuo.carservicecore.sys.user.ModelUser;
import com.huhuo.cmsystem.file.IServFileUpload;
import com.huhuo.cmsystem.security.user.IServUser;
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
	
	@Resource(name = "cmsystemServFileUpload")
	private IServFileUpload iServFileUpload;
	
	@Resource(name = "cmsystemServUser")
	private IServUser iServUser;

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
		String sql="SELECT cs.*,su.username,IF(TABLE2.rentNum IS NULL,0,TABLE2.rentNum) AS rentNum ,IF((TABLE1.totalNum-TABLE2.rentNum) IS NULL,0,(TABLE1.totalNum-TABLE2.rentNum)) AS freeNum,TABLE1.totalNum FROM  cust_store cs LEFT JOIN sys_user su ON su.id=cs.managerId and su.status=1  LEFT JOIN (SELECT cc.storeId AS storeId,   COUNT(cc.id) AS totalNum FROM cust_car cc GROUP BY cc.storeId) AS TABLE1 ON cs.id =TABLE1.storeId  LEFT JOIN ( SELECT cc.storeId ,COUNT(cc.id) AS rentNum FROM cust_car cc WHERE cc.status=2 GROUP BY cc.storeId) AS TABLE2 ON cs.id=TABLE2.storeId WHERE cs.status=1 %s ORDER BY cs.createTime DESC, cs.updateTime DESC, cs.id DESC LIMIT ?, ?;";
		StringBuilder sb = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		if(t.getManagerId() !=null ) {
			sb.append(" AND cs.managerId = ? ");
			params.add(t.getManagerId());
		}
		if(t.getId()!=null) {
			sb.append(" AND cs.id = ? ");
			params.add(t.getId());
		}
		if(t.getName()!=null) {
			sb.append(" AND cs.name LIKE ? ");
			params.add("%"+t.getName()+"%");
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
		String sql="SELECT count(*) FROM  cust_store cs LEFT JOIN sys_user su  ON su.id=cs.managerId and su.status=1 LEFT JOIN (SELECT cc.storeId AS storeId,   COUNT(cc.id) AS totallNum FROM cust_car cc GROUP BY cc.storeId) AS TABLE1 ON cs.id =TABLE1.storeId  LEFT JOIN ( SELECT cc.storeId ,COUNT(cc.id) AS rentNum FROM cust_car cc  WHERE cc.status=2  GROUP BY cc.storeId) AS TABLE2 ON cs.id=TABLE2.storeId WHERE cs.status=1 %s ;";
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
	
	
	
	
	@Override
	public List<ModelStore> findByCondition(
			Condition<ModelStore> condition, boolean injected) {
		List<ModelStore> list = findByCondition(condition);
		if(list!=null && injected) {
			/*String sql = "SELECT cc.storeId, cc.status, COUNT(id) FROM cust_car cc GROUP BY cc.storeId ASC, cc.status;";
			List<ModelCar> list2 = iDaoCar.findList(sql);*/
			List<ModelCar> list2 = iDaoCar.findByCondition(null);
			int i =0;
			int j =0;
			
			for(ModelStore t : list) {
				ModelFileUpload picture = iServFileUpload.find(t.getPictureId());
				t.setPicture(picture);
				ModelUser user = iServUser.find(t.getManagerId());
				t.setUser(user);
				for(ModelCar car : list2){
					if(car.getStoreId().equals(t.getId())){
						t.setCar(car);
						ModelCar car2 = t.getCar();
						if(car2.getStatus().equals(1)){
							++i;
						}
						if(car2.getStatus().equals(2)){
							++j;
						}
						
						t.setFreeNum(i);
						t.setRentNum(j);
						
					}else {
						t.setFreeNum(i);
						t.setRentNum(j);
						
					}
				}
				i=0;
				j=0;
				}
		}
		return list;
	}
	@Override
	public ModelStore detailFind(Long id) {
			/*String sql = "SELECT cc.storeId, cc.status, COUNT(id) FROM cust_car cc GROUP BY cc.storeId ASC, cc.status;";
			List<ModelCar> list2 = iDaoCar.findList(sql);*/
			ModelStore t = idaoStore.find(id);
			List<ModelCar> list2 = iDaoCar.findByCondition(null);
			int i =0;
			int j =0;
			int k =0 ;
			int l =0 ;
			ModelFileUpload picture = iServFileUpload.find(t.getPictureId());
			t.setPicture(picture);
			ModelUser user = iServUser.find(t.getManagerId());
			t.setUser(user);
			for(ModelCar car : list2){
				if(car.getStoreId().equals(t.getId())){
						t.setCar(car);
						ModelCar car2 = t.getCar();
						if(car2.getStatus().equals(1)){
							++i;
						}
						if(car2.getStatus().equals(2)){
							++j;
						}
						if(car2.getStatus().equals(3)){
							++k;
						}
						if(car2.getStatus().equals(4)){
							++l;
						}
						t.setFreeNum(i);
						t.setRentNum(j);
						t.setDisableNum(l);
						t.setRepairNum(k);
					}else {
						t.setFreeNum(i);
						t.setRentNum(j);
						t.setDisableNum(l);
						t.setRepairNum(k);
					}
				}
			i=0;
			j=0;
			k=0;
			l=0;
				return t;
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
		ModelFileUpload picture = t.getPicture();
		if(picture!=null){
			picture = iServFileUpload.uploadFile(picture);
			t.setPictureId(picture.getId());
		}
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
			storeDB.setUpdateTime(new Date());
			BeanUtils.copyProperties(storeDB, t, false);
			ModelFileUpload picture = t.getPicture();
			if(picture != null) {
				iServFileUpload.uploadFile(picture);
				storeDB.setPictureId(picture.getId());
			}
			return super.update(storeDB);
		} catch (Exception e) {
			throw new ServException(e);
		}
	}

	@Override
	public List<ModelCar> getCarByCondition(Condition<ModelCar> condition) {
		// TODO Auto-generated method stub
		return null;
	}

}
