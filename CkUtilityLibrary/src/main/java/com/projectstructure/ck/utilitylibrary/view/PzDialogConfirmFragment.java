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
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.projectstructure.ck.utilitylibrary.BuildConfig;
import com.projectstructure.ck.utilitylibrary.R;

import java.util.Objects;

public class PzDialogConfirmFragment extends DialogFragment {

    private static final String KEY_TAG = "KEY_TAG";
    private static final String KEY_CANCELABLE = "KEY_CANCELABLE";
    private static final String KEY_AUTO_DISMISS = "KEY_AUTO_DISMISS";
    private static final String KEY_STATE = "KEY_STATE";
    private static final String KEY_TITLE = "KEY_TITLE";
    private static final String KEY_MESSAGE = "KEY_MESSAGE";
    private static final String KEY_STR_OK = "KEY_STR_OK";
    private static final String KEY_STR_CANCEL = "KEY_STR_CANCEL";
    private static final String KEY_SINGLE = "KEY_SINGLE";

    private int tag = 0;
    private boolean single = true;
    private boolean cancelable = true;
    private boolean autoDismiss = false;
    private State state = State.NORMAL;
    private String title = "";
    private String message = "";
    private String strOk = "";
    private String strCancel = "";

    private OnOneDialogListener onOneDialogListener = null;
    private OnTwoDialogListener onTwoDialogListener = null;

    private TextView tvTitle;
    private TextView tvMessage;

    private TextView btnOk;
    private TextView btnCancel;

    public enum State {
        NORMAL(0), SUCCESS(1), FAILED(2), DELETE(3), UPLOAD(4);

        private int id;

        State(int id) {
            this.id = id;
        }

        static State fromId(int id) {
            for (State state : values()) {
                if (state.id == id) {
                    return state;
                }
            }
            throw new IllegalArgumentException();
        }
    }

    public static PzDialogConfirmFragment newInstance(int tag, boolean cancelable, boolean autoDismiss, State state, boolean single, String title, String message, String strOk, String strCancel) {
        PzDialogConfirmFragment fragment = new PzDialogConfirmFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(KEY_TAG, tag);
        bundle.putBoolean(KEY_CANCELABLE, cancelable);
        bundle.putBoolean(KEY_AUTO_DISMISS, autoDismiss);
        bundle.putInt(KEY_STATE, state.ordinal());
        bundle.putBoolean(KEY_SINGLE, single);
        bundle.putString(KEY_TITLE, title);
        bundle.putString(KEY_MESSAGE, message);
        bundle.putString(KEY_STR_OK, strOk);
        bundle.putString(KEY_STR_CANCEL, strCancel);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            assert getArguments() != null;
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
        return inflater.inflate(R.layout.fragment_dialog_confirm, container);
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

        tvTitle = v.findViewById(R.id.tv_title);
        tvMessage = v.findViewById(R.id.tv_message);

        btnOk = v.findViewById(R.id.btn_ok);
        btnCancel = v.findViewById(R.id.btn_cancel);
    }

