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

import com.bullsheep.bullsheepfood_android.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

public class ActionDialogFragment extends AppCompatDialogFragment {

    private static final int BOTTOM_MARGIN_IN_DP = 75;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        configureDialog();
        return inflater.inflate(R.layout.fragment_action_dialog, container);
    }

    private void configureDialog() {
        Dialog dialog = getDialog();
        if (dialog != null && dialog.getWindow() != null) {
            Window window = dialog.getWindow();
            window.setGravity(Gravity.BOTTOM | Gravity.END);
            WindowManager.LayoutParams layoutParams = getDialog().getWindow().getAttributes();
            layoutParams.y = (int) (BOTTOM_MARGIN_IN_DP * Resources.getSystem().getDisplayMetrics().density);
            window.setAttributes(layoutParams);

        }
    }
}
