package org.seckill.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by shunge on 2017/5/24.
 */
public class GwtdProject implements Serializable {

    private long projId;
    private long prodId;
    private String projName;
    private String projDesc;
    private Date projCtime;
    private Date projUtime;

    public GwtdProject() {
    }

    public GwtdProject(long projId, long prodId, String projName, String projDesc, Date projCtime, Date projUtime) {
        this.projId = projId;
        this.prodId = prodId;
        this.projName = projName;
        this.projDesc = projDesc;
        this.projCtime = projCtime;
        this.projUtime = projUtime;
    }

    public long getProjId() {
        return projId;
    }

    public void setProjId(long projId) {
        this.projId = projId;
    }

    public long getProdId() {
        return prodId;
    }

    public void setProdId(long prodId) {
        this.prodId = prodId;
    }

    public String getProjName() {
        return projName;
    }

    public void setProjName(String projName) {
        this.projName = projName;
    }

    public String getProjDesc() {
        return projDesc;
    }

    public void setProjDesc(String projDesc) {
        this.projDesc = projDesc;
    }

    public Date getProjCtime() {
        return projCtime;
    }

    public void setProjCtime(Date projCtime) {
        this.projCtime = projCtime;
    }

    public Date getProjUtime() {
        return projUtime;
    }

    public void setProjUtime(Date projUtime) {
        this.projUtime = projUtime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GwtdProject)) return false;

        GwtdProject that = (GwtdProject) o;

        return projId == that.projId;
    }

    @Override
    public int hashCode() {
        return (int) (projId ^ (projId >>> 32));
    }

    @Override
    public String toString() {
        return "GwtdProject{" +
                "projId=" + projId +
                ", prodId=" + prodId +
                ", projName='" + projName + '\'' +
                ", projDesc='" + projDesc + '\'' +
                ", projCtime=" + projCtime +
                ", projUtime=" + projUtime +
                '}';
    }
}
