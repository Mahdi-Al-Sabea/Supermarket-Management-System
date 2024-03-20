package mainClasses;


import java.util.Random;
import java.text.NumberFormat;

public class Item {

    private String name,category,barcode,description,imagePath;
    private double price;

  


	public String getBarcode() {
		return barcode;
	}


	Random gen=new Random();
    NumberFormat fmt1=NumberFormat.getCurrencyInstance();

    public Item(String category,String name,double price,String description) {//this is not used because we will no create the item we will buy it
        this.description=description;
        this.name=name;
        this.category=category;
        this.price=price;
        this.imagePath="/Programming3Project/src/application/images/7up can.jpg";
        int x=gen.nextInt(100001)+899999;
        barcode="BR_"+category.substring(0, 2).toUpperCase()+"_"+name.substring(0, 2).toLowerCase()+"_"+x;

    }


    public Item(String category,String name,double price,String description,String imagePath) {
        this.description=description;
        this.name=name;
        this.category=category;
        this.price=price;
        this.imagePath=imagePath;
        int x=gen.nextInt(100001)+899999;
        barcode="BR_"+category.substring(0, 2).toUpperCase()+"_"+name.substring(0, 2).toLowerCase()+"_"+x;



    }


    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }


    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public String getBarCode() {
        return barcode;
    }


    public String getCategory() {
        return category;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }






    public String toString() {
        return "name:"+name+" category:"+ category+" description:"+description+" barcode:"+barcode+" price per unit:"+fmt1.format(price);

    }


}