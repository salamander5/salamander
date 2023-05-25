import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.salamander.net.DefaultUrlConnectionClient;

import java.util.Arrays;
import java.util.List;

/**
 * Created by hsolano on 9/27/2016.
 */
public class GateKeeper {

    private final static Logger log = LoggerFactory.getLogger(GateKeeper.class);
    public static void main(String[] args) {

        //BasicConfigurator.configure();

        String url = "";

        if (args != null && args.length > 0) {
            List<String> options = Arrays.asList(args);

            url = options.get(0);

            if (options.contains("ssl")); {
                System.setProperty("javax.net.debug","ssl");
                log.debug("SSL debug enabled.");
            }
            if (options.contains("sni_false")) {
                System.getProperty("jsse.enableSNIExtension", "false");
                log.debug("SNI was disabled");
            } else {
                log.debug("SNI is enabled by default in Java 7 and 8");
            }
        }

        if (url == null || url.length() == 0) {
            log.debug("A URL is required!");
            System.exit(0);
        }

        try {
            DefaultUrlConnectionClient client = new DefaultUrlConnectionClient();
            client.connectToUrl(url);
            log.debug("Completed");
        } catch (Exception e) {
            log.error("error", e);
        }

    }

}
