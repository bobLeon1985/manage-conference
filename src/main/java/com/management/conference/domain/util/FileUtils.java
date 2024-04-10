package com.management.conference.domain.util;

import com.management.conference.domain.exception.FileException;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class FileUtils {
    public static List<String> readFile(String filename) {
        List<String> list = new ArrayList<>();
        InputStream inputStream = FileUtils.class.getClassLoader().getResourceAsStream(filename);
        if (null == inputStream) {
            String msg = "File not found：" + filename;
            if (log.isErrorEnabled()) {
                log.error("File not found：" + filename);
            }
            throw new FileException(msg);
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        String lineStr;
        try {
            while (null != (lineStr = br.readLine())) {
                list.add(lineStr);
            }
        } catch (IOException e) {
            if (log.isErrorEnabled()) {
                log.error("File reading failure");
            }
            e.printStackTrace();
        }
        return list;
    }


}
