package khanzahms;
import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

public class DlgKehadiran extends javax.swing.JDialog {
    private Connection connect=koneksiDB.condb();
    private DefaultTableModel tabMode;
    private sekuel Sequel=new sekuel();
    private validasi Valid=new validasi();

    /** Creates new form DlgProgramStudi */
    public DlgKehadiran(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();

        this.setLocation(8,1);
        setSize(885,674);
        Object[] row={"<html><font color='#b95656' face='Tahoma' size='3'>NIK",
                      "<html><font color='#b95656' face='Tahoma' size='3'>Nama",
                      "<html><font color='#b95656' face='Tahoma' size='3'>Departemen",
                      "<html><font color='#b95656' face='Tahoma' size='3'>Kehadiran",
                      "<html><font color='#b95656' face='Tahoma' size='3'>Siang",
                      "<html><font color='#b95656' face='Tahoma' size='3'>Malam",
                      "<html><font color='#b95656' face='Tahoma' size='3'>Terlambat"};
        
        tabMode=new DefaultTableModel(null,row){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };

        tbPegawai.setModel(tabMode);
        tbPegawai.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbPegawai.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 7; i++) {
            TableColumn column = tbPegawai.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(100);
            }else if(i==1){
                column.setPreferredWidth(350);
            }else if(i==2){
                column.setPreferredWidth(100);
            }else{
                column.setPreferredWidth(150);
            }
        }
        tbPegawai.setDefaultRenderer(Object.class, new WarnaTable());
        TCari.setDocument(new batasInput((int)100).getKata(TCari));
        
         loadTahun();
    }
    private Dimension screen=Toolkit.getDefaultToolkit().getScreenSize();

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        internalFrame1 = new widget.InternalFrame();
        jPanel2 = new widget.panelisi();
        jPanel4 = new widget.panelisi();
        label11 = new widget.Label();
        ThnCari = new widget.ComboBox();
        BlnCari = new widget.ComboBox();
        label9 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        label10 = new widget.Label();
        LCount = new widget.Label();
        jPanel5 = new widget.panelisi();
        BtnPrint = new widget.Button();
        BtnAll = new widget.Button();
        BtnKeluar = new widget.Button();
        scrollPane1 = new widget.ScrollPane();
        tbPegawai = new widget.Table();
        panelJudul1 = new widget.PanelJudul();
        jLabel1 = new widget.Label();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setModal(true);
        setResizable(false);
        setUndecorated(true);

        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        jPanel2.setName("jPanel2"); // NOI18N
        jPanel2.setOpaque(false);
        jPanel2.setPreferredSize(new java.awt.Dimension(816, 100));
        jPanel2.setLayout(new java.awt.BorderLayout(1, 1));

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(212, 212, 152)));
        jPanel4.setName("jPanel4"); // NOI18N
        jPanel4.setOpaque(false);
        jPanel4.setPreferredSize(new java.awt.Dimension(100, 44));
        jPanel4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 4, 9));

        label11.setText("Tahun & Bulan :");
        label11.setFont(new java.awt.Font("Tahoma", 0, 12));
        label11.setName("label11"); // NOI18N
        label11.setPreferredSize(new java.awt.Dimension(90, 23));
        jPanel4.add(label11);

        ThnCari.setFont(new java.awt.Font("Tahoma", 0, 12));
        ThnCari.setName("ThnCari"); // NOI18N
        ThnCari.setPreferredSize(new java.awt.Dimension(80, 23));
        jPanel4.add(ThnCari);

        BlnCari.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" }));
        BlnCari.setFont(new java.awt.Font("Tahoma", 0, 12));
        BlnCari.setName("BlnCari"); // NOI18N
        BlnCari.setPreferredSize(new java.awt.Dimension(50, 23));
        jPanel4.add(BlnCari);

        label9.setText("Key Word :");
        label9.setFont(new java.awt.Font("Tahoma", 0, 12));
        label9.setName("label9"); // NOI18N
        label9.setPreferredSize(new java.awt.Dimension(70, 23));
        jPanel4.add(label9);

        TCari.setFont(new java.awt.Font("Tahoma", 0, 12));
        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(250, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        jPanel4.add(TCari);

        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('1');
        BtnCari.setToolTipText("Alt+1");
        BtnCari.setFont(new java.awt.Font("Tahoma", 1, 12));
        BtnCari.setName("BtnCari"); // NOI18N
        BtnCari.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCariActionPerformed(evt);
            }
        });
        BtnCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnCariKeyPressed(evt);
            }
        });
        jPanel4.add(BtnCari);

        label10.setText("Record :");
        label10.setFont(new java.awt.Font("Tahoma", 0, 12));
        label10.setName("label10"); // NOI18N
        label10.setPreferredSize(new java.awt.Dimension(70, 23));
        jPanel4.add(label10);

        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setFont(new java.awt.Font("Tahoma", 0, 12));
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(60, 23));
        jPanel4.add(LCount);

        jPanel2.add(jPanel4, java.awt.BorderLayout.PAGE_START);

        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(212, 212, 152)));
        jPanel5.setName("jPanel5"); // NOI18N
        jPanel5.setOpaque(false);
        jPanel5.setPreferredSize(new java.awt.Dimension(100, 44));
        jPanel5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        BtnPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        BtnPrint.setMnemonic('P');
        BtnPrint.setText("Cetak");
        BtnPrint.setToolTipText("Alt+P");
        BtnPrint.setFont(new java.awt.Font("Tahoma", 1, 12));
        BtnPrint.setName("BtnPrint"); // NOI18N
        BtnPrint.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPrintActionPerformed(evt);
            }
        });
        jPanel5.add(BtnPrint);

        BtnAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll.setMnemonic('2');
        BtnAll.setText("Semua");
        BtnAll.setToolTipText("Alt+2");
        BtnAll.setFont(new java.awt.Font("Tahoma", 1, 12));
        BtnAll.setName("BtnAll"); // NOI18N
        BtnAll.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAllActionPerformed(evt);
            }
        });
        BtnAll.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnAllKeyPressed(evt);
            }
        });
        jPanel5.add(BtnAll);

        BtnKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar.setMnemonic('K');
        BtnKeluar.setText("Keluar");
        BtnKeluar.setToolTipText("Alt+K");
        BtnKeluar.setFont(new java.awt.Font("Tahoma", 1, 12));
        BtnKeluar.setName("BtnKeluar"); // NOI18N
        BtnKeluar.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnKeluar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnKeluarActionPerformed(evt);
            }
        });
        BtnKeluar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnKeluarKeyPressed(evt);
            }
        });
        jPanel5.add(BtnKeluar);

        jPanel2.add(jPanel5, java.awt.BorderLayout.CENTER);

        internalFrame1.add(jPanel2, java.awt.BorderLayout.PAGE_END);

        scrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(212, 212, 152)));
        scrollPane1.setName("scrollPane1"); // NOI18N
        scrollPane1.setOpaque(true);

        tbPegawai.setAutoCreateRowSorter(true);
        tbPegawai.setModel(new javax.swing.table.DefaultTableModel(
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
        tbPegawai.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbPegawai.setFont(new java.awt.Font("Tahoma", 0, 12));
        tbPegawai.setName("tbPegawai"); // NOI18N
        scrollPane1.setViewportView(tbPegawai);

        internalFrame1.add(scrollPane1, java.awt.BorderLayout.CENTER);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        panelJudul1.setName("panelJudul1"); // NOI18N
        panelJudul1.setPreferredSize(new java.awt.Dimension(163, 28));
        panelJudul1.setLayout(new java.awt.BorderLayout());

        jLabel1.setBackground(new java.awt.Color(0, 100, 0));
        jLabel1.setFont(new java.awt.Font("Tahoma", 3, 13));
        jLabel1.setForeground(new java.awt.Color(202, 202, 142));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText(":: REKAP KEHADIRAN BULANAN ::");
        jLabel1.setIconTextGap(2);
        jLabel1.setName("jLabel1"); // NOI18N
        panelJudul1.add(jLabel1, java.awt.BorderLayout.CENTER);

        getContentPane().add(panelJudul1, java.awt.BorderLayout.PAGE_START);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
       if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            BtnCariActionPerformed(null);
        }else{Valid.pindah(evt, BtnKeluar, BtnCari);}
}//GEN-LAST:event_TCariKeyPressed

    private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        prosesCari("select pegawai.nik,"+
                                "pegawai.nama,"+
                                "pegawai.departemen,pegawai.id from pegawai where  "+
                   " pegawai.nik like '%"+TCari.getText().trim()+"%' "+
                   "or pegawai.nama like '%"+TCari.getText().trim()+"%' "+
                   "or pegawai.departemen like '%"+TCari.getText().trim()+"%'  order by pegawai.nik ");
        this.setCursor(Cursor.getDefaultCursor());
}//GEN-LAST:event_BtnCariActionPerformed

    private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            BtnCariActionPerformed(null);
        }else{
            Valid.pindah(evt, TCari, BtnAll);
        }
}//GEN-LAST:event_BtnCariKeyPressed

