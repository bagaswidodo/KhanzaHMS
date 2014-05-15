/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * DlgPenyakit.java
 *
 * Created on May 23, 2010, 12:57:16 AM
 */

package keuangan;

import keuangan.DlgRekening;
import fungsi.WarnaTable;
import fungsi.batasInput;
import fungsi.koneksiDB;
import fungsi.sekuel;
import fungsi.validasi;
import fungsi.var;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

/**
 *
 * @author dosen
 */
public final class DlgRekeningTahun extends javax.swing.JDialog {
    private final DefaultTableModel tabMode;
    private sekuel Sequel=new sekuel();
    private validasi Valid=new validasi();
    private Connection koneksi=koneksiDB.condb();
    private PreparedStatement ps,ps2,ps3;
    private ResultSet rs,rs2;
    private Dimension screen=Toolkit.getDefaultToolkit().getScreenSize();
    private DlgRekening rekening=new DlgRekening(null,false);
    private DecimalFormat df2 = new DecimalFormat("###,###,###,###,###,###,###");
    private double md = 0,mk = 0,saldoakhir=0;
    /** Creates new form DlgPenyakit
     * @param parent
     * @param modal */
    public DlgRekeningTahun(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.setLocation(10,2);
        setSize(628,674);

        Object[] row={"Tahun",
                      "Kode Rekening",
                      "Nama Rekening",
                      "Tipe",
                      "Balance",
                      "Saldo Awal",
                      "Mutasi Debet",
                      "Mutasi Kredit",
                      "Saldo Akhir"};
        tabMode=new DefaultTableModel(null,row){
              @Override public boolean isCellEditable(int rowIndex, int colIndex){return false;}
        };
        tbKamar.setModel(tabMode);
        //tbPenyakit.setDefaultRenderer(Object.class, new WarnaTable(panelJudul.getBackground(),tbPenyakit.getBackground()));
        tbKamar.setPreferredScrollableViewportSize(new Dimension(500,500));
        tbKamar.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        for (int i = 0; i < 9; i++) {
            TableColumn column = tbKamar.getColumnModel().getColumn(i);
            if(i==0){
                column.setPreferredWidth(80);
            }else if(i==1){
                column.setPreferredWidth(100);
            }else if(i==2){
                column.setPreferredWidth(250);
            }else if(i==3){
                column.setPreferredWidth(80);
            }else if(i==4){
                column.setPreferredWidth(80);
            }else if(i==5){
                column.setPreferredWidth(150);
            }else if(i==6){
                column.setPreferredWidth(150);
            }else if(i==7){
                column.setPreferredWidth(150);
            }else if(i==8){
                column.setPreferredWidth(150);
            }
        }
        tbKamar.setDefaultRenderer(Object.class, new WarnaTable());
        
        Kd.setDocument(new batasInput((byte)5).getKata(Kd));
        Saldo.setDocument(new batasInput((byte)15).getOnlyAngka(Saldo));
        TCari.setDocument(new batasInput((byte)100).getKata(TCari));
        
        rekening.getTabel().addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) { 
                try {
                    Kd.setText(rekening.getTextField().getText());
                    ps3.setString(1,Kd.getText());
                    rs=ps3.executeQuery();
                    while(rs.next()){
                        Kd.setText(rs.getString(1));
                        Nm.setText(rs.getString(2));
                        Tipe.setText(rs.getString(3));
                        Balance.setText(rs.getString(4));
                    }
                } catch (SQLException ex) {
                    System.out.println("error rekening : "+ex);
                }
            }
            
