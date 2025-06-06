package com.vote.VotingApp.Repo;

import com.vote.VotingApp.Entity.Voter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<Voter,Long> {
    Voter findByVoterEmail(String voterEmail);
}
