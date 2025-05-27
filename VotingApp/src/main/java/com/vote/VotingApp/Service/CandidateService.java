package com.vote.VotingApp.Service;

import com.vote.VotingApp.Entity.Candidate;
import com.vote.VotingApp.Entity.Vote;
import com.vote.VotingApp.Exception.DuplicateResourceException;
import com.vote.VotingApp.Exception.ResourceNotFoundException;
import com.vote.VotingApp.Repo.CandidateRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CandidateService {
    public CandidateRepo candidateRepo;
    @Autowired
    public CandidateService(CandidateRepo candidateRepo) {
        this.candidateRepo = candidateRepo;
    }
    public Candidate registerCandidate(Candidate candidate){
        if (candidateRepo.existsByCandidateParty(candidate.getCandidateParty())){
            throw new DuplicateResourceException("Candidate with party "+candidate.getCandidateParty()+" already exists");
        }
        return candidateRepo.save(candidate);

    }
    public List<Candidate> getAllCandidate(){
        return candidateRepo.findAll();
    }
    public Candidate getCandidateById(Long id){
        Candidate candidate=candidateRepo.findById(id).orElse(null);
        if (candidate==null){
            throw new ResourceNotFoundException("Candidate with id: "+id+" not present");
        }
        return candidate;
    }
    public Candidate updateCandidate(Long id, Candidate candidate){
        Candidate updatedCandidate =getCandidateById(id);
        if (candidate.getCandidateName() !=null){
            updatedCandidate.setCandidateName(candidate.getCandidateName());
        }if (candidate.getCandidateParty()!=null){
            updatedCandidate.setCandidateParty(candidate.getCandidateParty());
        }
        return candidateRepo.save(updatedCandidate);
    }
    public void deleteCandidate(Long id){
        Candidate candidate =getCandidateById(id);
        List<Vote> votes=candidate.getVote();
        for (Vote v: votes){
            v.setCandidate(null);
        }
        candidate.getVote().clear();
        candidateRepo.delete(candidate);
    }
}
