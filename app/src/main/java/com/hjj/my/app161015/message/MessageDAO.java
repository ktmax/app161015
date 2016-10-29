package com.hjj.my.app161015.message;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import static com.hjj.my.app161015.util.Constants.DB_NAME;
import static com.hjj.my.app161015.util.Constants.DB_VERSION;

//copy 해서 넣는다.

/**
 * Created by 1027 on 2016-10-29.
 */

public class MessageDAO extends SQLiteOpenHelper {
    public static final String SEQ= "_id" ; //_id 하면 자동으로 증가
    public static final String RECEIVER  = "receiver" ;
    public static final String CONTENT   = "content" ;
    public static final String SEND_DATE = "send_date" ;

    public MessageDAO(Context context) {
        super(context,DB_NAME,null,DB_VERSION);
        this.getWritableDatabase();// 입력가능한 DB로 만들어라
        Log.d("메세지DB가 만들어지면 이 글이 보일것임.", "성공!!");

    }
    //모든 테이블은 처음에 만들어서 실행되는 곳에 넣어야 한다 여기서 onclick 을 만들면 난리 난다. 에러 나서 넣어 놨을뿐.
    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void write(MessageDTO member) {

    }


    public ArrayList<MessageDTO> getList() {
        return null;
    }


    public ArrayList<MessageDTO> getListByID(String id) {
        String sql = "select receiver, content,  writer,   sendDate,    seq \n" +
                " from member a \n" +
                "join message b \n" +
                "on a.id = b.id" +
                "where id = " + id;
        String sql2 = "select receiver, content,  writer,   sendDate,    seq \n" +
                " from member a \n" +
                "where id " +id;

        return null;
    }


    public MessageDTO getMessage(int seq) {
        return null;
    }


    public int count() {
        return 0;
    }


    public void deleteMessage(int seq) {

    }


}
