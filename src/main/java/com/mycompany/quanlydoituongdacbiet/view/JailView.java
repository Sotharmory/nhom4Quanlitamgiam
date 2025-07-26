/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.quanlydoituongdacbiet.view;
import com.mycompany.quanlydoituongdacbiet.action.ManagerPrisoners;
import com.mycompany.quanlydoituongdacbiet.entity.Jail;
import com.mycompany.quanlydoituongdacbiet.entity.Prisoner;
import com.mycompany.quanlydoituongdacbiet.entity.Visit;
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
import java.util.LinkedHashMap;
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
public class JailView extends javax.swing.JFrame {

    /**
     * Creates new form ManagerView
     */
    private SimpleDateFormat fDate=new SimpleDateFormat("dd/MM/yyyy");
    private String filename=null;
    private String[] columnNames = new String[]{"ID", "Mã nhà tù", "Tên nhà tù", "Sức chứa", "Số tù nhân hiện tại", "Địa chỉ"};
    private String [] columnNames2 = new String [] {
        "<none>","Số lượng"};
    private Object data = new Object [][] {};
    private ManagerPrisoners managerPrisoner;
    Chart chart=new Chart();

    public JailView() {
        managerPrisoner = new ManagerPrisoners();
        initComponents();
        btnAdd.setEnabled(true);
        btnEdit.setEnabled(false);
        btnDelete.setEnabled(false);
        btnSearch.setEnabled(true); 
        tableJail.setDefaultRenderer(Object.class, new MyRenderer());
        tableStatistic.setDefaultRenderer(Object.class, new MyRenderer2());
        FieldCurrent.setBackground(new Color(0, 204, 255));
        chart1.addLegend("Số lượng", new Color(0, 178, 238));
        CardLayout cl = (CardLayout) SwitchPanel.getLayout();
        cl.show(SwitchPanel, "byText");
    }
  
    
    public class MyRenderer extends DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
            TableColumnModel columnModel=table.getColumnModel();
            columnModel.getColumn(0).setPreferredWidth(80);  // STT
            columnModel.getColumn(1).setPreferredWidth(100); // JID
            columnModel.getColumn(2).setPreferredWidth(200); // Tên
            columnModel.getColumn(3).setPreferredWidth(80);  // Max
            columnModel.getColumn(4).setPreferredWidth(80); // Current
            columnModel.getColumn(5).setPreferredWidth(300);  // Address
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
        SearchPanel = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        CheckBoxJid = new javax.swing.JCheckBox();
        jLabel11 = new javax.swing.JLabel();
        CheckBoxMax = new javax.swing.JCheckBox();
        CheckBoxCurrent = new javax.swing.JCheckBox();
        btnCancelDialog1 = new javax.swing.JButton();
        btnSearchDialog = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        SwitchPanel = new javax.swing.JPanel();
        ByText = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        FieldSearch = new javax.swing.JTextField();
        ByRange = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        FieldFrom = new javax.swing.JTextField();
        FieldTo = new javax.swing.JTextField();
        btnGroupSearch = new javax.swing.ButtonGroup();
        StatisticView = new javax.swing.JFrame();
        panelChart = new javax.swing.JPanel();
        ScrollPaneStatistic = new javax.swing.JScrollPane();
        tableStatistic = new javax.swing.JTable();
        lblTable = new javax.swing.JLabel();
        lblChart = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        btnStatisticDistribution = new javax.swing.JButton();
        btnStatisticUnder = new javax.swing.JButton();
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
        btnStatistic = new javax.swing.JButton();
        btnSortByMax = new javax.swing.JButton();
        btnSortByID = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        TextAreaLocation = new javax.swing.JTextArea();
        Current = new javax.swing.JLabel();
        Jid = new javax.swing.JLabel();
        FieldJid = new javax.swing.JTextField();
        ID = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableJail = new javax.swing.JTable();
        Purpose = new javax.swing.JLabel();
        FieldID = new javax.swing.JTextField();
        Max = new javax.swing.JLabel();
        Ten = new javax.swing.JLabel();
        FieldName = new javax.swing.JTextField();
        FieldMax = new javax.swing.JTextField();
        FieldCurrent = new javax.swing.JTextField();
        btnSortByCurrent = new javax.swing.JButton();

