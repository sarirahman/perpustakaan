/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package program.peminjaman;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author myTeam
 */
public class Form extends javax.swing.JFrame {

    /**
     * Creates new form Form
     */
    private DefaultTableModel model;
    public Form() throws SQLException {
        initComponents();
        setDate_pinjam();
        setDate_kembali();
        
        model = new DefaultTableModel();
        tabelPeminjaman.setModel(model);
        model.addColumn("Tgl Peminjaman");
        model.addColumn("Tgl Pengembalian");
        model.addColumn("Nim");
        model.addColumn("Isbn");
        model.addColumn("Ket");
        
        loadData();
    }
    
    public String toddMMyy(Date day){
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String date = formatter.format(day);
        return date;
    }

    public void setDate_pinjam(){
        Date today = new Date();
        jLabel6.setText(toddMMyy(today));
    }
    
    public void setDate_kembali(){
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 3);
        Date tgl_kembali = cal.getTime();
        jLabel7.setText(toddMMyy(tgl_kembali)); 
    }
    
    public void delete_field(){
        inputNim.setText("");
        inputIsbn1.setText("");
    }
    
    public void loadData() throws SQLException{
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        Connection c = database_connection.getKoneksi();
        
        String data_pinjam = "select * from peminjaman";
        PreparedStatement ps = c.prepareStatement(data_pinjam);
        ResultSet resultSet = ps.executeQuery();
        
        while(resultSet.next()){//menelusuri data
                Object[] o = new Object[5];
                o[0] = resultSet.getString("tgl_pinjam");
                o[1] = resultSet.getString("tgl_kembali");
                o[2] = resultSet.getString("nim");
                o[3] = resultSet.getString("isbn");
                o[4] = resultSet.getString("keterangan");
                model.addRow(o);
            }
        
    }
    
    private int cekData_Mahasiswa(String nim) throws SQLException{
        String data_mhs;
        int berhasil;
        //koneksi ke database
        Connection c = database_connection.getKoneksi();
        //memililih tabel mahasiswa
        data_mhs = "select nim from mahasiswa where nim = ?";
        PreparedStatement ps = c.prepareStatement(data_mhs);
        ps.setString(1, nim);
        //hasil pencarian disimpan di resultset
        ResultSet resultSet = ps.executeQuery();
        //mengecek keberadaan data yang dicari
        if(resultSet.next())
            return 1;
        else
            return 0;
    }
    
    private int cekData_Buku(String isbn) throws SQLException{
        String data_buku;
        int berhasil;
        Connection c = database_connection.getKoneksi();
        data_buku = "select isbn from buku where isbn = ?";
        PreparedStatement ps = c.prepareStatement(data_buku);
        ps.setString(1, isbn);
        ResultSet resultSet = ps.executeQuery();
        if(resultSet.next())
            return 1;
        else
            return 0;
    }
    
    public void db_insert(){
        String nim, isbn, tgl_pinjam, tgl_kembali;
        nim = inputNim.getText();
        isbn = inputIsbn1.getText();
        tgl_pinjam = jLabel6.getText();
        tgl_kembali = jLabel7.getText();
        
        try {
            Connection c = database_connection.getKoneksi();
            int cek_mhs, cek_buku;
            cek_mhs = cekData_Mahasiswa(nim);
            cek_buku = cekData_Buku(isbn);
            if(cek_mhs == 1 && cek_buku == 1){
                String sql = "insert into peminjaman (nim, isbn, tgl_pinjam, tgl_kembali) values (?,?,?,?)";
                PreparedStatement statement =  c.prepareStatement(sql);
                statement.setString(1, nim);
                statement.setString(2, isbn);
                statement.setString(3, tgl_pinjam);
                statement.setString(4, tgl_kembali);
                statement.execute();
                loadData();
                JOptionPane.showMessageDialog(null,"Success","Display Message",JOptionPane.INFORMATION_MESSAGE);
                //c.close();
            }
            else
                JOptionPane.showMessageDialog(null,"Failed","Error Message",JOptionPane.ERROR_MESSAGE);
            
            }
        catch (SQLException t){
                System.out.println(t.getMessage());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFormattedTextField1 = new javax.swing.JFormattedTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        inputNim = new javax.swing.JTextField();
        inputIsbn1 = new javax.swing.JTextField();
        buttonPinjam = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelPeminjaman = new javax.swing.JTable();

        jFormattedTextField1.setText("jFormattedTextField1");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jLabel1.setText("Peminjaman Buku Perpustakaan UNSRI");

        jLabel2.setText("NIM :");

        jLabel3.setText("ISBN :");

        jLabel4.setText("Tgl Peminjaman :");

        jLabel5.setText("Tgl Pengembalian :");

        inputNim.setToolTipText("");
        inputNim.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inputNimActionPerformed(evt);
            }
        });
        inputNim.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                inputNimKeyTyped(evt);
            }
        });

        inputIsbn1.setToolTipText("");
        inputIsbn1.setPreferredSize(new java.awt.Dimension(6, 23));

        buttonPinjam.setText("Pinjam");
        buttonPinjam.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPinjamActionPerformed(evt);
            }
        });

        jLabel6.setText("jLabel6");

        jLabel7.setText("jLabel7");

        tabelPeminjaman.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tabelPeminjaman.setName(""); // NOI18N
        jScrollPane2.setViewportView(tabelPeminjaman);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(120, 120, 120))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonPinjam)
                    .addComponent(jLabel7)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(inputIsbn1, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(inputNim, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel6))
                .addGap(76, 76, 76))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(inputNim, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addGap(9, 9, 9))
                    .addComponent(inputIsbn1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(buttonPinjam)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void inputNimActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inputNimActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inputNimActionPerformed

    private void buttonPinjamActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonPinjamActionPerformed
        // TODO add your handling code here:
        db_insert();
        delete_field();
    }//GEN-LAST:event_buttonPinjamActionPerformed

    private void inputNimKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_inputNimKeyTyped
        // TODO add your handling code here:
        char vchar=evt.getKeyChar();
        if(!(Character.isDigit(vchar)) || (vchar==KeyEvent.VK_BACK_SPACE) || (vchar==KeyEvent.VK_ENTER))
            evt.consume();
    }//GEN-LAST:event_inputNimKeyTyped

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Form.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        //database_connection dbc = new database_connection();
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Form().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(Form.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonPinjam;
    private javax.swing.JTextField inputIsbn1;
    private javax.swing.JTextField inputNim;
    private javax.swing.JFormattedTextField jFormattedTextField1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable tabelPeminjaman;
    // End of variables declaration//GEN-END:variables
}
