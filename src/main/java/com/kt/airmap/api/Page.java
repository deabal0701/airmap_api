
package com.kt.airmap.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
//import com.wordnik.swagger.annotations.ApiModel;

/**
 * <PRE>
 *  ClassName Page
 * </PRE>
 * @brief
 * @version 1.0
 * @date 2015. 11. 4. 오후 6:44:56
 * @author kim.seokhun@kt.com
 */
@JsonInclude(Include.NON_EMPTY)
//@ApiModel(value = "Page", description = "Page resource representation" )
public class Page {

	@JsonIgnore
	private int total;
	@JsonIgnore
	private int offset;
	@JsonIgnore
	private int limit;
	
	public Page() {}

	public Page(int total, int offset, int limit) {
		this.total = total;
		this.offset = offset;
		this.limit = limit;
	}

	@JsonIgnore
	public int getTotal() {
		return total;
	}

	@JsonIgnore
	public void setTotal(int total) {
		this.total = total;
	}

	@JsonIgnore
	public int getOffset() {
		return offset;
	}

	@JsonIgnore
	public void setOffset(int offset) {
		this.offset = offset;
	}

	@JsonIgnore
	public int getLimit() {
		return limit;
	}

	@JsonIgnore
	public void setLimit(int limit) {
		this.limit = limit;
	}

}
