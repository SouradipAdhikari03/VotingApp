package com.vote.VotingApp.DTO.ElectionResultDTO;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ElectionResultRequestDTO {
    @NotNull(message = "Election Name is needed!")
    private String electionName;
}
