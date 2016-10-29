package com.hjj.my.app161015.Member;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import static com.hjj.my.app161015.util.Constants.DB_NAME;
import static com.hjj.my.app161015.util.Constants.DB_VERSION;

/**
 * Created by 1027 on 2016-10-15.
 */

public class MemberDAO extends SQLiteOpenHelper {
    //public static final String DB_NAME = "hanbit.db";
    //public static final int DB_VERSION =1 ;
    public static final String ID = "id";
    public static final String PW = "pw";
    public static final String NAME = "name";
    public static final String EMAIL = "email";
    public static final String ADDR = "addr";
    public static final String PHONE = "phone";
    public static final String PHOTO = "profileImg";
    public static final String TABLE_NAME = "member";
    public MemberDAO(Context context){
        //super(context,"hanbitdb3",null,1);
        super(context,DB_NAME,null,DB_VERSION);
        this.getWritableDatabase();// 입력가능한 DB로 만들어라
        Log.d("DB가 mda만들어지면 이 글이 보일것임.", "성공!!");
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
         /*String sql =
         "create table if not exists member("
                + "id         text primary key ,"
                + "pw         text,"
                + "name       text,"
                + "email      text,"
                + "addr       text,"
                + "phone      text,"
                + "profileImg text)";
        db.execSQL(sql); */
        db.execSQL( "create table if not exists member("
                + "id         text primary key ,"
                + "pw         text,"
                + "name       text,"
                + "email      text,"
                + "addr       text,"
                + "phone      text,"
                + "profileImg text)");


        db.execSQL("insert into member(id,pw,name,email,addr,phone,profileImg) "
                + "values ('hjj1','1','허중재1','hjj1@gamil.com','서울','010-1234-2222','default.jpg')");
        db.execSQL("insert into member(id,pw,name,email,addr,phone,profileImg) "
                + "values ('hjj2','1','허중재2','hjj1@gamil.com','서울','010-1234-2222','default.jpg')");
        db.execSQL("insert into member(id,pw,name,email,addr,phone,profileImg) "
                + "values ('hjj3','1','허중재3','hjj1@gamil.com','서울','010-1234-2222','default.jpg')");
        db.execSQL("insert into member(id,pw,name,email,addr,phone,profileImg) "
                + "values ('hjj4','1','허중재4','hjj1@gamil.com','서울','010-1234-2222','default.jpg')");
        db.execSQL("insert into member(id,pw,name,email,addr,phone,profileImg) "
                + "values ('hjj5','1','허중재5','hjj1@gamil.com','서울','010-1234-2222','default.jpg')");
        db.execSQL("insert into member(id,pw,name,email,addr,phone,profileImg) "
                + "values ('hjj10','1','허중재10','hjj1@gamil.com','37.5597680,126.9423080','010-5606-5101','default.jpg')");
        db.execSQL( "create table if not exists message("
                + "_id        INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + "receiver   text,"
                + "content    text,"
                + "send_date  text,"
                + "id         text,"
                + "FOREIGN KEY (id) REFERENCES member(Id));");
         //w3shools 에 가면 html5및
        db.execSQL("insert into message(receiver,content,send_date,id) "
                + "values ('hjj2','Hi!!! Thank you ...','2016-10-30 13:17','hjj1')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //db.execSQL("insert into Android values(null,'android_6.0.0',1);");
        //db.execSQL("insert into Android values(null,'android_6.0.0',2);");
       // this.onCreate(db);
        db.execSQL("drop table if exists member;");
        this.onCreate(db);
        db.execSQL("drop table if exists message;");
        this.onCreate(db);
    }


    // DML ( CREATE)
    public void insert(MemberDTO param){
        Log.i("=========DAO에서 받은 ID : ","insertList() 진입");
        String sql = "insert into " + TABLE_NAME
                +String.format("(%s,%s,%s,%s,%s,%s,%s)",ID,PW,NAME,EMAIL,ADDR,PHONE,PHOTO)
                +String.format(" values('%s','%s','%s','%s','%s','%s','%s')"
                           ,param.getId(),param.getPw(),param.getName(),param.getEmail(),param.getAddr()
                           ,param.getPhone(),param.getProfileImg());
        SQLiteDatabase db = this.getWritableDatabase();  //db에 쓰기 권한을 부여하여라.
        db.execSQL(sql);  //db  wirte 할때 사용함
        db.close();
    }
    // DML ( READ)  3가지 경우 (all , 건별, count)  -> return  자료구조냐,int냐
    // 검색 조건없이 전체 목록 조회
    public ArrayList<MemberDTO> selectList(){
        Log.i("=========DAO에서 받은 ID : ","selectListByName() 진입");
        String sql = "select "+
                String.format("%s,%s,%s,%s,%s,%s,%s",ID,PW,NAME,EMAIL,ADDR,PHONE,PHOTO)
                +" from member ;";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);    //read 문 sql 실행 데이타   --> Cursor 객체에 데이타를 담는다.
        ArrayList<MemberDTO> list = new ArrayList<MemberDTO>();


        if(cursor != null){
            Log.i("DAO LIST 조회결과 : ","SUCCESS");
            cursor.moveToFirst();  // 커서를 처음으로 옮겨서 복사해라
        }

        do{
            MemberDTO temp = new MemberDTO();
            temp.setId(cursor.getString(0));
            temp.setPw(cursor.getString(1));
            temp.setName(cursor.getString(2));
            temp.setEmail(cursor.getString(3));
            temp.setAddr(cursor.getString(4));
            temp.setPhone(cursor.getString(5));
            temp.setProfileImg(cursor.getString(6));
            list.add(temp);   // 불러온 데이타를 row 단위로 가져오기 때문에 cursor 라인별데이타를 상위 temp 에 넣어준다.
        }
        while (cursor.moveToNext());  //return 값이 ture or false true 이면 계속 진행
        return list;
    }
    // 검색 조건이 있는 상황에서 목록 조회
    public ArrayList<MemberDTO> selectListByName(MemberDTO param){
        Log.i("=========DAO에서 받은 ID : ","selectListByName() 진입");
        String sql = "select "+
                String.format("%s,%s,%s,%s,%s,%s,%s",ID,PW,NAME,EMAIL,ADDR,PHONE,PHOTO)
                +String.format(" from %s where %s = '%s' ;",TABLE_NAME,NAME,param.getName());
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);    //read 문 sql 실행 데이타   --> Cursor 객체에 데이타를 담는다.
        ArrayList<MemberDTO> list = new ArrayList<MemberDTO>();


