package com.management.conference.infrastructure.input.adapter.rest.impl;

import com.management.conference.application.input.port.ConferenceManager;
import com.management.conference.domain.dto.ConferenceDo;
import com.management.conference.domain.util.Constant;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api")
public class ConferenceManagerController {

    private final ConferenceManager manager;

    @PostMapping(value = "/read-file")
    @ResponseStatus(HttpStatus.OK)
    public Mono<String> readFile(@RequestBody Flux<FilePart> fileParts) {
        return manager.readTextFromFile(fileParts);
    }

    @GetMapping("/process")
    Mono<ResponseEntity<List<String>>> getProcess(String start) {
        ConferenceDo conference = manager.readData(Constant.INPUT_FILE_NAME, true);
        List<String> ooutput = new ArrayList<>();
        int talkIndex = 0;
        for (int trackNo = 0; trackNo < conference.getTrackCount(); trackNo++) {
            ooutput = manager.schedule(trackNo, conference, talkIndex);
        }
        manager.displaySchedule();
        return Mono.just(ResponseEntity.ok().body(ooutput));
    }
}
