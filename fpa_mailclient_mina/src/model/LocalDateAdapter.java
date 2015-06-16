package model;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDateTime;

/**
 * Created by mina on 26.05.15.
 */

    public class LocalDateAdapter extends XmlAdapter<String, LocalDateTime> {

        @Override
        public LocalDateTime unmarshal(String v) throws Exception {
            return LocalDateTime.parse(v);
        }

        @Override
        public String marshal(LocalDateTime v) throws Exception {
            return v.toString();
        }
    }


