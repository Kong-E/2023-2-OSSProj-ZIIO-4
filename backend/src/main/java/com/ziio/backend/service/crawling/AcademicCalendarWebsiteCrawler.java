// 학사일정 웹사이트
package com.ziio.backend.service.crawling;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class AcademicCalendarWebsiteCrawler {
    // 크롤링 실행
    public void crawl() {
        // category_id, category_name
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
            String titleAndhostDepartment = "";
            int index = 0;
            for (Element td : tr.select("td")) { // td 태그 조회
                if (index == 0) {
                    date = td.text(); // 기간
                    index++;
                } else if (index == 1) {
                    titleAndhostDepartment = td.text(); // 제목 및 주관 부서
                    break;
                }
            }
            // DB 저장 로직 구현 예정
            //System.out.println(date + " " + titleAndhostDepartment);

        }
    }
}