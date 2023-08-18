package com.mjc.school.repository;

import java.util.Comparator;
import java.util.List;

public class RepositoryOperations implements Repository<News, Author> {
    private DataSource dataSource = DataSource.getData();

    @Override
    public List<News> getAllNews() {
        return dataSource.getNewsList();
    }

    @Override
    public List<Author> getAllAuthors() {
        return dataSource.getAuthorsList();
    }

    @Override
    public News getNewsById(int id) {
        News news = dataSource.getNewsList().stream().filter(n -> n.getId() == id).findFirst().get();
        return news;
    }

    @Override
    public News create(News news) {
        List<News> list = dataSource.getNewsList();
        if (list.size() == 0) {
            news.setId(1);
            list.add(news);
            return news;
        } else {
            news.setId(list.size() + 1);
            list.sort(Comparator.comparing(News::getId));
            //list.add(list.size(), news);
            list.add(news);
            return news;
        }
    }

    @Override
    public News update(News n) {
        News news = getNewsById(n.getId());
        news.setTitle(n.getTitle());
        news.setContent(n.getContent());
        news.setAuthorId(n.getAuthorId());
        news.setLastUpdateDate(n.getLastUpdateDate());
        return news;
    }

    @Override
    public boolean deleteById(int id) {
        List<News> list = dataSource.getNewsList();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId() == id) {
                list.remove(i);
                return true;
            }
        }
        return false;
    }
}
