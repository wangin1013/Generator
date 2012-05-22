package com.eastcode.base.service.impl;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.eastcode.base.dao.BaseDao;
import com.eastcode.base.domain.PersistentObject;
import com.eastcode.base.service.BaseService;
import com.eastcode.server.system.dao.BasedataDao;
import com.eastcode.server.system.domain.Basedata;

@Service("baseService")
public class BaseServiceImpl implements BaseService {

	@Resource
	private BaseDao baseDao;

	@Resource
	private BasedataDao basedataDao;

	public void save(PersistentObject pojo) {
		if (null == pojo.getId())
			baseDao.save(pojo);
		else
			baseDao.merge(pojo);
	}

	public void merge(PersistentObject o) {
		baseDao.merge(o);
	}

	public void remove(PersistentObject o) {
		baseDao.delete(o);
	}

	public void update(PersistentObject o) {
		baseDao.update(o);
	}

	public void refresh(PersistentObject object) {
		baseDao.refresh(object);
	}

	public List<Basedata> getBasedataList(String datatypeCode) {
		List<Basedata> resultList = basedataDao.queryListByConditions(" and dataType.code='" + datatypeCode + "'");

		return resultList;
	}

	public Map<String, String> getBasedataMap(String datatypeCode) {
		List<Basedata> resultList = basedataDao.queryListByConditions(" and dataType.code='" + datatypeCode + "'");

		Map<String, String> basedataMap = new TreeMap<String, String>();

		for (int i = 0; null != resultList && i < resultList.size(); i++) {
			Basedata basedata = resultList.get(i);
			basedataMap.put(basedata.getCode(), basedata.getName());
		}
		return basedataMap;
	}

	public Basedata getBasedata(String datatypeCode, String basedataCode) {
		List<Basedata> resultList = basedataDao.queryListByConditions(" and dataType.code='" + datatypeCode
				+ "' and baseData.code='" + basedataCode + "'");

		if (!CollectionUtils.isEmpty(resultList)) {
			return (Basedata) resultList.get(0);
		}

		return null;
	}

}
