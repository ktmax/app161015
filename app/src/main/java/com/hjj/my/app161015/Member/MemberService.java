package com.hjj.my.app161015.Member;

import java.util.ArrayList;

/**
 * Created by 1027 on 2016-10-15.
 */
/*추상메소드*/
public interface MemberService {
    // CREATE
    public void regist(MemberDTO member);   //INSERT
    // READ
    public ArrayList<MemberDTO> getList();
    public ArrayList<MemberDTO> getListByname(MemberDTO member);
    public MemberDTO getOne(MemberDTO member);
    public int count();
    // UPDATE
    public void update(MemberDTO member);

    //DELETE
    public void unregist(String id);  //회원탈퇴

}
