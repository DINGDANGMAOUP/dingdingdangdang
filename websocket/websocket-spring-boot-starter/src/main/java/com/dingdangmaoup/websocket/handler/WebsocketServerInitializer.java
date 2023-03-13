package com.dingdangmaoup.websocket.handler;

import com.dingdangmaoup.websocket.config.NettyProperties;
import com.dingdangmaoup.websocket.decode.WebSocketFrameDecoder;
import com.dingdangmaoup.websocket.encode.WebsocketMessageLiteEncoder;
import com.dingdangmaoup.websocket.proto.WebsocketMessagesProto;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.codec.http.websocketx.extensions.compression.WebSocketServerCompressionHandler;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
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
    // HTTP请求的解码和编码
    pipeline.addLast(new HttpServerCodec());
    // 把多个消息转换为一个单一的FullHttpRequest或是FullHttpResponse，
    // 原因是HTTP解码器会在每个HTTP消息中生成多个消息对象HttpRequest/HttpResponse,HttpContent,LastHttpContent
    pipeline.addLast(new HttpObjectAggregator(65536));
    // 支持大数据流写入
    pipeline.addLast(new ChunkedWriteHandler());
    // WebSocket数据压缩
    pipeline.addLast(new WebSocketServerCompressionHandler());
    // WebSocket 握手、控制帧处理
    pipeline.addLast(
        new WebSocketServerProtocolHandler(nettyProperties.getPath(), null, true));
        // 协议包解码
    pipeline.addLast(new WebSocketFrameDecoder());
    // 重写 Protobuf消息编码器 协议包编码
    pipeline.addLast(new WebsocketMessageLiteEncoder());

    //解码器，通过Google Protocol Buffers序列化框架动态的切割接收到的ByteBuf
    pipeline.addLast(new ProtobufVarint32FrameDecoder());
    //Google Protocol Buffers 长度属性编码器
    pipeline.addLast(new ProtobufVarint32LengthFieldPrepender());
    // Protobuf消息解码器
    pipeline.addLast(new ProtobufDecoder(WebsocketMessagesProto.WebsocketMessage.getDefaultInstance()));

    pipeline.addLast(new WebsocketServerHandle());
  }
}
