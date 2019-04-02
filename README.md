# xnx3_util
A collection of common Java tools

# explain
##### com.xnx3.DateUtil	时间日期相关
- timeForUnix13()	返回当前13位的Unix时间戳
- timeForUnix10()	返回当前10位数的Unix时间戳
- intToString()	将Linux时间戳变为文字描述的时间
- dateFormat()	日期格式化，将Linux时间戳变为文字描述的时间
- currentDate()	获取当前时间，返回人看的时间，如 2016-03-19 00:00:00
- StringToDate()	将String类型时间转换为Date对象
- StringToInt()	将String类型时间转换为10位的linux时间戳
- weeHours()	获取当前传入时间的当天凌晨时间，如 2016-03-19 00:00:00
- midnight()	获取当前传入时间的当天午夜时间，如 2016-03-19 23:59:59
- dateToString()	转为String类型，变成当前显示的文字时间,如 2016-03-19 00:00:00
- dateToInt10()	将Date转化为 10位的时间戳
- long13To10()	将13位Linux时间戳转换为10位时间戳
- currentWeek()	获取当前是星期几,返回值从星期日开始
- getDateZeroTime()	传入一个10位的时间戳，返回当前时间戳所在的当天0点的10位时间戳
- getWeekForTime()	判断指定的日期是星期几

##### com.xnx3.FileUtil 文件
- read()	读文件，返回字符串
- write()	写文件
- inputStreamToFile()	InputStream转为文件并保存，为jar包内的资源导出而写
- copyFile()	复制文件
- deleteFile()	删除单个文件
- exists()	传入绝对路径，判断该文件是否存在
- getFileSize()	通过网址获得文件长度
- downFile()	从互联网下载文件。适用于http、https协议
- bufferedReaderToString()	将 BufferedReader 转换为 String
- inputstreamToByte()	将 InputStream 转化为 byte[]
- getCreateTime()	输入文件路径，返回这个文件的创建时间

##### com.xnx3.StringUtil 字符串
##### com.xnx3.IntegerUtil	整型、数字
##### com.xnx3.MD5Util	MD5加密
##### com.xnx3.SystemUtil 操作系统 
##### com.xnx3.UrlUtil	网址
##### com.xnx3.ZipUtil	zip压缩包相关
##### com.xnx3.DelayCycleEcecuteUtil	延迟多次执行，直到执行成功
	