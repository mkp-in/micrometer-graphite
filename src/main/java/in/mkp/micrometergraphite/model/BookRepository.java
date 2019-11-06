package in.mkp.micrometergraphite.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class BookRepository {

    public Iterable<String> getAll() {
        List<String> list = new ArrayList<>();
        list.add("Book1");
        list.add("Book2");
        return list;
    }

    public String findBookByIsbn(String isbn) {
        return isbn + "book";
    }
}
