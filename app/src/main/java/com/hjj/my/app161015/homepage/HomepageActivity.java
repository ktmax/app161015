package com.hjj.my.app161015.homepage;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.hjj.my.app161015.Member.MemberDTO;
import com.hjj.my.app161015.R;
import com.hjj.my.app161015.util.WebAppInterface;

public class HomepageActivity extends AppCompatActivity {
    WebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        webview = (WebView) findViewById(R.id.webview);
        //webview.loadUrl("http://www.naver.com");
        WebSettings webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webview.addJavascriptInterface(new WebAppInterface(this), "Android");
        MemberDTO member = new MemberDTO();
        member.setName("홍길동");
        String html = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<body>\n" +
                "\n" +
                "<video width=\"400\" controls>\n" +
                "  <source src=\"mov_bbb.mp4\" type=\"video/mp4\">\n" +
                "  <source src=\"mov_bbb.ogg\" type=\"video/ogg\">\n" +
                "  Your browser does not support HTML5 video.\n" +
                "</video>\n" +
                "\n" +
                "<p>\n" +
                "Video courtesy of\n" +
                "<a href=\"http://www.bigbuckbunny.org/\" target=\"_blank\">Big Buck Bunny</a>.\n" +
                "</p>\n" +
                "<button id='mypage'>마이페이지</button>"+
                //"<button onclick='mypage'>마이페이지</button>"+
                 "<script><script>"+
                "document.getElementById('demo')"+
                //"\n" + member.getName() +
                "</body>\n" +
                "</html>";
        webview.loadDataWithBaseURL("",html,"text/html","UTF-8",null);
        //baseurl -> naver.com -> null
        // data   -> HTML
        // mimeType -> text/html
        //encoding  -> UTF-8
        //history url -> null


    }
}
