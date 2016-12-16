package org.icube.dashboard;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DashboardHelper {

	public Map<Date, Integer> getCandidatesByMonth() {
		return new HashMap<Date, Integer>();
	}

	public List<LocationData> getLocationCount(int regionId, int circleId, int cityId) {

		// 0 for ALL else specific IDs

		// O/P : region, circle, city, candidate count

		return new ArrayList<LocationData>();
	}

	public Map<Integer, Integer> getRoleCount(int regionId, int circleId) {
		// 0 for ALL

		// O/P : designation, count

		return new HashMap<Integer, Integer>();
	}
}
