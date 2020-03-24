package cn.haoxiaoyong.oss.starter.config;

import cn.haoxiaoyong.oss.starter.model.OssLocalBean;
import cn.haoxiaoyong.oss.starter.service.OssService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author haoxiaoyong on 2020/3/23  1:41
 * e-mail: hxyHelloWorld@163.com
 * github: https://github.com/haoxiaoyong1014
 * Blog: www.haoxiaoyong.cn
 */
@Configuration
@EnableConfigurationProperties(OssProperties.class)
@ConditionalOnClass(OssLocalBean.class)
@ConditionalOnWebApplication
public class OssStarterAutoConfiguration {


    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty(prefix = "oss.config", name = "enable", havingValue = "true")
    public OssLocalBean defaultOssLocalBean(OssProperties ossProperties) {
        OssLocalBean oss = new OssLocalBean();
        oss.setAccessKeyId(ossProperties.getAccessKeyId());
        oss.setAccessKeySecret(ossProperties.getAccessKeySecret());
        oss.setBucketName(ossProperties.getBucketName());
        oss.setEndpoint(ossProperties.getEndpoint());
        oss.setEnable(ossProperties.getEnable());
        return oss;
    }

    @Bean
    @ConditionalOnMissingBean
    public OssService ossService () {
        return new OssService();
    }
}
