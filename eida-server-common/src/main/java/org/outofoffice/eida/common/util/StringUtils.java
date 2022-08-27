package org.outofoffice.eida.common.util;

import java.nio.file.FileSystems;
import java.util.Arrays;

import static java.util.stream.Collectors.joining;


public class StringUtils {

    public static String combinePath(String directoryPath, String fileName) {
        return directoryPath + FileSystems.getDefault().getSeparator() + fileName;
    }

    public static String combineComma(Object... objs) {
        return Arrays.stream(objs)
            .map(Object::toString)
            .collect(joining(","));
    }

}
