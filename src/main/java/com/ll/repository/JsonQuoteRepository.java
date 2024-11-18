package com.ll.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.ll.domain.Quote;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


// 파일 메서드 공부하기.. 거의 기억이 안남
public class JsonQuoteRepository implements QuoteRepository{

    private static final Path BASE_PATH = Paths.get("").toAbsolutePath()
            .resolve("db").resolve("wiseSaying");

    private static final Path LAST_ID_FILE = BASE_PATH.resolve("lastId.txt");
    private long uniqueNum = 1;

    private final ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

    public JsonQuoteRepository() {
        System.out.println(BASE_PATH);
    }

    @Override
    public long addQuote(Quote quote) {
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
    public void updateQuote(Quote quote) {
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
    public Optional<Quote> searchById(long id) {
        return Optional.ofNullable(loadQuote(id));
    }

    @Override
    public List<Quote> findAll() {

        List<Quote> ququoteList = new ArrayList<>();
        File directory = BASE_PATH.toFile();

        File[] jsonFiles = directory.listFiles((dir, name) ->
                name.endsWith(".json") && !name.equals("lastId.txt"));

        if (jsonFiles != null) {
            for (File file : jsonFiles) {
                try {
                    Quote quote = objectMapper.readValue(file, Quote.class);
                    ququoteList.add(quote);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return ququoteList;
    }

    @Override
    public boolean removeQuote(long quoteId) {

        try {
            Path quoteFile = BASE_PATH.resolve(quoteId + ".json");
            File file = quoteFile.toFile();

            if (!file.exists()) {
                return false;  // 파일 없음
            }
            boolean deleted = Files.deleteIfExists(quoteFile);

            // TODO : 마지막 index에 대한 파일 갱신도 필요할 듯

            return deleted;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private Quote loadQuote(long id) {
        try {
            Path quoteFile = BASE_PATH.resolve(id + ".json");

            if (!quoteFile.toFile().exists()) {
                return null;
            }

            return objectMapper.readValue(quoteFile.toFile(), Quote.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
