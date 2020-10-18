import com.test.sdk.pay.util.HTTPUtil;
import com.test.sdk.pay.wechat.WXPayUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 徒有琴
 */
public class WechatTest {
    public static final String APP_KEY = "sbNCm1JnevqI36LrEaxFwcaT0hkGxFnC";
    public static final String URL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
    public static void main(String[] args) {
        Map<String, String> data = new HashMap<>();
        data.put("appid", "wx632c8f211f8122c6");
        data.put("mch_id", "1497984412");
        data.put("nonce_str", System.currentTimeMillis() + "");
        data.put("body", "年货");
        data.put("out_trade_no", System.currentTimeMillis() + "");
        data.put("total_fee", "1");
        data.put("spbill_create_ip", "10.9.45.238");
        data.put("notify_url", "http://2361k301i0.iok.la:18121/wxcallback.html");
        data.put("trade_type", "NATIVE ");
        try {
            String xml = WXPayUtil.generateSignedXml(data, APP_KEY);
            System.out.println(xml);
            System.out.println(HTTPUtil.httpPost(URL, xml, "utf-8"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
