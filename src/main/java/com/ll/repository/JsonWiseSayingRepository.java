package com.ll.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.ll.domain.WiseSaying;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


// 파일 메서드 공부하기.. 거의 기억이 안남
public class JsonWiseSayingRepository implements WiseSayingRepository {

    private static final Path BASE_PATH = Paths.get("").toAbsolutePath().resolve("db").resolve("wiseSaying");
    private static final Path LAST_ID_FILE = BASE_PATH.resolve("lastId.txt");
    private static final Path DATA_FILE = BASE_PATH.resolve("data.json");

    private final ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
    private long uniqueNum = 1;

    public JsonWiseSayingRepository() {
        try {
            File lastIdFile = LAST_ID_FILE.toFile();

            // BASE_PATH 디렉토리가 없으면 생성
            Files.createDirectories(BASE_PATH);

            if (lastIdFile.exists()) {
                // lastId.json 파일이 존재하면 값을 읽어옴
                Long lastId = objectMapper.readValue(lastIdFile, Long.class);
                uniqueNum = lastId + 1;
            }
        } catch (IOException e) {
            System.err.println("Failed to initialize uniqueNum: " + e.getMessage());
            // 에러가 발생해도 기본값 1을 사용
            uniqueNum = 1;
        }
    }

    @Override
    public long addWiseSaying(WiseSaying quote) {
        quote.setId(uniqueNum);

        try{
            File directory = BASE_PATH.toFile();
            if (!directory.exists()) {
                directory.mkdirs();
            }

            Path quoteFile = BASE_PATH.resolve(quote.getId() + ".json");
            objectMapper.writeValue(quoteFile.toFile(), quote);

            Files.writeString(LAST_ID_FILE, String.valueOf(quote.getId()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return uniqueNum++;
    }

    @Override
    public void updateWiseSaying(WiseSaying quote) {
        Path wiseSayingFile = BASE_PATH.resolve( quote.getId() + ".json");
        if(!wiseSayingFile.toFile().exists()){
            return;
        }

        try {
            objectMapper.writeValue(wiseSayingFile.toFile(), quote);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Optional<WiseSaying> searchById(long id) {
        return Optional.ofNullable(loadWiseSaying(id));
    }

    @Override
    public List<WiseSaying> findAll() {

        List<WiseSaying> ququoteList = new ArrayList<>();
        File directory = BASE_PATH.toFile();

        File[] jsonFiles = directory.listFiles((dir, name) ->
                name.endsWith(".json") && !name.equals("lastId.txt"));

        if (jsonFiles != null) {
            for (File file : jsonFiles) {
                try {
                    WiseSaying quote = objectMapper.readValue(file, WiseSaying.class);
                    ququoteList.add(quote);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return ququoteList;
    }

    @Override
    public boolean removeWiseSaying(long quoteId) {

        try {
            Path quoteFile = BASE_PATH.resolve(quoteId + ".json");
            File file = quoteFile.toFile();

            if (!file.exists()) {
                return false;  // 파일 없음
            }
            boolean deleted = Files.deleteIfExists(quoteFile);
            return deleted;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private WiseSaying loadWiseSaying(long id) {
        try {
            Path quoteFile = BASE_PATH.resolve(id + ".json");

            if (!quoteFile.toFile().exists()) {
                return null;
            }

            return objectMapper.readValue(quoteFile.toFile(), WiseSaying.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void buildWiseSaying(){

    }
}