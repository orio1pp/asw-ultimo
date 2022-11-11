package com.example.demo.Commentary;

import com.example.demo.Reply.Reply;
import com.example.demo.User.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user", referencedColumnName = "username", nullable = true)
    private User user;
    @Column
    @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
    private String time;
    @Column
    private String body;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Reply> replies;

    @ManyToMany()
    private List<User> likedBy = new ArrayList<User>();

    public Comment() {}

    public Comment(Long id, User user, String time, String body) {
        this.id = id;
        this.user = user;
        this.time = time;
        this.body = body;
        this.replies = new ArrayList<Reply>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long itemId) {
        this.id = itemId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public List<Reply> getReplies() {
        return this.replies;
    }

    public void setComments(List<Reply> comments) {
        this.replies = comments;
    }

    public void addComments(Reply reply) {
        this.replies.add(reply);
    }

    public List<User> getLikedBy() {
        return likedBy;
    }

    public void setLikedBy(List<User> likedBy) {
        this.likedBy = likedBy;
    }

    public void like(User user) {
        int pos = -1;
        for (int i = 0; i < this.likedBy.size() && pos == -1; ++i) {
            if (this.likedBy.get(i).getUsername().equals(user.getUsername()))
                pos = i;
        }
        if (pos == -1) {
            this.likedBy.add(user);
        }
        else {
            this.likedBy.remove(pos);
        }
    }

}
