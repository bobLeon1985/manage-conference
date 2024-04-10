package com.management.conference.infrastructure.output.adapter;

import com.management.conference.domain.dto.TalkDo;

import com.management.conference.domain.enums.TalkTypeEnum;
import com.management.conference.domain.exception.FileException;
import com.management.conference.domain.util.StringUtils;
import com.management.conference.application.output.port.Parser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Slf4j
@Service
public class TalkParser implements Parser<TalkDo, List<String>> {
    private static final Pattern INPUT_PATTERN = Pattern.compile("^(.+)\\s(\\d+)?\\s?((min)|(lightning))$");
    @Override
    public List<TalkDo> execute(List<String> dataList) {
        List<TalkDo> talks = new ArrayList<>();
        dataList.forEach(lineStr ->
                {
                    final var match = INPUT_PATTERN.matcher(lineStr);
                    if (!match.find()) {
                        if (log.isErrorEnabled()) {
                            log.error("【{}】Formato incorrecto", lineStr);
                        }
                        throw new FileException("【" + lineStr + "】Formato incorrecto");
                    }
                    final var title = match.group(1);
                    final var talkType = match.group(3);
                    final TalkTypeEnum talkTypeEnum;
                    if (TalkTypeEnum.MINUTES.getName().equalsIgnoreCase(talkType)) {
                        talkTypeEnum = TalkTypeEnum.MINUTES;
                    } else {
                        talkTypeEnum = TalkTypeEnum.LIGHTNING;
                    }
                    final var durationStr = match.group(2);
                    int duration = StringUtils.isEmpty(durationStr) ? 1 : Integer.parseInt(durationStr);
                    duration = duration * talkTypeEnum.getValue();
                    talks.add(TalkDo.builder().title(title).duration(duration).build());
                }
        );
        return talks;
    }
}
