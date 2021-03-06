/**
 * 
 */
package org.cischina.action.npo.config;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.cischina.action.AjaxBaseAction;
import org.cischina.data.entity.EventStatus;
import org.cischina.service.EventStatusService;
import org.cischina.web.page.PageUtil;
/**
 * @author Ran Xu
 * @data 2014-5-7 下午4:49:39 
 * @company 浙江传媒学院-互联网与社会研究中心  CIS
 * @address 中国杭州下沙高教园区学源街998号
 * @tel 0571-86879294
 * @home www.cischina.org
 * @comment 事件状态ACTION
 *
 */
public class EventStatusAction extends AjaxBaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7831948040139004838L;
	private EventStatusService eventStatusService;
	private List<EventStatus> statusList;
	private EventStatus status;
	private int pageNum = 1;
	private int pageSize = 20;
	private PageUtil page;
	
	/**
	 * @return the eventStatusService
	 */
	public EventStatusService getEventStatusService() {
		return eventStatusService;
	}

	/**
	 * @param eventStatusService the eventStatusService to set
	 */
	public void setEventStatusService(EventStatusService eventStatusService) {
		this.eventStatusService = eventStatusService;
	}

	/**
	 * @return the statusList
	 */
	public List<EventStatus> getStatusList() {
		return statusList;
	}

	/**
	 * @param statusList the statusList to set
	 */
	public void setStatusList(List<EventStatus> statusList) {
		this.statusList = statusList;
	}

	/**
	 * @return the status
	 */
	public EventStatus getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(EventStatus status) {
		this.status = status;
	}

	/**
	 * @return the pageNum
	 */
	public int getPageNum() {
		return pageNum;
	}

	/**
	 * @param pageNum the pageNum to set
	 */
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	/**
	 * @return the pageSize
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * @param pageSize the pageSize to set
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * @return the page
	 */
	public PageUtil getPage() {
		return page;
	}

	/**
	 * @param page the page to set
	 */
	public void setPage(PageUtil page) {
		this.page = page;
	}

	/**
	 * 列表页
	 * @return
	 */
	public String index(){
		Map<String, Object> params = null;
		if(this.status != null){
			params = new HashMap<String, Object>(1);
			if(StringUtils.isNotBlank(status.getAesName())){
				params.put("ilikeAesName", status.getAesName().trim());
			}
		}
		int count = this.eventStatusService.getCountByParamsAndPages(params);
		page = new PageUtil(pageNum, pageSize, count);
		statusList = this.eventStatusService.getEntitiesByParamsAndPages(params, page.getStartData(), pageSize);
		return SUCCESS;
	}
	
	/**
	 * 查找建议
	 * @return
	 */
	public String suggest(){
		int count = this.eventStatusService.getCountByParamsAndPages(null);
		statusList = this.eventStatusService.getEntitiesByParamsAndPages(null, 0, count);
		return SUCCESS;
	}

}
