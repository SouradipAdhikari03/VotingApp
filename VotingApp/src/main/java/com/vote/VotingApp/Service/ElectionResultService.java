package com.vote.VotingApp.Service;

import com.vote.VotingApp.Entity.Candidate;
import com.vote.VotingApp.Entity.ElectionResult;
import com.vote.VotingApp.Exception.ResourceNotFoundException;
import com.vote.VotingApp.Repo.CandidateRepo;
import com.vote.VotingApp.Repo.ElectionResultRepo;
import com.vote.VotingApp.Repo.VoteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ElectionResultService {
    private ElectionResultRepo electionResultRepo;
    private CandidateRepo candidateRepo;
    private VoteRepo voteRepo;
    @Autowired
    public ElectionResultService(ElectionResultRepo electionResultRepo, CandidateRepo candidateRepo, VoteRepo voteRepo) {
        this.electionResultRepo = electionResultRepo;
        this.candidateRepo = candidateRepo;
        this.voteRepo = voteRepo;
    }
    public ElectionResult declareResult(String electionName){
        Optional<ElectionResult> electionResult=electionResultRepo.findByElectionName(electionName);
        if (electionResult.isPresent()){
                return electionResult.get();
        }
        if (voteRepo.count()==0){
            throw new ResourceNotFoundException("Voting with Name "+electionName+" was not conducted !");
        }
        List<Candidate> allCandidate = candidateRepo.findAllByOrderByVoteCountDesc();
        if (allCandidate.isEmpty()){
            throw new ResourceNotFoundException("No Candidates Available");
        }
        Candidate winningCandidate= allCandidate.getFirst();
        int totalVote= 0;
        for (Candidate candidate: allCandidate){
            totalVote+=candidate.getVoteCount();
        }
        ElectionResult electionResult1=new ElectionResult();
        electionResult1.setElectionName(electionName);
        electionResult1.setWinner(winningCandidate);
        electionResult1.setTotalVotes(totalVote);
        electionResultRepo.save(electionResult1);
        return electionResult1;
    }
    public List<ElectionResult> getAllElectionResult(){
        return electionResultRepo.findAll();
    }
}
