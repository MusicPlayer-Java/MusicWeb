
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import org.apache.commons.httpclient.HttpException;


public class View {
	
	public static void main(String[] args) throws HttpException, IOException{

		init();

	}
	
	public static void init() throws HttpException, IOException {
		final JFrame jFrame = new JFrame("MusicPlayer");
		MusicList mlList = new MusicList();
		MusicListDetail  mld = new MusicListDetail(SheetHelper.getSheet("0e1b86d3017542fa99e38e7d48cd2dc8"));
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
		
		final JPanel panel2 = mld.getjPanel2();
		
		jFrame.add(mlList.getjPanel1());
		jFrame.add(panel2);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		/*===================播放窗口===================*/
		final JPanel jPanel3 = Component.getPanel(1300, 100);
		
		//jPanel3.addMouseListener(new MouseListeneAdapter(1).mouseListener);
		jPanel3.setOpaque(true);
		jPanel3.setBackground(java.awt.Color.green);
		jFrame.add(jPanel3);
		System.out.println(Thread.currentThread());
		/*===============播放窗口-具体内容===============*/
		final JButton btnLast =Component.getButtom(100, 60, 18, "播放");
		//final JButton btnNext = getButtom(100, 60, 18, "暂停");

		/*Thread t1 = new Thread() {public void run() {
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
		t1.start();*/
		btnLast.addActionListener(new ActionListener() {
			

			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				jFrame.remove(panel2);
				jFrame.repaint();
			}
		});
		jPanel3.add(btnLast);
		//jPanel3.add(btnNext);
		
		jFrame.setVisible(false);
		jFrame.setVisible(true);
	}
}
