package com.management.conference.application.service;

import com.management.conference.domain.dto.TalkDo;
import com.management.conference.infrastructure.output.adapter.TalkParser;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.test.StepVerifier;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class ConferenceManagerServiceImplTest {
    @MockBean
    private TalkParser parser;

    @InjectMocks
    @Autowired
    private ConferenceManagerServiceImpl conferenceManagerService;


    @Test
    void readDataTest() {
        var listaTalks = List.of(TalkDo.builder().title("Rails for Python Developers").duration(5).build());
        when(parser.execute(anyList())).thenReturn(listaTalks);
    }

}