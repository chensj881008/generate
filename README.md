# J2EE工程开发实现代码生成工具

## SSM工程代码生成器

### master 分支为常规模式生成SSM需要的domain、dao、service和sqlmap文件

#### 配置说明
    * `config.isAutoPKs`  设置为true的话，表的主键使用UUID生成
    * `config.isUseLombok`  设置为true的话，实体类生成的时候使用Lombok，项目中需要添加如下的依赖
        ```xml
            <dependency>
              <groupId>org.projectlombok</groupId>
              <artifactId>lombok</artifactId>
              <version>1.18.4</version>
            </dependency>
        ```
    * `config.isUseRedis` 设置为true的话，在Dao层使用Redis，会自动生成Redis配置文件，项目中必须添加如下的依赖和配置redis的properties
        ```xml
            <dependency>
                        <groupId>org.springframework.boot</groupId>
                        <artifactId>spring-boot-starter-data-redis</artifactId>
                        <exclusions>
                            <exclusion>
                                <groupId>redis.clients</groupId>
                                <artifactId>jedis</artifactId>
                            </exclusion>
                            <exclusion>
                                <groupId>io.lettuce</groupId>
                                <artifactId>lettuce-core</artifactId>
                            </exclusion>
                        </exclusions>
                    </dependency>
                    <!-- 添加jedis客户端 -->
                    <dependency>
                        <groupId>redis.clients</groupId>
                        <artifactId>jedis</artifactId>
                    </dependency>
                    <dependency>
                        <groupId>org.apache.commons</groupId>
                        <artifactId>commons-pool2</artifactId>
                        <version>2.5.0</version>
                    </dependency>
        ```
        *application.properties*
        ```properties
            # redis配置
            # Redis数据库索引（默认为0）
            spring.redis.database=0
            ## Redis服务器地址
            spring.redis.host=127.0.0.1
            ## Redis服务器连接端口
            spring.redis.port=6379
            ## Redis服务器连接密码（默认为空）
            spring.redis.password=123456
            ## 连接池最大连接数（使用负值表示没有限制）
            spring.redis.jedis.pool.max-active=-1
            ## 连接池最大阻塞等待时间（使用负值表示没有限制）
            spring.redis.jedis.pool.max-wait=-1
            ## 连接池中的最大空闲连接
            spring.redis.jedis.pool.max-idle=50
            ## 连接池中的最小空闲连接
            spring.redis.jedis.pool.min-idle=10
            ## 连接超时时间（毫秒）
            spring.redis.timeout=10000
        ```
### lombok 分支为生成内容与上述一样，只是domain没有get/set方法

增加新的依赖
```xml
    <dependency>
      <groupId>org.projectlombok</groupId>
      <artifactId>lombok</artifactId>
      <version>1.18.4</version>
    </dependency>
```
### redis 分支为增加Redis的使用，与主分支的区别在于生成DAO层与增加Redis配置

增加新的依赖
```xml
        <!-- Spring Boot Redis依赖 -->
        <!-- 注意：1.5版本的依赖和2.0的依赖不一样，注意看哦 1.5我记得名字里面应该没有“data”, 2.0必须是“spring-boot-starter-data-redis” 这个才行-->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-redis</artifactId>
            <!-- 1.5的版本默认采用的连接池技术是jedis  2.0以上版本默认连接池是lettuce, 在这里采用jedis，所以需要排除lettuce的jar -->
            <exclusions>
                <exclusion>
                    <groupId>redis.clients</groupId>
                    <artifactId>jedis</artifactId>
                </exclusion>
                <exclusion>
                    <groupId>io.lettuce</groupId>
                    <artifactId>lettuce-core</artifactId>
                </exclusion>
            </exclusions>
        </dependency>
        <!-- 添加jedis客户端 -->
        <dependency>
            <groupId>redis.clients</groupId>
            <artifactId>jedis</artifactId>
        </dependency>
        <!--spring2.0集成redis所需common-pool2-->
        <!-- 必须加上，jedis依赖此  -->
        <!-- spring boot 2.0 的操作手册有标注 大家可以去看看 地址是：https://docs.spring.io/spring-boot/docs/2.0.3.RELEASE/reference/htmlsingle/-->
        <dependency>
            <groupId>org.apache.commons</groupId>
            <artifactId>commons-pool2</artifactId>
            <version>2.5.0</version>
        </dependency>
```

配置application.properties 
```properties
# redis配置
# Redis数据库索引（默认为0）
spring.redis.database=0
## Redis服务器地址
spring.redis.host=127.0.0.1
## Redis服务器连接端口
spring.redis.port=6379
## Redis服务器连接密码（默认为空）
spring.redis.password=123456
## 连接池最大连接数（使用负值表示没有限制）
spring.redis.jedis.pool.max-active=-1
## 连接池最大阻塞等待时间（使用负值表示没有限制）
spring.redis.jedis.pool.max-wait=-1
## 连接池中的最大空闲连接
spring.redis.jedis.pool.max-idle=50
## 连接池中的最小空闲连接
spring.redis.jedis.pool.min-idle=10
## 连接超时时间（毫秒）
spring.redis.timeout=10000
```