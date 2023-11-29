package archi.clientqueries;

public enum QueryType {
    NONE {
                @Override
                public String toString() {
                    return "/";
                }
            },
    SELECT {
        @Override
        public String toString() {
            return "SELECT";
        }
    },
    ADD {
        @Override
        public String toString() {
            return "ADD";
        }
    },
    DELETE {
        @Override
        public String toString() {
            return "DELETE";
        }
    }
}
