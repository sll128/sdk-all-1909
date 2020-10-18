package com.test.sdk.pay.controller;

import com.test.sdk.pay.util.HTTPUtil;
import com.test.sdk.pay.util.QRCodeUtil;
import com.test.sdk.pay.wechat.WXPayConstants;
import com.test.sdk.pay.wechat.WXPayUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 徒有琴
 */
@Controller
public class WchatController {
    @RequestMapping("wechat.html")
    public String wechat(Integer amount, Model model) {
        if (amount != null) {
            Map<String, String> data = new HashMap<>();
            data.put("appid", WXPayConstants.APP_ID);
            data.put("mch_id", WXPayConstants.MCH_ID);
            data.put("nonce_str", System.currentTimeMillis() + "");
            data.put("body", "年货");
            data.put("out_trade_no", System.currentTimeMillis() + "");
            data.put("total_fee", amount.toString());
            data.put("spbill_create_ip", "10.9.45.238");//从request中获取
            data.put("notify_url", WXPayConstants.NOTIFY_URL);
            data.put("trade_type", "NATIVE ");
            try {
                String xml = WXPayUtil.generateSignedXml(data, WXPayConstants.APP_KEY);
                //System.out.println(xml);
                String res = HTTPUtil.httpPost(WXPayConstants.URL, xml, "utf-8");
                //insert到数据库
                Map<String, String> result = WXPayUtil.xmlToMap(res);
                model.addAttribute("code_url", result.get("code_url"));
                model.addAttribute("orderNum", data.get("out_trade_no"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "wechat";
    }

    @RequestMapping("code.html")
    public void code(HttpServletResponse response, String code) {
        if (code == null) {
            return;

        }
        try {
            BufferedImage img = QRCodeUtil.createImage(code);
            ImageIO.write(img, "JPEG", response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    String OK = "<xml><return_code><![CDATA[SUCCESS]]></return_code> <return_msg><![CDATA[OK]]></return_msg></xml>";
    String ERROR = "<xml><return_code><![CDATA[FAIL]]></return_code> <return_msg><![CDATA[FAIL]]></return_msg></xml>";

    @RequestMapping("wxcallback.html")
    public void callback(HttpServletRequest request, HttpServletResponse response) {

        String line = null;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
            StringBuilder builder = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
            String xml = builder.toString();//微信通知结果
            //System.out.println(xml);
            Map<String, String> data = WXPayUtil.xmlToMap(xml);
            if ("SUCCESS".equals(data.get("return_code"))) {//成功
                //校验签名
                boolean check = WXPayUtil.isSignatureValid(xml, WXPayConstants.APP_KEY);
                if (check) {//签名正确
                    //更新订单
                    System.out.println("订单号：" + data.get("out_trade_no"));
                    PaySocket.sendMsg(data.get("out_trade_no"), "成功");
                    System.out.println("微信订单号：" + data.get("transaction_id"));
                    System.out.println("收到金额：" + data.get("cash_fee"));
                    System.out.println("支付状态：" + data.get("result_code"));
                    response.getWriter().write(OK);
                } else {//签名错误
                    response.getWriter().write(ERROR);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            response.getWriter().flush();
            response.getWriter().close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
