package com.bullsheep.bullsheepfood_android.ui.add_food;


import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.bullsheep.bullsheepfood_android.R;
import com.bullsheep.bullsheepfood_android.model.Product;
import com.bullsheep.bullsheepfood_android.ui.ParseUtil;
import com.bullsheep.bullsheepfood_android.ui.utils.ActivityUtils;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.text.FirebaseVisionText;
import com.google.firebase.ml.vision.text.FirebaseVisionTextRecognizer;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class AddFoodFragment extends Fragment {

    private static final String TAG = AddFoodFragment.class.getName();
    private static final int REQUEST_IMAGE_CAPTURE_CODE = 1024;

    private EditText nameEt;
    private EditText proteinsEt;
    private EditText fatsEt;
    private EditText carbsEt;
    private EditText kCalEt;
    private Button submitBtn;
    private ImageView scanIv;

    private ProgressBar loadingIndicator;

    private ProductViewModel productViewModel;

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

        productViewModel = ViewModelProviders.of(this).get(ProductViewModel.class);
        productViewModel.isProcessed.observe(this, isProcessed -> {
            if (isProcessed) {
                loadingIndicator.setVisibility(View.GONE);
                getActivity().getSupportFragmentManager().popBackStack();
            } else {
                loadingIndicator.setVisibility(View.VISIBLE);
            }
        });
    }

    private void initUI(View view) {
        initViews(view);
        initClickListeners();
    }

    private void initViews(View view) {
        scanIv = view.findViewById(R.id.fragment_add_food_scan_iv);
        nameEt = view.findViewById(R.id.add_food_name_et);
        proteinsEt = view.findViewById(R.id.add_food_proteins_et);
        fatsEt = view.findViewById(R.id.add_food_fats_et);
        carbsEt = view.findViewById(R.id.add_food_carbs_et);
        kCalEt = view.findViewById(R.id.add_food_kcal_et);
        submitBtn = view.findViewById(R.id.fragment_add_food_submit_btn);
        loadingIndicator = view.findViewById(R.id.loading_indicator);
    }

    private void initClickListeners() {
        submitBtn.setOnClickListener((view) -> submitData());
        scanIv.setOnClickListener((view) -> requestTakePictureActivity());
    }


    private void requestTakePictureActivity() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE_CODE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE_CODE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            if (extras != null) {
                Bitmap bitmap = (Bitmap) extras.get("data");
                runTextRecognition(bitmap);
            }
        }
    }

    private void submitData() {
        if (isEmpty(nameEt, proteinsEt, fatsEt, carbsEt, kCalEt)) {
            Log.i(TAG, "submitData: some filed is empty!");
            return;
        }

        ActivityUtils.hideKeyboard(getActivity());
        Product product = readData();
        Log.i(TAG, "submitData: " + product);
        productViewModel.saveProduct(product);
    }


    private void runTextRecognition(Bitmap imageBitmap) {
        FirebaseVisionImage image = FirebaseVisionImage.fromBitmap(imageBitmap);
        FirebaseVisionTextRecognizer recognizer = FirebaseVision.getInstance()
                .getOnDeviceTextRecognizer();
        scanIv.setEnabled(false);
        recognizer.processImage(image)
                .addOnSuccessListener(
                        texts -> {
                            scanIv.setEnabled(true);
                            processTextRecognitionResult(texts);
                        })
                .addOnFailureListener(
                        e -> {
                            // Task failed with an exception
                            Toast.makeText(this.getContext(),
                                    "Fail to process", Toast.LENGTH_LONG).show();
                            scanIv.setEnabled(true);
                            e.printStackTrace();
                        });
    }

    private void processTextRecognitionResult(FirebaseVisionText texts) {
        List<FirebaseVisionText.TextBlock> blocks = texts.getTextBlocks();
        StringBuilder sb = new StringBuilder("Result : ");
        if (blocks.size() == 0) {
            String empty = "No text found";
            Toast.makeText(this.getContext(), empty, Toast.LENGTH_LONG).show();
            sb.append(empty);
            return;
        }
        for (int i = 0; i < blocks.size(); i++) {
            List<FirebaseVisionText.Line> lines = blocks.get(i).getLines();
            for (int j = 0; j < lines.size(); j++) {
                sb.append("\n");
                List<FirebaseVisionText.Element> elements = lines.get(j).getElements();
                for (int k = 0; k < elements.size(); k++) {
                    FirebaseVisionText.Element element = elements.get(k);
                    String text = element.getText();
                    Log.d("ELEMENT: ", text);
                    sb.append(text);
                }
            }
        }
        Log.d(TAG, sb.toString());
        show(sb.toString());
    }

    private void show(String text) {
        proteinsEt.setText(ParseUtil.parseProtein(text));
        kCalEt.setText(ParseUtil.parseCalories(text));
        carbsEt.setText(ParseUtil.parseCarb(text));
        fatsEt.setText(ParseUtil.parseFat(text));
    }

    private Product readData() {
        return new Product(
                nameEt.getText().toString(),
                Double.valueOf(kCalEt.getText().toString()),
                Double.valueOf(proteinsEt.getText().toString()),
                Double.valueOf(fatsEt.getText().toString()),
                Double.valueOf(carbsEt.getText().toString()));
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
