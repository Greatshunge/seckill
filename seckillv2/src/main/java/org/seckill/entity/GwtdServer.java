package org.seckill.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by shunge on 2017/5/24.
 */
public class GwtdServer implements Serializable {

    private long servId;
    private long projId;
    private String servName;
    private String servIp;
    private int servVerPort;
    private int verId;
    private Date servVerUtime;
    private String servDesc;
    private Date servCtime;
    private Date servUtime;

    public GwtdServer() {
    }

    public GwtdServer(long servId, long projId, String servName, String servIp, int servVerPort, int verId, Date servVerUtime, String servDesc, Date servCtime, Date servUtime) {
        this.servId = servId;
        this.projId = projId;
        this.servName = servName;
        this.servIp = servIp;
        this.servVerPort = servVerPort;
        this.verId = verId;
        this.servVerUtime = servVerUtime;
        this.servDesc = servDesc;
        this.servCtime = servCtime;
        this.servUtime = servUtime;
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

    public String getServName() {
        return servName;
    }

    public void setServName(String servName) {
        this.servName = servName;
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

    public int getVerId() {
        return verId;
    }

    public void setVerId(int verId) {
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

    public Date getServUtime() {
        return servUtime;
    }

    public void setServUtime(Date servUtime) {
        this.servUtime = servUtime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GwtdServer)) return false;

        GwtdServer that = (GwtdServer) o;

        return servId == that.servId;
    }

    @Override
    public int hashCode() {
        return (int) (servId ^ (servId >>> 32));
    }

    @Override
    public String toString() {
        return "GwtdServer{" +
                "servId=" + servId +
                ", projId=" + projId +
                ", servName='" + servName + '\'' +
                ", servIp='" + servIp + '\'' +
                ", servVerPort=" + servVerPort +
                ", verId=" + verId +
                ", servVerUtime=" + servVerUtime +
                ", servDesc='" + servDesc + '\'' +
                ", servCtime=" + servCtime +
                ", servUtime=" + servUtime +
                '}';
    }
}
