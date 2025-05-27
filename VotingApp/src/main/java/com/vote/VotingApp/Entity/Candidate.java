package com.vote.VotingApp.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Candidate {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long candidateId;

    @NotBlank(message = "Name is required!")
    private String candidateName;
    @NotBlank(message = "Email is required!")
    private String candidateParty;

    private int voteCount=0;

    @OneToMany(mappedBy = "candidate", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Vote> vote;
}
