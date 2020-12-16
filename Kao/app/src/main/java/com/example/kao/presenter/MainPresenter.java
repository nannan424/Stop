package com.example.kao.presenter;

import com.example.kao.HomeActivity;
import com.example.kao.bean.BanInfo;
import com.example.kao.model.MainModel;
import com.example.kao.view.MainView;

public class MainPresenter {
    private MainView mainView;
    private final MainModel mainModel;

    public MainPresenter(MainView mainView) {
        this.mainView = mainView;
        //m层对象
        mainModel = new MainModel();
    }

    public void getBan() {
        mainModel.getBan(new Resutlcallback() {
            @Override
            public void oneSuecces(Object object) {
                //返回到v层将请求好的数据
                mainView.oneSuecces(object);
            }
        });
    }

    public void getList() {
        mainModel.getList(new Resutlcallback() {
            @Override
            public void oneSuecces(Object object) {
                //返回到v层将请求好的数据
                mainView.oneSuecces(object);
            }
        });
    }

    public void getStudent() {
        mainModel.getStudent(new Resutlcallback() {
            @Override
            public void oneSuecces(Object object) {
                //返回到v层将请求好的数据
                mainView.oneSuecces(object);
            }
        });
    }
}
