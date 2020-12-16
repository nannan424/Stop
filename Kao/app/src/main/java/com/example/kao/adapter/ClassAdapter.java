package com.example.kao.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.kao.R;
import com.example.kao.bean.StudentInfo;

import java.util.ArrayList;

public class ClassAdapter extends RecyclerView.Adapter {
    private ArrayList<StudentInfo.StudenlistBean>list;
    private Context context;
    private ItemClik itemClikl;//接口，set赋值

    public void setItemClikl(ItemClik itemClikl) {
        this.itemClikl = itemClikl;
    }

    public ClassAdapter(ArrayList<StudentInfo.StudenlistBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //加载布局
        View inflate = LayoutInflater.from(context).inflate(R.layout.class_item, parent, false);

        return new HomeHolder(inflate);//返回到onBindView
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
      //强制转型
        HomeHolder homeHolder= (HomeHolder) holder;
        homeHolder.tv_1.setText("姓名"+list.get(position).getName());
        homeHolder.tv_2.setText("机试成绩"+list.get(position).getSkill()+"分");
        homeHolder.tv_3.setText("理论成绩"+list.get(position).getTheory()+"分");
        //给条目绑定数据
        //监听
        homeHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                itemClikl.longclik(position);
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();//集合的数量

    }
    class HomeHolder extends RecyclerView.ViewHolder{
private TextView tv_1;
private TextView tv_2;
private TextView tv_3;
//获取条目控件绑定数据
        public HomeHolder(@NonNull View itemView) {
            super(itemView);
            tv_1=itemView.findViewById(R.id.tv_1);
            tv_3=itemView.findViewById(R.id.tv_3);
            tv_2=itemView.findViewById(R.id.tv_2);
        }
    }
    //自定义接口完成删除功能
    public interface ItemClik{
        void  longclik(int position);
    }
}
