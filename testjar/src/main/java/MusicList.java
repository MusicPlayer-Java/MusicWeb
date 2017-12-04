

import java.io.IOException;
import java.util.ArrayList;


import javax.swing.JLabel;
import javax.swing.JPanel;

import org.apache.commons.httpclient.HttpException;

import ouc.cs.course.java.model.MusicSheet;
public class MusicList {

	private ArrayList mySheet = null;
	private ArrayList othersSheet =   null;
	private JPanel jPanel1 = null;
	private JPanel jPanel11  = null;
	private JLabel jLableOtherTitle = null;
	private JPanel jPanel12 = null;
	private JLabel jLableMyTitle = null;
	private JLabel jLableMylist = null;
	
	
	public MusicList() throws HttpException, IOException {
		// TODO Auto-generated constructor stub
		
		mySheet = SheetHelper.getAll();
		
		othersSheet =  (ArrayList) SheetHelper.getServerSheets();
		//System.out.println("88");
		jPanel1 = Component.getPanel(300, 700);
		jPanel11 = Component.getPanel(280, 300);
		jLableOtherTitle = (JLabel) Component.getLable(280, 50, 18, "别人都在听");
		jPanel12 = Component.getPanel(280, 380);
		jLableMyTitle = Component.getLable(280, 40, 18,"我的歌单");
		jLableMylist = Component.getLable(280, 320);
		
		init();
		
	}
	
	public JPanel getjPanel1() {
		return jPanel1;
	}
	
	public void init() {
		jPanel1.add(jPanel11);
		jPanel11.add(jLableOtherTitle);
		System.out.println(othersSheet.size());
		if (othersSheet.size() > 0) {
			for (int i = 0; i < (othersSheet.size()>4?othersSheet.size():4); i++) {
				MusicSheet xSheet = (MusicSheet) othersSheet.get(i);
				JLabel other = Component.getLable(240, 59, 16, xSheet.getName());
				jPanel11.add(other);
			}
		}
		//System.out.print(mySheet.size()+"\n");
		jPanel1.add(jPanel12);
		jPanel12.add(jLableMyTitle);
		jPanel12.add(jLableMylist);;
		if (mySheet.size() > 0) {
			for (int i = 0; i < mySheet.size(); i++) {
				MusicSheet xSheet = (MusicSheet) mySheet.get(i);
				JLabel my = Component.getLable(240, 40, 16, xSheet.getName());
				//my.setBackground(java.awt.Color.YELLOW);
				//MouseListeneAdapter mlAdapter = new MouseListeneAdapter(i);
				//my.addMouseListener(mlAdapter.mouseListener);
				System.out.println(xSheet.getUuid());
				jLableMylist.add(my);
			}
		}
	}
	
	
}