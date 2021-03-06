package com.qawaa.action;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import com.opensymphony.xwork2.ActionSupport;
import com.qawaa.common.CustomerHttpClient;
import com.qawaa.model.Hour_timelineModel;
import com.qawaa.model.Hour_timeline_pointsModel;
import com.qawaa.model.TodayHotRankBean;
import com.qawaa.model.TodayHotRankPointBean;

/**
 * 
 */

/**
 * @author Ran Xu
 * @data 2014-6-5 上午8:46:40 
 * @company 浙江传媒学院-互联网与社会研究中心  CIS
 * @address 中国杭州下沙高教园区学源街998号
 * @tel 0571-86879294
 * @home www.cischina.org
 * @comment 
 *
 */
public class TodayHotRank extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7849192082769340638L;
	public String result;
	public String titles;
	public String pos;
	private List<TodayHotRankPointBean> point;
	public Date date;
	
	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the result
	 */
	public String getResult() {
		return result;
	}

	/**
	 * @param result the result to set
	 */
	public void setResult(String result) {
		this.result = result;
	}

	public String today(){
		try {
			this.authorize();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String url="http://220.113.8.252:8300/rank/rank_today.json";
		String response=CustomerHttpClient.get(url);
		JSONObject object = JSONObject.fromObject(response);
		Map<String, Class<?>> cmap = new HashMap<String, Class<?>>();
		cmap.put("hotEvents", TodayHotRankPointBean.class);
		TodayHotRankBean points = (TodayHotRankBean) JSONObject.toBean(object, TodayHotRankBean.class,cmap);
		point=points.getHotEvents();
		StringBuffer buffer1 = new StringBuffer();
		StringBuffer buffer2 = new StringBuffer();
		for(int i=0;i<point.size();i++){
			buffer1.append("'");
			buffer1.append(point.get(i).getTitle());
			buffer1.append("'");
			buffer2.append(point.get(i).getPoint());
			if(i != point.size() -1){
				buffer1.append(",");
				buffer2.append(",");
			}
		}
		this.titles = buffer1.toString();
		this.pos = buffer2.toString();
		this.date = new Date();
		return SUCCESS;
	}
	//授权
	private void authorize() throws Exception{
			String url="http://220.113.8.252:8300/authorize/pass.json?token=chinalab";
			String response=CustomerHttpClient.get(url);
		}

	/**
	 * @return the point
	 */
	public List<TodayHotRankPointBean> getPoint() {
		return point;
	}

	/**
	 * @param point the point to set
	 */
	public void setPoint(List<TodayHotRankPointBean> point) {
		this.point = point;
	}

	/**
	 * @return the titles
	 */
	public String getTitles() {
		return titles;
	}

	/**
	 * @param titles the titles to set
	 */
	public void setTitles(String titles) {
		this.titles = titles;
	}

	/**
	 * @return the pos
	 */
	public String getPos() {
		return pos;
	}

	/**
	 * @param pos the pos to set
	 */
	public void setPos(String pos) {
		this.pos = pos;
	}
}
