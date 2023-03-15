package com.dingdangmaoup.websocket.handler;

import com.dingdangmaoup.websocket.proto.WebsocketMessagesProto.WebsocketMessage;
import com.dingdangmaoup.websocket.proto.WebsocketMessagesProto.WebsocketMessage.MessageBody;
import com.dingdangmaoup.websocket.proto.WebsocketMessagesProto.WebsocketMessage.MessageBody.OperationType;
import com.dingdangmaoup.websocket.proto.WebsocketMessagesProto.WebsocketMessage.MessageFlag;
import com.google.protobuf.ByteString;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Sharable
public class HeartbeatHandler extends ChannelInboundHandlerAdapter {

  @Override
  public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
    if (evt instanceof IdleStateEvent event) {
      log.info("心跳检测");
      if (event.state() == IdleState.READER_IDLE) {
        MessageBody body = MessageBody.newBuilder().setOperationType(
            OperationType.HEARTBEAT).setIdentify(12321).setContext(
            ByteString.copyFromUtf8("heart beat")).build();
        // 发送心跳包
        WebsocketMessage message = WebsocketMessage.newBuilder().setFlag(MessageFlag.SERVER)
            .setTimestamp(System.currentTimeMillis())
            .setBody(body).build();
        ctx.writeAndFlush(message);
      } else if (event.state() == IdleState.ALL_IDLE) {
        // 超时关闭连接
        ctx.close();
      }
    }
  }


}
