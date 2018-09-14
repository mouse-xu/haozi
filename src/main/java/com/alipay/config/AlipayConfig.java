package com.alipay.config;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2016091700529848";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCYwZgiYI85brOZCBvCVLB9arM6q8Y9kEpzpBnZwmIhAlXJUTmfLRRxR8Ou68UNvi7/0mUG8eYs4kPZ5isHc5PsQY9+sPuguPyneT/P64pVqUfj4siGcY5G1vLSSeiKgcX+q5EXl/KaViTLmzlPJmn9GOAKmDExc4ksU5qnk/m20yMeFze9VVwmt3jAOMtV4Z6PTHtCySzA/4Cnx3AFIBquzmsdIm5R3YZlldttDPYEjiyFtnicaKxZ6givHdVEHoxfxtZ3lzlvvcwcc47aah8HjAeniQjMzODX0VsZ6fn3Vn2VA4fDgMNjB39igIUdaxpJlyVkYR7lGsxCaNjHdIn9AgMBAAECggEAEphulrC+frVVM6njBzMcEQhuLRRAG7p29cDiTLn3DCGmo805gIESXf/oeGDHKjcerZHFLqU/6fLZY6s7TbrqJPaOK1QMLbKXPyPperwnqOnkkH9I/eTQ9xqvkJ3iqwnFOo1UKlUa+WLHM+n/ff0lJeRjUabZRjXUWwsKZqAC5Bh7ZbKR5cy2mUJgjlKvp+9B8TqdHOS6FNF0weOOu20Biq4/RxbYM9+eoLU5SxHv0MaEDYu1l6bxW9eQoS85rvy0JOlsRletxI1WmnyJrlXyKUC8SbmjJAgEiRab/t6p2Ylt5ohyoJ0xwayHlNjIQw+OQHLO5xfBiEfmV0GK0TOQgQKBgQDbFnAgTj7qNg/YBsrYmxtAnS16v0VOUzKaqDN+uOZewbADLJPAYCGO9LxhQ0f9JDukW5c91XHzpgCn3KDEtXIXQ95VfaVQ6ZyS46HxzB5TVeu2MywftYqa+9JRh4X4RrpONU2BrratTh7RzxnpCFzLBrQtjmWO1bHLq7Fq13T7zQKBgQCyfjAOoUELpMRSFe6faJmC9cLl1QY+hAUx4D/qZjmz+nlo3ARWctzv6/HdPgJ9L3D9OHDjxj2apJulNdagkAJKDxY5PAxYcLk4dRuq9ECc71XS8qomtf6MawJGjmP7x3rlQFFLKktTaBKLhKDpGVyQFFMa8Y6zCQK/zC3lmk928QKBgQC36N/63nQF25f1Oc5xgdKgrNCOp50gIMrr9KI1kDUh+eks4PCunoPNIzg3VWlK4G2IjLIq39AmPYVzY8s5FS9OxFQQ48YwY8phq+A3v6b9QbT6X358p8JQU2+7rM4TGhwupqQHZohGdrt8WCNh8PkkHN6HF2+9NTloxRnLnE3BSQKBgHaeV4+RaGm1gOZQPvlfCQ24B2K3s+Od1tnzfmZmsxhBb6fY92UABoXHFkACcqMBASS2+obUeqInnGwpoE2qBmtzJUC6ADZSCzVS1iVBFyN0BmQe8iaxsc1dW/DJtfZf9fMeUCgUnmR6eECRzmG6ggIaD7SLLVsbtOEDbsj0SLTxAoGBAIaWmsrkaIHBBltOKlM88eSvVCNqGj48Xsa4j21E5hGOeVLhQn6ka7MKFRoIkkagfGMrbxcjkuL2FwpOLRSg0E7h5/SbnZDLPMetNhhyxul0BsrLwNG3KqjwXalNCyhHWEhnCu4jD7cjrvqa8NrZzFqVl+ieut+htpurkkysc0xg";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA3DXswBhEJniAzi0OZVn3oeI7g5vfuXUUpziSHJMA4Rmcd/D5HkbivmNtYG2e4u446YkJ3jjkLLuvEp+i8p9QE0XKhe8hzwO2oOr7KjjxCS9F8RsHN+oXoIwn2mkB3R1gVxS9/Ifn+AEYOD0yhtQnb8DZazU9ek9i9rX/qGhgfc6pBwsUdA2KHmliA+qLmAy2IuIPYb5EIqdmuJlTutBFfkg7QLEtHBLMuLLDIn4A4CVh5ylJ06f0Zd2XvEUPrTdSgAL+r/yrZimBNXjN+eTtnLq5kuCWkvd9zzSeBT394lfExPGuUlktRnQHpUCwaSTD2SS5brD1kXtpRwzIyQ8luQIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://localhost:8080/orders/modifiedOrdersState.do";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://localhost:8080/orders/modifiedOrdersState.do";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

