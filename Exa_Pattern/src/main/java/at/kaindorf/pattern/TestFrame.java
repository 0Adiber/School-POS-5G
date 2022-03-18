package at.kaindorf.pattern;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class TestFrame extends JFrame {

    public TestFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initComponents();
    }

    private void initComponents() {
        setSize(300,300);
        Container container = getContentPane();
        container.setLayout(new GridLayout(3,1,4,4));
        JButton btStart = new JButton("Start");
        btStart.addActionListener(this::onStartClick);
        container.add(btStart);
        setLocationRelativeTo(null);
    }

    private void onStartClick(ActionEvent actionEvent) {
        System.out.println("Start this shit");
    }

    public static void main(String[] args) {
        new TestFrame().setVisible(true);
    }

}
