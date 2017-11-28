/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package program.peminjaman;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author User
 */
public class database_connection {
    private static Connection koneksi;
    // dengan static, pada saat kelas lain akan mengakses kelas ini, tidak harus membuat objek kelas
    public static Connection getKoneksi(){
        if(koneksi == null){
            try {
                String url = "jdbc:mysql://localhost:3306/perpustakaan";
                String user = "root";
                String password = "";
                DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                koneksi = DriverManager.getConnection(url, user, password);
                System.out.println("Berhasil");
            }
            catch (SQLException t){
                System.out.println("Error membuat koneksi");
            }
        } 
        return koneksi;
    }
}
