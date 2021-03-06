package com.qawaa.controller;

import java.util.HashMap;
import java.util.Map;

import com.qawaa.common.CustomerHttpClient;
import com.qawaa.model.Event_timelineModel;
import com.qawaa.model.Event_timeline_pointsModel;



import net.sf.json.JSONObject;

public class Event_timeline {
	public static Event_timeline_pointsModel JsonToJavaBean(int event_id,int point_id){
		String url="http://220.113.8.252:8300/point/event_timeline.json?";
		if(event_id!=0){
			url+="event_id="+event_id;
		}
		if(point_id!=0){
			url+="&point_id="+point_id;
		}
		String response=CustomerHttpClient.get(url);
		JSONObject object = JSONObject.fromObject(response);
		Map<String, Class<?>> cmap = new HashMap<String, Class<?>>();
		cmap.put("p", Event_timeline_pointsModel.class);
		Event_timelineModel points = (Event_timelineModel) JSONObject.toBean(object,Event_timelineModel.class,cmap);
		Event_timeline_pointsModel point=points.getP();
		return point;
	}
}
