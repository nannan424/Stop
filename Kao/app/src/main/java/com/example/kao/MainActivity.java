package com.example.kao;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;

public class MainActivity extends AppCompatActivity {

    /**
     * 111
     */
    private TextView mTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    private void initView() {
        mTv = (TextView) findViewById(R.id.tv);
        //倒计时
       Observable.intervalRange(0,4,0,1, TimeUnit.SECONDS)
               .observeOn(AndroidSchedulers.mainThread())
               .subscribe(new Consumer<Long>() {
                   @Override
                   public void accept(Long aLong) throws Exception {
                       long time=3-aLong;
                       mTv.setText("剩余"+time+"s");
                       if(time==0){
                           //跳转第二个登录页面
                           startActivity(new Intent(MainActivity.this,LoginActivity.class));
                       }
                   }
               });
    }
}
