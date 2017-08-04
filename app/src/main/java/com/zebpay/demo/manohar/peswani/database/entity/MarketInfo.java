package com.zebpay.demo.manohar.peswani.database.entity;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Manohar on 04-08-2017.
 */

public class MarketInfo implements Parcelable {

    private String market;
    private String buy;
    private String sell;
    private String currency;
    private String volume;

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    public String getBuy() {
        return buy;
    }

    public void setBuy(String buy) {
        this.buy = buy;
    }

    public String getSell() {
        return sell;
    }

    public void setSell(String sell) {
        this.sell = sell;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.market);
        dest.writeString(this.buy);
        dest.writeString(this.sell);
        dest.writeString(this.currency);
        dest.writeString(this.volume);
    }

    public MarketInfo() {
    }

    protected MarketInfo(Parcel in) {
        this.market = in.readString();
        this.buy = in.readString();
        this.sell = in.readString();
        this.currency = in.readString();
        this.volume = in.readString();
    }

    public static final Parcelable.Creator<MarketInfo> CREATOR = new Parcelable.Creator<MarketInfo>() {
        @Override
        public MarketInfo createFromParcel(Parcel source) {
            return new MarketInfo(source);
        }

        @Override
        public MarketInfo[] newArray(int size) {
            return new MarketInfo[size];
        }
    };
}
