package com.hjj.my.app161015.message;

import java.util.ArrayList;

/**
 * Created by 1027 on 2016-10-29.
 */
//2번으로 작성
public interface MessageService {
    // CREATE
    public void write(MessageDTO member);   //INSERT
    // READ
    public ArrayList<MessageDTO> getList();
    public ArrayList<MessageDTO> getListByID(String id);
    public MessageDTO getMessage(int seq);
    public int count();
    // UPDATE
    //public void update(MessageDTO member);  --보낸거라 문자 수정 안되게

    //DELETE
    public void deleteMessage(int seq);  //
}
