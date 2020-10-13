package cn.edu.hust.model;

/**
 * Created by xiaolei03 on 17/1/10.
 */
public class StudentInfoImport {
    private String xh;
    private String name;
    private String csrq;
    private String yjxkdm;
    private String yjxkmc;
    private String ejxkdm;
    private String ejxkmc;
    private String ds;
    private String lwtm;
    private String rxnf;
    private String hxwsj;
    private String dbsj;

    public String getXh() {
        return xh;
    }

    public void setXh(String xh) {
        this.xh = xh;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCsrq() {
        return csrq;
    }

    public void setCsrq(String csrq) {
        this.csrq = csrq;
    }

    public String getYjxkdm() {
        return yjxkdm;
    }

    public void setYjxkdm(String yjxkdm) {
        this.yjxkdm = yjxkdm;
    }

    public String getYjxkmc() {
        return yjxkmc;
    }

    public void setYjxkmc(String yjxkmc) {
        this.yjxkmc = yjxkmc;
    }

    public String getEjxkdm() {
        return ejxkdm;
    }

    public void setEjxkdm(String ejxkdm) {
        this.ejxkdm = ejxkdm;
    }

    public String getEjxkmc() {
        return ejxkmc;
    }

    public void setEjxkmc(String ejxkmc) {
        this.ejxkmc = ejxkmc;
    }

    public String getDs() {
        return ds;
    }

    public void setDs(String ds) {
        this.ds = ds;
    }

    public String getLwtm() {
        return lwtm;
    }

    public void setLwtm(String lwtm) {
        this.lwtm = lwtm;
    }

    public String getRxnf() {
        return rxnf;
    }

    public void setRxnf(String rxnf) {
        this.rxnf = rxnf;
    }

    public String getHxwsj() {
        return hxwsj;
    }

    public void setHxwsj(String hxwsj) {
        this.hxwsj = hxwsj;
    }

    public String getDbsj() {
        return dbsj;
    }

    public void setDbsj(String dbsj) {
        this.dbsj = dbsj;
    }

    @Override
    public String toString() {
        return "StudentInfoImport{" +
                "xh='" + xh + '\'' +
                ", name='" + name + '\'' +
                ", csrq='" + csrq + '\'' +
                ", yjxkdm='" + yjxkdm + '\'' +
                ", yjxkmc='" + yjxkmc + '\'' +
                ", ejxkdm='" + ejxkdm + '\'' +
                ", ejxkmc='" + ejxkmc + '\'' +
                ", ds='" + ds + '\'' +
                ", lwtm='" + lwtm + '\'' +
                ", rxnf='" + rxnf + '\'' +
                ", hxwsj='" + hxwsj + '\'' +
                ", dbsj='" + dbsj + '\'' +
                '}';
    }
}
