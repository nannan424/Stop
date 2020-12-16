package com.example.kao.fragment;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kao.R;
import com.example.kao.adapter.SchoolAdapter;
import com.example.kao.bean.ListInfo;
import com.example.kao.presenter.MainPresenter;
import com.example.kao.view.MainView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ScholFragment extends Fragment implements MainView {


    private ArrayList<ListInfo.NewsBean> list;
    private MainPresenter mainPresenter;
    private RecyclerView rel;
    private SchoolAdapter schoolAdapter;

    public ScholFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_schol, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //创建p层对象
        mainPresenter = new MainPresenter(this);
        //初始护页面布局
        initView(getView());
        initData();
    }

    private void initData() {
        //p层调方法
        mainPresenter.getList();
    }

    private void initView(View view) {
        //找控件
        rel =view.findViewById(R.id.rel);
        //布局管理器
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        rel.setLayoutManager(manager);//设置布局管理器
        //分割线
        rel.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        //初始化数据源
        list = new ArrayList<>();
        //适配器
        schoolAdapter = new SchoolAdapter(list, getContext());
        //设置适配器
        rel.setAdapter(schoolAdapter);


    }

    @Override
    public void oneSuecces(Object object) {
        //强制转型
        if(object instanceof ListInfo){
            ListInfo info= (ListInfo) object;
            List<ListInfo.NewsBean> news = info.getNews();
            //添加到集合里面
            list.addAll(news);
            //刷新适配器
            schoolAdapter.notifyDataSetChanged();

        }

    }
}
