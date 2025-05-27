package com.vote.VotingApp.Entity;

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
    private Candidate winner;
}
