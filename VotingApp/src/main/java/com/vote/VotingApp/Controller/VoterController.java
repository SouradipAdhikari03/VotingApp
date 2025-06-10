package com.vote.VotingApp.Controller;

import com.vote.VotingApp.DTO.VoterDTO.VoterLoginDTO;
import com.vote.VotingApp.DTO.VoterDTO.VoterRequestDTO;
import com.vote.VotingApp.DTO.VoterDTO.VoterResponseDTO;
import com.vote.VotingApp.Entity.Voter;
import com.vote.VotingApp.Service.JWTService;
import com.vote.VotingApp.Service.VoterService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/voters")
@CrossOrigin
public class VoterController {
    @Autowired
     private VoterService voterService;
//    public VoterController(VoterService voterService) {
//        this.voterService = voterService;
//    }

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTService jwtService;

    @PostMapping("/login")
    public String login(@RequestBody @Valid VoterLoginDTO voterLoginDTO){
        Authentication authentication=authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(voterLoginDTO.getVoterEmail(),voterLoginDTO.getVoterPassword()));
        if (authentication.isAuthenticated()){
            return jwtService.generateToken(voterLoginDTO.getVoterEmail());
        }else{
            return "wrong credentials !";
        }

    }

    @PostMapping("/register")
    public ResponseEntity<VoterResponseDTO> RegisterVoter(@RequestBody VoterRequestDTO voterDTO){
        Voter savedVoter=voterService.registerVoter(voterDTO);
        VoterResponseDTO  voterRequestDTO=new VoterResponseDTO();
        voterRequestDTO.setVoterName(voterDTO.getVoterName());
        voterRequestDTO.setVoterEmail(voterDTO.getVoterEmail());
        voterRequestDTO.setVoterId(savedVoter.getVoterId());
        return new ResponseEntity<>(voterRequestDTO, HttpStatus.CREATED);
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
