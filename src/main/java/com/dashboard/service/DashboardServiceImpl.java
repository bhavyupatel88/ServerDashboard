package com.dashboard.service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.dashboard.modal.DataModal;
import com.dashboard.modal.DataSetModal;

@Service
public class DashboardServiceImpl implements DashboardService {

	@Value("${csv.columns}")
	private String CSV_COLUMNS;

	@Value("${csv.filePath}")
	private String filePath;

	private static List<String> columnList = new ArrayList<String>();
	private static Map<String, List<String>> dataMap = new HashMap<>();
	private static Map<String, DataModal> result = new HashMap<>();

	@SuppressWarnings("rawtypes")
	@Override
	public Map<String, DataModal> getCSVData() {
		initializeColMap();
		String line = "";
		String cvsSplitBy = ",";

		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
			Map<Integer, String> colSeqMap = getColumnIndexes(br);
			while ((line = br.readLine()) != null) {
				String[] country = line.split(cvsSplitBy);
				for (Map.Entry<Integer, String> map : colSeqMap.entrySet()) {
					String value = country[(int) map.getKey()];
					String colName = (String) map.getValue();
					List<String> list = dataMap.get(colName);
					list.add(value);
					dataMap.put(colName, list);
				}
			}

			for (Map.Entry<String, List<String>> map : dataMap.entrySet()) {
				DataModal dm = getChartData(map.getKey(), map.getValue());
				result.put(map.getKey(), dm);
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	private DataModal getChartData(String key, List<String> list) {

		Map<String, Long> result = list.stream()
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

		double total = list.size();

		DataModal dm = new DataModal();
		DataSetModal ds = new DataSetModal();
		List<String> labels = new ArrayList<>();
		List<Integer> data = new ArrayList<>();
		for (Map.Entry<String, Long> map : result.entrySet()) {
			labels.add(map.getKey());
			data.add(calculatePercentage(map.getValue(), total));
		}
		ds.setLabel("# of counts");
		ds.setData(data.toArray(new Integer[data.size()]));
		dm.setLabels(labels.toArray(new String[labels.size()]));
		dm.setDatasets(new DataSetModal[] { ds });

		return dm;
	}

	private int calculatePercentage(double obtained, double total) {
		return (int) Math.round(obtained * 100.0 / total);
	}

	private Map<Integer, String> getColumnIndexes(BufferedReader br) {
		Map<Integer, String> colSeqMap = new HashMap<>();
		String line = "";
		String cvsSplitBy = ",";
		int firstLine = 0;
		try {
			while ((line = br.readLine()) != null && firstLine == 0) {
				String[] columns = line.split(cvsSplitBy);
				int seq = 0;
				for (String col : columns) {
					if (columnList.contains(col)) {
						colSeqMap.put(seq, col);
						dataMap.put(col, new ArrayList<String>());
					}
					seq++;
				}
				firstLine++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return colSeqMap;
	}

	private void initializeColMap() {
		if (null != CSV_COLUMNS) {
			String[] columns = CSV_COLUMNS.split(",");
			columnList = Arrays.asList(columns);
		}
	}

}
