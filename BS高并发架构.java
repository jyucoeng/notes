
BS架构,首先要考虑的三个问题
	1,数据存储架构
	2,开发架构
	3,.负载均衡


1,数据存储架构
	* 这东西一般是DBA需要掌握的东西
	* 架构师进行部署,DBA完成细节
	1,数据从集中走向分布
		* Oracle,一般并发在5K左右.它的性能应该算是最好的.
	


A,数据复制的方式
	* 每台数据库中的数据完全一样
	问题:
		1,数据全量复制
		2,增量同步,修改同步
	* 大多数数据库服务器都有主备机制,也就是传说中的 Master/Slaver机制,并且能够自动的实现全量复制,增量同步,主备切换
		* Slaver可以是多台,任何一台宕掉都无所谓.可以切换主备
	优点:
		1,某个服务器宕掉了,另一个服务器还可以使用.
	* 只能解决并发大的问题,如果是数据库中数据量大产生的性能瓶颈,就没辙了

B,分片的方式(垂直切分)
	* 这是目前互联网上数据分布式采用最多的方式
	* 每台数据库中的数据不一样
	* 分模块存放数据(商品,用户,订单,存放不同的服务器)
	* 按照功能模块划分数据
	DBA的活儿,刚从集中到分布式,是要人工去完成的
	问题:
		1,某个模块的数据源并发异常高,可能无法再细致的划分
		解决方案:读写分离,异构数据源之间的读写分离
		* 这种方式就涉及到了'跨数据源查询',俩数据源肯定是不能之间join.必须由一个数据源的结果,去另一个数据源查询
		  这比较影响性能,要注意SQL优化
		2,事物的问题
			* 多个模块的的数据是不同的,
			* 有分布式的事务解决方案,JTA(不提倡,性能差)
			* 不用分布式的事物,只解决单个数据源的小事务,通过程序来控制zhengti
			  这个对程序要求比较高,需要书写高质量的代码
	b,水平切分
		* 解决数据量大的问题,
		* 把一张表,变成多张表
		* 分库分表存储
		* 开始都是DBA人工操作
		* 要注意的是拆分标准
			* 根据规则来拆分表(例如,用户表,可以根据地区,姓名来进行拆分),很多公司都有这种算法
		* 这东西其实是有很多难点
			* 表拆分之后,与之相关联的表的一系列问题



			
		
