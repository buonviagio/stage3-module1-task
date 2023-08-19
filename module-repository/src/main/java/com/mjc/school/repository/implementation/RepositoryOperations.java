package com.mjc.school.repository.implementation;

import com.mjc.school.repository.implementation.utils.DataSource;
import java.util.Comparator;
import java.util.List;

public class RepositoryOperations implements Repository<ModelNews, ModelAuthor> {
    public RepositoryOperations() {
    }
    private final DataSource dataSource = DataSource.getData();

    @Override
    public List<ModelNews> readAllNews() {
        return dataSource.getNewsList();
    }

    @Override
    public List<ModelAuthor> getAllAuthors() {
        return dataSource.getAuthorsList();
    }

    @Override
    public ModelNews readById(int id) {
        ModelNews modelNews = dataSource.getNewsList().stream().filter(n -> n.getId() == id).findFirst().get();
        return modelNews;
    }

    @Override
    public ModelNews create(ModelNews modelNews) {
        List<ModelNews> list = dataSource.getNewsList();
        if (list.size() == 0) {
            modelNews.setId(1);
            list.add(modelNews);
            return modelNews;
        } else {
            modelNews.setId(list.size() + 1);
            list.sort(Comparator.comparing(ModelNews::getId));
            //list.add(list.size(), news);
            list.add(modelNews);
            return modelNews;
        }
    }

    @Override
    public ModelNews update(ModelNews n) {
        ModelNews modelNews = readById(n.getId());
        modelNews.setTitle(n.getTitle());
        modelNews.setContent(n.getContent());
        modelNews.setAuthorId(n.getAuthorId());
        modelNews.setLastUpdateDate(n.getLastUpdateDate());
        return modelNews;
    }

    @Override
    public Boolean deleteById(int id) {
        List<ModelNews> list = dataSource.getNewsList();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId() == id) {
                list.remove(i);
                return true;
            }
        }
        return false;
    }
}
