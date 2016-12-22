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
import org.icube.role.Role;

public class DashboardHelper {

	/**
	 * Retrieves the number of candidates per month
	 * @return map of date and corresponding candidate count
	 */
	public Map<Date, Integer> getCandidatesByMonth() {
		DatabaseConnectionHelper dch = ObjectFactory.getDBHelper();
		org.apache.log4j.Logger.getLogger(DashboardHelper.class).debug("HashMap for candidates per month created");
		Map<Date, Integer> candidateCountMap = new HashMap<>();
		try (CallableStatement cstmt = dch.masterDS.getConnection().prepareCall("{call getCandidatesByMonth()}"); ResultSet rs = cstmt.executeQuery()) {
			// fill the candidate count map

			while (rs.next()) {

				candidateCountMap.put(rs.getDate("submission_month"), rs.getInt("candidate_count"));

			}

		} catch (SQLException e) {
			org.apache.log4j.Logger.getLogger(DashboardHelper.class).error("Exception while retrieving the number of candidater per month details :",
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
	public List<LocationData> getLocationCount() {

		DatabaseConnectionHelper dch = ObjectFactory.getDBHelper();
		List<LocationData> locationDataList = new ArrayList<>();
		try (CallableStatement cstmt = dch.masterDS.getConnection().prepareCall("{call getCityCount()}"); ResultSet rs = cstmt.executeQuery();) {
			while (rs.next()) {
				locationDataList.add(setLocationData(rs, true, true));
			}

			try (CallableStatement cstmt1 = dch.masterDS.getConnection().prepareCall("{call getCircleCount()}");
					ResultSet rs1 = cstmt1.executeQuery();) {
				while (rs1.next()) {
					locationDataList.add(setLocationData(rs1, true, false));
				}
			}

			try (CallableStatement cstmt1 = dch.masterDS.getConnection().prepareCall("{call getRegionCount()}");
					ResultSet rs1 = cstmt1.executeQuery();) {
				while (rs1.next()) {
					locationDataList.add(setLocationData(rs1, false, false));
				}
			}
		} catch (SQLException e) {
			org.apache.log4j.Logger.getLogger(DashboardHelper.class).error("Exception while retrieving the location count deatails :", e);
		}
		return locationDataList;
	}

	/**
	 * Fills the location object with data from the db
	 * @param rs - resultset with location data
	 * @return LocationData object
	 * @throws SQLException - If unable to retrieve data from the resultset
	 */
	private LocationData setLocationData(ResultSet rs, boolean isCircle, boolean isCity) throws SQLException {
		LocationData ld = new LocationData();

		ld.setRegionId(rs.getInt("region_id"));
		ld.setRegionName(rs.getString("region"));
		if (isCircle) {
			ld.setCircleId(rs.getInt("circle_id"));
			ld.setCircleName(rs.getString("circle"));
		}
		if (isCity) {
			ld.setCityId(rs.getInt("city_id"));
			ld.setCityName(rs.getString("city"));
		}
		ld.setCandidateCount(rs.getInt("candidateCount"));
		return ld;
	}

	/**
	 * Retrieves the count of candidates for each role
	 * @param regionId - region id : 0 for ALL
	 * @param circleId - circle id : 0 for ALL
	 * @return Map of role id and corresponding count
	 */
	public List<Role> getRoleCount(int regionId, int circleId) {
		DatabaseConnectionHelper dch = ObjectFactory.getDBHelper();
		org.apache.log4j.Logger.getLogger(DashboardHelper.class).debug("HashMap for role count created");
		List<Role> roleCountList = new ArrayList<>();
		try (CallableStatement cstmt = dch.masterDS.getConnection().prepareCall("{call getRoleCount(?,?)}")) {

			// send regionId and circleId as input to the procedure

			cstmt.setInt("regionId", regionId);
			cstmt.setInt("circleId", circleId);
			try (ResultSet rs = cstmt.executeQuery()) {
				while (rs.next()) {
					// fill the role object with role id,role and candidate count
					Role r = setRoleDetails(rs);

					// fill the roleCountList
					roleCountList.add(r);
				}
			}

		} catch (SQLException e) {
			org.apache.log4j.Logger.getLogger(DashboardHelper.class).error("Exeption in retrieving the role count details :", e);
		}

		return roleCountList;
	}

	/**
	 * Fills the Role object with 
	 * @param rs - resultset
	 * @return Role object
	 * @throws SQLException - If unable to retrieve data from the resultset
	 */
	private Role setRoleDetails(ResultSet rs) throws SQLException {
		Role r = new Role();
		r.setRole(rs.getString("role"));
		r.setRoleId(rs.getInt("role_id"));
		r.setCandidateCount(rs.getInt("candidate_count"));
		return r;
	}
}
