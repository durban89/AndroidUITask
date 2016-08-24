package com.gowhich.androiduitask;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.reflect.Field;

public class MainActivity extends AppCompatActivity {

    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private TextView textView4;
    private TextView textView5;


    public int getResourceId(String name) {
        try {
            //根据资源的ID的变量名获得Field的对象，还用反射机制来实现的
            Field field = R.drawable.class.getField(name);
            //取得并返回资源的id的字段（静态变量）的值，使用反射机制
            return Integer.parseInt(field.get(null).toString());
        } catch (Exception e) {

        }

        return 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView1 = (TextView) this.findViewById(R.id.textView1);
        textView2 = (TextView) this.findViewById(R.id.textView2);
        textView3 = (TextView) this.findViewById(R.id.textView3);
        textView4 = (TextView) this.findViewById(R.id.textView4);
        textView5 = (TextView) this.findViewById(R.id.textView5);

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

        //显示表情图像和文字
        textView3.setTextColor(Color.BLACK);
        textView3.setBackgroundColor(Color.WHITE);
        String textHtml3 = "图像1<img src='image1'>图像2<img src='image2' />图像3<img src='image3' />";
        textHtml3 += "图像4<a href='http://www.baidu.com'><img src='image4' /></a>图像5<img src='image5' />";

        CharSequence charSequence1 = Html.fromHtml(textHtml3, new Html.ImageGetter() {
            @Override
            public Drawable getDrawable(String s) {
                //获取系统资源信息，比如图片信息
                Drawable drawable = getResources().getDrawable(getResourceId(s));
                //第三个图片文件按照50%的比例进行压缩
                try{
                    Log.i("resource", s);
                    if(s.equals("image3")){
                        drawable.setBounds(0, 0, drawable.getIntrinsicWidth() / 8, drawable.getIntrinsicHeight() / 8);
                    } else {
                        drawable.setBounds(0, 0, drawable.getIntrinsicWidth() / 6, drawable.getIntrinsicHeight() / 6);
                    }
                }catch (Exception e){

                }

                return drawable;
            }
        }, null);

        textView3.setText(charSequence1);
        textView3.setMovementMethod(LinkMovementMethod.getInstance());

        //点击链接弹出Activity
        String text1 = "显示Activity1";
        String text2 = "显示Activity2";
        //主要用来拆分字符串
        SpannableString spannableString1 = new SpannableString(text1);
        SpannableString spannableString2 = new SpannableString(text2);

        spannableString1.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, String1Activity.class);
                startActivity(intent);
            }
        },0 , text1.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        spannableString2.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, String2Activity.class);
                startActivity(intent);
            }
        },0 , text1.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        textView4.setText(spannableString1);
        textView5.setText(spannableString2);
        textView4.setMovementMethod(LinkMovementMethod.getInstance());
        textView5.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
