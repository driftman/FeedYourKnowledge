package com.interview.fyk.feedyourknowledge.data.model;

import android.os.Parcel;
import android.os.Parcelable;

public class CategoryItem implements Parcelable {

    private String title;
    private String serviceName;
    private int ressourceId;

    public CategoryItem(String title, String serviceName, int ressourceId) {
        this.title = title;
        this.serviceName = serviceName;
        this.ressourceId = ressourceId;
    }

    protected CategoryItem(Parcel in) {
        title = in.readString();
        serviceName = in.readString();
        ressourceId = in.readInt();
    }

    public static final Creator<CategoryItem> CREATOR = new Creator<CategoryItem>() {
        @Override
        public CategoryItem createFromParcel(Parcel in) {
            return new CategoryItem(in);
        }

        @Override
        public CategoryItem[] newArray(int size) {
            return new CategoryItem[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public int getRessourceId() {
        return ressourceId;
    }

    public void setRessourceId(int ressourceId) {
        this.ressourceId = ressourceId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(serviceName);
        dest.writeInt(ressourceId);
    }
}