private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        TCari.setText("");
        tampil("");
        this.setCursor(Cursor.getDefaultCursor());
}//GEN-LAST:event_BtnAllActionPerformed

private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            BtnAllActionPerformed(null);
        }else{
            Valid.pindah(evt, TCari, BtnKeluar);
        }
}//GEN-LAST:event_BtnAllKeyPressed

private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
            dispose();  
}//GEN-LAST:event_BtnKeluarActionPerformed

private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            dispose();  
        }else{Valid.pindah(evt,BtnAll,TCari);}
}//GEN-LAST:event_BtnKeluarKeyPressed

private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if(! TCari.getText().trim().equals("")){
            BtnCariActionPerformed(evt);
        }
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            TCari.requestFocus();
        }else if(tabMode.getRowCount()!=0){
            if(TCari.getText().trim().equals("")){
                Valid.MyReport("rptHadir.jrxml","report","::[ Rekap Kehadiran Bulanan ]::","select pegawai.nik,"+
                                "pegawai.nama,"+
                                "pegawai.departemen,"+
                                "(select count(id) from rekap_presensi where rekap_presensi.id=pegawai.id "+
                                "and rekap_presensi.jam_datang like '%"+ThnCari.getSelectedItem()+"-"+BlnCari.getSelectedItem()+"%') as hadir, "+                
                                "(select count(id) from rekap_presensi where rekap_presensi.id=pegawai.id and rekap_presensi.shift not like '%Malam%' "+
                                "and rekap_presensi.jam_datang like '%"+ThnCari.getSelectedItem()+"-"+BlnCari.getSelectedItem()+"%') as siang , "+                 
                                "(select count(id) from rekap_presensi where rekap_presensi.id=pegawai.id and rekap_presensi.shift like '%Malam%' "+
                                "and rekap_presensi.jam_datang like '%"+ThnCari.getSelectedItem()+"-"+BlnCari.getSelectedItem()+"%') as malam, "+                  
                                "(select count(id) from rekap_presensi where rekap_presensi.id=pegawai.id and rekap_presensi.status='Terlambat' "+
                                "and rekap_presensi.jam_datang like '%"+ThnCari.getSelectedItem()+"-"+BlnCari.getSelectedItem()+"%') as terlambat "+ 
                                "from pegawai order by pegawai.nik ");
            }else if(! TCari.getText().trim().equals("")){
                Valid.MyReport("rptHadir.jrxml","report","::[ Rekap Kehadiran Bulanan ]::","select pegawai.nik,"+
                                "pegawai.nama,"+
                                "pegawai.departemen,"+
                                "(select count(id) from rekap_presensi where rekap_presensi.id=pegawai.id "+
                                "and rekap_presensi.jam_datang like '%"+ThnCari.getSelectedItem()+"-"+BlnCari.getSelectedItem()+"%') as hadir, "+                
                                "(select count(id) from rekap_presensi where rekap_presensi.id=pegawai.id and rekap_presensi.shift not like '%Malam%' "+
                                "and rekap_presensi.jam_datang like '%"+ThnCari.getSelectedItem()+"-"+BlnCari.getSelectedItem()+"%') as siang , "+                 
                                "(select count(id) from rekap_presensi where rekap_presensi.id=pegawai.id and rekap_presensi.shift like '%Malam%' "+
                                "and rekap_presensi.jam_datang like '%"+ThnCari.getSelectedItem()+"-"+BlnCari.getSelectedItem()+"%') as malam, "+                  
                                "(select count(id) from rekap_presensi where rekap_presensi.id=pegawai.id and rekap_presensi.status='Terlambat' "+
                                "and rekap_presensi.jam_datang like '%"+ThnCari.getSelectedItem()+"-"+BlnCari.getSelectedItem()+"%') as terlambat "+ 
                                "from pegawai where  "+
                   " pegawai.nik like '%"+TCari.getText().trim()+"%' "+
                   "or pegawai.nama like '%"+TCari.getText().trim()+"%' "+
                   "or pegawai.departemen like '%"+TCari.getText().trim()+"%' order by pegawai.nik ");                
            }
        }
        this.setCursor(Cursor.getDefaultCursor());
}//GEN-LAST:event_BtnPrintActionPerformed

