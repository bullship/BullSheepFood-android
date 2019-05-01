package com.bullsheep.bullsheepfood_android.ui.ration;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bullsheep.bullsheepfood_android.R;
import com.bullsheep.bullsheepfood_android.dto.ProductDTO;

import java.util.List;

public class RationDetailAdapter extends RecyclerView.Adapter<RationDetailAdapter.RationDetailViewHolder> {

    private List<ProductDTO> products;

    public RationDetailAdapter(List<ProductDTO> products) {
        this.products = products;
    }

    @NonNull
    @Override
    public RationDetailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.ration_detail_item, parent, false);
        return new RationDetailViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RationDetailViewHolder holder, int position) {
        final String name = products.get(position).getName();
        holder.nameTv.setText(name);
    }

    @Override
    public int getItemCount() {
        return products != null ? products.size() : 0;
    }

    static class RationDetailViewHolder extends RecyclerView.ViewHolder {

        private TextView nameTv;

        RationDetailViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTv = itemView.findViewById(R.id.ration_detail_name_tv);
        }
    }
}
