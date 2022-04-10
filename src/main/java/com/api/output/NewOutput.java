package com.api.output;

import java.util.ArrayList;
import java.util.List;

import com.dto.NewDTO;

public class NewOutput {
	
	private int page;//page đang đứng
	private int totalPage;// truyền vào tổng số item
	private List<NewDTO> listResult = new ArrayList<>();//mảng chứa thông tin ta cần
	//như danh sách người dùng, danh sách bài viết....
	
	public int getPage() {
		return page;
	}
	
	public void setPage(int page) {
		this.page = page;
	}
	
	public int getTotalPage() {
		return totalPage;
	}
	
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public List<NewDTO> getListResult() {
		return listResult;
	}

	public void setListResult(List<NewDTO> listResult) {
		this.listResult = listResult;
	}
}