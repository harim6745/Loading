package org.techtown.loading;

import android.app.Activity;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.CustomViewHolder> {

    private ArrayList<ProductData> mList = null;
    private Activity context = null;


    public ProductsAdapter(Activity context, ArrayList<ProductData> list) {
        this.context = context;
        this.mList = list;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        protected TextView product;


        public CustomViewHolder(View view) {
            super(view);
            this.product = (TextView) view.findViewById(R.id.textView_list_product);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
        }
    }


    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.product_list, null);
        CustomViewHolder viewHolder = new CustomViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder viewholder, int position) {

        viewholder.product.setText(mList.get(position).getProduct());
    }

    @Override
    public int getItemCount() {
        return (null != mList ? mList.size() : 0);
    }

}