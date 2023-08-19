package com.mjc.school.repository.implementation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DataSource {
    private static DataSource dataSource;
    private static final String PATH_OF_AUTHORS = "/Users/DimaHeinz/Desktop/test/stage3-module1-task/module-repository/src/main/resources/author.txt";
    private static final String PATH_OF_CONTENT = "/Users/DimaHeinz/Desktop/test/stage3-module1-task/module-repository/src/main/resources/content.txt";
    private static final String PATH_OF_TITLE = "/Users/DimaHeinz/Desktop/test/stage3-module1-task/module-repository/src/main/resources/title.txt";
    private List<News> newsList;
    private List<Author> authorList;
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
        newsList = new ArrayList<>();
        String title;
        String content;

        for (int i = 1; i <= 20; i++) {
            int r = random.nextInt(authorList.size());
            LocalDateTime date = CreateData.getInstance().getDateTime();
            title = createData.getReadedData(PATH_OF_TITLE);
            content = createData.getReadedData(PATH_OF_CONTENT);
            newsList.add(new News(i, title, content, date, date, authorList.get(r).getId()));
        }
    }

    private void initializeAuthors() {
        authorList = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            authorList.add(new Author(i, createData.getReadedData(PATH_OF_AUTHORS)));
        }
    }
    public List<News> getNewsList() {
        return newsList;
    }

    public List<Author> getAuthorsList() {
        return authorList;
    }
}
