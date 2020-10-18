## 安装
[👉下载jar包](https://github.com/eisuto/Rained/releases/download/v0.0.1/Rained-v0.0.1-alpha.jar)

- idea导入
```Text
 File -> Project Structure -> Modules -> Dependencies -> ➕ -> JARs or directories -> 选择下载的jar包
```
- eclipse导入
```Text
 将jar包添加至项目中，包上右键选择 Build Path -> Add to Build Path
```

## 快速使用
使用 [RainedRequest](#) 您将开启一段梦幻的请求体验

构造一个 https://bangumi.tv/ 的 **GET** 请求 
```java
RainRequest request = new RainRequest();
request.get("https://bangumi.tv/")
```

构造一个 https://bangumi.tv/FollowTheRabbit/ 的 **POST** 请求
```java
HashMap<String, String> body = new HashMap<>();
body.put("user","username");
body.put("pass","password");

RainRequest request = new RainRequest();
request.post("https://bangumi.tv/FollowTheRabbit/",body); 
```
## API文档