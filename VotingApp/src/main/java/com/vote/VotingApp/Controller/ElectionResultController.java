package com.vote.VotingApp.Controller;

import com.vote.VotingApp.DTO.ElectionResultDTO.ElectionResultRequestDTO;
import com.vote.VotingApp.DTO.ElectionResultDTO.ElectionResultResponseDTO;
import com.vote.VotingApp.Entity.ElectionResult;
import com.vote.VotingApp.Service.ElectionResultService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/election-result")
@CrossOrigin

public class ElectionResultController {

    private ElectionResultService electionResultService;

    public ElectionResultController(ElectionResultService electionResultService) {
        this.electionResultService = electionResultService;
    }
    @PostMapping("/declare")
    public ResponseEntity<ElectionResultResponseDTO> declareResult(@RequestBody @Valid ElectionResultRequestDTO electionResultRequestDTO){
        ElectionResult electionResult=electionResultService.declareResult(electionResultRequestDTO.getElectionName());
        ElectionResultResponseDTO electionResultResponseDTO =new ElectionResultResponseDTO();
        electionResultResponseDTO.setElectionName(electionResult.getElectionName());
        electionResultResponseDTO.setWinner(electionResult.getWinner().getCandidateName());
        electionResultResponseDTO.setVoteCount(electionResult.getTotalVotes());
        electionResultResponseDTO.setWinnerVoteCount(electionResult.getWinner().getVoteCount());
        return ResponseEntity.ok(electionResultResponseDTO);

    }
    @GetMapping("/get-all")
    public ResponseEntity<List<ElectionResult>> getAllResults(){
        List<ElectionResult> electionResults=electionResultService.getAllElectionResult();
        return ResponseEntity.ok(electionResults);
    }
}
