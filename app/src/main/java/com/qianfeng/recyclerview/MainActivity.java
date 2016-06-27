package com.qianfeng.recyclerview;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void doClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.btn_linear:
               // intent = new Intent(this, LinearLayoutRecyclerViewActivity.class);
                break;
            case R.id.btn_grid:
                //intent = new Intent(this, GridRecyclerViewActivity.class);
                break;
            case R.id.btn_water_fall:
                intent = new Intent(this, WaterFallRecyclerViewActivity.class);
                break;
        }
        startActivity(intent);
    }

}
