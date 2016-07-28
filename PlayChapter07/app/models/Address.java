package models;

import com.avaje.ebean.Model;
import javax.persistence.*;

@Entity
public class Address extends Model{

  @Id
  public Long id;

  @OneToOne(mappedBy = "address")
  public Warehouse warehouse;

  public String street;
  public String number;
  public String postalCode;
  public String city;
  public String country;

}
