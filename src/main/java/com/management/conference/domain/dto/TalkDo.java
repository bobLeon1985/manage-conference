package com.management.conference.domain.dto;

import com.management.conference.domain.util.Constant;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TalkDo {
    String title;
    Integer duration;
    Date startTime;
    Date endTime;

    @Override
    public String toString() {
        return title + " " + (duration == 5 ? Constant.LIGHTNING : duration + Constant.MIN);
    }
}
