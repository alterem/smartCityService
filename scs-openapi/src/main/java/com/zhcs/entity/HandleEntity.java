package com.zhcs.entity;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

//*****************************************************************************
/**
 * <p>Title:HandleEntity</p>
 * <p>Description:处理事件</p>
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: 深圳市智慧城市管家信息科技有限公司</p>
 * @author 刘晓东 - Alter
 * @version v1.0 2017年2月23日
 */
//*****************************************************************************
public class HandleEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * token
	 */
	@ApiModelProperty(value="token" ,required=true)
	private String token;
	
	/**
	 * 事件id
	 */
	@ApiModelProperty(value="id" ,required=true)
	private String id;
	
	
	/**
	 * 处理说明
	 */
	@ApiModelProperty(value="处理说明" ,required=true)
	private String content;
	
	/**
	 * 图片
	 */
	@ApiModelProperty(value="图片（将图片的key以\",\"隔开）" ,required=true)
	private String img;
	

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}
	
	
	
	
}
