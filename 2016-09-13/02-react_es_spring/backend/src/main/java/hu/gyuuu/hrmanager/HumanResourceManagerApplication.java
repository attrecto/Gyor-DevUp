package hu.gyuuu.hrmanager;

import java.util.TimeZone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HumanResourceManagerApplication {

    public static void main( String[] args )
    {
        TimeZone.setDefault(TimeZone.getTimeZone(Const.TIMEZONE));
        SpringApplication.run( HumanResourceManagerApplication.class, args );
    }
}
