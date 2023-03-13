package com.dingdangmaoup.websocket.decode;

import com.dingdangmaoup.websocket.proto.WebsocketMessagesProto;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

/**
 * 网络套接字帧解码
 *
 * @author dzhao1
 * @date 2023/03/09
 */
@Slf4j
public class WebSocketFrameDecode extends MessageToMessageDecoder<WebSocketFrame> {


  @Override
  protected void decode(ChannelHandlerContext ctx, WebSocketFrame msg, List<Object> out)
      throws Exception {
    log.info("WebSocketFrameDecode msg :{}", msg);
    if (msg instanceof BinaryWebSocketFrame frame) {
      ByteBuf content = frame.content();
      out.add(WebsocketMessagesProto.WebsocketMessage.parseFrom(
          content.nioBuffer()));
      content.retain();
    }
  }
}
