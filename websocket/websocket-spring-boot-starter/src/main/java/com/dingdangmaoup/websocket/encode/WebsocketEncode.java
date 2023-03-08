package com.dingdangmaoup.websocket.encode;

import com.dingdangmaoup.websocket.proto.WebsocketMessagesProto;
import com.dingdangmaoup.websocket.proto.WebsocketMessagesProto.WebsocketResponse;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WebsocketEncode extends
    MessageToByteEncoder<WebsocketMessagesProto.WebsocketResponse> {


  @Override
  protected void encode(ChannelHandlerContext ctx, WebsocketResponse msg, ByteBuf out)
      throws Exception {
    log.info("MessageToByteEncoder msg ------------------------");

    byte[] bytes = msg.toByteArray();
    out.writeInt(bytes.length);
    out.writeBytes(bytes);


  }
}
