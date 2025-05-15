package hospital;

import java.sql.*;
import java.util.Scanner;
import java.util.SortedMap;

public class HospitalManagment {

    private static  final String  url="jdbc:mysql://localhost:3306/bank";
    private static  final  String  username="root";
    private static  final String   pass="School@123";


public  static  void main(String[] args)
{

    try {
      Class.forName("com.mysql.cj.jdbc.Driver");

    }
    catch (Exception e)
    {
        System.out.println(e.getMessage());
    }

    Scanner sc=new Scanner(System.in);
    try {

        Connection cn= DriverManager.getConnection(url,username,pass);
        Patient patient=new Patient(cn,sc);
        doctor doc=new doctor(cn,sc);
        HospitalManagment hs=new HospitalManagment();

        while ( true)
        {
            System.out.println("HOSPITAL MANAGMEN SYSTEM");
            System.out.println("1.Add Patient");
            System.out.println("2.View Patients");
            System.out.println("3.View Doctor");
            System.out.println("4.Book Appointment");
            System.out.println("5.Exit");

            System.out.println("Enter the Choice");
            int Choice=sc.nextInt();


             switch (Choice)
             {
                 case 1:
                     patient.addpatient();
                     System.out.println();
                     break;
                 case 2:
                     patient.viewpatient();
                     System.out.println();
                     break;
                 case 3:
                     doc.view_Doctor();
                     System.out.println();
                     break;
                 case 4:
                     bookappointment(patient,doc,cn,sc);
                     System.out.println();
                     break;
                 case 5:
                     return;
                 default:
                     System.out.println("Enter the Valid Choice");

             }

        }

    }
    catch (Exception e)
    {
        System.out.println(e.getMessage());
    }



}

public static void  bookappointment(Patient p,doctor d,Connection cn,Scanner sc)
{
    System.out.println("Enter the patient id");
    int id=sc.nextInt();
    System.out.println("Enter the doctor id");
    int d_id=sc.nextInt();
    System.out.println("Enter the appointment date ");
    sc.nextLine();
    String a_date=sc.nextLine();


    if (p.getpationByid(id) && d.getdocbyid(d_id) )
    {

        if (checkavailable(d_id,a_date,cn))
        {
            String q="insert into appoinments(p_id,d_id,appoinment_date) values (?,?,?)";

            try {

            PreparedStatement pr= cn.prepareStatement(q);
            pr.setInt(1,id);
            pr.setInt(2,d_id);
            pr.setDate(3, Date.valueOf(a_date));

            int res=pr.executeUpdate();
                System.out.println(res);


            if (res > 0 && checkavailable(d_id,a_date,cn))
            {
                System.out.println("appoinments book successfully");
            }
            else
            {
                System.out.println("please select another date for booking");
            }
            }
            catch (Exception e)
            {
                System.out.println(e.getMessage()+"hoo");
            }
        }

        else
        {
            System.out.println("please select another date for booking");
        }


    }
    else
    {
        System.out.println("wrong doctor or patient id");
    }

}


public static   boolean checkavailable(int d_id,String app_date,Connection cn)
{

    String q="select count(*) from appoinments where d_id=? and appoinment_date=? ";
    try {
        PreparedStatement pr= cn.prepareStatement(q);
        pr.setInt(1,d_id);
        pr.setDate(2, Date.valueOf(app_date));
        ResultSet res=pr.executeQuery();

        if (res.next())
        {
            int count=res.getInt(1);
            if (count==0)
            {
                return  true;
            }
            else
            {
                return  false;
            }
        }

    }
    catch (Exception e)
    {
        System.out.println(e.getMessage());
    }

    return false;
}

}

