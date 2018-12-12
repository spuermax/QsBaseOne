package com.qsmaxmin.qsbase.common.widget.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.qsmaxmin.qsbase.R;
import com.qsmaxmin.qsbase.common.utils.QsHelper;
import com.qsmaxmin.qsbase.common.viewbind.ViewBindHelper;

/**
 * @CreateBy qsmaxmin
 * @Date 17/8/3  上午12:35
 * @Description
 */
public abstract class QsDialogFragment extends DialogFragment {

    @Override public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getDialogTheme() > 0) setStyle(DialogFragment.STYLE_NO_TITLE, getDialogTheme());
    }

    @Override public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewBindHelper.bindBundle(this, getArguments());
        getDialog().setCanceledOnTouchOutside(true);

        ViewGroup mainView = createMainView(inflater.getContext());
        View dialogView = getDialogView(inflater, container);
        FrameLayout.LayoutParams childLayout = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        childLayout.gravity = Gravity.CENTER;
        setAttribute(childLayout);
        mainView.addView(dialogView, childLayout);

        ViewBindHelper.bindView(this, dialogView);
        return mainView;
    }

    private ViewGroup createMainView(Context context) {
        FrameLayout mainView = new FrameLayout(context);
        mainView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        if (isCancelable()) {
            mainView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    dismissAllowingStateLoss();
                }
            });
        }
        return mainView;
    }

    @Override public void onStart() {
        super.onStart();
        initData();
    }

    protected int getDialogTheme() {
        return R.style.QsDialogTheme;
    }

    protected abstract void setAttribute(FrameLayout.LayoutParams params);

    protected abstract View getDialogView(LayoutInflater inflater, ViewGroup container);

    protected String initTag() {
        return QsHelper.getInstance().getApplication().isLogOpen() ? getClass().getSimpleName() : "QsDialogFragment";
    }

    public void onViewClick(View view) {
    }

    protected void initData() {

    }

    public void show() {
        QsHelper.getInstance().commitDialogFragment(this);
    }
}
