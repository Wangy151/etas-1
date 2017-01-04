package cn.edu.hust.dao;

import cn.edu.hust.model.MasterThesisApply;
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
    /**
     * 基本信息表
     * @param userId
     * @return
     */
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

    /**
     * 硕士 推荐表
     * @param userId
     * @return
     */
    @Select( "select count(*) from master_thesis_apply where zzxh = #{userId} ")
    int hasMasterUser(@Param("userId") String userId);

    @Insert(" INSERT INTO master_thesis_apply(zzxh) VALUES (#{userId}) ")
    int initMasterTjb(@Param("userId") String userId);

    @Select(" SELECT * FROM master_thesis_apply WHERE zzxh = #{userId} ")
    MasterThesisApply getMasterTjb(@Param("userId") String userId);

    @Update(" UPDATE master_thesis_apply SET " +
            " zzxh = #{model.zzxh}, zzxm = #{model.zzxm}, xb = #{model.xb}, csny = #{model.csny}, mz = #{model.mz}, " +
            " lwtm = #{model.lwtm}, lwywtm = #{model.lwywtm}, rxny = #{model.rxny}, dbrq = #{model.dbrq}, hdxwrq = #{model.hdxwrq}, " +
            " yjxkdm = #{model.yjxkdm}, yjxkmc = #{model.yjxkmc}, ejxkdm = #{model.ejxkdm}, ejxkmc = #{model.ejxkmc}, lwsjdyjfx = #{model.lwsjdyjfx} " +
            " WHERE zzxh = #{model.zzxh} ")
    int saveMasterTjb1(@Param("model") MasterThesisApply model);

    @Update(" UPDATE master_thesis_apply SET " +
            " dyzz = #{model.dyzz}, dezz = #{model.dezz}, sci = #{model.sci}, ei = #{model.ei}, ssci = #{model.ssci}, " +
            " istp = #{model.istp}, zls = #{model.zls}, cgjx = #{model.cgjx} " +
            " WHERE zzxh = #{model.zzxh} ")
    int saveMasterTjb2(@Param("model") MasterThesisApply model);

    @Update(" UPDATE master_thesis_apply SET " +
            " gdxwfs = #{model.gdxwfs}, bkjdxx = #{model.bkjdxx}, gdssxwdw = #{model.gdssxwdw}, zzdw = #{model.zzdw}, zzdz = #{model.zzdz}, " +
            " zzyb = #{model.zzyb}, zzdh = #{model.zzdh}, zc = #{model.zc}, zw = #{model.zw}, zdjsxm = #{model.zdjsxm}, " +
            " zdjsyjfx = #{model.zdjsyjfx} " +
            " WHERE zzxh = #{model.zzxh} ")
    int saveMasterTjb3(@Param("model") MasterThesisApply model);

    @Update(" UPDATE master_thesis_apply SET " +
            " fbxslw = #{model.fbxslw}, cbzz = #{model.cbzz}, hjxm = #{model.hjxm}, lwdzycxd = #{model.lwdzycxd}, dwtjyy = #{model.dwtjyy}, " +
            " tbrq = #{model.tbrq} " +
            " WHERE zzxh = #{model.zzxh} ")
    int saveMasterTjb4(@Param("model") MasterThesisApply model);

    /**
     * 博士推荐表
     * @param userId
     * @return
     */
    @Select( "select count(*) from doctor_thesis_apply where user_id = #{userId}")
    int hasDoctorUser(@Param("userId") String userId);

    @Insert(" INSERT INTO doctor_thesis_apply(user_id) VALUES (#{userId})")
    int initDoctorThesisApply(@Param("userId") String userId);

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
