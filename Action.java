import edu.rit.se.swen383.audio.AudioSource;

/**
 * Created by Nikola on 3.3.2017..
 */
public class Action implements Command{

    @Override
    public void pause(PlayList pl) {
        pl.pause();
    }

    @Override
    public void resume(PlayList pl) {
        pl.resume();
    }

    @Override
    public void playNext(PlayList pl) {
        int nextIndex = pl.getSourceIndex() + 1;
                /*
                 * Don't move beyond the last play list element.
                 */
        if (nextIndex < pl.size()) {
            pl.play(nextIndex);
        }
    }

    @Override
    public void playPrevious(PlayList pl) {
        int prevIndex = pl.getSourceIndex() - 1;
                /*
                 * Don't move before the first play list element.
                 */
        if (prevIndex >= 0) {
            pl.play(prevIndex);
        }
    }

    @Override
    public void quit(PlayList pl) {

    }

    @Override
    public void replay(PlayList pl) {
        pl.play(pl.getSourceIndex());
    }

    @Override
    public void printPlaylist(PlayList pl) {

    }

    @Override
    public void printFileInformation(PlayList pl, String s) {
        AudioSource as;
        int i;

        try {
            String iv = s.substring(1).trim();
            i = Integer.parseInt(iv);
        } catch (Exception e) {
            i = -1; // no integer argument.
        }

        if (i < 0) {
            i = pl.getSourceIndex();
        }
        as = pl.getSource(i);

        if (i == (-1)) {
            System.out.println("Player is idle");
        } else if (as != null) {
            int duration = as.getDuration();
            int secs = duration % 60;
            int mins = duration / 60;

            System.out.println("Index:    " + i);
            System.out.println("File:     " + as.getFileName());
            System.out.println("Title:    " + as.getTitle());
            System.out.println("Artist:   " + as.getArtist());
            System.out.println("Album:    " + as.getAlbum());
            System.out.println("Genre:    " + as.getGenre());
            System.out.printf("Duration: %d:%02d\n", mins, secs);
        }
    }

    @Override
    public void getHelp() {
        System.out.println("+ = Play the file after the current one.");
        System.out.println("- = Play the file before the current one.");
        System.out.println("@ = Replay the current file.");
        System.out.println("h or H or ? = Print this help screen.");
        System.out.println("i [n] = Print information on file #'n'");
        System.out.println("        (or the current file if 'n' is omitted).");
        System.out.println("p [n] = Terminate any playback and start playing");
        System.out.println("        AudioSource #'n' (default 0).");
        System.out.println("P = Pause playback if any.");
        System.out.println("R = Resume playback if any.");
        System.out.println("t = Print the playback position in seconds.");
        System.out.println("s = Print number of playlist entries.");
        System.out.println("q = Quit the player.");
    }

    @Override
    public void playSpecific(String s, PlayList pl) {
        int i;
        try {
            String iv = s.substring(1).trim();
            i = Integer.parseInt(iv);
        } catch (Exception e) {
            i = 0;
        }
        pl.play(i);
    }

    @Override
    public void playListSize(PlayList pl) {
        System.out.println("Playlist size: " + pl.size());
    }

    @Override
    public void printTime(PlayList pl) {
        int position = pl.getPosition() / 1000; // remove milliseconds

        int secs = position % 60;
        int mins = position / 60;
        System.out.printf("Source position: %d:%02d\n", mins, secs);

    }

}
