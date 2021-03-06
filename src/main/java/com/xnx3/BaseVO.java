package com.xnx3;

/**
 * <b>result</b>：执行成功{@link #SUCCESS}/失败{@link #FAILURE}
 * <p><b>info</b>：执行结果，若成功，此项可忽略，若失败，返回失败原因</p>
 * <p>(所有JSON返回值VO的父类)</p>
 * @author 管雷鸣
 *
 */
public class BaseVO implements java.io.Serializable{
	public final static int SUCCESS=1;
	public final static int FAILURE=0;
	
	/*
	 * 执行结果
	 * <li>0:失败
	 * <li>1:成功
	 */
	private int result;
	
	/*
	 * 执行信息,如执行成功，会返回执行成功的信息，执行失败，会返回为什么会失败的信息
	 */
	private String info;
	
	/**
	 * 默认为成功状态
	 */
	public BaseVO() {
		this.result = SUCCESS;
		this.info = "成功";
	}
	
	/**
	 * @param result 执行状态
	 * 			<ul>
	 * 				<li>{@link BaseVO#SUCCESS} 	：失败</li>
	 * 				<li>{@link BaseVO#FAILURE} 	：成功</li>
	 * 			</ul>
	 * @param info 执行信息,如执行成功，会返回执行成功的信息，执行失败，会返回为什么会失败的信息
	 */
	public void setBaseVO(int result,String info) {
		this.result = result;
		this.info = info;
	}
	
	/**
	 * 将另一个 BaseVO 的信息赋值到这个上。克隆
	 * @param baseVO {@link BaseVO}
	 */
	public void setBaseVO(BaseVO baseVO) {
		this.result = baseVO.getResult();
		this.info = baseVO.getInfo();
	}
	
	public int getResult() {
		return result;
	}

	/**
	 * 执行结果
	 * @param result 返回的结果，取值：
	 * 		<ul>
	 * 			<li>{@link BaseVO#SUCCESS} 	：失败
	 * 			<li>{@link BaseVO#FAILURE} 	：成功
	 * 		</ul>
	 */
	public void setResult(int result) {
		this.result = result;
	}

	public String getInfo() {
		return info;
	}

	/**
	 * 执行信息,如执行成功，会返回执行成功的信息，执行失败，会返回为什么会失败的信息
	 * @param info 成功的文字说明，或者成功要传回什么字符串
	 */
	public void setInfo(String info) {
		this.info = info;
	}
	
	/**
	 * 返回成功得 {@link BaseVO}
	 * @param info 成功得info信息
	 * @return 成功得{@link BaseVO}
	 */
	public static BaseVO success(String info){
		BaseVO vo = new BaseVO();
		vo.setBaseVO(BaseVO.SUCCESS, info);
		return vo;
	}
	
	/**
	 * 返回失败得 {@link BaseVO}
	 * @param info 失败得info信息，失败原因
	 * @return 失败得{@link BaseVO}
	 */
	public static BaseVO failure(String info){
		BaseVO vo = new BaseVO();
		vo.setBaseVO(BaseVO.FAILURE, info);
		return vo;
	}

	@Override
	public String toString() {
		return "BaseVO [result=" + result + ", info=" + info + "]";
	}
	
}
