import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import ouc.cs.course.java.model.MusicSheet;


public class Sheet extends MusicSheet {
	private String id;
	private String name;
	private String date;
	private String owner_id;
	private String image_path;
	
	private static String root = System.getProperty("user.dir").toString().replace('\\', '/');
	
	public Sheet(Map hm)
	{
		id = hm.get("SheetId").toString();
		name = hm.get("Name").toString();
		date = hm.get("Date").toString();
		owner_id = hm.get("OwnerId").toString();
		image_path = root + hm.get("ImagePath").toString();
	}
	
	public Sheet(){
		String s1 = "-";
		String s2 = "";
		id = UUID.randomUUID().toString().replaceAll(s1, s2);
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		date = formatter.format(new Date());
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
}
