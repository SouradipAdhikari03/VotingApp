package com.vote.VotingApp.Service;

import com.vote.VotingApp.Entity.Candidate;
import com.vote.VotingApp.Entity.Vote;
import com.vote.VotingApp.Entity.Voter;
import com.vote.VotingApp.Exception.DuplicateResourceException;
import com.vote.VotingApp.Exception.ResourceNotFoundException;
import com.vote.VotingApp.Repo.CandidateRepo;
import com.vote.VotingApp.Repo.VoterRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoterService {
    public VoterRepo voterRepo;
    public CandidateRepo candidateRepo;
    @Autowired
    public VoterService(VoterRepo voterRepo, CandidateRepo candidateRepo) {
        this.voterRepo = voterRepo;
        this.candidateRepo = candidateRepo;
    }
    public Voter registerVoter(Voter voter){
        if(voterRepo.existsByVoterEmail(voter.getVoterEmail())){
            throw new DuplicateResourceException("Voter with email: "+voter.getVoterEmail()+" already exists");
        }
        return voterRepo.save(voter);
    }
    public List<Voter> getAllVoter(){
        return voterRepo.findAll();
    }
    public Voter getVoterById(Long voterId){
        Voter voter=voterRepo.findById(voterId).orElse(null);
        if (voter==null){
            throw new ResourceNotFoundException("Voter with Id: "+voterId+" not present!");
        }
        return voter;
    }
    public Voter updateVoter(Long voterId, Voter voterArg){
        Voter voter=voterRepo.findById(voterId).orElse(null);
        if (voter==null){
            throw new ResourceNotFoundException("Voter with Id: "+voterId+" not present!");
        }
        voter.setVoterName(voterArg.getVoterName());
        voter.setVoterEmail(voterArg.getVoterEmail());
        return voterRepo.save(voter);
    }
    @Transactional
    public void deleteVoter(Long voterId){
        Voter voter=voterRepo.findById(voterId).orElse(null);
        if (voter==null){
            throw new ResourceNotFoundException("Voter with Id: "+voterId+" not present!");
        }
        Vote vote=voter.getVote();
        if (vote!=null){
            Candidate candidate=vote.getCandidate();
            candidate.setVoteCount(candidate.getVoteCount()-1);
            candidateRepo.save(candidate);
        }voterRepo.delete(voter);
    }
}
