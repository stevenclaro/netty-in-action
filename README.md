This Repository contains the source-code for all chapters of the book [Netty in Action](http://manning.com/maurer)
by Norman Maurer and Marvin Allen Wolfthal.

Latest version: https://github.com/normanmaurer/netty-in-action/tree/2.0-SNAPSHOT

Enjoy! Feedback and PR's welcome!


Prerequisites

	JDK 1.7.0u71 or better

	Maven 3.3.9 or better


If you want to build everything at once, from the top directory run

	mvn install


If you want to build only single projects then from the top directory first run

	mvn install -pl utils


This will make the utils jar available to all the projects.

2019年10月27日

在第二章中，增加了active 等动作。
active如何要第二个顺序要动作，需要在第一个中采用fire的动作来执行
经过测试发现，如果inbound里面的handle中
如果有public void channelActive(ChannelHandlerContext ctx) {
那么就按照顺序来执行（如能按照顺序，需要在每个执行完成之后，进行fire，然后才能进入下一个）
那么如果该handle中有这个方法，但是是空。那么后面就不会执行。
如果该handle中，没有override这个方法，那么就会按照 编排的顺序来进行执行后面的
