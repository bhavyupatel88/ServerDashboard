package com.dashboard.service;

import java.util.Map;

import com.dashboard.modal.DataModal;

public interface DashboardService {
	
	Map<String, DataModal> getCSVData();
}
