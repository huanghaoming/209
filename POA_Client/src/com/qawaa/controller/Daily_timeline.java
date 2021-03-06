package com.qawaa.controller;

import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;
import com.qawaa.model.Daily_timelineModel;
import com.qawaa.model.Daily_timeline_pointModel;
import com.qawaa.common.CustomerHttpClient;

public class Daily_timeline {
	public static Daily_timeline_pointModel JsonToJavaBean(int event_id,int min_point){
		String url="http://220.113.8.252:8300/point/daily_timeline.json?";
		if(event_id!=0){
			url+="event_id="+event_id;
		}
		if(min_point!=0){
			url+="&min_point="+min_point;
		}
		String response=CustomerHttpClient.get(url);
		JSONObject object = JSONObject.fromObject(response);
		Map<String, Class<?>> cmap = new HashMap<String, Class<?>>();
		cmap.put("p", Daily_timeline_pointModel.class);
		Daily_timelineModel points = (Daily_timelineModel) JSONObject.toBean(object, Daily_timelineModel.class,cmap);
		Daily_timeline_pointModel point=points.getP();
		return point;
		}
}
