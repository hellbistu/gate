package com.helbistu.gate.handler;

import com.helbistu.gate.Packet;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class PacketEncoder extends MessageToByteEncoder<Packet> {

    protected void encode(ChannelHandlerContext channelHandlerContext, Packet packet, ByteBuf out) {
        out.writeShortLE(packet.getPtype());
        out.writeIntLE(packet.getLength());
        out.writeBytes(packet.getPdata());
    }
}
