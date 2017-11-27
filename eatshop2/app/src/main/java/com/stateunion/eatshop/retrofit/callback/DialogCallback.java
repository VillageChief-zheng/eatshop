package com.stateunion.eatshop.retrofit.callback;

import android.app.Dialog;


import com.stateunion.eatshop.R;
import com.stateunion.eatshop.application.ProjectApplication;
import com.stateunion.eatshop.retrofit.RequestCommand;
import com.stateunion.eatshop.retrofit.bean.IBaseBean;
import com.stateunion.eatshop.retrofit.view.IBaseDialogView;
import com.stateunion.eatshop.util.SysDiaLog;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

 import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by zhangguozheng  on 2017/8/24.
 */

public class DialogCallback<E extends IBaseBean, T extends IBaseDialogView> extends RequestCallback<E>  {
    WeakReference<T> attachTargetWeakReference;
    Dialog dialog;

    public DialogCallback(T requestView) {
        attachTargetWeakReference = new WeakReference<>(requestView);
    }

    protected T getAttachTarget() {
        return attachTargetWeakReference.get();
    }

    /**
     *
     * @param e
     * @param call
     *  请求异常
     */
    @Override
    protected void onResponseFailureMessage(E e, Call<E> call) {
        getAttachTarget().showError(e.getInfo());
    }

    /**
     *
     * @param e
     * @param call
     * 超时 顶掉
     */
    @Override
    protected void onResponseFailureForceSignOut(E e, Call<E> call) {
        if(getSessionDialogs().size() !=0)return;
        sessionInvalid(e.getInfo());
    }
    /**
     *
     * @param e
     * @param call
     * 冻结
     */
    @Override
    protected void onResponseFailureFrozen(E e, Call<E> call) {
        if(getSessionDialogs().size() !=0)return;
        ProjectApplication application = ProjectApplication.sApplication;
//        AppSessionEngine.logout();
        Dialog dialog = new SysDiaLog(getAttachTarget().getBaseActivity())
                .buildSingle(e.getInfo(), "提示", "重新登录", new SysDiaLog.EtdSingleListener() {
                    @Override
                    public void OnLeftButtonClicked(SysDiaLog dialog) {
                        dialog.dismiss();
                        getSessionDialogs().remove(dialog);
                    }
                })
                .setCancelableChain(false)
                .showChain();
        getSessionDialogs().add(dialog);
    }

    @Override
    protected void onResponseSuccess(E e, Call<E> call) {

    }

    @Override
    public void onFailure(final Call<E> call) {
        SysDiaLog dialog = new SysDiaLog(getAttachTarget().getBaseActivity())
                .buildDouble(getAttachTarget().getBaseActivity().getString(R.string.the_network_is_dead), "稍后再试", "重新加载", new SysDiaLog.EtdDoubleListener() {
                    @Override
                    public void OnLeftButtonClicked(SysDiaLog dialog) {
                        dialog.dismiss();
                    }
                    @Override
                    public void OnRightButtonClicked(SysDiaLog dialog) {
                        dialog.dismiss();
                        onReCall(call);
                    }
                });
        dialog.setCancelable(true);
        dialog.show();
    }

    @Override
    public void onPreRequest(Call<E> call) {
        if (!isAttached()) return;
        if (isShowDialog()) {
            dialog = getAttachTarget().createDialog(R.style.customDialogTheme);
            dialog.setContentView(R.layout.wait_progress_dialog);
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
        }
    }
    protected void sessionInvalid(String message) {
        ProjectApplication application = ProjectApplication.sApplication;
//        AppSessionEngine.logout();
        SysDiaLog dialog = new SysDiaLog(getAttachTarget().getBaseActivity());
        dialog.buildSingle(message, "提示", "返回首页", new SysDiaLog.EtdSingleListener() {
            @Override
            public void OnLeftButtonClicked(SysDiaLog dialog) {
//                HomeActivity.startActivity(getAttachTarget().getBaseActivity(), R.id.tab_home, null);
                dialog.dismiss();
                getSessionDialogs().remove(dialog);
            }
        });
        dialog.show();
        getSessionDialogs().add(dialog);
    }
    public static List<Dialog> dialogs;//用来记录  异地登录，session失效，账户冻结等dialog，防止多次弹出此dialog，因为如果同时发出两个请求就有可能弹出两个这种dialog
    public static List<Dialog> getSessionDialogs(){
        if(dialogs == null){
            dialogs = new ArrayList<>();
        }
        return dialogs;
    }

    public void onReCall(Call<E> call) {
        if (call != null) {
            RequestCommand.call(DialogCallback.this, call.clone());
        }
    }

    @Override
    final public void onResponse(Call<E> call, Response<E> response) {
        if (!isAttached()) return;
        dismissDialog();
        super.onResponse(call, response);
    }

    @Override
    final public void onFailure(Call<E> call, Throwable t) {
        if (!isAttached()) return;
        dismissDialog();
        super.onFailure(call, t);
    }

    /**
     *
     * @return
     * 判断当前引用是否有效
     */
    private boolean isAttached() {
        return getAttachTarget() != null && getAttachTarget().isAlive();
    }
    /**
     *
     * @return
     *  dialog 状态
     */
    protected boolean isShowDialog() {
        return true;
    }
    private void dismissDialog() {
        if (dialog == null) return;
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }
    @Override
    public void onResponse(E e, Call<E> call) {
        super.onResponse(e, call);
    }
}