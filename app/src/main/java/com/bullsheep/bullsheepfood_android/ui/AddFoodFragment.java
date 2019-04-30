package com.bullsheep.bullsheepfood_android.ui;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bullsheep.bullsheepfood_android.R;
import com.bullsheep.bullsheepfood_android.dto.ProductDTO;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddFoodFragment extends Fragment {

    private static final String TAG = AddFoodFragment.class.getName();

    private EditText nameEt;
    private EditText proteinsEt;
    private EditText fatsEt;
    private EditText carbsEt;
    private EditText kCalEt;
    private Button submitBtn;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_food, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUI(view);
    }

    private void initUI(View view) {
        initViews(view);
        initClickListeners();
    }

    private void initViews(View view) {
        nameEt = view.findViewById(R.id.add_food_name_et);
        proteinsEt = view.findViewById(R.id.add_food_proteins_et);
        fatsEt = view.findViewById(R.id.add_food_fats_et);
        carbsEt = view.findViewById(R.id.add_food_carbs_et);
        kCalEt = view.findViewById(R.id.add_food_kcal_et);
        submitBtn = view.findViewById(R.id.fragment_add_food_submit_btn);
    }

    private void initClickListeners() {
        submitBtn.setOnClickListener((view) -> submitData());
    }

    private void submitData() {
        if (isEmpty(nameEt, proteinsEt, fatsEt, carbsEt, kCalEt)) {
            Log.i(TAG, "submitData: some filed is empty!");
            return;
        }

        ProductDTO product = readData();
        Log.i(TAG, "submitData: " + product);
        // TODO: 30.04.2019 send to back
    }

    private ProductDTO readData() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setName(nameEt.getText().toString());
        productDTO.setProteins(Float.valueOf(proteinsEt.getText().toString()));
        productDTO.setFats(Float.valueOf(fatsEt.getText().toString()));
        productDTO.setCarbs(Float.valueOf(carbsEt.getText().toString()));
        productDTO.setCal(Float.valueOf(kCalEt.getText().toString()));

        return productDTO;
    }

    private boolean isEmpty(@NonNull EditText... editTexts) {
        boolean isEmpty = false;
        for (EditText editText : editTexts) {
            if (editText.getText().toString().trim().length() == 0) {
                editText.setError(getActivity().getString(R.string.error_field_required));
                isEmpty = true;
            }
        }
        return isEmpty;
    }
}
