package cn.haoxiaoyong.oss.starter.service;

import cn.haoxiaoyong.oss.starter.model.OssLocalBean;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.OSSObject;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.*;
import java.net.URL;
import java.util.Date;

/**
 * @author haoxiaoyong on 2020/3/23 下午 2:08
 * e-mail: hxyHelloWorld@163.com
 * github: https://github.com/haoxiaoyong1014
 * Blog: www.haoxiaoyong.cn
 */

public class OssService {

    @Autowired
    private OssLocalBean ossLocalBean;

    /**
     * @param key
     * @param file
     * @return
     */
    public String upload(String key, File file) {

        if (ossLocalBean.getEnable()) {

            OSS ossClient = null;
            try {
                ossClient = getClient();
                ossClient.putObject(ossLocalBean.getBucketName(), key, file);

                Date expiration = new Date(System.currentTimeMillis() + 3600L * 1000 * 24 * 365 * 100);
                // 生成URL
                URL url = ossClient.generatePresignedUrl(ossLocalBean.getBucketName(), key, expiration);
                return ossLocalBean.getProtocol() + url.getHost() + url.getPath();
            } finally {
                ossClient.shutdown();
            }
        }
        return null;
    }

    /**
     * 文件流上传
     *
     * @param key
     * @param inputStream
     * @return
     */
    public String upload(String key, InputStream inputStream) {
        if (ossLocalBean.getEnable()) {
            OSS ossClient = null;
            try {
                ossClient = getClient();

                ossClient.putObject(ossLocalBean.getBucketName(), key, inputStream);
                // 设置URL过期时间为100年，默认这里是int型，转换为long型即可
                Date expiration = new Date(System.currentTimeMillis() + 3600L * 1000 * 24 * 365 * 100);
                // 生成URL
                URL url = ossClient.generatePresignedUrl(ossLocalBean.getBucketName(), key, expiration);

                return ossLocalBean.getProtocol() + url.getHost() + url.getPath();

            } finally {
                ossClient.shutdown();

            }
        }
        return null;
    }

    /**
     * 文件下载
     *
     * @param key
     * @return
     * @throws IOException
     */
    public BufferedReader download(String key) throws IOException {
        if (ossLocalBean.getEnable()) {
            OSS ossClient = null;
            BufferedReader reader = null;
            try {
                ossClient = getClient();

                OSSObject ossObject = ossClient.getObject(ossLocalBean.getBucketName(), key);
                reader = new BufferedReader(new InputStreamReader(ossObject.getObjectContent()));
                while (true) {
                    String line = reader.readLine();
                    if (line == null) {
                        break;
                    }

                }
                reader.close();
                return reader;
            } finally {
                ossClient.shutdown();

            }
        }
        return null;
    }

    /**
     * 文件下载
     *
     * @param key
     * @param file
     */
    public void downLoad(String key, File file) {
        if (ossLocalBean.getEnable()) {
            OSS ossClient = null;

            try {
                ossClient = getClient();
                ossClient.getObject(new GetObjectRequest(ossLocalBean.getBucketName(), key), file);
            } finally {
                ossClient.shutdown();

            }
        }
    }

    public boolean exist(String key) {
        if (ossLocalBean.getEnable()) {

            OSS ossClient = null;

            try {
                ossClient = getClient();
                return ossClient.doesObjectExist(ossLocalBean.getBucketName(), key);
            } finally {
                ossClient.shutdown();

            }
        }
        return false;
    }

    /**
     * 文件删除
     *
     * @param key
     */
    public void delete(String key) {
        if (ossLocalBean.getEnable()) {
            OSS ossClient = null;

            try {
                ossClient = getClient();
                ossClient.deleteObject(ossLocalBean.getBucketName(), key);
            } finally {
                ossClient.shutdown();

            }
        }
    }

    /**
     * 获取 oss Client
     *
     * @return
     */
    private OSS getClient() {
        return new OSSClientBuilder().build(ossLocalBean.getEndpoint(), ossLocalBean.getAccessKeyId(), ossLocalBean.getAccessKeySecret());
    }
}
