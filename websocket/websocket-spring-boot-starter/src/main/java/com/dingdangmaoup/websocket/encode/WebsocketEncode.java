package com.dingdangmaoup.websocket.encode;

import com.dingdangmaoup.websocket.proto.WebsocketMessagesProto;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import lombok.extern.slf4j.Slf4j;

/**
 * websocket编码
 *
 * @author dzhao1
 * @date 2023/03/09
 */
@Deprecated
@Slf4j
public class WebsocketEncode extends
    MessageToByteEncoder<WebsocketMessagesProto.WebsocketMessage> {


  @Override
  protected void encode(ChannelHandlerContext ctx, WebsocketMessagesProto.WebsocketMessage msg, ByteBuf out)
      throws Exception {
    log.info("MessageToByteEncoder msg ------------------------");

    byte[] bytes = msg.toByteArray();
    out.writeInt(bytes.length);
    out.writeBytes(bytes);


  }
}
