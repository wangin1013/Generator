package com.eastcode.utils;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

public class RssUtil {

	/**
	 * 解析RSS
	 * 
	 * @param url
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<SyndEntry> parseRSS(String url) {
		List<SyndEntry> entryList = null;
		try {
			URLConnection feedUrl = new URL(url).openConnection();

			feedUrl.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

			SyndFeedInput input = new SyndFeedInput();

			SyndFeed feed = input.build(new XmlReader(feedUrl));

			entryList = feed.getEntries();

		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (FeedException ex) {
			ex.printStackTrace();
		}

		return entryList;
	}

	public static void main(String[] args) {
		String url = "http://rss.sina.com.cn/news/china/focus15.xml";

		List<SyndEntry> entryList = parseRSS(url);
		for (int i = 0; i < entryList.size(); i++) {

			SyndEntry entry = entryList.get(i);
			System.out.println(entry.getTitle().trim());

			System.out.println(entry.getPublishedDate());
			System.out.println(entry.getDescription().getValue().trim().replaceAll("　", "").replaceAll("\\n", ""));

		}
	}
}
