package com.reddit.clone.service.implementation;

import com.reddit.clone.model.Post;
import com.reddit.clone.model.User;
import com.reddit.clone.model.Vote;
import com.reddit.clone.repository.VoteRepository;
import com.reddit.clone.service.VoteService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VoteServiceimpl implements VoteService {

    private VoteRepository voteRepository;

    public VoteServiceimpl(VoteRepository voteRepository) {
        this.voteRepository = voteRepository;
    }

    @Override
    public void save(Vote vote) {
        voteRepository.save(vote);
    }

    @Override
    public Vote findByPostAndUser(Post post, User user) {
        return voteRepository.findByPostAndUser(post, user);
    }

    @Override
    public void removeByPostAndUser(Post post, User user) {
//        return voteRepository.removeByPostAndUser(post, user);
         voteRepository.removeByPostAndUser(post, user);
    }

    @Override
    public List<Post> findUpVotesByUser(User user) {
        return voteRepository.findUpVotesByUser(user);
    }

    @Override
    public List<Post> findDownVotesByUser(User user) {
        return voteRepository.findDownVotesByUser(user);
    }

    @Override
    public void deleteAll(Iterable< Vote> votes) {
        voteRepository.deleteInBatch(votes);
    }
}
