public class bookingdetails {
    public int id;
    public int stdId;
    public int bookId;
    public String bookName;
    public String authorName;
    public int qty;
    public int ISBN;

    public int getId() {
        return id;
    }

    public int getStdId() {
        return stdId;
    }

    public int getBookId() {
        return bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public int getQty() {
        return qty;
    }

    public int getISBN() {
        return ISBN;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setStdId(int stdId) {
        this.stdId = stdId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public void setISBN(int ISBN) {
        this.ISBN = ISBN;
    }
}
