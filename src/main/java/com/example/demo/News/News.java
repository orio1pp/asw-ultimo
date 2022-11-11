package com.example.demo.News;

import com.example.demo.Commentary.Comment;
import com.example.demo.User.User;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "news")
public class News {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column
    private Long itemId;
    @Column(unique = true)
    private String title;
    @Column
    private String page_;
    @Column
    private Integer points;
    @OneToOne
    @JoinColumn(name = "username", referencedColumnName = "username")
    private User username;
    @Column
    @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
    private String datePublished;
    @Column(unique = true)
    private String link;
    @Column
    private String text;
    @Column
    private String type;
    @OneToMany()
    private List<Comment> comments = new ArrayList<Comment>();
    @ManyToMany()
    private List<User> likedBy = new ArrayList<User>();

    public News() {

    }

    public News(String title, String page, User publisher, String link, String text) {
        this.title = title;
        this.page_ = page;
        this.points = 0;
        this.username = publisher;
        this.link = link;
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPage_() {
        return page_;
    }

    public void setPage_(String page) {
        this.page_ = page;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }

    public User getUsername() {
        return username;
    }

    public void setUsername(User publisher) {
        this.username = publisher;
    }

    public String getDatePublished() {
        return datePublished;
    }

    public void setDatePublished(String datePublished) {
        this.datePublished = datePublished;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void addComment(Comment comment) {
        this.comments.add(comment);
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<User> getLikedBy() {
        return likedBy;
    }

    public void setLikedBy(List<User> likedBy) {
        this.likedBy = likedBy;
    }

    public Integer getLikes() {
        return likedBy.size();
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
