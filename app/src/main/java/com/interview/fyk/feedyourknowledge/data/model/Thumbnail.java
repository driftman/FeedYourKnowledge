package com.interview.fyk.feedyourknowledge.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Root;

/**
 * Created by abk on 27/01/2018.
 */

@Root(name = "thumbnail", strict = false)
public class Thumbnail implements Parcelable {

    @Attribute(name = "url")
    private String url;

    public Thumbnail() {
    }

    public Thumbnail(String url) {
        this.url = url;
    }

    protected Thumbnail(Parcel in) {
        url = in.readString();
    }

    public static final Creator<Thumbnail> CREATOR = new Creator<Thumbnail>() {
        @Override
        public Thumbnail createFromParcel(Parcel in) {
            return new Thumbnail(in);
        }

        @Override
        public Thumbnail[] newArray(int size) {
            return new Thumbnail[size];
        }
    };

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(url);
    }
}
