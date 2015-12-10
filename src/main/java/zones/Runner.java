package zones;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import sun.util.calendar.ZoneInfo;

import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.TimeZone;


@Component
@Slf4j
public class Runner implements ApplicationRunner {

    @Autowired
    private TestEntityRepository repository;

    @Override
    public void run(ApplicationArguments args) {
        log.info("");
        String stringDate = "2015-01-01T18:00:00+01:00[Europe/Berlin]";
        Date date = Date.from(ZonedDateTime.parse(stringDate).toInstant());
        log.info("Date    {} has {} millis", stringDate, date.getTime());
        log.info("");

        log.info("Current default time zone: {}", TimeZone.getDefault().getID());
        TimeZone berlinTZ = TimeZone.getTimeZone("Europe/Berlin");
        log.info("Setting default time zone to {}", berlinTZ.getID());
        ZoneInfo.setDefault(berlinTZ);
        log.info("Current default time zone: {}", TimeZone.getDefault().getID());
        log.info("");

        TestEntity e = new TestEntity();
        e.setDate(date);
        e.setDatetz(date);

        e = repository.save(e);

        TimeZone laTZ = TimeZone.getTimeZone("America/Los_Angeles");
        log.info("Setting default time zone to {}", laTZ.getID());
        ZoneInfo.setDefault(laTZ);
        log.info("Current default time zone: {}", TimeZone.getDefault().getID());
        log.info("");

        e = repository.findOne(e.getId());

        log.info("Setting default time zone to {}", berlinTZ.getID());
        ZoneInfo.setDefault(berlinTZ);
        log.info("Current default time zone: {}", TimeZone.getDefault().getID());
        log.info("");

        SimpleDateFormat f = new SimpleDateFormat("YYYY-MM-dd'T'HH:mm:ssZz");
        log.info("Date    {} with {} millis", f.format(e.getDate()), e.getDate().getTime());
        log.info("DateTZ  {} with {} millis", f.format(e.getDatetz()), e.getDatetz().getTime());
        log.info("");
    }
}
