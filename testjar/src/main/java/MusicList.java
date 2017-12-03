
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.List;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.apache.commons.httpclient.HttpException;
public class MusicList {

	private ArrayList mySheet = null;
	private java.util.List othersSheet =   null;
	private JPanel jPanel1 = null;
	private JPanel jPanel11  = null;
	private JLabel jLableTitle = null;
	
	public MusicList() throws HttpException, IOException {
		// TODO Auto-generated constructor stub
		mySheet = SheetHelper.getAll();
		othersSheet = SheetHelper.getServerSheets();
		jPanel1 = Component.getPanel(300, 700);
		jPanel11 = Component.getPanel(280, 300);
		jLableTitle = (JLabel) Component.getLable(280, 50, 18, "别人都在听");
	}
	
	public void init() {
		/*==============歌单窗口-别人都在听==============*/
		
		jPanel1.add(jPanel11);
		
		/*==========歌单窗口-别人都在听-具体内容==========*/
		
		//jLableTitle.setBackground(java.awt.Color.YELLOW);
		jPanel11.add(jLableTitle);
		
		//String[] others = {"别人的歌单1","别人的歌单2","别人的歌单3","别人的歌单4"};//歌单最多传4个
		
		for (int i = 0; i < others.length; i++) {
			JLabel other = Component.getLable(240, 59, 16, others[i]);
			other.setBackground(java.awt.Color.YELLOW);
			jPanel11.add(other);
		}
		
		/*===============歌单窗口-我的歌单===============*/
		JPanel jPanel12 = Component.getPanel(280, 380);
		jPanel12.setBackground(java.awt.Color.blue);
		jPanel1.add(jPanel12);
		
		
		/*==========歌单窗口-我的歌单-具体内容==========*/
		
		jLableTitle = Component.getLable(280, 40, 18,"我的歌单");
		//jLableTitle.setBackground(java.awt.Color.YELLOW);
		jPanel12.add(jLableTitle);
		
		jLableTitle = Component.getLable(280, 320);
		//jLableTitle.setBackground(java.awt.Color.YELLOW);
		jPanel12.add(jLableTitle);
		String[] mine = {"我的歌单1","我的歌单2","我的歌单3","我的歌单4","我的歌单5","我的歌单6","我的歌单7"};//歌单最多传5个
		
		for (int i = 0; i < mine.length; i++) {
			JLabel my = Component.getLable(240, 40, 16, mine[i]);
			my.setBackground(java.awt.Color.YELLOW);
			MouseListeneAdapter mlAdapter = new MouseListeneAdapter(i);
			my.addMouseListener(mlAdapter.mouseListener);
			jLableTitle.add(my);
		}
	
	}
	
	
}