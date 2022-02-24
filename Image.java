package Week6.Session27_JDBC;

import java.io.*;
import java.sql.*;

public class Image {
    public static void main(String[] args) {
        Connection con;
        PreparedStatement pst;

        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/demodb", "root", "root");
            pst = con.prepareStatement("INSERT INTO Image VALUES(?,?)");
            pst.setString(1,"Image1");

            FileInputStream fin = new FileInputStream("src/Week6/Session27_JDBC/demoImage.png");
            pst.setBinaryStream(2,fin);

           pst.executeUpdate();

            pst = con.prepareStatement("SELECT * FROM Image");
            ResultSet rs = pst.executeQuery();

            if(rs.next()) {
                Blob b = rs.getBlob(2);
                byte barr[] = b.getBytes(1,(int)b.length());

                FileOutputStream fout = new FileOutputStream("src/Week6/Session27_JDBC/ResultImage.png");
                fout.write(barr);

                fout.close();
            }




        } catch (SQLException | FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

/*
 File inserted originally : demoImage.png

 Retrieved File : ResultImage.png
* */
