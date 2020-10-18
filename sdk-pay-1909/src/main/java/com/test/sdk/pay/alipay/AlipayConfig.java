package com.test.sdk.pay.alipay;

public class AlipayConfig {
	// 商户appid
	public static String APPID = "2016080600178939";
	// 私钥 pkcs8格式的
	public static String RSA_PRIVATE_KEY = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQDQQD3XpNpfInBE\n" +
			"FajSC8aw75X1e7tdb++omUlN6rcy7DOh/cZF+E2MSHIlYVxkDLwvNU2XcuxCEf+k" +
			"l69GXHfRSz4mATnXZJKwNWB5CMB1bq7qTcPpcCWIOdpoH0hDN6rGfMXYjQ+658vc" +
			"HSmr54tr1D7caahpU6XeDK4Wx8ErLs+RVqvOFfMpr0KfvUvjIsB42WIW+wLu5awM" +
			"avYUlW1/DEwsAi+3+KzuvdC+Ru+9GsAI49V+5JukfCQrWCRQb+j7nob5VRjwZdSH" +
			"GIRRzbY1BddX9eBarj5fzBLAV2k0R5PeLhNMXEcoVQB2JBJL1WIXUxHtWT+/pR+1" +
			"mVGqRc9TAgMBAAECggEAJURZzM1jhsLS9Fwr6erRk7hMTbHmxcHSUYmc5KSsRCCg" +
			"3MxdPwhAWGpegvH5Zq4gvxH64K9gODKgXQQICb9iccHVTVfK0hDdb0seCIrYZit4" +
			"gdWIya2YCdjQjPGMLPs5RGvvu+ECf6AbizhszKaS/Kju1uLRkIe3lxUN0mXM270i" +
			"IhBhjwj64R/Yk5mbYYrBS2YmGFRXHIi0zKthEulu1pkWYL0wd5W7mEYUz5MXg6kH" +
			"S2bQOFltPvxXl46CiHkIxSEMGEdihUl+7hry9EkoP9fTV9g6ikftSsa4mRWn6vGC" +
			"eQt+XgB804/5D8AlYtpC9/LzUe7Jh0Tj9OCWcPV5AQKBgQDqUlPy5AgrEzFDksxC" +
			"OYwWtioJSeSEFhncrGmN9QhR7CT79kaNmi6VnnL8x0S6im2KP/fbH91OnCfwMIXw" +
			"OC4rEW52aROfjGWJXD/s3Rx3ov2W1Kla88zKJxGbzGJ44aRFBZhpSDx7zDfRnFHP" +
			"Ga7a+HvOG7jpCdUizzqfZidMiQKBgQDjhHTZDtghRnRa0/dYsuJjbU45DXilmDBd" +
			"ZvyRBdOeMUo3jETcr6P0FTuGTtjXc6AMeghc4L0jScdP1areBuQdpjgSGy5hBAtg" +
			"p6lrd5I8oLL3LXrrU/ekAoHQBA91uRyN1HAh82KdK6NeWte9BMF3tIovG0WiBBQy" +
			"HzLLB3Bd+wKBgDJyCOqolu2XEysGBn1XYEZfb0tSoiShkW3FnG/YSbNBUHECAwxn" +
			"HhJN61lqGOzD8nMp9FUzVfSff6Wg/KTLo3zYItzRIE1f71gUCRsYOxGygD+hhtgy" +
			"1mrK4GqXJLf7c978JmS8DXFldLrmmWIpmFBB4H1t0HAzErSMwoUge+NpAoGAfnBN" +
			"E5epSwzBevyTWErJf2Hud8xhwP1L24xxThMD4MUZrrcYSeCeZ/Yox2IoJcwPJP/o" +
			"h90pn2PQDTD8LTl9KnEGdiF+69F4lTV39M5VibXw32b6Ca/3TsFJ7RVklhLSqItn" +
			"6aY9poTq3HP1swjij6jYpocscnZ71sL50lUHAGMCgYAstGvDw2fC1Y9AhNs6XaOi" +
			"nTlAp0MdkZJCVc465rpjqt3qssO1LumfBjiU2HuG6blolAni7xoy49zb4sCPvoNe" +
			"KFpH1aKietn4drNIOpiwR46LQoQ1Y8b25422z++syPRgnD+jBuQEvCDTha790YvC" +
			"L61MitYyv/VKc3Yh/UuZOg==";
	// 服务器异步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://xxx/callback.html";
	// 页面跳转同步通知页面路径 需http://或者https://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问 商户可以自定义同步跳转地址
	public static String return_url = "http://xxx/return.html";
	// 请求网关地址
	public static String URL = "https://openapi.alipaydev.com/gateway.do";
	// 编码
	public static String CHARSET = "UTF-8";
	// 返回格式
	public static String FORMAT = "json";
	// 支付宝公钥
	public static String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA2wjs4psQfj/OBqE2UOTwE+ZC3Y2BwZXxNOrOTUF3TqjqLpQT/V3CVUEq9p8FLe5bmccmNOmHDo28yWwKsCj1Kawtg+1zIrF0ETy7DzHg0TqxKKQkLAf7DfGn/Zs47yj4K3FN6BA04JTBM7/+5eKfDLmy6/OYAiDiC4D2Z4LnSaupzR4qsDI3Nhn7JT9VwcGHZLayRCeyyjYn/i/BJu4oj3kMR82D+jxvnmUuufX9BE3VNq0ZuOI1v/iE5jshbEcyHHBo5rer9nQ4LYAQdL1s6IMXlAGSQ6Ah7gJ21YSJ3KGQJL6xfA9FMXow2sFcBJw1L6vOW22m4q20kqGfTe18wQIDAQAB";
	// 日志记录目录
	public static String log_path = "/log";
	// RSA2
	public static String SIGNTYPE = "RSA2";
}
