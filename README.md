# SpringBoot构建电商秒杀项目
**项目简介：** 使用SpringBoot快速搭建前后端分离的电商基础秒杀项目。
项目中会通过应用领域驱动型的分层模型设计方式去完成用户otp注册、登陆、查看、商品列表、进入商品详情以及倒计时秒杀开始后下单购买的基本流程。   

**课程地址：**[SpringBoot构建电商基础秒杀项目](https://www.imooc.com/learn/1079)
## 系统结构
系统使用了MVC的架构模式，各层之间的功能区分明显，对数据库的操作使用MySQL，运行在本地主机上。实际项目中的远程服务器和分布式缓存等本项目未使用。
<div align="center">
<img src="https://github.com/WangPanHUST/Test/blob/master/%E7%BB%93%E6%9E%84%E5%9B%BE.png?raw=true"/>
</div> 

## 一些问题  
1、ItemVO和UserVO必须设置get和set方法，否则报错com.fasterxml.jackson.databind.exc.InvalidDefinitionException: No serializer found for class，
猜测是BeanUtils.copyProperties(itemModel, itemVO)，进行属性赋值时会用到get和set方法  

2、数据库字段名称确保没有空格，而且要与Mapper.xml中定义的一致  

3、在使用自增主键的表的insert方法中，在队形的XM文件加入 keyProperty="id" useGeneratedKeys="true"保证可以获取自增id。在createItem时候，ItemStockDO必须先获取tem表中的item_id  

4、Java8 lambda表达式的应用  

5、在使用DOMapper设置自动装载时，使用@Autowired(required = false)，猜测是容器的加载顺序导致需要装载时该Bean还未生成  

6、在变量名的书写上注意完整正确，使用BeanUtils.copyProperties()方法，要求两个类其中的变量名称相同且类型相同，否则需要手动操作这些变量  

7、MySQL5.6之后，设置datetime类型的默认值不能为 '00-00-00 00:00:00'，需设置为大于1000的年数  

8、相应的annotation记得加，如对应的@Service，否则可能无法自动装载    

## 联系我 
**E-mail**:wangpanhust@qq.com
