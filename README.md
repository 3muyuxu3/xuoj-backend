# xuoj-backend

基于 Spring Boot 的在线判题系统（Online Judge）后端，提供用户管理、题目管理、代码提交与判题、帖子社区、文件上传、微信公众号接入等能力。

判题部分通过代码沙箱执行用户提交的代码，并配合判题策略对沙箱输出与预期结果进行比较，得出最终判题结果。

## 技术栈

| 技术 | 说明 |
| --- | --- |
| Java 8 | 开发语言 |
| Spring Boot 2.7.2 | 基础框架 |
| MyBatis-Plus 3.5.2 | ORM 框架 |
| MySQL | 数据库 |
| Redis / Spring Session | 分布式 Session（可选，默认关闭） |
| Elasticsearch | 帖子搜索（可选） |
| Knife4j 3.0.3 | 接口文档 |
| 腾讯云 COS | 对象存储（文件上传） |
| wx-java-mp 4.4.0 | 微信公众号 / 开放平台 |
| Hutool / Gson / EasyExcel | 工具库 |
| FreeMarker | 代码生成模板 |

## 功能模块

- 用户模块：注册、登录（账号密码 / 微信开放平台）、注销、用户管理与分页查询、权限控制（user / admin / ban）
- 题目模块：题目的增删改查、分页查询、题目提交与提交记录查询
- 判题模块：代码沙箱执行、判题策略、判题状态与判题信息管理
- 帖子模块：帖子增删改查、点赞、收藏、基于 Elasticsearch 的帖子搜索与同步
- 文件模块：通用文件上传（支持业务类型区分，如用户头像）
- 微信模块：微信公众号消息接收与处理

## 判题流程

1. 用户提交代码，写入 `question_submit` 表，状态为“待判题”
2. 判题服务读取提交记录与题目信息，将状态更新为“判题中”，防止重复执行
3. 通过代码沙箱执行代码（携带题目测试用例输入）
4. 判题策略将沙箱输出与预期输出进行比较，生成判题信息
5. 更新提交记录的状态与判题信息

### 判题状态

| 值 | 含义 |
| --- | --- |
| 0 | 待判题 |
| 1 | 判题中 |
| 2 | 成功 |
| 3 | 失败 |

### 判题信息

Accepted（成功）、Wrong Answer（答案错误）、Compile Error（编译错误）、Memory Limit Exceeded（内存溢出）、Time Limit Exceeded（超时）、Runtime Error（运行错误）、System Error（系统错误）。

### 代码沙箱类型

通过配置项 `codesandbox.type` 指定，取值如下：

| 类型 | 说明 |
| --- | --- |
| example | 示例沙箱，仅供本地联调参考 |
| remote | 远程代码沙箱，调用独立部署的代码沙箱服务（默认） |
| thirdParty | 第三方代码沙箱（预留） |

## 目录结构

```
xuoj-backend
├── sql                             数据库脚本
│   ├── create_table.sql            建库建表脚本
│   └── post_es_mapping.json        帖子 ES 索引映射
├── src/main/java/com/xu/xuoj
│   ├── annotation                  自定义注解（@AuthCheck）
│   ├── aop                         权限校验与请求日志拦截器
│   ├── common                      通用返回体、错误码、分页请求
│   ├── config                      配置类（跨域、COS、MyBatis-Plus、JSON、微信开放平台等）
│   ├── constant                    常量定义
│   ├── controller                  接口层
│   ├── esdao                       Elasticsearch 数据访问
│   ├── exception                   业务异常与全局异常处理
│   ├── generate                    代码生成器
│   ├── job                         定时任务（帖子全量 / 增量同步至 ES）
│   ├── judge                       判题服务（沙箱、判题策略、判题管理器）
│   ├── manager                     通用业务（COS 文件管理）
│   ├── mapper                      MyBatis-Plus Mapper
│   ├── model                       实体、DTO、VO、枚举
│   ├── service                     业务层
│   ├── utils                       工具类
│   └── wxmp                        微信公众号消息处理
├── src/main/resources
│   ├── mapper                      MyBatis XML
│   └── templates                   代码生成模板
└── src/test                        单元测试
```

## 环境要求

- JDK 1.8
- Maven 3.8 及以上
- MySQL 5.7 及以上
- Redis（可选，用于分布式 Session）
- Elasticsearch（可选，用于帖子搜索）
- 独立部署的代码沙箱服务（使用 remote 沙箱类型时）

## 快速开始

### 1. 初始化数据库

在 MySQL 中执行 `sql/create_table.sql`，创建 `xuoj` 库与数据表。

### 2. 修改配置

