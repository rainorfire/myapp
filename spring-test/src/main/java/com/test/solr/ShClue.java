package com.test.solr;

import java.io.Serializable;
import java.util.Date;

public class ShClue implements Serializable {
    private Long shClueId;

    private Long clueOrderId;

    private Long source;

    private String contactName;

    private String contactMobile;

    private Long city;

    private String babyName;

    private String weixin;

    private Boolean addWeixinFriend;

    private String qq;

    private Boolean addQqFriend;

    private Boolean sendCoupon;

    private Integer sendCouponNum;

    private Long shSalesmanId;

    private Byte serviceStatus;

    private Byte serviceSatisfy;

    private Byte expectRenew;

    private Date lastVisitTime;

    private Date nextTrackTime;

    private String nextTrackContent;

    private Long businessType;

    private String customerRequirement;

    private Integer status;

    private Byte isOrder;

    private Date invitationTime;

    private Date createTime;

    private Long creatorId;

    private Date modifyTime;

    private Long modifyManId;

    private Integer prop1;

    private String prop2;

    private String prop3;

    private String prop4;

    private static final long serialVersionUID = 1L;

    public Long getShClueId() {
        return shClueId;
    }

    public void setShClueId(Long shClueId) {
        this.shClueId = shClueId;
    }

    public Long getClueOrderId() {
        return clueOrderId;
    }

    public void setClueOrderId(Long clueOrderId) {
        this.clueOrderId = clueOrderId;
    }

    public Long getSource() {
        return source;
    }

    public void setSource(Long source) {
        this.source = source;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName == null ? null : contactName.trim();
    }

    public String getContactMobile() {
        return contactMobile;
    }

    public void setContactMobile(String contactMobile) {
        this.contactMobile = contactMobile == null ? null : contactMobile.trim();
    }

    public Long getCity() {
        return city;
    }

    public void setCity(Long city) {
        this.city = city;
    }

    public String getBabyName() {
        return babyName;
    }

    public void setBabyName(String babyName) {
        this.babyName = babyName == null ? null : babyName.trim();
    }

    public String getWeixin() {
        return weixin;
    }

    public void setWeixin(String weixin) {
        this.weixin = weixin == null ? null : weixin.trim();
    }

    public Boolean getAddWeixinFriend() {
        return addWeixinFriend;
    }

    public void setAddWeixinFriend(Boolean addWeixinFriend) {
        this.addWeixinFriend = addWeixinFriend;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq == null ? null : qq.trim();
    }

    public Boolean getAddQqFriend() {
        return addQqFriend;
    }

    public void setAddQqFriend(Boolean addQqFriend) {
        this.addQqFriend = addQqFriend;
    }

    public Boolean getSendCoupon() {
        return sendCoupon;
    }

    public void setSendCoupon(Boolean sendCoupon) {
        this.sendCoupon = sendCoupon;
    }

    public Integer getSendCouponNum() {
        return sendCouponNum;
    }

    public void setSendCouponNum(Integer sendCouponNum) {
        this.sendCouponNum = sendCouponNum;
    }

    public Long getShSalesmanId() {
        return shSalesmanId;
    }

    public void setShSalesmanId(Long shSalesmanId) {
        this.shSalesmanId = shSalesmanId;
    }

    public Byte getServiceStatus() {
        return serviceStatus;
    }

    public void setServiceStatus(Byte serviceStatus) {
        this.serviceStatus = serviceStatus;
    }

    public Byte getServiceSatisfy() {
        return serviceSatisfy;
    }

    public void setServiceSatisfy(Byte serviceSatisfy) {
        this.serviceSatisfy = serviceSatisfy;
    }

    public Byte getExpectRenew() {
        return expectRenew;
    }

    public void setExpectRenew(Byte expectRenew) {
        this.expectRenew = expectRenew;
    }

    public Date getLastVisitTime() {
        return lastVisitTime;
    }

    public void setLastVisitTime(Date lastVisitTime) {
        this.lastVisitTime = lastVisitTime;
    }

    public Date getNextTrackTime() {
        return nextTrackTime;
    }

    public void setNextTrackTime(Date nextTrackTime) {
        this.nextTrackTime = nextTrackTime;
    }

    public String getNextTrackContent() {
        return nextTrackContent;
    }

    public void setNextTrackContent(String nextTrackContent) {
        this.nextTrackContent = nextTrackContent == null ? null : nextTrackContent.trim();
    }

    public Long getBusinessType() {
        return businessType;
    }

    public void setBusinessType(Long businessType) {
        this.businessType = businessType;
    }

    public String getCustomerRequirement() {
        return customerRequirement;
    }

    public void setCustomerRequirement(String customerRequirement) {
        this.customerRequirement = customerRequirement == null ? null : customerRequirement.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Byte getIsOrder() {
        return isOrder;
    }

    public void setIsOrder(Byte isOrder) {
        this.isOrder = isOrder;
    }

    public Date getInvitationTime() {
        return invitationTime;
    }

    public void setInvitationTime(Date invitationTime) {
        this.invitationTime = invitationTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Long creatorId) {
        this.creatorId = creatorId;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Long getModifyManId() {
        return modifyManId;
    }

    public void setModifyManId(Long modifyManId) {
        this.modifyManId = modifyManId;
    }

    public Integer getProp1() {
        return prop1;
    }

    public void setProp1(Integer prop1) {
        this.prop1 = prop1;
    }

    public String getProp2() {
        return prop2;
    }

    public void setProp2(String prop2) {
        this.prop2 = prop2 == null ? null : prop2.trim();
    }

    public String getProp3() {
        return prop3;
    }

    public void setProp3(String prop3) {
        this.prop3 = prop3 == null ? null : prop3.trim();
    }

    public String getProp4() {
        return prop4;
    }

    public void setProp4(String prop4) {
        this.prop4 = prop4 == null ? null : prop4.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", shClueId=").append(shClueId);
        sb.append(", clueOrderId=").append(clueOrderId);
        sb.append(", source=").append(source);
        sb.append(", contactName=").append(contactName);
        sb.append(", contactMobile=").append(contactMobile);
        sb.append(", city=").append(city);
        sb.append(", babyName=").append(babyName);
        sb.append(", weixin=").append(weixin);
        sb.append(", addWeixinFriend=").append(addWeixinFriend);
        sb.append(", qq=").append(qq);
        sb.append(", addQqFriend=").append(addQqFriend);
        sb.append(", sendCoupon=").append(sendCoupon);
        sb.append(", sendCouponNum=").append(sendCouponNum);
        sb.append(", shSalesmanId=").append(shSalesmanId);
        sb.append(", serviceStatus=").append(serviceStatus);
        sb.append(", serviceSatisfy=").append(serviceSatisfy);
        sb.append(", expectRenew=").append(expectRenew);
        sb.append(", lastVisitTime=").append(lastVisitTime);
        sb.append(", nextTrackTime=").append(nextTrackTime);
        sb.append(", nextTrackContent=").append(nextTrackContent);
        sb.append(", businessType=").append(businessType);
        sb.append(", customerRequirement=").append(customerRequirement);
        sb.append(", status=").append(status);
        sb.append(", isOrder=").append(isOrder);
        sb.append(", invitationTime=").append(invitationTime);
        sb.append(", createTime=").append(createTime);
        sb.append(", creatorId=").append(creatorId);
        sb.append(", modifyTime=").append(modifyTime);
        sb.append(", modifyManId=").append(modifyManId);
        sb.append(", prop1=").append(prop1);
        sb.append(", prop2=").append(prop2);
        sb.append(", prop3=").append(prop3);
        sb.append(", prop4=").append(prop4);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}