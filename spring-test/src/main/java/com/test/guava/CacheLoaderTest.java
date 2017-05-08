package com.test.guava;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;

public class CacheLoaderTest {

	private static final Logger logger = LoggerFactory.getLogger(CacheLoader.class);
	private static LoadingCache<Long, String> buildCache(){
		return CacheBuilder.newBuilder()
				.maximumSize(1000)
				.expireAfterWrite(10, TimeUnit.SECONDS)
				.refreshAfterWrite(5, TimeUnit.SECONDS)
				.removalListener(new RemovalListener<Long, String>(){

					public void onRemoval(RemovalNotification<Long, String> key) {
						System.out.println("正在执行RemovalNotification=" + key + "正在removal！");
					}
			         
		          }
				).build(
		    		  new CacheLoader<Long, String>() {

						@Override
						public String load(Long key) throws Exception {
							System.out.println("正在执行load=" + key + "正在加载！");
							return key.toString();
						}
		              }
        		);
	}
	
	public static void main(String[] args) {
		LoadingCache<Long, String> loadingCache = buildCache();
		loadingCache.put(1L, "test1");
		loadingCache.put(2L, "test2");
		try {
			System.out.println("1:Get key=1 from cache = " + loadingCache.get(1L));
			System.out.println("1:Get key=2 from cache = " + loadingCache.get(2L));
			
			Thread.sleep(5500);
			
			System.out.println("2:Get key=1 from cache = " + loadingCache.get(1L));
			System.out.println("2:Get key=2 from cache = " + loadingCache.get(2L));
			
			Thread.sleep(5500);
			
//			System.out.println("3:Get key=1 from cache = " + loadingCache.get(1L));
//			System.out.println("3:Get key=2 from cache = " + loadingCache.get(2L));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
