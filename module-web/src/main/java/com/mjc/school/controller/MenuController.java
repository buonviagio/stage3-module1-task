package com.mjc.school.controller;

import com.mjc.school.service.AuthorDTO;
import com.mjc.school.service.NewsDTO;
import com.mjc.school.service.Service;
import com.mjc.school.service.ServiceImplementation;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Scanner;


public class MenuController {
    String[] arrayOfOperations = {
            "1 - Get all news.", "2 - Get news by id.", "3 - Create news.", "4 - Update news.", "5 - Remove news by id.", "0 - Exit."
    };
    private Service<NewsDTO, AuthorDTO> service = new ServiceImplementation();
    private Scanner scanner = new Scanner(System.in);

    public void printMenu() {
        System.out.println("Enter the number of operation:");
        for (String str : arrayOfOperations) {
            System.out.println(str);
        }
    }

    public void getAllMenu() {
        List<NewsDTO> list = service.getAllNews();
        for (NewsDTO newsDTO : list) {
            print(newsDTO);
        }
    }

    public void getNewsById() {
        System.out.println("Operation: Get news by id.");
        System.out.println("Enter news id:");
        String str = scanner.nextLine();
        List<NewsDTO> list = service.getAllNews();
        boolean flag = false;
        if (checkNumber(str)) {
            int i = Integer.parseInt(str);
            for (NewsDTO n : list) {
                if (n.getId() == i) {
                    print(service.getNewsById(i));
                    flag = true;
                }
            }
            if (!flag) {
                System.out.println("ERROR_CODE: 000001 ERROR_MESSAGE: News with id " + i + " does not exist.");
            }
        } else {
            System.out.println("ERROR_CODE: 000013 ERROR_MESSAGE: News Id should be number");
        }
    }

    public void createNews() {
        System.out.println("Operation: Create news.");
        System.out.println("Enter news title:");
        String title = scanner.nextLine();
        System.out.println("Enter news content:");
        String content = scanner.nextLine();
        System.out.println("Enter author id:");
        String authorId = scanner.nextLine();
        int id;
        if (checkNumber(authorId)) {
            id = Integer.parseInt(authorId);
        } else {
            System.out.println("ERROR_CODE: 000013 ERROR_MESSAGE: Author Id should be number");
            return;
        }
        LocalDateTime date = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        NewsDTO newsDTO = new NewsDTO(0, title, content, date, date, id);
        service.create(newsDTO);
    }

    public void updateNews() {
        System.out.println("Operation: Update news.");
        System.out.println("Enter news id:");
        String newsId = scanner.nextLine();
        int newsIdNumber = 0;
        if (checkNumber(newsId)) {
            newsIdNumber = Integer.parseInt(newsId);
        } else {
            System.out.println("ERROR_CODE: 000013 ERROR_MESSAGE: News Id should be number");
        }
        System.out.println("Enter news title:");
        String newsTitle = scanner.nextLine();
        System.out.println("Enter news content:");
        String newsContent = scanner.nextLine();
        System.out.println("Enter author id:");
        String authorId = scanner.nextLine();
        int authorIdNumber = 0;
        if (checkNumber(authorId)) {
            authorIdNumber = Integer.parseInt(authorId);
        } else {
            System.out.println("ERROR_CODE: 000013 ERROR_MESSAGE: Author Id should be number");
            return;
        }
        LocalDateTime date = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        //i have to add IdAuthor Check
        NewsDTO newsDTO = new NewsDTO(newsIdNumber, newsTitle, newsContent, date, date, authorIdNumber);
        service.update(newsDTO);
    }

    public void removeNewsById() {
        System.out.println("removeNewsById");
    }

    private void print(NewsDTO n) {
        System.out.println("NewsDtoResponse [id=" + n.getId() + ", title=" + n.getTitle() + ", content=" +
                n.getContent() + ", createDate=" + n.getCreateDate() + ", lastUpdatedDate=" +
                n.getLastUpdateDate() + ", authorId=" + n.getAuthorId() + "]");
    }

    private boolean checkNumber(String string) {
        try {
            Integer.parseInt(string);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
