import java.sql.*;
import java.util.*;

public class SqlHelper {
	public static Connection conn = null;
	public static Statement stat = null;
	
	// 连接数据库
	public static void getConnection() 
	{
		try
		{
			Class.forName("org.sqlite.JDBC");  
			conn = DriverManager.getConnection("jdbc:sqlite:music.db");  
			stat = conn.createStatement();   
	        stat.executeUpdate( "create table if not exists Sheet(SheetId VARCHAR(50) NOT NULL, Name VARCHAR(30) NOT NULL, Date DATETIME NOT NULL, OwnerId VARCHAR(15) NOT NULL, ImagePath VARCHAR(100) NOT NULL);" );
	        stat.executeUpdate( "create table if not exists Music(MusicId INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, Name VARCHAR(30) NOT NULL, Singer VARCHAR(30) NOT NULL, SheetId VARCHAR(50) NOT NULL, MusicMd5 VARCHAR(40) NOT NULL, MusicPath VARCHAR(100) NOT NULL, Time VARCHAR(10) NOT NULL);" );
		}
		catch( Exception e )
		{
			e.printStackTrace ( );
		}
	}
	
	// 关闭数据库连接
	public static void closeConnection()
	{
		try
		{
			conn.close();
		}
		catch( Exception e )
		{
			e.printStackTrace ( );
		}
	}
	
	// 更新数据库数据
	public static void update(String sql)
	{
		try
		{
			getConnection();
			stat.executeUpdate(sql);
			conn.close();
		}
		catch( Exception e )
		{
			e.printStackTrace ( );
		}
	}
	
	// 查询数据库数据
	public static ArrayList select(String sql)
	{
		try
		{
			getConnection();
			ResultSet rs = stat.executeQuery(sql); 
			if (rs == null)   
               return null;   
           ResultSetMetaData md = rs.getMetaData(); 
           int columnCount = md.getColumnCount();
           ArrayList list = new ArrayList();   
           Map rowData = new HashMap();   
           while (rs.next())
           {
        	   rowData = new HashMap(columnCount);   
               for (int i = 1; i <= columnCount; i++)
            	   rowData.put(md.getColumnName(i), rs.getObject(i));
               list.add(rowData);   
           }
           conn.close();
           return list;   		
		}
		catch( Exception e )
		{
			e.printStackTrace ( );
			return null;
		}		
	}
}
