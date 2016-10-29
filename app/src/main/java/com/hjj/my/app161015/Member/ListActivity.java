package com.hjj.my.app161015.Member;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.hjj.my.app161015.R;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {
    ListView lv_memberlist;
    //여기서 부터 리스트에 들어갈 member 들을 다 만들고 나서 리스트에서 볼수있도록 최종적으로 표현하여야한다.
    MemberService service;  // service 를 영역을 벗어날수있게 선언 해주고 아래서 사용
    //final String[] arr = new String[1];   //db에서 가져온다.
    final String[] arr = new String[1];  // id 만 받으면 되니까 1만 준다.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        lv_memberlist = (ListView) findViewById(R.id.lv_memberlist);
        service = new MemberServiceImpl(this.getApplicationContext());  // 나 어디 있으니까 이리로 오라는 선언
        ArrayList<MemberDTO> list = service.getList();  //db에 목록을 여기에 담는다.
        Log.d("서비스에서 불러온 데이터 갯수: ", String.valueOf(list.size())); //Log.d 는 int 인식할수없다 .  String.valueOf(list.size()) 형변환
        lv_memberlist.setAdapter(new MemberAdapter(this, list));
        //----지금부터 이벤트를 나누어서 진행한다. 클릭이벤트
        //lv_memberlist.setOnItemClickListener(new ) -> ctl + space -> OnItemClickListener 선택
        //짧게 누를때  --> detail (회원정보수정, 전화걸기,저보보기)
        lv_memberlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int i, long l) {
                //Object o = lv_memberlist.getItemAtPosition(i);   //Object 타입변수를 선언할때 쓰임
                //MemberDTO member= (MemberDTO) o;
                MemberDTO member = (MemberDTO) lv_memberlist.getItemAtPosition(i);
                Toast.makeText(ListActivity.this, "선택한 이름" + member.getName(), Toast.LENGTH_LONG).show();
                Intent intent = new Intent(ListActivity.this, MemberDetailActivity.class);  // 클릭하면 보내겠다 . ListActivity.this (from)   MemberDetailActivity.class(to)
                //391page
                intent.putExtra("id", member.getId());  //intent.putExtra() data값을 보낸다.  id 로 전달되는 값은 java에서 서로 다른 인스턴트로 전달시 Stirng 으로 전달된다.
                //intent.putExtra("pw",member.getPw());  <<-- 이렇게 하면 비번이 뚤린다...
                startActivity(intent);  //id 를 보낸다

            }
        });
        //lv_memberlist.setOnItemLongClickListener(new) -> ctl + space -> OnItemClickListener 선택
        //길게 누를때 (삭제이벤트)
        lv_memberlist.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View v, int i, long l) {
                MemberDTO member = (MemberDTO) lv_memberlist.getItemAtPosition(i);
                //Toast.makeText(ListActivity.this,"길게 누른 이름"+member.getName(),Toast.LENGTH_LONG).show();
                arr[0] = member.getId();
                new AlertDialog.Builder(ListActivity.this)
                        .setTitle("삭제 OK ?")
                        .setMessage("정말로 삭제하시겠습니까?")
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                service.unregist(arr[0]);
                                startActivity(new Intent(ListActivity.this, ListActivity.class));
                            }
                        })
                        .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }).show();
                return true;
            }
        });

    }
}
