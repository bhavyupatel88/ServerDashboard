package com.dashboard.modal;

import java.util.Arrays;

public class DataModal {

	private String[] labels;
	private DataSetModal[] datasets;

	public String[] getLabels() {
		return labels;
	}

	public void setLabels(String[] labels) {
		this.labels = labels;
	}

	public DataSetModal[] getDatasets() {
		return datasets;
	}

	public void setDatasets(DataSetModal[] datasets) {
		this.datasets = datasets;
	}

	@Override
	public String toString() {
		return "DataModal [labels=" + Arrays.toString(labels) + ", datasets=" + Arrays.toString(datasets) + "]";
	}
	
	

}
