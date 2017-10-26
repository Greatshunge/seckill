package org.seckill.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by shunge on 2017/5/23.
 */
public class GwtdProduct implements Serializable {

    private long prodId;
    private String prodName;
    private String prodDesc;
    private Date prodCtime;
    private Date prodUtime;

    public GwtdProduct() {
    }

    public GwtdProduct(long prodId, String prodName, String prodDesc, Date prodCtime, Date prodUtime) {
        this.prodId = prodId;
        this.prodName = prodName;
        this.prodDesc = prodDesc;
        this.prodCtime = prodCtime;
        this.prodUtime = prodUtime;
    }

    public long getProdId() {
        return prodId;
    }

    public void setProdId(long prodId) {
        this.prodId = prodId;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getProdDesc() {
        return prodDesc;
    }

    public void setProdDesc(String prodDesc) {
        this.prodDesc = prodDesc;
    }

    public Date getProdCtime() {
        return prodCtime;
    }

    public void setProdCtime(Date prodCtime) {
        this.prodCtime = prodCtime;
    }

    public Date getProdUtime() {
        return prodUtime;
    }

    public void setProdUtime(Date prodUtime) {
        this.prodUtime = prodUtime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GwtdProduct)) return false;

        GwtdProduct that = (GwtdProduct) o;

        return prodId == that.prodId;
    }

    @Override
    public int hashCode() {
        return (int) (prodId ^ (prodId >>> 32));
    }

    @Override
    public String toString() {
        return "GwtdProduct{" +
                "prodId=" + prodId +
                ", prodName='" + prodName + '\'' +
                ", prodDesc='" + prodDesc + '\'' +
                ", prodCtime=" + prodCtime +
                ", prodUtime=" + prodUtime +
                '}';
    }
}
