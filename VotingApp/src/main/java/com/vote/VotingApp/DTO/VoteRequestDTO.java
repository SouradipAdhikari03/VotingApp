package com.vote.VotingApp.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.Data;


@Data
public class VoteRequestDTO {
    @NotNull(message = "Voter Id required")
    public Long voterId;
    @NotNull(message = "Candidate Id required")
    public Long candidateId;
}
