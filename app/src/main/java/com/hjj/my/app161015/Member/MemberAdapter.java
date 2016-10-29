package com.hjj.my.app161015.Member;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hjj.my.app161015.R;

import java.util.ArrayList;

/**
 * Created by 1027 on 2016-10-22.
 */

public class MemberAdapter extends BaseAdapter {
    ArrayList<MemberDTO> list;
    LayoutInflater inflater;
    private int[] photos = {
            R.drawable.cupcake,
            R.drawable.donut,
            R.drawable.eclair,
            R.drawable.froyo,
            R.drawable.gingerbread,
            R.drawable.honeycomb,
            R.drawable.icecream,
            R.drawable.jellybean,
            R.drawable.kitkat,
            R.drawable.lollipop,
            R.drawable.cupcake2,
            R.drawable.donut2,
            R.drawable.icecream2
    };

    public MemberAdapter(Context context,ArrayList<MemberDTO> list) { //alt insert -> Constructor -> ArrayList
                         //Context context을 주어야 위치값을 주어 Activity 를 이용할수있다.
        this.list = list;
        this.inflater = LayoutInflater.from(context);  //여기까지 정해진내용  Context 를 주면 하나의 공간에서 왔다갔다한다...
    }

    @Override
    public int getCount() {  //getCount() 에 넣어둔 친구들의 수
        return list.size();    // 리스트의 값들만 제어하지 나머지는 다 가져다 쓴다.
    }

    @Override
    public Object getItem(int i) {   //.....번째 저장된 친구의 정보는 무엇이냐 할때 쓰임
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {  //... 친구의 위치값은 무엇이냐
        return i;
    }

    @Override
    public View getView(int i, View v, ViewGroup g) {
        ViewHolder holder;
        if(v==null){
            //어뎁터 주고와서 시작한다. layout
            //-------------------------문법 시작-----------------
            v = inflater.inflate(R.layout.member_list,null); // null 자동으로 만드는값 배제 하겠다.
            holder = new ViewHolder();
            holder.ivPhoto = (ImageView) v.findViewById(R.id.iv_photo);
            holder.tvName = (TextView) v.findViewById(R.id.tv_name);
            holder.tvPhone = (TextView) v.findViewById(R.id.tv_phone);
            v.setTag(holder);
            //------------------------문법끝-----------  이미지 삽입을 위한 view 양식이다.
        }else{
            holder = (ViewHolder) v.getTag();  //view 에 값이 들어있으면 가져와라...
        }
        Log.d("어댑터에서 체크한 이름",list.get(i).getName());
        holder.ivPhoto.setImageResource(photos[i]);
        holder.tvName.setText(list.get(i).getName());
        holder.tvPhone.setText(list.get(i).getPhone());
        return v;
    }
    static class  ViewHolder{   //ViewHoler() 하나의 데이타 값을 가져다 주는 컵의 역활 static은 교실은 하나만 두고 맴버 사용자 (고스트 들이 들락 날락 거린다.)
                                   // 칸막이된 3칸짜리 컵을 만들었다.
        ImageView ivPhoto;
        TextView tvName;
        TextView tvPhone;

    }
}
