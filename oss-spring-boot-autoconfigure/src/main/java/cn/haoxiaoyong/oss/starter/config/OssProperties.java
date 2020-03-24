package cn.haoxiaoyong.oss.starter.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author haoxiaoyong on 2020/3/23 下午 1:40
 * e-mail: hxyHelloWorld@163.com
 * github: https://github.com/haoxiaoyong1014
 * Blog: www.haoxiaoyong.cn
 */
@ConfigurationProperties(prefix = "oss.config")
public class OssProperties {

   private String endpoint;

   private String accessKeyId;

   private String accessKeySecret;

   private String bucketName;

   private Boolean enable;


    public Boolean getEnable() {
        return enable;
    }

    public void setEnable(Boolean enable) {
        this.enable = enable;
    }


    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    @Override
    public String toString() {
        return "OssProperties{" +
                "endpoint='" + endpoint + '\'' +
                ", accessKeyId='" + accessKeyId + '\'' +
                ", accessKeySecret='" + accessKeySecret + '\'' +
                ", bucketName='" + bucketName + '\'' +
                ", enable=" + enable +
                '}';
    }
}
