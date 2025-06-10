package com.vote.VotingApp.DTO.VoterDTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
@Data
public class VoterLoginDTO {
    @NotBlank(message = "Email is required!")
    @Email(message = "Invalid Email format!")
    private String voterEmail;
    @NotBlank(message = "Password is required!")
    private String voterPassword;
}
