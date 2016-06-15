import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.swing.JOptionPane;

/**
 * TCSS 445 - Summer 2015
 * 
 * Project Final
 *
 * Author: Casey Peterson
 * (Some code used from Professor Menaka Abraham example)
 */

/**
 * A class for the Shout database
 *
 */
public class ShoutDB 
{
	private static String userName = "root";
	private static String password = "";
	private static String serverName = "localhost:3306";
	private static Connection conn;
	private List<Shout> list;

	/**
	 * Creates a sql connection to MySQL using the properties for
	 * userid, password and server information.
	 * @throws SQLException
	 */
	public static void createConnection() throws SQLException {
		Properties connectionProps = new Properties();
		connectionProps.put("user", userName);
		connectionProps.put("password", password);

		conn = DriverManager.getConnection("jdbc:" + "mysql" + "://"
				+ serverName + "/", connectionProps);

		System.out.println("Connected to database");
	}

	/**
	 * Returns a list of Shout objects from the database.
	 * @return list of songs
	 * @throws SQLException
	 */
	public List<Shout> getShout() throws SQLException {
		if (conn == null) {
			createConnection();
		}
		Statement stmt = null;
		String query = " select artistName, songName, albumName, albumGenre, songLength "
				+ " from shoutdb.song natural join shoutdb.album ";

		list = new ArrayList<Shout>();
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				String song = rs.getString("songName");
				String artist = rs.getString("artistName");
				String album = rs.getString("albumName");
				Time length = rs.getTime("songLength");
				String genre = rs.getString("albumGenre");
				Shout shout = new Shout(song, artist, album, length, genre);
				list.add(shout);
			}
		} catch (SQLException e) {
			System.out.println(e);
		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
		return list;
	}

	/**
	 * Filters the shout list to find the given word. Returns a list with the
	 * shout objects that match the word provided.
	 * @param theWord
	 * @return list of songs/artist/albums that contain the word.
	 */
	public List<Shout> getShout(String theWord) {
		List<Shout> filterList = new ArrayList<Shout>();
		try {
			list = getShout();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for (Shout shout : list) {
			if (shout.getSong().toLowerCase().contains(theWord.toLowerCase())) 
			{
				filterList.add(shout);
			}
			else if (shout.getAlbum().toLowerCase().contains(theWord.toLowerCase()))
			{
			    filterList.add(shout);
			}
			else if (shout.getArtist().toLowerCase().contains(theWord.toLowerCase()))
			{
			    filterList.add(shout);
			}
		}
		return filterList;
	}
	
	/**
	 * Retrieves the list of favorite Artists/Songs/Albums
	 * @param theUserID
	 * @return
	 * @throws SQLException
	 */
	public List<Shout> getFavorites(String theUserID) throws SQLException
	{
	    List<Shout> favList = new ArrayList<Shout>();
	    String song = null;
	    String album = null;
	    Time length = null;
	    String genre = null;
	    
	    if (conn == null) {
            createConnection();
        }
	    
        PreparedStatement stmt = conn.prepareStatement("select artistName, songID, albumID "
                            + " from shoutdb.favorite "
                            + " where shoutdb.favorite.userID = ?");    
        stmt.setString(1, theUserID);    
       
        try 
        {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) 
            {
                String artist = rs.getString("artistName");
                String songID = rs.getString("songID");
                String albumID = rs.getString("albumID");
                if (songID != null) 
                {   
                    song = getSong(songID, albumID);
                    length = getSongLength(songID, albumID);                    
                }
                if (albumID != null)
                {
                    album = getAlbum(albumID);
                    genre = getAlbumGenre(albumID);
                }
                
                Shout shout = new Shout(song, artist, album, length, genre);
                favList.add(shout);
            }
        } 
        catch (SQLException e) 
        {
            System.out.println(e);
        } 
        finally 
        {
            if (stmt != null) 
            {
                stmt.close();
            }
        }
        return favList;
	}

	/**
	 * Get album genre with albumID
     * @param albumID
     * @return
	 * @throws SQLException 
     */
    private String getAlbumGenre(String theAlbumID) throws SQLException
    {
        String genre = null;
        if (conn == null) {
            createConnection();
        }
        
        PreparedStatement stmt = conn.prepareStatement("select albumGenre from shoutdb.Album  " 
                        + " where shoutdb.Album.albumID = ? ");    
        
        stmt.setString(1, theAlbumID);
       
        try 
        {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) 
            {
                genre = rs.getString("albumGenre");
            }
            
        } 
        catch (SQLException e) 
        {
            System.out.println(e);
        } 
        finally 
        {
            if (stmt != null) 
            {
                stmt.close();
            }
        }
        return genre;
    }

    /**
     * Get album name with albumID
     * @param albumID
     * @return
     * @throws SQLException 
     */
    private String getAlbum(String theAlbumID) throws SQLException
    {
        String album = null;
        if (conn == null) {
            createConnection();
        }
        
        PreparedStatement stmt = conn.prepareStatement("select albumName from shoutdb.Album  " 
                        + " where shoutdb.Album.albumID = ? ");    
        
        stmt.setString(1, theAlbumID);
       
        try 
        {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) 
            {
                album = rs.getString("albumName");
            }
            
        } 
        catch (SQLException e) 
        {
            System.out.println(e);
        } 
        finally 
        {
            if (stmt != null) 
            {
                stmt.close();
            }
        }
        return album;
    }

    /**
     * @param songID
     * @param albumID
     * @return
     * @throws SQLException 
     */
    private Time getSongLength(String theSongID, String theAlbumID) throws SQLException
    {
        Time length = null;
        if (conn == null) {
            createConnection();
        }
        
        PreparedStatement stmt = conn.prepareStatement("select songLength from shoutdb.Song  " 
                        + " where shoutdb.Song.songID = ? AND shoutdb.Song.albumID = ? ");    
        stmt.setString(1, theSongID);
        stmt.setString(2, theAlbumID);
       
        try 
        {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) 
            {
                length = rs.getTime("songLength");
            }
            
        } 
        catch (SQLException e) 
        {
            System.out.println(e);
        } 
        finally 
        {
            if (stmt != null) 
            {
                stmt.close();
            }
        }
        return length;

    }

    /**
     * Retrieve song name with songID and albumID
     * @param songID
     * @param albumID
     * @return
     * @throws SQLException 
     */
    private String getSong(String theSongID, String theAlbumID) throws SQLException
    {
           String song = "";
           if (conn == null) {
               createConnection();
           }
           
           PreparedStatement stmt = conn.prepareStatement("select songName from shoutdb.Song  " 
                           + " where shoutdb.Song.songID = ? AND shoutdb.Song.albumID = ? ");    
           stmt.setString(1, theSongID);
           stmt.setString(2, theAlbumID);
          
           try 
           {
               
               ResultSet rs = stmt.executeQuery();
               while (rs.next()) 
               {
                   song = rs.getString("songName");     
               }
           } 
           catch (SQLException e) 
           {
               System.out.println(e);
           } 
           finally 
           {
               if (stmt != null) 
               {
                   stmt.close();
               }
           }
           return song;

    }

    /**
	 * Create a new user.
	 * @param shout
	 */
	public void register(Shout shout) {
		String sql = "insert into shoutdb.User values " + "(?, ?, ?, ?); ";
		
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setString(1, shout.getUserID());
			preparedStatement.setString(2, shout.getFirstName());
			preparedStatement.setString(3, shout.getLastName());
			preparedStatement.setString(4, shout.getPassword());
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e);
			e.printStackTrace();
		} 
	}
	
	/**
     * Update a user account.
     * @param shout
     */
    public void update(Shout shout) 
    {
        
        String sql = "update shoutdb.User set firstName = ?, lastName = ?, password = ? " 
                     + " where shoutdb.User.userID = ? ";
        
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, shout.getFirstName());
            preparedStatement.setString(2, shout.getLastName());
            preparedStatement.setString(3, shout.getPassword());
            preparedStatement.setString(4, shout.getUserID());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);
            e.printStackTrace();
        } 
    }
	
    /**
     * Check whether there is a user and password for this login.
     * 
     * @param theUserID
     * @param thePassword
     * @return
     * @throws SQLException
     */
	public boolean login(String theUserID, String thePassword) throws SQLException
	{
	    boolean login = false;
	    
	    if (conn == null) {
            createConnection();
        }
        Statement stmt = null;
        
	    String sql = " select userID, password " +
	                 " from shoutdb.User ";
        
	    try {
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                String user = rs.getString("userID");
                String password = rs.getString("password");
                if (user.equals(theUserID) && (password.equals(thePassword)))
                    login = true;
            }
        } catch (SQLException e) {
            System.out.println(e);
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
	    if (login) JOptionPane.showMessageDialog(null, "Login Successfull!");
	    else JOptionPane.showMessageDialog(null, "Login Failed, Have You Registered?");
	    
	    return login;
	}

    /**
     * Delete the user.
     * @param userID
     */
    public void delete(String theUserID)
    {
        String sql = "delete from shoutdb.User  " 
                        + " where shoutdb.User.userID = ? ";
           
           PreparedStatement preparedStatement = null;
           try {
               preparedStatement = conn.prepareStatement(sql);
               preparedStatement.setString(1, theUserID);
               preparedStatement.executeUpdate();
           } catch (SQLException e) {
               System.out.println(e);
               e.printStackTrace();
           } 
    }

    /**
     * @param artist
     * @throws SQLException 
     */
    public List<String> shoutOut(String theArtist) throws SQLException
    {
        List<String> shoutList = new ArrayList<String>();
        
        if (conn == null) {
            createConnection();
        }
        
        PreparedStatement stmt = conn.prepareStatement("select shout "
                            + " from shoutdb.shout "
                            + " where shoutdb.shout.artistName = ?");    
        stmt.setString(1, theArtist);    
       
        try 
        {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) 
            {
                String shout = rs.getString("shout");                
                shoutList.add(theArtist + " [SHOUT!] " + shout);
            }
        } 
        catch (SQLException e) 
        {
            System.out.println(e);
        } 
        finally 
        {
            if (stmt != null) 
            {
                stmt.close();
            }
        }
        return shoutList;
    }
}

