package hospital;


import  java.util.*;
import  java .sql.*;

public class Patient {

    private  Connection cn;
    private  Scanner sc;
    public  Patient(Connection cn,Scanner sc)

    {
        this.cn=cn;
        this.sc=sc;

    }


    public  void  addpatient()
    {
        sc.nextLine();
        System.out.println("Enter the name");
        String name=sc.nextLine();
        System.out.println("Enter the age");
        int age=sc.nextInt();
        System.out.println("Enter the patient gender");
        String gender=sc.nextLine();

        try {

            String query="insert into p(name,age,gender) values( ?,?,?)";
            PreparedStatement pr=cn.prepareStatement(query);
            pr.setString(1,name);
            pr.setInt(2,age);
            pr.setString(3,gender);

            int res=pr.executeUpdate();
            if (res > 0)
            {
                System.out.println("record inderted");
            }
            else
            {
                System.out.println("Error.....");
            }



        }
        catch (Exception e)

        {
            System.out.println(e.getMessage());
        }


    }

    public void viewpatient()
    {
      try {
          String q="select * from p";
          PreparedStatement pr=cn.prepareStatement(q);
          ResultSet res=pr.executeQuery();
          System.out.println("+--------+--------------+------+-----+---------+----------------+");
          System.out.println("| id     |name          |age   |gender");
          System.out.println("+--------+--------------+------+-----+---------+----------------+");


          while (res.next())
          {
              int id=res.getInt("id");
              String name=res.getString("name");
              int age=res.getInt("age");
              String gender=res.getString("gender");


              System.out.printf("|%-9s|%-20s|%-10s|%-15s",id,name,age,gender);
              System.out.println();
              System.out.println("+--------+--------------+------+-----+---------+----------------+");


          }

      }
      catch (Exception e)
      {
          System.out.println(e.getMessage());
      }
    }


    public boolean  getpationByid(int id)
    {
        sc.nextLine();
        try {

            String q="select * from p where id=?";
            PreparedStatement pr=cn.prepareStatement(q);
            pr.setInt(1,id);
            ResultSet res= pr.executeQuery();


            if (res.next())
            {
                return true;
            }
            else
            {
                return false;
            }

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

        return false;
    }
    
}
