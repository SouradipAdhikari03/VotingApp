package com.vote.VotingApp.Controller;

import com.vote.VotingApp.Entity.Candidate;
import com.vote.VotingApp.Service.CandidateService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/candidate")
@CrossOrigin
public class CandidateController {
    private CandidateService candidateService;
    @Autowired

    public CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }
    @PostMapping("/register")
    public ResponseEntity<Candidate> registerCandidate(@Valid @RequestBody Candidate candidate){
        Candidate addCandidate= candidateService.registerCandidate(candidate);
        return new ResponseEntity<>(addCandidate, HttpStatus.CREATED);
    }
    @GetMapping("/all")
    public ResponseEntity<List<Candidate>> getAllCandidate(){
        List<Candidate> candidates=candidateService.getAllCandidate();
        return new ResponseEntity<>(candidates,HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Candidate> getCandidateById(@Valid @PathVariable Long id){
        return new ResponseEntity<>(candidateService.getCandidateById(id),HttpStatus.OK);
    }
    @PutMapping ("/update/{id}")
    public ResponseEntity<Candidate> updateCandidateById(@Valid @PathVariable Long id,@RequestBody Candidate candidate){
        Candidate updatedCandidate=candidateService.updateCandidate(id,candidate);
        return new ResponseEntity<>(updatedCandidate,HttpStatus.ACCEPTED);
    }
    @DeleteMapping ("/delete/{id}")
    public ResponseEntity<String> deleteCandidateById(@Valid @PathVariable Long id){
        candidateService.deleteCandidate(id);
        return new ResponseEntity<>("Candidate with id:"+id+" is deleted",HttpStatus.OK);
    }
}
