/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.quanlydoituongdacbiet.view;

import com.mycompany.quanlydoituongdacbiet.action.ManagerJails;
import com.mycompany.quanlydoituongdacbiet.entity.Jail;
import com.mycompany.quanlydoituongdacbiet.entity.Prisoner;
import com.raven.chart.Chart;
import com.raven.chart.ModelChart;
import java.awt.event.ActionListener;
import java.awt.event.ItemListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.HashMap;
import javax.swing.*;
import java.util.List;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
/**
 *
 * @author PC
 */
public class PrisonerView extends javax.swing.JFrame {

    /**
     * Creates new form ManagerView
     */
    private SimpleDateFormat fDate=new SimpleDateFormat("dd/MM/yyyy");
    private String filename=null;
    private byte[] prisoner_image=null;
    private byte[] image=null;
    private String [] columnNames = new String [] {
        "ID", "Họ và tên", "Năm sinh", "Quê quán", "Mã nhà tù", "Mã phạm nhân", "Tội danh", "Ngày vào", "Ngày ra", "Hạnh kiểm", "Ảnh"};
    private String [] columnNames2 = new String [] {
        "<none>","Số lượng"};
    private Object data = new Object [][] {};
    private ManagerJails jailManager;

    public PrisonerView() {
        jailManager = new ManagerJails();
        initComponents();
        btnAdd.setEnabled(true);
        btnEdit.setEnabled(false);
        btnDelete.setEnabled(false);
        btnSearch.setEnabled(true);
        lblImage.setIcon(new ImageIcon("default-image.png"));
        tablePrisoner.setDefaultRenderer(Object.class, new MyRenderer());
        tableStatistic.setDefaultRenderer(Object.class, new MyRenderer2());
        OutDateChooser.setBackground(new Color(0, 204, 255));
        chart1.addLegend("Số lượng", new Color(0, 178, 238));
    }
  
    private static Image getCircleImage(Image image) {
        int width = image.getWidth(null);
        int height = image.getHeight(null);
        BufferedImage circleImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = circleImage.createGraphics();
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Ellipse2D.Double circle = new Ellipse2D.Double(0, 0, width, height);
        graphics.setClip(circle);
        graphics.drawImage(image, 0, 0, null);
        graphics.setColor(Color.WHITE);
        graphics.setStroke(new BasicStroke(2));
        graphics.draw(circle);
        return circleImage;
    }
    
    private ImageIcon ImageIconSize(JLabel label, String filename) {
        Image image = new ImageIcon(filename).getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon imageIcon=new ImageIcon(image);
        return imageIcon;
    }

    private String abbreviation(String name) {
        return name;
    }
    
