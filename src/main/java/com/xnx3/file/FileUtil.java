package com.xnx3.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import com.xnx3.Lang;
import com.xnx3.net.HttpsUtil.TrustAnyHostnameVerifier;
import com.xnx3.net.HttpsUtil.TrustAnyTrustManager;

/**
 * 文件操作 。请使用 com.xnx3.FileUtil
 * @author 管雷鸣
 * @deprecated
 */
public class FileUtil {
	public final static String UTF8="UTF-8";
	public final static String GBK="GBK";
	
	static final String ENCODE="UTF-8";	//默认文件编码UTF-8
	
	/**
	 * 读文件，返回文件文本信息，默认编码UTF-8
	 * @param path 文件路径 C:\xnx3.txt
	 * @return String 读取的文件文本信息
	 */
	public static String read(String path){
		return read(path,ENCODE);
	}
	
	/**
	 * 读文件，返回文件文本信息
	 * @param path 文件路径 C:\xnx3.txt
	 * @param encode 文件编码.如 FileUtil.GBK
	 * @return String 返回的文件文本信息
	 */
	public static String read(String path,String encode){
		return com.xnx3.FileUtil.read(path, encode);
	}
	
	/**
	 * 读文件，返回文件内容
	 * @param file 要读取的文件
	 * @param encode 编码，如FileUtil.GBK
	 * @return String 读取的文件文本信息
	 */
	public static String read(File file,String encode){
		return com.xnx3.FileUtil.read(file, encode);
	}
	
	
	/**
	 * 写文件
	 * @param path 传入要保存至的路径————如D:\\a.txt
	 * @param xnx3_content 传入要保存的内容
	 * @return 成功|失败
	 */
	public static boolean write(String path,String xnx3_content){
		return com.xnx3.FileUtil.write(path, xnx3_content);
	}
	

	/**
	 * 写文件
	 * @param path 传入要保存至的路径————如D:\\a.txt
	 * @param xnx3_content 传入要保存的内容
	 * @param encode 写出文件的编码
	 * 			<ul>
	 * 				<li>{@link FileUtil#UTF8}</li>
	 * 				<li>{@link FileUtil#GBK}</li>
	 * 			</ul>
	 * @throws IOException IO异常
	 */
	public static void write(String path,String xnx3_content,String encode) throws IOException{
       com.xnx3.FileUtil.write(path, xnx3_content, encode);
	}
	
	/**
	 * 写文件
	 * @param file 传入要保存至的路径————如D:\\a.txt
	 * @param xnx3_content 传入要保存的内容
	 * @return boolean
	 */
	public static boolean write(File file,String xnx3_content){
		return com.xnx3.FileUtil.write(file, xnx3_content);
	}
	
	/**
	 * InputStream转为文件并保存，为jar包内的资源导出而写
	 * <pre>
	 * 	FileUtil.inputStreamToFile(getClass().getResourceAsStream("dm.dll"), "C:\\dm.dll");
	 * </pre>
	 * @param inputStream 输入流
	 * @param targetFilePath 要保存的文件路径
	 */
	public static void inputStreamToFile(InputStream inputStream, String targetFilePath) {
		com.xnx3.FileUtil.inputStreamToFile(inputStream, targetFilePath);
	}

	/**
	 *  复制文件
	 *  <pre>copyFile("E:\\a.txt", "E:\\aa.txt");</pre>
	 * @param sourceFile 源文件，要复制的文件所在路径
	 * @param targetFile 复制到那个地方
	 */
    public static void copyFile(String sourceFile, String targetFile){
        com.xnx3.FileUtil.copyFile(sourceFile, targetFile);
    }

	
	/**
	 * 删除单个文件，java操作
	 * @param fileName 文件名，包含路径。如E:\\a\\b.txt
	 * @return boolean true：删除成功
	 */
	public static boolean deleteFile(String fileName){
		return com.xnx3.FileUtil.deleteFile(fileName);
	}
	
	
	/**
	 * 传入绝对路径，判断该文件是否存在
	 * @param filePath 文件的绝对路径，如 "C:\\WINDOWS\\system32\\msvcr100.dll"
	 * @return Boolean true:存在
	 */
    public static boolean exists(String filePath){
    	return com.xnx3.FileUtil.exists(filePath);
    }


	/**
	 * 通过网址获得文件长度
	 * @param url 文件的链接地址
	 * @return 文件长度(Hander里的Content-Length)。失败返回-1
	 */
	public static long getFileSize(String url) {
		return com.xnx3.FileUtil.getFileSize(url);
	}

