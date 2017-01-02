package org.icube.test.helper;

import java.util.List;
import java.util.Map;

import org.icube.dashboard.LocationData;
import org.icube.helper.MasterListHelper;
import org.junit.Test;

public class MasterListHelperTest {
	
	@Test
	public void testGetLocationMasterList(){
		MasterListHelper mlh = new MasterListHelper();
		List<LocationData> locationMasterList = mlh.getLocationMasterList();
		for(LocationData ld :locationMasterList){
			System.out.println(ld.getRegionId());
			System.out.println(ld.getRegionName());
			System.out.println(ld.getCircleId());
			System.out.println(ld.getCircleName());
			System.out.println(ld.getCityId());
			System.out.println(ld.getCityName());
		}
	}
	
	@Test
	public void testGetRegionMasterList(){
		MasterListHelper mlh = new MasterListHelper();
		Map<Integer, String> regionMasterList = mlh.getRegionMasterList();
		for (int i : regionMasterList.keySet()){
			System.out.println(i + ":" + regionMasterList.get(i));
		}
	}
	
	@Test
	public void testGetCircleMasterList(){
		MasterListHelper mlh = new MasterListHelper();
		Map<Integer, String> circleMasterList = mlh.getCircleMasterList();
		for (int i : circleMasterList.keySet()){
			System.out.println(i + ":" + circleMasterList.get(i));
		}
	}
	
	@Test
	public void testGetCityMasterList(){
		MasterListHelper mlh = new MasterListHelper();
		Map<Integer, String> cityMasterList = mlh.getCityMasterList();
		for (int i : cityMasterList.keySet()){
			System.out.println(i + ":" + cityMasterList.get(i));
		}
	}
	
	@Test
	public void testGetRoleMasterList(){
		MasterListHelper mlh = new MasterListHelper();
		Map<Integer, String> roleMasterList = mlh.getRoleMasterList();
		for (int i : roleMasterList.keySet()){
			System.out.println(i + ":" +  roleMasterList.get(i));
		}
	}
	

}
