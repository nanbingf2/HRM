package com.rogercw.util.tag;

import com.rogercw.util.constant.HrmConstants;

/**hrm.util.common.HrmConstants;
 *  分页实体 
 */
public class PageModel {
	/** 分页总数据条数  */
	private int recordCount;
	/** 当前页面 */
	private int pageIndex ;
	/** 每页分多少条数据   */
	private int pageSize;
	
	/** 总页数  */
	private int totalSize;

	public PageModel() {
		pageIndex=1;
		pageSize=4;
	}

	public int getRecordCount() {
		this.recordCount = this.recordCount <= 0 ? 0:this.recordCount;
		return recordCount;
	}
	public void setRecordCount(int recordCount) {
		this.recordCount = recordCount;
	}
	public int getPageIndex() {
		this.pageIndex = this.pageIndex <= 0?1:this.pageIndex;
		/** 判断当前页面是否超过了总页数:如果超过了默认给最后一页作为当前页  */
		if(this.pageIndex<=0){
			this.pageIndex=1;
		}else if(pageIndex>this.getTotalSize()){
			this.pageIndex=this.getTotalSize();
		}
		
		return pageIndex;
	}
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	public int getPageSize() {
		this.pageSize = this.pageSize <= HrmConstants.PAGE_DEFAULT_SIZE?HrmConstants.PAGE_DEFAULT_SIZE:this.pageSize;
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public int getTotalSize() {
		if(this.getRecordCount() <=0){
			totalSize = 1 ;
		}else{
			totalSize = (this.getRecordCount() -1)/this.getPageSize() + 1;
		}
		return totalSize;
	}
	
	
	public int getFirstPage(){
		return (this.getPageIndex()-1)*this.getPageSize() ;
	}


}
