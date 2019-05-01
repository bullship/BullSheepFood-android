package com.bullsheep.bullsheepfood_android.ui.ration;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bullsheep.bullsheepfood_android.R;
import com.bullsheep.bullsheepfood_android.dto.RationDTO;
import com.bullsheep.bullsheepfood_android.model.Product;
import com.bumptech.glide.Glide;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RationAdapter extends RecyclerView.Adapter<RationAdapter.RationViewHolder> {

    private List<RationDTO> rations;
    private Context context;

    public RationAdapter(List<RationDTO> rations, Context context) {
        this.rations = rations;
        this.context = context;
    }

    @NonNull
    @Override
    public RationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.ration_item, parent, false);
        return new RationViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RationViewHolder holder, int position) {
        final RationDTO ration = rations.get(position);
        Glide.with(context)
                .load(ration.getImageUrl())
                .centerCrop()
                .into(holder.image);

        holder.titleTv.setText(ration.getTitle());

        List<Product> products = ration.getProducts();
        holder.nutrientsTv.setText("Proteins: " + calculateProteins(products) +
                ", Fats: " + calculateFats(products) +
                ", Carbs: " + calculateCarbs(products));
        holder.totalTv.setText("Total: " + calculateKCal(products) + " kCal");

        RationDetailAdapter adapter = new RationDetailAdapter(ration.getProducts());
        holder.namesRecycler.setLayoutManager(new LinearLayoutManager(context, RecyclerView.VERTICAL, false));
        holder.namesRecycler.setAdapter(adapter);
    }

    @Override
    public int getItemCount() {
        return rations != null ? rations.size() : 0;
    }

    private float calculateFats(@NonNull List<Product> products) {
        float result = 0;
        for (Product product : products) {
            result += product.getFat();
        }
        return result;
    }

    private float calculateProteins(@NonNull List<Product> products) {
        float result = 0;
        for (Product product : products) {
            result += product.getProtein();
        }
        return result;
    }

    private float calculateCarbs(@NonNull List<Product> products) {
        float result = 0;
        for (Product product : products) {
            result += product.getCarbohydrate();
        }
        return result;
    }

    private float calculateKCal(@NonNull List<Product> products) {
        float result = 0;
        for (Product product : products) {
            result += product.getkCal();
        }
        return result;
    }

    class RationViewHolder extends RecyclerView.ViewHolder {
        private ImageView image;
        private TextView titleTv;
        private RecyclerView namesRecycler;
        private TextView nutrientsTv;
        private TextView totalTv;
        private ImageView downImg;

        RationViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.item_ration_image);
            titleTv = itemView.findViewById(R.id.item_ration_title_tv);
            namesRecycler = itemView.findViewById(R.id.item_ration_recycler);
            nutrientsTv = itemView.findViewById(R.id.item_ration_nutrients_tv);
            totalTv = itemView.findViewById(R.id.item_ration_total_tv);
            downImg = itemView.findViewById(R.id.item_ration_down_img);

            itemView.setOnClickListener(view -> {
                boolean isExpanded = namesRecycler.getVisibility() != View.GONE;
                namesRecycler.setVisibility(isExpanded ? View.GONE : View.VISIBLE);
                downImg.setImageResource(isExpanded ? R.drawable.ic_arrow_down : R.drawable.ic_arrow_up);
            });
        }
    }
}
