package com.interview.fyk.feedyourknowledge.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * Created by abk on 26/01/2018.
 */

@Root(name = "item", strict = false)
public class FeedItem implements Parcelable {

    @Element(name = "title")
    private String title;
    @Element(name = "category")
    private String category;
    @Element(name = "link")
    private String link;
    @Element(name = "description")
    private String description;
    @Element(name = "creator")
    private String author;
    @Element(name = "thumbnail")
    private Thumbnail thumbnail;
    @Element(name = "pubDate")
    private String pubDate;

    public FeedItem() {
    }

    public FeedItem(String pubDate, String title, String link, String description, String author, Thumbnail thumbnail, String category) {
        this.pubDate = pubDate;
        this.title = title;
        this.link = link;
        this.description = description;
        this.author = author;
        this.thumbnail = thumbnail;
        this.category = category;
    }

    protected FeedItem(Parcel in) {
        pubDate = in.readString();
        title = in.readString();
        link = in.readString();
        description = in.readString();
        author = in.readString();
        thumbnail = in.readParcelable(Thumbnail.class.getClassLoader());
        category = in.readString();
    }

    public static final Creator<FeedItem> CREATOR = new Creator<FeedItem>() {
        @Override
        public FeedItem createFromParcel(Parcel in) {
            return new FeedItem(in);
        }

        @Override
        public FeedItem[] newArray(int size) {
            return new FeedItem[size];
        }
    };

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Thumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(pubDate);
        parcel.writeString(title);
        parcel.writeString(link);
        parcel.writeString(description);
        parcel.writeString(author);
        parcel.writeParcelable(thumbnail, i);
        parcel.writeString(category);
    }
}