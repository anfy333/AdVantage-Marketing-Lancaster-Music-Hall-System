import java.util.List;
public class FilmDatabaseImpl implements FilmDatabase{
    List<String> Films;

    @Override
    public List<String> getFilms() {
        return Films;
    }
}
