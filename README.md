gate是游戏服的网关服务器，负责与客户端连接管理。

用netty框架来处理客户端的连接请求，网络包收发(nio，可以考虑针对linux采用aio,即epoll模型，提高网络性能)

PacketEncoder 是编码器

PacketDecoder 是解码器，并处理粘包和拆包

DispatchHandler 是InBoundHandler中最后一步，负责把具体的协议派发到游戏服gs

IdleStateHandler 负责keep alive检测，如果超时未收到客户端发来的请求，会触发IdleStateEvent事件

KeepAliveHandler 负责检测IdleStateEvent事件，踢掉非活跃的客户端连接

