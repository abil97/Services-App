package com.example.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView numberText;

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Bundle bundle = intent.getExtras();
            if(bundle != null) {
                String counter = bundle.getString("MyCounter");
                numberText.setText(counter);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numberText = findViewById(R.id.numberView);
        configureReceiver();
    }

    public void startService(View view){
        Intent intent = new Intent(this, MyService.class);
        startService(intent);
    }

    public void stopService(View view){
        Intent intent = new Intent(this, MyService.class);
        stopService(intent);
        unregisterReceiver(receiver);
    }
    // Configuring the receiver
    private void configureReceiver() {
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.example.SendBroadcast");
        this.registerReceiver(receiver, filter);
    }


}
