package com.vs.toolsoso.eventbus;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: S
 * @date: 2018/11/14 10:35
 * @description:
 */

public class EvenBusData{
	
	Map<String, Object>mapdata;
	
	public void AddObject(String key,Object ob){
		if(mapdata==null){
			mapdata = new HashMap<String, Object>();
			
		}
		mapdata.put(key, ob);
	}
	
	public Map<String, Object>GetDataMap(){
		return mapdata;
	}
	
}