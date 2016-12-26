package org.icube.test.candidate;

import java.util.List;

import org.icube.candidate.Candidate;
import org.icube.candidate.CandidateHelper;
import org.junit.Test;

public class CandidateHelperTest {
	
	@Test
	public void testGetCandidateListByFilter(){
		CandidateHelper ch = new CandidateHelper();
		List<Candidate> candidateList = ch.getCandidateListByFilter(0, 0, 0, 0);
		for (Candidate c : candidateList){
			System.out.println(c.getName());
			System.out.println(c.getCandidateId());
			System.out.println(c.getEmailId());
			System.out.println(c.getMobileNumber());
			System.out.println(c.getCity());
			System.out.println(c.getRole());
			
		}
	}

}
