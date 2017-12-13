package com.stateunion.eatshop.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ExpandableListView;

import com.stateunion.eatshop.R;
import com.stateunion.eatshop.view.baseactivity.BaseActivity;

/**
 * Created by zhangguozheng on 2017/12/13.
 */

public class NewsActivity extends BaseActivity{
    private ExpandableListView listview;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
     }
     private void initView(){
         listview= (ExpandableListView) findViewById(R.id.ex_listview);
     }
}
