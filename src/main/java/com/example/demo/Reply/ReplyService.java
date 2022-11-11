package com.example.demo.Reply;

import org.springframework.stereotype.Service;

@Service
public class ReplyService {
    private final ReplyRepository replyRepository;

    public ReplyService(ReplyRepository replyRepository) {
        this.replyRepository = replyRepository;
    }

    public void setNewReply(Reply reply){
        replyRepository.save(reply);
    }
}
