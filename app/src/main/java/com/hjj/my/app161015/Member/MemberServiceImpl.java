package com.hjj.my.app161015.Member;

import android.content.Context;

import com.hjj.my.app161015.util.Retval;

import java.util.ArrayList;

/**
 * Created by 1027 on 2016-10-15.
 */

public class MemberServiceImpl implements MemberService{
    MemberDAO dao;

    public MemberServiceImpl(Context context){
        this.dao = new MemberDAO(context);
    }

    @Override
    public void regist(MemberDTO member) {
        dao.insert(member);
    }

    @Override
    public ArrayList<MemberDTO> getList(MemberDTO member) {
        return dao.selectList();
    }

    @Override
    public ArrayList<MemberDTO> getListByname(MemberDTO member) {
        return dao.selectListByName(member);
    }

    @Override
    public MemberDTO getOne(MemberDTO member) {
        return dao.selectOne(member);
    }

    @Override
    public int count() {
        return  dao.count();
    }

    @Override
    public void update(MemberDTO member) {
        dao.update(member);

    }

    @Override
    public void unregist(MemberDTO member) {
        dao.delete(member);

    }
}