    private void setupView() {

        tvTitle.setText(title);
//        tvMessage.setText(Html.fromHtml(message));
        tvMessage.setText(message);
        btnOk.setText(strOk);
        btnCancel.setText(strCancel);

        if (single) {
            btnCancel.setVisibility(View.GONE);
        } else {
            btnCancel.setVisibility(View.VISIBLE);
        }

        switch (state) {
            case NORMAL:
                tvTitle.setTextColor(ContextCompat.getColor(Objects.requireNonNull(getContext()), R.color.colorPrimary));
                tvMessage.setTextColor(ContextCompat.getColor(Objects.requireNonNull(getContext()), R.color.colorPrimary));
                btnOk.setBackground(ContextCompat.getDrawable(Objects.requireNonNull(getContext()), R.drawable.gradient_drawable_135_normal));
                break;
            case SUCCESS:
                tvTitle.setTextColor(ContextCompat.getColor(Objects.requireNonNull(getContext()), R.color.colorGreen));
                tvMessage.setTextColor(ContextCompat.getColor(Objects.requireNonNull(getContext()), R.color.colorGreen));
                btnOk.setBackground(ContextCompat.getDrawable(Objects.requireNonNull(getContext()), R.drawable.gradient_drawable_135_success));
                break;
            case FAILED:
                tvTitle.setTextColor(ContextCompat.getColor(Objects.requireNonNull(getContext()), R.color.colorRed));
                tvMessage.setTextColor(ContextCompat.getColor(Objects.requireNonNull(getContext()), R.color.colorRed));
                btnOk.setBackground(ContextCompat.getDrawable(Objects.requireNonNull(getContext()), R.drawable.gradient_drawable_135_error));
                break;
            case DELETE:
                tvTitle.setTextColor(ContextCompat.getColor(Objects.requireNonNull(getContext()), R.color.colorPrimary));
                tvMessage.setTextColor(ContextCompat.getColor(Objects.requireNonNull(getContext()), R.color.colorPrimary));
                btnOk.setBackground(ContextCompat.getDrawable(Objects.requireNonNull(getContext()), R.color.colorPrimary));
                btnCancel.setBackground(ContextCompat.getDrawable(Objects.requireNonNull(getContext()), R.color.colorPrimary));
            case UPLOAD:
                tvTitle.setTextColor(ContextCompat.getColor(Objects.requireNonNull(getContext()), R.color.colorPrimary));
                tvMessage.setTextColor(ContextCompat.getColor(Objects.requireNonNull(getContext()), R.color.colorPrimary));
                btnOk.setBackground(ContextCompat.getDrawable(Objects.requireNonNull(getContext()), R.color.colorPrimary));
                btnCancel.setBackground(ContextCompat.getDrawable(Objects.requireNonNull(getContext()), R.color.colorPrimary));
        }

        btnOk.setOnClickListener(onClickListener);
        btnCancel.setOnClickListener(onClickListener);
    }

    public void setOnOneDialogListener(OnOneDialogListener oneDialogListener) {
        this.onOneDialogListener = oneDialogListener;
    }

