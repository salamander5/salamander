package org.salamander.net;


import org.apache.log4j.Logger;

import javax.crypto.Cipher;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * This class supports some utilities to diagnostic of available encryption and
 * HTTP(S) connections.
 */
public class DefaultUrlConnectionClient {

    /**
     * Use to log data to console.
     */
    private final static Logger log = Logger.getLogger(DefaultUrlConnectionClient.class);

    public void connectToUrl(String url) throws Exception {

        int maxKeyLen = Cipher.getMaxAllowedKeyLength("AES");
        log.debug("Unlimited strength encryption available? " + (maxKeyLen == Integer.MAX_VALUE));
        log.debug("Max Length of key supported: " + maxKeyLen);

        HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("User-Agent", "Mozilla/5.0");
        int respCode = conn.getResponseCode();
        log.debug("Response code: " + respCode);
        String contentType = conn.getContentType();
        log.debug("Content Type: " + contentType);

    }
}
