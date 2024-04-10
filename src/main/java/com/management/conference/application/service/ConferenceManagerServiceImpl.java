package com.management.conference.application.service;

import com.management.conference.application.input.port.ConferenceManager;
import com.management.conference.domain.dto.ConferenceDo;
import com.management.conference.domain.dto.TalkDo;
import com.management.conference.domain.util.Constant;
import com.management.conference.domain.util.FileUtils;
import com.management.conference.infrastructure.output.adapter.TalkParser;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@RequiredArgsConstructor
public class ConferenceManagerServiceImpl implements ConferenceManager {
    private final List<String> scheduleTalks = new ArrayList<>();
    private final TalkParser parser;
    @Override
    public ConferenceDo readData(String filename, boolean idDispaly) {
        final var inputs = FileUtils.readFile(filename);
        if (idDispaly) {
            inputDisplay(inputs);
        }
        List<TalkDo> talks = parser.execute(inputs);
        talks.sort(Comparator.comparingInt(TalkDo::getDuration));
        // Calcular el tiempo total de la reunión
        Integer totalDuration = 0;
        for (TalkDo talk : talks) {
            totalDuration += talk.getDuration();
        }
        int trackCount;
        if (0 == totalDuration % Constant.DAY_DURATION) {
            trackCount = totalDuration / Constant.DAY_DURATION;
        } else {
            trackCount = totalDuration / Constant.DAY_DURATION + 1;
        }
        return ConferenceDo.builder().talks(talks).totalDuration(totalDuration).trackCount(trackCount).build();
    }

    private void inputDisplay(List<String> inputs) {
        System.out.println("Test input:");
        inputs.forEach(System.out::println);
        System.out.println();
    }

    @Override
    public List<String> schedule(int trackNo, ConferenceDo conference, int talkIndex) {
        scheduleTalks.add("Track:" + (trackNo + 1));
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.HOUR, 9);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.AM_PM, Calendar.AM);
        List<TalkDo> talks = conference.getTalks();
        String schedule;
        // Mañana
        talkIndex = subSchedule(talkIndex, Constant.MORNING_DURATION, cal, talks);

        // Periodo de almuerzo
        schedule = "12:00PM" + " " + "Lunch";
        scheduleTalks.add(schedule);
        cal.add(Calendar.MINUTE, 60);

        talkIndex++;
        // Tarde
        talkIndex = subSchedule(talkIndex, Constant.AFTERNOON_DURATION, cal, talks);
        // Reunion en linea
        schedule = "05:00PM" + " " + "Networking Event";
        scheduleTalks.add(schedule + "\n");

        return scheduleTalks;
    }

    private int subSchedule(int talkIndex, int duration, Calendar cal, List<TalkDo> talks) {
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm aa", Locale.ENGLISH);

        for (; talkIndex < talks.size(); talkIndex++) {
            int currDuration = talks.get(talkIndex).getDuration();
            if (duration >= currDuration) {
                duration = duration - currDuration;

                String schedule = sdf.format(cal.getTime()).replaceAll(" ", "") + " "
                        + talks.get(talkIndex).getTitle() + " " + currDuration + "min";
                scheduleTalks.add(schedule);
                cal.add(Calendar.MINUTE, currDuration);
            }
            if (duration < currDuration) {
                break;
            }


            if (duration > 0) {
                continue;
            }

            break;
        }

        return talkIndex;
    }

    @Override
    public void displaySchedule() {
        System.out.println("Test output:");
        scheduleTalks.forEach(System.out::println);
    }

    @Override
    public Mono<String> readTextFromFile(Flux<FilePart> fileParts) {
        return fileParts
                .flatMap(filePart -> filePart.content().map(dataBuffer -> {
                    byte[] bytes = new byte[dataBuffer.readableByteCount()];
                    dataBuffer.read(bytes);
                    DataBufferUtils.release(dataBuffer);
                    return new String(bytes, StandardCharsets.UTF_8);
                }))
                .reduce(String::concat)
                .doOnError(Throwable::printStackTrace);
    }
}
