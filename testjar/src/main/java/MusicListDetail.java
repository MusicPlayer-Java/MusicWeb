import java.nio.file.Path;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MusicListDetail {

	private JPanel jPanel2 = null;


	private JLabel cover = null;
	private JLabel intro = null;
	private JPanel list = null;
	private Sheet sheet;
	private ArrayList<Music> musics; 
	
	public JPanel getjPanel2() {
		return jPanel2;
	}

	
	public MusicListDetail(Sheet sheet) {
		// TODO Auto-generated constructor stub
		jPanel2 = Component.getPanel(918, 700);
		cover =Component.getLable(270, 270);
		//cover.setIcon(new ImageIcon(sheet.getImagePath()));
		cover.setIcon(new ImageIcon("D:\\Course\\java\\workplace\\CloudMusic\\images\\279759ee3d6d55fb8c19b66f6f224f4a21a4dd98.jpg"));
		intro = Component.getLable(600, 270);
		list = Component.getPanel(918, 450);
		this.sheet = sheet;
		musics = SheetHelper.getAllSongs(sheet.getUuid().toString());
		init();
		//System.out.println(sheet.getImagePath());
		//System.out.println(sheet.getName());
	}
	
	
	public void init() {
		//cover.setBackground(java.awt.Color.green);
		jPanel2.add(cover);
		//"D:\\Course\\java\\workplace\\CloudMusic\\images\\279759ee3d6d55fb8c19b66f6f224f4a21a4dd98.jpg"
		intro.setText(sheet.getName()+"\n"+sheet.getCreatorId()+"于"+sheet.getDateCreated()+"创建");
		jPanel2.add(intro);
		//list.setBackground(java.awt.Color.BLACK);
		jPanel2.add(list);
		System.out.print(musics.size());
		for (int i = 0; i < musics.size(); i++) {
			Music music = (Music) musics.get(i);
			JLabel name = Component.getLable(399, 40, 16, music.getName() );
			name.setBackground(java.awt.Color.gray);
			list.add(name);
			name = Component.getLable(150, 40, 16, music.getTime());
			name.setBackground(java.awt.Color.white);
			list.add(name);
			name = Component.getLable(150, 40, 16, music.getSinger());
			name.setBackground(java.awt.Color.gray);
			list.add(name);
			name = Component.getLable(80, 40, 16,"播放");
			name.setBackground(java.awt.Color.WHITE);
			list.add(name);
			name = Component.getLable(80, 40, 16,"下载");
			name.setBackground(java.awt.Color.gray);
			list.add(name);
		}
		
	}
	
}
