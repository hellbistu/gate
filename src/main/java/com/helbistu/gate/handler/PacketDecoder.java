package com.helbistu.gate.handler;

import com.helbistu.gate.Packet;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class PacketDecoder extends ByteToMessageDecoder {

    private static Logger logger = LoggerFactory.getLogger(PacketDecoder.class);

    private Packet packet;

    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {
        if (packet == null) {
            packet = new Packet();
        }

        if (packet.getPtype() == -1) {
            //read packet ptype
            if (in.readableBytes() < 2) {
                return;
            }

            short ptype = in.readRetainedSlice(2).getShortLE(0);
            packet.setPtype(ptype);
        }

        if (packet.getLength() == -1) {
            //read packet pdata length
            if (in.readableBytes() < 4) {
                return;
            }

            int length = in.readRetainedSlice(4).getIntLE(0);
            packet.setLength(length);
        }

        if (in.readableBytes() < packet.getLength()) {
            return;
        }

        packet.setPdata(new byte[packet.getLength()]);
        in.readRetainedSlice(packet.getLength()).getBytes(0,packet.getPdata());

        out.add(packet);

        in.discardReadBytes();

        packet = null;
    }
}
