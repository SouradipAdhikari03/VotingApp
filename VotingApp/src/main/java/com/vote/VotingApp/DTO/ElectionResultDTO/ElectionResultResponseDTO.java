package com.vote.VotingApp.DTO.ElectionResultDTO;

import lombok.Data;

@Data
public class ElectionResultResponseDTO {
    private String electionName;
    private int voteCount;
    private String winner;
    private int winnerVoteCount;
}
