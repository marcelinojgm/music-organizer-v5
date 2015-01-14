import java.time.Year;

/**
 * Store the details of a music track,
 * such as the artist, title, and file name.
 * 
 * @author David J. Barnes and Michael KÃ¶lling
 * @version 2011.07.31
 */
public class Track
{
    // The artist.
    private String artist;
    // The track's title.
    private String title;
    // Where the track is stored.
    private String filename;
    // numero de reproducciones de la cancion
    private int playCount;
    // año de la cancion 
    private Year year;

    /**
     * Constructor for objects of class Track.
     * @param artist The track's artist.
     * @param title The track's title.
     * @param filename The track file. 
     */
    public Track(String artist, String title,Year year, String filename)
    {
        setDetails(artist, title, year , filename);
        this.playCount = 0;
    }

    /**
     * Constructor for objects of class Track.
     * It is assumed that the file name cannot be
     * decoded to extract artist and title details.
     * @param filename The track file. 
     */
    public Track(String filename)
    {
        setDetails("unknown", "unknown", null ,filename);
    }

    /**
     * Return the artist.
     * @return The artist.
     */
    public String getArtist()
    {
        return artist;
    }

    /**
     * Return the title.
     * @return The title.
     */
    public String getTitle()
    {
        return title;
    }

    /**
     * Return the file name.
     * @return The file name.
     */
    public String getFilename()
    {
        return filename;
    }

    /**
     * return the playCount
     */
    public int getPlayCount()
    {
        return playCount;
    }

    /**
     * return age
     */
    public Year getAge()
    {
        return this.year;
    }

    /**
     * Return details of the track: artist, title and file name.
     * @return The track's details.
     */
    public String getDetails()
    {
        String year = null; 
        if(this.year == null)
        {
            year = "unknown";
        }
        else 
        {
            year = this.year + "";
        }
        return artist + ": " + title + " year: " + year + "  (file: " + filename + ") reproductions:" + playCount;
    }

    /**
     * Set details of the track.
     * @param artist The track's artist.
     * @param title The track's title.
     * @param filename The track file. 
     */
    private void setDetails(String artist, String title, Year year , String filename)
    {
        this.artist = artist;
        this.title = title;
        this.year   = year;
        this.filename = filename;
    }

    /**
     * fijar fecha del track
     */
    public void setYear(Year year)
    {
        this.year = year ;  
    }

    /**
     * incrementa en 1 playCount
     */
    public void incrementPlayCount()
    {
        playCount++;
    }

    /**
     * resetea el numero de veces que se ha escuchado el Track
     */
    public void resetPlayCount()
    {
        playCount = 0;
    }
}
