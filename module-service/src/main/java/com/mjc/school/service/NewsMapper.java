package com.mjc.school.service;

import com.mjc.school.repository.implementation.ModelAuthor;
import com.mjc.school.repository.implementation.ModelNews;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface NewsMapper {
    NewsMapper INSTANCE = Mappers.getMapper(NewsMapper.class);

    List<NewsDTO> newsListToDTOList (List<ModelNews> modelNewsList);
    List<AuthorDTO> authorListToDTOList (List<ModelAuthor> modelAuthors);
    NewsDTO newsToDTO (ModelNews modelNews);
    ModelNews dtoToNews (NewsDTO newsFromDTO);


}
