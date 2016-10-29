package com.hjj.my.app161015.message;

/**
 * Created by 1027 on 2016-10-29.
 */

public class MessageDTO {
    private String receiver, content,  writer,   sendDate,    seq;
                                     //alt insert

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getSendDate() {
        return sendDate;
    }

    public void setSendDate(String sendDate) {
        this.sendDate = sendDate;
    }

    public String getSeq() {
        return seq;
    }

    public void setSeq(String seq) {
        this.seq = seq;
    }
    //받는사람              보낸사람    보낸일자   키설정



    //alt insert => tostring
    @Override
    public String toString() {
        return "MESSAGE{" +
                "받는이 :'" + receiver + '\'' +
                ", 내용 :'" + content + '\'' +
                ", 글쓴이 :'" + writer + '\'' +
                ", 보낸시간 :'" + sendDate + '\'' +
                ", 일련번호 :'" + seq + '\'' +
                '}';
    }
}
