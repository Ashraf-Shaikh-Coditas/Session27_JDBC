package Week6.Session27_JDBC;

import java.sql.*;
import java.util.Formatter;

public class PreparedAndCallableDemo {
    public static void main(String[] args) {
        Connection con;
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/demodb","root","root");
            String query = "INSERT INTO Student VALUES(?,?)";

            PreparedStatement pst =con.prepareStatement(query);
            pst.setInt(1,1);
            pst.setString(2,"Ashraf");
            pst.execute();
            pst.setInt(1,2);
            pst.setString(2,"Eoin");
            pst.execute();
            pst.setInt(1,3);
            pst.setString(2,"Sam");
            pst.execute();
            pst.setInt(1,4);
            pst.setString(2,"Tom");
            pst.execute();
            pst.setInt(1,5);
            pst.setString(2,"Alex");
            pst.execute();

            CallableStatement stmt=con.prepareCall("{call insertTuple(?,?)}");  // inserts value into the table
            stmt.setInt(1,6);
            stmt.setString(2,"Jack");
            stmt.execute();

            stmt = con.prepareCall("{call getSumOfIds(?)}");   // calculate sum of all ids
            stmt.setInt(1,1);
            stmt.execute();

            stmt = con.prepareCall("{call getSid(?,?)}");      // returns id of Input name
            stmt.setInt(1,1);
            stmt.setString(2,"Sam");
            stmt.execute();




        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}

/*  USING IN

 delimiter $
mysql> create procedure insertTuple(IN num int,IN str varchar(25))
    -> begin
    -> insert into Student values(num,str);
    -> end $
*/

/* USING OUT
    create procedure getSumOfIds(OUT sum int)
    -> begin
    -> select sum(id) from Student;
    -> end $
*/

/* USING INOUT
    create procedure getSid(INOUT sid int ,IN sname varchar(10))
    -> begin
    ->  select id from Student where name = sname;
    -> end
    -> $

* */