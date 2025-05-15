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
         sc.nextLine();

        try {

            String query = "select * from d";
            PreparedStatement pr = cn.prepareStatement(query);
            ResultSet res=pr.executeQuery();

            System.out.println("Doctors:");
            System.out.println("+--------+--------------+------+-----+---------+----------------+");
            System.out.println("| d_id     |name                |sp ");
            System.out.println("+--------+--------------+------+-----+---------+----------------+");



            while (res.next())
            {
                int ids=res.getInt("id");
                String names= res.getString("name");
                String genders=res.getString("sp");


                System.out.printf("|%-9s|%-20s|%-15s",ids,names,genders);
                System.out.println();
                //.out.println("+--------+--------------+------+-----+---------+----------------+");

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
