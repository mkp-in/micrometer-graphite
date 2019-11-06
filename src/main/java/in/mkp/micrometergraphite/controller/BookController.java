package in.mkp.micrometergraphite.controller;

import java.time.Duration;
import java.util.Random;

import in.mkp.micrometergraphite.model.BookRepository;
import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    private final Counter counts;

    private final Random rand = new Random();

    private final Timer timer;

    private final MeterRegistry meterRegistry;

    @Autowired
    public BookController(final MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
        this.counts = Counter.builder(BookController.class.getName() + ".countcalls").register(meterRegistry);
        this.timer = Timer.builder(BookController.class.getName() + ".times")
                .publishPercentiles(0.5, 0.95, 0.99) // median and 95th percentile
                .sla(Duration.ofMillis(100))
                .register(meterRegistry);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @Timed("com.amobee.books.getAllBooks")
    public Iterable<String> getAllBooks() {
        counts.increment();
        return bookRepository.getAll();
    }

    @RequestMapping(value = "/isbn/{isbn}", method =
            RequestMethod.GET)
    public String getBook(@PathVariable String isbn) {
        counts.increment();
        timer.record(() -> {
            try {
                int randomNum = rand.nextInt((10 - 1) + 1) + 1;
                System.out.println("randomNum = " + randomNum);
                Thread.sleep(randomNum*randomNum);
                //TimeUnit.MILLISECONDS.sleep(1500);
            } catch (InterruptedException ignored) { }
        });
        return bookRepository.findBookByIsbn(isbn);
    }
}
