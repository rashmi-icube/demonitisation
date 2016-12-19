package org.icube.candidate;

import java.util.ArrayList;
import java.util.List;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.icube.axis.ObjectFactory;
import org.icube.helper.DatabaseConnectionHelper;

public class CandidateHelper {

	/**
	 * Retrieves the candidate list according to the selected filters (region, circle, city, role) 
	 * @param regionId - region id : 0 for ALL
	 * @param circleId - circle id : 0 for ALL
	 * @param cityId - city id : 0 for ALL
	 * @param roleId - role id : 0 for ALL
	 * @return List of Candidate objects
	 */
	public List<Candidate> getCandidateListByFilter(int regionId, int circleId,
			int cityId, int roleId) {
		DatabaseConnectionHelper dch = ObjectFactory.getDBHelper();
		List<Candidate> candidateList = new ArrayList<>();
		
		//call the procedure from db to get the candidate details
		
		try (CallableStatement cstmt = dch.masterDS.getConnection()
				.prepareCall("{call getCandidateListByFilter(?,?,?,?}")) {
			
			//send the specific id's as input to the procedure
			
			cstmt.setInt("region_id", regionId);
			cstmt.setInt("circle_id", circleId);
			cstmt.setInt("city_id", cityId);
			cstmt.setInt("role_id", roleId);
			try (ResultSet rs = cstmt.executeQuery()) {
				while (rs.next()) {
					
					//fill the candidate details
					Candidate c = setCandidateDetails(rs);
					
					//add the candidate object to the candidate list
					candidateList.add(c);
				}
			}
		} catch (SQLException e) {
			org.apache.log4j.Logger.getLogger(CandidateHelper.class).error(
					"Exceptiopn while retrieving the candidate details :", e);
		}

		return new ArrayList<Candidate>();
	}

	/**
	 * Fills the Candidate object
	 * @param rs - resultset
	 * @return the filled Candidate object
	 * @throws SQLException - if unable to retrieve details from the resultset
	 */
	private Candidate setCandidateDetails(ResultSet rs) throws SQLException {
		Candidate c = new Candidate();
		c.setName(rs.getString("cand_name"));
		c.setEmailId(rs.getString("email_id"));
		c.setMobileNumber(rs.getString("mob_no"));
		c.setRole(rs.getString("role_id"));
		c.setCity(rs.getString("city_id"));
		return c;
	}
}
