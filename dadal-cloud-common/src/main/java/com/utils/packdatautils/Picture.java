/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package com.utils.packutils   
 * @Description:    TODO 描述   
 * @author: Frankjiu
 * @date:   2020年2月13日下午6:28:05
 * @version V1.0
 */

package com.utils.packdatautils;

/**
 * @author: Frankjiu
 * @date: 2020年2月13日 下午6:28:05
 */

public class Picture {

	/**
	 * 服务名
	 */
	private String ServerName;

	/**
	 * 详情图
	 */
	private String DetailPicture;

	/**
	 * 缩略图
	 */
	private String SimplePicture;

	public String getServerName() {
		return ServerName;
	}

	public void setServerName(String serverName) {
		ServerName = serverName;
	}

	public String getDetailPicture() {
		return DetailPicture;
	}

	public void setDetailPicture(String detailPicture) {
		DetailPicture = detailPicture;
	}

	public String getSimplePicture() {
		return SimplePicture;
	}

	public void setSimplePicture(String simplePicture) {
		SimplePicture = simplePicture;
	}

	@Override
	public String toString() {
		return "Picture [ServerName=" + ServerName + ", DetailPicture=" + DetailPicture + ", SimplePicture=" + SimplePicture + "]";
	}

}
