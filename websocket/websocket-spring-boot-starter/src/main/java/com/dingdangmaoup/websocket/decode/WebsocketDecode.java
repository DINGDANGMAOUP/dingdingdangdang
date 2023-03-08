package com.dingdangmaoup.websocket.decode;

import com.dingdangmaoup.websocket.proto.WebsocketMessagesProto;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class WebsocketDecode extends ByteToMessageDecoder {


  @Override
  protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
    log.info("ByteToMessageDecoder msg ------------------------");

//    int length = in.readableBytes();
//    byte[] array = new byte[length];
//    in.getBytes(in.readerIndex(), array, 0, length);
//    out.add(WebsocketMessagesProto.WebsocketRequest.parseFrom(array));

    if (in.readableBytes() < 4) {
      return;
    }
    in.markReaderIndex();
    int length = in.readInt();
    if (in.readableBytes() < length) {
      in.resetReaderIndex();
      return;
    }
    byte[] bytes = new byte[length];
    in.readBytes(bytes);
    WebsocketMessagesProto.WebsocketRequest message = WebsocketMessagesProto.WebsocketRequest.parseFrom(
        bytes);
    out.add(message);

  }
}
