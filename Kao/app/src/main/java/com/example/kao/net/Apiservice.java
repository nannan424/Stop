package com.example.kao.net;

import com.example.kao.bean.BanInfo;
import com.example.kao.bean.ListInfo;
import com.example.kao.bean.StudentInfo;

import io.reactivex.Flowable;
import retrofit2.http.GET;

public interface Apiservice {
    //http://cdwan.cn:7000/exam2003/anewslist.json
    //http://cdwan.cn:7000/exam2003/abannerlist.json
    //http://cdwan.cn:7000/exam2003/astudent.json
    String url="http://cdwan.cn:7000/exam2003/";
    @GET("anewslist.json")
    Flowable<ListInfo>getList();

    @GET("abannerlist.json")
    Flowable<BanInfo>getBan();

    @GET("astudent.json")
    Flowable<StudentInfo>getStudent();
}
