package com.vs.toolsoso.eventbus;

import java.util.HashMap;
import java.util.Map;

public class EventInfo {

    String tag;
    EventBusData data;

    public EventInfo(String tag) {
        this.tag = tag;
        data = new EventBusData();
    }

    public void put(String key, Object ob){
        data.addObject(key,ob);
    }

    public Object get(String key){
        if (data == null){
            return null;
        }
        Map<String, Object> map = data.getDataMap();
        return map.get(key);
    }

    private class EventBusData {

        Map<String, Object> mapdata;

        public void addObject(String key,Object ob){
            if(mapdata==null){
                mapdata = new HashMap<String, Object>();
            }
            mapdata.put(key, ob);
        }
        public Map<String, Object>getDataMap(){
            return mapdata;
        }

    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
