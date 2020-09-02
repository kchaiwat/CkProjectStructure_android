package com.projectstructure.ck.utilitylibrary.view;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import com.projectstructure.ck.utilitylibrary.R;

import java.util.Objects;

/**
 * Created by nakharin on 8/22/2017 AD.
 */

public class PzLoadingDialogView extends DialogFragment {

    private static final String TAG = "PzLoadingDialogView";

    private static final String KEY_CANCELABLE = "KEY_CANCELABLE";
    private static final String KEY_TITLE = "KEY_TITLE";

    private ProgressBar progressBar;
    private TextView tvTitle;

    private String title = "";
    private boolean cancelable = true;

    public static PzLoadingDialogView newInstance(boolean cancelable) {
        PzLoadingDialogView fragment =
                new PzLoadingDialogView();
        Bundle bundle = new Bundle();
        bundle.putBoolean(KEY_CANCELABLE, cancelable);
        bundle.putString(KEY_TITLE, "Please waiting...");
        fragment.setCancelable(cancelable);
        fragment.setArguments(bundle);
        return fragment;
    }


    public static PzLoadingDialogView newInstance(String title, boolean cancelable) {
        PzLoadingDialogView fragment = new PzLoadingDialogView();
        Bundle bundle = new Bundle();
        bundle.putBoolean(KEY_CANCELABLE, cancelable);
        bundle.putString(KEY_TITLE, title);
        fragment.setCancelable(cancelable);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            restoreArguments(getArguments());
        } else {
            restoreInstanceState(savedInstanceState);
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        dialog.setCancelable(cancelable);
        dialog.setCanceledOnTouchOutside(cancelable);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        try {
            Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.view_pz_loading_dialog, container);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Method from this class
        bindView(view);
        // Method from this class
        setupView();
    }

    private void bindView(View v) {
        progressBar = v.findViewById(R.id.loading_dialog_progress_bar);
        tvTitle = v.findViewById(R.id.loading_dialog_text_view_title);

    }

    private void setupView() {
        tvTitle.setText(title);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(KEY_CANCELABLE, cancelable);
        outState.putString(KEY_TITLE, title);
    }

    private void restoreInstanceState(Bundle bundle) {
        cancelable = bundle.getBoolean(KEY_CANCELABLE);
        title = bundle.getString(KEY_TITLE);
    }

    private void restoreArguments(Bundle bundle) {
        cancelable = bundle.getBoolean(KEY_CANCELABLE);
        title = bundle.getString(KEY_TITLE);
    }

    public void showLoading(@NonNull FragmentManager manager) {
        if (!this.isVisible()) {
            try {
                this.show(manager, "dialog");
            } catch (IllegalStateException e) {
                Log.i(TAG, e.getLocalizedMessage());
            }
        }
    }

    public void hideLoading() {
        if (this.isVisible()) {
            try {
                this.dismiss();
            } catch (IllegalStateException e) {
                Log.i(TAG, e.getLocalizedMessage());
            }
        }
    }
}
