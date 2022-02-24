package Week6.Session27_JDBC;

import java.sql.*;
import java.util.Formatter;

public class Employee {
    public static void main(String[] args) {
        Connection con;
        Statement st;
        ResultSet rs;
        Formatter fmt = new Formatter();

        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/demodb", "root", "root");
            st = con.createStatement();

            String sql;
            // CASE 1 : ADD DATA IN TABLE .
            sql = "INSERT INTO Employee(name,designation,doj,exp,salary,status) VALUES(" +
                    "'Ashraf','Java Developer','2022-01-01',0,12000,'working')";
            st.execute(sql);
            sql = "INSERT INTO Employee(name,designation,doj,exp,salary,status) VALUES(" +
                    "'Eoin','Java Developer','2021-01-01',1,21000,'working')";
            st.execute(sql);
            sql = "INSERT INTO Employee(name,designation,doj,exp,salary,status) VALUES(" +
                    "'Tom','Python Developer','2011-01-01',10,21000,'working')";
            st.execute(sql);
            sql = "INSERT INTO Employee(name,designation,doj,exp,salary,status) VALUES(" +
                    "'Alex','Angular Developer','2012-01-01',11,25000,'left')";
            st.execute(sql);
            sql = "INSERT INTO Employee(name,designation,doj,exp,salary,status) VALUES(" +
                    "'Jason','Java Developer','2015-01-01',9,22000,'working')";
            st.execute(sql);
            sql = "INSERT INTO Employee(name,designation,doj,exp,salary,status) VALUES(" +
                    "'Chris','Python Developer','2022-01-01',0,10000,'working')";
            st.execute(sql);
            sql = "INSERT INTO Employee(name,designation,doj,exp,salary,status) VALUES(" +
                    "'Andre','React Developer','2020-01-01',2,21000,'left')";
            st.execute(sql);
            sql = "INSERT INTO Employee(name,designation,doj,exp,salary,status) VALUES(" +
                    "'Ben','Java Developer','2011-01-01',10,21000,'working')";
            st.execute(sql);
            sql = "INSERT INTO Employee(name,designation,doj,exp,salary,status) VALUES(" +
                    "'Jerry','Python Developer','2018-01-01',4,15000,'working')";
            st.execute(sql);
            sql = "INSERT INTO Employee(name,designation,doj,exp,salary,status) VALUES(" +
                    "'Loyd','Python Developer','2022-01-01',2,21000,'left')";
            st.execute(sql);


            System.out.println("----------------EMPLOYEE DATA --------------------------------");
            sql = "SELECT * FROM Employee";
            rs = st.executeQuery(sql);
            fmt.format("%13s %13s %20s %15s %15s %15s %15s\n", "ID", "Name", "Designation", "DOJ", "EXP", "Salary", "Status");
            while (rs.next()) {
                fmt.format("%13s %13s %20s %15s %15s %15s %15s\n", rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(6),
                        rs.getString(7));
            }
            System.out.println(fmt);

            // CASE 3 : Alter table , ADD city column
            sql = "ALTER TABLE Employee ADD city varchar(10)";
            st.executeUpdate(sql);

            PreparedStatement pst = con.prepareStatement("UPDATE Employee SET city = ? WHERE id = ? ");
            pst.setString(1, "Jalgaon");
            pst.setInt(2, 1);
            pst.execute();

            pst.setString(1, "Pune");
            pst.setInt(2, 2);
            pst.execute();

            pst.setString(1, "Pune");
            pst.setInt(2, 3);
            pst.executeUpdate();

            pst.setString(1, "Mumbai");
            pst.setInt(2, 4);
            pst.executeUpdate();

            pst.setString(1, "Pune");
            pst.setInt(2, 5);
            pst.executeUpdate();

            pst.setString(1, "Pune");
            pst.setInt(2, 6);
            pst.executeUpdate();

            pst.setString(1, "Jalgaon");
            pst.setInt(2, 7);
            pst.executeUpdate();

            pst.setString(1, "Pune");
            pst.setInt(2, 8);
            pst.executeUpdate();

            pst.setString(1, "Nashik");
            pst.setInt(2, 9);
            pst.executeUpdate();

            pst.setString(1, "Pune");
            pst.setInt(2, 10);
            pst.executeUpdate();

            pst.setString(1, "Pune");
            pst.setInt(2, 11);
            pst.executeUpdate();

            pst.setString(1, "Mumbai");
            pst.setInt(2, 12);
            pst.executeUpdate();

            sql = "SELECT * FROM Employee";
            rs = st.executeQuery(sql);
            fmt.format("%13s %13s %20s %15s %15s %15s %15s %15s\n", "ID", "Name", "Designation", "DOJ", "EXP", "Salary", "Status",
                    "City");
            System.out.println("----------------AFTER ADDING CITY COLUMN  DATA --------------------------------");
            while (rs.next()) {
                fmt.format("%13s %13s %20s %15s %15s %15s %15s %15s\n", rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(6),
                        rs.getString(7), rs.getString(8));
            }
            System.out.println(fmt);

            // CASE 4 : Fetch only records from pune and Salary > 20000

            sql = "SELECT * FROM Employee WHERE city='Pune' && salary > 20000";
            rs = st.executeQuery(sql);
            fmt.format("%13s %13s %20s %15s %15s %15s %15s %15s\n", "ID", "Name", "Designation", "DOJ", "EXP", "Salary", "Status",
                    "City");
            System.out.println("----------------city='Pune' && salary > 20000 --------------------------------");
            while (rs.next()) {
                fmt.format("%13s %13s %20s %15s %15s %15s %15s %15s\n", rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(6),
                        rs.getString(7), rs.getString(8));
            }
            System.out.println(fmt);

//             CASE 5 UPDATE SALARY BY ADDING 1000 WHOSE EXP > 3
            sql = "UPDATE Employee SET salary = salary+1000 where exp > 3";
            st.executeUpdate(sql);

            sql = "SELECT * FROM Employee ";
            rs = st.executeQuery(sql);
            fmt.format("%13s %13s %20s %15s %15s %15s %15s %15s\n", "ID", "Name", "Designation", "DOJ", "EXP", "Salary", "Status",
                    "City");
            System.out.println("----------------CASE 5 UPDATE SALARY BY ADDING 1000 WHOSE EXP > 3 --------------------------------");
            while (rs.next()) {
                fmt.format("%13s %13s %20s %15s %15s %15s %15s %15s\n", rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(6),
                        rs.getString(7), rs.getString(8));
            }
            System.out.println(fmt);


            // CASE 6 :
            sql = "DELETE FROM Employee WHERE status='left'";
            st.execute(sql);

            sql = "SELECT * FROM Employee ";
            rs = st.executeQuery(sql);
            fmt.format("%13s %13s %20s %15s %15s %15s %15s %15s\n", "ID", "Name", "Designation", "DOJ", "EXP", "Salary", "Status",
                    "City");
            System.out.println("----------------CASE 6 AFTER DELETING LEFT EMPLOYEES  --------------------------------");
            while (rs.next()) {
                fmt.format("%13s %13s %20s %15s %15s %15s %15s %15s\n", rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(6),
                        rs.getString(7), rs.getString(8));
            }
            System.out.println(fmt);

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}

/*

----------------EMPLOYEE DATA --------------------------------
           ID          Name          Designation             DOJ             EXP          Salary          Status
            1        Ashraf       Java Developer      2022-01-01               0           12000         working
            2          Eoin       Java Developer      2021-01-01               1           21000         working
            3           Tom     Python Developer      2011-01-01              10           21000         working
            4          Alex    Angular Developer      2012-01-01              11           25000            left
            5         Jason       Java Developer      2015-01-01               9           22000         working
            6         Chris     Python Developer      2022-01-01               0           10000         working
            7         Andre      React Developer      2020-01-01               2           21000            left
            8           Ben       Java Developer      2011-01-01              10           21000         working
            9         Jerry     Python Developer      2018-01-01               4           15000         working
           10          Loyd     Python Developer      2022-01-01               2           21000            left


----------------AFTER ADDING CITY COLUMN  DATA --------------------------------
           ID          Name          Designation             DOJ             EXP          Salary          Status            City
            1        Ashraf       Java Developer      2022-01-01               0           12000         working         Jalgaon
            2          Eoin       Java Developer      2021-01-01               1           21000         working            Pune
            3           Tom     Python Developer      2011-01-01              10           21000         working            Pune
            4          Alex    Angular Developer      2012-01-01              11           25000            left          Mumbai
            5         Jason       Java Developer      2015-01-01               9           22000         working            Pune
            6         Chris     Python Developer      2022-01-01               0           10000         working            Pune
            7         Andre      React Developer      2020-01-01               2           21000            left         Jalgaon
            8           Ben       Java Developer      2011-01-01              10           21000         working            Pune
            9         Jerry     Python Developer      2018-01-01               4           15000         working          Nashik
           10          Loyd     Python Developer      2022-01-01               2           21000            left            Pune

----------------city='Pune' && salary > 20000 --------------------------------
           ID          Name          Designation             DOJ             EXP          Salary          Status            City
            2          Eoin       Java Developer      2021-01-01               1           21000         working            Pune
            3           Tom     Python Developer      2011-01-01              10           21000         working            Pune
            5         Jason       Java Developer      2015-01-01               9           22000         working            Pune
            8           Ben       Java Developer      2011-01-01              10           21000         working            Pune
           10          Loyd     Python Developer      2022-01-01               2           21000            left            Pune

----------------CASE 5 UPDATE SALARY BY ADDING 1000 WHOSE EXP > 3 --------------------------------
           ID          Name          Designation             DOJ             EXP          Salary          Status            City
            1        Ashraf       Java Developer      2022-01-01               0           12000         working         Jalgaon
            2          Eoin       Java Developer      2021-01-01               1           21000         working            Pune
            3           Tom     Python Developer      2011-01-01              10           22000         working            Pune
            4          Alex    Angular Developer      2012-01-01              11           26000            left          Mumbai
            5         Jason       Java Developer      2015-01-01               9           23000         working            Pune
            6         Chris     Python Developer      2022-01-01               0           10000         working            Pune
            7         Andre      React Developer      2020-01-01               2           21000            left         Jalgaon
            8           Ben       Java Developer      2011-01-01              10           22000         working            Pune
            9         Jerry     Python Developer      2018-01-01               4           16000         working          Nashik
           10          Loyd     Python Developer      2022-01-01               2           21000            left            Pune


----------------CASE 6 AFTER DELETING LEFT EMPLOYEES  --------------------------------
           ID          Name          Designation             DOJ             EXP          Salary          Status            City
            1        Ashraf       Java Developer      2022-01-01               0           12000         working         Jalgaon
            2          Eoin       Java Developer      2021-01-01               1           21000         working            Pune
            3           Tom     Python Developer      2011-01-01              10           22000         working            Pune
            5         Jason       Java Developer      2015-01-01               9           23000         working            Pune
            6         Chris     Python Developer      2022-01-01               0           10000         working            Pune
            8           Ben       Java Developer      2011-01-01              10           22000         working            Pune
            9         Jerry     Python Developer      2018-01-01               4           16000         working          Nashik



* */