package com.vote.VotingApp.Service;

import com.vote.VotingApp.DTO.VoterDTO;
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
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Objects;

@Service
public class VoterService {
    public VoterRepo voterRepo;
    public CandidateRepo candidateRepo;
    @Autowired
    public VoterService(VoterRepo voterRepo, CandidateRepo candidateRepo) {
        this.voterRepo = voterRepo;
        this.candidateRepo = candidateRepo;
    }
    public Voter registerVoter(VoterDTO voterDTO){
        if(voterRepo.existsByVoterEmail(voterDTO.getVoterEmail())){
            throw new DuplicateResourceException("Voter with email: "+voterDTO.getVoterEmail()+" already exists");
        }
        Voter voter=new Voter();
        voter.setVoterName(voterDTO.getVoterName());
        voter.setVoterEmail(voterDTO.getVoterEmail());
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
        if (voterArg.getVoterName()!=null){
            voter.setVoterName(voterArg.getVoterName());
        }if(voterArg.getVoterEmail()!=null){
            voter.setVoterEmail(voterArg.getVoterEmail());
        }
        else{
            throw new ResourceNotFoundException("Voter Name or Voter Email needed");
        }
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
