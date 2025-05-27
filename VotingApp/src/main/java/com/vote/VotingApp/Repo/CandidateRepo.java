package com.vote.VotingApp.Repo;

import com.vote.VotingApp.Entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CandidateRepo extends JpaRepository<Candidate,Long> {
    List<Candidate> findAllByOrderByVoteCountDesc();
    boolean existsByCandidateParty(String candidateParty);
}
