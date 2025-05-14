package  hospital;

import  java.util.*;
import  java .sql.*;
public class doctor {

    private  Connection cn;
    private  Scanner sc;
    public  doctor(Connection cn,Scanner sc)

    {
        this.cn=cn;
        this.sc=sc;

    }


    public  void  view_data()
    {
        try {




        }
        catch (Exception e)
        {
            System.out.println( e.getMessage());
        }
    }

}
