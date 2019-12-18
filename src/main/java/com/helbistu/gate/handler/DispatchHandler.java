package com.helbistu.gate.handler;

import com.helbistu.gate.Packet;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DispatchHandler extends ChannelInboundHandlerAdapter {

    private static Logger logger = LoggerFactory.getLogger(DispatchHandler.class);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        if (msg instanceof Packet) {
            Packet packet = (Packet) msg;

            logger.debug("ptype= {}",packet.getPtype());
            logger.debug("pdata={}", new String(packet.getPdata()));

            //TODO 这里应该把协议转发到game server
            //可以通过grpc来转发
        }
    }
}
