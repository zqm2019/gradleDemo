> mybatisGenerator 说明 自动生成mybatis 代码
分为两步：第一步替换mybatisGenerator.xml中的属性值，第二步进入（cd）mybatisGenerator.xml所在目录，执行这个文件。

> 替换 

1. mybatisGenerator.xml 中的 classPathEntry location  替换为本地 mysql-connector-java-5.1.35.jar路径

2. 替换 3个 targetProject为本地路径（targetPackage="resources.mapper"到main）

3. 替换 tableName 为需要生成对应表的表名

>  执行

mybatisGenerator.xml 所在目录执行（替换mybatis-generator-core-1.3.7.jar本地路径） 
java -jar /Users/zhaqianming/.gradle/caches/modules-2/files-2.1/org.mybatis.generator/mybatis-generator-core/1.3.7/3708fc3697489d9890f4bbf1ff4098f84b9cf97b/mybatis-generator-core-1.3.7.jar  -configfile mybatisGenerator.xml -overwrite

