package com.example.demo.Commentary;

import com.example.demo.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class CommentController {
    @Autowired
    CommentService commentService;

    @GetMapping("commentlist/get")
    public List<Comment> getCommentList() {
        return commentService.getCommentList();
    }

    @GetMapping("comment/{id}")
    public CommentDTO getComment(@PathVariable("id") Long id) {
        return commentService.getComment(id);
    }

    @GetMapping("comment/user/{id}")
    public List<Comment> getUserComments(@PathVariable("id") String id) {
        return commentService.getUserComments(id);
    }

    @PutMapping("news/{id}/reply")
    public void addReply(@PathVariable("id") Long id, @RequestBody Comment comment) {
        commentService.addReply(id, comment);
    }
    @PutMapping("comment")
    public void addComment( @RequestBody Comment comment) {
        commentService.newComment(comment);
    }

    @PutMapping("comment/{id}/like")
    public void like(@PathVariable("id") Long id, @RequestBody User user) {
        commentService.like(id, user);
    }
}
