package com.vote.VotingApp.Exception;

public class DuplicateResourceException extends RuntimeException{

    public DuplicateResourceException(String message) {
        super(message);
    }
}
