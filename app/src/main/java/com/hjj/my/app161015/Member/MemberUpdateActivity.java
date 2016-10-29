package com.hjj.my.app161015.Member;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.hjj.my.app161015.R;

public class MemberUpdateActivity extends AppCompatActivity implements View.OnClickListener {
    MemberService service; //id 를 받아 오고 id 정보로 db를 다시 뒤져서 가져와야한다.
    TextView tv_id,tv_name;
    EditText et_pw,et_email,et_addr,et_phone;
    Button bt_submit,bt_cancel;
    MemberDTO member;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_update);
        service = new MemberServiceImpl(this.getApplicationContext());
        Intent intent = this.getIntent();
        String id = intent.getExtras().getString("id");  //서로다른 클레스 이동은 String 으로 id라는 지역값으로 이동
        member = new MemberDTO();  //--000 에서 주지 않고 여기서 주었다.
        member.setId(id);
        member = service.getOne(member);


        tv_id= (TextView) findViewById(R.id.tv_id);
        et_pw= (EditText) findViewById(R.id.et_pw);
        tv_name= (TextView) findViewById(R.id.tv_name);
        et_email= (EditText) findViewById(R.id.et_email);
        et_addr= (EditText) findViewById(R.id.et_addr);
        et_phone= (EditText) findViewById(R.id.et_phone);

        tv_id.setText(member.getId());
        et_pw.setHint(member.getPw());
        tv_name.setText(member.getName());
        et_email.setHint(member.getEmail());
        et_addr.setHint(member.getAddr());
        et_phone.setHint(member.getPhone());

        bt_submit= (Button)findViewById(R.id.bt_submit);
        bt_cancel= (Button)findViewById(R.id.bt_cancel);
        bt_submit.setOnClickListener(this);
        bt_cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_submit:
                MemberDTO param= new MemberDTO();
                param.setId(tv_id.getText().toString());
                param.setName(tv_name.getText().toString());
                param.setPw((et_pw.getText().toString().equals("") ) ? member.getPw(): et_pw.getText().toString());
                /*
                if(aa == ""){
                    param.setPw(member.getPw());
                }else {
                    param.setPw(et_pw.getText().toString());
                }  */
                param.setEmail((et_email.getText().toString().equals("")) ? member.getEmail(): et_email.getText().toString());
                param.setAddr((et_addr.getText().toString().equals("") )? member.getAddr(): et_addr.getText().toString());
                param.setPhone((et_phone.getText().toString().equals(""))? member.getAddr(): et_phone.getText().toString());

                //member.setPw(et_pw.getText().toString());

                //member.setEmail(et_email.getText().toString());
                //member.setAddr(et_addr.getText().toString());
                //member.setPhone(et_phone.getText().toString());
                service.update(param);
                Intent intent = new Intent(MemberUpdateActivity.this,MemberDetailActivity.class);
                intent.putExtra("id",member.getId());
                startActivity(intent);
                break;
            case R.id.bt_cancel:
                Intent intent2 = new Intent(MemberUpdateActivity.this,MemberDetailActivity.class);
                intent2.putExtra("id",member.getId());
                startActivity(intent2);
                break;
        }
    }
}
