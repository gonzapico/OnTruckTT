package xyz.gonzapico.ontrucktt.showLoads;

/**
 * Created by gfernandez on 6/12/16.
 */

public class Load {

  public String destination = "";
  public String destination_companyName = "";
  public String destination_contactName = "";
  public String destination_date = "";
  public String destination_date_end = "";
  public String destination_full_address = "";
  public double destination_lat;
  public double destination_lon;
  public int distance;
  public String driver;
  public String extra_info;
  public String freightType;
  public String name;
  public String origin;
  public String origin_companyName;
  public String origin_contactName;
  public String origin_date;
  public String origin_date_end;
  public String origin_full_address;
  public double origin_lat;
  public double origin_lon;
  public String packageType;
  public int price;
  public int quantity;
  public String shiper;
  public int size_height;
  public int size_length;
  public int size_width;
  public int status;
  public int weight;

  public String getOrigin(){
    return "["+origin_date+"]" + origin + "(" + origin_companyName + ")" + "["+origin_date_end+"]";
  }

  public String getDestination(){
    return "["+destination_date+"]" + destination + "(" + destination_companyName + ")" + "["+destination_date_end+"]" ;
  }

}
