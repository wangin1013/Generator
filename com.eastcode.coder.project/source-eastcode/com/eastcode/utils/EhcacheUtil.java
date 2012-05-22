package com.eastcode.utils;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class EhcacheUtil {

	private EhcacheUtil() {
	}

	private static CacheManager cacheManager = null;

	static {

		cacheManager = CacheManager.create();
	}

	public static CacheManager getCacheManager() {
		return cacheManager;
	}

	public static Cache getCache(String name) {
		return cacheManager.getCache(name);
	}

	public static Element createElement(Object key, Object value) {
		Element element = new Element(key, value);
		return element;
	}
}
