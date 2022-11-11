package com.example.demo.Commentary;

import com.example.demo.News.News;
import com.example.demo.Reply.Reply;
import com.example.demo.User.HackNewsRepository;
import com.example.demo.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {
    CommentService() {}

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private HackNewsRepository userRepository;

    public List<Comment> getCommentList() {
        List<Comment> a = commentRepository.findAll(Sort.by(Sort.Direction.DESC, "time"));
        return a;
    }

    public CommentDTO getComment(Long id) {
        Comment comment = commentRepository.findById(id).get();
        CommentDTO commentDTO = new CommentDTO(comment.getId(), comment.getUser(), comment.getTime(), comment.getBody(), new ArrayList<CommentDTO>());
        List<Reply> replies = comment.getReplies();
        getReplies(comment.getReplies(), commentDTO.getReplies());
        return commentDTO;
    }

    private void getReplies(List<Reply> replies, List<CommentDTO> comments){
        if(replies.isEmpty())
            return;
        Comment comment;
        for(Reply reply : replies) {
            comment = commentRepository.findById(reply.getComenntaryId()).get();
            CommentDTO commentDTO = new CommentDTO(comment.getId(), comment.getUser(), comment.getTime(), comment.getBody(), new ArrayList<CommentDTO>());
            comments.add(commentDTO);
            getReplies(comment.getReplies(), commentDTO.getReplies());
        }

    }

    public List<Comment> getUserComments(String id) {
        userRepository.findById(id);
        List<Comment> comments = commentRepository.findAll();
        List<Comment> res = new ArrayList<>();
        for (Comment comment : comments) {
            if (comment.getUser().getUsername() == id)
                res.add(comment);
        }
        return res;
    }


    public void newComment(Comment comment) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String currentDateTime = LocalDateTime.now().format(formatter);
        comment.setTime(currentDateTime);
        commentRepository.save(comment);
    }

    public void addReply(Long id, Comment reply){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String currentDateTime = LocalDateTime.now().format(formatter);
        reply.setTime(currentDateTime);

        Comment comment = commentRepository.findById(id).get();

        commentRepository.save(reply);
        comment.addComments(new Reply(reply.getId()));
        commentRepository.save(comment);
    }


    public void like(Long id, User user) {
        Comment comment = commentRepository.findById(id).get();
        User us = userRepository.findUserByUsername(user.getUsername());
        comment.like(us);
        userRepository.save(us);
        commentRepository.save(comment);
    }
}
