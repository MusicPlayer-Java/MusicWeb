
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.omg.CORBA.PUBLIC_MEMBER;


public class View {
	
	public static void main(String[] args){

		init();

	}
	
	//static MouseListeneAdapter ml = new MouseListeneAdapter(0);
	
	
	public static void init() {
		JFrame jFrame = new JFrame("MusicPlayer");
		
		//设置一些Jframe的默认参数
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
		jFrame.setVisible(true);
		jFrame.setSize(1300, 818);
		jFrame.setLayout(new FlowLayout());  //设置jframe布局
		
		//设置jframe默认启动位置
		Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
        int sreen_width = screensize.width;	//获取屏幕宽度
        int sreen_height = screensize.height;  //获取屏幕高度
		jFrame.setLocation((sreen_width-1290)/2, (sreen_height - 818)/2);
		
		
		/*===================歌单窗口===================*/
        JPanel jPanel1 = getPanel(300, 700);
		jPanel1.setBackground(java.awt.Color.BLACK);
		jFrame.add(jPanel1);
		
		/*==============歌单窗口-别人都在听==============*/
		JPanel jPanel11 = getPanel(280, 300);
		jPanel11.setBackground(java.awt.Color.BLUE);
		jPanel1.add(jPanel11);
		
		/*==========歌单窗口-别人都在听-具体内容==========*/
		JLabel jLableTitle = getLable(280, 50, 18, "别人都在听");
		jLableTitle.setBackground(java.awt.Color.YELLOW);
		jPanel11.add(jLableTitle);
		
		String[] others = {"别人的歌单1","别人的歌单2","别人的歌单3","别人的歌单4"};//歌单最多传4个
		
		for (int i = 0; i < others.length; i++) {
			JLabel other = getLable(240, 59, 16, others[i]);
			other.setBackground(java.awt.Color.YELLOW);
			jPanel11.add(other);
		}
		
		/*===============歌单窗口-我的歌单===============*/
		JPanel jPanel12 = getPanel(280, 380);
		jPanel12.setBackground(java.awt.Color.blue);
		jPanel1.add(jPanel12);
		
		
		/*==========歌单窗口-我的歌单-具体内容==========*/
		
		jLableTitle = getLable(280, 40, 18,"我的歌单");
		jLableTitle.setBackground(java.awt.Color.YELLOW);
		jPanel12.add(jLableTitle);
		
		jLableTitle = getLable(280, 320);
		jLableTitle.setBackground(java.awt.Color.YELLOW);
		jPanel12.add(jLableTitle);
		String[] mine = {"我的歌单1","我的歌单2","我的歌单3","我的歌单4","我的歌单5","我的歌单6","我的歌单7"};//歌单最多传5个
		
		for (int i = 0; i < mine.length; i++) {
			JLabel my = getLable(240, 40, 16, mine[i]);
			my.setBackground(java.awt.Color.YELLOW);
			MouseListeneAdapter mlAdapter = new MouseListeneAdapter(i);
			my.addMouseListener(mlAdapter.mouseListener);
			jLableTitle.add(my);
		}
		
		/*===================歌单内容===================*/
		JPanel jPanel2 = getPanel(918, 700);
		jPanel2.setBackground(java.awt.Color.RED);
		jFrame.add(jPanel2);
		
		/*===============歌单内容-具体内容===============*/
		JLabel cover =getLable(270, 270);
		cover.setBackground(java.awt.Color.WHITE);
		jPanel2.add(cover);
		cover.setIcon(new ImageIcon("D:\\Course\\java\\workplace\\CloudMusic\\images\\279759ee3d6d55fb8c19b66f6f224f4a21a4dd98.jpg"));

		JPanel intro = getPanel(600, 270);
		intro.setBackground(java.awt.Color.WHITE);
		jPanel2.add(intro);
		
		JPanel list = getPanel(918, 350);
		list.setBackground(java.awt.Color.BLACK);
		jPanel2.add(list);
		String[] musiclist = {"歌曲111111","歌曲111111","歌曲111111","歌曲111111","歌曲111111","歌曲111111","歌曲111111"};
		String[] timelsit = {"时长111111","时长111111","时长111111","时长111111","时长111111","时长111111","时长111111"};
		String[] singerlist = {"歌手1111111","歌手1111111","歌手1111111","歌手1111111","歌手1111111","歌手1111111","歌手1111111"};
		for (int i = 0; i < musiclist.length; i++) {
			JLabel name = getLable(399, 40, 16, musiclist[i]);
			name.setBackground(java.awt.Color.gray);
			list.add(name);
			name = getLable(150, 40, 16, timelsit[i]);
			name.setBackground(java.awt.Color.white);
			list.add(name);
			name = getLable(150, 40, 16, singerlist[i]);
			name.setBackground(java.awt.Color.gray);
			list.add(name);
			name = getLable(80, 40, 16,"播放");
			name.setBackground(java.awt.Color.WHITE);
			list.add(name);
			name = getLable(80, 40, 16,"下载");
			name.setBackground(java.awt.Color.gray);
			list.add(name);
		}
		
		/*===================播放窗口===================*/
		JPanel jPanel3 = getPanel(1300, 100);
		
		//jPanel3.addMouseListener(new MouseListeneAdapter(1).mouseListener);
		jPanel3.setOpaque(true);
		jPanel3.setBackground(java.awt.Color.green);
		jFrame.add(jPanel3);
		System.out.println(Thread.currentThread());
		/*===============播放窗口-具体内容===============*/
		final JButton btnLast = getButtom(100, 60, 18, "播放");
		final JButton btnNext = getButtom(100, 60, 18, "暂停");

		Thread t1 = new Thread() {public void run() {
				try {
					btnNext.addActionListener(new ActionListener() {
						
						MP3Player mp3 = new MP3Player("D:\\Course\\java\\workplace\\CloudMusic\\musics\\三无MarBlue,易言 - 明月天涯（Cover 五音Jw.mp3");

						public void actionPerformed(ActionEvent e) {
							// TODO Auto-generated method stub
							mp3.play();
						}
					});
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			
			}
			
		};
		t1.start();
		btnLast.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				btnLast.setText("change");
			}
		});
		jPanel3.add(btnLast);
		jPanel3.add(btnNext);
		
		jFrame.setVisible(false);
		jFrame.setVisible(true);
	}
	
	
	//获取JPanel
	static private JPanel getPanel(int width,int height) {
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		Dimension preferredSize = new Dimension(width,height);
		panel.setPreferredSize(preferredSize);
		panel.setOpaque(true);
		return panel;
	}
	
	//获取JLable
	static private JLabel getLable(int width,int height,int fontsize,String text) {
		JLabel label = new JLabel(text);					
		label.setLayout(new FlowLayout());
		Dimension preferredSize = new Dimension(width,height);
		label.setPreferredSize(preferredSize);
		label.setOpaque(true);
		label.setFont(new java.awt.Font("Microsoft YaHei", 0, fontsize));
		return label;	
	}
	//获取JLable
	static private JLabel getLable(int width,int height) {
		JLabel label = new JLabel();					
		label.setLayout(new FlowLayout());
		Dimension preferredSize = new Dimension(width,height);
		label.setPreferredSize(preferredSize);
		label.setOpaque(true);
		return label;
	}
	//获取JButton
	static private JButton getButtom(int width,int height,int fontsize,String text) {
		JButton button = new JButton(text);
		Dimension preferredSize = new Dimension(width,height);
		button.setPreferredSize(preferredSize);
		button.setFont(new java.awt.Font("Microsoft YaHei", 0, fontsize));
		return button;
	}
	
	//获取图片
	//static private ImageIcon getImg(String url) {

		//Image image =  getToolkit().getImage(url);
		//return imageIcon;
	//}
	
	
}
