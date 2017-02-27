package cn.edu.hust.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Created by xiaolei03 on 16/12/5.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MasterThesisApplyInfoRequest {
    private String ssdm = "42"; // 省市代码 42
    private String ssmc = "湖北省"; // 省市名称 湖北省
    private String xxdm = "10487"; // 学校代码 10487
    private String xxmc = "华中科技大学"; // 学校名称 华中科技大学
    private String cplx; // 参评类型
    private String zzxh; // 作者学号
    private String xh; // 序号
    private String lwzwgjz; // 论文中文关键字
    private int lwys; // 论文页数
    private String gdlb; // 攻读类别
    private String lwtjblj; // 论文推荐表路径
    private String lwywlj; // 论文原文路径
    private String zzzc; // 作者职称
    private String xxlxr; // 学校联系人
    private String bz; // 备注

    private String zzxm; // 作者姓名
    private String xb; // 性别
    private String csny; // 出生年月
    private String mz; // 名族
    private String lwtm; // 论文题目
    private String lwywtm; // 论文英文题目
    private String rxny; // 入学年月
    private String dbrq; // 论文答辩日期
    private String hdxwrq; // 获得学位日期
    private String yjxkdm; // 一级学科代码
    private String yjxkmc; // 一级学科名称
    private String ejxkdm; // 二级学科代码
    private String ejxkmc; // 二级学科名称
    private String lwsjdyjfx; // 论文涉及的研究方向

    private int dyzz; // 发表学术论文数(第一作者)
    private int dezz; // 发表学术论文数(第二作者)
    private int sci; // 论文被检索数(SCI)
    private int ei; // 论文被检索数(EI)
    private int ssci; // 论文被检索数(SSCI)
    private int istp; // 论文被检索数(ISTP)
    private int zls; // 获发明或实用新型专利数
    private String cgjx; // 出国进修的时间 国名 内容

    private String gdxwfs; // 攻读硕士学位方式
    private String bkjdxx; // 本科就读学校
    private String gdssxwdw; // 攻读硕士学位单位
    private String zzdw; // 作者单位
    private String zzyb; //作者邮编
    private String zzdz; // 作者地址
    private String zzdh; // 作者电话
    private String zc; // 职称
    private String zw; // 职务
    private String zdjsxm; // 指导教师姓名
    private String zdjsyjfx; // 指导教师研究方向

    private String fbxslw; // 发表学术论文
    private String cbzz; // 出版专著
    private String hjxm; // 获奖项目
    private String lwdzycxd; // 论文的主要创新点
    private String dwtjyj; // 单位推荐意见

    private String tbrq; // 填表日期

    private String studentType; // 学生类型

    public String getSsdm() {
        return ssdm;
    }

    public void setSsdm(String ssdm) {
        this.ssdm = ssdm;
    }

    public String getSsmc() {
        return ssmc;
    }

    public void setSsmc(String ssmc) {
        this.ssmc = ssmc;
    }

    public String getXxdm() {
        return xxdm;
    }

    public void setXxdm(String xxdm) {
        this.xxdm = xxdm;
    }

    public String getXxmc() {
        return xxmc;
    }

    public void setXxmc(String xxmc) {
        this.xxmc = xxmc;
    }

    public String getCplx() {
        return cplx;
    }

    public void setCplx(String cplx) {
        this.cplx = cplx;
    }

    public String getZzxh() {
        return zzxh;
    }

    public void setZzxh(String zzxh) {
        this.zzxh = zzxh;
    }

    public String getXh() {
        return xh;
    }

    public void setXh(String xh) {
        this.xh = xh;
    }

    public String getLwzwgjz() {
        return lwzwgjz;
    }

    public void setLwzwgjz(String lwzwgjz) {
        this.lwzwgjz = lwzwgjz;
    }

    public int getLwys() {
        return lwys;
    }

    public void setLwys(int lwys) {
        this.lwys = lwys;
    }

    public String getGdlb() {
        return gdlb;
    }

    public void setGdlb(String gdlb) {
        this.gdlb = gdlb;
    }

    public String getLwtjblj() {
        return lwtjblj;
    }

    public void setLwtjblj(String lwtjblj) {
        this.lwtjblj = lwtjblj;
    }

    public String getLwywlj() {
        return lwywlj;
    }

    public void setLwywlj(String lwywlj) {
        this.lwywlj = lwywlj;
    }

    public String getZzzc() {
        return zzzc;
    }

    public void setZzzc(String zzzc) {
        this.zzzc = zzzc;
    }

    public String getXxlxr() {
        return xxlxr;
    }

    public void setXxlxr(String xxlxr) {
        this.xxlxr = xxlxr;
    }

    public String getBz() {
        return bz;
    }

    public void setBz(String bz) {
        this.bz = bz;
    }

    public String getZzxm() {
        return zzxm;
    }

    public void setZzxm(String zzxm) {
        this.zzxm = zzxm;
    }

    public String getXb() {
        return xb;
    }

    public void setXb(String xb) {
        this.xb = xb;
    }

    public String getCsny() {
        return csny;
    }

    public void setCsny(String csny) {
        this.csny = csny;
    }

    public String getMz() {
        return mz;
    }

    public void setMz(String mz) {
        this.mz = mz;
    }

    public String getLwtm() {
        return lwtm;
    }

    public void setLwtm(String lwtm) {
        this.lwtm = lwtm;
    }

    public String getLwywtm() {
        return lwywtm;
    }

    public void setLwywtm(String lwywtm) {
        this.lwywtm = lwywtm;
    }

    public String getRxny() {
        return rxny;
    }

    public void setRxny(String rxny) {
        this.rxny = rxny;
    }

    public String getDbrq() {
        return dbrq;
    }

    public void setDbrq(String dbrq) {
        this.dbrq = dbrq;
    }

    public String getHdxwrq() {
        return hdxwrq;
    }

    public void setHdxwrq(String hdxwrq) {
        this.hdxwrq = hdxwrq;
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

    public String getLwsjdyjfx() {
        return lwsjdyjfx;
    }

    public void setLwsjdyjfx(String lwsjdyjfx) {
        this.lwsjdyjfx = lwsjdyjfx;
    }

    public int getDyzz() {
        return dyzz;
    }

    public void setDyzz(int dyzz) {
        this.dyzz = dyzz;
    }

    public int getDezz() {
        return dezz;
    }

    public void setDezz(int dezz) {
        this.dezz = dezz;
    }

    public int getSci() {
        return sci;
    }

    public void setSci(int sci) {
        this.sci = sci;
    }

    public int getEi() {
        return ei;
    }

    public void setEi(int ei) {
        this.ei = ei;
    }

    public int getSsci() {
        return ssci;
    }

    public void setSsci(int ssci) {
        this.ssci = ssci;
    }

    public int getIstp() {
        return istp;
    }

    public void setIstp(int istp) {
        this.istp = istp;
    }

    public int getZls() {
        return zls;
    }

    public void setZls(int zls) {
        this.zls = zls;
    }

    public String getCgjx() {
        return cgjx;
    }

    public void setCgjx(String cgjx) {
        this.cgjx = cgjx;
    }

    public String getGdxwfs() {
        return gdxwfs;
    }

    public void setGdxwfs(String gdxwfs) {
        this.gdxwfs = gdxwfs;
    }

    public String getBkjdxx() {
        return bkjdxx;
    }

    public void setBkjdxx(String bkjdxx) {
        this.bkjdxx = bkjdxx;
    }

    public String getGdssxwdw() {
        return gdssxwdw;
    }

    public void setGdssxwdw(String gdssxwdw) {
        this.gdssxwdw = gdssxwdw;
    }

    public String getZzdw() {
        return zzdw;
    }

    public void setZzdw(String zzdw) {
        this.zzdw = zzdw;
    }

    public String getZzyb() {
        return zzyb;
    }

    public void setZzyb(String zzyb) {
        this.zzyb = zzyb;
    }

    public String getZzdz() {
        return zzdz;
    }

    public void setZzdz(String zzdz) {
        this.zzdz = zzdz;
    }

    public String getZzdh() {
        return zzdh;
    }

    public void setZzdh(String zzdh) {
        this.zzdh = zzdh;
    }

    public String getZc() {
        return zc;
    }

    public void setZc(String zc) {
        this.zc = zc;
    }

    public String getZw() {
        return zw;
    }

    public void setZw(String zw) {
        this.zw = zw;
    }

    public String getZdjsxm() {
        return zdjsxm;
    }

    public void setZdjsxm(String zdjsxm) {
        this.zdjsxm = zdjsxm;
    }

    public String getZdjsyjfx() {
        return zdjsyjfx;
    }

    public void setZdjsyjfx(String zdjsyjfx) {
        this.zdjsyjfx = zdjsyjfx;
    }

    public String getFbxslw() {
        return fbxslw;
    }

    public void setFbxslw(String fbxslw) {
        this.fbxslw = fbxslw;
    }

    public String getCbzz() {
        return cbzz;
    }

    public void setCbzz(String cbzz) {
        this.cbzz = cbzz;
    }

    public String getHjxm() {
        return hjxm;
    }

    public void setHjxm(String hjxm) {
        this.hjxm = hjxm;
    }

    public String getLwdzycxd() {
        return lwdzycxd;
    }

    public void setLwdzycxd(String lwdzycxd) {
        this.lwdzycxd = lwdzycxd;
    }

    public String getDwtjyj() {
        return dwtjyj;
    }

    public void setDwtjyj(String dwtjyj) {
        this.dwtjyj = dwtjyj;
    }

    public String getTbrq() {
        return tbrq;
    }

    public void setTbrq(String tbrq) {
        this.tbrq = tbrq;
    }

    public String getStudentType() {
        return studentType;
    }

    public void setStudentType(String studentType) {
        this.studentType = studentType;
    }
}
