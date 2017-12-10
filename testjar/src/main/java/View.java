
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.Label;
import java.awt.List;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.commons.httpclient.HttpException;

import ouc.cs.course.java.model.MusicSheet;


public class View {
	
	private JFrame jFrame = new JFrame("MusicPlayer");
	private MP3Player mp3 = null;		//播放器
	private boolean isPlay = false; 	//判断播放状态
	private int playingIndex;	//播放器播放歌曲
	private boolean isMy = true;
	private int now_page_other;
	
	private ArrayList mySheet = null;	
	private ArrayList othersSheet =   null;
	private static JPanel jPanel1 = null;
	private JPanel jPanel11  = null;
	private JLabel jLableOtherTitle = null;
	private JPanel jPanelOtherList = null;
	private JPanel jPanel12 = null;
	private JLabel jLableMyTitle = null;
	private JLabel jLableMylist = null;
	private JLabel change = null;
	private JLabel beforeChange = null;
	
	private static JPanel jPanel2 = null;
	private JLabel cover = null;
	private JLabel intro = null;
	private JLabel upMusic = null;
	private JLabel upSheet = null;
	private JLabel deleteSheet = null;
	private JPanel upButtons = null;
	private JLabel blank = null;
	private JLabel listtitle = null;
	private JPanel list = null;
	private MusicSheet sheet;
	private ArrayList<Music> musics; 
	
	private static JPanel jPanel3 = null;
	private JLabel btnLast = null;
	private JLabel btnStop = null;
	private JLabel btnNext = null;
	private JLabel playingMusic = null;
	
	private String name, creater, id,path,time;
	private String musicpath;
	class MouseListen{
		
		int i;
		boolean isMine;
		String md5;
		String MusicName;
		int size;
		int last;
		int next;
		int page;
		
		
		public MouseListen(int page) {
			this.page = page;
		}
		
		public MouseListen(int i,boolean isMine) {
			// TODO Auto-generated constructor stub
			this.i = i < 0 ? musics.size()-1 : i;
			this.i = i == musics.size() ? 0: i;
			this.isMine = isMine;
		}
		
		public MouseListen(int i,int size,boolean isMine,String md5,String MusicName) {
			// TODO Auto-generated constructor stub
			this.i = i < 0 ? size -1 : i;
			this.i = i == size ? 0: i;
			this.isMine = isMine;
			this.md5 = md5;
			this.MusicName = MusicName;
			this.size = size;
		}
		
		MouseListener changeListener= new MouseListener() {
			
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
				jPanel11.remove(jPanelOtherList);
				jPanel11.remove(beforeChange);
				jPanel11.remove(change);
				jPanelOtherList = null;
				jPanelOtherList =Component.getPanel(280, 200);
				
				initOtherSheetlist(++now_page_other);
				change.addMouseListener(new MouseListen(now_page_other).changeListener);
				
				jPanel11.add(jPanelOtherList);
				jPanel11.add(beforeChange);
				jPanel11.add(change);
				
				jFrame.validate();
				jFrame.repaint();
			}
		};
		
