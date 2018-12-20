package invalue.core.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author liemtpt
 */
public class NameDocument {

    private String name;
    private String domain="face";

    public enum FIELDS {
        code("name", "name");

        private final String name;
        private final String value;
        private static Map<String, FIELDS> values = null;

        public String getValue() {
            return value;
        }

        public String getName() {
            return name;
        }

        /**
         * Instantiates a new gender type.
         *
         * @param value the value
         */
        FIELDS(String name, String value) {
            this.name = name;
            this.value = value;
        }

        /**
         * Parses the value.
         *
         * @param value the value
         * @return the gender type
         */
        public static FIELDS parseValue(String value) {
            if (values == null) {
                values = new HashMap<>(FIELDS.values().length);
                for (FIELDS e : FIELDS.values()) {
                    values.put(e.getName(), e);
                }
            }
            return values.get(value);
        }
    }

    public NameDocument() {

    }

    public static NameDocument toDocument(IndexInput value) {
        NameDocument doc = new NameDocument();
        Map<Integer, NameDocument> mapContractDoc = new HashMap();
        if (value != null) {
            doc.setName(value.getName());
        }
        return doc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }
    
}
