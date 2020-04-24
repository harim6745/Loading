package org.techtown.loading;

import android.app.Activity;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class GraphAdapter extends RecyclerView.Adapter<GraphAdapter.CustomViewHolder>{
    private ArrayList<GraphData> mList = null;
    private Activity context = null;


    public GraphAdapter(Activity context, ArrayList<GraphData> list) {
        this.context = context;
        this.mList = list;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        protected TextView prod_name;
        protected TextView prod_barcode;
        protected TextView prod_carbo;
        protected TextView prod_protein;
        protected TextView prod_sodium;
        protected TextView prod_fat;
        protected TextView prod_sugar;
        protected TextView prod_saturated_fat;
        protected TextView prod_kcal;




        public CustomViewHolder(View view) {
            super(view);
            this.prod_name = (TextView) view.findViewById(R.id.textView_list_name);
            this.prod_barcode = (TextView) view.findViewById(R.id.textView_list_barcode);
            this.prod_carbo = (TextView) view.findViewById(R.id.textView_list_carbo);
            this.prod_protein = (TextView) view.findViewById(R.id.textView_list_protein);
            this.prod_sodium = (TextView) view.findViewById(R.id.textView_list_sodium);
            this.prod_fat = (TextView) view.findViewById(R.id.textView_list_fat);
            this.prod_sugar = (TextView) view.findViewById(R.id.textView_list_sugar);
            this.prod_saturated_fat = (TextView) view.findViewById(R.id.textView_list_saturated_fat);
            this.prod_kcal = (TextView) view.findViewById(R.id.textView_list_kcal);

        }
    }


    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.health_list, null);
        CustomViewHolder viewHolder = new CustomViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder viewholder, int position) {

        viewholder.prod_name.setText(mList.get(position).getProd_name());
        viewholder.prod_barcode.setText(mList.get(position).getProd_barcode());
        viewholder.prod_carbo.setText(mList.get(position).getProd_carbo());
        viewholder.prod_protein.setText(mList.get(position).getProd_protein());
        viewholder.prod_sodium.setText(mList.get(position).getProd_sodium());
        viewholder.prod_fat.setText(mList.get(position).getProd_fat());
        viewholder.prod_sugar.setText(mList.get(position).getProd_sugar());
        viewholder.prod_saturated_fat.setText(mList.get(position).getProd_saturated_fat());
        viewholder.prod_kcal.setText(mList.get(position).getProd_kcal());
    }

    @Override
    public int getItemCount() {
        return (null != mList ? mList.size() : 0);
    }
}
