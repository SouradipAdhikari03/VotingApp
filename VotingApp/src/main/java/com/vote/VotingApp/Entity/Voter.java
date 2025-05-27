package com.vote.VotingApp.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
@Entity
@Data
public class Voter {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long voterId;

    @NotBlank(message = "Name is required!")
    private String voterName;
    @NotBlank(message = "Email is required!")
    @Email(message = "Invalid Email format!")
    private String voterEmail;

    private boolean hasVoted=false;
    @OneToOne(mappedBy = "voter", cascade = CascadeType.ALL)
    @JsonIgnore
    private Vote vote;
}
