package org.icube.dashboard;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.icube.axis.ObjectFactory;
import org.icube.helper.DatabaseConnectionHelper;

public class DashboardHelper {

	/**
	 * Retrieves the number of candidates per month
	 * @return map of date and corresponding candidate count
	 */
	public Map<Date, Integer> getCandidatesByMonth() {
		DatabaseConnectionHelper dch = ObjectFactory.getDBHelper();
		org.apache.log4j.Logger.getLogger(DashboardHelper.class).debug(
				"HashMap created");
		Map<Date, Integer> candidateCountMap = new HashMap<>();
		try (CallableStatement cstmt = dch.masterDS.getConnection()
				.prepareCall("{call getCandidatesByMonth()}");
				ResultSet rs = cstmt.executeQuery()) {
			//fill the candidate count map 
			
			while (rs.next()) {
				candidateCountMap.put(rs.getDate("Date"), rs.getInt("count"));
			}

		} catch (SQLException e) {
			org.apache.log4j.Logger
					.getLogger(DashboardHelper.class)
					.error("Exception while retrieving the number of candidater per month details :",
							e);
		}
		return candidateCountMap;
	}

	/**
	 * Retrieves the count by location
	 * @param regionId - region id : 0 for ALL
	 * @param circleId - circle id : 0 for ALL
	 * @param cityId - city id : 0 for ALL
	 * @return List of Location objects 
	 */
	public List<LocationData> getLocationCount(int regionId, int circleId,
			int cityId) {

		DatabaseConnectionHelper dch = ObjectFactory.getDBHelper();
		List<LocationData> locationDataList = new ArrayList<>();
		try (CallableStatement cstmt = dch.masterDS.getConnection()
				.prepareCall("{call getLocationCount(?,?,?}")) {
			
			//send regionId,circleId and cityId as input to the procedure
			cstmt.setInt("regionId", regionId);
			cstmt.setInt("circleId", circleId);
			cstmt.setInt("cityId", cityId);
			try (ResultSet rs = cstmt.executeQuery()) {
				while (rs.next()) {
					
					//fill the location object
					LocationData ld = setLocationData(rs);
					
					//add location object to the location data list
					locationDataList.add(ld);
				}
			}
		} catch (SQLException e) {
			org.apache.log4j.Logger.getLogger(DashboardHelper.class).error(
					"Exception while retrieving the location count deatails :",
					e);
		}
		return locationDataList;
	}

	/**
	 * Fills the location object with data from the db
	 * @param rs - resultset with location data
	 * @return LocationData object
	 * @throws SQLException - If unable to retrieve data from the resultset
	 */
	private LocationData setLocationData(ResultSet rs) throws SQLException {
		LocationData ld = new LocationData();
		ld.setRegionId(rs.getInt("regionId"));
		ld.setCircleId(rs.getInt("circleId"));
		ld.setCityId(rs.getInt("cityId"));
		ld.setCandidateCount(rs.getInt("candidateCount"));
		return ld;
	}

	/**
	 * Retrieves the count of candidates for each role
	 * @param regionId - region id : 0 for ALL
	 * @param circleId - circle id : 0 for ALL
	 * @return Map of role id and corresponding count
	 */
	public Map<Integer, Integer> getRoleCount(int regionId, int circleId) {
		DatabaseConnectionHelper dch = ObjectFactory.getDBHelper();
		org.apache.log4j.Logger.getLogger(DashboardHelper.class).debug(
				"HashMap created");
		Map<Integer, Integer> roleCountMap = new HashMap<>();
		try (CallableStatement cstmt = dch.masterDS.getConnection()
				.prepareCall("{call getRoleCount(?,?}")) {
			
			//send regionId and circleId as input to the procedure
			
			cstmt.setInt("regionId", regionId);
			cstmt.setInt("circleId", circleId);
			try (ResultSet rs = cstmt.executeQuery()) {
				while (rs.next()) {
					//fill the roleCountMap
					
					roleCountMap.put(rs.getInt("roleId"), rs.getInt("count"));
				}
			}

		} catch (SQLException e) {
			org.apache.log4j.Logger.getLogger(DashboardHelper.class).error(
					"Exeption in retrieving the role count details :", e);
		}

		return roleCountMap;
	}
}
