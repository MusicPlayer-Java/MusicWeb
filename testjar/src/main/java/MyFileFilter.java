import java.io.File;

import javax.swing.filechooser.FileFilter;

public class MyFileFilter extends FileFilter {
	private String ext;
	
	MyFileFilter(String ext) {
		this.ext = ext;
	}

	public boolean accept(File file) {
		if (file.isDirectory()) {
			return true;
		}
		String fileName = file.getName();
		int index = fileName.lastIndexOf('.');
	    if (index > 0 && index < fileName.length() - 1) {
	    	String extension = fileName.substring(index + 1).toLowerCase();
	    	if (extension.equals(ext)) {
	    		return true;
	    	}
	    }
	    return false;
	}

	public String getDescription() {
		if (ext.equals("mp3")) {
			return "Microsoft 音乐文件(*.mp3)";
		}
		if (ext.equals("jpg")) {
			return "Microsoft 图片文件(*.jpg)";
		}
		return "";
	}

}
