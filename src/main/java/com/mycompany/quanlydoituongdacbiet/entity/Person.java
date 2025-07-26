/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.quanlydoituongdacbiet.entity;
import com.mycompany.quanlydoituongdacbiet.utils.FileUtils;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author PC
 */

//@XmlRootElement(name = "Person")
//@XmlAccessorType(XmlAccessType.FIELD)
public abstract class Person 
{
  
    protected int id;
    protected String name;
    protected Date birthday;
    protected String address;
    //private SimpleDateFormat fDate=new SimpleDateFormat("dd/MM/yyyy");
    public Person() 
    {
        
    }
    public Person(int id, String name, Date birthday, String address) throws ParseException 
    {
        super();
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.address = address;
    }

    public int getId() 
    {
        return id;
    }

    public void setId(int id) 
    {
        this.id = id;
    }

    public String getName() 
    {
        return name;
    }

    public void setName(String name) 
    {
        this.name = name;
    }
    
    public String getLastName()
    {
        String fullName = this.getName();
        int lastSpaceIndex = fullName.lastIndexOf(" "); // tìm vị trí dấu cách cuối cùng
        String lastName = fullName.substring(lastSpaceIndex + 1); // lấy phần tử từ vị trí đó đến hết chuỗi tên
        return lastName;
    }
    
    public String getFirstName()
    {
        return this.getName().replace(this.getLastName(), "");
    }
    
    public Date getBirthday() 
    {
        return this.birthday;
    }

    public void setBirthday(Date birthday) 
    {
        this.birthday = birthday;
    }

    public String getAddress() 
    {
        return address;
    }

    public void setAddress(String address) 
    {
        this.address = address;
    } 
}