        SearchDialog.setResizable(false);
        SearchDialog.setSize(new java.awt.Dimension(450, 370));

        SearchPanel.setLayout(null);

        jLabel10.setFont(new java.awt.Font("Times New Roman", 3, 24)); // NOI18N
        jLabel10.setText("Tìm kiếm");
        SearchPanel.add(jLabel10);
        jLabel10.setBounds(170, 30, 110, 29);

        jLabel12.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel12.setText("Chọn tiêu chí tìm kiếm:");
        SearchPanel.add(jLabel12);
        jLabel12.setBounds(40, 190, 290, 29);

        btnGroupSearch.add(CheckBoxJid);
        CheckBoxJid.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        CheckBoxJid.setText("Mã nhà tù");
        CheckBoxJid.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        CheckBoxJid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CheckBoxJidActionPerformed(evt);
            }
        });
        SearchPanel.add(CheckBoxJid);
        CheckBoxJid.setBounds(20, 230, 120, 20);
        CheckBoxJid.setOpaque(false);

        jLabel11.setIcon(new ImageIcon(getClass().getResource("/images/search.png")));
        SearchPanel.add(jLabel11);
        jLabel11.setBounds(270, 40, 30, 30);

        btnGroupSearch.add(CheckBoxMax);
        CheckBoxMax.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        CheckBoxMax.setText("Sức chứa");
        CheckBoxMax.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        CheckBoxMax.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CheckBoxMaxActionPerformed(evt);
            }
        });
        SearchPanel.add(CheckBoxMax);
        CheckBoxMax.setBounds(140, 230, 100, 20);
        CheckBoxMax.setOpaque(false);

        btnGroupSearch.add(CheckBoxCurrent);
        CheckBoxCurrent.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        CheckBoxCurrent.setText("Số tù nhân hiện tại");
        CheckBoxCurrent.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        CheckBoxCurrent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CheckBoxCurrentActionPerformed(evt);
            }
        });
        SearchPanel.add(CheckBoxCurrent);
        CheckBoxCurrent.setBounds(250, 230, 160, 20);
        CheckBoxCurrent.setOpaque(false);

        btnCancelDialog1.setBackground(new java.awt.Color(255, 255, 255, 0));
        btnCancelDialog1.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnCancelDialog1.setText("Hủy");
        btnCancelDialog1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        SearchPanel.add(btnCancelDialog1);
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
        SearchPanel.add(btnSearchDialog);
        btnSearchDialog.setBounds(50, 270, 140, 50);
        btnSearchDialog.setBorder(new RoundedBorder(20));
        btnSearchDialog.setOpaque(false);

        jLabel15.setIcon(new ImageIcon(getClass().getResource("/images/viewSearchView.png")));
        jLabel15.setText("=");
        SearchPanel.add(jLabel15);
        jLabel15.setBounds(0, 0, 450, 360);

        SwitchPanel.setLayout(new java.awt.CardLayout());

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel8.setText("Nhập nội dung tìm kiếm:");
        jLabel8.setOpaque(true);

        FieldSearch.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        FieldSearch.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 0, 102)));
        FieldSearch.setOpaque(true);

        javax.swing.GroupLayout ByTextLayout = new javax.swing.GroupLayout(ByText);
        ByText.setLayout(ByTextLayout);
        ByTextLayout.setHorizontalGroup(
            ByTextLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 440, Short.MAX_VALUE)
            .addGroup(ByTextLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(ByTextLayout.createSequentialGroup()
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 95, Short.MAX_VALUE)))
            .addGroup(ByTextLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(ByTextLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(FieldSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        ByTextLayout.setVerticalGroup(
            ByTextLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 110, Short.MAX_VALUE)
            .addGroup(ByTextLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(ByTextLayout.createSequentialGroup()
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 76, Short.MAX_VALUE)))
            .addGroup(ByTextLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(ByTextLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(FieldSearch, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        FieldSearch.setOpaque(false);

        SwitchPanel.add(ByText, "byText");

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel1.setText("Đến");
        jLabel1.setOpaque(true);

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        jLabel2.setText("Từ");
        jLabel2.setOpaque(true);

        FieldFrom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FieldFromActionPerformed(evt);
            }
        });

        FieldTo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FieldToActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ByRangeLayout = new javax.swing.GroupLayout(ByRange);
        ByRange.setLayout(ByRangeLayout);
        ByRangeLayout.setHorizontalGroup(
            ByRangeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ByRangeLayout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addGroup(ByRangeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ByRangeLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1))
                    .addGroup(ByRangeLayout.createSequentialGroup()
                        .addComponent(FieldFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 192, Short.MAX_VALUE)
                        .addComponent(FieldTo, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(38, 38, 38))
        );
        ByRangeLayout.setVerticalGroup(
            ByRangeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ByRangeLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(ByRangeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ByRangeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(FieldTo, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(FieldFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        SwitchPanel.add(ByRange, "byRange");
        ByRange.setVisible(false);

        SearchPanel.add(SwitchPanel);
        SwitchPanel.setBounds(0, 70, 440, 110);

        javax.swing.GroupLayout SearchDialogLayout = new javax.swing.GroupLayout(SearchDialog.getContentPane());
        SearchDialog.getContentPane().setLayout(SearchDialogLayout);
        SearchDialogLayout.setHorizontalGroup(
            SearchDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(SearchPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 440, Short.MAX_VALUE)
        );
        SearchDialogLayout.setVerticalGroup(
            SearchDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(SearchPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 355, Short.MAX_VALUE)
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

        btnStatisticDistribution.setBackground(new java.awt.Color(0, 0, 102));
        btnStatisticDistribution.setFont(new java.awt.Font("Times New Roman", 0, 18)); // NOI18N
        btnStatisticDistribution.setForeground(new java.awt.Color(255, 255, 255));
        btnStatisticDistribution.setIcon(new ImageIcon(getClass().getResource("/images/Calendar.png")));
        btnStatisticDistribution.setText("Mật độ phân bố");
        btnStatisticDistribution.setActionCommand("Thống kê");
        btnStatisticDistribution.setBorder(null);
        btnStatisticDistribution.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnStatisticDistribution.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnStatisticDistributionActionPerformed(evt);
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

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(btnStatisticUnder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(btnStatisticDistribution)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(113, 113, 113)
                .addComponent(btnStatisticDistribution, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(174, 174, 174)
                .addComponent(btnStatisticUnder, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(121, Short.MAX_VALUE))
        );

        btnStatisticDistribution.getAccessibleContext().setAccessibleName("Thống kê");

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
                    .addComponent(chart1, javax.swing.GroupLayout.DEFAULT_SIZE, 444, Short.MAX_VALUE))
                .addContainerGap(416, Short.MAX_VALUE))
        );
        panelChartLayout.setVerticalGroup(
            panelChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelChartLayout.createSequentialGroup()
                .addGap(74, 74, 74)
                .addGroup(panelChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTable, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblChart, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelChartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ScrollPaneStatistic, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(chart1, javax.swing.GroupLayout.DEFAULT_SIZE, 282, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout StatisticViewLayout = new javax.swing.GroupLayout(StatisticView.getContentPane());
        StatisticView.getContentPane().setLayout(StatisticViewLayout);
        StatisticViewLayout.setHorizontalGroup(
            StatisticViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(StatisticViewLayout.createSequentialGroup()
                .addComponent(panelChart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        StatisticViewLayout.setVerticalGroup(
            StatisticViewLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(StatisticViewLayout.createSequentialGroup()
                .addComponent(panelChart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
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
        Purpose.setOpaque(false);

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
        btnStatistic.setBounds(910, 320, 150, 40);
        //btnSortByID.setOpaque(false);

        btnSortByMax.setBackground(new java.awt.Color(51, 204, 255));
        btnSortByMax.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        btnSortByMax.setText("Sắp xếp theo sức chứa");
        btnSortByMax.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSortByMax.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSortByMaxActionPerformed(evt);
            }
        });
        jPanel1.add(btnSortByMax);
        btnSortByMax.setBounds(680, 320, 180, 40);

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
        btnSortByID.setBounds(200, 320, 150, 40);
        //btnSortByID.setOpaque(false);

        TextAreaLocation.setBackground(new java.awt.Color(255, 255, 255, 0));
        TextAreaLocation.setColumns(20);
        TextAreaLocation.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        TextAreaLocation.setRows(5);
        TextAreaLocation.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 51, 153)));
        jScrollPane2.setViewportView(TextAreaLocation);
        TextAreaLocation.setOpaque(false);

        jPanel1.add(jScrollPane2);
        jScrollPane2.setBounds(490, 230, 250, 70);
        jScrollPane2.setOpaque(false);

        Current.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        Current.setText("Số tù nhân hiện tại:");
        jPanel1.add(Current);
        Current.setBounds(620, 150, 170, 42);

        Jid.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        Jid.setText("Mã nhà tù:");
        jPanel1.add(Jid);
        Jid.setBounds(470, 50, 100, 40);

        FieldJid.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        FieldJid.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 51, 102)));
        FieldJid.setOpaque(true);
        FieldJid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FieldJidActionPerformed(evt);
            }
        });
        jPanel1.add(FieldJid);
        FieldJid.setBounds(570, 50, 100, 40);
        FieldJid.setOpaque(false);

        ID.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        ID.setText("ID:");
        jPanel1.add(ID);
        ID.setBounds(200, 60, 60, 21);

        jScrollPane1.setBackground(new java.awt.Color(0, 51, 153, 125));

        tableJail.setFont(new java.awt.Font("Times New Roman", 0, 12)); // NOI18N
        tableJail.setModel(new javax.swing.table.DefaultTableModel(
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
        tableJail.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_LAST_COLUMN);
        tableJail.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        tableJail.setRowHeight(30);
        jScrollPane1.setViewportView(tableJail);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(200, 370, 1020, 270);

        Purpose.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        Purpose.setText("Địa chỉ nhà tù:");
        jPanel1.add(Purpose);
        Purpose.setBounds(360, 250, 120, 42);

        FieldID.setEditable(false);
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

        Max.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        Max.setText("Sức chứa:");
        jPanel1.add(Max);
        Max.setBounds(790, 50, 90, 42);

        Ten.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        Ten.setText("Tên nhà tù:");
        jPanel1.add(Ten);
        Ten.setBounds(280, 150, 90, 40);

        FieldName.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        FieldName.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 51, 102)));
        FieldName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FieldNameActionPerformed(evt);
            }
        });
        jPanel1.add(FieldName);
        FieldName.setBounds(370, 150, 220, 40);
        FieldJid.setOpaque(false);

        FieldMax.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        FieldMax.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 51, 102)));
        FieldMax.setOpaque(true);
        FieldMax.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FieldMaxActionPerformed(evt);
            }
        });
        jPanel1.add(FieldMax);
        FieldMax.setBounds(880, 50, 100, 40);
        FieldJid.setOpaque(false);

        FieldCurrent.setBackground(Color.WHITE);
        FieldCurrent.setEditable(false);
        FieldCurrent.setFont(new java.awt.Font("Times New Roman", 0, 20)); // NOI18N
        FieldCurrent.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 51, 102)));
        FieldCurrent.setOpaque(true);
        FieldCurrent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FieldCurrentActionPerformed(evt);
            }
        });
        jPanel1.add(FieldCurrent);
        FieldCurrent.setBounds(790, 150, 90, 40);
        FieldJid.setOpaque(false);

        btnSortByCurrent.setBackground(new java.awt.Color(51, 204, 255));
        btnSortByCurrent.setFont(new java.awt.Font("Times New Roman", 0, 16)); // NOI18N
        btnSortByCurrent.setText("Sắp xếp theo số tù nhân hiện tại");
        btnSortByCurrent.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSortByCurrent.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSortByCurrentActionPerformed(evt);
            }
        });
        jPanel1.add(btnSortByCurrent);
        btnSortByCurrent.setBounds(400, 320, 230, 40);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 21, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 653, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(665, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CheckBoxJidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CheckBoxJidActionPerformed
        CardLayout cl = (CardLayout) SwitchPanel.getLayout();
        cl.show(SwitchPanel, "byText");
    }//GEN-LAST:event_CheckBoxJidActionPerformed

    private void btnSortByIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSortByIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSortByIDActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnCancelSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCancelSearchActionPerformed

    private void FieldJidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FieldJidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FieldJidActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void FieldIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FieldIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FieldIDActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSearchActionPerformed

    private void btnSortByMaxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSortByMaxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSortByMaxActionPerformed

    private void btnManagerUndoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnManagerUndoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnManagerUndoActionPerformed

    private void btnStatisticActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStatisticActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnStatisticActionPerformed

    private void StatisticViewWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_StatisticViewWindowOpened
        // TODO add your handling code here:
    }//GEN-LAST:event_StatisticViewWindowOpened

    private void btnStatisticUnderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStatisticUnderActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnStatisticUnderActionPerformed

    private void btnStatisticDistributionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnStatisticDistributionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnStatisticDistributionActionPerformed

    private void FieldNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FieldNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FieldNameActionPerformed

    private void btnSearchDialogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSearchDialogActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSearchDialogActionPerformed

    private void CheckBoxCurrentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CheckBoxCurrentActionPerformed
        CardLayout cl = (CardLayout) SwitchPanel.getLayout();
        cl.show(SwitchPanel, "byRange");
    }//GEN-LAST:event_CheckBoxCurrentActionPerformed

    private void CheckBoxMaxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CheckBoxMaxActionPerformed
        CardLayout cl = (CardLayout) SwitchPanel.getLayout();
        cl.show(SwitchPanel, "byRange");
    }//GEN-LAST:event_CheckBoxMaxActionPerformed

    private void FieldCurrentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FieldCurrentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FieldCurrentActionPerformed

    private void FieldMaxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FieldMaxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FieldMaxActionPerformed

    private void btnSortByCurrentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSortByCurrentActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSortByCurrentActionPerformed

    private void FieldFromActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FieldFromActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FieldFromActionPerformed

    private void FieldToActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FieldToActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FieldToActionPerformed

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
            java.util.logging.Logger.getLogger(VisitView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VisitView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VisitView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VisitView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VisitView().setVisible(true);
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
    
    
    /**
     * hiển thị list  vào bảng table
     * 
     * @param list
     */
    public void showListJails(List<Jail> list) {
        int size = list.size();
        Object[][] jails = new Object[size][6];
        for (int i = 0; i < size; i++) {
            Jail j = list.get(i);
            jails[i][0] = j.getId();
            jails[i][1] = j.getJailId();
            jails[i][2] = j.getName();
            jails[i][3] = j.getCapacity();
            jails[i][4] = j.getCurrentInmates();
            jails[i][5] = j.getLocation();
        }
        tableJail.setModel(new DefaultTableModel(jails, columnNames));
    }

    public void fillFromSelectedRow() throws ParseException {
        int row = tableJail.getSelectedRow();
        if (row >= 0) {
            FieldID.setText(tableJail.getModel().getValueAt(row, 0).toString());
            FieldJid.setText(tableJail.getModel().getValueAt(row, 1).toString());
            FieldName.setText(tableJail.getModel().getValueAt(row, 2).toString());
            FieldMax.setText(tableJail.getModel().getValueAt(row, 3).toString());
            FieldCurrent.setText(tableJail.getModel().getValueAt(row, 4).toString());
            TextAreaLocation.setText(tableJail.getModel().getValueAt(row, 5).toString());

            
            btnEdit.setEnabled(true);
            btnDelete.setEnabled(true);
            btnAdd.setEnabled(false);
            btnClear.setEnabled(true);
        }
    }

    public void clearInfo() {
        FieldID.setText("");
        FieldJid.setText("");
        FieldName.setText("");
        FieldMax.setText("");
        FieldCurrent.setText("");
        TextAreaLocation.setText("");
        
        btnEdit.setEnabled(false);
        btnDelete.setEnabled(false);
        btnAdd.setEnabled(true);
    }

    public Jail getJailInfo() {
        if (!validateJailName() || !validateJailId() || !validateCapacity() || !validateLocation()) {
            return null;
        }
        try {
            Jail jail = new Jail();
            if (FieldID.getText() != null && !FieldID.getText().isEmpty()) {
                jail.setId(Integer.parseInt(FieldID.getText()));
            }
            jail.setName(FieldName.getText().trim());
            jail.setJailId(FieldJid.getText().trim());
            jail.setCapacity(Integer.parseInt(FieldMax.getText().trim()));
            if (FieldCurrent.getText() != null && !FieldCurrent.getText().isEmpty()){
                jail.setCurrentInmates(Integer.parseInt(FieldCurrent.getText().trim()));
            }
            jail.setLocation(TextAreaLocation.getText().trim());
            return jail ;
        } catch (Exception e) {
            showMessage(e.getMessage());
        }
        return null;
    }

    // validate
    private boolean validateJailName() {
        String name = FieldName.getText();
        if (name == null || name.trim().isEmpty()) {
            FieldName.requestFocus();
            showMessage("Tên nhà tù không được trống.");
            return false;
        }
        return true;
    }
    
    private boolean validateJailId() {
        String vid = FieldJid.getText();
        if (vid == null || vid.trim().isEmpty()) {
            FieldJid.requestFocus();
            showMessage("Mã nhà tù không được trống.");
            return false;
        }
        return true;
    }
    
    private boolean validateCapacity() {
        String input = FieldMax.getText();
        if (input == null || input.isEmpty()) {
            FieldMax.requestFocus();
            showMessage("Sức chứa không được để trống.");
            return false;
        }
        try {
            int capacity = Integer.parseInt(input.trim());
            if (capacity <= 0) {
                FieldMax.requestFocus();
                showMessage("Sức chứa không được nhỏ hơn 0.");
                return false;
            }
        }
        catch (NumberFormatException e) {
            FieldMax.requestFocus();
            showMessage("Sức chứa phải là một số nguyên.");
            return false;
        }
        return true;
    }
    
    private boolean validateLocation() {
        String location = TextAreaLocation.getText();
        if (location == null || location.trim().isEmpty()) {
            FieldJid.requestFocus();
            showMessage("Địa chỉ nhà tù không được trống.");
            return false;
        }
        return true;
    }

    // thống kê

    
    
    public void displayStatisticView() {
        StatisticView.setVisible(true);
        JailView.this.setVisible(false);
        StatisticView.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                StatisticView.dispose();
                System.exit(0); // Optional: terminate the entire application
            }
        });
    }
        
    public void HideStatisticView()
    {
        StatisticView.setVisible(false);
        JailView.this.setVisible(true);
    }
    
    public void showStatisticByDistribution(List<Jail> list){
        lblTable.setText("Thống kê phân bố tù nhân");
        lblChart.setText("Biểu đồ thống kê phân bố tù nhân");
        chart1.clear();

        Map<String, Integer> countMap = new LinkedHashMap<>();
        countMap.put("Dưới 25%", 0);
        countMap.put("25-50%", 0);
        countMap.put("50-75%", 0);
        countMap.put("75-100%", 0);
        countMap.put("Trên 100%", 0);
        for (Jail jail : list) {
            float percent =(float)jail.getCurrentInmates()/jail.getCapacity();
            if (percent < 0.25) {
                countMap.put("Dưới 25%", countMap.get("Dưới 25%") + 1);
            } else if (percent <= 0.5) {
                countMap.put("25-50%", countMap.get("25-50%") + 1);
            } else if (percent <= 0.75) {
                countMap.put("50-75%", countMap.get("50-75%") + 1);
            } else if (percent <= 1) {
                countMap.put("75-100%", countMap.get("75-100%") + 1);
            } else {
                countMap.put("Trên 100%", countMap.get("Trên 100%") + 1);
            }
        }

        Object[][] statistic = new Object[countMap.size()][2];
        int index = 0;
        for (Map.Entry<String, Integer> entry : countMap.entrySet()) {
            chart1.addData(new ModelChart(entry.getKey(), new double[]{entry.getValue()}));
            statistic[index][0] = entry.getKey();
            statistic[index][1] = entry.getValue();
            index++;
        }

        String[] columnNames = {"Phân bố", "Số lượng"};
        tableStatistic.setModel(new DefaultTableModel(statistic, columnNames));
        chart1.start();
        panelChart.revalidate();
        panelChart.repaint();
    }
    
    //search 
    
    public void displaySearchView() {
        SearchDialog.setVisible(true);
        CardLayout cl = (CardLayout) SwitchPanel.getLayout();
        cl.show(SwitchPanel, "byText");
    }
    
    public void hideSearchView() {
        SearchDialog.setVisible(false);
    }

    public int getChooseSelectSearch(){
        if(CheckBoxJid.isSelected()) return 1;
        else if(CheckBoxMax.isSelected()) return 2;
        else if(CheckBoxCurrent.isSelected()) return 3;
        return 0;
    }
    
    public Integer[] validateSearchRange(){
        String from = FieldFrom.getText();
        String to = FieldTo.getText();
        if (from == null || to==null) {
            showMessage("Ngày thăm không được trống.");
            return null;
        }
        try {
            int i_from = Integer.parseInt(from.trim());
            int i_to = Integer.parseInt(to.trim());
            if (i_from > i_to) {
                showMessage("Giá trị 'Từ' phải nhỏ hơn hoặc bằng 'Đến'.");
                return null;
            }
            btnCancelSearch.setEnabled(true);
            SearchDialog.setVisible(false);
            btnAdd.setEnabled(false);
            btnEdit.setEnabled(false);
            btnDelete.setEnabled(false);
            btnClear.setEnabled(false);
            btnSearch.setEnabled(false);
            return new Integer[]{i_from, i_to} ;
        }
        catch (NumberFormatException e) {
            showMessage("Cần phải điền vào số nguyên.");
            return null;
        }
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
    
    private double ConvertToDouble(Object k)
    {
        return Double.parseDouble(k.toString());
    }
    // listener cho controller
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
    
    
    public void addSortByIDListener(ActionListener listener) {
        btnSortByID.addActionListener(listener);
    }
    
    
    public void addSortByMaxListener(ActionListener listener) {
        btnSortByMax.addActionListener(listener);
    }
    
    public void addSortByCurrentListener(ActionListener listener) {
        btnSortByCurrent.addActionListener(listener);
    }
    
    public void addSearchListener(ActionListener listener) {
        btnSearch.addActionListener(listener);
    }
    
    public void addSearchDialogListener(ActionListener listener) {
        btnSearchDialog.addActionListener(listener);
    }
    
    public void addListSelectionListener(ListSelectionListener listener) {
        tableJail.getSelectionModel().addListSelectionListener(listener);
    }
    
    public void addSearchDiaLogListener(ActionListener listener){
        btnSearchDialog.addActionListener(listener);
    }
    
    public void addCancelSearchListener(ActionListener listener){
        btnCancelSearch.addActionListener(listener);
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
    
    public void addStatisticByDistributionListener(ActionListener listener){
        btnStatisticDistribution.addActionListener(listener);
    }
    
    public void addStatisticUnderListener(ActionListener listener){
        btnStatisticUnder.addActionListener(listener);
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel ByRange;
    private javax.swing.JPanel ByText;
    private javax.swing.JCheckBox CheckBoxCurrent;
    private javax.swing.JCheckBox CheckBoxJid;
    private javax.swing.JCheckBox CheckBoxMax;
    private javax.swing.JLabel Current;
    private javax.swing.JTextField FieldCurrent;
    private javax.swing.JTextField FieldFrom;
    private javax.swing.JTextField FieldID;
    private javax.swing.JTextField FieldJid;
    private javax.swing.JTextField FieldMax;
    private javax.swing.JTextField FieldName;
    private javax.swing.JTextField FieldSearch;
    private javax.swing.JTextField FieldTo;
    private javax.swing.JLabel ID;
    private javax.swing.JLabel Jid;
    private javax.swing.JLabel Max;
    private javax.swing.JLabel Purpose;
    private javax.swing.JScrollPane ScrollPaneStatistic;
    private javax.swing.JDialog SearchDialog;
    private javax.swing.JPanel SearchPanel;
    private javax.swing.JFrame StatisticView;
    private javax.swing.JPanel SwitchPanel;
    private javax.swing.JLabel Ten;
    private javax.swing.JTextArea TextAreaLocation;
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnCancelDialog1;
    private javax.swing.JButton btnCancelSearch;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.ButtonGroup btnGroupSearch;
    private javax.swing.JButton btnManagerUndo;
    private javax.swing.JButton btnSearch;
    private javax.swing.JButton btnSearchDialog;
    private javax.swing.JButton btnSortByCurrent;
    private javax.swing.JButton btnSortByID;
    private javax.swing.JButton btnSortByMax;
    private javax.swing.JButton btnStatistic;
    private javax.swing.JButton btnStatisticDistribution;
    private javax.swing.JButton btnStatisticUnder;
    private com.raven.chart.Chart chart1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblChart;
    private javax.swing.JLabel lblTable;
    private javax.swing.JPanel panelChart;
    private javax.swing.JTable tableJail;
    private javax.swing.JTable tableStatistic;
    private org.jdesktop.animation.timing.TimingTargetAdapter timingTargetAdapter1;
    // End of variables declaration//GEN-END:variables
}
