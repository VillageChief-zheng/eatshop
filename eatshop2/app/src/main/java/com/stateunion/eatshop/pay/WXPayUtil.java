package com.stateunion.eatshop.pay;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Xml;

import com.stateunion.eatshop.commons.Constants;
import com.stateunion.eatshop.util.PayMD5;
import com.stateunion.eatshop.util.WxPayUtil;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.utils.Log;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.xmlpull.v1.XmlPullParser;

import java.io.StringReader;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import okhttp3.internal.Util;

/**
 * Created by admini on 2017/12/14.
 */

public class WXPayUtil {
    private static final String TAG = "MicroMsg.SDKSample.PayActivity";
    private PayReq req;
    private IWXAPI msgApi;
    private Map<String, String> resultunifiedorder;
    private StringBuffer sb;
    private String price, name,ordernum;

    public void payWX(Context context, String price, String name, String ordernum, IWXAPI msgApi) {
        this.price = price;
        this.name = name;
        this.ordernum=ordernum;
        this.msgApi = msgApi;

        req = new PayReq();
        sb = new StringBuffer();
        this.msgApi.registerApp(Constants.APP_ID);
		/*GetPrepayIdTask getPrepayId = new GetPrepayIdTask();
		getPrepayId.execute();*/

        //genProductArgs();
        genPayReq();
        sendPayReq();
    }


