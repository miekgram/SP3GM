import java.util.ArrayList;

public abstract class Media {
        String title;
        ArrayList<String> categories;
        float rating;



        public Media (String title, ArrayList<String> categories, float rating) {
            this.title = title;
            this.categories = categories;
            this.rating = rating;
        }

        public void create(){

        }

        public void addToWatched(){

        }

        public void addToSaved(){

        }

        public void deleteFromSaved(){

        }



}