private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            BtnPrintActionPerformed(null);
        }else{
            Valid.pindah(evt,TCari,BtnAll);
        }
}//GEN-LAST:event_BtnPrintKeyPressed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DlgKehadiran dialog = new DlgKehadiran(new javax.swing.JFrame(), true);
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
    private widget.ComboBox BlnCari;
    private widget.Button BtnAll;
    private widget.Button BtnCari;
    private widget.Button BtnKeluar;
    private widget.Button BtnPrint;
    private widget.Label LCount;
    private widget.TextBox TCari;
    private widget.ComboBox ThnCari;
    private widget.InternalFrame internalFrame1;
    private widget.Label jLabel1;
    private widget.panelisi jPanel2;
    private widget.panelisi jPanel4;
    private widget.panelisi jPanel5;
    private widget.Label label10;
    private widget.Label label11;
    private widget.Label label9;
    private widget.PanelJudul panelJudul1;
    private widget.ScrollPane scrollPane1;
    private widget.Table tbPegawai;
    // End of variables declaration//GEN-END:variables

    public void tampil(String key) {
        String sql="select pegawai.nik,"+
                                "pegawai.nama,"+
                                "pegawai.departemen,pegawai.id "+ 
                                "from pegawai order by pegawai.nik ";
        prosesCari(sql);
    }

    private void prosesCari(String sql) {
        Valid.tabelKosong(tabMode);
        try{
            java.sql.Statement stat=connect.createStatement();
            java.sql.Statement stat2=connect.createStatement();
            ResultSet rs=stat.executeQuery(sql);
            while(rs.next()){
                String hadir="0",siang="0",malam="0",terlambat="0";
                ResultSet rs2=stat2.executeQuery("select count(rekap_presensi.id) from rekap_presensi where rekap_presensi.id='"+rs.getString(4)+"'  "+
                                "and rekap_presensi.jam_datang like '%"+ThnCari.getSelectedItem()+"-"+BlnCari.getSelectedItem()+"%'");
                rs2.last();
                if(rs2.getRow()>0){
                    hadir=rs2.getString(1);
                }
                
                ResultSet rs3=stat2.executeQuery("select count(rekap_presensi.id) from rekap_presensi where rekap_presensi.id='"+rs.getString(4)+"' and rekap_presensi.shift not like '%Malam%' "+
                                "and rekap_presensi.jam_datang like '%"+ThnCari.getSelectedItem()+"-"+BlnCari.getSelectedItem()+"%'");
                rs3.last();
                if(rs3.getRow()>0){
                    siang=rs3.getString(1);
                }
                
                ResultSet rs4=stat2.executeQuery("select count(rekap_presensi.id) from rekap_presensi where rekap_presensi.id='"+rs.getString(4)+"' and rekap_presensi.shift like '%Malam%' "+
                                "and rekap_presensi.jam_datang like '%"+ThnCari.getSelectedItem()+"-"+BlnCari.getSelectedItem()+"%'");
                rs4.last();
                if(rs4.getRow()>0){
                    malam=rs4.getString(1);
                }
                
                ResultSet rs5=stat2.executeQuery("select count(rekap_presensi.id) from rekap_presensi where rekap_presensi.id='"+rs.getString(4)+"' and rekap_presensi.status like '%Terlambat%' "+
                                "and rekap_presensi.jam_datang like '%"+ThnCari.getSelectedItem()+"-"+BlnCari.getSelectedItem()+"%'");
                rs5.last();
                if(rs5.getRow()>0){
                    terlambat=rs5.getString(1);
                }
                String[] data={rs.getString(1),
                               rs.getString(2),
                               rs.getString(3),
                               hadir,
                               siang,
                               malam,
                               terlambat};
                tabMode.addRow(data);
             }
            rs.close();
            stat.close();
        }catch(SQLException e){
            System.out.println("Error : "+e);
        }
        LCount.setText(""+tabMode.getRowCount());
    }
    
    private  void loadTahun(){
        Valid.LoadTahun(ThnCari);
    }

 
}