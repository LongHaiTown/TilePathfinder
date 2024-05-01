package main;


import java.awt.*;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrameMain extends JFrame{
	Mappanel map;
	
	public FrameMain() {
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 10, 1250, 600);
		
		map = new Mappanel();
		panel.add(map);
        
        
		getContentPane().add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(547, 620, 191, 43);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int signalBigger = 1;
				map.sizeUpdate(signalBigger);
			}
		});
		btnNewButton.setBounds(10, 0, 85, 21);
		panel_1.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int signalSmaller = -1;
				map.sizeUpdate(signalSmaller);
			}
		});
		btnNewButton_1.setBounds(96, 0, 85, 21);
		panel_1.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("New button");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_2.setBounds(48, 22, 85, 21);
		panel_1.add(btnNewButton_2);
		
		
	}
        public static void main(String[] args) {
                FrameMain myFrameMain = new FrameMain();
                myFrameMain.setSize(myFrameMain.map.ScreeW,myFrameMain.map.ScreenH);
                myFrameMain.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                myFrameMain.getContentPane().setLayout(null);
				myFrameMain.map.startGameThread();
				myFrameMain.setVisible(true);
        }

}










