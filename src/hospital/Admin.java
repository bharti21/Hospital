/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hospital;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author sailee
 */
public class Admin {
    private final IntegerProperty id;
    private  final StringProperty pfnm;
    private final StringProperty plnm;
    private final LongProperty phone_no;
    private final StringProperty p_add;
    private final StringProperty p_visitDate;
    
    public Admin(int id,String pfnm,String plnm,Long phone_no,String p_add,String p_visitDate)
    {
        super();
        this.id=new SimpleIntegerProperty(id);
        this.pfnm=new SimpleStringProperty(pfnm);
        this.plnm=new SimpleStringProperty(plnm);
        this.phone_no=new SimpleLongProperty(phone_no);
        this.p_add=new SimpleStringProperty(p_add);
       this.p_visitDate=new SimpleStringProperty(p_visitDate);
    }
    
    public  int getPID()
    {
        return id.get();
    }
    
    
    public String getFirstName()
    {
        return pfnm.get();
    }
    
    
    public String getLastName()
    {
        return plnm.get();
    }
    
    
    public Long getPhone()
    {
        return phone_no.get();
    }
    
    
    public String getAddress()
    {
        return p_add.get();
    }
    
    
    public String getVisitDate()
    {
        return p_visitDate.get();
    }
    
}
