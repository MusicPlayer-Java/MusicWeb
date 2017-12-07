import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

import ouc.cs.course.java.model.MusicSheet;


public class Sheet extends MusicSheet {
	private String id;
	private String name;
	private String date;
	private String owner_id;
	private String owner_name;
	private String image_path;
	private Map musicItems = new HashMap();
	
	
	private static String root = System.getProperty("user.dir").toString().replace('\\', '/');
	
	public Sheet(Map hm)
	{
		id = hm.get("SheetId").toString();
		name = hm.get("Name").toString();
		date = hm.get("Date").toString();
		owner_id = hm.get("OwnerId").toString();
		image_path = root + hm.get("ImagePath").toString();
		owner_name = "UNKNOWN";
		SqlHelper.getConnection();
		ArrayList ls = SqlHelper.select("select * from Music where SheetId = '" + id + "'");
		Iterator it = ls.iterator(); 
		while(it.hasNext()) {   
		    Map map = (Map)it.next();
		    if(map.get("MusicMd5") != null && map.get("Name") != null)
		    	musicItems.put(map.get("MusicMd5").toString(), map.get("Name").toString());
		} 	
		SqlHelper.closeConnection();
		
	}
	
	public Sheet(){
		String s1 = "-";
		String s2 = "";
		id = UUID.randomUUID().toString().replaceAll(s1, s2);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		date = formatter.format(new Date());
		owner_id = "16020032011";
		owner_name = "UNKNOWN";
	}
	
	public String getUuid()
	{
		return id;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getDateCreated()
	{
		return date;
	}
	
	public String getCreatorId()
	{
		return owner_id;
	}
	
	public String getCreator() {
		return owner_name;
	}
	
	public String getPicture()
	{
		return image_path;
	}
	
	public void setUuid(String id)
	{
		this.id = id;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public void setDateCreated(String date)
	{
		this.date = date;
	}
	
	public void setCreatorId(String id)
	{
		this.owner_id = id;
	}
	
	public void setPicture(String path)
	{
		this.image_path = path;
	}
	
	public void setMusicItems(Map musicItems) {
		this.musicItems = musicItems;
	}
}
