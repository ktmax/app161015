package com.hjj.my.app161015.Member;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hjj.my.app161015.R;
import com.hjj.my.app161015.homepage.HomepageActivity;
import com.hjj.my.app161015.message.MessageWriteActivity;
import com.hjj.my.app161015.util.Phone;

public class MemberDetailActivity extends AppCompatActivity implements View.OnClickListener {
    MemberService service; //id 를 받아 오고 id 정보로 db를 다시 뒤져서 가져와야한다.
    TextView tv_id,tv_pw,tv_name,tv_email,tv_addr,tv_phone;
    Button bt_call,bt_map,bt_update,bt_list,bt_message,bt_email;
    MemberDTO member; //인스턴트 변수는 초기화 하지 않는다.  연산자는 메서드안에 있어야한다. 000
    Phone phone;
    //Butter Knife 검색
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_detail);
        service = new MemberServiceImpl(this.getApplicationContext());
        phone = new Phone(this,this); // 폰이 activity 에 접근 가능해진다.util/phone
        Intent intent = this.getIntent();
        String id = intent.getExtras().getString("id");  //서로다른 클레스 이동은 String 으로 id라는 지역값으로 이동
        member = new MemberDTO();  //--000 에서 주지 않고 여기서 주었다.
        member.setId(id);
        member = service.getOne(member);


        tv_id= (TextView) findViewById(R.id.tv_id);
        tv_pw= (TextView) findViewById(R.id.tv_pw);
        tv_name= (TextView) findViewById(R.id.tv_name);
        tv_email= (TextView) findViewById(R.id.tv_email);
        tv_addr= (TextView) findViewById(R.id.tv_addr);
        tv_phone= (TextView) findViewById(R.id.tv_phone);

        tv_id.setText(member.getId());
        tv_pw.setText(member.getPw());
        tv_name.setText(member.getName());
        tv_email.setText(member.getEmail());
        tv_addr.setText(member.getAddr());
        tv_phone.setText(member.getPhone());

        bt_call= (Button)findViewById(R.id.bt_call);
        bt_map= (Button)findViewById(R.id.bt_map);
        bt_update= (Button)findViewById(R.id.bt_update);
        bt_list= (Button)findViewById(R.id.bt_list);
        bt_message= (Button)findViewById(R.id.bt_message);
        bt_email= (Button)findViewById(R.id.bt_email);
        bt_call.setOnClickListener(this);
        bt_map.setOnClickListener(this);
        bt_update.setOnClickListener(this);
        bt_list.setOnClickListener(this);
        bt_message.setOnClickListener(this);
        bt_email.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_call:
                phone.dial(member.getPhone());
                //phone.directCall(member,getPhone());

                break;
            case R.id.bt_map:
                break;
            case R.id.bt_update:
                Intent intent = new Intent(MemberDetailActivity.this,MemberUpdateActivity.class);  // 클릭하면 보내겠다 . MemberDetailActivity.this (from)   MemberUpdateActivity.class(to)
                intent.putExtra("id",member.getId());  //intent.putExtra() data값을 보낸다.  id 로 전달되는 값은 java에서 서로 다른 인스턴트로 전달시 Stirng 으로 전달된다.
                startActivity(intent);  //id 를 보낸다
                break;
            case R.id.bt_list:
                startActivity(new Intent(MemberDetailActivity.this, ListActivity.class)); //list창으로 보내라
                break;
            case R.id.bt_message:
                Intent intent2 = new Intent(MemberDetailActivity.this,MessageWriteActivity.class);  // 클릭하면 보내겠다 . MemberDetailActivity.this (from)   MemberUpdateActivity.class(to)
                intent2.putExtra("id",member.getId());  //intent.putExtra() data값을 보낸다.  id 로 전달되는 값은 java에서 서로 다른 인스턴트로 전달시 Stirng 으로 전달된다.
                startActivity(intent2);  //id 를 보낸다
                break;
            case R.id.bt_email:
                //startActivity(new Intent(MemberDetailActivity.this, ListActivity.class)); //list창으로 보내라
                startActivity(new Intent(MemberDetailActivity.this, HomepageActivity.class)); //list창으로 보내라
                break;
        }
    }


}
