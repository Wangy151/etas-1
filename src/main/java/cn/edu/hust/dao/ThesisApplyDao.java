package cn.edu.hust.dao;

import cn.edu.hust.model.*;
import org.apache.ibatis.annotations.Delete;
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
            " apply_year = #{thesisBasicInfo.applyYear}, student_type = #{thesisBasicInfo.studentType}, apply_status = #{thesisBasicInfo.applyStatus}, " +
            " department = #{thesisBasicInfo.department}, upload_status = #{thesisBasicInfo.uploadStatus} " +
            " WHERE zzxh = #{thesisBasicInfo.zzxh}" )
    int saveThesisBasicInfoTable(@Param("thesisBasicInfo") ThesisBasicInfo thesisBasicInfo);

    @Select(" SELECT apply_status FROM thesis_basic_info WHERE zzxh = #{zzxh}")
    String queryThesisApplyStatus(@Param("zzxh") String zzxh);

    @Update(" UPDATE thesis_basic_info SET apply_status = #{applyStatus} WHERE zzxh = #{zzxh} ")
    int updateThesisApplyStatus(@Param("applyStatus") String applyStatus, @Param("zzxh") String zzxh);

    @Delete(" DELETE FROM thesis_basic_info WHERE zzxh = #{zzxh} ")
    int deleteThesisBasicInfoRecord(@Param("zzxh") String zzxh);

    @Update( "update thesis_basic_info set upload_status = #{uploadStatus} where zzxh = #{zzxh}")
    int updateUploadStatus(@Param("uploadStatus") int uploadStatus,
                           @Param("zzxh") String zzxh);

    /**
     * 硕士 推荐表
     * @param userId
     * @return
     */
    @Select( " select count(*) from master_thesis_apply where zzxh = #{userId} ")
    int hasMasterUser(@Param("userId") String userId);

    @Insert(" INSERT INTO master_thesis_apply(zzxh) VALUES (#{userId}) ")
    int initMasterTjb(@Param("userId") String userId);

    @Select(" select part1, part2, part3, part4 from master_thesis_apply where zzxh = #{userId}")
    MasterThesisApplyPartStatus getMasterThesisApplyPartStatus(@Param("userId") String userId);

    @Select(" SELECT * FROM master_thesis_apply WHERE zzxh = #{userId} ")
    MasterThesisApply getMasterTjb(@Param("userId") String userId);

    @Update(" UPDATE master_thesis_apply SET " +
            " zzxh = #{model.zzxh}, zzxm = #{model.zzxm}, xb = #{model.xb}, csny = #{model.csny}, mz = #{model.mz}, " +
            " lwtm = #{model.lwtm}, lwywtm = #{model.lwywtm}, rxny = #{model.rxny}, dbrq = #{model.dbrq}, hdxwrq = #{model.hdxwrq}, " +
            " yjxkdm = #{model.yjxkdm}, yjxkmc = #{model.yjxkmc}, ejxkdm = #{model.ejxkdm}, ejxkmc = #{model.ejxkmc}, lwsjdyjfx = #{model.lwsjdyjfx}, " +
            " part1 = 1 " +
            " WHERE zzxh = #{model.zzxh} ")
    int saveMasterTjb1(@Param("model") MasterThesisApply model);

    @Update(" UPDATE master_thesis_apply SET " +
            " dyzz = #{model.dyzz}, dezz = #{model.dezz}, sci = #{model.sci}, ei = #{model.ei}, ssci = #{model.ssci}, " +
            " istp = #{model.istp}, zls = #{model.zls}, cgjx = #{model.cgjx}, " +
            " part2 = 1 " +
            " WHERE zzxh = #{model.zzxh} ")
    int saveMasterTjb2(@Param("model") MasterThesisApply model);

    @Update(" UPDATE master_thesis_apply SET " +
            " gdxwfs = #{model.gdxwfs}, bkjdxx = #{model.bkjdxx}, gdssxwdw = #{model.gdssxwdw}, zzdw = #{model.zzdw}, zzdz = #{model.zzdz}, " +
            " zzyb = #{model.zzyb}, zzdh = #{model.zzdh}, zc = #{model.zc}, zw = #{model.zw}, zdjsxm = #{model.zdjsxm}, " +
            " zdjsyjfx = #{model.zdjsyjfx}, " +
            " part3 = 1 " +
            " WHERE zzxh = #{model.zzxh} ")
    int saveMasterTjb3(@Param("model") MasterThesisApply model);

    @Update(" UPDATE master_thesis_apply SET " +
            " fbxslw = #{model.fbxslw}, cbzz = #{model.cbzz}, hjxm = #{model.hjxm}, lwdzycxd = #{model.lwdzycxd}, dwtjyy = #{model.dwtjyy}, " +
            " tbrq = #{model.tbrq}, " +
            " part4 = 1 " +
            " WHERE zzxh = #{model.zzxh} ")
    int saveMasterTjb4(@Param("model") MasterThesisApply model);

    @Delete(" DELETE FROM master_thesis_apply WHERE zzxh = #{zzxh} ")
    int deleteMasterTjbRecord(@Param("zzxh") String zzxh);

    /**
     * 博士推荐表
     * @param userId
     * @return
     */
    @Select( "select count(*) from doctor_thesis_apply where zzxh = #{userId} ")
    int hasDoctorUser(@Param("userId") String userId);

    @Insert(" INSERT INTO doctor_thesis_apply(zzxh) VALUES (#{userId}) ")
    int initDoctorThesisApply(@Param("userId") String userId);

    @Select(" select part1, part2 from doctor_thesis_apply where zzxh = #{userId}")
    DoctorThesisApplyPartStatus getDoctorThesisApplyPartStatus(@Param("userId") String userId);

    @Select(" SELECT * FROM doctor_thesis_apply WHERE zzxh = #{userId} ")
    DoctorThesisApply getDoctorTjb(@Param("userId") String userId);

    @Update(" UPDATE doctor_thesis_apply SET " +
            " lwtm = #{model.lwtm}, lwywtm = #{model.lwywtm}, zzxm = #{model.zzxm}, dbrq = #{model.dbrq}, hdxwrq = #{model.hdxwrq}, " +
            " lwsjdyjfx = #{model.lwsjdyjfx}, yjxkdm = #{model.yjxkdm}, yjxkmc = #{model.yjxkmc}, ejxkdm = #{model.ejxkdm}, ejxkmc = #{model.ejxkmc}, " +
            " zdjsxm = #{model.zdjsxm}, zdjsyjfx = #{model.zdjsyjfx} " +
            " WHERE zzxh = #{model.zzxh} ")
    int saveDoctorTjb1(@Param("model") DoctorThesisApply model);

    @Update(" UPDATE doctor_thesis_apply SET " +
            " fbxslw = #{model.fbxslw}, cbzz = #{model.cbzz}, hjxm = #{model.hjxm}, lwdzycxd = #{model.lwdzycxd}, dwtjyy = #{model.dwtjyy}, " +
            " tbrq = #{model.tbrq} " +
            " WHERE zzxh = #{model.zzxh} ")
    int saveDoctorTjb2(@Param("model") DoctorThesisApply model);

    @Delete(" DELETE FROM doctor_thesis_apply WHERE zzxh = #{zzxh} ")
    int deleteDoctorTjbRecord(@Param("zzxh") String zzxh);

}
