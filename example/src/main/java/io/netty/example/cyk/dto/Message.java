package io.netty.example.cyk.dto;

import io.netty.buffer.ByteBuf;
import lombok.Data;

import java.nio.charset.Charset;

@Data
public class Message {
    private Long callId;
    private String content;

    public void fromBytebuf(ByteBuf msg){
        long callId = msg.readLong();
        byte[] bytes = new byte[msg.readableBytes()];
        msg.readBytes(bytes);
        String content = new String(bytes,Charset.defaultCharset());
        this.setCallId(callId);
        this.setContent(content);
    }

    public void toBytebuf(ByteBuf msg){
        msg.writeLong(callId);
        msg.writeBytes(content.getBytes(Charset.defaultCharset()));
    }
}
