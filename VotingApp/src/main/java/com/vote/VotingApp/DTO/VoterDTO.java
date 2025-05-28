package com.vote.VotingApp.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class VoterDTO {
    @NotBlank(message = "Name is required!")
    private String voterName;
    @NotBlank(message = "Email is required!")
    @Email(message = "Invalid Email format!")
    private String voterEmail;
}
