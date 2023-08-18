package com.mjc.school.service;

public class NewsCheck {
    private static NewsCheck newsCheck;
    private static int TITLE_MIN = 5;
    private static int TITLE_MAX = 30;
    private static int CONTENT_FIELD_MIN = 5;
    private static int CONTENT_FIELD_MAX = 255;
    private static int AUTHOR_MIN = 3;
    private static int AUTHOR_MAX = 15;
    private static int AUTHOR_ID = 20;
    public static NewsCheck getInstance(){
        if (newsCheck == null){
            newsCheck = new NewsCheck();
            return newsCheck;
        } else {
            return newsCheck;
        }
    }


    public void checkNews (NewsDTO newsDTO){
        if (newsDTO == null) {
            throw new CheckException("The field is null ");
        } else {
            checkString(newsDTO.getTitle(), TITLE_MIN, TITLE_MAX, "ERROR_CODE: 000012 ERROR_MESSAGE: News title can not be less than 5 and more than 30 symbols. News title is ");
            checkString(newsDTO.getContent(), CONTENT_FIELD_MIN, CONTENT_FIELD_MAX, "ERROR_CODE: 000012 ERROR_MESSAGE: News content can not be less than 5 and more than 255 symbols. News content is");
            checkAuthorId(newsDTO.getAuthorId());
        }
    }

    public void checkAuthor (AuthorDTO authorDTO){
        if (authorDTO == null) {
            throw new CheckException("The field is null ");
        } else {
            checkString(authorDTO.getName(), AUTHOR_MIN, AUTHOR_MAX, "author name");
        }
    }

    private void checkString (String param, int min, int max, String str) {
        if (!(param.length() >= min || param.length() <= max)){
            throw new CheckException(str + param);
        }
    }

    private void checkAuthorId (int id){
        if (id <= 0 || id > AUTHOR_ID){
            throw new CheckException("ERROR_CODE: 000002 ERROR_MESSAGE: Author Id does not exist. Author Id is: " + id);
        }
    }
}
