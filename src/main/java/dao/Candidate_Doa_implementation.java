package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import pojo.Candidate;
import utils.DbConnection;

public class Candidate_Doa_implementation implements Candidate_Doa {

	private Connection cn;
	private PreparedStatement pst1;

	
	public Candidate_Doa_implementation() throws SQLException{
		super();
	
		
	}


	@Override
	public List<Candidate> getAllCandidates() throws SQLException {

		return null;
	}

}
