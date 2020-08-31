package com.nullatom.cxapi.handler;

import com.nullatom.cxapi.apis.CxAPI;
import com.nullatom.httpserver.handler.NAServerHandler;
import com.nullatom.httpserver.utils.Request;
import com.nullatom.httpserver.utils.Response;

public class NSHandler implements NAServerHandler {
    private final String NOT_SUPPORT_GET = "此接口不支持GET方式请求";//接口不支持GET请求
    private final String NOT_SUPPORT_POST = "此接口不支持POST方式请求";//接口不支持POST请求
    private final String API_NOT_EXIST = "此接口不存在";//接口不存在
    private final String NOT_SUPPOT_METHOD = "不支持的请求方法";//请求方法不支持
    private final String PARAM_NOT_CORRECT = "请求参数格式错误，请检查后重试";//参数不正确
    @Override
    public void handle(Request request, Response response) {
        try {
            switch (request.getUrl()){

                case "cxapi"://如果是xx.xx/cxapi/?a=xx&b=xxx


                            response.setResponseType("text/json;charset=UTF-8");
                            if (request.getMethod().equals("get")) {
                                response.print(NOT_SUPPORT_GET);//如果是GET，则返回不支持
                                response.pushToBrowser(500);//500状态码


                            }else if (request.getMethod().equals("post")){
                                //如果请求cxapi是POST方式
                                if (request.getParameter("msg")==null){//如果没有msg这个参数
                                    response.print(PARAM_NOT_CORRECT);
                                    response.pushToBrowser(500);
                                    return;
                                }
                                String msg = request.getParameter("msg");

                                //如果一切正常，有msg参数

                                response.print(CxAPI.toCxEmoji(msg));//输出转换好的文字
                                response.pushToBrowser(200);
                                return;

                            }else{//如果是其他方式
                                response.print(NOT_SUPPOT_METHOD);//不支持的请求方式
                                response.pushToBrowser(500);//500状态码
                                return;
                            }
                            break;



                default:
                            response.print(API_NOT_EXIST);//接口不存在
                            response.setResponseType("text/text");
                            response.pushToBrowser(404);//500状态码
                            break;

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
