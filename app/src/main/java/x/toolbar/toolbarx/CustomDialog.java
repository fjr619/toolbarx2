package x.toolbar.toolbarx;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;

/**
 * Created by franky.wijanarko on 26/02/18.
 */

public class CustomDialog extends Dialog {

    private Context mContext;

    public CustomDialog(@NonNull Context context) {
        super(context, R.style.DialogTheme);
        setView(context);
    }

    public CustomDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        setView(context);
    }

    protected CustomDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        setView(context);
    }

    private void setView(Context context) {
        setContentView(R.layout.dialog_test);
        mContext = context;

        ImageView ivClose = this.findViewById(R.id.iv_close);
        ivClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });


        View vStatusBar = this.findViewById(R.id.v_statusbar);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            vStatusBar.setVisibility(View.VISIBLE);
            vStatusBar.getLayoutParams().height =getStatusBarHeight();
        }


        setCanceledOnTouchOutside(true);
    }


    @Override
    protected void onStart() {
        super.onStart();
        Window window = getWindow();
        window.setGravity(Gravity.TOP);
        window.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        window.getAttributes().windowAnimations = R.style.DialogAnimation;
        window.setBackgroundDrawableResource(android.R.color.transparent);
    }

    private int getStatusBarHeight() {
        int result = 0;
        int resourceId = mContext.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = mContext.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
}
