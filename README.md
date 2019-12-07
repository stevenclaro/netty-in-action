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

ch12
将index.html的页面中，jquery的访问地址修改为中国能访问的地址。
首先访问这个内容http://localhost:8081/，将index.html发给客户端。可以看它如何实现将index.html发给客户端的代码


然后在Idea中，采用浏览器的方式打开这个页面。在这个页面上，有这个地址 ws://localhost:63342/ws  （location上的地址没有作用）
因为程序设置的是8081，所以修改为 ws://localhost:8081/ws

如果通信信道建立之后，websocket不会拆，然后在浏览器上，在发送信息的时候，就看不到了。

客户端建立之后，这个通道就可以一直进行发送及接受。在这个例子中，是客户端建立连接，也是客户端进行主动发送。服务端收到之后进行反馈。

那么，服务端是否可以主动发送呢？
答：感觉是可以的，当前服务器，都是在接受到channelread之后进行写回的，只要在服务器，由代码直接调用 该channel的 writeandflush
所以，channel需要保持住。


