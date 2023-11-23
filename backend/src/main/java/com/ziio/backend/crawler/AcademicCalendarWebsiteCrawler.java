// 학사일정 웹사이트
package com.ziio.backend.crawler;

import com.ziio.backend.entity.Academic;
import com.ziio.backend.service.AcademicService;
import com.ziio.backend.service.ColorService;
import com.ziio.backend.util.RandomUtil;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class AcademicCalendarWebsiteCrawler {
    private final AcademicService academicService;
    private final ColorService colorService;
    private final RandomUtil randomUtil;

    @Autowired
    public AcademicCalendarWebsiteCrawler(AcademicService academicService, ColorService colorService, RandomUtil randomUtil) {
        this.academicService = academicService;
        this.colorService = colorService;
        this.randomUtil = randomUtil;
    }
    // 크롤링 실행
    public void crawl() {
        getNotice();
    }
    // 크롤링 동작
    private void getNotice() {
        String URL = "https://www.dongguk.edu/schedule/detail?schedule_info_seq=22"; // 크롤링할 웹사이트 URL
        Connection conn = Jsoup.connect(URL); // Jsoup 연결 객체 생성
        try {
            Document document = conn.get(); // HTML 구조를 불러와 할당
            getNoticeInfos(document);   // 기간
        } catch (IOException ignored) {
        }
    }
    private void getNoticeInfos(Document document) {
        Elements boardList = document.select("table.tbl tbody"); // class명이 tbl인 tbody 태그 조회

        for (Element tr : boardList.select("tr")) { // tr 태그 조회
            String date = "";
            String title = "";
            String host_department = "";
            int index = 0;
            for (Element td : tr.select("td")) { // td 태그 조회
                if (index == 0) {
                    date = td.text(); // 기간
                    index++;
                } else if (index == 1) {
                    title = td.ownText().trim(); // 제목
                    host_department = td.select("p").text().trim(); // 주관 부서
                    break;
                }
            }
            // DB 저장 로직
            Academic academic = new Academic();
            // 시작일과 종료일로 구분
            String[] startAndEndDate = date.split("~");

            String startDate = startAndEndDate[0].trim();
            // 종료일이 없으면 null 처리
            String endDate = startAndEndDate.length > 1 ? startAndEndDate[1].trim() : null;
            academic.setStart_date(startDate);
            academic.setEnd_date(endDate);

            academic.setTitle(title); // 제목
            // 제목과 주관부서가 다른 경우 == 주관부서가 있는 경우에만 저장
            if (!title.equals(host_department)) {
                academic.setHost_department(host_department); // 주관부서
            }
            // 색상은 랜덤으로 설정
            String code = colorService.findCodeById(randomUtil.generateRandomNumber());
            academic.setColor_code(code); // 헥스코드로 저장

            academicService.save(academic);
        }
    }
}