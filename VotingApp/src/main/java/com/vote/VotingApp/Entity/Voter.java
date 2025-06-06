package com.vote.VotingApp.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Voter {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long voterId;

    @NotBlank(message = "Name is required!")
    private String voterName;
    @NotBlank(message = "Email is required!")
    @Email(message = "Invalid Email format!")
    private String voterEmail;
    @NotBlank(message = "Password is required!")
    private String voterPassword;

    private boolean hasVoted=false;
    @OneToOne(mappedBy = "voter", cascade = CascadeType.ALL)
    @JsonIgnore
    private Vote vote;
}
