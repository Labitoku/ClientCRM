package archi.clientqueries;

public enum QueryType {
    NONE {
                @Override
                public String toString() {
                    return "/";
                }
            },
    SELECT_ALL {
        @Override
        public String toString() {
            return "allUsers";
        }
    },
    SELECT_BY_REVENUE {
        @Override
        public String toString() {
            return "allUsers";
        }
    },
    SELECT_BY_DATE {
        @Override
        public String toString() {
            return "allUsers";
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
