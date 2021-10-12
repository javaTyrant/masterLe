package netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author lufengxiang
 * @since 2021/9/24
 **/
public class SampleInBoundHandler extends ChannelInboundHandlerAdapter {
    private final String name;
    private final boolean flush;

    public SampleInBoundHandler(String name, boolean flush) {
        this.name = name;
        this.flush = flush;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("InBoundHandler: " + name);
        if (flush) {
            ctx.channel().writeAndFlush(msg);
        } else {
            //这个才会让链走下去.
            super.channelRead(ctx, msg);
        }
    }

    @Override

    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {

        System.out.println("InBoundHandlerException: " + name);

        ctx.fireExceptionCaught(cause);

    }
}