package org.seckill.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by shunge on 2017/5/24.
 */
public class GwtdVersion implements Serializable {

    private long verId;
    private long  projId;
    private String verName;
    private String verUrl;
    private int verLength;
    private String verDesc;
    private Date verCtime;
    private Date verUtime;

    public GwtdVersion() {
    }

    public GwtdVersion(long verId, long projId, String verName,String verUrl, int verLength, String verDesc, Date verCtime, Date verUtime) {
        this.verId = verId;
        this.projId = projId;
        this.verName = verName;
        this.verUrl = verUrl;
        this.verLength = verLength;
        this.verDesc = verDesc;
        this.verCtime = verCtime;
        this.verUtime = verUtime;
    }

    public long getVerId() {
        return verId;
    }

    public void setVerId(long verId) {
        this.verId = verId;
    }

    public long getProjId() {
        return projId;
    }

    public void setProjId(long projId) {
        this.projId = projId;
    }

    public String getVerName() {
        return verName;
    }

    public void setVerName(String verName) {
        this.verName = verName;
    }

    public int getVerLength() {
        return verLength;
    }

    public void setVerLength(int verLength) {
        this.verLength = verLength;
    }

    public String getVerDesc() {
        return verDesc;
    }

    public void setVerDesc(String verDesc) {
        this.verDesc = verDesc;
    }

    public Date getVerCtime() {
        return verCtime;
    }

    public void setVerCtime(Date verCtime) {
        this.verCtime = verCtime;
    }

    public Date getVerUtime() {
        return verUtime;
    }

    public void setVerUtime(Date verUtime) {
        this.verUtime = verUtime;
    }

    public String getVerUrl() {
        return verUrl;
    }

    public void setVerUrl(String verUrl) {
        this.verUrl = verUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GwtdVersion)) return false;

        GwtdVersion that = (GwtdVersion) o;

        return verId == that.verId;
    }

    @Override
    public int hashCode() {
        return (int) (verId ^ (verId >>> 32));
    }

    @Override
    public String toString() {
        return "GwtdVersion{" +
                "verId=" + verId +
                ", projId=" + projId +
                ", verName='" + verName + '\'' +
                ", verUrl='" + verUrl + '\'' +
                ", verLength=" + verLength +
                ", verDesc='" + verDesc + '\'' +
                ", verCtime=" + verCtime +
                ", verUtime=" + verUtime +
                '}';
    }
}
