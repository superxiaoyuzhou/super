package com.chinaums.ework.promote.manager.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "PROMOTE_NOTICE")
public class PromoteNotice {
    @Column(name = "ID")
    private String id;

    @Column(name = "PROD_CODE")
    private String prodCode;

    @Column(name = "TITLE")
    private String title;

    @Column(name = "SUMMARY")
    private String summary;

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "AUTHOR")
    private String author;

    @Column(name = "SOURCE")
    private String source;

    @Column(name = "STATUS")
    private String status;

    @Column(name = "VALID_DATE_BEGIN")
    private Date validDateBegin;

    @Column(name = "VALID_DATE_END")
    private Date validDateEnd;

    @Column(name = "CREATE_USER_ID")
    private String createUserId;

    @Column(name = "CREATE_TIME")
    private Date createTime;

    @Column(name = "UPDATE_USER_ID")
    private String updateUserId;

    @Column(name = "UPDATE_TIME")
    private Date updateTime;

    @Column(name = "DEL_FLAG")
    private String delFlag;

    @Column(name = "NOTICE_TYPE")
    private String noticeType;

    @Column(name = "PROMOTE_CODE")
    private String promoteCode;

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
     * @return PROD_CODE
     */
    public String getProdCode() {
        return prodCode;
    }

    /**
     * @param prodCode
     */
    public void setProdCode(String prodCode) {
        this.prodCode = prodCode;
    }

    /**
     * @return TITLE
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return SUMMARY
     */
    public String getSummary() {
        return summary;
    }

    /**
     * @param summary
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }

    /**
     * @return CONTENT
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * @return AUTHOR
     */
    public String getAuthor() {
        return author;
    }

    /**
     * @param author
     */
    public void setAuthor(String author) {
        this.author = author;
    }

    /**
     * @return SOURCE
     */
    public String getSource() {
        return source;
    }

    /**
     * @param source
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * @return STATUS
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return VALID_DATE_BEGIN
     */
    public Date getValidDateBegin() {
        return validDateBegin;
    }

    /**
     * @param validDateBegin
     */
    public void setValidDateBegin(Date validDateBegin) {
        this.validDateBegin = validDateBegin;
    }

    /**
     * @return VALID_DATE_END
     */
    public Date getValidDateEnd() {
        return validDateEnd;
    }

    /**
     * @param validDateEnd
     */
    public void setValidDateEnd(Date validDateEnd) {
        this.validDateEnd = validDateEnd;
    }

    /**
     * @return CREATE_USER_ID
     */
    public String getCreateUserId() {
        return createUserId;
    }

    /**
     * @param createUserId
     */
    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId;
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
     * @return UPDATE_USER_ID
     */
    public String getUpdateUserId() {
        return updateUserId;
    }

    /**
     * @param updateUserId
     */
    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId;
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

    /**
     * @return NOTICE_TYPE
     */
    public String getNoticeType() {
        return noticeType;
    }

    /**
     * @param noticeType
     */
    public void setNoticeType(String noticeType) {
        this.noticeType = noticeType;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", prodCode=").append(prodCode);
        sb.append(", title=").append(title);
        sb.append(", summary=").append(summary);
        sb.append(", content=").append(content);
        sb.append(", author=").append(author);
        sb.append(", source=").append(source);
        sb.append(", status=").append(status);
        sb.append(", validDateBegin=").append(validDateBegin);
        sb.append(", validDateEnd=").append(validDateEnd);
        sb.append(", createUserId=").append(createUserId);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateUserId=").append(updateUserId);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", delFlag=").append(delFlag);
        sb.append(", noticeType=").append(noticeType);
        sb.append(", promoteCode=").append(promoteCode);
        sb.append("]");
        return sb.toString();
    }
}