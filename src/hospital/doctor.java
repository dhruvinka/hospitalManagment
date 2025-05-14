package  hospital;

import  java.util.*;
import  java .sql.*;
public class doctor {

    private Connection cn;
    private Scanner sc;

    public doctor(Connection cn, Scanner sc) {
        this.cn = cn;
        this.sc = sc;

    }


    public void view_Doctor() {
        System.out.println("Enter the name");
        String name = sc.nextLine();
        System.out.println("Enter the age");
        int age = sc.nextInt();
        System.out.println("Enter the patient gender");
        String gender = sc.nextLine();

        try {

            String query = "select * from d";
            PreparedStatement pr = cn.prepareStatement(query);
            pr.setString(1, name);
            pr.setInt(2, age);
            pr.setString(3, gender);
            ResultSet res=pr.executeQuery();

            System.out.println("Patients:");
            System.out.println("+--------+--------------+------+-----+---------+----------------+");
            System.out.println("| d_id     |name          |age   |gender");
            System.out.println("+--------+--------------+------+-----+---------+----------------+");



            while (res.next())
            {
                int ids=res.getInt("id");
                String names= res.getString("name");
                int ages=res.getInt("age");
                String genders=res.getString(gender);


                System.out.printf("|%-9s|%-20s|%-10s|%-15s",ids,names,ages,genders);
                System.out.println("+--------+--------------+------+-----+---------+----------------+");

            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }


    public  boolean getdocbyid(int id)
    {
        sc.nextLine();

        try {

            String q="select * from d where id=?";
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
