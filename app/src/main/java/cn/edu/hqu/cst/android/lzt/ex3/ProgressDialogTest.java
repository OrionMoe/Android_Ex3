package cn.edu.hqu.cst.android.lzt.ex3;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;

import java.lang.ref.WeakReference;

public class ProgressDialogTest extends Activity {
    final static int MAX_PROGRESS = 100;
    // 该程序模拟填充长度为 100 的数组
    private int[] data = new int[100];
    int hasData = 0;
    // 定义进度对话框的标识
    final int PROGRESS_DIALOG = 0x112;
    // 记录进度对话框的完成百分比
    int progressStatus = 0;
    ProgressDialog pd1,pd2;
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            // 表明消息是由该程序发送的。
            if (msg.what == 0x123) {
                pd2.setProgress(progressStatus);
            }
        }
    };
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progress_dialog_test);

        Button homeButton = findViewById(R.id.homeButton);
        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    public void showSpinner(View source)
    {
        ProgressDialog.show(this, "Currently running"
                , "Please wait...", false, true);
    }

    public void showIndeterminate(View source)
    {
        pd1 = new ProgressDialog(ProgressDialogTest.this);
        // 设置对话框的标题
        pd1.setTitle("Currently running");
        // 设置对话框显示的内容
        pd1.setMessage("Please wait...");
        // 设置对话框能用“取消”button关闭
        pd1.setCancelable(true);
        // 设置对话框的进度条风格
        pd1.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        // 设置对话框的进度条是否显示运行进度
        pd1.setIndeterminate(true);
        pd1.show();
    }

    public void showProgress(View source)
    {
        // 将进度条的完毕进度重设为0
        progressStatus = 0;
        // 又一次開始填充数组。
        hasData = 0;
        pd2 = new ProgressDialog(ProgressDialogTest.this);
        pd2.setMax(MAX_PROGRESS);
        // 设置对话框的标题
        pd2.setTitle("Currently running");
        // 设置对话框 显示的内容
        pd2.setMessage("Please wait...");
        // 设置对话框不能用“取消”button关闭
        pd2.setCancelable(false);
        // 设置对话框的进度条风格
        pd2.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        // 设置对话框的进度条是否显示运行进度
        pd2.setIndeterminate(false);
        pd2.show();

        new Thread()
        {
            public void run()
            {
                while (progressStatus < MAX_PROGRESS)
                {
                    // 获取耗时操作的完毕百分比
                    progressStatus = MAX_PROGRESS
                            * doWork() / data.length;
                    // 发送空消息到Handler
                    handler.sendEmptyMessage(0x123);
                }
                // 如果任务已经完成
                if (progressStatus >= MAX_PROGRESS)
                {
                    // 关闭对话框
                    pd2.dismiss();
                }
            }
        }.start();
    }
    // 模拟一个耗时的操作。
    public int doWork() {
        // 为数组元素赋值
        data[hasData++] = (int) (Math.random() * 100);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return hasData;
    }
}