        if(cursor != null){
            Log.i("DAO LIST 조회결과 : ","SUCCESS");
            cursor.moveToFirst();  // 커서를 처음으로 옮겨서 복사해라
        }

        do{
            MemberDTO temp = new MemberDTO();
            temp.setId(cursor.getString(0));
            temp.setPw(cursor.getString(1));
            temp.setName(cursor.getString(2));
            temp.setEmail(cursor.getString(3));
            temp.setAddr(cursor.getString(4));
            temp.setPhone(cursor.getString(5));
            temp.setProfileImg(cursor.getString(6));
            list.add(temp);   // 불러온 데이타를 row 단위로 가져오기 때문에 cursor 라인별데이타를 상위 temp 에 넣어준다.
        }
        while (cursor.moveToNext());  //return 값이 ture or false true 이면 계속 진행
        return list;
    }
    // 검색 조건이 있는 상황에서 한건 조회
    public MemberDTO selectOne(MemberDTO param){
        Log.i("=========DAO에서 받은 ID : ","selectOne() 진입");
        String sql = "select "+
                String.format("%s,%s,%s,%s,%s,%s,%s",ID,PW,NAME,EMAIL,ADDR,PHONE,PHOTO)
                +String.format(" from %s where %s = '%s' ;",TABLE_NAME,ID,param.getId());
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);    //read 문 sql 실행 데이타   --> Cursor 객체에 데이타를 담는다.
        //ArrayList<MemberDTO> list = new ArrayList<MemberDTO>();  --1건으로 list 에 담을 필요가 겂다
        MemberDTO temp = null;   //메모리에 주소값만 할당된 상태 null 체크가 되더라
        if(cursor.moveToNext()){
            Log.i("DAO ID 조회결과 : ","ID 존재함");
            //cursor.moveToFirst();  // 커서를 처음으로 옮겨서 복사해라  --1건으로 moveTofirst 가 필요없다.
            //MemberDTO temp = new MemberDTO();
            temp = new MemberDTO();
            temp.setId(cursor.getString(0));
            temp.setPw(cursor.getString(1));
            temp.setName(cursor.getString(2));
            temp.setEmail(cursor.getString(3));
            temp.setAddr(cursor.getString(4));
            temp.setPhone(cursor.getString(5));
            temp.setProfileImg(cursor.getString(6));
        }
        return temp;
    }
    public int count(){
        Log.i("=========DAO에서 받은 ID : ","진입");
        String sql = "select count(*) as count from member;";
        SQLiteDatabase db = this.getReadableDatabase();
        int count = 0;
        Cursor cursor = db.rawQuery(sql,null);   //null 내가준것만 처리해라 시스템 파라미터 안쓴다는 표현.
        if(cursor.moveToNext()){
            Log.i("DAO ID 조회결과 : ","ID 존재함");
            //cursor.moveToFirst();  // 커서를 처음으로 옮겨서 복사해라  --1건으로 moveTofirst 가 필요없다.
            //MemberDTO temp = new MemberDTO();
            count = cursor.getInt(cursor.getColumnIndex("count"));  // 원래 존재하는게 아닌 동적으로 생성한 count 이므로 동적으로 준다.
        }
        return count;
    }
    // DML ( UPDATE)
    public void update(MemberDTO param){  // 외부에서 접근 못하게 void 로닫아준다.
        Log.i("=========DAO update : ","진입");
        String sql = String.format("update %s set ",TABLE_NAME)
               + String.format("%s = '%s',",PW,param.getPw())
                + String.format("%s = '%s',",EMAIL,param.getEmail())
                + String.format("%s = '%s',",ADDR,param.getAddr())
                + String.format("%s = '%s' ",PHOTO,param.getProfileImg())
                + String.format(" where %s = '%s';",ID,param.getId());
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(sql);
        db.close();
    }
    // DML ( DELETE)
    public void delete(String id){
        Log.i("=========DAO delete : ","진입");
        String sql = String.format("delete from %s ",TABLE_NAME)
                + String.format(" where %s = '%s' ;",ID, id);
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(sql);
        db.close();
    }



}
