package com.vote.VotingApp.Service;

import com.vote.VotingApp.Entity.Candidate;
import com.vote.VotingApp.Entity.Vote;
import com.vote.VotingApp.Entity.Voter;
import com.vote.VotingApp.Exception.ResourceNotFoundException;
import com.vote.VotingApp.Exception.VoteNotAllowedException;
import com.vote.VotingApp.Repo.CandidateRepo;
import com.vote.VotingApp.Repo.VoteRepo;
import com.vote.VotingApp.Repo.VoterRepo;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class VotingService {
    private CandidateRepo candidateRepo;
    private VoterRepo voterRepo;
    private VoteRepo voteRepo;

    public VotingService(CandidateRepo candidateRepo, VoterRepo voterRepo,VoteRepo voteRepo) {
        this.candidateRepo = candidateRepo;
        this.voterRepo = voterRepo;
        this.voteRepo=voteRepo;
    }
    @Transactional
    public Vote castVote(Long voterId,Long candidateId){
        if (!voterRepo.existsById(voterId)){
            throw new ResourceNotFoundException("Voter with id: "+voterId+" does not exists!");
        }
        if (!candidateRepo.existsById(candidateId)){
            throw new ResourceNotFoundException("Candidate with id: "+candidateId+" does not exists!");
        }
        Voter voter=voterRepo.findById(voterId).get();
        if (voter.isHasVoted()){
            throw new VoteNotAllowedException("Vote already completed by Voter Id: "+voterId);
        }
        Candidate candidate=candidateRepo.findById(candidateId).get();
        candidate.setVoteCount(candidate.getVoteCount()+1);
        candidateRepo.save(candidate);

        Vote vote=new Vote();
        vote.setCandidate(candidate);
        vote.setVoter(voter);
//        voteRepo.save(vote);
        voter.setVote(vote);
        voter.setHasVoted(true);
        voterRepo.save(voter);
        return vote;
    }
    public List<Vote> getAllVotes(){
        return voteRepo.findAll();
    }
}
