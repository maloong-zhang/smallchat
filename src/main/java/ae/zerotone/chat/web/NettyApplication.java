package ae.zerotone.chat.web;

import ae.zerotone.chat.server.NettyServer;
import io.netty.channel.ChannelFuture;
import java.net.InetSocketAddress;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("ae.zerotone.chat")
public class NettyApplication implements CommandLineRunner {

  @Value("${netty.host}")
  private String host;

  @Value("${netty.port}")
  private int port;

  @Resource
  private NettyServer nettyServer;

  public static void main(String[] args) {
    SpringApplication.run(NettyApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    InetSocketAddress address = new InetSocketAddress(host, port);
    ChannelFuture channelFuture = nettyServer.bing(address);
    Runtime.getRuntime().addShutdownHook(new Thread(() -> nettyServer.destroy()));
    channelFuture.channel().closeFuture().syncUninterruptibly();
  }
}
