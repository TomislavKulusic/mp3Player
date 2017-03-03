/**
 * Created by Nikola on 3.3.2017..
 */
public interface Command {


    public void pause(PlayList pl);
    public void resume(PlayList pl);
    public void playNext(PlayList pl);
    public void playPrevious(PlayList pl);
    public void quit(PlayList pl);
    public void replay(PlayList pl);
    public void printPlaylist(PlayList pl);
    public void printFileInformation(PlayList pl);
    public void getHelp();
    public void playSpecific(String s, PlayList pl);
    public void playListSize(PlayList pl);
    public void printTime(PlayList pl);
}
