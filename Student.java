package Week6.Session27_JDBC;

import java.sql.*;
import java.util.Formatter;

public class Student {
    public static void main(String[] args) {
        Connection con;
        Statement st;
        ResultSet rs;
        Formatter fmt = new Formatter();

        try {

            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/demodb", "root", "root");
            st = con.createStatement();

            String sql;


            sql = "insert into Students(name,year,percentage,city) values('Ashraf','BE',92,'Jalgaon');";
            st.execute(sql);
            sql = " insert into Students(name,year,percentage,city) values('Swaraj','TE',56,'Pune');";
            st.execute(sql);
            sql = "insert into Students(name,year,percentage,city) values('Hussain','TE',78,'Pune')";
            st.execute(sql);
            sql = "insert into Students(name,year,percentage,city) values('Ajay','BE',87,'Ahmednagar');";
            st.execute(sql);
            sql = "insert into Students(name,year,percentage,city) values('Sarthak','SE',90,'Ahmednagar');";
            st.execute(sql);
            sql = "insert into Students(name,year,percentage,city) values('Dhiraj','FE',90,'Pune');";
            st.execute(sql);
            sql = " insert into Students(name,year,percentage,city) values('Harshal','FE',74,'Pune');";
            st.execute(sql);
            sql = "insert into Students(name,year,percentage,city) values('Allaudin','BE',97,'Mumbai');";
            st.execute(sql);
            sql = "insert into Students(name,year,percentage,city) values('Azim','BE',78,'Kolkata');";
            st.execute(sql);
            sql = "insert into Students(name,year,percentage,city) values('Shubham','SE',58,'Kolkata');";
            st.execute(sql);
            sql = "insert into Students(name,year,percentage,city) values('Sam','SE',98,'Pune');";
            st.execute(sql);
            sql = "insert into Students(name,year,percentage,city) values('Tejas','TE',80,'Pune');";
            st.execute(sql);

            sql = "SELECT * FROM Students ";
            rs = st.executeQuery(sql);

            System.out.println("-------------------------PRINING STUDENTS TABLE -------------------------------");
            fmt.format("%13s %13s %11s %11s %13s\n", "ID", "Name", "Year", "Percentage", "City");
            while (rs.next()) {
                fmt.format("%13s %13s %11s %11s %13s \n", rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getInt(4), rs.getString(5));
            }
            System.out.println(fmt);


//             CASE 1 : ADD GENDER COLUMN USING ALTER
            sql = "ALTER TABLE Students ADD gender varchar(2)";
            st.executeUpdate(sql);

            sql = "UPDATE Students SET gender='M'";
            st.executeUpdate(sql);
            sql = "SELECT * FROM Students ";
            rs = st.executeQuery(sql);

            System.out.println("-------------------------AFTER ADDING GENDER COLUMN  -------------------------------");
            fmt.format("%13s %13s %11s %11s %13s %11s\n", "ID", "Name", "Year", "Percentage", "City", "Gender");
            while (rs.next()) {
                fmt.format("%13s %13s %11s %11s %13s %11s\n", rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6));
            }
            System.out.println(fmt);


//            // CASE 2  : Year = TE and percentage > 70
            sql = "SELECT * FROM Students WHERE year='TE' && percentage > 70 ";
            rs = st.executeQuery(sql);
            System.out.println("------------------------- YEAR = TE && percentage > 70  -------------------------------");
            fmt.format("%13s %13s %11s %11s %13s %11s\n", "ID", "Name", "Year", "Percentage", "City", "Gender");
            while (rs.next()) {
                fmt.format("%13s %13s %11s %11s %13s %11s\n", rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6));
            }
            System.out.println(fmt);

//            // CASE 3 : Records between range 1 to 10
            sql = "SELECT * FROM Students where id BETWEEN 1 AND 10";
            rs = st.executeQuery(sql);
            System.out.println("------------------------- RECORDS HAVING ID 1 TO 10  -------------------------------");
            fmt.format("%13s %13s %11s %11s %13s %11s\n", "ID", "Name", "Year", "Percentage", "City", "Gender");
            while (rs.next()) {
                fmt.format("%13s %13s %11s %11s %13s %11s\n", rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6));
            }
            System.out.println(fmt);


