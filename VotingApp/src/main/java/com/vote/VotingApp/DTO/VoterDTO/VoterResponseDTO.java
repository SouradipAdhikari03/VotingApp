package com.vote.VotingApp.DTO.VoterDTO;

import lombok.Data;
@Data
public class VoterResponseDTO {
    private Long voterId;
    private String voterName;
    private String voterEmail;
}
