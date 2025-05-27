package com.vote.VotingApp.Exception;

public class VoteNotAllowedException extends RuntimeException{

    public VoteNotAllowedException(String message) {
        super(message);
    }
}
