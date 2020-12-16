package com.example.kao;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    /**
     * 输入用户名
     */
    private EditText mEtName;
    /**
     * 密码
     */
    private EditText mEtPassword;
    /**
     * 登录
     */
    private Button mBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
    }

    private void initView() {
        mEtName = (EditText) findViewById(R.id.et_name);
        mEtPassword = (EditText) findViewById(R.id.et_password);
        mBtn = (Button) findViewById(R.id.btn);
        mBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
            case R.id.btn:
                //判断，输入其他内容吐司输入错误
                String name = mEtName.getText().toString();
                String paw = mEtPassword.getText().toString();
                if(name.equals("H2003xs")&&paw.equals("H2003")){
                    Toast.makeText(this, "输入正确,进入主页", Toast.LENGTH_SHORT).show();
                   startActivity(new Intent(this,HomeActivity.class));
                   //记住状态
                    SharedPreferences user = getSharedPreferences("user", MODE_PRIVATE);
                    SharedPreferences.Editor edit = user.edit();
                    edit.putBoolean("cb",true);
                    edit.commit();//提交
                }
                else {
                    Toast.makeText(this, "输入错误", Toast.LENGTH_SHORT).show();
                    //记住状态.输入不成功存储状态为false
                    SharedPreferences user = getSharedPreferences("user", MODE_PRIVATE);
                    SharedPreferences.Editor edit = user.edit();
                    edit.putBoolean("cb",false);
                }
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        //只要登陆过,直接进入主页
        SharedPreferences user = getSharedPreferences("user", MODE_PRIVATE);
        boolean cb = user.getBoolean("cb", false);//登录状态失败
        //如果没有存值为false
        if(cb){
            //直接跳转
            startActivity(new Intent(this,HomeActivity.class));
        }
    }
}
