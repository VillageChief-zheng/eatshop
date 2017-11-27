package com.stateunion.eatshop.widget;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.ColorRes;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.stateunion.eatshop.R;
import com.stateunion.eatshop.util.OnSafetyClickListener;


/**
 * Created by zhangguozheng on 2017/8/28.
 * * 有两种颜色模式  一种是蓝色  一种是白色 默认是蓝色模式
 * 左边返回箭头默认点击事件是activity.finish
 */

public class NavigationView extends LinearLayout {
    private TextView back;
    private TextView title;
    private TextView operation;
    private View line;
    public NavigationView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.navigation_view, this);
        setOrientation(VERTICAL);
        back = (TextView) findViewById(R.id.navigation_back);
        title = (TextView) findViewById(R.id.navigation_title);
        operation = (TextView) findViewById(R.id.navigation_operation);
        line = findViewById(R.id.navigation_line);
        final TypedArray attributes = context.obtainStyledAttributes(attrs,
                R.styleable.NavigationView);
        try {
            title.setText(attributes
                    .getString(R.styleable.NavigationView_nvTitle));
            if(attributes.hasValue(R.styleable.NavigationView_nvOperation)){
                this.operation.setVisibility(View.VISIBLE);
                this.operation.setText(attributes.getString(R.styleable.NavigationView_nvOperation));
            }
            int backVisibility = attributes.getInteger(R.styleable.NavigationView_nvBackVisibility,0x1);
            if(backVisibility == 0x0){//gone
                back.setVisibility(GONE);
            }else{//visible
                back.setVisibility(VISIBLE);
            }
            int mode = attributes.getInteger(R.styleable.NavigationView_nvColorMode,0x0);
            if(mode == 0x0){//blue 模式
                Log.e("mode","blue 模式");
                setBackgroundResource(R.color.blue_bg);
                back.setTextColor(getColorById(R.color.white));
                title.setTextColor(getColorById(R.color.white));
                operation.setTextColor(getColorById(R.color.white));
                back.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.reset_psd,0,0,0);
                line.setVisibility(GONE);
            }else if(mode == 0x1){//white 模式
                Log.e("mode","white 模式");
                setBackgroundResource(R.color.white);
                back.setTextColor(getColorById(R.color.blue_bg));
                title.setTextColor(getColorById(R.color.black));
                operation.setTextColor(getColorById(R.color.blue_bg));
                back.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.site_notice_back,0,0,0);
                line.setVisibility(VISIBLE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            attributes.recycle();
        }
        back.setOnClickListener(new OnSafetyClickListener() {
            @Override
            public void onSafetyClick(View v) {
                if(getContext() instanceof Activity){
                    Activity activity = (Activity) getContext();
                    activity.finish();
                }
            }
        });
    }
    int getColorById(@ColorRes int colorId){
        return getContext().getResources().getColor(colorId);
    }
    public TextView getTitle() {
        return title;
    }

    public TextView getBack() {
        return back;
    }

    public TextView getOperation() {
        return operation;
    }

}
