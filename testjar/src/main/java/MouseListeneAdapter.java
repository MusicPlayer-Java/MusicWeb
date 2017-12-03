import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;

public class MouseListeneAdapter {
		
		int musicID;
		
		MouseListeneAdapter(int id) {
			musicID = id;
		}
		
		//��������
		public MouseListener mouseListener =  new MouseListener() {
			
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				System.out.println(Thread.currentThread());
				MP3Player mp3 = null;
				try {
					mp3 = new MP3Player("D:\\Course\\java\\workplace\\CloudMusic\\musics\\����MarBlue,���� - �������ģ�Cover ����Jw.mp3");
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				mp3.play();
			}
		};
}
