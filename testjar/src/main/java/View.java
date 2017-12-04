
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.apache.commons.httpclient.HttpException;

import ouc.cs.course.java.model.MusicSheet;


public class View {
	
	private JFrame jFrame = new JFrame("MusicPlayer");
	private MP3Player mp3 = null;		//播放器
	private boolean isPlay = false; 	//判断播放状态
	
	private ArrayList mySheet = null;	
	private ArrayList othersSheet =   null;
	private static JPanel jPanel1 = null;
	private JPanel jPanel11  = null;
	private JLabel jLableOtherTitle = null;
	private JPanel jPanel12 = null;
	private JLabel jLableMyTitle = null;
	private JLabel jLableMylist = null;
	
	private static JPanel jPanel2 = null;
	private JLabel cover = null;
	private JLabel intro = null;
	private JLabel blank = null;
	private JLabel listtitle = null;
	private JPanel list = null;
	private MusicSheet sheet;
	private ArrayList<Music> musics; 
	
	private static JPanel jPanel3 = null;
	private JLabel stop = null;
	private JLabel last = null;
	private JLabel next = null;
	
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
		

		/*===============播放窗口-具体内容===============*/
		//final JButton btnLast =Component.getButtom(100, 60, 18, "播放");
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
		/*btnLast.addActionListener(new ActionListener() {
			

			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				jFrame.remove(panel2);
				jFrame.repaint();
			}
		});
		jPanel3.add(btnLast);
		//jPanel3.add(btnNext);*/
		
	}

	//初始化歌单内容
	private void initMusicListDetail() {
		
		jPanel2 = Component.getPanel(918, 700);
		cover =Component.getLable(270, 270);
		//cover.setIcon(new ImageIcon(sheet.getImagePath()));
		sheet = SheetHelper.getSheet("cd7e8ee8 038d 4c6e ae26 248704c59d67");
		cover.setIcon(new ImageIcon("D:\\Course\\java\\workplace\\CloudMusic\\images\\279759ee3d6d55fb8c19b66f6f224f4a21a4dd98.jpg"));
		blank = Component.getLable(80, 270);
		intro = Component.getLable(500, 270,25,"<html><body><span style='color:red;'>"+sheet.getName()+"</span><br><br><br><span style='color:red;'>"+sheet.getCreatorId()+"</span>于<span style='color:red;'>"+sheet.getDateCreated()+"</span>创建"+"<body></html>");
		listtitle = Component.getLable(918, 80, 22, "歌曲列表");
		list = Component.getPanel(918, 450);
		musics = SheetHelper.getAllSongs(sheet.getUuid().toString());
		
		
		// TODO Auto-generated method stub
		jPanel2.add(cover);
		jPanel2.add(blank);
		jPanel2.add(intro);
		jPanel2.add(listtitle);
		jPanel2.add(list);
		for (int i = 0; i < musics.size(); i++) {
			final Music music = (Music) musics.get(i);
			JLabel name = Component.getLable(399, 40, 16, music.getName() );
			name.setBackground(java.awt.Color.gray);
			list.add(name);
			name = Component.getLable(150, 40, 16, music.getTime());
			name.setBackground(java.awt.Color.white);
			list.add(name);
			name = Component.getLable(150, 40, 16, music.getSinger());
			name.setBackground(java.awt.Color.gray);
			list.add(name);
			JLabel name1 = Component.getLable(80, 40, 16,"播放");
			name1.setBackground(java.awt.Color.WHITE);
			name1.addMouseListener(new MouseListener() {
					
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
								mp3 = new MP3Player(music.getPath());
							} catch (FileNotFoundException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							isPlay = true;
							mp3.play();
						}
					};
					t1.start();
				}
			});

			list.add(name1);
			name = Component.getLable(80, 40, 16,"下载");
			name.setBackground(java.awt.Color.gray);
			list.add(name);
			
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
		jLableMylist = Component.getLable(280, 320);
		
		
		jPanel1.add(jPanel11);
		jPanel11.add(jLableOtherTitle);
		System.out.println(othersSheet.size());
		if (othersSheet.size() > 0) {
			for (int i = 0; i < (othersSheet.size()>4?othersSheet.size():4); i++) {
				final MusicSheet xSheet = (MusicSheet) othersSheet.get(i);
				JLabel other = Component.getLable(240, 59, 16, xSheet.getName());
				jPanel11.add(other);
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
						sheet = (MusicSheet) xSheet;
						musics = null;
						//musics = SheetHelper.getAllSongs(sheet.getUuid().toString());
						Map map =(Map) xSheet.getMusicItems();
						String[] msList = new String[map.size()];
						map.values().toArray(msList);

						intro.setText("<html><body><span style='color:red;'>   "+sheet.getName()+"</span><br><br><br><span style='color:red;'>   "+sheet.getCreatorId()+"</span>于<span style='color:red;'>"+sheet.getDateCreated()+"</span>创建"+"<body></html>");
						jPanel2.remove(list);
						list = null;
						list = Component.getPanel(918, 450);
						jPanel2.add(list);
						for (int i = 0; i < musics.size(); i++) {
							final Music music = (Music) musics.get(i);
							JLabel name = Component.getLable(399, 40, 16, music.getName() );
							name.setBackground(java.awt.Color.gray);
							list.add(name);
							name = Component.getLable(150, 40, 16, music.getTime());
							name.setBackground(java.awt.Color.white);
							list.add(name);
							name = Component.getLable(150, 40, 16, music.getSinger());
							name.setBackground(java.awt.Color.gray);
							list.add(name);
							JLabel name1 = Component.getLable(80, 40, 16,"播放");
							name1.setBackground(java.awt.Color.WHITE);
							name1.addMouseListener(new MouseListener() {
								
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
												mp3 = new MP3Player(music.getPath());
											} catch (FileNotFoundException e1) {
												// TODO Auto-generated catch block
												e1.printStackTrace();
											}
											isPlay = true;
											mp3.play();
										}
									};
									t1.start();
								}
							});
							
							name = Component.getLable(80, 40, 16,"下载");
							name.setBackground(java.awt.Color.gray);
							list.add(name);
						}
						jFrame.repaint();
					}
				});
			}
		}
		jPanel1.add(jPanel12);
		jPanel12.add(jLableMyTitle);
		jPanel12.add(jLableMylist);;
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
							sheet = (MusicSheet) xSheet;
							musics = null;
							musics = SheetHelper.getAllSongs(sheet.getUuid().toString());
							
							intro.setText("<html><body><span style='color:red;'>   "+sheet.getName()+"</span><br><br><br><span style='color:red;'>   "+sheet.getCreatorId()+"</span>于<span style='color:red;'>"+sheet.getDateCreated()+"</span>创建"+"<body></html>");
							jPanel2.remove(list);
							list = null;
							list = Component.getPanel(918, 450);
							jPanel2.add(list);
							for (int i = 0; i < musics.size(); i++) {
								final Music music = (Music) musics.get(i);
								JLabel name = Component.getLable(399, 40, 16, music.getName() );
								name.setBackground(java.awt.Color.gray);
								list.add(name);
								name = Component.getLable(150, 40, 16, music.getTime());
								name.setBackground(java.awt.Color.white);
								list.add(name);
								name = Component.getLable(150, 40, 16, music.getSinger());
								name.setBackground(java.awt.Color.gray);
								list.add(name);
								JLabel name1 = Component.getLable(80, 40, 16,"播放");
								name1.setBackground(java.awt.Color.WHITE);
								name1.addMouseListener(new MouseListener() {
									
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
													mp3 = new MP3Player(music.getPath());
												} catch (FileNotFoundException e1) {
													// TODO Auto-generated catch block
													e1.printStackTrace();
												}
												mp3.play();
											}
										};
										t1.start();
									}
								});
								list.add(name1);
								name = Component.getLable(80, 40, 16,"下载");
								name.setBackground(java.awt.Color.gray);
								list.add(name);
							}
							jFrame.repaint();
						}
					});

				jLableMylist.add(my);
			}
		}
	}

	public void init() throws HttpException, IOException {

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
}
