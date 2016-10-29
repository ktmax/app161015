package com.hjj.my.app161015.message;

import android.content.Context;

import java.util.ArrayList;

/**
 * Created by 1027 on 2016-10-29.
 */

public class MessageServiceImpl implements  MessageService{
    MessageDAO dao;
    public MessageServiceImpl(Context context) {
        dao =new MessageDAO(context);
    }

    @Override
    public void write(MessageDTO member) {

    }

    @Override
    public ArrayList<MessageDTO> getList() {
        return null;
    }

    @Override
    public ArrayList<MessageDTO> getListByID(String id) {
        return null;
    }

    @Override
    public MessageDTO getMessage(int seq) {
        return null;
    }

    @Override
    public int count() {
        return 0;
    }

    @Override
    public void deleteMessage(int seq) {

    }
}
