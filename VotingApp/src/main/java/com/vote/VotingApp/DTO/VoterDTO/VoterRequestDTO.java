package com.vote.VotingApp.DTO.VoterDTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class VoterRequestDTO {
    @NotBlank(message = "Name is required!")
    private String voterName;
    @NotBlank(message = "Email is required!")
    @Email(message = "Invalid Email format!")
    private String voterEmail;
    @NotBlank(message = "Password is required!")
    private String voterPassword;
    @NotBlank(message = "Please confirm your password !")
    private String voterConfirmPassword;
}
