package com.zzp.cloud.provider.api;

import com.zzp.cloud.common.dto.emp.HrDto;
import com.zzp.cloud.provider.domain.Hr;

import java.util.List;

/**
 * hr模块服务
 * <p>
 *  //TODO
 *  HrService.java
 * </p>
 * @version v0.0.1
 * @author 佐斯特勒
 * @date 2020/8/1 18:47
 * @see  com.zzp.cloud.provider.api
 **/
public interface HrService {
    /**
     * 加载hr通过userName
     * @param userName .
     * @return .
     */
    Hr loadByUserName(String userName);

    /**
     * 更新hr信息
     * @param hr orm的hr
     * @return row
     */
    Integer updateHr(Hr hr);

    /**
     * 更新hr密码
     * @param oldPass 旧密码
     * @param pass 新密码
     * @param hrId hr的id
     * @return ok不
     */
    boolean updateHrPasswd(String oldPass, String pass, Integer hrId);

    /**
     * 更新头像
     * @param url url地址
     * @param id hrid
     * @return row
     */
    Integer updateUserFace(String url, Integer id);

    /**
     * 查询带权限的所有hr
     * @param hrId .
     * @param keywords .
     * @return .
     */
    List<HrDto> findAllHrs(Integer hrId,String keywords);

    /**
     * 更新hr的权限
     * @param hrid .
     * @param rids .
     * @return .
     */
    boolean updateHrRole(Integer hrid, Integer[] rids);

    /**
     * 删除hr
     * @param id .
     * @return .
     */
    int deleteHrById(Integer id);

    /**
     * 获取所有除了当前的hr
     * @param id .
     * @return .
     */
    List<Hr> getAllHrsExceptCurrentHr(Integer id);

}
