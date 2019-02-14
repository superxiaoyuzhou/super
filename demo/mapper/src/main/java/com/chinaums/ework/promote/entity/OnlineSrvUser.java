package com.chinaums.ework.promote.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "ONLINE_SRV_USER")
public class OnlineSrvUser {
    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "PROMOTE_CODE")
    private String promoteCode;

    @Column(name = "MOBILE")
    private String mobile;

    @Column(name = "LOGIN_NAME")
    private String loginName;

    @Column(name = "LOGIN_PWD")
    private String loginPwd;

    @Column(name = "ADMIN_FLAG")
    private String adminFlag;

    @Column(name = "BIND_DN")
    private String bindDn;

    @Column(name = "CERT_CN")
    private String certCn;

    @Column(name = "NAME")
    private String name;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PHONE")
    private String phone;

    @Column(name = "LOGIN_IP")
    private String loginIp;

    @Column(name = "LOGIN_TIME")
    private Date loginTime;

    @Column(name = "LOGIN_FLAG")
    private String loginFlag;

    @Column(name = "CREATE_BY")
    private String createBy;

    @Column(name = "CREATE_TIME")
    private Date createTime;

    @Column(name = "UPDATE_BY")
    private String updateBy;

    @Column(name = "UPDATE_TIME")
    private Date updateTime;

    @Column(name = "REMARKS")
    private String remarks;

    @Column(name = "DEL_FLAG")
    private String delFlag;

    /**
     * @return ID
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return PROMOTE_CODE
     */
    public String getPromoteCode() {
        return promoteCode;
    }

    /**
     * @param promoteCode
     */
    public void setPromoteCode(String promoteCode) {
        this.promoteCode = promoteCode;
    }

    /**
     * @return MOBILE
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * @param mobile
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * @return LOGIN_NAME
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     * @param loginName
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    /**
     * @return LOGIN_PWD
     */
    public String getLoginPwd() {
        return loginPwd;
    }

    /**
     * @param loginPwd
     */
    public void setLoginPwd(String loginPwd) {
        this.loginPwd = loginPwd;
    }

    /**
     * @return ADMIN_FLAG
     */
    public String getAdminFlag() {
        return adminFlag;
    }

    /**
     * @param adminFlag
     */
    public void setAdminFlag(String adminFlag) {
        this.adminFlag = adminFlag;
    }

    /**
     * @return BIND_DN
     */
    public String getBindDn() {
        return bindDn;
    }

    /**
     * @param bindDn
     */
    public void setBindDn(String bindDn) {
        this.bindDn = bindDn;
    }

    /**
     * @return CERT_CN
     */
    public String getCertCn() {
        return certCn;
    }

    /**
     * @param certCn
     */
    public void setCertCn(String certCn) {
        this.certCn = certCn;
    }

    /**
     * @return NAME
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return EMAIL
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return PHONE
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return LOGIN_IP
     */
    public String getLoginIp() {
        return loginIp;
    }

    /**
     * @param loginIp
     */
    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    /**
     * @return LOGIN_TIME
     */
    public Date getLoginTime() {
        return loginTime;
    }

    /**
     * @param loginTime
     */
    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    /**
     * @return LOGIN_FLAG
     */
    public String getLoginFlag() {
        return loginFlag;
    }

    /**
     * @param loginFlag
     */
    public void setLoginFlag(String loginFlag) {
        this.loginFlag = loginFlag;
    }

    /**
     * @return CREATE_BY
     */
    public String getCreateBy() {
        return createBy;
    }

    /**
     * @param createBy
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    /**
     * @return CREATE_TIME
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return UPDATE_BY
     */
    public String getUpdateBy() {
        return updateBy;
    }

    /**
     * @param updateBy
     */
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    /**
     * @return UPDATE_TIME
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * @return REMARKS
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * @param remarks
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    /**
     * @return DEL_FLAG
     */
    public String getDelFlag() {
        return delFlag;
    }

    /**
     * @param delFlag
     */
    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", promoteCode=").append(promoteCode);
        sb.append(", mobile=").append(mobile);
        sb.append(", loginName=").append(loginName);
        sb.append(", loginPwd=").append(loginPwd);
        sb.append(", adminFlag=").append(adminFlag);
        sb.append(", bindDn=").append(bindDn);
        sb.append(", certCn=").append(certCn);
        sb.append(", name=").append(name);
        sb.append(", email=").append(email);
        sb.append(", phone=").append(phone);
        sb.append(", loginIp=").append(loginIp);
        sb.append(", loginTime=").append(loginTime);
        sb.append(", loginFlag=").append(loginFlag);
        sb.append(", createBy=").append(createBy);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateBy=").append(updateBy);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", remarks=").append(remarks);
        sb.append(", delFlag=").append(delFlag);
        sb.append("]");
        return sb.toString();
    }
}