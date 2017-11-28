package com.stateunion.eatshop;

import android.graphics.drawable.Drawable;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.stateunion.eatshop.retrofit.RequestCommand;
import com.stateunion.eatshop.retrofit.callback.DialogCallback;
import com.stateunion.eatshop.retrofit.entiity.UserInfoBean;
import com.stateunion.eatshop.view.baseactivity.BaseActivity;
import com.stateunion.eatshop.view.basefrment.BaseFragment;
import com.stateunion.eatshop.view.basefrment.BaseFragmentAct;
import com.stateunion.eatshop.view.mainfrment.CouPonFragment;
import com.stateunion.eatshop.view.mainfrment.MainFragment;
import com.stateunion.eatshop.view.mainfrment.PersFragment;
import com.stateunion.eatshop.view.mainfrment.TakeFragment;

import retrofit2.Call;

public class MainActivity extends BaseFragmentAct implements RadioGroup.OnCheckedChangeListener{
    /**
     *  首页 fragment
     */
    private MainFragment mainFragment=null;
     /**
     * 取餐 fragment
      */
    private TakeFragment takeFragment=null;
    /**
     * 福利 fragment
     */
    private CouPonFragment couPonFragment=null;
    /**
     * 个人 fragment
     */
    private PersFragment persFragment=null;
    private RadioButton [] arrRadios=null;
    /**
     * 首页下面引导button
      */
    RadioButton radio0,radio1,radio2,radio3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acty_main);
        initview();
        setRadios();
     }
 private void initview(){
     radio0= (RadioButton) findViewById(R.id.acty_main_radio0);
     radio1= (RadioButton) findViewById(R.id.acty_main_radio1);
     radio2= (RadioButton) findViewById(R.id.acty_main_radio2);
     radio3= (RadioButton) findViewById(R.id.acty_main_radio3);
     arrRadios=new RadioButton[]{radio0,radio1,radio2,radio3};
     ((RadioGroup)findViewById(R.id.acty_main_radioGroups)).setOnCheckedChangeListener(this);
 }

    @Override
    public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
        switch (checkedId){
            case R.id.acty_main_radio0:
                getSupportFragmentManager().beginTransaction().replace(R.id.acty_main_frame,getFragment(DataStore.getInt(APPKey.SP_MAIN_RADIO_1 ))).commit();
               break;
            case R.id.acty_main_radio1:
                getSupportFragmentManager().beginTransaction().replace(R.id.acty_main_frame,getFragment(DataStore.getInt(APPKey.SP_MAIN_RADIO_2 ))).commit();

                break;
            case R.id.acty_main_radio2:
                getSupportFragmentManager().beginTransaction().replace(R.id.acty_main_frame,getFragment(DataStore.getInt(APPKey.SP_MAIN_RADIO_3 ))).commit();

                break;
            case R.id.acty_main_radio3:
                getSupportFragmentManager().beginTransaction().replace(R.id.acty_main_frame,getFragment(DataStore.getInt(APPKey.SP_MAIN_RADIO_4))).commit();

                break;
        }
    }

    /**
     *  根据初始值 获取对应fragment
      */
    private BaseFragment getFragment(int fr_id){
        switch (fr_id){
            case APPKey.SP_MAIN_RADIO_LAYOUT_MAIN:
                return getMainFragment();
            case APPKey.SP_MAIN_RADIO_LAYOUT_TAKE:
                return getTakeFragment();
            case APPKey.SP_MAIN_RADIO_LAYOUT_COUP:
                return getCouPonFragment();
            case APPKey.SP_MAIN_RADIO_LAYOUT_PERS:
                return getPersFragment();
            default:
                return null;
        }
     }

    /**
     *  获取 首页 取餐 福利 个人
     */
    private MainFragment getMainFragment(){
        return mainFragment==null ?mainFragment=new MainFragment():mainFragment;
    }
    private TakeFragment getTakeFragment(){
        return takeFragment==null?takeFragment=new TakeFragment():takeFragment;
    }
    private CouPonFragment getCouPonFragment(){
        return couPonFragment==null?couPonFragment=new CouPonFragment():couPonFragment;
    }
    private PersFragment getPersFragment(){
        return persFragment==null?persFragment=new PersFragment():persFragment;
    }

    /**
     * 设置 自定义导航后的 radio button 样式
     *
     * @param radio      radio button
     * @param layoutType 对应静态数据的样式
     */
    private void setSelfNaviRadio(RadioButton radio, int layoutType) {
        switch (layoutType) {
            case APPKey.SP_MAIN_RADIO_LAYOUT_MAIN:
                radio.setCompoundDrawables(null, getRadioDrawable(R.drawable.slt_main_radio_main), null, null);
                radio.setText(R.string.main_radio_main);
                radio.setChecked(true);
                break;
            case APPKey.SP_MAIN_RADIO_LAYOUT_TAKE:
                radio.setCompoundDrawables(null, getRadioDrawable(R.drawable.slt_main_radio_game), null, null);
                radio.setText(R.string.main_radio_take);
                break;
            case APPKey.SP_MAIN_RADIO_LAYOUT_COUP:
                radio.setCompoundDrawables(null, getRadioDrawable(R.drawable.slt_main_radio_shop), null, null);
                radio.setText(R.string.main_radio_coup);
                break;
            case APPKey.SP_MAIN_RADIO_LAYOUT_PERS:
                radio.setCompoundDrawables(null, getRadioDrawable(R.drawable.slt_main_radio_social), null, null);
                radio.setText(R.string.main_radio_per);
                break;

        }
    }
    /**
     * 根据 drawable ID ，获取radio button 的 drawable 样式
     *
     * @param drawableID id
     * @return drawable
     */
    private Drawable getRadioDrawable(int drawableID) {
        Drawable drawable = getResources().getDrawable(drawableID);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        return drawable;
    }
    /**
     * 设置 radio buttons 的样式
     */
    private void setRadios() {
        String[] arrMainRadioType = getResources().getStringArray(R.array.main_radio);
        for (int i = 0; i < 4; i++) {
             setSelfNaviRadio(arrRadios[i], DataStore.getInt(arrMainRadioType[i]));
        }
    }

    @Override
    protected void onResume() {
        /**
         * 设置自定义导航
         */
        if (DataStore.getBoolean(APPKey.SP_MAIN_SELF_NAVI)) {
            setRadios();
            DataStore.put(APPKey.SP_MAIN_SELF_NAVI, false);
        }
        super.onResume();
    }
}
