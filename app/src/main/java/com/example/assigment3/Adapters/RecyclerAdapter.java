package com.example.assigment3.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assigment3.ModelClass.ModelClass;
import com.example.assigment3.R;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {
    public List<ModelClass> modelClasslist;
    private onListItemClickListener onListitemClickListener;

    public RecyclerAdapter(List<ModelClass> modelClasslist, onListItemClickListener onListitemClickListener) {
        this.modelClasslist = modelClasslist;
        this.onListitemClickListener = onListitemClickListener;
    }




    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        final  ModelClass modelClass=modelClasslist.get(position);
        String name = modelClasslist.get(position).getMname();
        int std = modelClasslist.get(position).getMclass();
        holder.setData(name,std);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //onListitemClickListener.onListItemClick(model);
                onListitemClickListener.onListItemClick(modelClass);

            }
        });

    }

    @Override
    public int getItemCount() {
        return modelClasslist.size();
    }

    public interface onListItemClickListener {
        void onListItemClick(ModelClass modelClass);

    }

    public class MyViewHolder extends RecyclerView.ViewHolder{


        private TextView tvName;
        private TextView tvClass;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tv_name_of_stu);
            tvClass = itemView.findViewById(R.id.tv_class_of_stu);
        }

        private void setData(String name,int std)
        {
            tvName.setText(name);
            tvClass.setText(Integer.toString(std));
        }
    }
}