//            // CASE 4.1 : Add 3 Records .
            PreparedStatement pst = con.prepareStatement("INSERT INTO Students(name,year,percentage,city,gender)" +
                    " VALUES(?,?,?,?,?)");
            pst.setString(1, "Aradhna");
            pst.setString(2, "BE");
            pst.setInt(3, 98);
            pst.setString(4, "Pune");
            pst.setString(5, "F");
            pst.execute();

            pst.setString(1, "Rohini");
            pst.setString(2, "BE");
            pst.setInt(3, 89);
            pst.setString(4, "Pune");
            pst.setString(5, "F");
            pst.execute();

            pst.setString(1, "Ashwini");
            pst.setString(2, "BE");
            pst.setInt(3, 90);
            pst.setString(4, "Mumbai");
            pst.setString(5, "F");
            pst.execute();

            sql = "SELECT * FROM Students ";
            rs = st.executeQuery(sql);

            System.out.println("-------------------------AFTER ADDING 3 Records   -------------------------------");
            fmt.format("%13s %13s %11s %11s %13s %11s\n", "ID", "Name", "Year", "Percentage", "City", "Gender");
            while (rs.next()) {
                fmt.format("%13s %13s %11s %11s %13s %11s\n", rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6));
            }
            System.out.println(fmt);


            // Case 4.2 : Update Names.

            sql = "UPDATE Students SET name = (SELECT concat('Mr. ',name)) WHERE gender = 'M'";
            st.executeUpdate(sql);
            sql = "UPDATE Students SET name = (SELECT concat('Ms. ',name)) WHERE gender = 'F'";
            st.executeUpdate(sql);

            sql = "SELECT * FROM Students ";
            rs = st.executeQuery(sql);

            System.out.println("-------------------------AFTER UPDATING NAMES in  Records   -------------------------------");
            fmt.format("%13s %13s %11s %11s %13s %11s\n", "ID", "Name", "Year", "Percentage", "City", "Gender");
            while (rs.next()) {
                fmt.format("%13s %13s %11s %11s %13s %11s\n", rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6));
            }
            System.out.println(fmt);

            // CASE 4.3 : Remove students in BE

            sql = "DELETE FROM Students WHERE year='BE'";
            st.executeUpdate(sql);

            sql = "SELECT * FROM Students ";
            rs = st.executeQuery(sql);
            System.out.println("-------------------------AFTER DELETING BE STUDENTS DATA   -------------------------------");
            fmt.format("%13s %13s %11s %11s %13s %11s\n", "ID", "Name", "Year", "Percentage", "City", "Gender");
            while (rs.next()) {
                fmt.format("%13s %13s %11s %11s %13s %11s\n", rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getInt(4), rs.getString(5), rs.getString(6));
            }
            System.out.println(fmt);


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}

