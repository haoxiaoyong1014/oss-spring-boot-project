### Aliyun Oss Spring Boot Project

#### Released version

```xml
<dependencie>
    <groupId>cn.haoxiaoyong.oss</groupId>
    <artifactId>oss-spring-boot-starter</artifactId>
    <version>0.0.2-beta</version>
</dependencie>
```
#### Quick start

```yaml
oss:
  config:
    enable: true
    access-key-id: 
    access-key-secret: 
    endpoint: 
    bucket-name: 
```

```java
    @Autowired
    private OssService ossService;

    String url = ossService.upload("oss.png", new File("D:\\hcwork\\work-doc\\oss.png"));
```

#### API

* String upload(String , File);
* String upload(String , InputStream)
* BufferedReader download(String)
* boolean exist(String)
* delete(String)

#### Legacy Versions

|  Oss Spring Boot  | sdk-oss  | Spring Boot |
|  ----  | ----  |  ----    |
|~~0.0.1-beta~~| 3.8.0 |  2.1.x |
| 0.0.2-beta | 3.8.0 |  2.1.x |
