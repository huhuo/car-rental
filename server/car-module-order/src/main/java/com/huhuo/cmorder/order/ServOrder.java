package com.huhuo.cmorder.order;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.huhuo.carservicecore.csm.consumer.ModelConsumer;
import com.huhuo.carservicecore.csm.order.IDaoOrder;
import com.huhuo.carservicecore.csm.order.ModelOrder;
import com.huhuo.carservicecore.cust.car.ModelCar;
import com.huhuo.carservicecore.cust.car.ModelCarType;
import com.huhuo.carservicecore.cust.car.ModelChargeStandard;
import com.huhuo.carservicecore.cust.store.ModelStore;
import com.huhuo.carservicecore.db.GenericBaseExtenseServ;
import com.huhuo.integration.base.IBaseExtenseDao;
import com.huhuo.integration.db.mysql.Condition;
import com.huhuo.integration.db.mysql.Page;
import com.huhuo.integration.exception.ServException;

@Service("cmorderServOrder")
public class ServOrder extends GenericBaseExtenseServ<ModelOrder> implements
		IServOrder {

	@Resource(name = "carservicecoreDaoOrder")
	private IDaoOrder idaoOrder;

	@Override
	public IBaseExtenseDao<ModelOrder> getDao() {
		// TODO Auto-generated method stub
		return idaoOrder;
	}

	@Override
	public Class<ModelOrder> getModelClazz() {
		// TODO Auto-generated method stub
		return ModelOrder.class;
	}

	@Override
	public List<ModelConsumer> getConsumerListByPhone(String phone) {
		List<ModelConsumer> queryForList = null;
		StringBuilder sb = new StringBuilder();
		List<Object> list = new ArrayList<Object>();
		sb.append("select * from csm_consumer where status!=0 ");
		if (phone != null) {
			sb.append(" and mobileNumber like ?");
			list.add(phone + "%");
		}
		sb.append(" limit 0 , 10");
		queryForList = idaoOrder.queryForList(sb.toString(),
				ModelConsumer.class, list.toArray());

		return queryForList;
	}

	@Override
	public List<ModelCar> getCarListBylicencePlate(String licencePlate,
			Long carTypeId) {
		StringBuilder sb = new StringBuilder();
		List<Object> list = new ArrayList<Object>();
		sb.append("select * from cust_car where 1=1 and status=1 ");
		if (licencePlate != null) {
			sb.append(" and licencePlate like ?");
			list.add(licencePlate + "%");
		}
		if (carTypeId != null) {
			sb.append(" and carTypeId = ?");
			list.add(carTypeId);
		}
		List<ModelCar> queryForList = idaoOrder.queryForList(sb.toString(),
				ModelCar.class, list.toArray());

		return queryForList;
	}

	@Override
	public List<ModelCarType> getCarTypeList(String carTypeName, Long carTypeId) {
		StringBuilder sb = new StringBuilder();
		List<Object> list = new ArrayList<Object>();
		sb.append("select * from cust_car_type where 1=1 ");
		if (carTypeName != null) {
			sb.append(" and name like ?");
			list.add(carTypeName + "%");
		}
		if (carTypeId != null) {
			sb.append(" and id = ?");
			list.add(carTypeId);
		}
		List<ModelCarType> queryForList = idaoOrder.queryForList(sb.toString(),
				ModelCarType.class, list.toArray());

		return queryForList;
	}

	@Override
	public List<ModelStore> getStoreById(Long storeId) {
		StringBuilder sb = new StringBuilder();
		List<Object> list = new ArrayList<Object>();
		sb.append("select * from cust_store where 1=1 ");
		if (storeId != null) {
			sb.append(" and id = ?");
			list.add(storeId);
		}
		List<ModelStore> queryForList = idaoOrder.queryForList(sb.toString(),
				ModelStore.class, list.toArray());

		return queryForList;
	}

	@Override
	public List<ModelChargeStandard> getchargeStandardById(Long chargeStandardId) {
		StringBuilder sb = new StringBuilder();
		List<Object> list = new ArrayList<Object>();
		sb.append("select * from cust_charge_standard where 1=1 ");
		if (chargeStandardId != null) {
			sb.append(" and id = ?");
			list.add(chargeStandardId);
		}
		List<ModelChargeStandard> queryForList = idaoOrder.queryForList(
				sb.toString(), ModelChargeStandard.class, list.toArray());

		return queryForList;
	}

	@Override
	public void updateCarStatus(Long id,int status) {
		if(id!=null){
			StringBuilder sb = new StringBuilder();
			sb.append("update  cust_car set status=? where id=? ");
			int update = idaoOrder.update(sb.toString(),status, id);
		}else{
			throw new ServException("车辆信息不能为空");
		}
	}

	@Override
	public Page<Map<String, Object>> findOrderPage(
			Condition<Map<String, Object>> condition) {
		Map<String, Object> opt = condition.getOpt();
		
		StringBuilder querysb = new StringBuilder();
		StringBuilder countsb = new StringBuilder();
		StringBuilder sqlsb = new StringBuilder();
		
		
		
		
		
		querysb.append("SELECT o.id, o.carRentTime,o.carPlanRetTime,con.username,con.mobileNumber,car.licencePlate,ct.name,f.path,f.md5 ");
		
		countsb.append("SELECT COUNT(o.id) AS oCount    ");
		
		
		List<Object> list = new ArrayList<Object>();
		
		sqlsb.append(" FROM  csm_order o LEFT JOIN csm_consumer con ON o.consumerId = con.id LEFT JOIN cust_car car ON o.carId=car.id LEFT JOIN cust_car_type ct ON car.carTypeId=ct.id LEFT JOIN sys_file_upload f ON ct.iconId = f.id  where 1=1 ");
		
		
		if(opt!=null){
			Object status = opt.get("status");
			
			if(status!=null){
				sqlsb.append(" and o.status = ?");
				list.add(status);
			}
			Object phone = opt.get("phone");
			if(phone!=null){
				sqlsb.append(" and con.mobileNumber = ?");
				list.add(phone);
			}
			Object orderNo = opt.get("orderNo");
			if(orderNo!=null){
				sqlsb.append(" and o.id = ?");
				list.add(orderNo);
			}
			Object name = opt.get("name");
			if(name!=null){
				sqlsb.append(" and con.username like ?");
				list.add(name);
			}
			
			
			
		}
		
		
		Map<String, Object> countMap = idaoOrder.queryForMap(countsb.append(sqlsb).toString(), list.toArray());
		Long count=(Long) countMap.get("oCount");
		
		Page<Map<String, Object>> page = condition.getPage();
		sqlsb.append("  limit ? , ?");
		list.add(page.getStart());
		list.add(page.getLimit());
		

		List<Map<String,Object>> queryForList = idaoOrder.queryForMapList(querysb.append(sqlsb).toString(), list.toArray());
		
		page.setRecords(queryForList);
		page.setTotal(count);
		return page;
	}

}
