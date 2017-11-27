package com.stateunion.eatshop.retrofit.entiity;

import java.io.Serializable;

/**
 * Created by zhangguozheng on 2017/8/24.
 */

public class UserInfoEntity implements Serializable{


    private String useId;
    private String useAddrContact;
    private String useName;
    private String useMobile;
    private String useIdentityNum;
    private String useLoginName;
    private String useAuthMp;
    private boolean isOldUser;
    /**
     * 银行卡号
     */
    private String bankCard;
    /**
     * 是否违约
     */
    private boolean isViolation;
    /**
     * 开户状态 新用户，1-未开户2-开户中5-开户成功
     * 老用户：1-未开户3-待激活4-激活中5-开户成功
     */
    private String accountState;
    /**
     * 绑卡状态  是否绑定银行卡0-未绑定 1-已绑定
     */
    private String bankCardState;
    /**
     * 交易密码状态 0-未设置 1-已设置
     */
    private String tranPwdState;
    /**
     * 银行编码
     */
    private String bankCode;


    public String getUseAuthMp() {
        return useAuthMp;
    }

    public void setUseAuthMp(String useAuthMp) {
        this.useAuthMp = useAuthMp;
    }



    public String getUseLoginName() {
        return useLoginName;
    }


    public void setUseLoginName(String useLoginName) {
        this.useLoginName = useLoginName;
    }

    public String getUseIdentityNum() {
        return useIdentityNum;
    }


    public void setUseIdentityNum(String useIdentityNum) {
        this.useIdentityNum = useIdentityNum;
    }

    public String getUseId() {
        return useId;
    }


    public void setUseId(String useId) {
        this.useId = useId;
    }


    public String getUseAddrContact() {
        return useAddrContact;
    }


    public void setUseAddrContact(String useAddrContact) {
        this.useAddrContact = useAddrContact;
    }

    public String getUseName() {
        return useName;
    }

    public void setUseName(String useName) {
        this.useName = useName;
    }

    public String getUseMobile() {
        return useMobile;
    }

    public void setUseMobile(String useMobile) {
        this.useMobile = useMobile;
    }

    public String getBankCard() {
        return bankCard;
    }

    public void setBankCard(String bankCard) {
        this.bankCard = bankCard;
    }

    public boolean isViolation() {
        return isViolation;
    }

    public void setViolation(boolean violation) {
        isViolation = violation;
    }

    public String getBankCardState() {
        return bankCardState;
    }

    public void setBankCardState(String bankCardState) {
        this.bankCardState = bankCardState;
    }

    public String getTranPwdState() {
        return tranPwdState;
    }

    public void setTranPwdState(String tranPwdState) {
        this.tranPwdState = tranPwdState;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getAccountState() {
        return accountState;
    }

    public void setAccountState(String accountState) {
        this.accountState = accountState;
    }

    public boolean isOldUser() {
        return isOldUser;
    }

    public void setOldUser(boolean oldUser) {
        isOldUser = oldUser;
    }
}
