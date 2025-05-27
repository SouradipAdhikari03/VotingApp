package com.vote.VotingApp.Repo;

import com.vote.VotingApp.Entity.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoteRepo extends JpaRepository<Vote,Long> {
}
