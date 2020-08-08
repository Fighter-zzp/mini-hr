package com.zzp.cloud.business.service;

import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.ListObjectsRequest;
import com.aliyun.oss.model.PutObjectRequest;
import com.zzp.cloud.common.dto.RespBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

/**
 * 阿里的Oss
 * <p>
 * //TODO
 * OssCloudService.java
 * </p>
 *
 * @author 佐斯特勒
 * @version v0.0.1
 * @date 2020/8/3 19:43
 * @see com.zzp.cloud.business.service
 **/
@Service
@Slf4j
public class OssCloudService {
    /**
     * 请求信息配置
     */
    private static final String ENDPOINT = "oss-cn-shenzhen.aliyuncs.com";
    private static final String ACCESS_KEY_ID = "LTAI4Fu5q5rkmfp5ChjF7FPU";
    private static final String ACCESS_KEY_SECRET = "NRDQkwDOeV3Q1airygVS0rUtyG5KlX";
    private static final String BUCKET_NAME = "zzp-test";
    private static final String DIRECTORY_NAME = "face/";

    /**
     * 文件上传类
     *
     * @param multipartFile {@code MultipartFile}
     * @return {@code String} 文件上传路径
     */
    public RespBean uploadFileOss(MultipartFile multipartFile, String oldFileName) {
        var newName = supplyNewFileName(multipartFile.getOriginalFilename());
        var client = new OSSClientBuilder().build(ENDPOINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET);
        try {
            // 如果存在则删除
            if (oldFileName != null && client.doesObjectExist(BUCKET_NAME, DIRECTORY_NAME + oldFileName)) {
                client.deleteObject(BUCKET_NAME, DIRECTORY_NAME + oldFileName);
            }
            // 上传头像
            client.putObject(new PutObjectRequest(BUCKET_NAME, DIRECTORY_NAME + newName, new ByteArrayInputStream(multipartFile.getBytes())));
            return RespBean.ok("https://" + BUCKET_NAME + "." + ENDPOINT + "/face/" + newName, newName);
        } catch (IOException e) {
            log.error("头像上传出现错误！错误信息：{}",e.getMessage());
            return RespBean.error("文件上传失败，请重试");
        } finally {
            client.shutdown();
        }
    }

    /**
     * 根据文件名产出oss文件
     *
     * @param fileName 文件名
     */
    public void deleteFileOss(String fileName) {
        var client = new OSSClientBuilder().build(ENDPOINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET);
        client.deleteObject(BUCKET_NAME, DIRECTORY_NAME + fileName);
        client.shutdown();
    }

    /**
     * 判断文件是否存在
     *
     * @param fileName .
     * @return .
     */
    public boolean existFileInOss(String fileName) {
        // 创建OSSClient实例
        var client = new OSSClientBuilder().build(ENDPOINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET);
        // Object是否存在
        var found = client.doesObjectExist(BUCKET_NAME, DIRECTORY_NAME + fileName);
        // 关闭client
        client.shutdown();
        return found;
    }

    /**
     * 把文件名修改为UUID
     *
     * @param fileName .
     * @return .
     */
    private String supplyNewFileName(String fileName) {
        return Optional.ofNullable(fileName).map(fn -> {
            var suffix = fn.substring(fileName.lastIndexOf(".") + 1);
            return UUID.randomUUID() + "." + suffix;
        }).orElseThrow(() -> new NullPointerException("文件名不能为空"));
    }

}
