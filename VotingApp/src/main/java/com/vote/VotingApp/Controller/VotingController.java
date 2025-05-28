package com.vote.VotingApp.Controller;

import com.vote.VotingApp.DTO.VoteRequestDTO;
import com.vote.VotingApp.DTO.VoteResponseDTO;
import com.vote.VotingApp.Entity.Vote;
import com.vote.VotingApp.Entity.Voter;
import com.vote.VotingApp.Service.VotingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/votes")
@CrossOrigin
public class VotingController {
    private VotingService votingService;
@Autowired
    public VotingController(VotingService votingService) {
        this.votingService = votingService;
    }
    @PostMapping("/cast")
    public ResponseEntity<VoteResponseDTO> castVote(@Valid @RequestBody VoteRequestDTO voteRequestDTO){
        Vote vote=votingService.castVote(voteRequestDTO.getVoterId(),voteRequestDTO.getCandidateId());
        VoteResponseDTO voteResponseDTO=  new VoteResponseDTO("Vote casted successfully",true,vote.getVoterId(),vote.getCandidateId());
        return new ResponseEntity<>(voteResponseDTO, HttpStatus.CREATED);
    }
    @GetMapping("/all")
    public ResponseEntity<List<Vote>> getAllVotes(){
    return new ResponseEntity<>(votingService.getAllVotes(),HttpStatus.OK);
    }
}
