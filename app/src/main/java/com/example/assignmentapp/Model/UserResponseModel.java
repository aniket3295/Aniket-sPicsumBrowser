package com.example.assignmentapp.Model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.StringRes;

import com.google.gson.annotations.SerializedName;


public class UserResponseModel implements Parcelable {

  @SerializedName("format")
  private String format;
  @SerializedName("width")
  private long width;
  @SerializedName("height")
  private long height;
  @SerializedName("filename")
  private String filename;
  @SerializedName("id")
  private int id;
  @SerializedName("author")
  private String author;
  @SerializedName("author_url")
  private String author_url;
  @SerializedName("post_url")
  private String post_url;

    protected UserResponseModel(Parcel in) {
        format = in.readString();
        width = in.readLong();
        height = in.readLong();
        filename = in.readString();
        id = in.readInt();
        author = in.readString();
        author_url = in.readString();
        post_url = in.readString();
    }

    public static final Creator<UserResponseModel> CREATOR = new Creator<UserResponseModel>() {
        @Override
        public UserResponseModel createFromParcel(Parcel in) {
            return new UserResponseModel(in);
        }

        @Override
        public UserResponseModel[] newArray(int size) {
            return new UserResponseModel[size];
        }
    };

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public long getWidth() {
        return width;
    }

    public void setWidth(long width) {
        this.width = width;
    }

    public long getHeight() {
        return height;
    }

    public void setHeight(long height) {
        this.height = height;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthor_url() {
        return author_url;
    }

    public void setAuthor_url(String author_url) {
        this.author_url = author_url;
    }

    public String getPost_url() {
        return post_url;
    }

    public void setPost_url(String post_url) {
        this.post_url = post_url;
    }

    @Override
    public String toString() {
        return "UserResponseModel{" +
                "format='" + format + '\'' +
                ", width=" + width +
                ", height=" + height +
                ", filename='" + filename + '\'' +
                ", id=" + id +
                ", author='" + author + '\'' +
                ", author_url='" + author_url + '\'' +
                ", post_url='" + post_url + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(format);
        dest.writeLong(width);
        dest.writeLong(height);
        dest.writeString(filename);
        dest.writeInt(id);
        dest.writeString(author);
        dest.writeString(author_url);
        dest.writeString(post_url);
    }
}
