package org.techtown.loading;

import android.app.Activity;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.CustomViewHolder> {

    private ArrayList<PersonalData> mList = null;
    private Activity context = null;


    public UsersAdapter(Activity context, ArrayList<PersonalData> list) {
        this.context = context;
        this.mList = list;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        protected TextView user_id;
        protected TextView content;
        protected TextView grade;
        protected TextView date;


        public CustomViewHolder(View view) {
            super(view);
            this.user_id = (TextView) view.findViewById(R.id.textView_list_user_id);
            this.content = (TextView) view.findViewById(R.id.textView_list_content);
            this.grade = (TextView) view.findViewById(R.id.textView_list_grade);
            this.date = (TextView) view.findViewById(R.id.textView_list_date);
        }
    }


    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_list, null);
        CustomViewHolder viewHolder = new CustomViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder viewholder, int position) {

        viewholder.user_id.setText(mList.get(position).getUser_id());
        viewholder.content.setText(mList.get(position).getContent());
        viewholder.grade.setText(mList.get(position).getGrade());
        viewholder.date.setText(mList.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return (null != mList ? mList.size() : 0);
    }

}