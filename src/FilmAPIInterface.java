import java.time.LocalDate;
import java.util.List;
import java.time.LocalDateTime;
public interface FilmAPIInterface {
    List<String> getAvailableFilms();
    boolean addFilmBooking(int filmId, LocalDateTime dateTime, int seats);
    boolean updateFilmBooking(int filmId, LocalDateTime newDate,int newSeats);
    boolean removeFilmBooking(int filmId);
    boolean getFilmsScheduledOn(LocalDate date);
}
