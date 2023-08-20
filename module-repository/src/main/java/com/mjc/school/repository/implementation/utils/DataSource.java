package com.mjc.school.repository.implementation.utils;

import com.mjc.school.repository.implementation.AuthorModel;
import com.mjc.school.repository.implementation.CreateData;
import com.mjc.school.repository.implementation.NewsModel;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DataSource {
    private static DataSource dataSource;
    private static final String PATH_OF_AUTHORS = "/Users/DimaHeinz/Desktop/test/stage3-module1-task/module-repository/src/main/resources/author.txt";
    private static final String PATH_OF_CONTENT = "/Users/DimaHeinz/Desktop/test/stage3-module1-task/module-repository/src/main/resources/content.txt";
    private static final String PATH_OF_TITLE = "/Users/DimaHeinz/Desktop/test/stage3-module1-task/module-repository/src/main/resources/title.txt";
    private List<NewsModel> newsModelList;
    private List<AuthorModel> authorModelList;
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
        newsModelList = new ArrayList<>();
        String title;
        String content;

        for (int i = 1; i <= 20; i++) {
            int r = random.nextInt(authorModelList.size());
            LocalDateTime date = CreateData.getInstance().getDateTime();
            title = createData.getReadedData(PATH_OF_TITLE);
            content = createData.getReadedData(PATH_OF_CONTENT);
            newsModelList.add(new NewsModel((long)i, title, content, date, date, authorModelList.get(r).getId()));
        }
    }

    private void initializeAuthors() {
        authorModelList = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            authorModelList.add(new AuthorModel((long)i, createData.getReadedData(PATH_OF_AUTHORS)));
        }
    }
    public List<NewsModel> getNewsList() {
        return newsModelList;
    }

    public List<AuthorModel> getAuthorsList() {
        return authorModelList;
    }
}
