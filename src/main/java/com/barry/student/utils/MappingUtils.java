package com.barry.student.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.Objects;

@Slf4j
public final class MappingUtils {
    private MappingUtils() {}

    public static void checkIfValidateId(Long id){
        if(Objects.isNull(id)){
            log.error("Invalid id: it should not be empty");
            throw new IllegalArgumentException("Invalid id: it should not be Empty");
        }
    }

    public static String writeJson(Serializable obj) {
        ObjectMapper mapper = new ObjectMapper();
        String json=  "";
        try {
            json += " "+mapper.writeValueAsString(obj);
        }catch (JsonProcessingException e){
            log.trace("object is not json compatible",e);
        }
        return json;

    }
}
