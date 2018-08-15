package com.jt.dubbo.pojo;



import org.apache.solr.client.solrj.beans.Field;

import com.jt.common.po.BasePojo;

public class Item extends BasePojo{

	@Field("id")
	private Long id;	    //商品Id
	
	@Field("title")
	private String title;	//商品标题
	/**
	 * @Column(name="sell_point")
	 * 如果表中的字段与对象的属性不一致,
	 * 需要使用该注解进行标识.但是如何
	 * 开启了驼峰映射规则,可以省略不写
	 */
	@Field("sellPoint")
	private String sellPoint; //卖点信息
	@Field("price")
	private Long price;		  //价格  
	@Field("num")
	private Integer num;	  //商品数量
	private String barcode;	  //二维码
	@Field("image")
	private String image;	  //商品图片信息
	private Long cid;		  //商品分类信息
	private Integer status;	  //状态码信息
	
	//为了实现图片信息的回显正常添加方法
	public String[] getImages(){
		
		return image.split(",");
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSellPoint() {
		return sellPoint;
	}
	public void setSellPoint(String sellPoint) {
		this.sellPoint = sellPoint;
	}
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public Long getCid() {
		return cid;
	}
	public void setCid(Long cid) {
		this.cid = cid;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
}
