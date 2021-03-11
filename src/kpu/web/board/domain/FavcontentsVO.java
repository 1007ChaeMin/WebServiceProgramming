package kpu.web.board.domain;

public class FavcontentsVO {
	
	private String id;
	private String menu;
	private String pkid;
	private String bbsConfigFK;
	private String title;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getMenu() {
		return menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}
	
	public String getPkid() {
		return pkid;
	}

	public void setPkid(String pkid) {
		this.pkid = pkid;
	}
	
	public String getBbsConfigFK() {
		return bbsConfigFK;
	}

	public void setBbsConfigFK(String bbsConfigFK) {
		this.bbsConfigFK = bbsConfigFK;
	}
	
	public String getTitle() {
		return title;
	}

	public void SetTitle(String title) {
		this.title = title;
	}
}
