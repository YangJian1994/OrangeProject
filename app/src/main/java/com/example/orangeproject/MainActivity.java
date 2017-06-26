package com.example.orangeproject;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.orangeproject.bean.Data;
import com.example.orangeproject.service.UsbService;
import com.google.gson.Gson;

import butterknife.Bind;
import butterknife.ButterKnife;
import tech.linjiang.suitlines.SuitLines;

public class MainActivity extends AppCompatActivity {

    private MessageReceiver messageReceiver;

    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.sl_detail)
    SuitLines slDetail;
    @Bind(R.id.frame_layout)
    LinearLayout frameLayout;
    @Bind(R.id.tv_PM)
    TextView tvPM;
    @Bind(R.id.tv_AQI)
    TextView tvAQI;
    @Bind(R.id.air_layout)
    LinearLayout airLayout;
    @Bind(R.id.tv_battery)
    TextView tvBattery;
    @Bind(R.id.battery_layout)
    LinearLayout batteryLayout;
    @Bind(R.id.tv_disk)
    TextView tvDisk;
    @Bind(R.id.disk_layout)
    LinearLayout diskLayout;
    @Bind(R.id.tv_wrist)
    TextView tvWrist;
    @Bind(R.id.tv_step)
    TextView tvStep;
    @Bind(R.id.wrist_layout)
    LinearLayout wristLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //动态注册广播
        messageReceiver = new MessageReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.example.orangeproject.RECEIVER");
        registerReceiver(messageReceiver, intentFilter);

        Intent intent = new Intent(this, UsbService.class);
        startService(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public class MessageReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String jsonData = intent.getStringExtra("data");
            Gson gson = new Gson();
            Data data = gson.fromJson(jsonData, Data.class);
            if (data != null && data.getModules() != null) {
                if (data.isState()) {
                    frameLayout.setBackgroundColor(Color.parseColor("#E91E63"));
                } else {
                    frameLayout.setBackgroundColor(Color.parseColor("#BDBDBD"));
                }
                for (int i = 0; i < data.getModules().size(); i++) {
                    switch (data.getModules().get(i).getType()) {
                        //电池
                        case 512:
                            if (data.getModules().get(i).isState()) {
                                batteryLayout.setBackgroundColor(Color.parseColor("#FFC107"));
                            } else {
                                batteryLayout.setBackgroundColor(Color.parseColor("#BDBDBD"));
                            }
                            break;
                        //U盘
                        case 768:
                            if (data.getModules().get(i).isState()) {
                                diskLayout.setBackgroundColor(Color.parseColor("#FF5722"));
                            } else {
                                diskLayout.setBackgroundColor(Color.parseColor("#BDBDBD"));
                            }
                            break;
                        //空气
                        case 1024:
                            if (data.getModules().get(i).isState()) {
                                airLayout.setBackgroundColor(Color.parseColor("#4CAF50"));
                            } else {
                                airLayout.setBackgroundColor(Color.parseColor("#BDBDBD"));
                            }
                            break;
                        //耳机
                        case 1280:
                            break;
                        //手环
                        case 1792:
                            if (data.getModules().get(i).isState()) {
                                wristLayout.setBackgroundColor(Color.parseColor("#303F9F"));
                            } else {
                                wristLayout.setBackgroundColor(Color.parseColor("#BDBDBD"));
                            }
                            break;
                    }
                }
            }
        }
    }
}
