package com.xnx3;

import java.util.HashMap;
import java.util.Map;
import com.xnx3.net.HttpResponse;
import com.xnx3.net.HttpsUtil;

/**
 * 短信发送。
 * <br/>短信开通及使用，联系 www.leimingyun.com
 * <br/>整个短信内容（包含签名的字符）不要超过70个字符，不然会记为发送多条短信。以70个字符为一条短信计费
 * @author 管雷鸣
 *
 */
public class SMSUtil implements java.io.Serializable{
	private int uid;
	private String password;
	
	public static HttpsUtil httpsUtil;	//https请求
	public static Map<String, String> headers;	//https请求的hader
	static{
		httpsUtil = new HttpsUtil();
		
		headers = new HashMap<String, String>();
		headers.put("Content-Type", "application/json;charset=utf-8");
	}
	
	/**
	 * 短信发送。
	 * <br/>短信开通及使用，联系 www.leimingyun.com
	 * @param uid 用户在短信平台的uid（登录用户名）
	 * @param password 用户在短信平台登录的密码
	 */
	public SMSUtil(int uid, String password) {
		this.uid = uid;
		this.password = MD5Util.MD5(password);
	}
	
	public static void main(String[] args) {
		SMSUtil util = new SMSUtil(112354, "9i930!!8");
		BaseVO vo = util.send("17076012262", "哈哈哈哈哈");
//		BaseVO vo = util.getBalance();
		System.out.println(vo);
	}
	
	/**
	 * 发送一条短信。短信内容自己定，不过前缀会加上签名。
	 * @param phone 接收短信的手机号
	 * @param content 发送短信的内容。不加签名，比如这里传入“哈哈哈”,那么用户接收到的短信为 
	 * 		<pre>
	 * 			【雷鸣云】哈哈哈
	 * 		</pre>
	 * @return 其中 {@link BaseVO#getResult()} 为执行状态，是否成功
	 * 		<ul>
	 *	 		<li>{@link BaseVO#SUCCESS} 	：失败,可以通过 {@link BaseVO#getInfo()} 获得失败原因 </li>
	 * 			<li>{@link BaseVO#FAILURE} 	：成功,可以通过 {@link BaseVO#getInfo()} 获得发送的这个短信的消息唯一编号</li>
	 * 		</ul>
	 */
	public BaseVO send(String phone, String content){
		HttpResponse hr = httpsUtil.get("https://submit.10690221.com/send/ordinarykv?uid="+this.uid+"&password="+this.password+"&mobile="+phone+"&msg="+StringUtil.stringToUrl(content));
		
		if(hr.getCode() != 200){
			return BaseVO.failure("请求短信接口响应异常，http code : "+hr.getCode());
		}else{
			//响应正常
			String response = hr.getContent();
			response = response.replace("}", ",");	//吧最后一个}替换为 , 方便substring 截取，避免出错
			//取出code来
			String code = StringUtil.subString(response, "\"code\":", ",", 2);
			if(code == null){
				return BaseVO.failure("未能识别响应结果。响应内容："+response);
			}
			if(code.trim().equals("0")){
				//成功，那么拿到  msgid  返回
				//取出 msgId
				String msgId = StringUtil.subString(response, "\"msgId\":", ",", 2);
				return BaseVO.success(msgId.trim());
			}else{
				//失败，拿到失败原因返回
				
				//取出  msg 
				String msg = StringUtil.subString(response, "\"msg\":\"", "\",", 2);
				return BaseVO.failure(msg);
			}
		}
	}
	
	/**
	 * 获取自己当前的短信余额，短信还剩多少条
	 * @return 其中 {@link BaseVO#getResult()} 为执行状态，是否成功
	 * 		<ul>
	 *	 		<li>{@link BaseVO#SUCCESS} 	：失败,可以通过 {@link BaseVO#getInfo()} 获得失败原因 </li>
	 * 			<li>{@link BaseVO#FAILURE} 	：成功,可以通过 {@link BaseVO#getInfo()} 获得短信剩余条数</li>
	 * 		</ul>
	 */
	public BaseVO getBalance(){
		String param = "{\"uid\":"+this.uid+",\"password\":\""+this.password+"\"}";
		
		HttpResponse hr;
		try {
			hr = httpsUtil.send("https://submit.10690221.com/get/balance", param, headers);
		} catch (Exception e) {
			e.printStackTrace();
			return BaseVO.failure(e.getMessage());
		}
		
		if(hr.getCode() != 200){
			return BaseVO.failure("请求短信接口响应异常，http code : "+hr.getCode());
		}else{
			//响应正常
			String response = hr.getContent();
			response = response.replace("}", ",");	//吧最后一个}替换为 , 方便substring 截取，避免出错
			//取出code来
			String code = StringUtil.subString(response, "\"code\":", ",", 2);
			if(code == null){
				return BaseVO.failure("未能识别响应结果。响应内容："+response);
			}
			if(code.trim().equals("0")){
				//成功，那么拿到  balance  返回
				//取出 balance
				String balance = StringUtil.subString(response, "\"balance\":", ",", 2);
				return BaseVO.success(balance.trim());
			}else{
				//失败，拿到失败原因返回
				
				//取出  msg 
				String msg = StringUtil.subString(response, "\"msg\":\"", "\",", 2);
				return BaseVO.failure(msg);
			}
		}
	}
	
}
