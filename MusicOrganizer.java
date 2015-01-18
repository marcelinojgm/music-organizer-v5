import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Collections;

/**
 * A class to hold details of audio tracks.
 * Individual tracks may be played.
 * 
 * @author David J. Barnes and Michael KÃ¶lling
 * @version 2011.07.31
 */
public class MusicOrganizer
{
    // An ArrayList for storing music tracks.
    private ArrayList<Track> tracks;
    // A player for the music tracks.
    private MusicPlayer player;
    // A reader that can read music files and load them as tracks.
    private TrackReader reader;

    /**
     * Create a MusicOrganizer
     */
    public MusicOrganizer()
    {
        tracks = new ArrayList<Track>();
        player = new MusicPlayer();
        reader = new TrackReader();
        readLibrary("audio");
        System.out.println("Music library loaded. " + getNumberOfTracks() + " tracks.");
        System.out.println();
    }

    /**
     * Create a MusicOrganizer
     * indicando el directorio de donde saca las canciones
     */
    public MusicOrganizer(String directorio)
    {
        tracks = new ArrayList<Track>();
        player = new MusicPlayer();
        reader = new TrackReader();
        readLibrary(directorio);
        System.out.println("Music library loaded. " + getNumberOfTracks() + " tracks.");
        System.out.println();
    }

    /**
     * Add a track file to the collection.
     * @param filename The file name of the track to be added.
     */
    public void addFile(String filename)
    {
        tracks.add(new Track(filename));
    }

    /**
     * Add a track to the collection.
     * @param track The track to be added.
     */
    public void addTrack(Track track)
    {
        tracks.add(track);
    }

    /**
     * Play a track in the collection.
     * @param index The index of the track to be played.
     */
    public void playTrack(int index)
    {
        //en caso de que haya una reproduccion en curso
        if(player.getPlaying())
        {
            System.out.println("ERROR!!! Ya hay un track en reproduccion.");
            System.out.println("Detenga la reproduccion actual o espere a que termine");
        }
        //en caso de que no haya ninguna reproduccion en curso
        else
        {
            if(indexValid(index)) {
                Track track = tracks.get(index);
                player.startPlaying(track.getFilename());
                System.out.println("Now playing: " + track.getArtist() + " - " + track.getTitle());
                track.incrementPlayCount();
            }
        }
    }

    /**
     * Return the number of tracks in the collection.
     * @return The number of tracks in the collection.
     */
    public int getNumberOfTracks()
    {
        return tracks.size();
    }

    /**
     * List a track from the collection.
     * @param index The index of the track to be listed.
     */
    public void listTrack(int index)
    {
        System.out.print("Track " + index + ": ");
        Track track = tracks.get(index);
        System.out.println(track.getDetails());
    }

    /**
     * Show a list of all the tracks in the collection.
     */
    public void listAllTracks()
    {
        System.out.println("Track listing: ");

        for(Track track : tracks) {
            System.out.println(track.getDetails());
        }
        System.out.println();
    }

    /**
     * muestre los detalles de todos los tracks almacenados 
     */
    public void listAllTrackWithIterator()
    {
        Iterator<Track> it = tracks.iterator();
        while(it.hasNext())
        {
            System.out.println(it.next().getDetails());
        }
        System.out.println();
    }

    /**
     * List all tracks by the given artist.
     * @param artist The artist's name.
     */
    public void listByArtist(String artist)
    {
        for(Track track : tracks) {
            if(track.getArtist().contains(artist)) {
                System.out.println(track.getDetails());
            }
        }
    }

    /**
     * Remove a track from the collection.
     * @param index The index of the track to be removed.
     */
    public void removeTrack(int index)
    {
        if(indexValid(index)) {
            tracks.remove(index);
        }
    }

    /**
     * Play the first track in the collection, if there is one.
     */
    public void playFirst()
    {
        //en caso de que haya una reproduccion en curso
        if(player.getPlaying())
        {
            System.out.println("ERROR!!! Ya hay un track en reproduccion.");
            System.out.println("Detenga la reproduccion actual o espere a que termine");
        }
        //en caso de que no haya ninguna reproduccion en curso
        else
        {
            if(tracks.size() > 0) 
            {
                player.startPlaying(tracks.get(0).getFilename());
                tracks.get(0).incrementPlayCount();
            }
        }
    }

