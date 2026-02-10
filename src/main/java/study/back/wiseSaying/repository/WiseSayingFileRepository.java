package study.back.wiseSaying.repository;

import study.back.standard.util.Util;
import study.back.wiseSaying.dto.PageDto;
import study.back.wiseSaying.entity.WiseSaying;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class WiseSayingFileRepository {

    public WiseSaying save(WiseSaying wiseSaying) {
        //메모리에서는 set으로도 이미 업데이트 된거나 마찬가지이지만 file이라서 저장해야함
        if (wiseSaying.isNew()) {

            increaseLastId();
            int lastId = getLastId();

            wiseSaying.setId(lastId);
            Map<String, Object> wiseSayingMap = wiseSaying.toMap();
            String jsonStr = Util.json.toString(wiseSayingMap);
            Util.file.set("db/wiseSaying/%d.json".formatted(wiseSaying.getId()), jsonStr);

            return wiseSaying;
        }

        String jsonStr = Util.json.toString(wiseSaying.toMap());
        Util.file.set("%s/%d.json".formatted(getDbPath(), wiseSaying.getId()), jsonStr);

        return wiseSaying;
    }

    private int getLastId() {
        return Util.file.getAsInt("%slastId.txt".formatted(getDbPath()), 0);
    }

    private void increaseLastId() {
        Util.file.set("%slastId.txt".formatted(getDbPath()), String.valueOf(getLastId() + 1));
    }

    public Optional<WiseSaying> findById(int id) {
        String jsonStr = Util.file.get("%s%d.json".formatted(getDbPath(), id), "");
        if (jsonStr.isBlank()) {
            return Optional.empty();
        }

        Map<String, Object> map = Util.json.toMap(jsonStr);
        WiseSaying ws = WiseSaying.fromMap(map);
        return Optional.of(ws);
    }

    public void clear() {
        Util.file.delete(getDbPath());
    }

    public String getDbPath() {
        return "db/wiseSaying/";
    }

    public void delete(WiseSaying wiseSaying1) {
        Util.file.delete("%s/%d.json".formatted(getDbPath(), wiseSaying1.getId()));
    }

    public List<WiseSaying> findAll() {
        return Util.file.walkRegularFiles(getDbPath(), "^\\d+\\.json$")
                .map(path -> Util.file.get(path.toString(), ""))
                .map(Util.json::toMap) //json을 java map으로
                .map(WiseSaying::fromMap)// map을 받아서 wiseSaying으로
                .toList();

    }

    private PageDto pageOf(List<WiseSaying> filteredContent, int page, int pageSize) {
        int totalCount = filteredContent.size();

        List<WiseSaying> pagedFilteredContent = filteredContent
                .stream()
                .skip((long) (page - 1) * pageSize)
                .limit(pageSize)
                .toList();

        return new PageDto(page, pageSize, totalCount, pagedFilteredContent);
    }

    public PageDto findByContentContainingDesc(String kw, int page, int pageSize) {
        List<WiseSaying> filteredContent = findAll().reversed()
                .stream()
                .filter(w -> w.getSaying().contains(kw))
                .toList();
        return pageOf(filteredContent, page, pageSize);
    }

    public PageDto findByAuthorContainingDesc(String kw, int page, int pageSize) {
        List<WiseSaying> filteredContent = findAll().reversed().stream()
                .filter(w -> w.getAuthor().contains(kw))
                .toList();

        return pageOf(filteredContent, page, pageSize);
    }
}
