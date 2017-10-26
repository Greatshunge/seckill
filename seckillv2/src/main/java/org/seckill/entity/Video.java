package org.seckill.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Jason on 2017/10/12.
 */
public class Video implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 第三方视频ID
     */
    private String videoId;
    /**
     * 视频封面图
     */
    private String thumbnail;
    /**
     * 播放时长
     */
    private int Duration;
    /**
     * 视频高度
     */
    private int Height;
    /**
     * 视频宽度
     */
    private int Width;
    /**
     * 视频大小
     */
    private int Size;
    /**
     * 播放url
     */
    private String PlayURL;
    /**
     * 视频状态 1-转码成功
     */
    private int transCode;
    private Date updateTime;

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public int getDuration() {
        return Duration;
    }

    public void setDuration(int duration) {
        Duration = duration;
    }

    public int getHeight() {
        return Height;
    }

    public void setHeight(int height) {
        Height = height;
    }

    public int getWidth() {
        return Width;
    }

    public void setWidth(int width) {
        Width = width;
    }

    public int getSize() {
        return Size;
    }

    public void setSize(int size) {
        Size = size;
    }

    public String getPlayURL() {
        return PlayURL;
    }

    public void setPlayURL(String playURL) {
        PlayURL = playURL;
    }

    public int getTransCode() {
        return transCode;
    }

    public void setTransCode(int transCode) {
        this.transCode = transCode;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Video{" +
                "videoId='" + videoId + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                ", Duration=" + Duration +
                ", Height=" + Height +
                ", Width=" + Width +
                ", Size=" + Size +
                ", PlayURL='" + PlayURL + '\'' +
                '}';
    }
}
