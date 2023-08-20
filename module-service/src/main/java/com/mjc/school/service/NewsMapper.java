package com.mjc.school.service;

import com.mjc.school.repository.implementation.AuthorModel;
import com.mjc.school.repository.implementation.NewsModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface NewsMapper {
    NewsMapper INSTANCE = Mappers.getMapper(NewsMapper.class);

    List<NewsDTO> newsListToDTOList (List<NewsModel> newsModelList);
    List<AuthorDTO> authorListToDTOList (List<AuthorModel> authorModels);
    NewsDTO newsToDTO (NewsModel newsModel);
    NewsModel dtoToNews (NewsDTO newsFromDTO);


}
