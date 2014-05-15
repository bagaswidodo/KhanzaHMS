

package keuangan;

import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author perpustakaan
 */
public final class DlgLhtPiutang extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private Connection koneksi=koneksiDB.condb();
    private sekuel Sequel=new sekuel();
    private validasi Valid=new validasi();
    private PreparedStatement ps;
    private ResultSet rs;

    /** Creates new form DlgLhtBiaya
     * @param parent
     * @param modal */
    public DlgLhtPiutang(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocation(8,1);
        setSize(885,674);

        Object[] rowRwJlDr={"No.Rawat/No.tagihan",
                            "Tgl.Piutang",
                            "Pasien",
                            "Status",
                            "Total Piutang",
                            "Uang Muka",
                            "Cicilan",
                            "Sisa Piutang",
                            "Jatuh Tempo"};
        tabMode=new DefaultTableModel(null,rowRwJlDr){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        tbBangsal.setModel(tabMode);
        //tbBangsal.setDefaultRenderer(Object.class, new WarnaTable(jPanel2.getBackground(),tbBangsal.getBackground()));
        tbBangsal.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbBangsal.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 9; i++) {
            TableColumn column = tbBangsal.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(130);
            }else if(i==1){
                column.setPreferredWidth(100);
            }else if(i==2){
                column.setPreferredWidth(250);
            }else if(i==3){
                column.setPreferredWidth(80);
            }else if(i==4){
                column.setPreferredWidth(110);
            }else if(i==5){
                column.setPreferredWidth(110);
            }else if(i==6){
                column.setPreferredWidth(110);
            }else if(i==7){
                column.setPreferredWidth(110);
            }else if(i==8){
                column.setPreferredWidth(100);
            }
        }
        tbBangsal.setDefaultRenderer(Object.class, new WarnaTable());

        TKd.setDocument(new batasInput((byte)20).getKata(TKd));
        try {
            ps=koneksi.prepareStatement("select piutang_pasien.no_rawat, piutang_pasien.tgl_piutang, concat(piutang_pasien.no_rkm_medis,' ',pasien.nm_pasien), "+
                   "piutang_pasien.status,piutang_pasien.totalpiutang, piutang_pasien.uangmuka, piutang_pasien.sisapiutang, piutang_pasien.tgltempo "+
                   "from piutang_pasien inner join pasien on  piutang_pasien.no_rkm_medis=pasien.no_rkm_medis where "+
                   "piutang_pasien.no_rawat like ? and piutang_pasien.tgl_piutang between ? and ? or "+
                   "piutang_pasien.no_rkm_medis like ? and piutang_pasien.tgl_piutang between ? and ? or "+
                   "pasien.nm_pasien like ? and piutang_pasien.tgl_piutang between ? and ? or "+
                   "piutang_pasien.status like ? and piutang_pasien.tgl_piutang between ? and ? order by piutang_pasien.tgl_piutang");
        } catch (SQLException e) {
            System.out.println(e);
        }

    }
    

     double sisapiutang=0,cicilan=0;

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TKd = new widget.TextBox();
        jPopupMenu1 = new javax.swing.JPopupMenu();
        MnDetailPiutang = new javax.swing.JMenuItem();
        MnDetailCicilan = new javax.swing.JMenuItem();
        internalFrame1 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbBangsal = new widget.Table();
        panelGlass5 = new widget.panelisi();
        jLabel10 = new javax.swing.JLabel();
        LCount = new javax.swing.JLabel();
        BtnHapus = new widget.Button();
        BtnPrint = new widget.Button();
        BtnAll = new widget.Button();
        BtnKeluar = new widget.Button();
        panelisi4 = new widget.panelisi();
        label11 = new widget.Label();
        Tgl1 = new widget.Tanggal();
        label18 = new widget.Label();
        Tgl2 = new widget.Tanggal();
        label17 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();

        TKd.setForeground(new java.awt.Color(255, 255, 255));
        TKd.setName("TKd"); // NOI18N
        TKd.setSelectionColor(new java.awt.Color(255, 255, 255));

        jPopupMenu1.setName("jPopupMenu1"); // NOI18N

        MnDetailPiutang.setBackground(new java.awt.Color(255, 255, 255));
        MnDetailPiutang.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnDetailPiutang.setForeground(java.awt.Color.darkGray);
        MnDetailPiutang.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/PatientFile.png"))); // NOI18N
        MnDetailPiutang.setText("Detail Piutang");
        MnDetailPiutang.setName("MnDetailPiutang"); // NOI18N
        MnDetailPiutang.setPreferredSize(new java.awt.Dimension(250, 28));
        MnDetailPiutang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDetailPiutangActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnDetailPiutang);

        MnDetailCicilan.setBackground(new java.awt.Color(255, 255, 255));
        MnDetailCicilan.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        MnDetailCicilan.setForeground(java.awt.Color.darkGray);
        MnDetailCicilan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/PatientFile.png"))); // NOI18N
        MnDetailCicilan.setText("Detail Cicilan");
        MnDetailCicilan.setName("MnDetailCicilan"); // NOI18N
        MnDetailCicilan.setPreferredSize(new java.awt.Dimension(250, 28));
        MnDetailCicilan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MnDetailCicilanActionPerformed(evt);
            }
        });
        jPopupMenu1.add(MnDetailCicilan);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Data Tagihan Piutang Pasien ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 70, 40))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setComponentPopupMenu(jPopupMenu1);
        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbBangsal.setAutoCreateRowSorter(true);
        tbBangsal.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbBangsal.setComponentPopupMenu(jPopupMenu1);
        tbBangsal.setName("tbBangsal"); // NOI18N
        tbBangsal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbBangsalMouseClicked(evt);
            }
        });
        tbBangsal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbBangsalKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbBangsal);

        internalFrame1.add(Scroll, java.awt.BorderLayout.CENTER);

        panelGlass5.setName("panelGlass5"); // NOI18N
        panelGlass5.setPreferredSize(new java.awt.Dimension(55, 55));
        panelGlass5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 11)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(153, 0, 51));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel10.setText("Total Tagihan Masuk :");
        jLabel10.setName("jLabel10"); // NOI18N
        jLabel10.setPreferredSize(new java.awt.Dimension(130, 30));
        panelGlass5.add(jLabel10);

        LCount.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        LCount.setForeground(new java.awt.Color(153, 0, 51));
        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(250, 30));
        panelGlass5.add(LCount);

        BtnHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/stop_f2.png"))); // NOI18N
        BtnHapus.setMnemonic('H');
        BtnHapus.setText("Hapus");
        BtnHapus.setToolTipText("Alt+H");
        BtnHapus.setName("BtnHapus"); // NOI18N
        BtnHapus.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapusActionPerformed(evt);
            }
        });
        BtnHapus.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnHapusKeyPressed(evt);
            }
        });
        panelGlass5.add(BtnHapus);

        BtnPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        BtnPrint.setMnemonic('T');
        BtnPrint.setText("Cetak");
        BtnPrint.setToolTipText("Alt+T");
        BtnPrint.setName("BtnPrint"); // NOI18N
        BtnPrint.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPrintActionPerformed(evt);
            }
        });
        BtnPrint.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnPrintKeyPressed(evt);
            }
        });
        panelGlass5.add(BtnPrint);

        BtnAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll.setMnemonic('M');
        BtnAll.setText("Semua");
        BtnAll.setToolTipText("Alt+M");
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
        panelGlass5.add(BtnAll);

        BtnKeluar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/exit.png"))); // NOI18N
        BtnKeluar.setMnemonic('K');
        BtnKeluar.setText("Keluar");
        BtnKeluar.setToolTipText("Alt+K");
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
        panelGlass5.add(BtnKeluar);

        internalFrame1.add(panelGlass5, java.awt.BorderLayout.PAGE_END);

        panelisi4.setName("panelisi4"); // NOI18N
        panelisi4.setPreferredSize(new java.awt.Dimension(100, 44));
        panelisi4.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        label11.setText("Tgl.Tagihan :");
        label11.setName("label11"); // NOI18N
        label11.setPreferredSize(new java.awt.Dimension(80, 23));
        panelisi4.add(label11);

        Tgl1.setEditable(false);
        Tgl1.setDisplayFormat("yyyy-MM-dd");
        Tgl1.setName("Tgl1"); // NOI18N
        Tgl1.setPreferredSize(new java.awt.Dimension(100, 23));
        panelisi4.add(Tgl1);

        label18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        label18.setText("s.d.");
        label18.setName("label18"); // NOI18N
        label18.setPreferredSize(new java.awt.Dimension(30, 23));
        panelisi4.add(label18);

        Tgl2.setEditable(false);
        Tgl2.setDisplayFormat("yyyy-MM-dd");
        Tgl2.setName("Tgl2"); // NOI18N
        Tgl2.setPreferredSize(new java.awt.Dimension(100, 23));
        panelisi4.add(Tgl2);

        label17.setText("Key Word :");
        label17.setName("label17"); // NOI18N
        label17.setPreferredSize(new java.awt.Dimension(80, 23));
        panelisi4.add(label17);

        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(350, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelisi4.add(TCari);

        BtnCari.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/accept.png"))); // NOI18N
        BtnCari.setMnemonic('2');
        BtnCari.setToolTipText("Alt+2");
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
        panelisi4.add(BtnCari);

        internalFrame1.add(panelisi4, java.awt.BorderLayout.PAGE_START);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
         tampil();
         Sequel.meghapus("piutang_pasien","no_rawat",tbBangsal.getValueAt(tbBangsal.getSelectedRow(),0).toString());
         int reply2 = JOptionPane.showConfirmDialog(rootPane,"Anda sudah melakukan penghapusan data tagihan pasien.....!!!!\n"+
                                                             "Silahkan lakukan 'Jurnal Penyesuaian' agar transaksi akuntansi tetap balance.\n"+
                                                             "agar jumlah barang tetap valid. Anda ingin melakukan 'Jurnal Penyesuaian' sekarang...??",
                                                             "Konfirmasi",JOptionPane.YES_NO_OPTION);
         if (reply2 == JOptionPane.YES_OPTION) {
             this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
             //Sequel.menyimpan("tampjurnal","'42000','PEMBAYARAN PASIEN','"+all+"','0'","Rekening");    
             //Sequel.menyimpan("tampjurnal","'11120','KAS DI TANGAN','0','"+all+"'","Rekening"); 
             DlgJurnal form=new DlgJurnal(null,false);
             form.emptTeks();  
             form.setData(tbBangsal.getValueAt(tbBangsal.getSelectedRow(),0).toString());
             form.tampil();
             form.setSize(internalFrame1.getWidth(), internalFrame1.getHeight());
             form.setLocationRelativeTo(internalFrame1);
             form.setVisible(true);
             this.setCursor(Cursor.getDefaultCursor());
        }
        TCari.setText("");
        tampil();
}//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            BtnHapusActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnHapus, BtnPrint);
        }
}//GEN-LAST:event_BtnHapusKeyPressed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            //TCari.requestFocus();
        }else if(tabMode.getRowCount()!=0){
            Sequel.queryu("delete from temporary");
            int row=tabMode.getRowCount();
            for(int i=0;i<row;i++){  
                    Sequel.menyimpan("temporary","'0','"+
                                tabMode.getValueAt(i,0).toString()+"','"+
                                tabMode.getValueAt(i,1).toString()+"','"+
                                tabMode.getValueAt(i,2).toString()+"','"+
                                tabMode.getValueAt(i,3).toString()+"','"+
                                tabMode.getValueAt(i,4).toString()+"','"+
                                tabMode.getValueAt(i,5).toString()+"','"+
                                tabMode.getValueAt(i,6).toString()+"','"+
                                tabMode.getValueAt(i,7).toString()+"','"+
                                tabMode.getValueAt(i,8).toString()+"','','','','','','','',''","Piutang Pasien"); 
            }
            Sequel.menyimpan("temporary","'0','','','','','','','','','','','','','','','','',''","Rekap Harian Tindakan Dokter"); 
            Sequel.menyimpan("temporary","'0','TOTAL PIUTANG','',':','','','','','"+LCount.getText()+"','','','','','','','','',''","Rekap Harian Tindakan Dokter"); 
            Valid.MyReport("rptRPiutangMasuk.jrxml","report","::[ Rekap Piutang Masuk ]::",
                "select no, temp1, temp2, temp3, temp4, temp5, temp6, temp7, temp8, temp9, temp10, temp11, temp12, temp13, temp14 from temporary order by no asc");
        }
        this.setCursor(Cursor.getDefaultCursor());
}//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            BtnPrintActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnHapus, BtnAll);
        }
}//GEN-LAST:event_BtnPrintKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            dispose();
        }else{Valid.pindah(evt,BtnAll,TKd);}
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
        TCari.setText("");
        tampil();

}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            BtnAllActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnPrint, BtnKeluar);
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void tbBangsalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbBangsalMouseClicked
        if(tabMode.getRowCount()!=0){
            if(evt.getClickCount()==2){
                int kolom=tbBangsal.getSelectedColumn();
                if(kolom==1){
                    this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    DlgBayarPiutang bayarpiutang=new DlgBayarPiutang(null,false);
                    bayarpiutang.emptTeks();
                    String norm=Sequel.cariIsi("select no_rkm_medis from piutang_pasien where no_rawat='"+tbBangsal.getValueAt(tbBangsal.getSelectedRow(),0).toString() +"'");
                    String nama=Sequel.cariIsi("select nm_pasien from pasien where no_rkm_medis='"+norm+"'"); 
                    bayarpiutang.setData(tbBangsal.getValueAt(tbBangsal.getSelectedRow(),0).toString(),norm,nama);                   
                    bayarpiutang.tampil();  
                    bayarpiutang.setSize(this.getWidth()-40,this.getHeight()-40);
                    bayarpiutang.setLocationRelativeTo(this);
                    bayarpiutang.setVisible(true);
                    this.setCursor(Cursor.getDefaultCursor());
                }else if(kolom==0){
                    this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    DlgBilingPiutang rincianpiutang=new DlgBilingPiutang(null,false);
                    rincianpiutang.isRawat(tbBangsal.getValueAt(tbBangsal.getSelectedRow(),0).toString());                    
                    rincianpiutang.setSize(this.getWidth()-40,this.getHeight()-40);
                    rincianpiutang.setLocationRelativeTo(this);
                    rincianpiutang.setVisible(true);
                    this.setCursor(Cursor.getDefaultCursor());
                }
            }
        }
}//GEN-LAST:event_tbBangsalMouseClicked

    private void tbBangsalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbBangsalKeyPressed
        if(tabMode.getRowCount()!=0){
            if(evt.getKeyCode()==KeyEvent.VK_SPACE){
                int kolom=tbBangsal.getSelectedColumn();
                if(kolom==1){
                    this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    DlgBayarPiutang bayarpiutang=new DlgBayarPiutang(null,false);
                    bayarpiutang.emptTeks();
                    String norm=Sequel.cariIsi("select no_rkm_medis from piutang_pasien where no_rawat='"+tbBangsal.getValueAt(tbBangsal.getSelectedRow(),0).toString()+"'");
                    String nama=Sequel.cariIsi("select nm_pasien from pasien where no_rkm_medis='"+norm+"'");
                    bayarpiutang.setData(tbBangsal.getValueAt(tbBangsal.getSelectedRow(),0).toString(),norm,nama);
                    bayarpiutang.tampil();  
                    bayarpiutang.setSize(this.getWidth()-40,this.getHeight()-40);
                    bayarpiutang.setLocationRelativeTo(this);
                    bayarpiutang.setVisible(true);
                    this.setCursor(Cursor.getDefaultCursor());
                }else if(kolom==0){
                    this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    DlgBilingPiutang rincianpiutang=new DlgBilingPiutang(null,false);
                    rincianpiutang.isRawat(tbBangsal.getValueAt(tbBangsal.getSelectedRow(),0).toString());                    
                    rincianpiutang.setSize(this.getWidth()-40,this.getHeight()-40);
                    rincianpiutang.setLocationRelativeTo(this);
                    rincianpiutang.setVisible(true);
                    this.setCursor(Cursor.getDefaultCursor());
                }               
            }
        }
}//GEN-LAST:event_tbBangsalKeyPressed

