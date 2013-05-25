package com.huhuo.cmcar.cartype;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.huhuo.carservicecore.cust.car.IDaoCarType;
import com.huhuo.carservicecore.cust.car.ModelCarType;
import com.huhuo.carservicecore.cust.car.ModelChargeStandard;
import com.huhuo.carservicecore.db.GenericBaseExtenseServ;
import com.huhuo.carservicecore.sys.file.ModelFileUpload;
import com.huhuo.cmsystem.file.IServFileUpload;
import com.huhuo.integration.base.IBaseExtenseDao;
import com.huhuo.integration.db.mysql.Condition;
import com.huhuo.integration.exception.ServException;
import com.huhuo.integration.util.BeanUtils;

@Service("cmcarServCarType")
public class ServCarType extends GenericBaseExtenseServ<ModelCarType> implements IServCarType {

	@Resource(name = "carservicecoreDaoCarType")
	private IDaoCarType idaoCarType;
	
	@Resource(name = "cmcarServChargeStandards")
	private IServChargeStandard iServChargeStandard;
	
	@Resource(name = "cmsystemServFileUpload")
	private IServFileUpload iServFileUpload;

	@Override
	public IBaseExtenseDao<ModelCarType> getDao() {
		return idaoCarType;
	}

	@Override
	public Class<ModelCarType> getModelClazz() {
		return ModelCarType.class;
	}

	@Override
	public <V> ModelCarType find(V id) {
		// TODO Auto-generated method stub
		ModelCarType t = super.find(id);
		ModelChargeStandard chargeStandard = iServChargeStandard.find(t.getChargeStandardId());
		t.setChargeStandard(chargeStandard);
		ModelFileUpload icon = iServFileUpload.find(t.getIconId());
		t.setIcon(icon);
		return t;
	}

	@Override
	public List<ModelCarType> findByCondition(
			Condition<ModelCarType> condition, boolean injected) {
		List<ModelCarType> list = findByCondition(condition);
		if(list!=null && injected) {
			for(ModelCarType t : list) {
				ModelChargeStandard chargeStandard = iServChargeStandard.find(t.getChargeStandardId());
				t.setChargeStandard(chargeStandard);
				ModelFileUpload icon = iServFileUpload.find(t.getIconId());
				t.setIcon(icon);
			}
		}
		return list;
	}
	
	@Override
	public Integer add(ModelCarType t) {
		// validation
		if(t == null) {
			return null;
		}
		// update inner object chargeStandard
		ModelChargeStandard chargeStandard = t.getChargeStandard();
		if(chargeStandard != null) {
			iServChargeStandard.add(chargeStandard);
			t.setChargeStandardId(chargeStandard.getId());
		}
		// update t
		t.setCreateTime(new Date());
		t.setUpdateTime(new Date());
		// update icon information
		ModelFileUpload icon = t.getIcon();
		// update DB
		if(icon != null) {
			icon = iServFileUpload.uploadFile(icon);
			t.setIconId(icon.getId());
		}
		return super.add(t);
	}
	
	@Override
	public Integer update(ModelCarType t) {
		try {
			// validation
			if(t == null) {
				return null;
			}
			// persist object in security mode
			ModelCarType carTypeDB = find(t.getId());
			BeanUtils.copyProperties(carTypeDB, t, false);
			// update inner object chargeStandard
			ModelChargeStandard chargeStandard = t.getChargeStandard();
			if (chargeStandard != null) {
				iServChargeStandard.update(chargeStandard);
				carTypeDB.setChargeStandardId(chargeStandard.getId());
			}
			ModelFileUpload icon = t.getIcon();
			if(icon != null) {
				iServFileUpload.uploadFile(icon);
				carTypeDB.setIconId(icon.getId());
			}
			return super.update(carTypeDB);
		} catch (Exception e) {
			throw new ServException(e);
		}
	}

}
