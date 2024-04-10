package com.management.conference.application.input.port;

import com.management.conference.domain.dto.ConferenceDo;
import org.springframework.http.codec.multipart.FilePart;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;


public interface ConferenceManager {
    ConferenceDo readData(String source, boolean isDispaly);

    List<String> schedule(int trackNo, ConferenceDo conference, int talkIndex);

    void displaySchedule();

    Mono<String> readTextFromFile(Flux<FilePart> fileParts);

}
