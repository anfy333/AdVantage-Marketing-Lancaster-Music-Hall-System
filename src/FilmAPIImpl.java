import java.time.LocalDate;
import java.util.List;
import java.time.LocalDateTime;

public class FilmAPIImpl implements FilmAPIInterface {
    private FilmDatabase filmDatabase;
    //private BookingSystem bookingSystem;

    public FilmAPIImpl(FilmDatabase filmDatabase, BookingSystem bookingSystem) {
        this.filmDatabase = filmDatabase;
        //this.bookingSystem = bookingSystem;
    }
    @Override
    public List<String> getAvailableFilms() {
        return filmDatabase.getFilms();
    }

    @Override
    public boolean addFilmBooking(int filmId, LocalDateTime dateTime, int seats) {
        return true;
    }

    @Override
    public boolean updateFilmBooking(int filmId, LocalDateTime newDate,int newSeats) {
        return true;
    }

    @Override
    public boolean removeFilmBooking(int filmId) {
        return true;
    }
    @Override
    public boolean getFilmsScheduledOn(LocalDate date) {
        return true;
    }
}
