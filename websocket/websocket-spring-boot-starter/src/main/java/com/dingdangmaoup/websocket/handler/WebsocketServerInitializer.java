package com.dingdangmaoup.websocket.handler;

import com.dingdangmaoup.websocket.config.NettyProperties;
import com.dingdangmaoup.websocket.decode.WebsocketDecode;
import com.dingdangmaoup.websocket.encode.WebsocketEncode;
import com.dingdangmaoup.websocket.proto.WebsocketMessagesProto.WebsocketRequest;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.codec.http.websocketx.extensions.compression.WebSocketServerCompressionHandler;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.stream.ChunkedWriteHandler;

public class WebsocketServerInitializer extends ChannelInitializer<SocketChannel> {

  private final NettyProperties nettyProperties;


  public WebsocketServerInitializer(NettyProperties nettyProperties) {
    this.nettyProperties = nettyProperties;
  }

  @Override
  protected void initChannel(SocketChannel ch) {
    ChannelPipeline pipeline = ch.pipeline();
//    pipeline.addLast(new HttpServerCodec());
//    pipeline.addLast(new HttpObjectAggregator(65536));
    // 支持大数据流写入
    pipeline.addLast(new ChunkedWriteHandler());
    pipeline.addLast(new WebSocketServerCompressionHandler());
    // WebSocket 握手、控制帧处理
//    pipeline.addLast(new WebSocketServerProtocolHandler(nettyProperties.getPath()));
    //解码器，通过Google Protocol Buffers序列化框架动态的切割接收到的ByteBuf
    pipeline.addLast(new ProtobufVarint32FrameDecoder());
    //Google Protocol Buffers 长度属性编码器
    pipeline.addLast(new ProtobufVarint32LengthFieldPrepender());
    // Protobuf消息解码器
    pipeline.addLast(new ProtobufDecoder(WebsocketRequest.getDefaultInstance()));
    // Protobuf消息编码器
//    pipeline.addLast(new ProtobufEncoder());
    //自定义解码器
    pipeline.addLast(new WebsocketDecode());
    //自定义编码器
    pipeline.addLast(new WebsocketEncode());
    pipeline.addLast(new WebsocketServerHandle());
  }
}
