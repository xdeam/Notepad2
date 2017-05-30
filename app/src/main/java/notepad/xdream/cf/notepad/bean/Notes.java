package notepad.xdream.cf.notepad.bean;
import org.litepal.crud.DataSupport;

import java.util.Date;

/**
 * Created by kwinter on 2017/5/26.
 *
 */
public class Notes extends DataSupport {

    private int id;
    private Date date;
    private String content;
    private String  music;
    private String  pictue;
    private String style;
    private int type;




    public Notes() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMusic() {
        return music;
    }

    public void setMusic(String music) {
        this.music = music;
    }

    public String getPictue() {
        return pictue;
    }

    public void setPictue(String pictue) {
        this.pictue = pictue;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
