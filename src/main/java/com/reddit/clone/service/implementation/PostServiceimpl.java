package com.reddit.clone.service.implementation;

import com.reddit.clone.dto.ShowPostDto;
import com.reddit.clone.dto.TextPostDto;
import com.reddit.clone.model.Post;
import com.reddit.clone.model.Subreddit;
import com.reddit.clone.model.User;
import com.reddit.clone.model.Vote;
import com.reddit.clone.repository.PostRepository;
import com.reddit.clone.repository.SubredditRepository;
import com.reddit.clone.repository.repositoryImpl.PostRepositoryImpl;
import com.reddit.clone.service.PostService;
import com.reddit.clone.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PostServiceimpl implements PostService {


    private PostRepository postRepository;
    private SubredditRepository subredditRepository;
    private VoteService voteService;

    public PostServiceimpl(PostRepository postRepository, SubredditRepository subredditRepository, VoteService voteService) {
        this.postRepository = postRepository;
        this.subredditRepository = subredditRepository;
        this.voteService = voteService;
    }

    @Override
    public Post save(Post post, TextPostDto textPostDto) {

        Subreddit subreddit = subredditRepository.findBycommunityName(textPostDto.getSubredditName());
        post.setSubreddit(subreddit);
        subreddit.getPost().add(post);
        return postRepository.save(post);
    }

    @Override
    public Post savePost(Post post, TextPostDto textPostDto) {
        return null;
    }

    @Override
    public List<Post> findAll() {
        return postRepository.findAll();

    }


    @Override
    public Post findByPostId(long postId) {
        return postRepository.findById(postId);
    }

    @Override
    public Optional<Post> findByPostId(Long postId) {
        return postRepository.findById(postId);
    }

    @Override
    public List<Post> findByUser(User user, Sort sort) {
        return postRepository.findByUser(user, sort);
    }

    @Override
    public List<Post> findSortedPosts(String sort) {
        switch (sort) {
            case "best":
                return postRepository.findBestSortedPosts();
            case "new":
                return postRepository.findNewSortedPosts();
            case "top":
                return postRepository.findTopSortedPosts();
            case "hot":
                return postRepository.findHotSortedPosts();
        }
        return postRepository.findHotSortedPosts();
    }

    @Override
    public List<ShowPostDto> findSortedAllPosts(String sort, User user) {

        switch (sort) {
            case "best":
                return getShowPostDtoList(postRepository.findBestSortedPosts(), user);
            case "new":
                return getShowPostDtoList(postRepository.findNewSortedPosts(), user);
            case "top":
                return getShowPostDtoList(postRepository.findTopSortedPosts(), user);
            case "hot":
                return getShowPostDtoList(postRepository.findHotSortedPosts(), user);
        }
        return getShowPostDtoList(postRepository.findHotSortedPosts(), user);
    }

    @Override
    public void delete(Post post) {
        postRepository.delete(post);
    }

    @Override
    public List<Post> findBySubreddit(List<Subreddit> subredditList) {
        return postRepository.findBySubredditIn(subredditList);
    }


    public List<ShowPostDto> getShowPostDtoList(List<Post> postList, User user) {

        List<ShowPostDto> showPostDtoList = new ArrayList<>();

        if (user != null) {
            Set<Post> savedPostList = user.getSavedPostList();
            Set<Subreddit> joinedCommunityList = user.getJoinedCommunitieList();

            for (Post post : postList) {
                Vote vote = voteService.findByPostAndUser(post, user);
                ShowPostDto showPostDto = new ShowPostDto(post);

                if (vote != null) {
                    showPostDto.setIsVoted(true);
                    showPostDto.getIsUpVote(vote.isUpVote());
                }

                if (savedPostList.contains(post)) {
                    showPostDto.setIsSaved(true);
                }

                if (joinedCommunityList.contains(post.getSubreddit())) {
                    showPostDto.setIsJoined(true);
                }

                showPostDtoList.add(showPostDto);
            }
        } else {

            for (Post post : postList) {
                ShowPostDto showPostDto = new ShowPostDto(post);
                showPostDtoList.add(showPostDto);
            }
        }

        return showPostDtoList;
    }

    public ShowPostDto getShowPostDto(Post post, User user) {
        ShowPostDto showPostDto = new ShowPostDto(post);

        if (user == null) {
            return showPostDto;
        }

        Vote vote = voteService.findByPostAndUser(post, user);
        if (vote != null) {
            showPostDto.setIsVoted(true);
            showPostDto.getIsUpVote(vote.isUpVote());
        }

        if (user.getSavedPostList().contains(post)) {
            showPostDto.setIsSaved(true);
        }

        if (user.getJoinedCommunitieList().contains(post.getSubreddit())) {
            showPostDto.setIsJoined(true);
        }

        return showPostDto;
    }

    private String getSortString(String sort) {
        String sortBy = null;

        switch (sort) {
            case "best":
                sortBy = "(post.upVoteCount/post.downVoteCount)";
                break;
            case "new":
                sortBy = "post.createdAt";
                break;
            case "top":
                sortBy = "(post.upVoteCount - post.downVoteCount)";
                break;
            case "hot":
                sortBy = "(post.upVoteCount*(EXTRACT(EPOCH FROM (NOW()::timestamp - post.createdAt)))";
                break;
            default:
                sortBy = "post.createdAt";
        }

        return sortBy;
    }
}
