package com.mjc.school.repository.implementation.utils;

import com.mjc.school.repository.implementation.CreateData;
import com.mjc.school.repository.implementation.ModelAuthor;
import com.mjc.school.repository.implementation.ModelNews;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DataSource {
    private static DataSource dataSource;
    private static final String PATH_OF_AUTHORS = "/Users/DimaHeinz/Desktop/test/stage3-module1-task/module-repository/src/main/resources/author.txt";
    private static final String PATH_OF_CONTENT = "/Users/DimaHeinz/Desktop/test/stage3-module1-task/module-repository/src/main/resources/content.txt";
    private static final String PATH_OF_TITLE = "/Users/DimaHeinz/Desktop/test/stage3-module1-task/module-repository/src/main/resources/title.txt";
    private List<ModelNews> modelNewsList;
    private List<ModelAuthor> modelAuthorList;
    private Random random = new Random();
    private CreateData createData = CreateData.getInstance();

    public static DataSource getData() {
        if (dataSource == null) {
            dataSource = new DataSource();
            dataSource.initialize();
        }
        return dataSource;
    }

    private void initialize() {
        initializeAuthors();
        modelNewsList = new ArrayList<>();
        String title;
        String content;

        for (int i = 1; i <= 20; i++) {
            int r = random.nextInt(modelAuthorList.size());
            LocalDateTime date = CreateData.getInstance().getDateTime();
            title = createData.getReadedData(PATH_OF_TITLE);
            content = createData.getReadedData(PATH_OF_CONTENT);
            modelNewsList.add(new ModelNews(i, title, content, date, date, modelAuthorList.get(r).getId()));
        }
    }

    private void initializeAuthors() {
        modelAuthorList = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            modelAuthorList.add(new ModelAuthor(i, createData.getReadedData(PATH_OF_AUTHORS)));
        }
    }
    public List<ModelNews> getNewsList() {
        return modelNewsList;
    }

    public List<ModelAuthor> getAuthorsList() {
        return modelAuthorList;
    }
}