    private String genPackageSign(List<NameValuePair> params) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < params.size(); i++) {
            sb.append(params.get(i).getName());
            sb.append('=');
            sb.append(params.get(i).getValue());
            sb.append('&');
        }
        sb.append("key=");
        sb.append(Constants.API_KEY);


        String packageSign = PayMD5.getMessageDigest(sb.toString().getBytes()).toUpperCase();
         return packageSign;
    }
    private String genAppSign(List<NameValuePair> params) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < params.size(); i++) {
            sb.append(params.get(i).getName());
            sb.append('=');
            sb.append(params.get(i).getValue());
            sb.append('&');
        }
        sb.append("key=");
        sb.append(Constants.API_KEY);

        this.sb.append("sign str\n"+sb.toString()+"\n\n");
        String appSign = PayMD5.getMessageDigest(sb.toString().getBytes());
         return appSign;
    }
    private String toXml(List<NameValuePair> params) {
        StringBuilder sb = new StringBuilder();
        sb.append("<xml>");
        for (int i = 0; i < params.size(); i++) {
            sb.append("<"+params.get(i).getName()+">");
            sb.append(params.get(i).getValue());
            sb.append("</"+params.get(i).getName()+">");
        }
        sb.append("</xml>");

         return sb.toString();
    }

    private class GetPrepayIdTask extends AsyncTask<Void, Void, Map<String,String>> {

        private ProgressDialog dialog;


        @Override
        protected void onPreExecute() {
//			dialog = ProgressDialog.show(PayActivity.this, getString(R.string.app_tip), getString(R.string.getting_prepayid));
        }

        @Override
        protected void onPostExecute(Map<String,String> result) {
            if (dialog != null) {
                dialog.dismiss();
            }
            sb.append("prepay_id\n"+result.get("prepay_id")+"\n\n");
//			show.setText(sb.toString());

            resultunifiedorder=result;
            System.out.println("------------------------------------------------------------"+sb);
			/*genPayReq();
			sendPayReq();*/
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected Map<String,String>  doInBackground(Void... params) {

            String url = String.format("https://api.mch.weixin.qq.com/pay/unifiedorder");
            String entity = genProductArgs();


            byte[] buf = WxPayUtil.httpPost(url, entity);

            String content = new String(buf);
             Map<String,String> xml=decodeXml(content);


            return xml;
        }
    }



    public Map<String,String> decodeXml(String content) {

        try {
            Map<String, String> xml = new HashMap<String, String>();
            XmlPullParser parser = Xml.newPullParser();
            parser.setInput(new StringReader(content));
            int event = parser.getEventType();
            while (event != XmlPullParser.END_DOCUMENT) {

                String nodeName=parser.getName();
                switch (event) {
                    case XmlPullParser.START_DOCUMENT:

                        break;
                    case XmlPullParser.START_TAG:

                        if("xml".equals(nodeName)==false){
                            xml.put(nodeName,parser.nextText());
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        break;
                }
                event = parser.next();
            }

            return xml;
        } catch (Exception e) {
            Log.e("orion",e.toString());
        }
        return null;

    }


    private String genNonceStr() {
        Random random = new Random();
        return PayMD5.getMessageDigest(String.valueOf(random.nextInt(10000)).getBytes());
    }

    private long genTimeStamp() {
        return System.currentTimeMillis() / 1000;
    }



    private String genOutTradNo() {
        Random random = new Random();
        return PayMD5.getMessageDigest(String.valueOf(random.nextInt(10000)).getBytes());
    }

    //
    private String genProductArgs() {
        StringBuffer xml = new StringBuffer();

        try {
            String	nonceStr = genNonceStr();
            System.out.println(ordernum+"ppppppppppppp");

            xml.append("</xml>");
            List<NameValuePair> packageParams = new LinkedList<NameValuePair>();
            packageParams.add(new BasicNameValuePair("appid", Constants.APP_ID));
            packageParams.add(new BasicNameValuePair("body", name));
            packageParams.add(new BasicNameValuePair("mch_id", Constants.MCH_ID));
            packageParams.add(new BasicNameValuePair("nonce_str", nonceStr));
            packageParams.add(new BasicNameValuePair("notify_url", "http://223.72.216.227:8088/un-fzjktj-web/appService/wxpayCallBack.action?third_id=thirdpay_41"));
            packageParams.add(new BasicNameValuePair("out_trade_no",PayMD5.getMessageDigest(ordernum.getBytes())));//genOutTradNo())
            packageParams.add(new BasicNameValuePair("spbill_create_ip",getPhoneIp()));
            packageParams.add(new BasicNameValuePair("total_fee", String.valueOf((int)(Float.valueOf(price)*100))));
            packageParams.add(new BasicNameValuePair("trade_type", "APP"));


            String sign = genPackageSign(packageParams);
            packageParams.add(new BasicNameValuePair("sign", sign));


            String xmlstring =toXml(packageParams);
            System.out.println("xmlstring::::::::"+xmlstring);
            System.out.println("xmlstring.getBytes() to  ISO8859-1"+new String(xmlstring.getBytes(),"ISO8859-1"));
            return new String(xmlstring.getBytes(),"ISO8859-1");

        } catch (Exception e) {
            Log.e(TAG, "genProductArgs fail, ex = " + e.getMessage());
            return null;
        }
    }
    private void genPayReq() {
        req.appId = Constants.APP_ID;
        req.partnerId = Constants.MCH_ID;
        req.prepayId = ordernum;
        req.packageValue = "prepay_id="+ordernum;
        req.nonceStr = genNonceStr();
        req.timeStamp = String.valueOf(genTimeStamp());

        List<NameValuePair> signParams = new LinkedList<NameValuePair>();
        signParams.add(new BasicNameValuePair("appid", req.appId));
        signParams.add(new BasicNameValuePair("noncestr", req.nonceStr));
        signParams.add(new BasicNameValuePair("package", req.packageValue));
        signParams.add(new BasicNameValuePair("partnerid", req.partnerId));
        signParams.add(new BasicNameValuePair("prepayid", req.prepayId));
        signParams.add(new BasicNameValuePair("timestamp", req.timeStamp));
        req.sign = genAppSign(signParams);

        sb.append("sign\n"+req.sign+"\n\n");


        Log.e("orion", signParams.toString());

    }
    private void sendPayReq() {


        msgApi.registerApp(Constants.APP_ID);
        msgApi.sendReq(req);
    }

    public static String getPhoneIp() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
                        // if (!inetAddress.isLoopbackAddress() && inetAddress
                        // instanceof Inet6Address) {
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
        } catch (Exception e) {
        }
        return "127.0.0.1";
    }

}