    public class MyRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
            TableColumnModel columnModel=table.getColumnModel();
            columnModel.getColumn(0).setPreferredWidth(40);  // STT
            columnModel.getColumn(1).setPreferredWidth(150); // Họ tên
            columnModel.getColumn(2).setPreferredWidth(60);  // Năm sinh
            columnModel.getColumn(3).setPreferredWidth(200); // Quê quán
            columnModel.getColumn(4).setPreferredWidth(80);  // Mã nhà tù
            columnModel.getColumn(5).setPreferredWidth(100); // Mã phạm nhân
            columnModel.getColumn(6).setPreferredWidth(150); // Tội danh
            columnModel.getColumn(7).setPreferredWidth(100); // Ngày vào
            columnModel.getColumn(8).setPreferredWidth(100); // Ngày ra
            columnModel.getColumn(9).setPreferredWidth(80);  // Hạnh kiểm
            JTableHeader header = table.getTableHeader();
            header.setBackground(new Color(0, 0, 139));
            header.setForeground(Color.WHITE);
            header.setFont(new java.awt.Font("Times New Roman", 0, 18));
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
            if (!isSelected) {
                if (row % 2 == 0) {
                    c.setBackground(new Color(191, 239, 255));
                } else {
                    c.setBackground(new Color(135, 206, 250));
                }
            } else {
                c.setBackground(new Color(193, 255, 193));
            }
            return c;
        }
    }
    
    public class MyRenderer2 extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
            TableColumnModel columnModel=table.getColumnModel();
            JTableHeader header = table.getTableHeader();
            header.setBackground(new Color(0, 0, 139));
            header.setForeground(Color.WHITE);
            header.setFont(new java.awt.Font("Times New Roman", 0, 18));
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
            if (!isSelected) {
                if (row % 2 == 0) {
                    c.setBackground(new Color(191, 239, 255));
                } else {
                    c.setBackground(new Color(135, 206, 250));
                }
            } else {
                c.setBackground(new Color(193, 255, 193));
            }
            return c;
        }
    }
    
    public static String capitalizeWords(String str) {
        str = str.toLowerCase();
        String[] words = str.split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            if (word.length() > 0) {
                if (word.equals("tt") || word.equals("tp") || word.equals("tx")) {
                    sb.append(word.toUpperCase());
                } else {
                    sb.append(Character.toUpperCase(word.charAt(0)));
                    sb.append(word.substring(1));
                }
                sb.append(" ");
            }
        }
        return sb.toString().trim();
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        SearchDialog = new javax.swing.JDialog();
        jPanel3 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        CheckBoxName = new javax.swing.JCheckBox();
        jLabel11 = new javax.swing.JLabel();
        CheckBoxID = new javax.swing.JCheckBox();
        CheckBoxOutYear = new javax.swing.JCheckBox();
        jLabel8 = new javax.swing.JLabel();
        FieldSearch = new javax.swing.JTextField();
        btnCancelDialog1 = new javax.swing.JButton();
        btnSearchDialog = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        btnGroupSearch = new javax.swing.ButtonGroup();
        StatisticView = new javax.swing.JFrame();
        panelChart = new javax.swing.JPanel();
        ScrollPaneStatistic = new javax.swing.JScrollPane();
        tableStatistic = new javax.swing.JTable();
        lblTable = new javax.swing.JLabel();
        lblChart = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        btnStatisticRemainTime = new javax.swing.JButton();
        btnStatisticUnder = new javax.swing.JButton();
        btnStatisticBehavior = new javax.swing.JButton();
        chart1 = new com.raven.chart.Chart();
        timingTargetAdapter1 = new org.jdesktop.animation.timing.TimingTargetAdapter();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btnAdd = new javax.swing.JButton();
        btnCancelSearch = new javax.swing.JButton();
        btnManagerUndo = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        btnSearch = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        btnSortByInDate = new javax.swing.JButton();
        btnSortByName = new javax.swing.JButton();
        btnStatistic = new javax.swing.JButton();
        btnSortByYear = new javax.swing.JButton();
        btnSortByID = new javax.swing.JButton();
        imgLb = new javax.swing.JLabel();
        DDRate = new javax.swing.JComboBox<>();
        Jid = new javax.swing.JLabel();
        btnImage = new javax.swing.JButton();
        BirthdayChooser = new com.toedter.calendar.JDateChooser();
        OutDateChooser = new com.toedter.calendar.JDateChooser();
        jScrollPane2 = new javax.swing.JScrollPane();
        TextAreaAddress = new javax.swing.JTextArea();
        NgayRa = new javax.swing.JLabel();
        Rate = new javax.swing.JLabel();
        Toi = new javax.swing.JLabel();
        lblImage = new javax.swing.JLabel();
        FieldCrime = new javax.swing.JTextField();
        ID = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablePrisoner = new javax.swing.JTable();
        Address = new javax.swing.JLabel();
        NgaySinh = new javax.swing.JLabel();
        FieldID = new javax.swing.JTextField();
        InDateChooser = new com.toedter.calendar.JDateChooser();
        NgayVao = new javax.swing.JLabel();
        Pid = new javax.swing.JLabel();
        DDJid = new javax.swing.JComboBox<>();
        Ten = new javax.swing.JLabel();
        FieldName = new javax.swing.JTextField();
        FieldPid = new javax.swing.JTextField();

        SearchDialog.setResizable(false);
        SearchDialog.setSize(new java.awt.Dimension(450, 370));

        jPanel3.setLayout(null);

        jLabel10.setFont(new java.awt.Font("Times New Roman", 3, 24)); // NOI18N
        jLabel10.setText("Tìm kiếm");
        jPanel3.add(jLabel10);
        jLabel10.setBounds(170, 40, 110, 29);

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel12.setText("Chọn tiêu chí tìm kiếm:");
        jPanel3.add(jLabel12);
        jLabel12.setBounds(40, 190, 290, 29);

        btnGroupSearch.add(CheckBoxName);
        CheckBoxName.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        CheckBoxName.setText("Tên");
        CheckBoxName.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        CheckBoxName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CheckBoxNameActionPerformed(evt);
            }
        });
        jPanel3.add(CheckBoxName);
        CheckBoxName.setBounds(20, 230, 85, 20);
        CheckBoxName.setOpaque(false);

        jLabel11.setIcon(new ImageIcon(getClass().getResource("/images/search.png")));
        jPanel3.add(jLabel11);
        jLabel11.setBounds(30, 130, 30, 30);

        btnGroupSearch.add(CheckBoxID);
        CheckBoxID.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        CheckBoxID.setText("ID");
        CheckBoxID.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        CheckBoxID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CheckBoxIDActionPerformed(evt);
            }
        });
        jPanel3.add(CheckBoxID);
        CheckBoxID.setBounds(160, 230, 100, 20);
        CheckBoxID.setOpaque(false);

        btnGroupSearch.add(CheckBoxOutYear);
        CheckBoxOutYear.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        CheckBoxOutYear.setText("Năm ra tù");
        CheckBoxOutYear.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        CheckBoxOutYear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CheckBoxOutYearActionPerformed(evt);
            }
        });
        jPanel3.add(CheckBoxOutYear);
        CheckBoxOutYear.setBounds(320, 230, 100, 25);
        CheckBoxOutYear.setOpaque(false);

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel8.setText("Nhập nội dung tìm kiếm:");
        jPanel3.add(jLabel8);
        jLabel8.setBounds(40, 90, 290, 29);

        FieldSearch.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        FieldSearch.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 102)));
        jPanel3.add(FieldSearch);
        FieldSearch.setBounds(60, 130, 340, 40);
        FieldSearch.setOpaque(false);

        btnCancelDialog1.setBackground(new java.awt.Color(255, 255, 255, 0));
        btnCancelDialog1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnCancelDialog1.setText("Hủy");
        btnCancelDialog1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel3.add(btnCancelDialog1);
        btnCancelDialog1.setBounds(240, 270, 140, 50);
        btnCancelDialog1.setBorder(new RoundedBorder(20));
        btnCancelDialog1.setOpaque(false);

        btnSearchDialog.setBackground(new java.awt.Color(255, 255, 255, 0));
        btnSearchDialog.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnSearchDialog.setText("Tìm kiếm");
        btnSearchDialog.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSearchDialog.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchDialogActionPerformed(evt);
            }
        });
        jPanel3.add(btnSearchDialog);
        btnSearchDialog.setBounds(50, 270, 140, 50);
        btnSearchDialog.setBorder(new RoundedBorder(20));
        btnSearchDialog.setOpaque(false);

        jLabel15.setIcon(new ImageIcon(getClass().getResource("/images/viewSearchView.png")));
        jLabel15.setText("=");
        jPanel3.add(jLabel15);
        jLabel15.setBounds(0, 0, 450, 360);

        javax.swing.GroupLayout SearchDialogLayout = new javax.swing.GroupLayout(SearchDialog.getContentPane());
        SearchDialog.getContentPane().setLayout(SearchDialogLayout);
        SearchDialogLayout.setHorizontalGroup(
            SearchDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE)
        );
        SearchDialogLayout.setVerticalGroup(
            SearchDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE)
        );

        StatisticView.setTitle("Thống kê");
        StatisticView.setResizable(false);
        StatisticView.setSize(new java.awt.Dimension(1250, 600));
        StatisticView.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                StatisticViewWindowOpened(evt);
            }
        });

        panelChart.setBackground(new java.awt.Color(102, 204, 255));

        tableStatistic.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        tableStatistic.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            columnNames2
        ));
        tableStatistic.setRowHeight(30);
        ScrollPaneStatistic.setViewportView(tableStatistic);

        lblTable.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        lblTable.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblTable.setText("Thống kê số lượng theo mục");

        lblChart.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        lblChart.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblChart.setText("Biểu đồ");

        jPanel4.setBackground(new java.awt.Color(0, 102, 255));

        btnStatisticRemainTime.setBackground(new java.awt.Color(0, 0, 102));
        btnStatisticRemainTime.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnStatisticRemainTime.setForeground(new java.awt.Color(255, 255, 255));
        btnStatisticRemainTime.setIcon(new ImageIcon(getClass().getResource("/images/Calendar.png")));
        btnStatisticRemainTime.setText("Thời hạn cải tạo còn lại");
        btnStatisticRemainTime.setBorder(null);
        btnStatisticRemainTime.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnStatisticRemainTime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStatisticRemainTimeActionPerformed(evt);
            }
        });

        btnStatisticUnder.setBackground(new java.awt.Color(0, 0, 102));
        btnStatisticUnder.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnStatisticUnder.setForeground(new java.awt.Color(255, 255, 255));
        btnStatisticUnder.setIcon(new ImageIcon(getClass().getResource("/images/Undo.png")));
        btnStatisticUnder.setText("Quay lại");
        btnStatisticUnder.setBorder(null);
        btnStatisticUnder.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnStatisticUnder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStatisticUnderActionPerformed(evt);
            }
        });

        btnStatisticBehavior.setBackground(new java.awt.Color(0, 0, 102));
        btnStatisticBehavior.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnStatisticBehavior.setForeground(new java.awt.Color(255, 255, 255));
        btnStatisticBehavior.setIcon(new ImageIcon(getClass().getResource("/images/People.png")));
        btnStatisticBehavior.setText("Thái độ cải tạo");
        btnStatisticBehavior.setBorder(null);
        btnStatisticBehavior.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnStatisticBehavior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStatisticBehaviorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnStatisticRemainTime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnStatisticUnder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnStatisticBehavior, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(181, 181, 181)
                .addComponent(btnStatisticRemainTime, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(btnStatisticBehavior, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 201, Short.MAX_VALUE)
                .addComponent(btnStatisticUnder, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54))
        );

        javax.swing.GroupLayout panelChartLayout = new javax.swing.GroupLayout(panelChart);
        panelChart.setLayout(panelChartLayout);
        panelChartLayout.setHorizontalGroup(
            panelChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelChartLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addGroup(panelChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ScrollPaneStatistic)
                    .addComponent(lblTable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblChart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(chart1, javax.swing.GroupLayout.DEFAULT_SIZE, 514, Short.MAX_VALUE))
                .addContainerGap(85, Short.MAX_VALUE))
        );
        panelChartLayout.setVerticalGroup(
            panelChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelChartLayout.createSequentialGroup()
                .addGap(188, 188, 188)
                .addGroup(panelChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTable, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblChart, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ScrollPaneStatistic, javax.swing.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE)
                    .addComponent(chart1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout StatisticViewLayout = new javax.swing.GroupLayout(StatisticView.getContentPane());
        StatisticView.getContentPane().setLayout(StatisticViewLayout);
        StatisticViewLayout.setHorizontalGroup(
            StatisticViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelChart, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        StatisticViewLayout.setVerticalGroup(
            StatisticViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelChart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Quản lý các đối tượng đặc biệt trong khu vực");
        setName("ManagerFrame"); // NOI18N
        setResizable(false);

        //jPanel1.setLayout(new AbsoluteLayout());
        jPanel1.setLayout(null);

        jPanel2.setBackground(new java.awt.Color(0, 102, 204, 175));

        btnAdd.setBackground(new java.awt.Color(0, 0, 102));
        btnAdd.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnAdd.setForeground(new java.awt.Color(255, 255, 255));
        btnAdd.setIcon(new ImageIcon(getClass().getResource("/images/add.png")));
        btnAdd.setText("Thêm");
        btnAdd.setBorder(null);
        btnAdd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        btnCancelSearch.setBackground(new java.awt.Color(0, 0, 102));
        btnCancelSearch.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnCancelSearch.setForeground(new java.awt.Color(255, 255, 255));
        btnCancelSearch.setIcon(new ImageIcon(getClass().getResource("/images/cancel.png")));
        btnCancelSearch.setText("Hủy tìm kiếm");
        btnCancelSearch.setEnabled(false);
        btnCancelSearch.setToolTipText("");
        btnCancelSearch.setBorder(null);
        btnCancelSearch.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCancelSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelSearchActionPerformed(evt);
            }
        });

        btnManagerUndo.setBackground(new java.awt.Color(0, 0, 102));
        btnManagerUndo.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnManagerUndo.setForeground(new java.awt.Color(255, 255, 255));
        btnManagerUndo.setIcon(new ImageIcon(getClass().getResource("/images/LogOut.png")));
        btnManagerUndo.setText("Quay lại");
        btnManagerUndo.setToolTipText("");
        btnManagerUndo.setBorder(null);
        btnManagerUndo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnManagerUndo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnManagerUndoActionPerformed(evt);
            }
        });

        btnDelete.setBackground(new java.awt.Color(0, 0, 102));
        btnDelete.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnDelete.setForeground(new java.awt.Color(255, 255, 255));
        btnDelete.setIcon(new ImageIcon(getClass().getResource("/images/delete.png")));
        btnDelete.setText("Xóa");
        btnDelete.setBorder(null);
        btnDelete.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnClear.setBackground(new java.awt.Color(0, 0, 102));
        btnClear.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnClear.setForeground(new java.awt.Color(255, 255, 255));
        btnClear.setIcon(new ImageIcon(getClass().getResource("/images/trash.png")));
        btnClear.setText("Làm mới");
        btnClear.setToolTipText("");
        btnClear.setBorder(null);
        btnClear.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });

        btnSearch.setBackground(new java.awt.Color(0, 0, 102));
        btnSearch.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnSearch.setForeground(new java.awt.Color(255, 255, 255));
        btnSearch.setIcon(new ImageIcon(getClass().getResource("/images/search.png")));
        btnSearch.setText("Tìm kiếm");
        btnSearch.setBorder(null);
        btnSearch.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSearch.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btnSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSearchActionPerformed(evt);
            }
        });

        btnEdit.setBackground(new java.awt.Color(0, 0, 102));
        btnEdit.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnEdit.setForeground(new java.awt.Color(255, 255, 255));
        btnEdit.setIcon(new ImageIcon(getClass().getResource("/images/Edit.png")));
        btnEdit.setText("Sửa");
        btnEdit.setBorder(null);
        btnEdit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        ImageIcon imageIcon = new ImageIcon(getClass().getResource("/images/logo.png"));
        Image image = imageIcon.getImage().getScaledInstance(100,100, Image.SCALE_SMOOTH);
        imageIcon=new ImageIcon(image);
        jLabel14.setIcon(imageIcon);
        Address.setOpaque(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnAdd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnEdit, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnManagerUndo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnDelete, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnClear, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnSearch, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(btnCancelSearch, javax.swing.GroupLayout.DEFAULT_SIZE, 170, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnEdit, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnClear, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnCancelSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 53, Short.MAX_VALUE)
                .addComponent(btnManagerUndo, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );

        jPanel1.add(jPanel2);
        jPanel2.setBounds(0, 0, 170, 660);
        //jPanel2.setOpaque(false);

        btnSortByInDate.setBackground(new java.awt.Color(51, 204, 255));
        btnSortByInDate.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        btnSortByInDate.setText("Sắp xếp theo ngày vào");
        btnSortByInDate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSortByInDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSortByInDateActionPerformed(evt);
            }
        });
        jPanel1.add(btnSortByInDate);
        btnSortByInDate.setBounds(800, 330, 180, 40);

        btnSortByName.setBackground(new java.awt.Color(51, 204, 255));
        btnSortByName.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        btnSortByName.setText("Sắp xếp theo tên");
        btnSortByName.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSortByName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSortByNameActionPerformed(evt);
            }
        });
        jPanel1.add(btnSortByName);
        btnSortByName.setBounds(390, 330, 150, 40);

        btnStatistic.setBackground(new java.awt.Color(51, 204, 255));
        btnStatistic.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        btnStatistic.setText("Thống kê");
        btnStatistic.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnStatistic.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStatisticActionPerformed(evt);
            }
        });
        jPanel1.add(btnStatistic);
        btnStatistic.setBounds(1020, 330, 150, 40);
        //btnSortByID.setOpaque(false);

        btnSortByYear.setBackground(new java.awt.Color(51, 204, 255));
        btnSortByYear.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        btnSortByYear.setText("Sắp xếp theo năm sinh");
        btnSortByYear.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSortByYear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSortByYearActionPerformed(evt);
            }
        });
        jPanel1.add(btnSortByYear);
        btnSortByYear.setBounds(580, 330, 180, 40);

        btnSortByID.setBackground(new java.awt.Color(51, 204, 255));
        btnSortByID.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        btnSortByID.setText("Sắp xếp theo ID");
        btnSortByID.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSortByID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSortByIDActionPerformed(evt);
            }
        });
        jPanel1.add(btnSortByID);
        btnSortByID.setBounds(200, 330, 150, 40);
        //btnSortByID.setOpaque(false);

        imgLb.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        imgLb.setText("Ảnh:");
        jPanel1.add(imgLb);
        imgLb.setBounds(870, 60, 50, 42);

        DDRate.setBackground(Color.WHITE);
        DDRate.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        DDRate.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "<none>", "Rất tốt", "Tốt", "Bình thường", "Tệ", "Rất tệ" }));
        DDRate.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.add(DDRate);
        DDRate.setBounds(870, 270, 260, 45);

        Jid.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        Jid.setText("Mã nhà tù:");
        jPanel1.add(Jid);
        Jid.setBounds(630, 60, 130, 21);

        btnImage.setBackground(new java.awt.Color(255, 255, 255, 0));
        btnImage.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnImage.setIcon(new ImageIcon(getClass().getResource("/images/green pin.png")));
        btnImage.setText("<html>Thêm ảnh<br> ");
        btnImage.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImageActionPerformed(evt);
            }
        });
        jPanel1.add(btnImage);
        btnImage.setBounds(1090, 60, 90, 50);
        btnImage.setOpaque(false);
        btnImage.setBorder(new RoundedBorder(20));

        BirthdayChooser.setBackground(new java.awt.Color(0, 204, 255));
        BirthdayChooser.setForeground(new java.awt.Color(102, 255, 255));
        BirthdayChooser.setDateFormatString("dd/MM/yyyy");
        BirthdayChooser.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jPanel1.add(BirthdayChooser);
        BirthdayChooser.setBounds(300, 180, 220, 40);

        OutDateChooser.setBackground(new java.awt.Color(0, 204, 255));
        OutDateChooser.setForeground(new java.awt.Color(102, 255, 255));
        OutDateChooser.setDateFormatString("dd/MM/yyyy");
        OutDateChooser.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jPanel1.add(OutDateChooser);
        OutDateChooser.setBounds(450, 280, 250, 40);

        TextAreaAddress.setBackground(new java.awt.Color(255, 255, 255, 0));
        TextAreaAddress.setColumns(20);
        TextAreaAddress.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        TextAreaAddress.setRows(5);
        TextAreaAddress.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 51, 153)));
        jScrollPane2.setViewportView(TextAreaAddress);
        TextAreaAddress.setOpaque(false);

        jPanel1.add(jScrollPane2);
        jScrollPane2.setBounds(870, 190, 260, 70);

        NgayRa.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        NgayRa.setText("Ngày ra dự kiến: (dd/MM/yyyy)");
        jPanel1.add(NgayRa);
        NgayRa.setBounds(200, 280, 250, 42);

        Rate.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        Rate.setText("Đánh giá:");
        jPanel1.add(Rate);
        Rate.setBounds(760, 280, 130, 37);

        Toi.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        Toi.setText("Tội danh:");
        jPanel1.add(Toi);
        Toi.setBounds(600, 140, 90, 30);

        lblImage.setBackground(new java.awt.Color(153, 153, 153));
        lblImage.setBorder(javax.swing.BorderFactory.createMatteBorder(1, 1, 1, 1, new java.awt.Color(0, 51, 153)));
        jPanel1.add(lblImage);
        lblImage.setBounds(930, 10, 153, 153);

        FieldCrime.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        FieldCrime.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 51, 102)));
        FieldCrime.setOpaque(true);
        FieldCrime.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FieldCrimeActionPerformed(evt);
            }
        });
        jPanel1.add(FieldCrime);
        FieldCrime.setBounds(690, 130, 220, 40);
        FieldCrime.setOpaque(false);

        ID.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        ID.setText("ID:");
        jPanel1.add(ID);
        ID.setBounds(200, 60, 60, 21);

        jScrollPane1.setBackground(new java.awt.Color(0, 51, 153, 125));

        tablePrisoner.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        tablePrisoner.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            }, columnNames
        ));
        tablePrisoner.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_LAST_COLUMN);
        tablePrisoner.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tablePrisoner.setRowHeight(30);
        jScrollPane1.setViewportView(tablePrisoner);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(180, 380, 1020, 270);

        Address.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        Address.setText("Quê quán:");
        jPanel1.add(Address);
        Address.setBounds(760, 230, 90, 42);

        NgaySinh.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        NgaySinh.setText("Ngày sinh:");
        jPanel1.add(NgaySinh);
        NgaySinh.setBounds(200, 180, 90, 42);

        FieldID.setEditable(false);
        FieldID.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        FieldID.setBorder(javax.swing.BorderFactory.createMatteBorder(2, 2, 2, 2, new java.awt.Color(0, 51, 102)));
        FieldID.setOpaque(true);
        FieldID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FieldIDActionPerformed(evt);
            }
        });
        jPanel1.add(FieldID);
        FieldID.setBounds(230, 50, 100, 40);
        FieldID.setOpaque(false);

        InDateChooser.setBackground(new java.awt.Color(0, 204, 255));
        InDateChooser.setForeground(new java.awt.Color(102, 255, 255));
        InDateChooser.setDateFormatString("dd/MM/yyyy");
        InDateChooser.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        jPanel1.add(InDateChooser);
        InDateChooser.setBounds(450, 230, 250, 40);

        NgayVao.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        NgayVao.setText("Ngày vào: (dd/MM/yyyy)");
        jPanel1.add(NgayVao);
        NgayVao.setBounds(200, 230, 250, 42);

        Pid.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        Pid.setText("Mã phạm nhân:");
        jPanel1.add(Pid);
        Pid.setBounds(380, 60, 130, 21);

        DDJid.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        DDJid.setOpaque(true);
        DDJid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DDJidActionPerformed(evt);
            }
        });
        jPanel1.add(DDJid);
        DDJid.setBounds(730, 50, 100, 40);
        DDJid.removeAllItems();

        List<Jail> jails = jailManager.getListJails();
        for (Jail j : jails) {
            DDJid.addItem(j.getJailId());
        }

        Ten.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        Ten.setText("Họ và tên:");
        jPanel1.add(Ten);
        Ten.setBounds(200, 140, 90, 30);

        FieldName.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        FieldName.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 51, 102)));
        FieldName.setOpaque(true);
        FieldName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FieldNameActionPerformed(evt);
            }
        });
        jPanel1.add(FieldName);
        FieldName.setBounds(300, 130, 220, 40);
        FieldCrime.setOpaque(false);

        FieldPid.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        FieldPid.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 51, 102)));
        FieldPid.setOpaque(true);
        FieldPid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FieldPidActionPerformed(evt);
            }
        });
        jPanel1.add(FieldPid);
        FieldPid.setBounds(510, 50, 100, 40);
        FieldCrime.setOpaque(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1203, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 653, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CheckBoxNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CheckBoxNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CheckBoxNameActionPerformed

    private void btnSortByIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSortByIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSortByIDActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnSortByNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSortByNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSortByNameActionPerformed

    private void btnCancelSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCancelSearchActionPerformed

    private void FieldCrimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FieldCrimeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FieldCrimeActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void FieldIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FieldIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FieldIDActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnSortByInDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSortByInDateActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSortByInDateActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnSortByYearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSortByYearActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSortByYearActionPerformed

    private void btnImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImageActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnImageActionPerformed

    private void btnManagerUndoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnManagerUndoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnManagerUndoActionPerformed

    private void btnStatisticActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStatisticActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnStatisticActionPerformed

    private void StatisticViewWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_StatisticViewWindowOpened
        // TODO add your handling code here:
    }//GEN-LAST:event_StatisticViewWindowOpened

    private void btnStatisticBehaviorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStatisticBehaviorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnStatisticBehaviorActionPerformed

    private void btnStatisticUnderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStatisticUnderActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnStatisticUnderActionPerformed

    private void btnStatisticRemainTimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStatisticRemainTimeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnStatisticRemainTimeActionPerformed

    private void FieldNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FieldNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FieldNameActionPerformed

    private void DDJidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DDJidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_DDJidActionPerformed

    private void FieldPidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FieldPidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FieldPidActionPerformed

    private void btnSearchDialogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchDialogActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSearchDialogActionPerformed

    private void CheckBoxOutYearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CheckBoxOutYearActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CheckBoxOutYearActionPerformed

    private void CheckBoxIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CheckBoxIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CheckBoxIDActionPerformed

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
            java.util.logging.Logger.getLogger(PrisonerView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PrisonerView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PrisonerView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PrisonerView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PrisonerView().setVisible(true);
            }
        });
    }
    
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
    
    public class RoundedBorder implements Border {
        private int radius;
        RoundedBorder(int radius) {
            this.radius = radius;
        }
        public Insets getBorderInsets(Component c) {
            return new Insets(this.radius+1, this.radius+1, this.radius+2, this.radius);
        }
        public boolean isBorderOpaque() {
            return true;
        }
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.drawRoundRect(x, y, width-1, height-1, radius, radius);
        }
    }
    
    public void PrisonerImage()
    {
        String lastImagePath = "";
        JFileChooser chooser=new JFileChooser(lastImagePath);
        chooser.setDialogTitle("Chọn ảnh");
        // Giới hạn chọn tệp đến các tệp hình ảnh
        chooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
            public boolean accept(File f) {
                return f.getName().toLowerCase().endsWith(".jpg")
                    || f.getName().toLowerCase().endsWith(".png")
                    || f.getName().toLowerCase().endsWith(".gif")
                    || f.isDirectory();
            }
            public String getDescription() {
                return "Hình ảnh (*.jpg, *.png, *.gif)";
            }
        });
        chooser.showOpenDialog(null);
        File f=chooser.getSelectedFile();
        filename=f.getAbsolutePath();
        lastImagePath = f.getPath();
        ImageIcon imageIcon=new ImageIcon(new ImageIcon(filename).getImage().getScaledInstance(lblImage.getWidth(), lblImage.getHeight(), Image.SCALE_SMOOTH));
        lblImage.setIcon(imageIcon);
        try
        {
            File image=new File(filename);
            FileInputStream fis=new FileInputStream(image);
            ByteArrayOutputStream bos=new ByteArrayOutputStream();
            byte[] buf=new byte[1024];
            for (int readNum;(readNum=fis.read(buf))!=-1;)
            {
                bos.write(buf, 0, readNum);
            }
            prisoner_image=bos.toByteArray();
        }
        catch (Exception e)
        {
            showMessage(e.toString());
        }
    }
    
    /**
     * hiển thị list  vào bảng table
     * 
     * @param list
     */
      public void showListPrisoners(List<Prisoner> list) {
        int size = list.size();
        Object [][] prisoners = new Object[size][11];
        for (int i = 0; i < size; i++) {
            prisoners[i][0] = list.get(i).getId();
            prisoners[i][1] = list.get(i).getName();
            prisoners[i][2] = fDate.format(list.get(i).getBirthday());
            prisoners[i][3] = list.get(i).getAddress();
            prisoners[i][4] = list.get(i).getJailId();
            prisoners[i][5] = list.get(i).getPrisonerId();
            prisoners[i][6] = list.get(i).getCrime();
            prisoners[i][7] = fDate.format(list.get(i).getSentenceStartDate());
            prisoners[i][8] = fDate.format(list.get(i).getSentenceEndDate());
            prisoners[i][9] = list.get(i).getBehavior();
            prisoners[i][10] = list.get(i).getImage();
        }
        tablePrisoner.setModel(new DefaultTableModel(prisoners, columnNames));
        tablePrisoner.removeColumn(tablePrisoner.getColumnModel().getColumn(10));
    }
    
    public void fillPrisonerFromSelectedRow() throws ParseException {
        int row = tablePrisoner.getSelectedRow();
        if (row >= 0) {
            FieldID.setText(tablePrisoner.getModel().getValueAt(row, 0).toString());
            FieldName.setText(tablePrisoner.getModel().getValueAt(row, 1).toString());
            BirthdayChooser.setDate(fDate.parse(tablePrisoner.getModel().getValueAt(row, 2).toString()));
            TextAreaAddress.setText(tablePrisoner.getModel().getValueAt(row, 3).toString());
            DDJid.setSelectedItem(tablePrisoner.getModel().getValueAt(row, 4).toString());
            FieldPid.setText(tablePrisoner.getModel().getValueAt(row, 5).toString());
            FieldCrime.setText(tablePrisoner.getModel().getValueAt(row, 6).toString());
            InDateChooser.setDate(fDate.parse(tablePrisoner.getModel().getValueAt(row, 7).toString()));
            OutDateChooser.setDate(fDate.parse(tablePrisoner.getModel().getValueAt(row, 8).toString()));
            DDRate.setSelectedItem(tablePrisoner.getModel().getValueAt(row, 9).toString());
            
            byte[] img=(byte[]) tablePrisoner.getModel().getValueAt(row, 10);
            prisoner_image=img;
            ImageIcon imageIcon=new ImageIcon(new ImageIcon(img).getImage().getScaledInstance(lblImage.getWidth(), lblImage.getHeight(), Image.SCALE_SMOOTH));
            lblImage.setIcon(imageIcon);
            
            btnEdit.setEnabled(true);
            btnDelete.setEnabled(true);
            btnAdd.setEnabled(false);
            btnClear.setEnabled(true);
        }
    }

    public void clearPrisonerInfo() {
        FieldID.setText("");
        FieldCrime.setText("");
        BirthdayChooser.setDate(null);
        TextAreaAddress.setText("");
        FieldCrime.setText("");
        FieldPid.setText("");
        DDJid.setSelectedItem("<none>");
        InDateChooser.setDate(null);
        OutDateChooser.setDate(null);
        DDRate.setSelectedItem("<none>");
        lblImage.setIcon(new ImageIcon("default-image.png")); 
        prisoner_image=null;
        
        btnEdit.setEnabled(false);
        btnDelete.setEnabled(false);
        btnAdd.setEnabled(true);
    }
    
    public void showPrisoner(Prisoner prisoner) {
        FieldID.setText("" + prisoner.getId());
        FieldName.setText(prisoner.getName());
        BirthdayChooser.setDate(prisoner.getBirthday());
        TextAreaAddress.setText(prisoner.getAddress());
        DDJid.setSelectedItem(prisoner.getJailId());
        FieldPid.setText(prisoner.getPrisonerId());
        FieldCrime.setText(prisoner.getCrime());
        InDateChooser.setDate(prisoner.getSentenceStartDate());
        OutDateChooser.setDate(prisoner.getSentenceEndDate());
        DDRate.setSelectedItem(prisoner.getBehavior());
        
        byte[] img=prisoner.getImage();
        ImageIcon imageIcon=new ImageIcon(new ImageIcon(img).getImage().getScaledInstance(lblImage.getWidth(), lblImage.getHeight(), Image.SCALE_SMOOTH));
        lblImage.setIcon(imageIcon);
        
        btnEdit.setEnabled(true);
        btnDelete.setEnabled(true);
        btnAdd.setEnabled(false);
    }
    
    public Prisoner getPrisonerInfo() {
        if (!validateName() || !validateBirthday() || !validateAddress() || 
            !validateJailId() || !validatePrisonerId() || !validateCrime() || 
            !validateStartDate() || !validateEndDate() || !validateBehavior() || 
            !validateImage()) {
            return null;
        }
        try {
            Prisoner prisoner = new Prisoner();
            if (FieldID.getText() != null && !"".equals(FieldID.getText())) {
                prisoner.setId(Integer.parseInt(FieldID.getText()));
            }
            prisoner.setName(capitalizeWords(FieldName.getText().trim()));
            prisoner.setBirthday(BirthdayChooser.getDate());
            prisoner.setAddress(capitalizeWords(TextAreaAddress.getText().trim()));
            prisoner.setJailId(DDJid.getSelectedItem().toString().trim());
            prisoner.setPrisonerId(FieldPid.getText().trim());
            prisoner.setCrime(FieldCrime.getText().trim());
            prisoner.setSentenceStartDate(InDateChooser.getDate());
            prisoner.setSentenceEndDate(OutDateChooser.getDate());
            prisoner.setBehavior(DDRate.getSelectedItem().toString().trim());
            prisoner.setImage(prisoner_image);
            return prisoner;
        } catch (Exception e) {
            showMessage(e.getMessage());
        }
        return null;
    }
    
    private boolean validateName() {
        String name = FieldCrime.getText();
        if (name == null || "".equals(name.trim())) {
            FieldCrime.requestFocus();
            showMessage("Họ và tên không được trống.");
            return false;
        }
        return true;
    }
    
    private boolean validateBirthday() {
        try {
            java.util.Date today=new java.util.Date();
            Date date=BirthdayChooser.getDate();
            if (date.compareTo(today)==1) {
                BirthdayChooser.requestFocus();
                showMessage("Ngày sinh không hợp lệ");
                return false;
            }
        } catch (Exception e) {
            BirthdayChooser.requestFocus();
            showMessage("Ngày sinh không được trống");
            return false;
        }
        return true;
    }
    
    private boolean validateAddress() {
        String address = TextAreaAddress.getText();
        if (address == null || "".equals(address.trim())) {
            TextAreaAddress.requestFocus();
            showMessage("Quê quán không được trống.");
            return false;
        }
        return true;
    }
    
    private boolean validateJailId() {
        Object selectedItem = DDJid.getSelectedItem();
        if (selectedItem == null || selectedItem.toString().trim().isEmpty()) {
            DDJid.requestFocus();
            showMessage("Mã nhà tù không được để trống.");
            return false;
        }
        return true;
    }
    
    private boolean validatePrisonerId() {
        String prisonerId = FieldPid.getText();
        if (prisonerId == null || "".equals(prisonerId.trim())) {
            FieldPid.requestFocus();
            showMessage("Mã phạm nhân không được trống.");
            return false;
        }
        return true;
    }
    
    private boolean validateCrime() {
        String crime = FieldCrime.getText();
        if (crime.equals("<none>")) {
            FieldCrime.requestFocus();
            showMessage("Bạn chưa chọn tội danh");
            return false;
        }
        return true;
    }
    
    private boolean validateStartDate() {
        try {
            java.util.Date today=new java.util.Date();
            Date date=InDateChooser.getDate();
            if (date.compareTo(today)==1) {
                InDateChooser.requestFocus();
                showMessage("Ngày vào không hợp lệ");
                return false;
            }
        } catch (Exception e) {
            InDateChooser.requestFocus();
            showMessage("Ngày vào không được trống");
            return false;
        }
        return true;
    }
    
    private boolean validateEndDate() {
        try {
            Date startDate = InDateChooser.getDate();
            Date endDate = OutDateChooser.getDate();
            if (endDate.compareTo(startDate) < 0) {
                OutDateChooser.requestFocus();
                showMessage("Ngày ra phải sau ngày vào");
                return false;
            }
        } catch (Exception e) {
            OutDateChooser.requestFocus();
            showMessage("Ngày ra không được trống");
            return false;
        }
        return true;
    }
    
    private boolean validateBehavior() {
        String behavior = DDRate.getSelectedItem().toString().trim();
        if (behavior.equals("<none>")) {
            DDRate.requestFocus();
            showMessage("Bạn chưa chọn hạnh kiểm");
            return false;
        }
        return true;
    }
    
    public boolean validateImage() {
        byte[]k=prisoner_image;
        if (k == null) {
            showMessage("Bạn chưa tải ảnh lên!");
            return false;
        }
        return true;
    }
    
    public void displaySearchView() {
        //FrameSearch = new ManagerView();
        SearchDialog.setVisible(true);
    }
    
    public void displayStatisticView() {
        //FrameSearch = new ManagerView();
        StatisticView.setVisible(true);
        PrisonerView.this.setVisible(false);
        StatisticView.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                StatisticView.dispose();
                System.exit(0); // Optional: terminate the entire application
            }
        });
    }
    
    public void hideSearchView() {
        SearchDialog.setVisible(false);
    }

    public int getChooseSelectSearch(){
        if(CheckBoxName.isSelected()) return 1;
        else if(CheckBoxID.isSelected()) return 2;
        else if(CheckBoxOutYear.isSelected()) return 3;
        return 0;
    }
     
    public String validateSearch(){
        String search = FieldSearch.getText();
        if (search == null || "".equals(search.trim())) {
            FieldSearch.requestFocus();
            showMessage("Nội dung tìm kiếm không hợp lệ!");
            return "";
        }
        btnCancelSearch.setEnabled(true);
        SearchDialog.setVisible(false);
        btnAdd.setEnabled(false);
        btnEdit.setEnabled(false);
        btnDelete.setEnabled(false);
        btnClear.setEnabled(false);
        btnSearch.setEnabled(false);
        return search;
    }
    
    public void cancelSearch()
    {
        String id=FieldID.getText();
        btnCancelSearch.setEnabled(false);
        btnSearch.setEnabled(true);
        btnClear.setEnabled(true);
        if (id == null || "".equals(id.trim()))
        {
            
            btnAdd.setEnabled(true);
            btnEdit.setEnabled(false);
            btnDelete.setEnabled(false);
        }
        else
        {
            btnAdd.setEnabled(false);
            btnEdit.setEnabled(true);
            btnDelete.setEnabled(true);
        }
    }
    
    public void HideStatisticView()
    {
        StatisticView.setVisible(false);
        PrisonerView.this.setVisible(true);
    }
    
    public void showStatisticBehaviorPrisoners(List<Prisoner> list) {
        lblTable.setText("Thống kê số lượng theo hạnh kiểm");
        lblChart.setText("Biểu đồ thống kê số lượng theo hạnh kiểm");
        chart1.clear();

        Map<String, Integer> countMap = new HashMap<>();
        for (Prisoner prisoner : list) {
            String behavior = prisoner.getBehavior();
            countMap.put(behavior, countMap.getOrDefault(behavior, 0) + 1);
        }

        Object[][] statistic = new Object[countMap.size()][2];
        int index = 0;
        for (Map.Entry<String, Integer> entry : countMap.entrySet()) {
            chart1.addData(new ModelChart(entry.getKey(), new double[]{entry.getValue()}));
            statistic[index][0] = entry.getKey();
            statistic[index][1] = entry.getValue();
            index++;
        }

        String[] columnNames = {"Hạnh kiểm", "Số lượng"};
        tableStatistic.setModel(new DefaultTableModel(statistic, columnNames));
        chart1.start();
        panelChart.revalidate();
        panelChart.repaint();
    }

    public void showStatisticSentenceDuration(List<Prisoner> list) {
        lblTable.setText("Thống kê thời gian án");
        lblChart.setText("Biểu đồ thống kê thời gian án");
        chart1.clear();

        // Phân loại theo khoảng thời gian án
        Map<String, Integer> durationMap = new HashMap<>();
        durationMap.put("Dưới 1 năm", 0);
        durationMap.put("1-3 năm", 0);
        durationMap.put("3-5 năm", 0);
        durationMap.put("5-10 năm", 0);
        durationMap.put("Trên 10 năm", 0);

        for (Prisoner prisoner : list) {
            long diff = prisoner.getSentenceEndDate().getTime() - prisoner.getSentenceStartDate().getTime();
            long days = diff / (24 * 60 * 60 * 1000);
            double years = days / 365.0;
            if (years < 1) {
                durationMap.put("Dưới 1 năm", durationMap.get("Dưới 1 năm") + 1);
            } else if (years <= 3) {
                durationMap.put("1-3 năm", durationMap.get("1-3 năm") + 1);
            } else if (years <= 5) {
                durationMap.put("3-5 năm", durationMap.get("3-5 năm") + 1);
            } else if (years <= 10) {
                durationMap.put("5-10 năm", durationMap.get("5-10 năm") + 1);
            } else {
                durationMap.put("Trên 10 năm", durationMap.get("Trên 10 năm") + 1);
            }
        }

        Object[][] statistic = new Object[durationMap.size()][2];
        int index = 0;
        for (Map.Entry<String, Integer> entry : durationMap.entrySet()) {
            chart1.addData(new ModelChart(entry.getKey(), new double[]{entry.getValue()}));
            statistic[index][0] = entry.getKey();
            statistic[index][1] = entry.getValue();
            index++;
        }
        String[] columnNames = {"Thời gian án", "Số lượng"};
        tableStatistic.setModel(new DefaultTableModel(statistic, columnNames));
        chart1.start();
        panelChart.revalidate();
        panelChart.repaint();
    }
    
    private int calculateAge(Date birthdate, Date referenceDate) {
    if ((birthdate != null) && (referenceDate != null)) {
        Calendar birthCalendar = Calendar.getInstance();
        Calendar referenceCalendar = Calendar.getInstance();
        birthCalendar.setTime(birthdate);
        referenceCalendar.setTime(referenceDate);

        int birthYear = birthCalendar.get(Calendar.YEAR);
        int referenceYear = referenceCalendar.get(Calendar.YEAR);

        return referenceYear - birthYear;
    } else {
        return 0; // Hoặc giá trị mặc định khác tùy vào yêu cầu của bạn
    }
}
    
    private double ConvertToDouble(Object k)
    {
        return Double.parseDouble(k.toString());
    }
    
    public void addAddListener(ActionListener listener) {
        btnAdd.addActionListener(listener);
    }
    
    public void addEditListener(ActionListener listener) {
        btnEdit.addActionListener(listener);
    }
    
    public void addDeleteListener(ActionListener listener) {
        btnDelete.addActionListener(listener);
    }
    
    public void addClearListener(ActionListener listener) {
        btnClear.addActionListener(listener);
    }
    
    public void addSortByInDateListener(ActionListener listener) {
        btnSortByInDate.addActionListener(listener);
    }
    
    public void addSortByIDListener(ActionListener listener) {
        btnSortByID.addActionListener(listener);
    }
    
    public void addSortByNameListener(ActionListener listener) {
        btnSortByName.addActionListener(listener);
    }
    
    public void addSortByYearListener(ActionListener listener) {
        btnSortByYear.addActionListener(listener);
    }
    
    public void addSearchListener(ActionListener listener) {
        btnSearch.addActionListener(listener);
    }
    
    public void addSearchDialogListener(ActionListener listener) {
        btnSearchDialog.addActionListener(listener);
    }
    
    public void addListSelectionListener(ListSelectionListener listener) {
        tablePrisoner.getSelectionModel().addListSelectionListener(listener);
    }
    
    public void addSearchDiaLogListener(ActionListener listener){
        btnSearchDialog.addActionListener(listener);
    }
    
    public void addCancelSearchListener(ActionListener listener){
        btnCancelSearch.addActionListener(listener);
    }
    
    public void addImageListener(ActionListener listener){
        btnImage.addActionListener(listener);
    }
    
    public void addCancelDialogListener(ActionListener listener){
        btnCancelDialog1.addActionListener(listener);
    }
    
    public void addUndoListener(ActionListener listener){
        btnManagerUndo.addActionListener(listener);
    }
    public void addStatisticListener(ActionListener listener){
        btnStatistic.addActionListener(listener);
    }
    
    public void addStatisticTimeListener(ActionListener listener){
        btnStatisticRemainTime.addActionListener(listener);
    }
    
    public void addStatisticBehaviorListener(ActionListener listener){
        btnStatisticBehavior.addActionListener(listener);
    }
    
    
    public void addStatisticUnderListener(ActionListener listener){
        btnStatisticUnder.addActionListener(listener);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Address;
    private com.toedter.calendar.JDateChooser BirthdayChooser;
    private javax.swing.JCheckBox CheckBoxID;
    private javax.swing.JCheckBox CheckBoxName;
    private javax.swing.JCheckBox CheckBoxOutYear;
    private javax.swing.JComboBox<String> DDJid;
    private javax.swing.JComboBox<String> DDRate;
    private javax.swing.JTextField FieldCrime;
    private javax.swing.JTextField FieldID;
    private javax.swing.JTextField FieldName;
    private javax.swing.JTextField FieldPid;
    private javax.swing.JTextField FieldSearch;
    private javax.swing.JLabel ID;
    private com.toedter.calendar.JDateChooser InDateChooser;
    private javax.swing.JLabel Jid;
    private javax.swing.JLabel NgayRa;
    private javax.swing.JLabel NgaySinh;
    private javax.swing.JLabel NgayVao;
    private com.toedter.calendar.JDateChooser OutDateChooser;
    private javax.swing.JLabel Pid;
    private javax.swing.JLabel Rate;
    private javax.swing.JScrollPane ScrollPaneStatistic;
    private javax.swing.JDialog SearchDialog;
    private javax.swing.JFrame StatisticView;
    private javax.swing.JLabel Ten;
    private javax.swing.JTextArea TextAreaAddress;
    private javax.swing.JLabel Toi;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnCancelDialog1;
    private javax.swing.JButton btnCancelSearch;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.ButtonGroup btnGroupSearch;
    private javax.swing.JButton btnImage;
    private javax.swing.JButton btnManagerUndo;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnSearchDialog;
    private javax.swing.JButton btnSortByID;
    private javax.swing.JButton btnSortByInDate;
    private javax.swing.JButton btnSortByName;
    private javax.swing.JButton btnSortByYear;
    private javax.swing.JButton btnStatistic;
    private javax.swing.JButton btnStatisticBehavior;
    private javax.swing.JButton btnStatisticRemainTime;
    private javax.swing.JButton btnStatisticUnder;
    private com.raven.chart.Chart chart1;
    private javax.swing.JLabel imgLb;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblChart;
    private javax.swing.JLabel lblImage;
    private javax.swing.JLabel lblTable;
    private javax.swing.JPanel panelChart;
    private javax.swing.JTable tablePrisoner;
    private javax.swing.JTable tableStatistic;
    private org.jdesktop.animation.timing.TimingTargetAdapter timingTargetAdapter1;
    // End of variables declaration//GEN-END:variables
}