            @Override
            public void mousePressed(MouseEvent e) {}
            @Override
            public void mouseReleased(MouseEvent e) {}
            @Override
            public void mouseEntered(MouseEvent e) {}
            @Override
            public void mouseExited(MouseEvent e) {}
        });
        Valid.LoadTahun(Tahun);
        try {
            ps=koneksi.prepareStatement("select rekeningtahun.thn,rekening.kd_rek, rekening.nm_rek, rekening.tipe, "+
                "rekening.balance,rekeningtahun.saldo_awal  "+
                "from rekening inner join rekeningtahun on rekeningtahun.kd_rek=rekening.kd_rek "+
                "where rekeningtahun.thn=? and rekening.kd_rek like ? or "+
                "rekeningtahun.thn=? and rekening.nm_rek like ? or "+
                "rekeningtahun.thn=? and rekening.tipe like ? or "+
                "rekeningtahun.thn=? and rekening.balance like ? order by rekening.kd_rek");
            ps2=koneksi.prepareStatement("select sum(detailjurnal.debet),sum(detailjurnal.kredit) "+
                                                 "from jurnal inner join detailjurnal on detailjurnal.no_jurnal=jurnal.no_jurnal where "+
                                                 "detailjurnal.kd_rek=? and jurnal.tgl_jurnal like ? ");
            ps3=koneksi.prepareStatement("select kd_rek, nm_rek, tipe, balance from rekening where kd_rek=? order by kd_rek");
        } catch (SQLException e) {
            System.out.println(e);
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

        Popup = new javax.swing.JPopupMenu();
        ppSimpan = new javax.swing.JMenuItem();
        ppGanti = new javax.swing.JMenuItem();
        ppHapus = new javax.swing.JMenuItem();
        ppCetak = new javax.swing.JMenuItem();
        Kd2 = new widget.TextBox();
        internalFrame1 = new widget.InternalFrame();
        Scroll = new widget.ScrollPane();
        tbKamar = new widget.Table();
        panelisi4 = new widget.panelisi();
        label34 = new widget.Label();
        label32 = new widget.Label();
        Kd = new widget.TextBox();
        Nm = new widget.TextBox();
        Tahun = new widget.ComboBox();
        label33 = new widget.Label();
        Saldo = new widget.TextBox();
        BtnCari7 = new widget.Button();
        label35 = new widget.Label();
        Tipe = new widget.TextBox();
        Balance = new widget.TextBox();
        label36 = new widget.Label();
        jPanel1 = new javax.swing.JPanel();
        panelisi3 = new widget.panelisi();
        label9 = new widget.Label();
        TCari = new widget.TextBox();
        BtnCari = new widget.Button();
        BtnAll = new widget.Button();
        label10 = new widget.Label();
        LCount = new widget.Label();
        panelisi1 = new widget.panelisi();
        BtnSimpan = new widget.Button();
        BtnBatal = new widget.Button();
        BtnHapus = new widget.Button();
        BtnEdit = new widget.Button();
        BtnPrint = new widget.Button();
        BtnKeluar = new widget.Button();

        Popup.setName("Popup"); // NOI18N

        ppSimpan.setBackground(new java.awt.Color(242, 242, 242));
        ppSimpan.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        ppSimpan.setForeground(new java.awt.Color(102, 51, 0));
        ppSimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        ppSimpan.setText("Simpan");
        ppSimpan.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppSimpan.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppSimpan.setIconTextGap(8);
        ppSimpan.setName("ppSimpan"); // NOI18N
        ppSimpan.setPreferredSize(new java.awt.Dimension(150, 25));
        ppSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpanActionPerformed(evt);
            }
        });
        Popup.add(ppSimpan);

        ppGanti.setBackground(new java.awt.Color(242, 242, 242));
        ppGanti.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        ppGanti.setForeground(new java.awt.Color(102, 51, 0));
        ppGanti.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        ppGanti.setText("Ganti");
        ppGanti.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppGanti.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppGanti.setIconTextGap(8);
        ppGanti.setName("ppGanti"); // NOI18N
        ppGanti.setPreferredSize(new java.awt.Dimension(150, 25));
        ppGanti.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEditActionPerformed(evt);
            }
        });
        Popup.add(ppGanti);

        ppHapus.setBackground(new java.awt.Color(242, 242, 242));
        ppHapus.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        ppHapus.setForeground(new java.awt.Color(102, 51, 0));
        ppHapus.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/stop_f2.png"))); // NOI18N
        ppHapus.setText("Hapus");
        ppHapus.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppHapus.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppHapus.setIconTextGap(8);
        ppHapus.setName("ppHapus"); // NOI18N
        ppHapus.setPreferredSize(new java.awt.Dimension(150, 25));
        ppHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnHapusActionPerformed(evt);
            }
        });
        Popup.add(ppHapus);

        ppCetak.setBackground(new java.awt.Color(242, 242, 242));
        ppCetak.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        ppCetak.setForeground(new java.awt.Color(102, 51, 0));
        ppCetak.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/b_print.png"))); // NOI18N
        ppCetak.setText("Cetak");
        ppCetak.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ppCetak.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        ppCetak.setIconTextGap(8);
        ppCetak.setName("ppCetak"); // NOI18N
        ppCetak.setPreferredSize(new java.awt.Dimension(150, 25));
        ppCetak.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnPrintActionPerformed(evt);
            }
        });
        Popup.add(ppCetak);

        Kd2.setHighlighter(null);
        Kd2.setName("Kd2"); // NOI18N
        Kd2.setSelectionColor(new java.awt.Color(255, 255, 255));
        Kd2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                Kd2KeyPressed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);

        internalFrame1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(240, 245, 235)), "::[ Data Saldo Rekening ]::", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(50, 70, 40))); // NOI18N
        internalFrame1.setName("internalFrame1"); // NOI18N
        internalFrame1.setLayout(new java.awt.BorderLayout(1, 1));

        Scroll.setComponentPopupMenu(Popup);
        Scroll.setName("Scroll"); // NOI18N
        Scroll.setOpaque(true);

        tbKamar.setToolTipText("Silahkan klik untuk memilih data yang mau diedit ataupun dihapus");
        tbKamar.setComponentPopupMenu(Popup);
        tbKamar.setName("tbKamar"); // NOI18N
        tbKamar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbKamarMouseClicked(evt);
            }
        });
        tbKamar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbKamarKeyPressed(evt);
            }
        });
        Scroll.setViewportView(tbKamar);

        internalFrame1.add(Scroll, java.awt.BorderLayout.CENTER);

        panelisi4.setName("panelisi4"); // NOI18N
        panelisi4.setPreferredSize(new java.awt.Dimension(100, 77));
        panelisi4.setLayout(null);

        label34.setText("Rekening :");
        label34.setName("label34"); // NOI18N
        label34.setPreferredSize(new java.awt.Dimension(35, 23));
        panelisi4.add(label34);
        label34.setBounds(0, 12, 67, 23);

        label32.setText("Tahun :");
        label32.setName("label32"); // NOI18N
        label32.setPreferredSize(new java.awt.Dimension(35, 23));
        panelisi4.add(label32);
        label32.setBounds(437, 12, 110, 23);

        Kd.setHighlighter(null);
        Kd.setName("Kd"); // NOI18N
        Kd.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                KdKeyPressed(evt);
            }
        });
        panelisi4.add(Kd);
        Kd.setBounds(70, 12, 80, 23);

        Nm.setEditable(false);
        Nm.setHighlighter(null);
        Nm.setName("Nm"); // NOI18N
        Nm.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                NmKeyPressed(evt);
            }
        });
        panelisi4.add(Nm);
        Nm.setBounds(152, 12, 240, 23);

        Tahun.setName("Tahun"); // NOI18N
        Tahun.setPreferredSize(new java.awt.Dimension(45, 23));
        Tahun.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TahunKeyPressed(evt);
            }
        });
        panelisi4.add(Tahun);
        Tahun.setBounds(550, 12, 70, 23);

        label33.setText("Saldo Awal : Rp");
        label33.setName("label33"); // NOI18N
        label33.setPreferredSize(new java.awt.Dimension(35, 23));
        panelisi4.add(label33);
        label33.setBounds(407, 42, 100, 23);

        Saldo.setHighlighter(null);
        Saldo.setName("Saldo"); // NOI18N
        Saldo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                SaldoKeyPressed(evt);
            }
        });
        panelisi4.add(Saldo);
        Saldo.setBounds(510, 42, 110, 23);

        BtnCari7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/190.png"))); // NOI18N
        BtnCari7.setMnemonic('1');
        BtnCari7.setToolTipText("Alt+1");
        BtnCari7.setName("BtnCari7"); // NOI18N
        BtnCari7.setPreferredSize(new java.awt.Dimension(28, 23));
        BtnCari7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnCari7ActionPerformed(evt);
            }
        });
        panelisi4.add(BtnCari7);
        BtnCari7.setBounds(395, 12, 28, 23);

        label35.setText("Tipe :");
        label35.setName("label35"); // NOI18N
        label35.setPreferredSize(new java.awt.Dimension(35, 23));
        panelisi4.add(label35);
        label35.setBounds(0, 42, 67, 23);

        Tipe.setEditable(false);
        Tipe.setHighlighter(null);
        Tipe.setName("Tipe"); // NOI18N
        panelisi4.add(Tipe);
        Tipe.setBounds(70, 42, 80, 23);

        Balance.setEditable(false);
        Balance.setHighlighter(null);
        Balance.setName("Balance"); // NOI18N
        panelisi4.add(Balance);
        Balance.setBounds(312, 42, 80, 23);

        label36.setText("Balance :");
        label36.setName("label36"); // NOI18N
        label36.setPreferredSize(new java.awt.Dimension(35, 23));
        panelisi4.add(label36);
        label36.setBounds(217, 42, 90, 23);

        internalFrame1.add(panelisi4, java.awt.BorderLayout.PAGE_START);

        jPanel1.setName("jPanel1"); // NOI18N
        jPanel1.setOpaque(false);
        jPanel1.setPreferredSize(new java.awt.Dimension(816, 100));
        jPanel1.setLayout(new java.awt.BorderLayout(1, 1));

        panelisi3.setName("panelisi3"); // NOI18N
        panelisi3.setPreferredSize(new java.awt.Dimension(100, 44));
        panelisi3.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 4, 9));

        label9.setText("Key Word :");
        label9.setName("label9"); // NOI18N
        label9.setPreferredSize(new java.awt.Dimension(70, 23));
        panelisi3.add(label9);

        TCari.setName("TCari"); // NOI18N
        TCari.setPreferredSize(new java.awt.Dimension(350, 23));
        TCari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TCariKeyPressed(evt);
            }
        });
        panelisi3.add(TCari);

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
        panelisi3.add(BtnCari);

        BtnAll.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Search-16x16.png"))); // NOI18N
        BtnAll.setMnemonic('3');
        BtnAll.setToolTipText("Alt+3");
        BtnAll.setName("BtnAll"); // NOI18N
        BtnAll.setPreferredSize(new java.awt.Dimension(28, 23));
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
        panelisi3.add(BtnAll);

        label10.setText("Record :");
        label10.setName("label10"); // NOI18N
        label10.setPreferredSize(new java.awt.Dimension(70, 23));
        panelisi3.add(label10);

        LCount.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        LCount.setText("0");
        LCount.setName("LCount"); // NOI18N
        LCount.setPreferredSize(new java.awt.Dimension(60, 23));
        panelisi3.add(LCount);

        jPanel1.add(panelisi3, java.awt.BorderLayout.PAGE_START);

        panelisi1.setName("panelisi1"); // NOI18N
        panelisi1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 5, 9));

        BtnSimpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/save-16x16.png"))); // NOI18N
        BtnSimpan.setMnemonic('S');
        BtnSimpan.setText("Simpan");
        BtnSimpan.setToolTipText("Alt+S");
        BtnSimpan.setName("BtnSimpan"); // NOI18N
        BtnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnSimpanActionPerformed(evt);
            }
        });
        BtnSimpan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnSimpanKeyPressed(evt);
            }
        });
        panelisi1.add(BtnSimpan);

        BtnBatal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/Cancel-2-16x16.png"))); // NOI18N
        BtnBatal.setMnemonic('B');
        BtnBatal.setText("Baru");
        BtnBatal.setToolTipText("Alt+B");
        BtnBatal.setName("BtnBatal"); // NOI18N
        BtnBatal.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnBatal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBatalActionPerformed(evt);
            }
        });
        BtnBatal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnBatalKeyPressed(evt);
            }
        });
        panelisi1.add(BtnBatal);

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
        panelisi1.add(BtnHapus);

        BtnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/picture/inventaris.png"))); // NOI18N
        BtnEdit.setMnemonic('G');
        BtnEdit.setText("Ganti");
        BtnEdit.setToolTipText("Alt+G");
        BtnEdit.setName("BtnEdit"); // NOI18N
        BtnEdit.setPreferredSize(new java.awt.Dimension(100, 30));
        BtnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEditActionPerformed(evt);
            }
        });
        BtnEdit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                BtnEditKeyPressed(evt);
            }
        });
        panelisi1.add(BtnEdit);

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
        panelisi1.add(BtnPrint);

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
        panelisi1.add(BtnKeluar);

        jPanel1.add(panelisi1, java.awt.BorderLayout.CENTER);

        internalFrame1.add(jPanel1, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(internalFrame1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void KdKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_KdKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_PAGE_DOWN){
            try {
                    ps3.setString(1,Kd.getText());
                    rs=ps3.executeQuery();
                    while(rs.next()){
                        Kd.setText(rs.getString(1));
                        Nm.setText(rs.getString(2));
                        Tipe.setText(rs.getString(3));
                        Balance.setText(rs.getString(4));
                    }
            } catch (SQLException ex) {
                    System.out.println("error rekening : "+ex);
            }
            Tahun.requestFocus();            
        }else if(evt.getKeyCode()==KeyEvent.VK_PAGE_UP){
            try {
                    ps3.setString(1,Kd.getText());
                    rs=ps3.executeQuery();
                    while(rs.next()){
                        Kd.setText(rs.getString(1));
                        Nm.setText(rs.getString(2));
                        Tipe.setText(rs.getString(3));
                        Balance.setText(rs.getString(4));
                    }
            } catch (SQLException ex) {
                    System.out.println("error rekening : "+ex);
            }
            BtnKeluar.requestFocus();
        }else if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            try {
                    ps3.setString(1,Kd.getText());
                    rs=ps3.executeQuery();
                    while(rs.next()){
                        Kd.setText(rs.getString(1));
                        Nm.setText(rs.getString(2));
                        Tipe.setText(rs.getString(3));
                        Balance.setText(rs.getString(4));
                    }
            } catch (SQLException ex) {
                    System.out.println("error rekening : "+ex);
            }
        }
}//GEN-LAST:event_KdKeyPressed

    private void BtnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnSimpanActionPerformed
        if(Kd.getText().trim().equals("")){
            Valid.textKosong(Kd,"Rekening");
        }else if(Saldo.getText().trim().equals("")){
            Valid.textKosong(Saldo,"Saldo");
        }else{
            Sequel.menyimpan("rekeningtahun","'"+Tahun.getSelectedItem()+"','"+
                    Kd.getText()+"','"+Saldo.getText() +"'",
                    "rekening dan tahun");
            BtnCariActionPerformed(evt);
            emptTeks();
        }
}//GEN-LAST:event_BtnSimpanActionPerformed

    private void BtnSimpanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnSimpanKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            BtnSimpanActionPerformed(null);
        }else{
            Valid.pindah(evt,Nm,BtnBatal);
        }
}//GEN-LAST:event_BtnSimpanKeyPressed

    private void BtnBatalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBatalActionPerformed
        emptTeks();
}//GEN-LAST:event_BtnBatalActionPerformed

    private void BtnBatalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnBatalKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            emptTeks();
        }else{Valid.pindah(evt, BtnSimpan, BtnHapus);}
}//GEN-LAST:event_BtnBatalKeyPressed

    private void BtnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnHapusActionPerformed
        Valid.hapusTable(tabMode,Kd,"rekeningtahun ","thn='"+Tahun.getSelectedItem()+"' and kd_rek");
        BtnCariActionPerformed(evt);
        emptTeks();
}//GEN-LAST:event_BtnHapusActionPerformed

    private void BtnHapusKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnHapusKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            BtnHapusActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnBatal, BtnEdit);
        }
}//GEN-LAST:event_BtnHapusKeyPressed

    private void BtnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditActionPerformed
        if(Kd.getText().trim().equals("")){
            Valid.textKosong(Kd,"Rekening");
        }else if(Saldo.getText().trim().equals("")){
            Valid.textKosong(Nm,"Saldo");
        }else{
            Valid.editTable(tabMode,"rekeningtahun","thn='"+Tahun.getSelectedItem()+"' and kd_rek",Kd,"saldo_awal='"+Saldo.getText()+"' ");
            if(tabMode.getRowCount()!=0){BtnCariActionPerformed(evt);}
            emptTeks();
        }
}//GEN-LAST:event_BtnEditActionPerformed

    private void BtnEditKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnEditKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            BtnEditActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnHapus, BtnPrint);
        }
}//GEN-LAST:event_BtnEditKeyPressed

    private void BtnKeluarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnKeluarActionPerformed
        dispose();
}//GEN-LAST:event_BtnKeluarActionPerformed

    private void BtnKeluarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnKeluarKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            dispose();
        }else{Valid.pindah(evt,BtnEdit,TCari);}
}//GEN-LAST:event_BtnKeluarKeyPressed

    private void BtnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnPrintActionPerformed
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        BtnCariActionPerformed(evt);
        if(tabMode.getRowCount()==0){
            JOptionPane.showMessageDialog(null,"Maaf, data sudah habis. Tidak ada data yang bisa anda print...!!!!");
            TCari.requestFocus();
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
                                tabMode.getValueAt(i,8).toString()+"','','','','','','','',''","Rekening Tahun"); 
            }
            Valid.MyReport("rptRekeningTahun.jrxml","report","::[ Saldo Rekening ]::",
                "select no, temp1, temp2, temp3, temp4, temp5, temp6, temp7, temp8, temp9, temp10, temp11, temp12, temp13, temp14 from temporary order by no asc");
        }
        this.setCursor(Cursor.getDefaultCursor());
}//GEN-LAST:event_BtnPrintActionPerformed

    private void BtnPrintKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnPrintKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            BtnPrintActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnEdit, BtnKeluar);
        }
}//GEN-LAST:event_BtnPrintKeyPressed

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
            BtnCariActionPerformed(null);
        }else{
            Valid.pindah(evt, TCari, BtnAll);
        }
}//GEN-LAST:event_BtnCariKeyPressed

    private void BtnAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAllActionPerformed
        TCari.setText("");
        tampil();
}//GEN-LAST:event_BtnAllActionPerformed

    private void BtnAllKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_BtnAllKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            BtnAllActionPerformed(null);
        }else{
            Valid.pindah(evt, BtnCari, TCari);
        }
}//GEN-LAST:event_BtnAllKeyPressed

    private void tbKamarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbKamarMouseClicked
        if(tabMode.getRowCount()!=0){
            try {
                getData();
            } catch (java.lang.NullPointerException e) {
            }
            if((var.getStatus()==true)&&(evt.getClickCount()==2)){
                var.setStatus(false);
                dispose();
            }
        }
}//GEN-LAST:event_tbKamarMouseClicked

    private void tbKamarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbKamarKeyPressed
        if(tabMode.getRowCount()!=0){
            if((evt.getKeyCode()==KeyEvent.VK_ENTER)||(evt.getKeyCode()==KeyEvent.VK_UP)||(evt.getKeyCode()==KeyEvent.VK_DOWN)){
                try {
                    getData();
                } catch (java.lang.NullPointerException e) {
                }
            }
            if((var.getStatus()==true)&&(evt.getKeyCode()==KeyEvent.VK_SPACE)){
                var.setStatus(false);
                dispose();
            }
        }
}//GEN-LAST:event_tbKamarKeyPressed

