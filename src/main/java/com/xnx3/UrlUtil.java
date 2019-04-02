package com.xnx3;

/**
 * url网址相关操作
 * @author 管雷鸣
 */
public class UrlUtil {
	
	/**
	 * 替换掉路径的层级，即替换掉前缀的../  、  ./  、 /
	 * @param path 绝对路径前缀，如 http://www.wang.market/a/
	 * @param originalUrl 具体文件，如 ../js/func.js
	 */
	public static String hierarchyReplace(String path, String originalUrl){
		if(isAbsoluteUrl(originalUrl)){
			return originalUrl;
		}
		
		//提取出path中的域名
		String domain = "";		//如  http://wang.market   
		String s = path.substring(9, path.length());
		int domianInt = s.indexOf("/");
		if(domianInt > 0){
			domain = path.substring(0, domianInt+9);
		}
		
		//判断前缀路径末尾是否有/，如没有，补上
		if(path.lastIndexOf("/") != path.length()){
			path = path + "/";
		}
		
		//如果是跟路径引用，直接返回组合网址
		if(originalUrl.indexOf("/") == 0){
			return domain+originalUrl;
		}
		
		while(originalUrl.indexOf("./") == 0 || originalUrl.indexOf("../") == 0){
			if(originalUrl.indexOf("./") == 0){
				//过滤前面的./
				originalUrl = originalUrl.substring(2, originalUrl.length());
			}else if (originalUrl.indexOf("../") == 0) {
				//过滤前面的./
				originalUrl = originalUrl.substring(3, originalUrl.length());
				//prefixUrl要向上一级  
				path = path.substring(0, path.substring(0, path.length()-1).lastIndexOf("/")+1);
			}
		}
		
		return path+originalUrl;
	}
	
	/**
	 * 判断url是否是绝对路径网址
	 * @param url 要判断的url
	 * @return true:是绝对路径的
	 */
	public static boolean isAbsoluteUrl(String url){
		if(url.indexOf("http://") > -1 || url.indexOf("https://") > -1 || url.indexOf("//") > -1){
			return true;
		}
		return false;
	}
	
	/**
	 * 获取当前url地址所在的远程文件路径
	 * @param url 目标url地址，绝对路径，如 http://www.wscso.com/test/a.jsp
	 * @return url所在地址的路径，返回如 http://www.wscso.com/test/
	 */
	public static String getPath(String url){
		//排除问号及其后的字符串
		int wenhao = url.lastIndexOf("?");
		if(wenhao > -1){
			//发现问号的存在，那么将问号后面的字符删除掉
			url = url.substring(0, wenhao);
		}
		
		//排除 # 及其后的字符串
		int jinghao = url.lastIndexOf("#");
		if(jinghao > -1){
			//发现问号的存在，那么将问号后面的字符删除掉
			url = url.substring(0, jinghao);
		}
		
		
		int last = url.lastIndexOf("/");
		if(last > 8){
			url = url.substring(0, last+1);
		}
		
		return url;
	}
	
	/**
	 * 根据url地址，获取其访问的文件名字
	 * @param url 目标url，如 http://wang.market/images/a.jpg?a=123
	 * @return 返回访问的文件名，如以上url返回： a.jpg
	 */
	public static String getFileName(String url){
		//排除问号及其后的字符串
		int wenhao = url.lastIndexOf("?");
		if(wenhao > -1){
			//发现问号的存在，那么将问号后面的字符删除掉
			url = url.substring(0, wenhao);
		}
		
		//排除 # 及其后的字符串
		int jinghao = url.lastIndexOf("#");
		if(jinghao > -1){
			//发现问号的存在，那么将问号后面的字符删除掉
			url = url.substring(0, jinghao);
		}
		
		int last = url.lastIndexOf("/");
		if(last > 8){
			url = url.substring(last+1, url.length());
//			if(url.indexOf("?") > 0){
//				url = url.substring(0, url.indexOf("?"));
//			}
//			if(url.indexOf("#") > 0){
//				url = url.substring(0, url.indexOf("#"));
//			}
			return url;
		}else{
			//不是正常的url地址
			return "";
		}
	}
	
	

	/**
	 * 根据url地址，获取其访问的域名。若么有发现，返回空字符
	 * @param url 目标url，如 http://wang.market/images/a.jpg
	 * @return 返回wang.market
	 */
	public static String getDomain(String url){
		int start = url.indexOf("//");
		if(start > 0){
			url = url.substring(start+2, url.length());
			
			int end = url.indexOf("/");
			if(end > 0){
				url = url.substring(0, end);
			}
			return url;
		}
		
		return "";
	}
	
	/**
	 * 获取文件前缀名字。
	 * @param fileName 传入的文件名字。如传入 test.jpg ，则会返回不带后缀的名字： test
	 * @return 若传入null，则返回空字符串""
	 */
	public static String getFileBeforeName(String fileName){
		if(fileName == null){
			return "";
		}
		
		String htmlFile = "";
		if(fileName.indexOf(".") > 0){
			String s[] = fileName.split("\\.");
			htmlFile = s[0];
		}else{
			htmlFile = fileName;
		}
		return htmlFile;
	}
	
	/**
	 * 获取当前url的协议，返回如 http 、 https 、 ftp 、 file 等
	 * @param url 绝对路径，必须是全的，包含协议的，如 http://www.xnx3.com
	 * @return 自动截取的协议，如 http 。若取不到，则返回null
	 */
	public static String getProtocols(String url){
		if(url == null){
			return null;
		}
		
		if(url.indexOf("//") > 1){
			return url.substring(0, url.indexOf("//") - 1);
		}
		return null;
	}
	
	
	public static void main(String[] args) {
		System.out.println(getPath("http://conference.cioe.cn/skin/gaofeng/css/reset.css?version=2018/6/1%2020:20:49"));
	}
}
