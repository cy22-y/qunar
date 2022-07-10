package org.example.question5.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.example.question5.util.HttpUtil;
import org.example.question5.util.MsgUtil;

public class MsgServerSocketHandler extends SimpleChannelInboundHandler<String> {

    public MsgServerSocketHandler() {

    }

    //发送消息时候被接收
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, String msg) {
        msg = msg.startsWith("http://") ? msg : "http://" + msg;
        String response = HttpUtil.sendGet(msg);
        System.out.println(response);
        String text = MsgUtil.parseText(response);
        ctx.channel().writeAndFlush(text);
//        try {
//            CloseableHttpClient httpClients = HttpClients.createDefault();
//            //默认当作get请求处理
//            HttpGet httpGet = new HttpGet(msg);
//            CloseableHttpResponse response;
//            response = httpClients.execute(httpGet);
//            int statusCode = response.getStatusLine().getStatusCode();
//            if (statusCode != 200) {
//                throw new RuntimeException("http请求接口错误");
//            }
//
//            String entity = EntityUtils.toString(response.getEntity());
//            String result = MsgUtil.parseText(entity);
//            Channel channel = ctx.channel();
//            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
//            channel.writeAndFlush(dateFormat.format(new Date()) + " 服务端：" + result);
//        } catch (Exception e) {
//            System.out.println("请求url" + msg + "," + e.getMessage());
//        }
    }





}
