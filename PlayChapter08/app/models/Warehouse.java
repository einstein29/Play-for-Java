package models;

import com.avaje.ebean.Model;
import com.avaje.ebean.Model.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Warehouse extends Model {

  @Id
  public Long id;

  public String name;

  @OneToMany(mappedBy = "warehouse")
  public List<StockItem> stock = new ArrayList<StockItem>();

  @OneToOne
  public Address address;
  
  public static final Find<Long, Warehouse> find = new Find<Long, Warehouse>(){};
  
  public static Warehouse findById(Long id){
	  return find.byId(id);
  }

  //@Override
  public String toString() {
    return name;
  }
}
