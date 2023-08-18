package com.mjc.school.service;

import com.mjc.school.repository.Author;
import com.mjc.school.repository.News;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface NewsMapper {
    NewsMapper INSTANCE = Mappers.getMapper(NewsMapper.class);

    List<NewsDTO> newsListToDTOList (List<News> newsList);
    List<AuthorDTO> authorListToDTOList (List<Author> authors);
    NewsDTO newsToDTO (News news);
    News dtoToNews (NewsDTO newsFromDTO);


}
