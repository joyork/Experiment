package game;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Product entity. @author MyEclipse Persistence Tools
 */

public class Product {

	// Fields

	private String gameName;
	private String gameDesc;
	private String gameIO;
	private String imgUrl;
	private String swfPath;
	private Integer gameWidth;
	private Integer gameHeight;
	private Integer gameType;
	private String createDate;

	public String getGameName() {
		return this.gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public String getGameDesc() {
		return this.gameDesc;
	}

	public void setGameDesc(String gameDesc) {
		this.gameDesc = gameDesc;
	}

	public String getGameIO() {
		return this.gameIO;
	}

	public void setGameIO(String gameIO) {
		this.gameIO = gameIO;
	}

	public String getImgUrl() {
		return this.imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public String getSwfPath() {
		return this.swfPath;
	}

	public void setSwfPath(String swfPath) {
		this.swfPath = swfPath;
	}

	public Integer getGameWidth() {
		return this.gameWidth;
	}

	public void setGameWidth(Integer width) {
		this.gameWidth = width;
	}

	public Integer getGameHeight() {
		return this.gameHeight;
	}

	public void setGameHeight(Integer height) {
		this.gameHeight = height;
	}

	public Integer getGameType() {
		return this.gameType;
	}

	public void setGameType(Integer gameType) {
		this.gameType = gameType;
	}

	public String getCreateDate() {
		return this.createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

}