编辑 `src/main/resources/application.yml`，至少替换数据库连接配置（`spring.datasource`）。文件中以 `todo` 标注的配置项均需按实际环境替换或按需启用：

| 配置项 | 说明 |
| --- | --- |
| `spring.datasource` | MySQL 连接信息（必须替换） |
| `spring.redis` | Redis 连接信息（启用 Redis 时取消注释） |
| `spring.session.store-type` | 分布式 Session（启用 Redis 后取消注释，并移除启动类中对 RedisAutoConfiguration 的排除） |
| `spring.elasticsearch` | Elasticsearch 连接信息（启用搜索时取消注释） |
| `wx.mp` / `wx.open` | 微信公众号 / 开放平台配置 |
| `cos.client` | 腾讯云对象存储配置 |
| `codesandbox.type` | 代码沙箱类型 |

默认服务端口为 `8121`（开发环境），接口统一前缀为 `/api`；生产环境配置（`application-prod.yml`）端口为 `8101`，需同样替换其中的数据库等配置。

### 3. 启动服务

运行 `com.xu.xuoj.MainApplication`，或使用 Maven 启动：

```bash
mvn spring-boot:run
```

### 4. 访问接口文档

启动成功后访问 Knife4j 接口文档：

```
http://localhost:8121/api/doc.html
```

### 5. 管理员账号

用户表 `user` 的 `user_role` 字段控制角色，取值 `user` / `admin` / `ban`。如需管理员权限，可先注册账号，再将对应记录的 `user_role` 更新为 `admin`。

## 接口概览

所有接口以 `/api` 为前缀，统一返回 `BaseResponse` 结构（`code` / `data` / `message`，成功时 `code` 为 0）。

| 模块 | 主要接口 |
| --- | --- |
| 用户 | `/user/register`、`/user/login`、`/user/logout`、`/user/get/login`、`/user/get`、`/user/get/vo`、`/user/add`、`/user/update`、`/user/update/my`、`/user/delete`、`/user/list/page`、`/user/list/page/vo`、`/user/login/wx_open` |
| 题目 | `/question/add`、`/question/update`、`/question/edit`、`/question/delete`、`/question/get`、`/question/get/vo`、`/question/list/page`、`/question/list/page/vo`、`/question/my/list/page/vo` |
| 题目提交与判题 | `/question/question_submit/do`、`/question/question_submit/list/page` |
| 帖子 | `/post/add`、`/post/update`、`/post/edit`、`/post/delete`、`/post/get/vo`、`/post/list/page`、`/post/list/page/vo`、`/post/my/list/page/vo`、`/post/search/page/vo` |
| 帖子点赞 | `/post_thumb/` |
| 帖子收藏 | `/post_favour/`、`/post_favour/my/list/page`、`/post_favour/list/page` |
| 文件 | `/file/upload` |
| 微信公众号 | 消息接收与事件处理（见 `WxMpController`） |

完整的接口参数与返回值以 Knife4j 接口文档为准。

## 打包与部署

### 本地打包

```bash
mvn clean package -DskipTests
java -jar target/xuoj-backend-0.0.1-SNAPSHOT.jar --spring.profiles.active=prod
```

### Docker 部署

项目根目录提供 `Dockerfile`（基于 Maven 镜像构建、以 prod 配置运行）：

```bash
docker build -t xuoj-backend .
docker run -p 8101:8101 xuoj-backend
```

部署前需确保 `application-prod.yml` 中的数据库等配置已替换为实际环境的值，且容器内可访问对应服务。

## 测试

```bash
mvn test
```

部分测试（远程代码沙箱、COS、ES 等）依赖外部服务，运行前需先完成对应配置。

## 注意事项

- 配置文件中以 `todo` 标注的项均为占位配置，部署前必须替换。
- 默认未启用 Redis：如需 Redis，除取消配置注释外，还需移除 `MainApplication` 中对 `RedisAutoConfiguration` 的排除。
- 帖子搜索依赖 Elasticsearch：需先按 `sql/post_es_mapping.json` 创建索引，并可通过定时任务将帖子数据同步至 ES。
- 使用 remote 沙箱类型时，需先部署独立的代码沙箱服务。
- 新增数据表的 CRUD 代码可通过 `com.xu.xuoj.generate.CodeGenerator` 结合 `resources/templates` 下的模板生成。

## 代码生成器

`com.xu.xuoj.generate.CodeGenerator` 基于 FreeMarker 模板（`src/main/resources/templates`）生成 Controller、Service、ServiceImpl 及各类请求 / 视图对象，便于快速扩展新的数据表相关代码。