private void TCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            BtnCariActionPerformed(null);
        }else{Valid.pindah(evt, BtnKeluar, BtnCari);}
}//GEN-LAST:event_TCariKeyPressed

private void BtnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCariActionPerformed
        tampil();
}//GEN-LAST:event_BtnCariActionPerformed

private void BtnCariKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnCariKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            tampil();
        }else{
            Valid.pindah(evt, TKd, BtnAll);
        }
}//GEN-LAST:event_BtnCariKeyPressed

private void MnDetailPiutangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDetailPiutangActionPerformed
     if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, table masih kosong...!!!!");
            TCari.requestFocus();
        }else{
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    DlgBilingPiutang rincianpiutang=new DlgBilingPiutang(null,false);
                    rincianpiutang.isRawat(tbBangsal.getValueAt(tbBangsal.getSelectedRow(),0).toString());                    
                    rincianpiutang.setSize(this.getWidth()-40,this.getHeight()-40);
                    rincianpiutang.setLocationRelativeTo(this);
                    rincianpiutang.setAlwaysOnTop(false);
                    rincianpiutang.setVisible(true);
                    this.setCursor(Cursor.getDefaultCursor());                     
        } 
}//GEN-LAST:event_MnDetailPiutangActionPerformed

private void MnDetailCicilanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MnDetailCicilanActionPerformed
   if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, table masih kosong...!!!!");
            TCari.requestFocus();
        }else{
            this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
                    DlgBayarPiutang bayarpiutang=new DlgBayarPiutang(null,false);
                    bayarpiutang.emptTeks();
                    String norm=Sequel.cariIsi("select no_rkm_medis from piutang_pasien where no_rawat='"+tbBangsal.getValueAt(tbBangsal.getSelectedRow(),0).toString()+"'");
                    String nama=Sequel.cariIsi("select nm_pasien from pasien where no_rkm_medis='"+norm+"'"); 
                    bayarpiutang.setData(tbBangsal.getValueAt(tbBangsal.getSelectedRow(),0).toString(),norm,nama);                   
                    bayarpiutang.tampil();  
                    bayarpiutang.setSize(this.getWidth()-40,this.getHeight()-40);
                    bayarpiutang.setLocationRelativeTo(this);
                    bayarpiutang.setAlwaysOnTop(false);
                    bayarpiutang.setVisible(true);
                    this.setCursor(Cursor.getDefaultCursor());                    
        } 
}//GEN-LAST:event_MnDetailCicilanActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgLhtPiutang dialog = new DlgLhtPiutang(new javax.swing.JFrame(), true);
            dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    System.exit(0);
                }
            });
            dialog.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private widget.Button BtnAll;
    private widget.Button BtnCari;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnPrint;
    private javax.swing.JLabel LCount;
    private javax.swing.JMenuItem MnDetailCicilan;
    private javax.swing.JMenuItem MnDetailPiutang;
    private widget.ScrollPane Scroll;
    private widget.TextBox TCari;
    private widget.TextBox TKd;
    private widget.Tanggal Tgl1;
    private widget.Tanggal Tgl2;
    private widget.InternalFrame internalFrame1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JPopupMenu jPopupMenu1;
    private widget.Label label11;
    private widget.Label label17;
    private widget.Label label18;
    private widget.panelisi panelGlass5;
    private widget.panelisi panelisi4;
    private widget.Table tbBangsal;
    // End of variables declaration//GEN-END:variables

    public void tampil(){
        Valid.tabelKosong(tabMode);
        try{
            ps.setString(1,"%"+TCari.getText()+"%");
            ps.setString(2, Tgl1.getSelectedItem().toString());
            ps.setString(3, Tgl2.getSelectedItem().toString());
            ps.setString(4,"%"+TCari.getText()+"%");
            ps.setString(5, Tgl1.getSelectedItem().toString());
            ps.setString(6, Tgl2.getSelectedItem().toString());
            ps.setString(7,"%"+TCari.getText()+"%");
            ps.setString(8, Tgl1.getSelectedItem().toString());
            ps.setString(9, Tgl2.getSelectedItem().toString());
            ps.setString(10,"%"+TCari.getText()+"%");
            ps.setString(11, Tgl1.getSelectedItem().toString());
            ps.setString(12, Tgl2.getSelectedItem().toString());
            rs=ps.executeQuery();
            sisapiutang=0;
            while(rs.next()){
                cicilan=Sequel.cariIsiAngka("SELECT ifnull(SUM(bayar_piutang.besar_cicilan),0) FROM bayar_piutang where bayar_piutang.no_rawat=?",rs.getString(1));
                tabMode.addRow(new Object[]{rs.getString(1),
                                    rs.getString(2),
                                    rs.getString(3),
                                    rs.getString(4),
                                    Valid.SetAngka(rs.getDouble(5)),
                                    Valid.SetAngka(rs.getDouble(6)),
                                    Valid.SetAngka(cicilan),
                                    Valid.SetAngka(rs.getDouble(7)-cicilan),
                                    rs.getString(8)});
                sisapiutang=sisapiutang+rs.getDouble(7)-cicilan;
            }
            LCount.setText(Valid.SetAngka(sisapiutang));
        }catch(SQLException e){
            System.out.println("Error : "+e);
        }
    }



}
