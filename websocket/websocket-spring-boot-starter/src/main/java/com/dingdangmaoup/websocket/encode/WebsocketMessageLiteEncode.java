package com.dingdangmaoup.websocket.encode;

import static io.netty.buffer.Unpooled.wrappedBuffer;

import com.google.protobuf.MessageLite;
import com.google.protobuf.MessageLiteOrBuilder;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

/**
 * websocket消息lite编码 重写{@link ProtobufEncoder}编码器，将protobuf编码后的二进制流转成websocket二进制流
 * <p/>{@link io.netty.handler.codec.protobuf.ProtobufEncoder}
 *
 * @author dzhao1
 * @date 2023/03/09
 */
@Slf4j
public class WebsocketMessageLiteEncode extends MessageToMessageEncoder<MessageLiteOrBuilder> {

  @Override
  protected void encode(ChannelHandlerContext ctx, MessageLiteOrBuilder msg, List<Object> out)
      throws Exception {
    log.info(" {}", msg);
    // copy from ProtobufEncoder
    ByteBuf result = null;
    if (msg instanceof MessageLite lite) {
      result = wrappedBuffer(lite.toByteArray());
    }
    if (msg instanceof MessageLite.Builder builder) {
      result = wrappedBuffer(builder.build().toByteArray());
    }

    // rewrite code
    assert result != null;
    WebSocketFrame frame = new BinaryWebSocketFrame(result);
    out.add(frame);
  }
}
