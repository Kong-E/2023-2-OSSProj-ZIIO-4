package com.ziio.backend.crawler;

import com.ziio.backend.service.AcademicService;
import com.ziio.backend.service.CategoryService;
import com.ziio.backend.service.ColorService;
import com.ziio.backend.service.NoticeService;
import com.ziio.backend.util.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CrawlerManager {
    private final MainWebsiteCrawler mainWebsiteCrawler;
    private final CollegeAndDepartmentWebsiteCrawler collegeAndDepartmentWebsiteCrawler;
    private final EtcWebsiteCrawler etcWebsiteCrawler;
    private final AcademicCalendarWebsiteCrawler academicCalendarWebsiteCrawler;

    @Autowired
    public CrawlerManager(NoticeService noticeService, AcademicService academicService, CategoryService categoryService,
                          ColorService colorService, RandomUtil randomUtil) {
        this.mainWebsiteCrawler = new MainWebsiteCrawler(noticeService, categoryService);
        this.collegeAndDepartmentWebsiteCrawler = new CollegeAndDepartmentWebsiteCrawler(noticeService, categoryService);
        this.academicCalendarWebsiteCrawler = new AcademicCalendarWebsiteCrawler(academicService, colorService, randomUtil);
        this.etcWebsiteCrawler = new EtcWebsiteCrawler(noticeService, categoryService);
    }

    //@PostConstruct
    public void runAllCrawlers() {
        // 각 크롤러 실행
        mainWebsiteCrawler.crawl();
        collegeAndDepartmentWebsiteCrawler.crawl();
        etcWebsiteCrawler.crawl();
    }
    //@PostConstruct
    // 학사일정 크롤러 실행
    public void runAcademicCrawlers() {
        academicCalendarWebsiteCrawler.crawl();
    }
}
