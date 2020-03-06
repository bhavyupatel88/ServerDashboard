package com.dashboard.modal;

public class DataSetModal {

	private String label;
	private Integer[] data;

	public DataSetModal(String label, Integer[] data) {
		super();
		this.label = label;
		this.data = data;
	}

	public DataSetModal() {
		super();
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Integer[] getData() {
		return data;
	}

	public void setData(Integer[] data) {
		this.data = data;
	}

}
