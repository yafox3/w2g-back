package ru.romanov.watchtogether.model;

import java.util.Objects;

public class Video {

    private String url;
    private String title;
    private String img;
    private String platform;
    private String oid;
    private String id;

    public Video() {
    }

    public Video(String url, String title, String img, String platform, String oid, String id) {
        this.url = url;
        this.title = title;
        this.img = img;
        this.platform = platform;
        this.oid = oid;
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Video video = (Video) o;
        return Objects.equals(url, video.url) && Objects.equals(title, video.title) && Objects.equals(img, video.img) && Objects.equals(platform, video.platform) && Objects.equals(oid, video.oid) && Objects.equals(id, video.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url, title, img, platform, oid, id);
    }
}
