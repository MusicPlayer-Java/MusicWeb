import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Component {

	//获取JPanel
	static public JPanel getPanel(int width,int height) {
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		Dimension preferredSize = new Dimension(width,height);
		panel.setPreferredSize(preferredSize);
		panel.setOpaque(true);
		return panel;
	}
	
	//获取JLable
	static public JLabel getLable(int width,int height,int fontsize,String text) {
		JLabel label = new JLabel(text);					
		label.setLayout(new FlowLayout());
		Dimension preferredSize = new Dimension(width,height);
		label.setPreferredSize(preferredSize);
		label.setOpaque(true);
		label.setFont(new java.awt.Font("Microsoft YaHei", 0, fontsize));
		return label;	
	}
	//获取JLable
	static public JLabel getLable(int width,int height) {
		JLabel label = new JLabel();					
		label.setLayout(new FlowLayout());
		Dimension preferredSize = new Dimension(width,height);
		label.setPreferredSize(preferredSize);
		label.setOpaque(true);
		return label;
	}
	//获取JButton
	static public JButton getButtom(int width,int height,int fontsize,String text) {
		JButton button = new JButton(text);
		Dimension preferredSize = new Dimension(width,height);
		button.setPreferredSize(preferredSize);
		button.setFont(new java.awt.Font("Microsoft YaHei", 0, fontsize));
		return button;
	}	
}
