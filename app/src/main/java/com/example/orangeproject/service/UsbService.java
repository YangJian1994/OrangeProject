package com.example.orangeproject.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.example.orangeproject.MainActivity;
import com.example.orangeproject.MyApplication;
import com.example.orangeproject.UsbCommunication;
import com.example.orangeproject.bean.Message;
import com.example.orangeproject.utils.MessageUtil;

import java.util.Timer;
import java.util.TimerTask;

public class UsbService extends Service {

    private static final String TAG = UsbService.class.getSimpleName();
    private UsbCommunication communication = UsbCommunication.getInstance(MyApplication.getContext());
    private byte[] receiveBytes = new byte[1024];
    private Timer timer;
    private Intent intent = new Intent("com.example.orangeproject.RECEIVER");
    private int count = 0;

    public UsbService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate() executed");
    }

    @Override
    public int onStartCommand(final Intent intent, int flags, int startId) {
        //这里执行后台逻辑
        usbTask();
        Log.d(TAG, "onStartCommand() executed");
        return START_REDELIVER_INTENT;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() executed");
    }

    private void usbTask() {
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                //这里执行延迟操作
                Log.e(TAG, count + "");
                communication.sendMessage(Message.sendStateBytes());
                receiveBytes = communication.receiveData();
                count++;
                if (receiveBytes != null) {
                    intent.putExtra("data", MessageUtil.printData(receiveBytes));
                    sendBroadcast(intent);
                    Log.e(TAG, MessageUtil.printData(receiveBytes));
                } else {
                    Log.e(TAG, "No Data !");
                }
            }
        }, 20000, 5000);
    }
}
