package com.mjc.school.service;

import com.mjc.school.repository.implementation.NewsModel;
import com.mjc.school.repository.implementation.Repository;
import com.mjc.school.repository.implementation.NewsRepository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;

public class ServiceImplementation implements Service<NewsDTO, AuthorDTO> {
    private NewsCheck newsCheck = new NewsCheck();
    private Repository<NewsModel> newsRepository = new NewsRepository();
    private NewsMapper newsMapper = NewsMapper.INSTANCE;

    @Override
    public List<NewsDTO> getAllNews() {
        return newsMapper.newsListToDTOList(newsRepository.readAllNews());
    }

    @Override
    public NewsDTO getNewsById(Long id) {
        checkNewsId(id);
        return this.getAllNews().stream().filter(v -> Objects.equals(v.getId(), id)).findFirst().get();
    }

    @Override
    public NewsDTO create(NewsDTO newsDTO) {
        newsCheck.checkNews(newsDTO);
        NewsModel newsModel = newsMapper.dtoToNews(newsDTO);
        LocalDateTime time = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        newsModel.setCreateDate(time);
        newsModel.setLastUpdateDate(time);
        newsRepository.create(newsModel);
        return newsMapper.newsToDTO(newsModel);
    }

    @Override
    public NewsDTO update(NewsDTO newsDTO) {
        checkNewsId(newsDTO.getId());
        newsCheck.checkNews(newsDTO);
        NewsModel newsModel = newsMapper.dtoToNews(newsDTO);
        LocalDateTime date = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        newsModel.setLastUpdateDate(date);
        newsRepository.update(newsModel);
        return newsDTO;
    }

    @Override
    public boolean deleteById(Long id) {
        List<NewsDTO> newsDTOList = getAllNews();
        checkNewsId(id);
        for (int i = 0; i < newsDTOList.size(); i++) {
            if (Objects.equals(newsDTOList.get(i).getId(), id)) {
                newsDTOList.remove(i);
                newsRepository.deleteById(id);
                return true;
            }
        }
        return false;
    }

    private void checkNewsId(Long id) {
        List<NewsDTO> newsDTOList = getAllNews();
        boolean flag = false;
        for (NewsDTO n : newsDTOList) {
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
