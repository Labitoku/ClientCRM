package archi.clientqueries;

public enum TypeCRM {
    INTERNAL
            {
                @Override
                public String toString() {
                    return "http://localhost:8080/";
                }
            },
    SALESFORCE
            {
                @Override
                public String toString() {
                    return "http://localhost:8081/";
                }
            },
    VIRTUAL
            {
                @Override
                public String toString() {
                    return "http://localhost:8080/";
                }
            };
}
