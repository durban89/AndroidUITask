package com.gowhich.androiduitask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private TextView textView1;
    private TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView1 = (TextView) this.findViewById(R.id.textView1);
        textView2 = (TextView) this.findViewById(R.id.textView2);

        //添加html 标签
        String html = "<font color='red'>I Love You!</font><br />";
        html += "<font color='#0000ff'><big><i>I Love You!</i></big></font>";
        html += "<p><big><a href='http://www.baidu.com'>百度</a></big></p>";
        CharSequence charSequence = Html.fromHtml(html);
        textView1.setText(charSequence);
        //点击的时候产生超链接
        textView1.setMovementMethod(LinkMovementMethod.getInstance());

        String textString = "我的URL http://www.gowhich.com \n";
        textString += "我的邮箱地址：email:zhangdapeng89@gmail.com";
        textString += "我的电话： 15000711265";
        textView2.setText(textString);
        textView2.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
