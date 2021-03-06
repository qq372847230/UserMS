package com.ms.userms.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ms.userms.R;
import com.ms.userms.models.Info;

import java.util.ArrayList;

public class MSRecycleAdapter extends  RecyclerView.Adapter<MSRecycleAdapter.MyHolder> {

    private ArrayList<Info> list;
    private Context context;
    private Callback callback;

    public MSRecycleAdapter(Context context, ArrayList<Info> list,Callback callback){
        this.list = list;
        this.context = context;
        this.callback = callback;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //将我们自定义的item布局R.layout.item_one转换为View
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recy_item, parent, false);
        //将view传递给我们自定义的ViewHolder
        //返回这个MyHolder实体
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        holder.tv_name.setText(list.get(position).getName());
        holder.tv_phone.setText(list.get(position).getPhone());
        holder.checkBox.setChecked(list.get(position).isCheck());
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("TAG", "onClick: "+list.get(position).getId());
                callback.click(list.get(position).getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyHolder extends RecyclerView.ViewHolder {

        TextView tv_name;
        TextView tv_phone;
        CheckBox checkBox;

        public MyHolder(View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.name);
            tv_phone = itemView.findViewById(R.id.phone);
            checkBox = itemView.findViewById(R.id.checmsg);
        }
    }

    public interface Callback {
        public void click(int v);
    }


}
