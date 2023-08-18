package com.mjc.school.service;

import com.mjc.school.repository.Author;
import com.mjc.school.repository.News;
import com.mjc.school.repository.Repository;
import com.mjc.school.repository.RepositoryOperations;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class ServiceImplementation implements Service<NewsDTO, AuthorDTO> {
    private NewsCheck newsCheck = new NewsCheck();
    private Repository<News, Author> newsRepository = new RepositoryOperations();
    private NewsMapper newsMapper = NewsMapper.INSTANCE;

    @Override
    public List<NewsDTO> getAllNews() {
        List<NewsDTO> listOfNews = newsMapper.newsListToDTOList(newsRepository.getAllNews());
        /*
        List<AuthorDTO> listOfAutors = this.getAllAuthors();

        for (NewsDTO newsDTO : listOfNews) {
            for (AuthorDTO authorDTO : listOfAutors) {
                int authorId = authorDTO.getId();
                if (newsDTO.getAuthorId() == authorId) {
                    newsCheck.checkAuthor(authorDTO);
                }
            }
            newsCheck.checkNews(newsDTO);
        }

         */
        return listOfNews;
    }

    @Override
    public List<AuthorDTO> getAllAuthors() {
        return newsMapper.authorListToDTOList(newsRepository.getAllAuthors());
    }

    @Override
    public NewsDTO getNewsById(int id) {
        checkNewsId(id);
        return this.getAllNews().stream().filter(v -> v.getId() == id).findFirst().get();
    }

    @Override
    public NewsDTO create(NewsDTO newsDTO) {
        newsCheck.checkNews(newsDTO);
        News news = newsMapper.dtoToNews(newsDTO);
        LocalDateTime time = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        news.setCreateDate(time);
        news.setLastUpdateDate(time);
        newsRepository.create(news);
        return newsMapper.newsToDTO(news);
    }

    @Override
    public NewsDTO update(NewsDTO newsDTO) {
        //List<NewsDTO> listOfNews = getAllNews();

        checkNewsId(newsDTO.getId());
        newsCheck.checkNews(newsDTO);
        News news = newsMapper.dtoToNews(newsDTO);
        LocalDateTime date = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        news.setLastUpdateDate(date);
        newsRepository.update(news);
        return newsDTO;
        //return newsMapper.newsToDTO(news);

    }

    @Override
    public boolean deleteById(int id) {
        List<NewsDTO> newsDTOList = getAllNews();
        checkNewsId(id);
        for (int i = 0; i < newsDTOList.size(); i++) {
            if (newsDTOList.get(i).getId() == id) {
                newsDTOList.remove(i);
                newsRepository.deleteById(id);
                return true;
            }
        }
        return false;
    }

    private void checkNewsId(int id) {
        List<NewsDTO> newsDTOList = getAllNews();
        boolean flag = false;
        for (NewsDTO n : newsDTOList) {
            if (n.getId() == id) {
                flag = true;
            }
        }
        if (!flag) {
            throw new CheckException("ERROR_CODE: 000001 ERROR_MESSAGE: News with id " + id + " does not exist.");
        }
    }

    /*
    public static void main(String[] args) {
        Service<NewsDTO, AuthorDTO> service = new ServiceImplementation();
        LocalDateTime time = LocalDateTime.now();
        NewsDTO newsDTO1 = new NewsDTO(23, "peace", "This is an amazing news", time, time, 3);
        service.create(newsDTO1);
        NewsDTO newsDTO2 = new NewsDTO(14, "NEWNhjjhEW", "NEWSHJJJH shgggd hhhggggs", time, time, 8);
        service.update(newsDTO2);
        service.deleteById(2);
        service.deleteById(21);
        service.deleteById(20);
        service.deleteById(13);

        List<NewsDTO> list = new ServiceImplementation().getAllNews();
        for (NewsDTO n : list) {
            System.out.println(" ID " + n.getId());
            System.out.println(" TITLE " + n.getTitle());
            System.out.println(" CREATE DATE " + n.getCreateDate());
            System.out.println(" UPDATE DATE " + n.getLastUpdateDate());
            System.out.println(" AUTHOR ID " + n.getAuthorId());
            System.out.println(" CONTENT " + n.getContent());
            System.out.println("--------------------------");
        }
    }
     */
}
