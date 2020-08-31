package com.nullatom.cxapi;

import com.nullatom.cxapi.apis.CxAPI;
import com.nullatom.cxapi.handler.NSHandler;
import com.nullatom.httpserver.NAServer;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        if(args.length != 1){
            throw new RuntimeException("未检测到有效参数，本程序仅接受一个 端口 参数，请检查后重试！");
        }
        NAServer ns = new NAServer(Integer.valueOf(args[0]));
        NSHandler handler = new NSHandler();
        ns.setHandler(handler);

        new Thread(()->{

            ns.start();

        }).start();//开启线程运行NA服务器
        Scanner s = new Scanner(System.in);
        String cmd = "";
        System.out.print(">: ");
        while(true){
            cmd = s.next();
            switch (cmd){
                case "help":
                    System.out.println("------HELP------");
                    System.out.println("help - 查看帮助");
                    System.out.println("refresh - 刷新词库");
                    System.out.println("info - 服务器信息");
                    System.out.println("stop - 停止服务器");
                    System.out.println("----------------");
                    System.out.print(">: ");
                    break;
                case "refresh":
                    CxAPI.refreshCiku();//刷新词库
                    System.out.println("词库刷新成功！");
                    System.out.print(">: ");
                    break;
                case "info":
                    System.out.println("运行端口："+ns.getPort());
                    System.out.println("运行状态："+ns.getStatus());
                    System.out.print(">: ");
                    break;
                case "stop":
                    ns.stop();
                    System.exit(0);
                    break;
                default:
                    System.out.println("未知的命令，请检查命令是否正确或者输入help查看帮助");
                    System.out.print(">: ");
                    break;

            }
        }
    }
}
