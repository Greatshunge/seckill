package org.seckill.info;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by shunge on 2017/5/27.
 */
public class SerAndVerInfo implements Serializable{

    private long servId;
    private long projId;
    private String servName;
    private String servIp;
    private int servVerPort;
    private long verId;
    private Date servVerUtime;
    private String servDesc;
    private Date servCtime;
    private String verName;
    private String verUrl;

    public SerAndVerInfo() {
    }

    public SerAndVerInfo(long servId, long projId,String servName, String servIp, int servVerPort, long verId, Date servVerUtime, String servDesc, Date servCtime, String verName, String verUrl) {
        this.servId = servId;
        this.projId = projId;
        this.servName = servName;
        this.servIp = servIp;
        this.servVerPort = servVerPort;
        this.verId = verId;
        this.servVerUtime = servVerUtime;
        this.servDesc = servDesc;
        this.servCtime = servCtime;
        this.verName = verName;
        this.verUrl = verUrl;
    }

    public long getServId() {
        return servId;
    }

    public void setServId(long servId) {
        this.servId = servId;
    }

    public long getProjId() {
        return projId;
    }

    public void setProjId(long projId) {
        this.projId = projId;
    }

    public String getServIp() {
        return servIp;
    }

    public void setServIp(String servIp) {
        this.servIp = servIp;
    }

    public int getServVerPort() {
        return servVerPort;
    }

    public void setServVerPort(int servVerPort) {
        this.servVerPort = servVerPort;
    }

    public long getVerId() {
        return verId;
    }

    public void setVerId(long verId) {
        this.verId = verId;
    }

    public Date getServVerUtime() {
        return servVerUtime;
    }

    public void setServVerUtime(Date servVerUtime) {
        this.servVerUtime = servVerUtime;
    }

    public String getServDesc() {
        return servDesc;
    }

    public void setServDesc(String servDesc) {
        this.servDesc = servDesc;
    }

    public Date getServCtime() {
        return servCtime;
    }

    public void setServCtime(Date servCtime) {
        this.servCtime = servCtime;
    }

    public String getVerName() {
        return verName;
    }

    public void setVerName(String verName) {
        this.verName = verName;
    }

    public String getVerUrl() {
        return verUrl;
    }

    public void setVerUrl(String verUrl) {
        this.verUrl = verUrl;
    }

    public String getServName() {
        return servName;
    }

    public void setServName(String servName) {
        this.servName = servName;
    }

    @Override
    public String toString() {
        return "SerAndVerInfo{" +
                "servId=" + servId +
                ", projId=" + projId +
                ",servName="+servName+
                ", servIp='" + servIp + '\'' +
                ", servVerPort=" + servVerPort +
                ", verId=" + verId +
                ", servVerUtime=" + servVerUtime +
                ", servDesc='" + servDesc + '\'' +
                ", servCtime=" + servCtime +
                ", verName='" + verName + '\'' +
                ", verUrl='" + verUrl + '\'' +
                '}';
    }
}
