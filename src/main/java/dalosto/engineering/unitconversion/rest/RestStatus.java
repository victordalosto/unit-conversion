package dalosto.engineering.unitconversion.rest;

public enum RestStatus {
    
    SUCCESS, ERROR, INFO;


    public static String possibleStatus() {
        StringBuilder sb = new StringBuilder();
        sb.append("{ ");
        for (int i=0; i<RestStatus.values().length; i++) {
            sb.append(RestStatus.values()[i].name());
            if (i < RestStatus.values().length-1) {
                sb.append(" | ");
            }
        }
        sb.append(" }");
        return sb.toString();
    }
}
