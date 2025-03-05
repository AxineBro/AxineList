package basicFile;

import sun.security.provider.NativePRNG;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Arrays;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;

public class Frame extends JFrame {

    private JPanel contentPane;
    private JTable tableOutput;
    private JTable tableInput;
    private DefaultTableModel tableModel = new DefaultTableModel();
    private DefaultTableModel outTableModel = new DefaultTableModel();

    private JFileChooser fileChooserOpen;
    private JPanel panelMain;
    private JLabel labelChoiseFile;
    {
        tableModel.addColumn("");
        tableModel.addRow(new Object[]{});
    }
    public Frame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 479, 340);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

        JPanel panelFileName = new JPanel();
        contentPane.add(panelFileName);

        labelChoiseFile = new JLabel("Файл: не выбран");
        labelChoiseFile.setHorizontalAlignment(SwingConstants.CENTER);
        labelChoiseFile.setFont(new Font("Times New Roman", Font.BOLD, 11));
        panelFileName.add(labelChoiseFile);

        JPanel panel = new JPanel();
        contentPane.add(panel);
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

        JScrollPane scrollPane = new JScrollPane();

        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        panel.add(scrollPane);

        tableInput = new JTable(tableModel);
        tableInput.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    SwingUtilities.invokeLater(() -> {
                        Object[] array = getRows(tableModel, 0);
                        AxineLinkedList<Object> axineList = new AxineLinkedList<>();
                        for (Object obj : array) {
                            axineList.add(obj);
                        }
                        System.out.print(Arrays.toString(axineList.toArray()));
                        axineList.reverseAll();
                        setTabel(tableOutput, outTableModel, axineList.toArray());
                    });
                }
            }
        });
        tableInput.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tableInput.setCellSelectionEnabled(true);
        scrollPane.setViewportView(tableInput);

        JPanel addInTable = new JPanel();
        contentPane.add(addInTable);
        addInTable.setLayout(new BoxLayout(addInTable, BoxLayout.X_AXIS));

        JButton addColumButton = createButton("/image/addColum.png");
        addColumButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addColum(tableInput, tableModel, tableModel.getColumnCount()-1);
            }
        });
        addInTable.add(addColumButton);

        JButton deleteColumButton = createButton("/image/deleteColum.png");
        deleteColumButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteColum(tableModel);
            }
        });
        addInTable.add(deleteColumButton);

        /*JButton addRowsButton = createButton("/image/addRows.png");
        addInTable.add(addRowsButton);

        JButton deleteRowsButton = createButton("/image/deleteRows.png");
        addInTable.add(deleteRowsButton);*/

        Component horizontalGlue_2 = Box.createHorizontalGlue();
        addInTable.add(horizontalGlue_2);

        JPanel panelInputSveLoad = new JPanel();
        contentPane.add(panelInputSveLoad);
        panelInputSveLoad.setLayout(new BoxLayout(panelInputSveLoad, BoxLayout.X_AXIS));

        Component horizontalGlue = Box.createHorizontalGlue();
        panelInputSveLoad.add(horizontalGlue);

        JButton openButton = new JButton("Открыть");
        openButton.setBackground(new Color(255, 255, 255));
        openButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    clearTabel(tableModel);
                    clearTabel(outTableModel);
                    fileChooserOpen = new JFileChooser();
                    if (fileChooserOpen.showOpenDialog(Frame.this) == JFileChooser.APPROVE_OPTION) {
                        labelChoiseFile.setText("Файл: " + fileChooserOpen.getSelectedFile());
                        Object[] arr = new utils.Files<Integer>().readFile(fileChooserOpen.getSelectedFile());
                        AxineLinkedList<Object> axineList = new AxineLinkedList<>();
                        for(int i = 0;  i < arr.length; i++){
                            axineList.add(arr[i]);
                            addColum(tableInput,tableModel,i);
                            addColum(tableOutput,outTableModel,i);
                        }
                        tableModel.addRow(arr);
                        axineList.reverseAll();
                        outTableModel.addRow(axineList.toArray());
                    }
                } catch (Exception err) {}
            }
        });
        panelInputSveLoad.add(openButton);

        Component horizontalStrut = Box.createHorizontalStrut(5);
        horizontalStrut.setMaximumSize(getPreferredSize());
        panelInputSveLoad.add(horizontalStrut);

        JButton saveButtonInput = new JButton("Сохранить");
        saveButtonInput.setBackground(new Color(255, 255, 255));
        saveButtonInput.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fileChooserOpen = new JFileChooser();
                if (fileChooserOpen.showSaveDialog(Frame.this) == JFileChooser.APPROVE_OPTION) {
                    Object[] arrat =getRows(tableModel, 0);
                    String file = fileChooserOpen.getSelectedFile().getPath();
                    if (!file.toLowerCase().endsWith(".txt")) {
                        file += ".txt";
                    }
                    new utils.Files<Integer>().writeFile(new File(file), arrat);
                }
            }
        });
        panelInputSveLoad.add(saveButtonInput);

        JPanel paneResultName = new JPanel();
        contentPane.add(paneResultName);

        JLabel lblNewLabel_1 = new JLabel("Результат реверса");
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 11));

        paneResultName.add(lblNewLabel_1);
        JPanel panel_2 = new JPanel();
        contentPane.add(panel_2);
        panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.X_AXIS));

        JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        panel_2.add(scrollPane_1);

        tableOutput = new JTable(outTableModel);
        tableOutput.setEnabled(false);
        tableOutput.setCellSelectionEnabled(true);
        tableOutput.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        scrollPane_1.setViewportView(tableOutput);

        JPanel panelOutputSave = new JPanel();
        contentPane.add(panelOutputSave);
        panelOutputSave.setLayout(new BoxLayout(panelOutputSave, BoxLayout.X_AXIS));

        Component horizontalGlue_1 = Box.createHorizontalGlue();
        panelOutputSave.add(horizontalGlue_1);

        JButton saveButtonOutput = new JButton("Сохранить");
        saveButtonOutput.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fileChooserOpen = new JFileChooser();
                if (fileChooserOpen.showSaveDialog(Frame.this) == JFileChooser.APPROVE_OPTION) {
                    Object[] arrat =getRows(outTableModel, 0);
                    String file = fileChooserOpen.getSelectedFile().getPath();
                    if (!file.toLowerCase().endsWith(".txt")) {
                        file += ".txt";
                    }
                    new utils.Files<Integer>().writeFile(new File(file), arrat);
                }
            }
        });
        saveButtonOutput.setBackground(Color.WHITE);
        panelOutputSave.add(saveButtonOutput);


    }

    private void clearTabel(DefaultTableModel tableModel){
        tableModel.setRowCount(0);
        tableModel.setColumnCount(0);
    }

    private void addColum(JTable table, DefaultTableModel tableModel, int index){
        tableModel.addColumn("");
        table.getColumnModel().getColumn(index).setPreferredWidth(table.getColumnModel().getColumn(index).getPreferredWidth());
    }

    private void deleteColum(DefaultTableModel tableModel){
        tableModel.setColumnCount(tableModel.getColumnCount()-1);
    }

    private Object[] getRows(DefaultTableModel tableModel, int index){
        Object[] array = new Object[tableModel.getColumnCount()];
        for(int i = 0; i < array.length; i++){
            array[i] = tableModel.getValueAt(index, i);
        }
        return array;
    }

    private void setTabel(JTable table, DefaultTableModel tableModel, Object[] array){
        clearTabel(tableModel);
        for(int i = 0; i < array.length; i++)
            addColum(table, tableModel, i);
        tableModel.addRow(array);
    }

    private JButton createButton(String image){
        ImageIcon icon = new ImageIcon(basicFile.Frame.class.getResource(image));
        JButton btnNewButton = new JButton(icon);
        btnNewButton.setMargin(new Insets(0, 0, 0, 0));
        btnNewButton.setBorderPainted(false);
        btnNewButton.setContentAreaFilled(false);
        btnNewButton.setFocusPainted(false);
        btnNewButton.setPreferredSize(
                new Dimension(icon.getIconWidth(), icon.getIconHeight())
        );
        return btnNewButton;
    }
}
