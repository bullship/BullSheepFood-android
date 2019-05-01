package com.bullsheep.bullsheepfood_android.ui.stats.actions;

import android.app.Dialog;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.bullsheep.bullsheepfood_android.R;
import com.bullsheep.bullsheepfood_android.ui.add_food.AddFoodFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.Fragment;

/**
 * Actions menu for FAB
 */
public class ActionDialogFragment extends AppCompatDialogFragment {

    private static final int BOTTOM_MARGIN_IN_DP = 75;

    private TextView addItemTv;
    private TextView addSchedule;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        configureDialog();
        return inflater.inflate(R.layout.fragment_action_dialog, container);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initUI(view);
    }

    private void configureDialog() {
        Dialog dialog = getDialog();
        if (dialog != null && dialog.getWindow() != null) {
            Window window = dialog.getWindow();
            window.setGravity(Gravity.BOTTOM | Gravity.END);
            WindowManager.LayoutParams layoutParams = getDialog().getWindow().getAttributes();
            layoutParams.y = (int) (BOTTOM_MARGIN_IN_DP * Resources.getSystem().getDisplayMetrics().density);
            window.setAttributes(layoutParams);
            getDialog().getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;

        }
    }

    private void initUI(View view) {
        addItemTv = view.findViewById(R.id.add_item);
        addSchedule = view.findViewById(R.id.add_schedule);
        initClickListeners();
    }

    private void initClickListeners() {
        addItemTv.setOnClickListener(view -> {
            startFragment(new AddFoodFragment());
            dismiss();
        });

        addSchedule.setOnClickListener(view -> {
            Toast.makeText(getActivity(), getString(R.string.not_implemented_text), Toast.LENGTH_SHORT).show();
            dismiss();
        });
    }

    private void startFragment(Fragment fragment) {
        if (getActivity() != null) {
            getActivity()
                    .getSupportFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations(R.anim.slide_in, R.anim.slide_out)
                    .addToBackStack(null)
                    .replace(R.id.fragment_container, fragment)
                    .commit();
        }
    }
}
