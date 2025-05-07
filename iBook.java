import java.io.Serializable;

public interface IBook extends Serializable {
    public String getTitle();
    public String getAuthor();
    public String getISBN();
    public String getHomeLibrary();
}


public class Book implements IBook {
    private String title;
    private String author;
    private String isbn;
    private String homeLibrary;

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getAuthor() {
        return author;
    }

    @Override
    public String getISBN() {
        return isbn;
    }

    @Override
    public String getHomeLibrary() {
        return homeLibrary;
    }
}