package com.zebpay.demo.manohar.peswani.database.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Manohar on 04-08-2017.
 */

@Entity(tableName = "zebpay_feed")
public class ZebPayFeed implements Parcelable {
    public static final Parcelable.Creator<ZebPayFeed> CREATOR = new Parcelable.Creator<ZebPayFeed>() {
        @Override
        public ZebPayFeed createFromParcel(Parcel source) {
            return new ZebPayFeed(source);
        }

        @Override
        public ZebPayFeed[] newArray(int size) {
            return new ZebPayFeed[size];
        }
    };

    @SerializedName("AcitvityType")
    private int acitvityType;
    @SerializedName("SourceImageUrl")
    private String sourceImageUrl;
    @SerializedName("Title")
    private String title;
    @SerializedName("Description")
    private String description;
    @SerializedName("Time")
    private String time;
    @SerializedName("RefNumber")
    private String refNumber;
    @SerializedName("Name")
    private String name;
    @SerializedName("RefGuid")
    @PrimaryKey
    private String refGuid;
    @SerializedName("PaymentTypeId")
    private String paymentTypeId;
    @SerializedName("PaymentTypeGuid")
    private String paymentTypeGuid;

    public ZebPayFeed() {
    }

    protected ZebPayFeed(Parcel in) {
        this.acitvityType = in.readInt();
        this.sourceImageUrl = in.readString();
        this.title = in.readString();
        this.description = in.readString();
        this.time = in.readString();
        this.refNumber = in.readString();
        this.name = in.readString();
        this.refGuid = in.readString();
        this.paymentTypeId = in.readString();
        this.paymentTypeGuid = in.readString();
    }

    public int getAcitvityType() {
        return acitvityType;
    }

    public void setAcitvityType(int acitvityType) {
        this.acitvityType = acitvityType;
    }

    public String getSourceImageUrl() {
        return sourceImageUrl;
    }

    public void setSourceImageUrl(String sourceImageUrl) {
        this.sourceImageUrl = sourceImageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRefNumber() {
        return refNumber;
    }

    public void setRefNumber(String refNumber) {
        this.refNumber = refNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRefGuid() {
        return refGuid;
    }

    public void setRefGuid(String refGuid) {
        this.refGuid = refGuid;
    }

    public String getPaymentTypeId() {
        return paymentTypeId;
    }

    public void setPaymentTypeId(String paymentTypeId) {
        this.paymentTypeId = paymentTypeId;
    }

    public String getPaymentTypeGuid() {
        return paymentTypeGuid;
    }

    public void setPaymentTypeGuid(String paymentTypeGuid) {
        this.paymentTypeGuid = paymentTypeGuid;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.acitvityType);
        dest.writeString(this.sourceImageUrl);
        dest.writeString(this.title);
        dest.writeString(this.description);
        dest.writeString(this.time);
        dest.writeString(this.refNumber);
        dest.writeString(this.name);
        dest.writeString(this.refGuid);
        dest.writeString(this.paymentTypeId);
        dest.writeString(this.paymentTypeGuid);
    }
}