	/**
	 * 从互联网下载文件。适用于http、https协议
	 * <p>下载过程会阻塞当前线程</p>
	 * <p>若文件存在，会先删除存在的文件，再下载</p>
	 * @param downUrl 下载的目标文件网址 如 "http://www.xnx3.com/down/java/j2se_util.zip"
	 * @param savePath 下载的文件保存路径。如 "C:\\test\\j2se_util.zip"
	 * @throws IOException IO异常
	 */
	public static void downFiles(String downUrl,String savePath) throws IOException{
		downFiles(downUrl, savePath, null);
	}
	

	/**
	 * 从互联网下载文件。适用于http、https协议
	 * <p>下载过程会阻塞当前线程</p>
	 * <p>若文件存在，会先删除存在的文件，再下载</p>
	 * @param downUrl 下载的目标文件网址 如 "http://www.xnx3.com/down/java/j2se_util.zip"
	 * @param savePath 下载的文件保存路径。如 "C:\\test\\j2se_util.zip"
	 * @param param 包含在请求头中的一些参数。比如 User-Agent 等。若为空，则不传递任何参数。例如：
	 * 			<ul>
	 * 				<li>key:User-Agent &nbsp;&nbsp;&nbsp;&nbsp; value: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.87 Safari/537.36</li>
	 * 				<li>key:Host &nbsp;&nbsp;&nbsp;&nbsp; value:xnx3.com</li>
	 * 			</ul>
	 * @throws IOException  IO异常
	 */
	public static void downFiles(String downUrl,String savePath, Map<String, String> param) throws IOException{
		//默认超时是30秒
		downFiles(downUrl, savePath, param, 30000);
	}
	
	/**
	 * 从互联网下载文件。适用于http、https协议
	 * <p>下载过程会阻塞当前线程</p>
	 * <p>若文件存在，会先删除存在的文件，再下载</p>
	 * @param downUrl 下载的目标文件网址 如 "http://www.xnx3.com/down/java/j2se_util.zip"
	 * @param savePath 下载的文件保存路径。如 "C:\\test\\j2se_util.zip"
	 * @param param 包含在请求头中的一些参数。比如 User-Agent 等。若为空，则不传递任何参数。例如：
	 * 			<ul>
	 * 				<li>key:User-Agent &nbsp;&nbsp;&nbsp;&nbsp; value: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.87 Safari/537.36</li>
	 * 				<li>key:Host &nbsp;&nbsp;&nbsp;&nbsp; value:xnx3.com</li>
	 * 			</ul>
	 * @param timeout 超时时间，单位毫秒
	 * @throws IOException IO异常
	 */
	public static void downFiles(String downUrl,String savePath, Map<String, String> param, int timeout) throws IOException{
		com.xnx3.FileUtil.downFile(downUrl, savePath, param, timeout);
	}
	
	/**
	 * 从互联网下载文件
	 * <p>建议使用 {@link #downFiles(String, String)}</p>
	 * <p>下载过程会阻塞当前线程</p>
	 * <p>若文件存在，会先删除存在的文件，再下载</p>
	 * @param downUrl 下载的目标文件网址 如 "http://www.xnx3.com/down/java/j2se_util.zip"
	 * @param savePath 下载的文件保存路径。如 "C:\\test\\j2se_util.zip"
	 * @return 返回下载出现的异常
	 * 			<ul>
	 * 				<li>若返回null，则为下载成功，下载完毕，没有出现异常</li>
	 * 				<li>若返回具体字符串，则出现了异常，被try捕获到了，返回e.getMessage()异常信息</li>
	 * 			</ul>
	 */
	@Deprecated
	public static String downFile(String downUrl,String savePath){
		String result = null;
		try {
			new FileUtil().downFiles(downUrl, savePath);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result = e.getMessage();
		}
		return result;
	}
	
	/**
	 * 将 {@link BufferedReader} 转换为 {@link String}
	 * @param br {@link BufferedReader}
	 * @return String 若失败，返回 ""
	 */
	public static String BufferedReaderToString(BufferedReader br) {
		return com.xnx3.FileUtil.bufferedReaderToString(br);
	}
	
	/**
	 * 将 {@link InputStream} 转化为 byte[]
	 * @param input 传入的 {@link InputStream}
	 * @return 转化为的结果
	 * @throws IOException IO异常
	 */
	public static byte[] inputstreamToByte(InputStream input) throws IOException {
	   return com.xnx3.FileUtil.inputstreamToByte(input);
	}
	
	/**
	 * 输入文件路径，返回这个文件的创建时间
	 * @param filePath 要获取创建时间的文件的路径，绝对路径
	 * @return 此文件创建的时间
	 */
	public static Date getCreateTime(String filePath){  
		return com.xnx3.FileUtil.getCreateTime(filePath);
	}
	
  
}