/*
-------------------------PRINING STUDENTS TABLE -------------------------------
           ID          Name        Year  Percentage          City
            1        Ashraf          BE          92       Jalgaon
            2        Swaraj          TE          56          Pune
            3       Hussain          TE          78          Pune
            4          Ajay          BE          87    Ahmednagar
            5       Sarthak          SE          90    Ahmednagar
            6        Dhiraj          FE          90          Pune
            7       Harshal          FE          74          Pune
            8      Allaudin          BE          97        Mumbai
            9          Azim          BE          78       Kolkata
           10       Shubham          SE          58       Kolkata
           11           Sam          SE          98          Pune
           12         Tejas          TE          80          Pune

-------------------------AFTER ADDING GENDER COLUMN  -------------------------------
           ID          Name        Year  Percentage          City      Gender
            1        Ashraf          BE          92       Jalgaon           M
            2        Swaraj          TE          56          Pune           M
            3       Hussain          TE          78          Pune           M
            4          Ajay          BE          87    Ahmednagar           M
            5       Sarthak          SE          90    Ahmednagar           M
            6        Dhiraj          FE          90          Pune           M
            7       Harshal          FE          74          Pune           M
            8      Allaudin          BE          97        Mumbai           M
            9          Azim          BE          78       Kolkata           M
           10       Shubham          SE          58       Kolkata           M
           11           Sam          SE          98          Pune           M
           12         Tejas          TE          80          Pune           M


------------------------- YEAR = TE && percentage > 70  -------------------------------
           ID          Name        Year  Percentage          City      Gender
            3       Hussain          TE          78          Pune           M
           12         Tejas          TE          80          Pune           M

------------------------- RECORDS HAVING ID 1 TO 10  -------------------------------
           ID          Name        Year  Percentage          City      Gender
            1        Ashraf          BE          92       Jalgaon           M
            2        Swaraj          TE          56          Pune           M
            3       Hussain          TE          78          Pune           M
            4          Ajay          BE          87    Ahmednagar           M
            5       Sarthak          SE          90    Ahmednagar           M
            6        Dhiraj          FE          90          Pune           M
            7       Harshal          FE          74          Pune           M
            8      Allaudin          BE          97        Mumbai           M
            9          Azim          BE          78       Kolkata           M
           10       Shubham          SE          58       Kolkata           M

-------------------------AFTER ADDING 3 Records   -------------------------------
           ID          Name        Year  Percentage          City      Gender
            1        Ashraf          BE          92       Jalgaon           M
            2        Swaraj          TE          56          Pune           M
            3       Hussain          TE          78          Pune           M
            4          Ajay          BE          87    Ahmednagar           M
            5       Sarthak          SE          90    Ahmednagar           M
            6        Dhiraj          FE          90          Pune           M
            7       Harshal          FE          74          Pune           M
            8      Allaudin          BE          97        Mumbai           M
            9          Azim          BE          78       Kolkata           M
           10       Shubham          SE          58       Kolkata           M
           11           Sam          SE          98          Pune           M
           12         Tejas          TE          80          Pune           M
           13       Aradhna          BE          98          Pune           F
           14        Rohini          BE          89          Pune           F
           15       Ashwini          BE          90        Mumbai           F


-------------------------AFTER UPDATING NAMES in  Records   -------------------------------
           ID          Name        Year  Percentage          City      Gender
            1    Mr. Ashraf          BE          92       Jalgaon           M
            2    Mr. Swaraj          TE          56          Pune           M
            3   Mr. Hussain          TE          78          Pune           M
            4      Mr. Ajay          BE          87    Ahmednagar           M
            5   Mr. Sarthak          SE          90    Ahmednagar           M
            6    Mr. Dhiraj          FE          90          Pune           M
            7   Mr. Harshal          FE          74          Pune           M
            8  Mr. Allaudin          BE          97        Mumbai           M
            9      Mr. Azim          BE          78       Kolkata           M
           10   Mr. Shubham          SE          58       Kolkata           M
           11       Mr. Sam          SE          98          Pune           M
           12     Mr. Tejas          TE          80          Pune           M
           13   Ms. Aradhna          BE          98          Pune           F
           14    Ms. Rohini          BE          89          Pune           F
           15   Ms. Ashwini          BE          90        Mumbai           F



-------------------------AFTER DELETING BE STUDENTS DATA   -------------------------------
           ID          Name        Year  Percentage          City      Gender
            2    Mr. Swaraj          TE          56          Pune           M
            3   Mr. Hussain          TE          78          Pune           M
            5   Mr. Sarthak          SE          90    Ahmednagar           M
            6    Mr. Dhiraj          FE          90          Pune           M
            7   Mr. Harshal          FE          74          Pune           M
           10   Mr. Shubham          SE          58       Kolkata           M
           11       Mr. Sam          SE          98          Pune           M
           12     Mr. Tejas          TE          80          Pune           M

 * */