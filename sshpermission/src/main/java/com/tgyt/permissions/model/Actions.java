package com.tgyt.permissions.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


/** 
  * @ClassName: Actions 
  * @Description: 操作管理实体类 
  * @author sunct sunchaotong18@163.com 
  * @date 2011-9-19 下午2:45:45 
  *  
  */
@Entity
@Table(name="c_action")
public class Actions  implements java.io.Serializable {


    // Fields    

     private Integer id;
     private String name;
     private String enname;
     private String methodName;
     private String icon;
     private String status;
     private Integer orderid;
     private String memo;


    // Constructors

    /** default constructor */
    public Actions() {
    }

	/** minimal constructor */
    public Actions(String name, String enname, String status) {
        this.name = name;
        this.enname = enname;
        this.status = status;
    }
    
    /** full constructor */
    public Actions(String name, String enname, String methodName, String icon, String status, Integer orderid, String memo) {
        this.name = name;
        this.enname = enname;
        this.methodName = methodName;
        this.icon = icon;
        this.status = status;
        this.orderid = orderid;
        this.memo = memo;
    }

   
    // Property accessors
    @Id 
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="id", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    @Column(name="name", nullable=false, length=30)

    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    @Column(name="enname", nullable=false, length=30)

    public String getEnname() {
        return this.enname;
    }
    
    public void setEnname(String enname) {
        this.enname = enname;
    }
    
    @Column(name="handler", length=100)

    public String getMethodName() {
        return this.methodName;
    }
    
    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }
    
    @Column(name="icon", length=60)

    public String getIcon() {
        return this.icon;
    }
    
    public void setIcon(String icon) {
        this.icon = icon;
    }
    
    @Column(name="status", nullable=false)

    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    @Column(name="orderid")

    public Integer getOrderid() {
        return this.orderid;
    }
    
    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }
    
    @Column(name="memo", length=50)

    public String getMemo() {
        return this.memo;
    }
    
    public void setMemo(String memo) {
        this.memo = memo;
    }

}