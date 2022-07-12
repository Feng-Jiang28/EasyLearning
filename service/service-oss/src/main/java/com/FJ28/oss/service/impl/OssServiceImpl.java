package com.FJ28.oss.service.impl;

import com.FJ28.oss.service.OssService;
import com.FJ28.oss.utils.ConstantPropertiesUtils;
import com.FJ28.servicebase.exceptionhandler.FJ28Exception;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.CannedAccessControlList;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;


@Service
public class OssServiceImpl implements OssService {
    @Override
    public String uploadFileAvator(MultipartFile file) {
        String endPoint = ConstantPropertiesUtils.END_POINT;
        String accessKeyId = ConstantPropertiesUtils.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtils.ACCESS_KEY_SECRET;
        String bucketName = ConstantPropertiesUtils.BUCKET_NAME;
        // String fileHost = ConstantPropertiesUtils.FILE_HOST;

        String uploadUrl = null;
        try{

            OSSClient ossClient = new OSSClient(endPoint, accessKeyId, accessKeySecret);
            if(!ossClient.doesBucketExist(bucketName)
            ){
                //创建bucket
                ossClient.createBucket(bucketName);
                //设置oss实例的访问权限：公共读
                ossClient.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
            }

            InputStream inputStream = file.getInputStream();

            String filePath = new DateTime().toString("yyyy/MM/dd");

            // fileName = uuid + extension
            String original = file.getOriginalFilename();
            String fileName = UUID.randomUUID().toString();
            String fileType = original.substring(original.lastIndexOf("."));
            String newName = fileName + fileType;
            String fileUrl = filePath + "/" + newName;

            // upload to aliyun
            ossClient.putObject(bucketName, fileUrl, inputStream);

            // close ossClient
            ossClient.shutdown();

            // 上传之后文件路径返回
            // https://...
            uploadUrl = "http://" + bucketName + "." + endPoint + "/" + fileUrl;


        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return uploadUrl;
    }


}
