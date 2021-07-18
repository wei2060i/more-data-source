# javaWeb多数据源练习

### mybatisdynamic
  配置了两个数据源master和write.

  默认匹配第一个数据源.

  可以通过在方法上添加注解@DS(DataSource.WRITE) 选择哪一个数据源.
