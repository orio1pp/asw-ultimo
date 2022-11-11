package com.example.demo.Reply;

import javax.persistence.*;

@Entity
@Table(name = "reply")
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column
    private Long id;
    @Column
    private Long commentaryId;

    public Reply(Long comenntaryId) {
        this.id = id;
        this.commentaryId = comenntaryId;
    }
    public Reply(){
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getComenntaryId() {
        return commentaryId;
    }

    public void setComenntaryId(Long comenntaryId) {
        this.commentaryId = comenntaryId;
    }

}
