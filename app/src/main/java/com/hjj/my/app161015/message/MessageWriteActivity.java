package com.hjj.my.app161015.message;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.hjj.my.app161015.Member.MemberDTO;
import com.hjj.my.app161015.Member.MemberService;
import com.hjj.my.app161015.Member.MemberServiceImpl;
import com.hjj.my.app161015.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MessageWriteActivity extends AppCompatActivity implements View.OnClickListener{
    MessageService service;
    MemberDTO member;
    MemberService memberService;
    TextView tv_receiver,tv_id;
    EditText et_wirte;
    Button bt_send,bt_cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_write);
        service = new MessageServiceImpl(this.getApplicationContext());
        memberService = new MemberServiceImpl(this.getApplicationContext());
        Intent intent = this.getIntent();
        String id = intent.getExtras().getString("id");  //서로다른 클레스 이동은 String 으로 id라는 지역값으로 이동
        member = new MemberDTO();  //--000 에서 주지 않고 여기서 주었다.
        member.setId(id);
        member = memberService.getOne(member);
        tv_receiver = (TextView) findViewById(R.id.tv_receiver);
        tv_id = (TextView) findViewById(R.id.tv_id);
        tv_receiver.setText(member.getName()); //이름은 보여주고
        tv_id.setText(member.getId());  // id는 화면 숨김 xml에서 처리했기때문에
        //tv_id.setVisibility(View.INVISIBLE);  // id는 화면 숨김
        et_wirte = (EditText) findViewById(R.id.et_wirte);
        bt_send = (Button) findViewById(R.id.bt_send);
        bt_cancel = (Button) findViewById(R.id.bt_cancel);
        bt_send.setOnClickListener(this);
        bt_cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //onclicklistener 가 2가지 이상이기때문에 switch 로 나눈다.
            case R.id.bt_send:
                String content = et_wirte.getText().toString();
                Log.d("메시지내용: ",content);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
                String sendDate = sdf.format(new Date());
                Log.d("보낸날짜: ",sendDate);
                String receiver = tv_id.getText().toString();
                Log.d("받는 사람 : ",receiver);
                MessageDTO message = new MessageDTO();
                message.setContent(content);
                message.setReceiver(receiver);
                message.setSendDate(sendDate);
                //message.setWriter();  처리 안함.
                service.write(message);
                break;
            case R.id.bt_cancel: break;
        }

    }
}
