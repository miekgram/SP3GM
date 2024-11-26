import java.util.ArrayList;
public abstract class Media {

    String title;
    String category;
    float rating;

    public Media(String title, String category, float rating) {
        this.title = title;
        this.category = category;
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public float getRating() {
        return rating;
    }


    public void create(String[] data) {
    }




    public void addToWatched() {
        //this.haveWatched.add(media);

    }

    public void addToSaved() {
        //this.savedForLater.add(media);

    }

    public void deleteFromSaved() {

    }
}

