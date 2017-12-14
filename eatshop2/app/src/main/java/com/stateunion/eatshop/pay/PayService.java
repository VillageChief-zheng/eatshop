package com.stateunion.eatshop.pay;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.stateunion.eatshop.R;
import com.stateunion.eatshop.view.baseactivity.BaseActivity;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import static android.R.attr.type;

/**
 * Created by zhangguozheng on 2017/12/14.
 */

public class PayService extends BaseActivity{
   private ImageView zfbPay,wxPay;
    private Button buyNow;
    private boolean isWxPay=false;
    private IWXAPI msgApi;
    private RelativeLayout rl_zhifubao_pay,rl_weixin_pay;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setContentView(R.layout.activity_pay);
        init();
    }
    private void init(){
         rl_zhifubao_pay= (RelativeLayout) findViewById(R.id.rl_zhifubao_pay);
        rl_weixin_pay=(RelativeLayout) findViewById(R.id.rl_weixin_pay);
        zfbPay= (ImageView) findViewById(R.id.iv_right_circle1);
        wxPay= (ImageView) findViewById(R.id.iv_right_circle2);
        buyNow=(Button) findViewById(R.id.bt_buy);
        zfbPay.setImageResource(R.mipmap.right_circle_true);
        rl_zhifubao_pay.setOnClickListener(listener);
        rl_weixin_pay.setOnClickListener(listener);
        buyNow.setOnClickListener(listener);

    }

      private View.OnClickListener listener=new View.OnClickListener() {
          @Override
          public void onClick(View v) {
          switch (v.getId()){
              case R.id.bt_buy:
                if (isWxPay){
                    if(msgApi==null)
                        msgApi= WXAPIFactory.createWXAPI(PayService.this,null);
                    if(msgApi.isWXAppInstalled()&&msgApi.isWXAppSupportAPI()){
                        payWithWX();
                    } else {
                        showError("微信客户端未安装，请确认");
                    }

                } else {
                    payWithZFB();
                }
                  break;
              case R.id.rl_zhifubao_pay:

                  zfbPay.setImageResource(R.mipmap.right_circle_true);
                  wxPay.setImageResource(R.mipmap.right_circle_false);
                  isWxPay = false;
                  break;
              case R.id.rl_weixin_pay:
                  wxPay.setImageResource(R.mipmap.right_circle_true);
                  zfbPay.setImageResource(R.mipmap.right_circle_false);
                  isWxPay = true;
                  break;
              default:
                  break;
          }
          }
      };

    private void payWithZFB() {
        ZFBPayUtil zfb = new ZFBPayUtil();
//        zfb.payZFB(PayService.this, price, ss[type], ordernum, urlback);  调用
    }

    private void payWithWX() {
        WXPayUtil pay = new WXPayUtil();
 //        pay.payWX(getApplicationContext(), (int) f + "", ss[type], wxorder, msgApi);
    }


    public void callWXPayFailure() {

     }
}
