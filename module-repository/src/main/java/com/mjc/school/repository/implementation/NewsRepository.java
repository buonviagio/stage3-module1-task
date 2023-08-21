package com.mjc.school.repository.implementation;

import com.mjc.school.repository.Repository;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.repository.utils.DataSource;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class NewsRepository implements Repository<NewsModel> {
    private final DataSource dataSource;
    public NewsRepository() {
        dataSource = DataSource.getData();
    }


    @Override
    public List<NewsModel> readAllNews() {
        return dataSource.getAllNews();
    }

    @Override
    public NewsModel readById(Long id) {
        return dataSource.getAllNews().stream().filter(n -> Objects.equals(n.getId(), id)).findFirst().get();
    }

    @Override
    public NewsModel create(NewsModel newsModel) {
        List<NewsModel> list = dataSource.getAllNews();
        if (list.size() == 0) {
            newsModel.setId(1L);
            list.add(newsModel);
            return newsModel;
        } else {
            newsModel.setId(list.size() + 1L);
            list.sort(Comparator.comparing(NewsModel::getId));
            list.add(newsModel);
            return newsModel;
        }
    }

    @Override
    public NewsModel update(NewsModel n) {
        NewsModel newsModel = readById(n.getId());
        newsModel.setTitle(n.getTitle());
        newsModel.setContent(n.getContent());
        newsModel.setAuthorId(n.getAuthorId());
        newsModel.setLastUpdateDate(n.getLastUpdateDate());
        return newsModel;
    }

    @Override
    public Boolean deleteById(Long id) {
        List<NewsModel> list = dataSource.getAllNews();
        for (int i = 0; i < list.size(); i++) {
            if (Objects.equals(list.get(i).getId(), id)) {
                list.remove(i);
                return true;
            }
        }
        return false;
    }
}
