package dao;

import java.sql.SQLException;
import java.util.List;

import pojo.Candidate;

public interface Candidate_Doa {

	List<Candidate> getAllCandidates()throws SQLException;
}
