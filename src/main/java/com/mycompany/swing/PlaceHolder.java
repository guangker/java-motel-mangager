package com.mycompany.swing;

import java.awt.Color;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.JTextField;

public class PlaceHolder {
     private void addPlaceholder(JTextField textField, String placeholder) {
        // Đặt màu chữ mờ cho placeholder
        textField.setForeground(Color.GRAY);
        // Đặt placeholder
        textField.setText(placeholder);

        textField.addFocusListener(new FocusListener() {
            //@Override
            public void focusGained(FocusEvent e) {
                // Khi JTextField được tập trung, kiểm tra nếu nội dung là placeholder thì xóa đi
                if (textField.getText().equals(placeholder)) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK); // Đặt màu chữ khi nhập dữ liệu
                }
            }

            //@Override
            public void focusLost(FocusEvent e) {
                // Khi JTextField mất tập trung, kiểm tra nếu nội dung rỗng thì đặt lại placeholder
                if (textField.getText().isEmpty()) {
                    textField.setForeground(Color.GRAY);
                    textField.setText(placeholder);
                }
            }
        });
    }
}
