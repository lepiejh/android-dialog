package com.orhanobut.dialog.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.orhanobut.dialog.R;

public class LoadDialog extends AlertDialog
{
    private Context mContext;
    private String mHint;

    public LoadDialog(Context context,String hint, int theme)
    {
        super(context, theme);
        this.mContext = context;
        this.mHint = hint;
    }

    public LoadDialog(Context context)
    {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.load);
        initView(mContext);
    }

    private void initView(Context context)
    {
        Animation operatingAnim = AnimationUtils.loadAnimation(context, R.anim.tips);
        LinearInterpolator lin = new LinearInterpolator();
        operatingAnim.setInterpolator(lin);
        ImageView iv_load = (ImageView) findViewById(R.id.iv_load);
        TextView tv_hint = (TextView) findViewById(R.id.tv_hint);
        iv_load.setAnimation(operatingAnim);
        tv_hint.setText(mHint);
    }
}
