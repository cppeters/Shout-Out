import java.sql.Time;

/**
 * TCSS 445 - Summer 2015
 * 
 * Project Final
 *
 * Author: Casey Peterson
 * (Some code used from Professor Menaka Abraham example)
 */

/**
 * Represents a 'Shout' Table based on user favorites
 */
public class Shout
{
    // instance fields
    
    private String myUserID;
    private String myPassword;
    private String myFirstName;
    private String myLastName;
    private String myArtist;
    private String myAlbum;
    private String mySong;
    private String myGenre;
    private Time mySongLength;
    
    /**
     * Constructor that will Initialize instance fields.
     *  
     * @param theArtist
     * @param theAlbum
     * @param theSong
     * @param theGenre
     * @param theSongLength
     */
    public Shout(String theSong, String theArtist, String theAlbum, Time theSongLength,
                 String theGenre)
    {
        setArtist(theArtist);
        setAlbum(theAlbum);
        setSong(theSong);
        setGenre(theGenre);
        setSongLength(theSongLength);
    }
    
    /**
     * Overload Constructor.
     * @param theUserID
     * @param theFirstName
     * @param theLastName
     * @param thePassword
     */
    public Shout(String theUserID, String theFirstName, String theLastName, String thePassword)
    {
        setUserID(theUserID);
        setFirstName(theFirstName);
        setLastName(theLastName);
        setPassword(thePassword);
    }
    
    /**
     * Overload constructor.
     * @param theUserID
     * @param thePassword
     */
    public Shout(String theUserID, String thePassword)
    {
        setUserID(theUserID);
        setPassword(thePassword);
    }
    
    /**
     * Set the user password.
     * @param thePassword
     */
    private void setPassword(String thePassword)
    {
        if (thePassword == null || thePassword.length() == 0 )
            throw new IllegalArgumentException("Not a valid password.");
        myPassword = thePassword;
        
    }

    /**
     * Get the user password.
     * @return
     */
    public String getPassword()
    {
        return myPassword;
    }
    
    /**
     * Set user last name.
     * @param theLastName
     */
    private void setLastName(String theLastName)
    {
        if (theLastName == null || theLastName.length() == 0 )
            throw new IllegalArgumentException("Not a valid name.");
        myLastName = theLastName;
        
    }
    
    /**
     * Get the user last name.
     * @return
     */
    public String getLastName()
    {
        return myLastName;
    }

    /**
     * Set user first name.
     * @param theFirstName
     */
    private void setFirstName(String theFirstName)
    {
        if (theFirstName == null || theFirstName.length() == 0 )
            throw new IllegalArgumentException("Not a valid name.");
        myFirstName = theFirstName;
        
    }

    /**
     * Get the user first name.
     * @return
     */
    public String getFirstName()
    {
        return myFirstName;
    }
    
    /**
     * Set userID.
     * @param theUserID
     */
    private void setUserID(String theUserID)
    {
        if (theUserID == null || theUserID.length() == 0 )
            throw new IllegalArgumentException("Not a valid userID.");
        myUserID = theUserID;
        
    }
    
    /**
     * Get the userID.
     * @return
     */
    public String getUserID()
    {
        return myUserID;
    }

    /**
     * Set the artist name.
     * @param theArtist the artist name.
     */
    public void setArtist(String theArtist)
    {/*
        if (theArtist == null || theArtist.length() == 0 )
            throw new IllegalArgumentException("Not a valid artist.");*/
        myArtist = theArtist;
    }
    
    /**
     * Get the artist name.
     * @return the artist name.
     */
    public String getArtist()
    {
        return myArtist;
    }
    
    /**
     * Set the album name.
     * @param theAlbum the album name.
     */
    public void setAlbum(String theAlbum)
    {/*
        if (theAlbum == null || theAlbum.length() == 0 )
            throw new IllegalArgumentException("Not a valid album.");*/
        myAlbum = theAlbum;
    }
    
    /**
     * Get the album name.
     * @return the album name.
     */
    public String getAlbum()
    {
        return myAlbum;
    }
    
    /**
     * Set the song name.
     * @param theSong the song name.
     */
    public void setSong(String theSong)
    {/*
        if (theSong == null || theSong.length() == 0 )
            throw new IllegalArgumentException("Not a valid song.");*/
        mySong = theSong;
    }
    
    /**
     * Get the song name.
     * @return the song name.
     */
    public String getSong()
    {
        return mySong;
    }
    
    /**
     * Set the genre.
     * @param theGenre the genre.
     */
    public void setGenre(String theGenre)
    {/*
        if (theGenre == null || theGenre.length() == 0 )
            throw new IllegalArgumentException("Not a valid genre.");*/
        myGenre = theGenre;
    }
    
    /**
     * Get the genre.
     * @return the genre.
     */
    public String getGenre()
    {
        return myGenre;
    }
    
    /**
     * Set the song length.
     * @param theSongLength length of song.
     */
    public void setSongLength(Time theSongLength)
    {/*
        if (theSongLength == null)
            throw new IllegalArgumentException("Song length cannot be negative or 0.");*/
        
        mySongLength = theSongLength;
    }
    
    /**
     * Get the song length.
     * @return length of song
     */
    public Time getSongLength()
    {
        return mySongLength;
    }
    
    /**
     * String representation.
     *
     * {@inheritDoc}
     */
    @Override
    public String toString()
    {
        return "Song [Name=" + mySong + ", " + " artist= " + myArtist + ", " + " length="
                        + mySongLength + ", genre=" + myGenre + "]";
    }
}
