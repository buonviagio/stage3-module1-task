package com.mjc.school.service;

import com.mjc.school.repository.model.AuthorModel;
import com.mjc.school.repository.model.NewsModel;
import com.mjc.school.service.dto.AuthorDto;
import com.mjc.school.service.dto.NewsDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface NewsMapper {
    NewsMapper INSTANCE = Mappers.getMapper(NewsMapper.class);

    List<NewsDto> newsListToDTOList (List<NewsModel> newsModelList);
    List<AuthorDto> authorListToDTOList (List<AuthorModel> authorModels);
    NewsDto newsToDTO (NewsModel newsModel);
    NewsModel dtoToNews (NewsDto newsFromDTO);


}
