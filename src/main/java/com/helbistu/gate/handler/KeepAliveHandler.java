package com.helbistu.gate.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 用于KeepAlive检测的handler，超过制定时间则把玩家踢下线
 */
public class KeepAliveHandler extends ChannelInboundHandlerAdapter {

    private static Logger logger = LoggerFactory.getLogger(KeepAliveHandler.class);

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent){
            IdleStateEvent event = (IdleStateEvent)evt;
            if (event.state()== IdleState.READER_IDLE){
                System.out.println("关闭这个不活跃通道！");
                ctx.channel().close();
            }
        }else {
            super.userEventTriggered(ctx,evt);
        }
    }
}
