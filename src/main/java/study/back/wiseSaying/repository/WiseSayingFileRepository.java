package study.back.wiseSaying.repository;

import study.back.standard.util.Util;
import study.back.wiseSaying.entity.WiseSaying;

import java.util.Map;
import java.util.Optional;

public class WiseSayingFileRepository {

    public WiseSaying save(WiseSaying wiseSaying) {

        if (wiseSaying.isNew()) {

            increaseLastId();
            int lastId  = getLastId();

            wiseSaying.setId(lastId);
            Map<String, Object> wiseSayingMap = wiseSaying.toMap();
            String jsonStr = Util.json.toString(wiseSayingMap);
            Util.file.set("db/wiseSaying/%d.json".formatted(wiseSaying.getId()), jsonStr);

        }
        return wiseSaying;
    }

    private int getLastId() {
        return Util.file.getAsInt("%slastId.txt".formatted(getDbPath()),0);
    }

    private void increaseLastId() {
        Util.file.set("%slastId.txt".formatted(getDbPath()), String.valueOf(getLastId() + 1));
    }

    public Optional<WiseSaying> findById(int id) {
        String jsonStr = Util.file.get("%s%d.json".formatted(getDbPath(),id), "");
        if( jsonStr.isBlank()) {
            return Optional.empty();
        }

        Map<String, Object> map = Util.json.toMap(jsonStr);
        WiseSaying ws = WiseSaying.fromMap(map);
        return Optional.of(ws);
    }

    public void clear() {
        Util.file.delete(getDbPath());
    }

    public String getDbPath(){
        return "db/wiseSaying/";
    }
}
