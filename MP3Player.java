/*
 * Main driver class for the command line version of the audio player.
 * Creates a playlist from the command line arguments and then loops
 * looking for simple commands to control playback.
 */

import edu.rit.se.swen383.audio.AudioSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class MP3Player {
    /*
     * Driver with a simple command language to control the
     * audio playback.
     */
    public static void main(String args[]) {
        /*
         * We need at least one file to play.
         */
        PlayList pl;

        Scanner sc = new Scanner(System.in);

        if (args.length < 1) {
            println("Usage: MP3Player mp3file ...");
            return;
        }

        if (args[0].substring(args[0].length() - 3).equals("txt")) {
            ArrayList<String> songList = new ArrayList<>();

            try {
                for (String line : Files.readAllLines(Paths.get(args[0])))
                    songList.add(line);
            } catch (IOException e) {
                e.printStackTrace();
            }


            pl = new PlayList(songList.toArray(new String[0]));

            println("You've loaded playlist with these songs:");
            for (String song : songList)
                println("\t" + song);
        } else {
            /*
            * Make the play list if file with playlist isn't found.
            */
            pl = new PlayList(args);

            println("Songs selected: ");
            for (String song : args)
                println("\t" + song);
        }



        /*
         * Command loop.
         * Unrecognized commands are ignored.
         */
        char command = ' ';
        while (command != 'q') {
            String s = null;
            s = sc.next();
            command = s.charAt(0);

            if (command == '+') {
                int nextIndex = pl.getSourceIndex() + 1;
                /*
                 * Don't move beyond the last play list element.
                 */
                if (nextIndex < pl.size()) {
                    pl.play(nextIndex);
                }
            }
            if (command == '-') {
                int prevIndex = pl.getSourceIndex() - 1;
                /*
                 * Don't move before the first play list element.
                 */
                if (prevIndex >= 0) {
                    pl.play(prevIndex);
                }
            }
            if (command == '@') {
                pl.play(pl.getSourceIndex());
            }
            if (command == 'h' || command == 'H' || command == '?') {
                println("+ = Play the file after the current one.");
                println("- = Play the file before the current one.");
                println("@ = Replay the current file.");
                println("h or H or ? = Print this help screen.");
                println("i [n] = Print information on file #'n'");
                println("        (or the current file if 'n' is omitted).");
                println("p [n] = Terminate any playback and start playing");
                println("        AudioSource #'n' (default 0).");
                println("P = Pause playback if any.");
                println("R = Resume playback if any.");
                println("t = Print the playback position in seconds.");
                println("s = Print number of playlist entries.");
                println("q = Quit the player.");
            }
            if (command == 'i') {
                AudioSource as = null;
                int i = -1;

                try {
                    String iv = s.substring(1).trim();
                    i = Integer.parseInt(iv);
                } catch (Exception e) {
                    i = -1; // no integer argument.
                }
                ;
                if (i < 0) {
                    i = pl.getSourceIndex();
                }
                as = pl.getSource(i);

                if (i == (-1)) {
                    println("Player is idle");
                } else if (as != null) {
                    int duration = as.getDuration();
                    int secs = duration % 60;
                    int mins = duration / 60;

                    println("Index:    " + i);
                    println("File:     " + as.getFileName());
                    println("Title:    " + as.getTitle());
                    println("Artist:   " + as.getArtist());
                    println("Album:    " + as.getAlbum());
                    println("Genre:    " + as.getGenre());
                    System.out.printf("Duration: %d:%02d\n", mins, secs);
                }
            }
            if (command == 'p') {
                int i = 0;
                try {
                    String iv = s.substring(1).trim();
                    i = Integer.parseInt(iv);
                } catch (Exception e) {
                    i = 0;
                }
                pl.play(i);
            }
            if (command == 'P') {
                pl.pause();
            }
            if (command == 'R') {
                pl.resume();
            }
            if (command == 's') {
                println("Playlist size: " + pl.size());
            }
            if (command == 't') {
                int position = pl.getPosition() / 1000; // remove milliseconds
                int secs = position % 60;
                int mins = position / 60;
                System.out.printf("Source position: %d:%02d\n", mins, secs);
            }
        }
        /*
         * System.exit(0) rather than return as there is another thread
         * running and a return would only terminate the main thread.
         */
        System.exit(0);
    }

    private static void println(String s) {
        System.out.println(s);
    }
}
