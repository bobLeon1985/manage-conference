package com.management.conference.domain.util;

import com.management.conference.domain.dto.TalkDo;

import java.util.Comparator;

public class TalkComparator implements Comparator<TalkDo> {

    @Override
    public int compare(TalkDo talk1, TalkDo talk2) {
        if (talk1.getDuration() < talk2.getDuration()) {
            return 1;
        } else {
            return -1;
        }
    }
}
