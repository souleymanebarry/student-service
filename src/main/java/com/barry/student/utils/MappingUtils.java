package com.barry.student.utils;


import lombok.extern.slf4j.Slf4j;

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
}
