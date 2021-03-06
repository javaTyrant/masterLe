package netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author lufengxiang
 * @since 2021/9/24
 **/
public class SampleInBoundHandler1 extends ChannelInboundHandlerAdapter {
    private final String name;
    private final boolean flush;

    public SampleInBoundHandler1(String name, boolean flush) {
        this.name = name;
        this.flush = flush;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        System.out.println("InBoundHandler: " + name);

        if (flush) {
            ctx.channel().writeAndFlush(msg);
        } else {
            throw new RuntimeException("InBoundHandler: " + name);
        }
    }

    @Override

    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {

        System.out.println("InBoundHandlerException: " + name);

        ctx.fireExceptionCaught(cause);

    }
}
