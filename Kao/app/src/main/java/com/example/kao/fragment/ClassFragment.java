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
import com.example.kao.adapter.ClassAdapter;
import com.example.kao.bean.StudentInfo;
import com.example.kao.presenter.MainPresenter;
import com.example.kao.view.MainView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ClassFragment extends Fragment implements MainView {


    private RecyclerView rel;
    private ArrayList<StudentInfo.StudenlistBean> list;
    private MainPresenter mainPresenter;
    private ClassAdapter classAdapter;

    public ClassFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_class, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //创建p层对象
        mainPresenter = new MainPresenter(this);
        //初始化布局页面
        initView(getView());
        initData();
    }

    private void initData() {
        mainPresenter.getStudent();
    }

    private void initView(View view) {
        //找控件
        rel = view.findViewById(R.id.rel);
        //布局管理器
        rel.setLayoutManager(new LinearLayoutManager(getContext()));
        //分割线
        rel.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
       //初始化数据源
        list = new ArrayList<>();
        //创建适配器
        classAdapter = new ClassAdapter(list, getContext());
        rel.setAdapter(classAdapter);
        //
        classAdapter.setItemClikl(new ClassAdapter.ItemClik() {
            @Override
            public void longclik(int position) {
                //长按监听。完成删除功能
                list.remove(list.get(position));
                //刷新适配器
                classAdapter.notifyDataSetChanged();//刷新
            }
        });


    }

    @Override
    public void oneSuecces(Object object) {
        //回调方法。进行强制
        if(object instanceof StudentInfo){
            StudentInfo info= (StudentInfo) object;
            List<StudentInfo.StudenlistBean> studenlist = info.getStudenlist();
            //添加到集合里
            list.addAll(studenlist);
            //刷新适配器
            classAdapter.notifyDataSetChanged();//刷新

        }

    }
}
