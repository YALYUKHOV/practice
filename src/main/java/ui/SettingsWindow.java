package ui;

import functions.factory.ArrayTabulatedFunctionFactory;
import functions.factory.TabulatedFunctionFactory;
import functions.factory.LinkedListTabulatedFunctionFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.border.EmptyBorder;

public class SettingsWindow extends JDialog {
    private final TabulatedFunctionFactory factoryArray = new ArrayTabulatedFunctionFactory();
    private final TabulatedFunctionFactory factoryLinkList = new LinkedListTabulatedFunctionFactory();

    public SettingsWindow(MainWindow mainWindow, TabulatedFunctionFactory factory) {



        setTitle("Настройки");
        setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
        setLayout(new BorderLayout());

        // Отступ от края окна
        int borderMargin = 10;
        EmptyBorder contentBorder = new EmptyBorder(borderMargin, borderMargin, borderMargin, borderMargin);

        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBorder(contentBorder);
        setContentPane(contentPanel);

        JLabel settings = new JLabel("Выберите тип функции:");
        add(settings, BorderLayout.NORTH);

        // Создание кнопки для сохранения выбранной фабрики
        JButton saveButton = new JButton("Сохранить");
        saveButton.setPreferredSize(new Dimension(100, 30));
        add(saveButton, BorderLayout.SOUTH);

        // Создаем радиокнопки для выбора фабрики
        JRadioButton arrayRadioButton = new JRadioButton("Массив");
        JRadioButton linkedListRadioButton = new JRadioButton("Связный список");

        // Группируем радиокнопки в ButtonGroup
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(arrayRadioButton);
        buttonGroup.add(linkedListRadioButton);
        JPanel radioButtonPanel = new JPanel(new GridLayout(1, 2));
        radioButtonPanel.add(arrayRadioButton);
        radioButtonPanel.add(linkedListRadioButton);
        add(radioButtonPanel, BorderLayout.CENTER);

        if (factory instanceof ArrayTabulatedFunctionFactory){
            arrayRadioButton.setSelected(true);
        }
        else {
            linkedListRadioButton.setSelected(true);
        }

        saveButton.addActionListener(new ActionListener() {
            @Override // Получение выбранной фабрики
            public void actionPerformed(ActionEvent e) {

                if (arrayRadioButton.isSelected()) {
                    mainWindow.setFactory(factoryArray);
                } else {
                    mainWindow.setFactory(factoryLinkList);
                }// Закрытие окна настроек
                dispose();
            }
        });
        pack();
        setLocationRelativeTo(mainWindow);
        setPreferredSize(new Dimension(400, 400));
        setResizable(true);
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}