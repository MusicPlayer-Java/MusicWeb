import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

import javax.swing.JFileChooser;


public class FileHelper {
	// 打开文件资源管理器
	public static String openMusicChooser() {
		JFileChooser file = new JFileChooser (".");
		file.setAcceptAllFileFilterUsed(false);
		file.addChoosableFileFilter(new MyFileFilter("mp3"));
		int result = file.showOpenDialog(null);
		if(result == JFileChooser.APPROVE_OPTION)
		{
		   String path = file.getSelectedFile().getAbsolutePath();
		   return path;
		}
		else
		{
		    System.out.println("你已取消并关闭了窗口！");
		    return "";
		}
	}
	
	public static String openPictureChooser() {
		JFileChooser file = new JFileChooser (".");
		file.setAcceptAllFileFilterUsed(false);
		file.addChoosableFileFilter(new MyFileFilter("jpg"));
		int result = file.showOpenDialog(null);
		if(result == JFileChooser.APPROVE_OPTION)
		{
		   String path = file.getSelectedFile().getAbsolutePath();
		   return path;
		}
		else
		{
		    System.out.println("你已取消并关闭了窗口！");
		    return "";
		}
	}
	
	// 将文件从一个目录拷贝至另一个目录
	public static void copyFile(String oldPath, String newPath) { 
		try { 
			int bytesum = 0; 
			int byteread = 0; 
			File oldfile = new File(oldPath); 
			if (oldfile.exists()) { //文件存在时 
				String name = oldfile.getName();
				InputStream inStream = new FileInputStream(oldPath); //读入原文件 
				FileOutputStream fs = new FileOutputStream(newPath); 
				byte[] buffer = new byte[1444]; 
				int length; 
				while ( (byteread = inStream.read(buffer)) != -1) { 
					bytesum += byteread; //字节数 文件大小 
					fs.write(buffer, 0, byteread); 
				} 
				inStream.close(); 
			} 
		} 
		catch (Exception e) { 
			System.out.println("复制单个文件操作出错"); 
			e.printStackTrace(); 
		} 
	} 
}
