package com.zzp.cloud.provider.service;

import com.zzp.cloud.common.dto.emp.HrDto;
import com.zzp.cloud.provider.api.HrService;
import com.zzp.cloud.provider.domain.Hr;
import com.zzp.cloud.provider.domain.HrRole;
import com.zzp.cloud.provider.mapper.HrMapper;
import com.zzp.cloud.provider.mapper.HrRoleMapper;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.MapperUtils;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Hr 服务实现
 * <p>
 * //TODO
 * HrServiceImpl.java
 * </p>
 *
 * @author 佐斯特勒
 * @version v0.0.1
 * @date 2020/7/27 20:05
 * @see HrServiceImpl
 **/
@DubboService(version = "0.0.1", delay = -1, timeout = 6000, retries = 5)
public class HrServiceImpl implements HrService {

    @Resource
    private HrMapper hrMapper;

    @Resource
    private HrRoleMapper hrRoleMapper;

    @Resource
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Hr loadByUserName(String userName) {
        var e = MapperUtils.whereArgs(Hr.class, "username", userName);
        return hrMapper.selectOneByExample(e);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer updateHr(Hr hr) {
        return hrMapper.updateByPrimaryKeySelective(hr);
    }

    @Override
    public boolean updateHrPasswd(String oldPass, String pass, Integer hrId) {
        var hr = hrMapper.selectByPrimaryKey(hrId);
        if (bCryptPasswordEncoder.matches(oldPass, hr.getPassword())) {
            var encodePass = bCryptPasswordEncoder.encode(pass);
            var result = hrMapper.updatePasswd(hrId, encodePass);
            return result == 1;
        }
        return false;
    }

    @Override
    public Integer updateUserFace(String url, Integer id) {
        var hr = new Hr();
        hr.setUserface(url);
        hr.setId(id);
        return hrMapper.updateByPrimaryKeySelective(hr);
    }

    @Override
    public List<HrDto> findAllHrs(Integer hrId, String keywords) {
        return hrMapper.findAllHrs(hrId, keywords);
    }

    @Override
    public boolean updateHrRole(Integer hrid, Integer[] rids) {
        if (deleteHrRole(hrid)) {
            if (rids == null || rids.length == 0) {
                return true;
            }
            var hrRoleList = Stream.of(rids).map(rid -> new HrRole(hrid, rid)).collect(Collectors.toList());
            return hrRoleMapper.insertList(hrRoleList)==rids.length;
        }
        return false;
    }

    @Override
    public int deleteHrById(Integer id) {
        if(deleteHrRole(id)){
            return hrMapper.deleteByPrimaryKey(id);
        }
        return -1;
    }

    @Override
    public List<Hr> getAllHrsExceptCurrentHr(Integer id) {
        var example = new Example(Hr.class);
        MapperUtils.andEqualOrNo(example,true,"id",String.valueOf(id));
        example = example.excludeProperties("password");
        return hrMapper.selectByExample(example);
    }

    /**
     * 删除hr与role连接表
     * 即删除hr的权限
     * @param hrid .
     * @return .
     */
    private boolean deleteHrRole(Integer hrid){
        var e = MapperUtils.whereArgs(HrRole.class, "hrid", String.valueOf(hrid));
        return hrRoleMapper.deleteByExample(e) > 0;
    }
}
