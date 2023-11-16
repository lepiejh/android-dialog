package com.orhanobut.dialog.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.orhanobut.dialog.R;


/**
 * Created by Administrator on 2017/4/24.
 */

public class LoadDialog extends AlertDialog
{
    private Context context;


    public LoadDialog(Context context, int theme)
    {
        super(context, theme);
        this.context = context;
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
        initView(context);
    }

    private void initView(Context context)
    {
        Animation operatingAnim = AnimationUtils.loadAnimation(context, R.anim.tips);
        LinearInterpolator lin = new LinearInterpolator();
        operatingAnim.setInterpolator(lin);
        ImageView iv_load = (ImageView) findViewById(R.id.iv_load);
        iv_load.setAnimation(operatingAnim);
    }
}
