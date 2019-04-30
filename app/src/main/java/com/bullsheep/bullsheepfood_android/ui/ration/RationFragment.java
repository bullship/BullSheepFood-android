package com.bullsheep.bullsheepfood_android.ui.ration;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bullsheep.bullsheepfood_android.R;
import com.bullsheep.bullsheepfood_android.dto.ProductDTO;
import com.bullsheep.bullsheepfood_android.dto.RationDTO;

import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class RationFragment extends Fragment {

    private RecyclerView recyclerView;

    public RationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ration, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUI(view);
    }

    private void initUI(View view) {
        recyclerView = view.findViewById(R.id.fragment_ration_main_recycler);
        initRecyclerView();

    }

    private void initRecyclerView() {
        RationAdapter adapter = new RationAdapter(generateMockData(), getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
    }

    //TODO should be data from server
    private List<RationDTO> generateMockData() {
        RationDTO rationDTO = new RationDTO();

        rationDTO.setImageUrl("https://media.eggs.ca/assets/RecipePhotos/_resampled/FillWyIxMjgwIiwiNzIwIl0/breafast-tostada-031.jpg");
        rationDTO.setTitle("Breakfast");

        ProductDTO product = new ProductDTO();
        product.setName("Bread");
        product.setCarbs(23.5f);
        product.setFats(23.5f);
        product.setProteins(23.5f);
        product.setCal(254.34f);
        rationDTO.setProducts(Arrays.asList(product, product, product));

        RationDTO rationDTO1 = new RationDTO();
        rationDTO1.setTitle("Lunch");
        rationDTO1.setImageUrl("https://crackerbarrel.com/-/media/CrackerBarrel/Menu/Dinner/Weekday-Lunch-Specials/Pick-Two_Country_Combo_WLS_780x390.jpg?h=390&w=780&la=en&hash=894DFB8FFABF066A98CB0000F78F442655A01BAE");
        rationDTO1.setProducts(Arrays.asList(product, product, product));

        RationDTO rationDTO3 = new RationDTO();
        rationDTO3.setTitle("Dinner");
        rationDTO3.setImageUrl("https://keyassets-p2.timeincuk.net/wp/prod/wp-content/uploads/sites/53/2019/02/Slimming-Worlds-roast-dinner.jpg");
        rationDTO3.setProducts(Arrays.asList(product, product, product));

        return Arrays.asList(rationDTO, rationDTO1, rationDTO3, rationDTO, rationDTO1, rationDTO3);
    }

}
