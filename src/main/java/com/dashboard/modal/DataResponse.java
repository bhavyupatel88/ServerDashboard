package com.dashboard.modal;

import java.util.ArrayList;
import java.util.List;

public class DataResponse {

	private List<DataModal> dataList = new ArrayList<DataModal>();

	public List<DataModal> getDataList() {
		return dataList;
	}

	public void setDataList(List<DataModal> dataList) {
		this.dataList = dataList;
	}
}
