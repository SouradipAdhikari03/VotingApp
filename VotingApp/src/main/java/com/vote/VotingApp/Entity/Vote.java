package com.vote.VotingApp.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Vote {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long voteId;

    @OneToOne
    @JoinColumn(name = "voterId", unique = true)
    private Voter voter;

    @ManyToOne
    @JoinColumn(name = "candidateId")
    private Candidate candidate;

    @JsonProperty("VoterID")
    public Long getVoterId(){
        return voter!=null?voter.getVoterId():null;
    }

    @JsonProperty("CandidateID")
    public Long getCandidateId(){
        return candidate!=null?candidate.getCandidateId():null;
    }

}
