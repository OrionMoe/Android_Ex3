package cn.edu.hqu.cst.android.lzt.ex3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public TextView MainActivity_Display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MainActivity_Display = findViewById(R.id.MainActivity_Display);
        Button Button_ActivityListener = findViewById(R.id.Button_ActivityListener);
        Button Button_InnerClass = findViewById(R.id.Button_InnerClass);
        Button Button_AnonInnerClass = findViewById(R.id.Button_AnonInnerClass);
        Button Button_OuterClass = findViewById(R.id.Button_OuterClass);
        Button Button_BindToLabel = findViewById(R.id.Button_BindToLabel);
        Button Button_SysInfo = findViewById(R.id.Button_SysInfo);
        Button Button_ProgressBar = findViewById(R.id.Button_ProgressBar);

        // Set Activity as Listener
        Button_ActivityListener.setOnClickListener((View.OnClickListener) MainActivity.this);

        // Inner Class
        Button_InnerClass.setOnClickListener(new MyClickListener());

        // Anon
        Button_AnonInnerClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity_Display.setText(R.string.Main_anoninner);
            }
        });

        // Outer Class
        Button_OuterClass.setOnClickListener((View.OnClickListener) new OuterClass (MainActivity.this, MainActivity_Display));

        // System Info
        Button_SysInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openSysInfo(); }
        });

        // Progress bar
        Button_ProgressBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openProgressBar(); }
        });
    }

    //activity
    public void onClick(View v) {
        MainActivity_Display.setText(R.string.Main_activitylistener);
    }

    //绑定到标签里声明的函数
    public void clickHandler(View source) {
        MainActivity_Display.setText(R.string.Main_bindtolabel);
    }

    //内部类
    class MyClickListener implements View.OnClickListener
    {
        @Override
        public void onClick(View v) {
            MainActivity_Display.setText(R.string.Main_innerclass);
        }
    }

    public void openSysInfo() {
        Intent intent = new Intent(this, ConfigurationTest.class);
        startActivity(intent);
    }
    public void openProgressBar() {
        Intent intent = new Intent(this, ProgressDialogTest.class);
        startActivity(intent);
    }
}