		MouseListener musicListener = new MouseListener() {
			
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
				if (isPlay) {
					mp3.stop();
				}
				
				Thread t1 = new Thread() {public void run() {
						try {
							if (isMine) {
								mp3 = new MP3Player(musics.get(i).getPath());
								playingMusic.setText("正在播放: "+musics.get(i).getName());
							}
							else {
								playingMusic.setText("正在下载: "+ MusicName);
								jFrame.validate(); 
								jFrame.repaint();
								String path = MusicHelper.downloadMusic(md5);
								System.out.println(path);
								mp3 = new MP3Player(path);
								
							}
							
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
						isPlay = true;
						playingIndex = i;
						
						
						jPanel3.remove(btnNext);
						jPanel3.remove(btnStop);
						jPanel3.remove(btnLast);
						
						btnNext = null;
						btnLast = null;
						
						btnLast =Component.getLable(100, 60, 18, "上一首");
						btnNext =Component.getLable(100, 60, 18, "下一首");
						
						
						if (isMine) {
							last = playingIndex==0?musics.size():playingIndex-1;
							next = playingIndex == musics.size() ? 0:playingIndex+1;
							btnLast.addMouseListener(new MouseListen(last,true).musicListener);
							btnNext.addMouseListener(new MouseListen(next,true).musicListener);
						}
						else {
							last = playingIndex==0?size:playingIndex-1;
							next = playingIndex == size ? 0:playingIndex+1;
							playingMusic.setText("正在播放: "+ MusicName);
							btnLast.addMouseListener(new MouseListen(last,size,false,md5,name).musicListener);
							btnNext.addMouseListener(new MouseListen(last,size,false,md5,name).musicListener);
						}
						
						
						
						jPanel3.add(btnLast);
						jPanel3.add(btnStop);
						jPanel3.add(btnNext);
						
						btnLast.setBackground(java.awt.Color.gray);
						btnNext.setBackground(java.awt.Color.gray);
						
						jFrame.validate(); 
						jFrame.repaint();

						System.out.println(i);
						System.out.println(playingIndex);
						
						
						mp3.play();
						
						
					}
				};
				
				t1.start();
				
			}
		};
		
		
	}
	

		
		MouseListener changgelistener = new MouseListener() {
			
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
				
			}
		};
	
	public View() throws HttpException, IOException {
		
		initMusicList();
		initMusicListDetail();
		initMusicConsole();
	}
	
	
	//初始化播放台
	private void initMusicConsole() {
		// TODO Auto-generated method stub
		jPanel3 = Component.getPanel(1300, 100);
		jPanel3.setBackground(java.awt.Color.gray);

		btnLast =Component.getLable(100, 60, 18, "上一首");
		btnStop =Component.getLable(100, 60, 18, "暂停");
		btnNext =Component.getLable(100, 60, 18, "下一首");
		playingMusic = Component.getLable(750, 60, 18, "正在播放: "+musics.get(playingIndex).getName());
		
		
		btnLast.addMouseListener(new MouseListen(playingIndex==0?musics.size():playingIndex-1,true).musicListener);
		btnNext.addMouseListener(new MouseListen(playingIndex == musics.size() ? 0:playingIndex+1,true).musicListener);
		
		btnStop.addMouseListener(new MouseListener() {
			
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
				if (mp3!=null) {
					mp3.stop();
					isPlay = false;
				}
			}
		});
		
		btnLast.setBackground(java.awt.Color.gray);
		btnNext.setBackground(java.awt.Color.gray);
		btnStop.setBackground(java.awt.Color.gray);
		playingMusic.setBackground(java.awt.Color.gray);
		
		jPanel3.add(playingMusic);
		jPanel3.add(btnLast);
		jPanel3.add(btnStop);
		jPanel3.add(btnNext);
		
	}

	//初始化歌单内容
	private void initMusicListDetail() {
		
		jPanel2 = Component.getPanel(918, 700);
		cover =Component.getLable(270, 270);
		//cover.setIcon(new ImageIcon(sheet.getImagePath()));
		sheet = SheetHelper.getSheet("cd7e8ee8 038d 4c6e ae26 248704c59d67");
		ImageIcon image = new ImageIcon(sheet.getPicture());
		image.setImage(image.getImage().getScaledInstance(270, 270,Image.SCALE_DEFAULT ));
		cover.setIcon(image);
		blank = Component.getLable(80, 270);
		
		intro = Component.getLable(500, 200,25,"<html><body><span style='color:red;'>"+sheet.getName()+"</span><br><br><br><span style='color:red;'>"+sheet.getCreatorId()+"</span>于<span style='color:red;'>"+sheet.getDateCreated()+"</span>创建"+"<body></html>");
		listtitle = Component.getLable(918, 80, 22, "歌曲列表");
		list = Component.getPanel(918, 450);
		musics = SheetHelper.getAllSongs(sheet.getUuid().toString());
		
		JPanel Box = Component.getPanel(500, 270);
		upButtons = Component.getPanel(500, 70);
		//upButtons.setBackground(java.awt.Color.gray);
		upMusic = Component.getLable(160, 60, 18, "上传歌曲");
		upMusic.addMouseListener(new MouseListener() {
			
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
				
				//String musicpath =  FileHelper.openMusicChooser();
				
				final JFrame addMusic = new JFrame("create sheet");
				addMusic.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	
				addMusic.setVisible(true);
				addMusic.setSize(530,500);
				addMusic.setLayout(new FlowLayout());  //设置jframe布局
				Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
		        int sreen_width = screensize.width;	//获取屏幕宽度
		        int sreen_height = screensize.height;  //获取屏幕高度
		        addMusic.setLocation((sreen_width-530)/2, (sreen_height - 500)/2);
		        
		        JPanel blankmusic1 = Component.getPanel(500, 80);
		        JPanel blankmusic2 = Component.getPanel(500, 50);
		        JPanel blankmusic3 = Component.getPanel(500, 50);
		        JLabel lbName = Component.getLable(150, 50, 20, "歌手:");
		        final TextField txtSinger = Component.getTextField(300, 40);
		        JLabel lbUp = Component.getLable(150, 50, 20, "添加歌曲:");
		        JLabel lbMusic = Component.getLable(300, 50, 20, "上传歌曲");
		        lbMusic.addMouseListener(new MouseListener() {
					
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
						musicpath =  FileHelper.openMusicChooser();
					}
				});
		        JLabel lbSub = Component.getLable(450, 50, 20, "                                 添加歌曲");
		        lbSub.addMouseListener(new MouseListener() {
					
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
						
						MusicHelper.AddMusicToSheet(musicpath,sheet.getUuid(),txtSinger.getText());
						
						jPanel2.remove(list);
						list = null;
						list = Component.getPanel(918, 450);
						
						musics = SheetHelper.getAllSongs(sheet.getUuid());
						
						for (int i = 0; i < musics.size(); i++) {
							final Music music = (Music) musics.get(i);
							JLabel name = Component.getLable(240, 40, 16, music.getName() );
							name.setBackground(java.awt.Color.gray);
							list.add(name);
							//name = Component.getLable(150, 40, 16, music.getTime());
							//name.setBackground(java.awt.Color.white);
							//list.add(name);
							//name = Component.getLable(150, 40, 16, music.getSinger());
							//name.setBackground(java.awt.Color.gray);
							//list.add(name);
							JLabel name1 = Component.getLable(100, 40, 16,"播放");
							name1.setBackground(java.awt.Color.WHITE);
							name1.addMouseListener(new MouseListen(i,true).musicListener);
							list.add(name1);
							//name = Component.getLable(80, 40, 16,"下载");
							//name.setBackground(java.awt.Color.gray);
							//list.add(name);
							if (i%2==0) {
								JLabel name2 = Component.getLable(40, 40);
								//name.setBackground(java.awt.Color.gray);
								list.add(name2);
							}
						}
						if (musics.size()%2!=0) {
							JLabel name = Component.getLable(240, 40);
							list.add(name);
							JLabel name1 = Component.getLable(100, 40);
							list.add(name1);
						}
						jPanel2.add(list);
						
						addMusic.dispose();
						jFrame.validate();
						jFrame.repaint();
						//jFrame.setVisible(false);
						//jFrame.setVisible(true);
					}
				});
		        addMusic.add(blankmusic1);
		        addMusic.add(lbName);
		        addMusic.add(txtSinger);
		        addMusic.add(blankmusic2);
		        addMusic.add(lbUp);
		        addMusic.add(lbMusic);
		        addMusic.add(blankmusic3);
		        addMusic.add(lbSub);
		        
		        
			}
		});
		upSheet = Component.getLable(160, 60, 18, "上传歌单");
		upSheet.addMouseListener(new MouseListener() {
			
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
				
			}
		});
		deleteSheet = Component.getLable(160, 60, 18, "删除该歌单");
		deleteSheet.addMouseListener(new MouseListener() {
			
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
				
			}
		});
		
		upButtons.add(upMusic);
		upButtons.add(upSheet);
		upButtons.add(deleteSheet);
		jPanel2.add(cover);
		jPanel2.add(blank);
		jPanel2.add(Box);
		Box.add(intro);
		Box.add(upButtons);
		jPanel2.add(listtitle);
		jPanel2.add(list);
		for (int i = 0; i < musics.size(); i++) {
			final Music music = (Music) musics.get(i);
			JLabel name = Component.getLable(240, 40, 16, music.getName() );
			name.setBackground(java.awt.Color.gray);
			list.add(name);
			//name = Component.getLable(150, 40, 16, music.getTime());
			//name.setBackground(java.awt.Color.white);
			//list.add(name);
			//name = Component.getLable(150, 40, 16, music.getSinger());
			//name.setBackground(java.awt.Color.gray);
			//list.add(name);
			JLabel name1 = Component.getLable(100, 40, 16,"播放");
			name1.setBackground(java.awt.Color.WHITE);
			name1.addMouseListener(new MouseListen(i,true).musicListener);
			list.add(name1);
			//name = Component.getLable(80, 40, 16,"下载");
			//name.setBackground(java.awt.Color.gray);
			//list.add(name);
			if (i%2==0) {
				JLabel name2 = Component.getLable(40, 40);
				//name.setBackground(java.awt.Color.gray);
				list.add(name2);
			}
		}
		if (musics.size()%2!=0) {
			JLabel name = Component.getLable(240, 40);
			list.add(name);
			JLabel name1 = Component.getLable(100, 40);
			list.add(name1);
		}
	}
	//初始化歌单列表
	private void initMusicList() throws HttpException, IOException {
		// TODO Auto-generated method stub
		
		mySheet = SheetHelper.getAll();
		othersSheet =  (ArrayList) SheetHelper.getServerSheets();
		jPanel1 = Component.getPanel(300, 700);
		jPanel11 = Component.getPanel(280, 300);
		jLableOtherTitle = (JLabel) Component.getLable(280, 50, 18, "别人都在听");
		jPanel12 = Component.getPanel(280, 380);
		jLableMyTitle = Component.getLable(280, 40, 18,"我的歌单");
		jLableMylist = Component.getLable(280, 280);
		jPanelOtherList = Component.getPanel(280, 200);
		beforeChange =  Component.getLable(80, 50);
		change = Component.getLable(100, 50, 18, "换一换");
		change.addMouseListener(new MouseListen(now_page_other+1).musicListener);
		
		jPanel1.add(jPanel11);
		jPanel11.add(jLableOtherTitle);
		jPanel11.add(jPanelOtherList);
		jPanel11.add(beforeChange);
		jPanel11.add(change);
		//jPanel11.setBackground(java.awt.Color.gray);
		System.out.println(othersSheet.size());
		
		initOtherSheetlist(now_page_other=1);
		
		jPanel1.add(jPanel12);
		JLabel jLabelAdd = Component.getLable(280, 40, 18, "创建歌单");
		jPanel12.add(jLabelAdd);
		
		
		jLabelAdd.addMouseListener(new MouseListener() {
			
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
				final JFrame addSheet = new JFrame("create sheet");
				addSheet.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	
				addSheet.setVisible(true);
				addSheet.setSize(530,640);
				addSheet.setLayout(new FlowLayout());  //设置jframe布局
				Dimension screensize = Toolkit.getDefaultToolkit().getScreenSize();
		        int sreen_width = screensize.width;	//获取屏幕宽度
		        int sreen_height = screensize.height;  //获取屏幕高度
		        addSheet.setLocation((sreen_width-530)/2, (sreen_height - 640)/2);
		        
		        JPanel blank1 = Component.getPanel(500, 20);
		        JPanel blank2 = Component.getPanel(500, 60);
		        JPanel blank3 = Component.getPanel(500, 60);
		        JPanel blank4 = Component.getPanel(500, 60);
		        JPanel blank5 = Component.getPanel(500, 60);
		        JLabel lbName = Component.getLable(150, 50, 20, "歌单名字:");
		        final TextField txtName = Component.getTextField(300, 40);
		        JLabel lbCreator = Component.getLable(150, 50, 20, "创建人:");
		        final TextField txtCretor = Component.getTextField(300, 40);
		        JLabel lbID = Component.getLable(150, 50, 20, "学号:");
		        final TextField txtID = Component.getTextField(300, 40);
		        JLabel lbUp = Component.getLable(150, 50, 20, "设置封面:");
		        JLabel lbMusic = Component.getLable(300, 50, 20, "上传图片");
		        
		        
		        
		        lbMusic.addMouseListener(new MouseListener() {
					
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
						
						path = FileHelper.openPictureChooser();
					}
				});
		        JLabel lbSub = Component.getLable(450, 50, 20, "                                 创建歌单");
		        lbSub.addMouseListener(new MouseListener() {
					
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
						//name = txtName.getText();
						//creater = txtCretor.getText();
						
						SheetHelper.createSheet(txtName.getText(),path,txtCretor.getText(),txtID.getText());
						
						jPanel12.remove(jLableMylist);
						jLableMylist = null;
						jLableMylist = Component.getLable(280, 280);
						mySheet = SheetHelper.getAll();
						
						initMySheetlist();
						jPanel12.add(jLableMylist);
						//addSheet.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						addSheet.dispose();
						jFrame.setVisible(false);
						jFrame.setVisible(true);
					}
				});
		        addSheet.add(blank1);
		        addSheet.add(lbName);
		        addSheet.add(txtName);
		        addSheet.add(blank2);
		        addSheet.add(lbCreator);
		        addSheet.add(txtCretor);
		        addSheet.add(blank3);
		        addSheet.add(lbID);
		        addSheet.add(txtID);
		        addSheet.add(blank5);
		        addSheet.add(lbUp);
		        addSheet.add(lbMusic);
		        addSheet.add(blank4);
		        addSheet.add(lbSub);
			}
		});
	
		jPanel12.add(jLableMyTitle);
		jPanel12.add(jLableMylist);;
		//jPanel12.setBackground(java.awt.Color.gray);
		initMySheetlist();
	}

	public void init() throws HttpException, IOException {
		playingIndex = 0;
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
		
		jFrame.add(jPanel1);
		jFrame.add(jPanel2);
		jFrame.add(jPanel3);
		
		jFrame.setVisible(false);
		jFrame.setVisible(true);
	}
	
	
	private void initMySheetlist() {
		playingIndex = 0;
		if (mySheet.size() > 0) {
			for (int i = 0; i < mySheet.size(); i++) {
				final MusicSheet xSheet = (MusicSheet) mySheet.get(i);
				JLabel my = Component.getLable(240, 40, 16, xSheet.getName());
				my.addMouseListener(new MouseListener() {
						
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
						upButtons.add(upMusic);
						upButtons.add(upSheet);
						upButtons.add(deleteSheet);
							
						
							sheet = (MusicSheet) xSheet;
							musics = null;
							musics = SheetHelper.getAllSongs(sheet.getUuid().toString());
							
							intro.setText("<html><body><span style='color:red;'>   "+sheet.getName()+"</span><br><br><br><span style='color:red;'>   "+sheet.getCreatorId()+"</span>于<span style='color:red;'>"+sheet.getDateCreated()+"</span>创建"+"<body></html>");
							System.out.println( sheet.getPicture());
							ImageIcon image = new ImageIcon(sheet.getPicture());
							image.setImage(image.getImage().getScaledInstance(270, 270,Image.SCALE_DEFAULT ));
							cover.setIcon(image);
							
							//cover.setIcon(new ImageIcon( sheet.getPicture()));
							
							
							
							jPanel2.remove(list);
							list = null;
							list = Component.getPanel(918, 450);
							jPanel2.add(list);
							for (int i = 0; i < musics.size(); i++) {
								final Music music = (Music) musics.get(i);
								JLabel name = Component.getLable(240, 40, 16, music.getName() );
								name.setBackground(java.awt.Color.gray);
								list.add(name);
								//name = Component.getLable(150, 40, 16, music.getTime());
								//name.setBackground(java.awt.Color.white);
								//list.add(name);
								//name = Component.getLable(150, 40, 16, music.getSinger());
								//name.setBackground(java.awt.Color.gray);
								//list.add(name);
								JLabel name1 = Component.getLable(100, 40, 16,"播放");
								name1.setBackground(java.awt.Color.WHITE);
								name1.addMouseListener(new MouseListen(i,true).musicListener);
								list.add(name1);
								//name = Component.getLable(80, 40, 16,"下载");
								//name.setBackground(java.awt.Color.gray);
								//list.add(name);
								if (i%2==0) {
									JLabel name2 = Component.getLable(40, 40);
									//name.setBackground(java.awt.Color.gray);
									list.add(name2);
								}
							}
							if (musics.size()%2!=0) {
								JLabel name = Component.getLable(240, 40);
								list.add(name);
								JLabel name1 = Component.getLable(100, 40);
								list.add(name1);
							}
							jFrame.validate();
							jFrame.repaint();
						}
					});

				jLableMylist.add(my);
			}
		}
	}
	
	private void initOtherSheetlist(int page) {
		
		
		
		if (!isMy) {
			jPanel2.remove(upMusic);
			jPanel2.remove(upSheet);
			jPanel2.remove(deleteSheet);
		}
		
		playingIndex = 0;
		
		if ((page-1)*4> othersSheet.size())
			page = 1;
		
		if (othersSheet.size() > 0) {
			for (int i = 0 + (page-1)*4 ; i < (othersSheet.size()< page * 4 ? othersSheet.size() : page * 4); i++) {
				final MusicSheet xSheet = (MusicSheet) othersSheet.get(i);
				JLabel other = Component.getLable(240, 45, 16, xSheet.getName());
				jPanelOtherList.add(other);
				other.addMouseListener(new MouseListener() {
					
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
						
						
						upButtons.remove(upMusic);
						upButtons.remove(upSheet);
						upButtons.remove(deleteSheet);
						
						
						
						
						sheet = (MusicSheet) xSheet;
						musics = null;
						//musics = SheetHelper.getAllSongs(sheet.getUuid().toString());
						//颖芳看这里！！！
						Map map =(Map) xSheet.getMusicItems();
						String[] msList = new String[map.size()];
						map.values().toArray(msList);
						for(int j =0; j < msList.length; j++)
							System.out.println(msList[j]);
						String[] md5List = new String[map.size()];
						map.keySet().toArray(md5List);
						
						intro.setText("<html><body><span style='color:red;'>   "+sheet.getName()+"</span><br><br><br><span style='color:red;'>   "+sheet.getCreatorId()+"</span>于<span style='color:red;'>"+sheet.getDateCreated()+"</span>创建"+"<body></html>");
						ImageIcon image = new ImageIcon(SheetHelper.getPicture(sheet.getUuid(), sheet.getPicture()));
						image.setImage(image.getImage().getScaledInstance(270, 270,Image.SCALE_DEFAULT ));
						cover.setIcon(image);
						
						//cover.setIcon(new ImageIcon(SheetHelper.getPicture(sheet.getUuid(), sheet.getPicture())));
						jPanel2.remove(list);
						list = null;
						list = Component.getPanel(918, 450);
						jPanel2.add(list);
						for (int i = 0; i < msList.length; i++) {
							JLabel name = Component.getLable(240, 40, 16, msList[i] );
							name.setBackground(java.awt.Color.gray);
							list.add(name);
							JLabel name1 = Component.getLable(100, 40, 16,"播放");
							name1.setBackground(java.awt.Color.WHITE);
							name1.addMouseListener(new MouseListen(i,msList.length, false, md5List[i],msList[i]).musicListener);
							list.add(name1);
							if (i%2==0) {
								JLabel name2 = Component.getLable(40, 40);
								//name.setBackground(java.awt.Color.gray);
								list.add(name2);
							}
							
						}
						if (msList.length%2!=0) {
							JLabel name = Component.getLable(240, 40);
							list.add(name);
							JLabel name1 = Component.getLable(100, 40);
							list.add(name1);
						}
						jFrame.validate();
						jFrame.repaint();
					}
				});
			}
		}
	}
}
