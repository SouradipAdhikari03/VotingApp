package com.vote.VotingApp.DTO.VoteDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VoteResponseDTO {
    public String message;
    public boolean success;
    public Long voterId;
    public Long candidateId;
}
