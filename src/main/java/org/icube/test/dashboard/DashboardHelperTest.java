package org.icube.test.dashboard;


import java.util.Date;
import java.util.List;
import java.util.Map;

import org.icube.dashboard.DashboardHelper;
import org.icube.dashboard.LocationData;
import org.icube.role.Role;
import org.junit.Test;

public class DashboardHelperTest {
	
	@Test
	public void testGetCandidatesByMonth(){
		DashboardHelper dh = new DashboardHelper();
		Map<Date, Integer> candidateMap = dh.getCandidatesByMonth();
		for (Date d : candidateMap.keySet()){
			
			System.out.println(d + ":" + candidateMap.get(d));
			
		}
	}
	
	@Test
	public void testGetLocationCount(){
		DashboardHelper dh = new DashboardHelper();
		List<LocationData> locationList = dh.getLocationCount();
		for (LocationData ld : locationList){
			System.out.println("region id :" + ld.getRegionId());
			System.out.println("circle id :" + ld.getCircleId());
			System.out.println("city id :" + ld.getCityId());
			System.out.println("count :" + ld.getCandidateCount());
			
		}
	}
	
	@Test
	public void testGetRoleCount(){
		DashboardHelper dh = new DashboardHelper();
		List<Role> roleCountList = dh.getRoleCount(1, 0);
		for (Role r : roleCountList){
			
			System.out.println(r.getRoleId() + " - " + r.getRole() + " - " + r.getCandidateCount());
			
		}
	}

}
