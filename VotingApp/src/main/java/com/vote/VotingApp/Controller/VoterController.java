package com.vote.VotingApp.Controller;

import com.vote.VotingApp.Entity.Voter;
import com.vote.VotingApp.Service.VoterService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/voters")
@CrossOrigin
public class VoterController {
    private VoterService voterService;
    @Autowired
    public VoterController(VoterService voterService) {
        this.voterService = voterService;
    }
    @PostMapping("/register")
    public ResponseEntity<Voter> RegisterVoter(@RequestBody @Valid Voter voter){
        Voter savedVoter=voterService.registerVoter(voter);
        return new ResponseEntity<>(savedVoter, HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Voter> GetVoter(@PathVariable Long id){
        Voter getVoter=voterService.getVoterById(id);
        return new ResponseEntity<>(getVoter, HttpStatus.OK);
    }
    @GetMapping("/all")
    public ResponseEntity<List<Voter>> getAllVoter(){
        List<Voter> voters=voterService.getAllVoter();
        return new ResponseEntity<>(voters,HttpStatus.OK);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Voter> updateVoter(@PathVariable Long id, @RequestBody Voter voter){
        Voter updatedVoter =voterService.updateVoter(id,voter);
        return new ResponseEntity<>(updatedVoter,HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteVoter(@PathVariable Long id){
        voterService.deleteVoter(id);
        return new ResponseEntity<>("Voter with id: "+id+" deleted successfully",HttpStatus.OK);
    }
}
