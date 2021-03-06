这东西吧,得这么去理解
从Session开始说!Session是有个缓存,当第一次从数据库中获取对象之后,这个对象还在Session里面缓存着
当你下次还要使用这个对象的时候,Session就从缓存中把这个对象给你！如果缓存没找到,就连接数据库进行查找!
。。那么问题来了,这个机制非常的不错！但是,Session之间的缓存不能同步!如果多个请求都在请求同一个对象的话。。每个请求虽然都有自己的Session缓存!但是每个Session都要去查一遍数据库!
我们得找个办法,让这些Session缓存的东西！再次缓存一遍!不管是谁,访问!不管是哪个Session,只要是这个数据,都不用访问数据库！

一级缓存,最大的作用就是管理对象!其实不是提高性能!(Session的那个缓存就是一级缓存)
二级缓存,就是提高效率的


集合缓存
	> 类中的成员属性是集合!也可以对集合进行缓存！集合缓存,缓存的就是id,真正的类还是要去类缓存中找
类缓存
	> 只是做了类缓存的配置,那么类中的集合其实是没有缓存的!没有没有缓存类中的集合,你在使用集合的时候,还是回去数据库中查找
-----------------------------------------结构演示
Session1	Session2	Session3...
  |		   |                |
		  缓存
		   |
		 数据库
----------------------------------------
二级缓存机制默认是没有开启的！因为不是任何程序都适用缓存,例如:财务,等一些敏感的数据!就不能被缓存!

开启二级缓存:类
1,在主配置文件(hibernate.cfg.xml)中添加如下标签
<property name="cache.region.factory_class">org.hibernate.cache.ehcache.EhCacheRegionFactory</property> 
	> 表示已经开启了缓存,但是还没指定哪些类要缓存
	> 这个更高级的缓存,有自己的一个配置文件...懒得写了!会在本地生成缓存文件!这东西吧,自己去百度吧我的天！累死了今天！
	* 这东西吧,可以换成其他的第三方缓存类!
<property name="cache.use_query_cache">true</property>
	> 这东西,表示查询!也缓存!不仅仅是get/load方法获取的对象缓存!QHL查询的出来的也会缓存
	> 这样的设置还不够,如果想要HQL查询出来的东西也缓的话需要在查询的时候使用到Query的setCacheable(true);双管齐下!才奏效
2,导入jar包
ehcache文件夹下的(自己找)
	> ehcache-core-2.4.3.jar
	> hibernate-ehcache-5.0.5.Final.jar
	> slf4j-api-1.6.1.jar
3,指定要缓存的类
	> 第一办法就是在主配置文件中指出
		> <class-cache usage="read-only" class="指定类的全路径"/>
		* usage:有几个变量可以自己选择.只读/可读...
		* 这个东西有问题,妈的,会报错！坑死爹了！先别用,用下面种
	> 第二种办法就是在'需要被缓存的类的配置文件'中写出
		> 在class标签下添加这个标签:<cache usage="read-only"/>
		* 表示要缓存,缓存的方式就是:只读
		* 测试成功, 没问题！可以用这种方式

开启二级缓存:集合
	满足上面条件后..添加下面的方法之一
	1,在主配置文件中声明
		> <collection-cache usage="read-only" collection="com.kevin.domain.Department.employees"/> 
		* coolection:要写明是哪个类?的哪个属性(集合)需要被缓存
		* 尼玛,这个也有问题!5.0.5版本会报错!
	2,在类的配置文件中声明
		

------------------------------注意
1,不管是一级缓存,还是二级缓存,都是在使用OID的方式的获取对象的时候才有效!get/load方法的就可以!
2,Query.list()默认不会使用缓存,就算你的HQL语句是:from xx where id=?也不行！

-----------------------------非要让HQL查询的数据放到缓存
Iterator<xxx> it = session.createQuert("from xxx where id > 10").iterate();
> 这个方法就会使用缓存,但是!因为这个方法是先查询所有符合条件的id集合,再一个一个的按id查找数据!就能上缓存了
但是这个方法会有N+1次的差问题,效率提高很有限!不常用!

------------------------------更为专业的查询缓存
session1.createQuery("").setCacheable(true).list();
	> setCacheable,就是是否设置缓存!
	> 那么每次使用查询都带有true,而且,每次查询的条件都是一样的！因为它保存的就是查询条件
	> 默认,这个东西是没开启的！需要主配置文件,进行配置
	> <property name="cache.use_query_cache">true</property>
	> 配置存在且值为true,同时设置setCacheable(true);查询就会被缓存！

总结---------------
集合的缓存	自己看
类的缓存	自己看
查询的缓存	自己看

注意---------------
update ---> delete   -----> 都不会通知Session缓存
	> 就是你把对象缓存到Session后,又执行了修改语句!数据库中已经改变!但是Session没有执行refresh就不会改变!
那么二级缓存呢?
	如果二级缓存中的数据,在缓存的时候,执行了update,delete等语句!那么缓存中的数据就会失效!下次使用的时候,就会重新到数据库中去获取
	那就是没影响的！


