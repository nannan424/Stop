package com.example.kao.model;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.kao.bean.BanInfo;
import com.example.kao.bean.ListInfo;
import com.example.kao.bean.StudentInfo;
import com.example.kao.net.Apiservice;
import com.example.kao.presenter.Resutlcallback;
import com.youth.banner.loader.ImageLoader;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subscribers.ResourceSubscriber;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainModel {
    public void getBan(final Resutlcallback resutlcallback) {
       new Retrofit.Builder()
               .addConverterFactory(GsonConverterFactory.create())
               .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
               .baseUrl(Apiservice.url)
               .build()
               .create(Apiservice.class)
               .getBan()
               .subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread())
               .subscribeWith(new ResourceSubscriber<BanInfo>() {
                   @Override
                   public void onNext(BanInfo banInfo) {
                       resutlcallback.oneSuecces(banInfo);
                   }

                   @Override
                   public void onError(Throwable t) {

                   }

                   @Override
                   public void onComplete() {

                   }
               });
    }

    public void getList(final Resutlcallback resutlcallback) {
        //rxjava+retrofit解析
        new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(Apiservice.url)
                .build()
                .create(Apiservice.class)
                .getList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new ResourceSubscriber<ListInfo>() {
                    @Override
                    public void onNext(ListInfo listInfo) {
                        //请求好的网络数据返回到p层
                        resutlcallback.oneSuecces(listInfo);
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void getStudent(final Resutlcallback resutlcallback) {
        //网络解析
        new Retrofit.Builder()
                .baseUrl(Apiservice.url)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(Apiservice.class)
                .getStudent()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new ResourceSubscriber<StudentInfo>() {
                    @Override
                    public void onNext(StudentInfo studentInfo) {
                        resutlcallback.oneSuecces(studentInfo);
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
