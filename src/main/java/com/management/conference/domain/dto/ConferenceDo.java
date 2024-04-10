package com.management.conference.domain.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ConferenceDo {
    List<TalkDo> talks;
    Integer totalDuration;
    Integer trackCount;

     @Override
     public String toString() {
          return "Conference{" +
                  "talks=" + talks +
                  ", totalDuration=" + totalDuration +
                  ", trackCount=" + trackCount +
                  '}';
     }
}
