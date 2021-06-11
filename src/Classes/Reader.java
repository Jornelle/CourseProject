package Classes;

import java.time.LocalDate;

public class Reader {
    public int getUserPass() {
        return userPass;
    }

    public void setUserPass(int userPass) throws Exception {
        if(userPass < 100000 || userPass > 999999){
            throw new Exception("Не верное значение паспорта");
        }
        this.userPass = userPass;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public LocalDate getReceiving() {
        return receiving;
    }

    public void setReceiving(LocalDate receiving) {
        this.receiving = receiving;
    }

    public LocalDate getLease() {
        return lease;
    }

    public void setLease(LocalDate lease) {
        this.lease = lease;
    }

    public boolean isOverdue() {
        return overdue;
    }

    public void setOverdue(boolean overdue) {
        this.overdue = overdue;
    }

    private int userPass;
    private int bookId;
    private LocalDate receiving;
    private LocalDate lease;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private boolean overdue;
    private int id;

    public Reader(int id, int userPass, int bookId, LocalDate receiving, LocalDate lease, boolean overdue) throws Exception {

        if(userPass < 100000 || userPass > 999999){
            throw new Exception("Не верное значение паспорта");
        }
        this.id = id;
        this.userPass = userPass;
        this.bookId = bookId;
        this.receiving = receiving;
        this.lease = lease;
        this.overdue = overdue;
    }

    public Reader() {
        this.id = 0;
        this.userPass = 0;
        this.bookId = 0;
        this.receiving = LocalDate.now();
        this.lease = LocalDate.now();
        this.overdue = false;
    }
}
