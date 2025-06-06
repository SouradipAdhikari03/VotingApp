package com.vote.VotingApp.Service;

import com.vote.VotingApp.Entity.UserPrincipal;
import com.vote.VotingApp.Entity.Voter;
import com.vote.VotingApp.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String voterEmail) throws UsernameNotFoundException {
        Voter voter=userRepo.findByVoterEmail(voterEmail);
        if (voter==null){
            throw new UsernameNotFoundException("Voter with Email: "+voterEmail+" does not exist");
        }
        return new UserPrincipal(voter);
    }
}
