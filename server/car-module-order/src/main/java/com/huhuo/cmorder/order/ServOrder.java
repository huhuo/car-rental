package com.huhuo.cmorder.order;

import java.util.ArrayList;
import java.util.List;

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
		sb.append("select * from csm_consumer where 1=1 ");
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
		sb.append(" limit 0 , 10");
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
	public void updateCarStatus(Long id) {
		if(id!=null){
			StringBuilder sb = new StringBuilder();
			List<Object> list = new ArrayList<Object>();
			sb.append("update  cust_car set status=2 where id=? ");
			int update = idaoOrder.update(sb.toString(), id);
		}else{
			throw new ServException("车辆信息不能为空");
		}
	}

}
