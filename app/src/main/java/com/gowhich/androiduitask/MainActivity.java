package com.gowhich.androiduitask;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.reflect.Field;

public class MainActivity extends AppCompatActivity {

    private TextView textView1;
    private TextView textView2;
    private TextView textView3;

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
        textView3 = (TextView) this.findViewById(R.id.textView2);

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
        textHtml3 += "图像4<a href='http://www.baidu.com'><img src='image4'></a>图像5<img src='image5' />";

        CharSequence charSequence1 = Html.fromHtml(textHtml3, new Html.ImageGetter() {
            @Override
            public Drawable getDrawable(String s) {
                //获取系统资源信息，比如图片信息
                Drawable drawable = getResources().getDrawable(getResourceId(s));
                //第三个图片文件按照50%的比例进行压缩
                try{
                    Log.i("resource", s);
                    if(s.equals("image3")){
                        drawable.setBounds(0, 0, drawable.getIntrinsicWidth() / 2, drawable.getIntrinsicHeight() / 2);
                    } else {
                        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
                    }
                }catch (Exception e){

                }

                return drawable;
            }
        }, null);

        textView3.setText(charSequence1);
        textView3.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
