package com.stateunion.eatshop.view.basefrment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by admini on 2017/11/28.
 */

     public abstract class BaseFragment extends Fragment {


    public static final String TAG = "BaseFragment";

    private View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (rootView == null) {
            rootView = inflater.inflate(getLayoutId(), null);
            rootView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            createView(rootView);
        }
        refreshData(rootView);

        return rootView;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (rootView != null) {
            ViewGroup viewGroup = (ViewGroup) rootView.getParent();
            viewGroup.removeAllViews();
        }
    }

    public View getRootView() {
        return rootView;
    }

    /**
     * 资源id
     */
    public abstract int getLayoutId();

    /**
     * 创建view
     */
    public abstract void createView(View rootView);

    /**
     * 刷新数据
     */
    public abstract void refreshData(View rootView);

}
