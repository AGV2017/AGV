package happyfood.vn.kaak.myapplication.Model;

import android.graphics.Bitmap;

/**
 * Created by MyPC on 02/10/2017.
 */

public class Friend {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLinkImage() {
        return linkImage;
    }

    public void setLinkImage(String linkImage) {
        this.linkImage = linkImage;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    String name;
    String linkImage;
    Bitmap image;
}