    /**
     * Stop the player.
     */
    public void stopPlaying()
    {
        player.stop();
    }

    /**
     * Determine whether the given index is valid for the collection.
     * Print an error message if it is not.
     * @param index The index to be checked.
     * @return true if the index is valid, false otherwise.
     */
    private boolean indexValid(int index)
    {
        // The return value.
        // Set according to whether the index is valid or not.
        boolean valid;

        if(index < 0) {
            System.out.println("Index cannot be negative: " + index);
            valid = false;
        }
        else if(index >= tracks.size()) {
            System.out.println("Index is too large: " + index);
            valid = false;
        }
        else {
            valid = true;
        }
        return valid;
    }

    private void readLibrary(String folderName)
    {
        ArrayList<Track> tempTracks = reader.readTracks(folderName, ".mp3");

        // Put all thetracks into the organizer.
        for(Track track : tempTracks) {
            addTrack(track);
        }
    }

    /**
     * tome un único parámetro de tipo String 
     * y muestre por pantalla la información de los tracks 
     * que contienen dicha cadena en el título de la canción.
     */
    public void findInTitle(String title)
    {
        for(Track track : tracks) 
        {
            if(track.getTitle().contains(title))
            {
                System.out.println(track.getDetails());
            }
        }
    }

    /**
     * selecciona el la cancion por su indice y fija su valor de año
     */
    public void setAlbumTrack(int index,String album)
    {
        tracks.get(index).setAlbum(album);
    }

    /**
     * informe por pantalla de si en este momento se está reproduciendo música o no
     */
    public void isPlaying()
    {
        if(player.getPlaying())
        {
            System.out.println("is playing");

        }
        else
        {
            System.out.println("stop");
        }
    }

    /**
     * borra los elementos de un artista determinado
     */
    public void removeByArtist(String artist)
    {
        Iterator<Track> it = tracks.iterator();
        while(it.hasNext())
        {
            if(it.next().getArtist().contains(artist)) 
            {
                it.remove();
            } 
        }
    }

    /**
     * borra los elementos de un titulo determinado
     */
    public void removeByTitle(String title)
    {
        Iterator<Track> it = tracks.iterator();
        while(it.hasNext())
        {
            if(it.next().getTitle().contains(title)) 
            {
                it.remove();
            } 
        }
    }

    public void playRandom()
    {
        Random random = new Random();
        playTrack(random.nextInt(tracks.size()));
    }

    /**
     * reproduce los primeros segundos de todas las canciones de la coleccion en un orden aleatorio
     */
    public void playShuffle()
    {
        //coleccion random
        ArrayList<Track> tracksRandom = new ArrayList<>();
        tracksRandom = (ArrayList)tracks.clone();
        
        //barajado de la coleccion
        Collections.shuffle(tracksRandom);
        
        Iterator<Track> itTracks = tracksRandom.iterator();
        
        while(itTracks.hasNext())
        {
            Track track = itTracks.next();
            //info del elemento sonando actualmente
            System.out.println("Playing:");
            System.out.println(track.getDetails());
            //reproduccion primeros segundos e incremento del ccount
            player.playSample(track.getFilename());
            track.incrementPlayCount();
            
        }
    }
    
    /**
     * reproduce los primeros segundos de todas las canciones de la coleccion en un orden aleatorio
     */
        public void playShuffle2()
    {
        //copia de la coleccion
        ArrayList<Track> copia = new ArrayList<>();
        copia = (ArrayList)tracks.clone();
        //contador de reproducciones
        int countPlayer = 0;
        while(countPlayer < tracks.size())
        {
            //seleccion aleatoria de un elemento de la coleccion
            Random random = new Random();
            int trackRandom = random.nextInt(copia.size()); 
            Track track = copia.get(trackRandom);
            
            //info del elemento sonando actualmente
            System.out.println("Playing:");
            System.out.println(track.getDetails());
            
            //reproduccion primeros segundos e incremento del ccount
            player.playSample(track.getFilename());
            track.incrementPlayCount();
           
            //remover la cancion de la coleccion
            copia.remove(trackRandom);
            countPlayer++;
            
        }

    }
}