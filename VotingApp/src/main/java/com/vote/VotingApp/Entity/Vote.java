package com.vote.VotingApp.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

//@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Vote {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long voteId;

    @OneToOne
    @JoinColumn(name = "voterId", unique = true)
    @JsonIgnore
    private Voter voter;

    @ManyToOne
    @JoinColumn(name = "candidateId")
    @JsonIgnore
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
