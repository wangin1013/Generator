package com.eastcode.base.service;

import java.util.List;
import java.util.Map;

import com.eastcode.base.domain.PersistentObject;
import com.eastcode.server.system.domain.Basedata;

public interface BaseService {

	public void save(PersistentObject o);

	public void merge(PersistentObject o);

	public void update(PersistentObject o);

	public void remove(PersistentObject o);

	public void refresh(PersistentObject object);

	public List<Basedata> getBasedataList(String datatypeCode);

	public Map<String, String> getBasedataMap(String datatypeCode);

	public Basedata getBasedata(String datatypeCode, String basedataCode);
}
