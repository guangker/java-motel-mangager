package com.mycompany.swing;

import java.awt.Font;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelListener;
import javax.swing.JTextArea;

//  make this for disable mouse event
public class TextArea extends JTextArea {

    public TextArea() {
        setEditable(false);
        setFocusable(false);
        setWrapStyleWord(true);
        setLineWrap(true);
        setFont(new Font("Segoe UI", Font.PLAIN, 14)); // Thiết lập kích thước font chữ là 14
    }

    @Override
    public synchronized void addMouseListener(MouseListener ml) {
    }

    @Override
    public synchronized void addMouseMotionListener(MouseMotionListener ml) {
    }

    @Override
    public synchronized void addMouseWheelListener(MouseWheelListener ml) {
    }

    @Override
    public void addNotify() {
    }
}
