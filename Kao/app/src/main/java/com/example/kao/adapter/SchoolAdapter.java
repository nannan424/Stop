package com.example.kao.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.kao.R;
import com.example.kao.bean.ListInfo;

import java.util.ArrayList;

public class SchoolAdapter extends RecyclerView.Adapter {
    //创建学校新闻对应的适配器
    private ArrayList<ListInfo.NewsBean>list;
    private Context context;

    public SchoolAdapter(ArrayList<ListInfo.NewsBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       //加载条目布局
        View inflate = LayoutInflater.from(context).inflate(R.layout.school_item1, parent, false);
        return new HomeHolder(inflate);
        //返回到bindView
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
       //给条目设置数据
        //强制转型
        HomeHolder homeHolder= (HomeHolder) holder;
        homeHolder.tv_1.setText(list.get(position).getTile());
        homeHolder.tv_2.setText(list.get(position).getContent());
        //加载图片
        Glide.with(context).load(list.get(position).getImageUrl()).into(homeHolder.img);
    }

    @Override
    public int getItemCount() {
        return list.size();//条目数量
    }
    class HomeHolder extends RecyclerView.ViewHolder{
        //给条目绑定数据
private TextView tv_1;
private TextView tv_2;
private ImageView img;
        public HomeHolder(@NonNull View itemView) {
            super(itemView);
            tv_1=itemView.findViewById(R.id.tv_1);
            tv_2=itemView.findViewById(R.id.tv_2);
            img=itemView.findViewById(R.id.img);

        }
    }
}
