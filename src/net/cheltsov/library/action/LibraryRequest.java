package net.cheltsov.library.action;

import java.util.EnumMap;

public class LibraryRequest {

    private EnumMap<RequestParameter, Object> parameters = new EnumMap(RequestParameter.class);

    public LibraryRequest() {
        parameters.put(RequestParameter.IS_REVERS, false);
    }

    public Object getParameter(RequestParameter parameter) {
        return parameters.get(parameter);
    }

    public void setParameter(RequestParameter parameter, Object value) {
        parameters.put(parameter, value);
    }
}
