package model;

import java.sql.Date;

import javafx.scene.image.ImageView;

public class Medicine {
	private String name;
	private String image;
	private String character;
	private String effect;
	private String warning;
	private String company;
	private Date expiration;
	private int price;
	private int stock;

	private ImageView img;

	private String expiration1;

	public Medicine(String name, String image, String character, String effect, String warning, String company, Date expiration,
			int price, int stock) {
	this.name = name;
	this.image = image;
	this.character = character;
	this.effect = effect;
	this.warning = warning;
	this.company = company;
	this.expiration = expiration;
	this.price = price;
	this.stock = stock;
}
	public Medicine(String name, String image, String character, String effect, String warning, String company, String expiration1,
			int price, int stock) { // medAppDao ¿ë
	this.name = name;
	this.character = character;
	this.effect = effect;
	this.warning = warning;
	this.company = company;
	this.setExpiration1(expiration1);
	this.price = price;
	this.stock = stock;
}

	public Medicine(String name, ImageView img, String character, String effect, String warning, String company, String expiration1,
			int price, int stock) {
	this.name = name;
	this.img = img;
	this.character = character;
	this.effect = effect;
	this.warning = warning;
	this.company = company;
	this.setExpiration1(expiration1);
	this.price = price;
	this.stock = stock;
}
	public Medicine(String name, Date expiration) {
	this.name = name;
	this.expiration = expiration;
}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getCharacter() {
		return character;
	}
	public void setCharacter(String character) {
		this.character = character;
	}

	public String getEffect() {
		return effect;
	}
	public void setEffect(String effect) {
		this.effect = effect;
	}

	public String getWarning() {
		return warning;
	}
	public void setWarning(String warning) {
		this.warning = warning;
	}

	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}

	public Date getExpiration() {
		return expiration;
	}
	public void setExpiration(Date expiration) {
		this.expiration = expiration;
	}

	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}

	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public String getExpiration1() {
		return expiration1;
	}
	public void setExpiration1(String expiration1) {
		this.expiration1 = expiration1;
	}

}