    public void setOnTwoDialogListener(OnTwoDialogListener onTwoDialogListener) {
        this.onTwoDialogListener = onTwoDialogListener;
    }

//    private OnDialogListener getOnDialogListener() {
//        Fragment fragment = getParentFragment();
//        try {
//            if (fragment != null) {
//                return (OnDialogListener) fragment;
//            } else {
//                return (OnDialogListener) getActivity();
//            }
//        } catch (ClassCastException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_TAG, tag);
        outState.putBoolean(KEY_CANCELABLE, cancelable);
        outState.putBoolean(KEY_AUTO_DISMISS, autoDismiss);
        outState.putInt(KEY_STATE, state.ordinal());
        outState.putBoolean(KEY_SINGLE, single);
        outState.putString(KEY_TITLE, title);
        outState.putString(KEY_MESSAGE, message);
        outState.putString(KEY_STR_OK, strOk);
        outState.putString(KEY_STR_CANCEL, strCancel);
    }

    private void restoreInstanceState(Bundle bundle) {
        tag = bundle.getInt(KEY_TAG);
        cancelable = bundle.getBoolean(KEY_CANCELABLE);
        autoDismiss = bundle.getBoolean(KEY_AUTO_DISMISS);
        state = State.fromId(bundle.getInt(KEY_STATE));
        single = bundle.getBoolean(KEY_SINGLE);
        title = bundle.getString(KEY_TITLE);
        message = bundle.getString(KEY_MESSAGE);
        strOk = bundle.getString(KEY_STR_OK);
        strCancel = bundle.getString(KEY_STR_CANCEL);
    }

    private void restoreArguments(Bundle bundle) {
        tag = bundle.getInt(KEY_TAG);
        cancelable = bundle.getBoolean(KEY_CANCELABLE);
        autoDismiss = bundle.getBoolean(KEY_AUTO_DISMISS);
        state = State.fromId(bundle.getInt(KEY_STATE));
        single = bundle.getBoolean(KEY_SINGLE);
        title = bundle.getString(KEY_TITLE);
        message = bundle.getString(KEY_MESSAGE);
        strOk = bundle.getString(KEY_STR_OK);
        strCancel = bundle.getString(KEY_STR_CANCEL);
    }

    /********************************************************************************
     ********************************** Listener ***********************************
     ********************************************************************************/

    private final View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if (v == btnOk) {

                if (onOneDialogListener != null) {
                    onOneDialogListener.onPositiveButtonClick(tag, getDialog());
                    if (autoDismiss) {
                        getDialog().dismiss();
                    }
                }

                if (onTwoDialogListener != null) {
                    onTwoDialogListener.onPositiveButtonClick(tag, getDialog());
                    if (autoDismiss) {
                        getDialog().dismiss();
                    }
                }
            }

            if (v == btnCancel) {
                if (onTwoDialogListener != null) {
                    onTwoDialogListener.onNegativeButtonClick(tag, getDialog());
                    if (autoDismiss) {
                        getDialog().dismiss();
                    }
                }
            }
        }
    };

    /********************************************************************************
     ********************************** Interface ***********************************
     ********************************************************************************/

    public interface OnOneDialogListener {
        void onPositiveButtonClick(int tag, Dialog d);
    }

    public interface OnTwoDialogListener {
        void onPositiveButtonClick(int tag, Dialog d);

        void onNegativeButtonClick(int tag, Dialog d);
    }

    /********************************************************************************
     ******************************* Inner Class ***********************************
     ********************************************************************************/

    public static class Builder {

        private OnOneDialogListener onOneDialogListener = null;
        private OnTwoDialogListener onTwoDialogListener = null;

        private FragmentManager f;

        private int tag = 0;

        private boolean single = true;
        private boolean cancelable = true;
        private boolean autoDismiss = false;

        private State state = State.NORMAL;

        private String title = "แจ้งเตือน";
        private String message = "";
        private String strOk = "ใช่";
        private String strCancel = "ไม่";

        public Builder(FragmentManager f) {
            this.f = f;

        }

        public Builder setTag(int tag) {
            this.tag = tag;
            return this;
        }

        public Builder setCancelable(boolean cancelable) {
            this.cancelable = cancelable;
            return this;
        }

        public Builder setAutoDismiss(boolean autoDismiss) {
            this.autoDismiss = autoDismiss;
            return this;
        }

        public Builder setState(State state) {
            this.state = state;
            return this;
        }

        public Builder setTitle(String title) {
            if (title == null || title.equals("")) {
                return this;
            }

            this.title = title;
            return this;
        }

        public Builder setTitleEmty() {
            this.title = "";
            return this;
        }

        public Builder setMessage(String message) {
            this.message = message;
            return this;
        }

        public Builder setMessage(int resId) {
            this.message = message;
            return this;
        }

        public Builder setStrOk(String strOk) {
            this.strOk = strOk;
            return this;
        }

        public Builder setStrCancel(String strCancel) {
            this.strCancel = strCancel;
            return this;
        }

        public Builder setOnDialogListener(OnOneDialogListener onOneDialogListener) {
            single = true;
            this.onOneDialogListener = onOneDialogListener;
            return this;
        }

        public Builder setOnDialogListener(OnTwoDialogListener onTwoDialogListener) {
            single = false;
            this.onTwoDialogListener = onTwoDialogListener;
            return this;
        }

        public void build() {
            try {
                PzDialogConfirmFragment fragment = PzDialogConfirmFragment.newInstance(tag, cancelable, autoDismiss, state, single, title, message, strOk, strCancel);
                fragment.setOnOneDialogListener(onOneDialogListener);
                fragment.setOnTwoDialogListener(onTwoDialogListener);
                fragment.setCancelable(cancelable);
                fragment.show(f, "PzDialogConfirmFragment");
            } catch (IllegalStateException e) {
                if (BuildConfig.DEBUG) {
                    Log.w("PzDialogConfirmFragment", "IGNORE Exception");
                }
            }
        }
    }
}
