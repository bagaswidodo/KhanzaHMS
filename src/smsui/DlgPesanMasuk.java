/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DlgPesanMasuk.java
 *
 * Created on 20 Jun 10, 18:05:00
 */

package smsui;

import java.awt.Dimension;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author perpustakaan
 */
public class DlgPesanMasuk extends javax.swing.JDialog {
    private Connection koneksi;

    /** Creates new form DlgPesanMasuk */
    public DlgPesanMasuk(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocation(10,10);
        setSize(545,600);
        tbPesan.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbPesan.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
    }

    class TabelModel extends AbstractTableModel{
        String[] kolomHeader = {"Id",
                                "SMS ",
                                "No.HP",
                                "PDU",
                                "Encoding",
                                "Id Gtateway",
                                "Tanggal&Jam SMS",
                                "SMS Balasan",
                                "Stts.SMS"};
        Object[][] data;

        public void addData(Object[][] obj){
            data = obj;
            fireTableDataChanged();
        }

        public int getRowCount() {
            return data.length;
        }

        public int getColumnCount() {
            return kolomHeader.length;
        }

        public Object getValueAt(int rowIndex, int columnIndex) {
            return data[rowIndex][columnIndex];
        }

        @Override
        public String getColumnName(int column) {
            return kolomHeader[column];
        }
        
    }

    public void setKoneksi(Connection koneksi){
        this.koneksi = koneksi;
        try {
            String sql = "SELECT * FROM sms";
            PreparedStatement prepare =koneksi.prepareStatement(sql);
            ResultSet res = prepare.executeQuery();
            res.last();
            int n = res.getRow();

            Object[][] data = new Object[n][9];
            int p = 0;

            res.beforeFirst();
            while (res.next()) {
                data[p][0] = res.getInt(1);
                data[p][1] = res.getString(2);
                data[p][2] = res.getString(3);
                data[p][3] = res.getString(4);
                data[p][4] = res.getString(5);
                data[p][5] = res.getString(6);
                data[p][6] = res.getDate(7);
                data[p][7] = res.getString(8);
                data[p][8] = res.getString(9);
                p++;
            }

            TabelModel model = new TabelModel();
            model.addData(data);
            tbPesan.setModel(model);
            tbPesan.getColumnModel().getColumn(0).setPreferredWidth(120);
            tbPesan.getColumnModel().getColumn(1).setPreferredWidth(355);
            tbPesan.getColumnModel().getColumn(2).setPreferredWidth(120);
            tbPesan.getColumnModel().getColumn(3).setPreferredWidth(355);
            tbPesan.getColumnModel().getColumn(4).setPreferredWidth(120);
            tbPesan.getColumnModel().getColumn(5).setPreferredWidth(120);
            tbPesan.getColumnModel().getColumn(6).setPreferredWidth(120);
            tbPesan.getColumnModel().getColumn(7).setPreferredWidth(355);
            tbPesan.getColumnModel().getColumn(8).setPreferredWidth(120);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        internalFrame1 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbPesan = new widget.Table();
        panelJudul1 = new widget.PanelJudul();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        panel1 = new widget.Panel();
        jLabel5 = new javax.swing.JLabel();
        button1 = new widget.Button();
        jSeparator2 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setModal(true);
        setResizable(false);
        setUndecorated(true);

        internalFrame1.setBorder(null);
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(185, 185, 185)));
        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbPesan.setAutoCreateRowSorter(true);
        tbPesan.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbPesan.setName("tbPesan"); // NOI18N
        Scroll.setViewportView(tbPesan);

        internalFrame1.add(Scroll, java.awt.BorderLayout.CENTER);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        panelJudul1.setName("panelJudul1"); // NOI18N
        panelJudul1.setPreferredSize(new java.awt.Dimension(440, 40));
        panelJudul1.setLayout(new java.awt.BorderLayout());

        jLabel1.setBackground(java.awt.Color.darkGray);
        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 3, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(254, 184, 254));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText(":: DATA PESAN MASUK ::        ");
        jLabel1.setIconTextGap(2);
        jLabel1.setName("jLabel1"); // NOI18N
        panelJudul1.add(jLabel1, java.awt.BorderLayout.CENTER);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/file-edit-32x32.png"))); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N
        panelJudul1.add(jLabel2, java.awt.BorderLayout.WEST);

        getContentPane().add(panelJudul1, java.awt.BorderLayout.PAGE_START);

        panel1.setName("panel1"); // NOI18N
        panel1.setPreferredSize(new java.awt.Dimension(440, 27));
        panel1.setLayout(new java.awt.BorderLayout());

        jLabel5.setFont(new java.awt.Font("Comic Sans MS", 1, 11));
        jLabel5.setForeground(new java.awt.Color(180, 180, 180));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setName("jLabel5"); // NOI18N
        panel1.add(jLabel5, java.awt.BorderLayout.CENTER);

        button1.setForeground(new java.awt.Color(254, 184, 254));
        button1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/smsimage/control_power.png"))); // NOI18N
        button1.setMnemonic('C');
        button1.setText("Close");
        button1.setToolTipText("Alt+C");
        button1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        button1.setFont(new java.awt.Font("Comic Sans MS", 1, 12)); // NOI18N
        button1.setName("button1"); // NOI18N
        button1.setPreferredSize(new java.awt.Dimension(100, 27));
        button1.setRoundRect(false);
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });
        panel1.add(button1, java.awt.BorderLayout.LINE_END);

        jSeparator2.setBackground(new java.awt.Color(0, 0, 51));
        jSeparator2.setForeground(new java.awt.Color(0, 0, 51));
        jSeparator2.setName("jSeparator2"); // NOI18N
        jSeparator2.setPreferredSize(new java.awt.Dimension(1, 1));
        panel1.add(jSeparator2, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(panel1, java.awt.BorderLayout.PAGE_END);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
        dispose();
}//GEN-LAST:event_button1ActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DlgPesanMasuk dialog = new DlgPesanMasuk(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private widget.ScrollPane Scroll;
    private widget.Button button1;
    private widget.InternalFrame internalFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JSeparator jSeparator2;
    private widget.Panel panel1;
    private widget.PanelJudul panelJudul1;
    private widget.Table tbPesan;
    // End of variables declaration//GEN-END:variables

}
