package com.vote.VotingApp.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Data
public class ElectionResult {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long electionResultId;
    @NotBlank(message = "Election name is required!")
    private String electionName;
    private int totalVotes;
    @OneToOne
    @JoinColumn(name = "winner_id")
    @JsonIgnore
    private Candidate winner;

    @JsonProperty("winnerId")
    public Long getWinnerId(){
        return winner!=null?winner.getCandidateId():null;
    }
}
