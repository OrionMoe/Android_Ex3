package cn.edu.hqu.cst.android.lzt.ex3;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

public class OuterClass implements View.OnClickListener {

    private Activity act;
    private TextView MainActivity_Display;

    public OuterClass(Activity act,TextView tv_msg) {
        this.act=act;
        this.MainActivity_Display=tv_msg;
    }
    @Override
    public void onClick(View v) {
        this.MainActivity_Display.setText("点击了采用外部类作为监听器");
    }
}