private void NmKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_NmKeyPressed
   Valid.pindah(evt,Kd,Tahun);
}//GEN-LAST:event_NmKeyPressed

    private void Kd2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Kd2KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_Kd2KeyPressed

    private void TahunKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TahunKeyPressed
        Valid.pindah(evt,Kd,Saldo);
    }//GEN-LAST:event_TahunKeyPressed

    private void SaldoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_SaldoKeyPressed
        Valid.pindah(evt,Tahun,BtnSimpan);
    }//GEN-LAST:event_SaldoKeyPressed

    private void BtnCari7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnCari7ActionPerformed
        var.setStatus(true);
        rekening.emptTeks();
        rekening.tampil();
        rekening.setSize(internalFrame1.getWidth()-40,internalFrame1.getHeight()-40);
        rekening.setLocationRelativeTo(internalFrame1);
        rekening.setAlwaysOnTop(false);
        rekening.setVisible(true);
    }//GEN-LAST:event_BtnCari7ActionPerformed

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            DlgRekeningTahun dialog = new DlgRekeningTahun(new javax.swing.JFrame(), true);
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
    private widget.TextBox Balance;
    private widget.Button BtnAll;
    private widget.Button BtnBatal;
    private widget.Button BtnCari;
    private widget.Button BtnCari7;
    private widget.Button BtnEdit;
    private widget.Button BtnHapus;
    private widget.Button BtnKeluar;
    private widget.Button BtnPrint;
    private widget.Button BtnSimpan;
    private widget.TextBox Kd;
    private widget.TextBox Kd2;
    private widget.Label LCount;
    private widget.TextBox Nm;
    private javax.swing.JPopupMenu Popup;
    private widget.TextBox Saldo;
    private widget.ScrollPane Scroll;
    private widget.TextBox TCari;
    private widget.ComboBox Tahun;
    private widget.TextBox Tipe;
    private widget.InternalFrame internalFrame1;
    private javax.swing.JPanel jPanel1;
    private widget.Label label10;
    private widget.Label label32;
    private widget.Label label33;
    private widget.Label label34;
    private widget.Label label35;
    private widget.Label label36;
    private widget.Label label9;
    private widget.panelisi panelisi1;
    private widget.panelisi panelisi3;
    private widget.panelisi panelisi4;
    private javax.swing.JMenuItem ppCetak;
    private javax.swing.JMenuItem ppGanti;
    private javax.swing.JMenuItem ppHapus;
    private javax.swing.JMenuItem ppSimpan;
    private widget.Table tbKamar;
    // End of variables declaration//GEN-END:variables

    public void tampil() {
        Valid.tabelKosong(tabMode);
        try{            
            ps.setString(1,Tahun.getSelectedItem().toString());
            ps.setString(2,"%"+TCari.getText().trim()+"%");
            ps.setString(3,Tahun.getSelectedItem().toString());
            ps.setString(4,"%"+TCari.getText().trim()+"%");
            ps.setString(5,Tahun.getSelectedItem().toString());
            ps.setString(6,"%"+TCari.getText().trim()+"%");
            ps.setString(7,Tahun.getSelectedItem().toString());
            ps.setString(8,"%"+TCari.getText().trim()+"%");
            rs=ps.executeQuery();
            while(rs.next()){
                md = 0;mk = 0;saldoakhir=0;
                ps2.setString(1,rs.getString(2));
                ps2.setString(2,"%"+Tahun.getSelectedItem()+"%");
                rs2=ps2.executeQuery();                
                if(rs2.next()){
                    md=rs2.getDouble(1);
                    mk=rs2.getDouble(2);
                    saldoakhir=rs.getDouble(6)+(md-mk);
                    if(saldoakhir<0){
                        saldoakhir=(rs.getDouble(6)+(md-mk))*(-1);
                    }
                }
                tabMode.addRow(new Object[]{rs.getString(1).substring(0, 4),
                               rs.getString(2),
                               rs.getString(3),
                               rs.getString(4),
                               rs.getString(5),
                               df2.format(rs.getDouble(6)),
                               df2.format(md),
                               df2.format(mk),
                               df2.format(saldoakhir)});
            }
        }catch(SQLException e){
            System.out.println("Error : "+e);
        }
        LCount.setText(""+tabMode.getRowCount());
    }

    public void emptTeks() {
        Kd.setText("");
        Kd2.setText("");
        Nm.setText("");
        Balance.setText("");
        Tipe.setText("");
        Saldo.setText("");
        Tahun.setSelectedIndex(0);
        Kd.requestFocus();        
    }

    private void getData() {
        int row=tbKamar.getSelectedRow();
        if(row!= -1){
            Tahun.setSelectedItem(tbKamar.getValueAt(row,0).toString());
            Kd.setText(tbKamar.getValueAt(row,1).toString());
            Kd2.setText(tbKamar.getValueAt(row,1).toString());
            Nm.setText(tbKamar.getValueAt(row,2).toString());
            Tipe.setText(tbKamar.getValueAt(row,3).toString());
            Balance.setText(tbKamar.getValueAt(row,4).toString());
            Saldo.setText(tbKamar.getValueAt(row,5).toString());
        }
    }

    public JTextField getTextField(){
        return Kd;
    }
    
    public JTextField getSaldo(){
        return Saldo;
    }

    public JButton getButton(){
        return BtnKeluar;
    }
    
    public void isCek(){
        if(var.getjml2()>=1){        
            BtnSimpan.setEnabled(var.getrekening_tahun());
            BtnBatal.setEnabled(var.getrekening_tahun());
            BtnEdit.setEnabled(var.getrekening_tahun());
            BtnHapus.setEnabled(var.getrekening_tahun());
            BtnPrint.setEnabled(var.getrekening_tahun());
            ppSimpan.setEnabled(var.getrekening_tahun());
            ppGanti.setEnabled(var.getrekening_tahun());        
            ppHapus.setEnabled(var.getrekening_tahun());
            ppCetak.setEnabled(var.getrekening_tahun());
        }
    }
}