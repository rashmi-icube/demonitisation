package org.icube.helper;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.icube.axis.ObjectFactory;

public class MasterListHelper {

	/**
	 * Retrieves the region master list
	 * @return Map of region id and region name
	 */
	public Map<Integer, String> getRegionMasterList() {
		DatabaseConnectionHelper dch = ObjectFactory.getDBHelper();
		org.apache.log4j.Logger.getLogger(MasterListHelper.class).debug(
				"HashMap for region created");
		Map<Integer, String> regionMasterList = new HashMap<>();
		
		//call the procedure to get the region master list
		try (CallableStatement cstmt = dch.masterDS.getConnection()
				.prepareCall("{call getRegionMasterList()}");
				ResultSet rs = cstmt.executeQuery()) {
			while (rs.next()) {
				regionMasterList.put(rs.getInt("region_id"),
						rs.getString("region"));
			}

		} catch (SQLException e) {
			org.apache.log4j.Logger.getLogger(MasterListHelper.class).error(
					"Exception in retrieving the region master list :", e);
		}
		return regionMasterList;
	}

	/**
	 * Retrieves the circle master list
	 * @return Map of circle id and circle name
	 */
	public Map<Integer, String> getCircleMasterList() {
		DatabaseConnectionHelper dch = ObjectFactory.getDBHelper();
		org.apache.log4j.Logger.getLogger(MasterListHelper.class).debug(
				"HashMap for circle created");
		Map<Integer, String> circleMasterList = new HashMap<>();
		//call the procedure to get the circle master list
		try (CallableStatement cstmt = dch.masterDS.getConnection()
				.prepareCall("{call getCircleMasterList()}");
				ResultSet rs = cstmt.executeQuery()) {
			while (rs.next()) {
				circleMasterList.put(rs.getInt("circle_id"),
						rs.getString("circle"));
			}

		} catch (SQLException e) {
			org.apache.log4j.Logger.getLogger(MasterListHelper.class).error(
					"Exception in retrieving the circle master list :", e);
		}
		circleMasterList.put(0, "ALL");
		return circleMasterList;
	}

	/**
	 * Retrieves the city master list
	 * @return Map of city id and city name
	 */
	public Map<Integer, String> getCityMasterList() {
		DatabaseConnectionHelper dch = ObjectFactory.getDBHelper();
		org.apache.log4j.Logger.getLogger(MasterListHelper.class).debug(
				"HashMap for city created");
		Map<Integer, String> cityMasterList = new HashMap<>();
		//call the procedure to get the city master list
		try (CallableStatement cstmt = dch.masterDS.getConnection()
				.prepareCall("{call getCityMasterList()}");
				ResultSet rs = cstmt.executeQuery()) {
			while (rs.next()) {
				cityMasterList.put(rs.getInt("city_id"), rs.getString("city"));
			}

		} catch (SQLException e) {
			org.apache.log4j.Logger.getLogger(MasterListHelper.class).error(
					"Exception in retrieving the city master list :", e);
		}
		cityMasterList.put(0, "ALL");
		return cityMasterList;
	}

	/**
	 * Retrieves the role master list
	 * @return Map of role id and role name
	 */
	public Map<Integer, String> getRoleMasterList() {
		DatabaseConnectionHelper dch = ObjectFactory.getDBHelper();
		org.apache.log4j.Logger.getLogger(MasterListHelper.class).debug(
				"HashMap for role created");
		Map<Integer, String> roleMasterList = new HashMap<>();
		//call the procedure to get the role master list
		try (CallableStatement cstmt = dch.masterDS.getConnection()
				.prepareCall("{call getRoleMasterList()}");
				ResultSet rs = cstmt.executeQuery()) {
			while (rs.next()) {
				roleMasterList.put(rs.getInt("role_id"), rs.getString("role"));
			}

		} catch (SQLException e) {
			org.apache.log4j.Logger.getLogger(MasterListHelper.class).error(
					"Exception in retrieving the role master list :", e);
		}
		roleMasterList.put(0, "ALL");
		return roleMasterList;
	}

}
