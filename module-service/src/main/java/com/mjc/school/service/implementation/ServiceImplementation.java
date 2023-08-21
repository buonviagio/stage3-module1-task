package com.mjc.school.service.implementation;

import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.repository.Repository;
import com.mjc.school.repository.implementation.NewsRepository;
import com.mjc.school.service.exceptions.CheckException;
import com.mjc.school.service.validator.NewsCheck;
import com.mjc.school.service.NewsMapper;
import com.mjc.school.service.Service;
import com.mjc.school.service.dto.AuthorDto;
import com.mjc.school.service.dto.NewsDto;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;

public class ServiceImplementation implements Service<NewsDto, AuthorDto> {
    private NewsCheck newsCheck = new NewsCheck();
    private Repository<NewsModel> newsRepository = new NewsRepository();
    private NewsMapper newsMapper = NewsMapper.INSTANCE;

    @Override
    public List<NewsDto> readAllNews() {
        return newsMapper.newsListToDTOList(newsRepository.readAllNews());
    }

    @Override
    public NewsDto readById(Long id) {
        checkNewsId(id);
        return this.readAllNews().stream().filter(v -> Objects.equals(v.getId(), id)).findFirst().get();
    }

    @Override
    public NewsDto create(NewsDto newsDTO) {
        newsCheck.checkNews(newsDTO);
        NewsModel newsModel = newsMapper.dtoToNews(newsDTO);
        LocalDateTime time = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        newsModel.setCreateDate(time);
        newsModel.setLastUpdateDate(time);
        newsRepository.create(newsModel);
        return newsMapper.newsToDTO(newsModel);
    }

    @Override
    public NewsDto update(NewsDto newsDTO) {
        checkNewsId(newsDTO.getId());
        newsCheck.checkNews(newsDTO);
        NewsModel newsModel = newsMapper.dtoToNews(newsDTO);
        LocalDateTime date = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        newsModel.setLastUpdateDate(date);
        newsRepository.update(newsModel);
        return newsDTO;
    }

    @Override
    public Boolean deleteById(Long id) {
        List<NewsDto> newsDtoList = readAllNews();
        checkNewsId(id);
        for (int i = 0; i < newsDtoList.size(); i++) {
            if (Objects.equals(newsDtoList.get(i).getId(), id)) {
                newsDtoList.remove(i);
                newsRepository.deleteById(id);
                return true;
            }
        }
        return false;
    }

    private void checkNewsId(Long id) {
        List<NewsDto> newsDtoList = readAllNews();
        boolean flag = false;
        for (NewsDto n : newsDtoList) {
            if (Objects.equals(n.getId(), id)) {
                flag = true;
                break;
            }
        }
        if (!flag) {
            throw new CheckException("ERROR_CODE: 000001 ERROR_MESSAGE: News with id " + id + " does not exist.");
        }
    }
}
