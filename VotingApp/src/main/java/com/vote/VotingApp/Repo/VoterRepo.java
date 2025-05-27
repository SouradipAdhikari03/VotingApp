package com.vote.VotingApp.Repo;

import com.vote.VotingApp.Entity.Voter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoterRepo extends JpaRepository<Voter, Long> {
    boolean existsByVoterEmail(String email);
}
