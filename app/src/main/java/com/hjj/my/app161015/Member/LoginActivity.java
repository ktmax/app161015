package com.hjj.my.app161015.Member;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hjj.my.app161015.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    EditText et_id, et_pw;
    Button bt_login, bt_join;
    MemberService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        service = new MemberServiceImpl(this.getApplicationContext());
        et_id= (EditText) findViewById(R.id.et_id);
        et_pw = (EditText) findViewById(R.id.et_pw);
        bt_login = (Button) findViewById(R.id.bt_login);
        bt_join = (Button) findViewById(R.id.bt_join);
        bt_login.setOnClickListener(this);
        bt_join.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String id = et_id.getText().toString();
        String pw = et_pw.getText().toString();
        MemberDTO param = new MemberDTO();
        //MemberDTO param = new MemberDTO(); 아래 안으로 이동
        switch (v.getId()){
            case R.id.bt_login :
                param.setId(id);
                param.setPw(pw);
                //result = service.getOne(param);
                MemberDTO result = service.getOne(param);
                if(result==null){
                    Toast.makeText(LoginActivity.this,
                            "는 존재하지 않는 아이디 입니다.",
                            Toast.LENGTH_LONG).show();
                }else if(result.getId().equals(param.getPw())){
                    Toast.makeText(LoginActivity.this,
                            "비밀번호가 일치하지 않습니다.",
                            Toast.LENGTH_LONG).show();
                }else{
                    Log.d("로그인{} !!","성공");
                    startActivity(new Intent(LoginActivity.this, ListActivity.class));
                  //  Toast.makeText(LoginActivity.this,
                  //          "환영합니다."+result.getName(),
                  //          Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.bt_join :
                startActivity(new Intent(LoginActivity.this, JoinActivity.class));
                break;
        }

    }
}
