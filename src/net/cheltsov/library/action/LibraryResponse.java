package net.cheltsov.library.action;

import java.util.EnumMap;

public class LibraryResponse {

    private EnumMap<ResponseParameter, Object> parameters = new EnumMap(ResponseParameter.class);

    public Object getParameter(ResponseParameter parameter) {
        return parameters.get(parameter);
    }

    public void setParameter(ResponseParameter parameter, Object value) {
        parameters.put(parameter, value);
    }


}
