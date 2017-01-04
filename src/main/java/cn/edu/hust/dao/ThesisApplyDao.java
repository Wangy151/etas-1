package cn.edu.hust.dao;

import cn.edu.hust.model.ThesisBasicInfo;
import cn.edu.hust.model.request.DoctorThesisApplyInfoRequest;
import cn.edu.hust.model.request.MasterThesisApplyInfoRequest;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * Created by xiaolei03 on 16/12/6.
 */
@Mapper
public interface ThesisApplyDao {
    @Select(" SELECT COUNT(*) FROM thesis_basic_info WHERE zzxh = #{userId} ")
    int hasApplyBasicInfoTable(@Param("userId") String userId);

    @Insert(" INSERT INTO thesis_basic_info(zzxh) VALUES (#{userId}) ")
    int initThesisBasicInfoTable(@Param("userId") String userId);

    @Select(" SELECT * FROM thesis_basic_info WHERE zzxh = #{userId} ")
    ThesisBasicInfo getThesisBasicInfo(@Param("userId") String userId);

    @Update(" UPDATE thesis_basic_info SET ssdm = #{thesisBasicInfo.ssdm}, ssmc = #{thesisBasicInfo.ssmc}, " +
            " xxdm = #{thesisBasicInfo.xxdm}, xxmc = #{thesisBasicInfo.xxmc}, cplx = #{thesisBasicInfo.cplx}, " +
            " xh = #{thesisBasicInfo.xh}, zzxm = #{thesisBasicInfo.zzxm}, xb = #{thesisBasicInfo.xb}, " +
            " csny = #{thesisBasicInfo.csny}, mz = #{thesisBasicInfo.mz}, dsxm = #{thesisBasicInfo.dsxm}, " +
            " lwtm = #{thesisBasicInfo.lwtm}, lwywtm = #{thesisBasicInfo.lwywtm}, yjfx = #{thesisBasicInfo.yjfx}, " +
            " lwzwgjz = #{thesisBasicInfo.lwzwgjz}, lwys = #{thesisBasicInfo.lwys}, lwtjblj = #{thesisBasicInfo.lwtjblj}, " +
            " lwywlj = #{thesisBasicInfo.lwywlj}, rxny = #{thesisBasicInfo.rxny}, hdxwrq = #{thesisBasicInfo.hdxwrq}, " +
            " yjxkdm = #{thesisBasicInfo.yjxkdm}, yjxkmc = #{thesisBasicInfo.yjxkmc}, ejxkdm = #{thesisBasicInfo.ejxkdm}, " +
            " ejxkmc = #{thesisBasicInfo.ejxkmc}, gdlb = #{thesisBasicInfo.gdlb}, gdfs = #{thesisBasicInfo.gdfs}, " +
            " zzzc = #{thesisBasicInfo.zzzc}, xxlxr = #{thesisBasicInfo.xxlxr}, bz = #{thesisBasicInfo.bz}, " +
            " applyYear = #{thesisBasicInfo.applyYear}, studentType = #{thesisBasicInfo.studentType}, applyStatus = #{thesisBasicInfo.applyStatus} " +
            " WHERE zzxh = #{thesisBasicInfo.zzxh}" )
    int saveThesisBasicInfoTable(@Param("thesisBasicInfo") ThesisBasicInfo thesisBasicInfo);

    @Insert(" INSERT INTO master_thesis_apply(zzxh) VALUES (#{userId})")
    int initMasterThesisApply(@Param("userId") String userId);

    @Insert(" INSERT INTO doctor_thesis_apply(zzxh) VALUES (#{userId})")
    int initDocterThesisApply(@Param("userId") String userId);

    @Select( "select count(*) from master_thesis_apply where zzxh = #{userId}")
    int hasMasterUser(@Param("userId") String userId);

    @Select( "select count(*) from doctor_thesis_apply where zzxh = #{userId}")
    int hasDoctorUser(@Param("userId") String userId);

