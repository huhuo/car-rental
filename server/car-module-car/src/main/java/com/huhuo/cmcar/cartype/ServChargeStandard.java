package com.huhuo.cmcar.cartype;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.huhuo.carservicecore.cust.car.IDaoChargeStandard;
import com.huhuo.carservicecore.cust.car.ModelChargeStandard;
import com.huhuo.carservicecore.db.GenericBaseExtenseServ;
import com.huhuo.integration.base.IBaseExtenseDao;
import com.huhuo.integration.exception.ServException;
import com.huhuo.integration.util.BeanUtils;

@Service("cmcarServChargeStandards")
public class ServChargeStandard extends GenericBaseExtenseServ<ModelChargeStandard> implements IServChargeStandard {

	@Resource(name = "carservicecoreDaoChargeStandard")
	private IDaoChargeStandard iDaoChargeStandard;

	@Override
	public IBaseExtenseDao<ModelChargeStandard> getDao() {
		// TODO Auto-generated method stub
		return iDaoChargeStandard;
	}

	@Override
	public Class<ModelChargeStandard> getModelClazz() {
		// TODO Auto-generated method stub
		return ModelChargeStandard.class;
	}

	@Override
	public Integer add(ModelChargeStandard t) {
		// validation
		if(t == null) {
			return null;
		}
		t.setCreateTime(new Date());
		t.setUpdateTime(new Date());
		return super.add(t);
	}

	@Override
	public Integer update(ModelChargeStandard t) {
		try {
			// validation
			if(t == null) {
				return null;
			}
			// persist object in security mode
			ModelChargeStandard chargeStandardDB = find(t.getId());
			if(chargeStandardDB != null) {
				BeanUtils.copyProperties(chargeStandardDB, t, false);
				chargeStandardDB.setUpdateTime(new Date());
				return super.update(chargeStandardDB);
			} else {
				return add(t);
			}
		} catch (Exception e) {
			throw new ServException(e);
		}
	}

	@Override
	public Integer addBatch(List<ModelChargeStandard> list) {
		// TODO Auto-generated method stub
		return super.addBatch(list);
	}
	

}
