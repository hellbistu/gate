package com.helbistu.gate;

import com.helbistu.gate.config.ConfigManager;
import com.helbistu.gate.config.GateConfig;
import com.helbistu.gate.handler.DispatchHandler;
import com.helbistu.gate.handler.KeepAliveHandler;
import com.helbistu.gate.handler.PacketDecoder;
import com.helbistu.gate.handler.PacketEncoder;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.IdleStateHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 服务启动类
 */
public class Bootstrap {

    private static Logger logger = LoggerFactory.getLogger(Bootstrap.class);

    public static void main(String[] args) {
        logger.info("gate start");

        ConfigManager.getInstance().load();
        final GateConfig config = ConfigManager.getInstance().getCfg();

        ServerBootstrap bootstrap = new ServerBootstrap();

        int availProcessors = Runtime.getRuntime().availableProcessors();
        logger.info("gate config work thread num={}", availProcessors);

        EventLoopGroup bossGroup = new NioEventLoopGroup(1);
        EventLoopGroup workGroup = new NioEventLoopGroup(availProcessors);

        bootstrap.group(bossGroup,workGroup)
                .channel(NioServerSocketChannel.class)
                .childOption(ChannelOption.SO_BACKLOG,config.getBackLog()) //建立连接的队列
                .childOption(ChannelOption.SO_SNDBUF,config.getSndBuff()) //snd buff
                .childOption(ChannelOption.SO_RCVBUF,config.getRcvBuff()) //receive buff
                .childOption(ChannelOption.TCP_NODELAY,config.isTcpNodelay()) //禁用 nagle算法
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    protected void initChannel(NioSocketChannel ch) {
                        if (config.isOpenKeepAlive()) {
                            ch.pipeline().addLast(new IdleStateHandler(config.getKeepAliveSec(),0,0));
                            ch.pipeline().addLast(new KeepAliveHandler());
                        }
                        ch.pipeline().addLast(new PacketDecoder());
                        ch.pipeline().addLast(new DispatchHandler());
                        ch.pipeline().addLast(new PacketEncoder());
                    }
                })
                .bind(config.getPublicPort());
    }
}
