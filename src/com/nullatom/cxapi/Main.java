package com.nullatom.cxapi;

import com.nullatom.cxapi.handler.NSHandler;
import com.nullatom.httpserver.NAServer;

public class Main {
    public static void main(String[] args) {
        NAServer ns = new NAServer(50000);
        NSHandler handler = new NSHandler();
        ns.setHandler(handler);

        new Thread(()->{

            ns.start();

        }).start();//开启线程运行NA服务器
    }
}
