package com.mjc.school.controller;

import com.mjc.school.service.*;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;
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
        Long i = input(scanner, "News");
        List<NewsDTO> list = service.getAllNews();
        boolean flag = false;
        for (NewsDTO n : list) {
            if (Objects.equals(n.getId(), i)) {
                print(service.getNewsById(i));
                flag = true;
                break;
            }
        }
        if (!flag) {
            throw new CheckException("ERROR_CODE: 000001 ERROR_MESSAGE: News with id " + i + " does not exist.");
        }
    }

    public void createNews() {
        System.out.println("Operation: Create news.");
        System.out.println("Enter news title:");
        String title = input(scanner);
        System.out.println("Enter news content:");
        String content = input(scanner);
        System.out.println("Enter author id:");
        Long authorId = input(scanner, "Author");
        LocalDateTime date = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        NewsDTO newsDTO = new NewsDTO(0L, title, content, date, date, authorId);
        service.create(newsDTO);
    }

    public void updateNews() {
        System.out.println("Operation: Update news.");
        System.out.println("Enter news id:");
        Long newsIdNumber = input(scanner, "News");
        System.out.println("Enter news title:");
        String newsTitle = input(scanner);
        System.out.println("Enter news content:");
        String newsContent = input(scanner);
        System.out.println("Enter author id:");
        Long authorIdNumber = input(scanner, "Author");
        LocalDateTime date = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        NewsDTO newsDTO = new NewsDTO(newsIdNumber, newsTitle, newsContent, date, date, authorIdNumber);
        service.update(newsDTO);
    }

    public void removeNewsById() {
        System.out.println("Operation: Remove news by id.");
        System.out.println("Enter news id:");
        Long newsIdNumber = input(scanner, "News");
        System.out.println(service.deleteById(newsIdNumber));
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

    private String input(Scanner scanner) {
        String str = scanner.nextLine();
        while (str.equals("")) {
            str = scanner.nextLine();
        }
        return str;
    }

    private Long input(Scanner scanner, String param) {
        String str = scanner.nextLine();
        while (str.equals("")) {
            str = scanner.nextLine();
        }
        try {
            return Long.parseLong(str);
        } catch (NumberFormatException e) {
            throw new CheckException("ERROR_CODE: 000013 ERROR_MESSAGE: " + param + " Id should be number");
        }
    }
}
