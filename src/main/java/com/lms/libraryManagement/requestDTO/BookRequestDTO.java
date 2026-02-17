package com.lms.libraryManagement.requestDTO;
import lombok.Data;

import java.time.LocalDate;

@Data
public class BookRequestDTO {
    private String title;
    private String publisherName;
    private LocalDate publishedYear;
    private String language;
    private int pages;
    private boolean isAvaliable;
}
