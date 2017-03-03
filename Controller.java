/**
 * Created by Nikola on 3.3.2017..
 */
public class Controller {
    Command cmd = null;

    public Controller(Action a){
        this.cmd = a;
    }

    public void doStuff(PlayList pl, String s){
        char c = s.charAt(0);

        switch (c) {
            case '+':
                cmd.playNext(pl);
                break;
            case '-':
                cmd.playPrevious(pl);
                break;
            case '@':
                cmd.replay(pl);
                break;
            case 'h':
            case 'H':
            case '?':
                cmd.getHelp();
                break;
            case 'i': {
                cmd.printFileInformation(pl);
                break;
            }
            case 'p': {
                cmd.playSpecific(s, pl);
                break;
            }
            case 'P':
                cmd.pause(pl);
                break;
            case 'R':
                cmd.resume(pl);
                break;
            case 's':
                cmd.printPlaylist(pl);
                break;
            case 't':
                cmd.printTime(pl);
                break;
        }
    }

}