    @Insert(" INSERT INTO master_thesis_apply " +
            " (ssdm,ssmc,xxdm,xxmc,cplx,zzxh,xh,lwzwgjz,lwys,gdlb,lwtjblj," +
            " lwywlj,zzzc,xxlxr,bz,zzxm,xb,csny,mz,lwtm,lwywtm,rxny,dbrq," +
            " hdxwrq,yjxkdm,yjxkmc,ejxkdm,ejxkmc,lwsjdyjfx,dyzz,dezz,sci," +
            " ei,ssci,istp,zls,cgjx,gdxwfs,bkjdxx,gdssxwdw,zzdw,zzyb,zzdz," +
            " zzdh,zc,zw,zdjsxm,zdjsyjfx,fbxslw,cbzz,hjxm,lwdzycxd,dwtjyy," +
            " tbrq,student_type) " +
            " VALUES (#{request.ssdm},#{request.ssmc},#{request.xxdm},#{request.xxmc},#{request.cplx}," +
            " #{request.zzxh},#{request.xh},#{request.lwzwgjz},#{request.lwys},#{request.gdlb}," +
            " #{request.lwtjblj},#{request.lwywlj},#{request.zzzc},#{request.xxlxr},#{request.bz}," +
            " #{request.zzxm},#{request.xb},#{request.csny},#{request.mz},#{request.lwtm},#{request.lwywtm}," +
            " #{request.rxny},#{request.dbrq},#{request.hdxwrq},#{request.yjxkdm},#{request.yjxkmc}," +
            " #{request.ejxkdm},#{request.ejxkmc},#{request.lwsjdyjfx},#{request.dyzz},#{request.dezz}," +
            " #{request.sci},#{request.ei},#{request.ssci},#{request.istp},#{request.zls},#{request.cgjx}," +
            " #{request.gdxwfs},#{request.bkjdxx},#{request.gdssxwdw},#{request.zzdw},#{request.zzyb}," +
            " #{request.zzdz},#{request.zzdh},#{request.zc},#{request.zw},#{request.zdjsxm}," +
            " #{request.zdjsyjfx},#{request.fbxslw},#{request.cbzz},#{request.hjxm},#{request.lwdzycxd}," +
            " #{request.dwtjyy},#{request.tbrq},#{request.studentType}) ")
    int saveMaster(@Param("request") MasterThesisApplyInfoRequest request);

    @Insert(" INSERT INTO doctor_thesis_apply " +
            " (ssdm,ssmc,xxdm,xxmc,zzxh,xh,cplx,xb,mz,csny,rxny,gdxwfs,lwzwgjz,lwys,gdlb," +
            " lwtjblj,lwywlj,zzzc,xxlxr,bz,lwtm,lwywtm,zzxm,dbrq,hdxwrq,lwsjdyjfx,yjxkdm," +
            " yjxkmc,ejxkdm,ejxkmc,zdjsxm,zdjsyjfx,fbxslw,cbzz,hjxm,lwdzycxd,dwtjyy,tbrq,student_type) " +
            " VALUES (#{request.ssdm}},#{request.#{request.ssmc},#{request.xxdm},#{request.xxmc}," +
            " #{request.zzxh},#{request.xh},#{request.cplx},#{request.xb},#{request.mz},#{request.csny}," +
            " #{request.rxny},#{request.gdxwfs},#{request.lwzwgjz},#{request.lwys},#{request.gdlb}," +
            " #{request.lwtjblj},#{request.lwywlj},#{request.zzzc},#{request.xxlxr},#{request.bz}," +
            " #{request.lwtm},#{request.lwywtm},#{request.zzxm},#{request.dbrq},#{request.hdxwrq}," +
            " #{request.lwsjdyjfx},#{request.yjxkdm},#{request.yjxkmc},#{request.ejxkdm},#{request.ejxkmc}," +
            " #{request.zdjsxm},#{request.zdjsyjfx},#{request.fbxslw},#{request.cbzz},#{request.hjxm}," +
            " #{request.lwdzycxd},#{request.dwtjyy},#{request.tbrq},#{request.studentType}) ")
    int saveDoctor(@Param("request")DoctorThesisApplyInfoRequest request);

}