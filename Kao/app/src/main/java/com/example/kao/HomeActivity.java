package com.example.kao;

import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.kao.adapter.VpAdapter;
import com.example.kao.bean.BanInfo;
import com.example.kao.fragment.ClassFragment;
import com.example.kao.fragment.ScholFragment;
import com.example.kao.net.Apiservice;
import com.example.kao.presenter.MainPresenter;
import com.example.kao.view.MainView;
import com.google.android.material.tabs.TabLayout;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeActivity extends AppCompatActivity implements MainView {

    private Banner mBan;
    private TabLayout mTab;
    private ViewPager mVp;
    private MainPresenter mainPresenter;
    private ArrayList<BanInfo.BannerlistBean> banList;
    private VpAdapter vpAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //创建p层对象
        mainPresenter = new MainPresenter(this);
        initView();
        initBan();

    }

    private void initBan() {
          //p层对象调用方法
        mainPresenter.getBan();
    }

    private void initView() {
        mBan = (Banner) findViewById(R.id.ban);
        mTab = (TabLayout) findViewById(R.id.tab);
        mVp = (ViewPager) findViewById(R.id.vp);
        //初始护标题集合
        ArrayList<String> titles = new ArrayList<>();
        titles.add("学校新闻");
        titles.add("班级成绩查询");
        //初始化fragment
        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new ScholFragment());
        fragments.add(new ClassFragment());
        //适配器
        vpAdapter = new VpAdapter(getSupportFragmentManager(), fragments, titles);
        mVp.setAdapter(vpAdapter);
        //联动
        mTab.setupWithViewPager(mVp);


    }

    @Override
    public void oneSuecces(Object object) {
        //初始化banner集合
        banList = new ArrayList<>();
        if(object instanceof BanInfo){
            BanInfo info= (BanInfo) object;
            List<BanInfo.BannerlistBean> bannerlist = info.getBannerlist();
            banList.addAll(bannerlist);
            //加载banner
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mBan.setImageLoader(new ImageLoader() {
                        @Override
                        public void displayImage(Context context, Object path, ImageView imageView) {
                            BanInfo.BannerlistBean BD= (BanInfo.BannerlistBean) path;
                            //Glide加载图片
                            Glide.with(context).load(BD.getImageurl()).into(imageView);
                        }
                    }).start();//启动baner
                }
            });


        }

    }
}
