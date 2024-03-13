/**
 * 2024.02.01 Added. Schedule Tasks
 * https://www.youtube.com/watch?v=ZMMqAMe6QP8
 */

package shop.onekorea.springboot2.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static shop.onekorea.springboot2.Springboot2Application.getInfo;

@Component
public class ScheduleTasks {

    private static final Logger logger = LoggerFactory.getLogger(ScheduleTasks.class);
    private static final SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private int subscriber = 0;

    /**
     * 2024.02.01 Conclusions by rwkang.
     * @Scheduled(fixedRate = 1000) : 현재 작업이 완료된 후, 세팅된 시간 만큼 쉬고 다시 시작
     * @Scheduled(fixedDelay = 1000) : 현재 작업 진행과 상관 없이, 설정된 시간 마다 다시 시작
     * @Scheduled(initialDelay = 1000) : 작업 시작 전 설정된 시간 만큼 쉬고 다시 시작
     * @Scheduled(cron = "S M H D M W (Y)") : 작업 시작 전 설정된 항목 시간 만큼 쉬고 다시 시작. (S:0-59초 M:0-59분 H:0-23시간 D:1-31일 월: 1-12 W:0-6요일 Y: 년(옵션))
     * Ranges and lists: Use - for ranges (e.g., 10-15 for 10 AM to 3 PM) and , for lists (e.g., MON,WED,FRI for Mondays, Wednesdays, and Fridays).
     * Asterisk (*): Represents "every" for a field.
     * Question mark (?): Represents "no specific value" for a field, typically used for Day of Month or Day of Week.
     * ex1) @Scheduled(cron = "1 0 * * * *") : 1초 마다 실행: 거의 안 씀.
     * ex1) @Scheduled(cron = "0 1 * * * *") : 1분 마다 실행: 거의 안 씀.
     * ex1) @Scheduled(cron = "0 0 14 * * *") : 매년 매월 매일 14시 마다 실행
     * ex1) @Scheduled(cron = "0 0 16 31 * *") : 매년 매월 31일 16시 마다 실행
     * ex1) @Scheduled(cron = "0 0 23 29 12 *") : 매년 12월 29일 23시 한번만 실행
     * ex1) @Scheduled(cron = "0 0 23 ? * SUN") : 매년 매월 일요일 23시 마다 실행
     */

    @Scheduled(fixedRate = 1000, initialDelay = 2) // 2/1000초 쉬었다가 실행.
    public void scheduleExecuteType() {
        subscriber++;
//        System.out.println(getInfo() + " subscriber: " + subscriber); // 1초
        if (subscriber % 10 == 0) { // 10초 단위로 찍게 한다.
            System.out.println(getInfo() + " task\\ScheduleTasks.scheduleExecuteType() \u001B[34m실행 중 입니다.\u001B[0m " + subscriber);
        }
    }

    @Scheduled(fixedRate = 50000, initialDelay = 1) // 1/1000초 쉬었다가 실행, 즉 이것을 위의 scheduleExecuteType() 보다 반드시 먼저 실행하게 해야 할 때.
//    @Scheduled(fixedRate = 5000, initialDelay = 1) // 1/1000초 쉬었다가 실행, 즉 이것을 위의 scheduleExecuteType() 보다 반드시 먼저 실행하게 해야 할 때.
    public void scheduleCurrentTime() {
        System.out.println(getInfo() + " \u001B[34m현재 구독자: " + subscriber + ", [현재 시각: " + sDateFormat.format(new Date()) + "]\u001B[0m");
    }

    @Scheduled(fixedRate = 5000, initialDelay = 1) // to return.
    public List<String> getTasks() {
        List<String> tasks = new ArrayList<>();
        String currentTime = sDateFormat.format(new Date());

        tasks.add(String.valueOf(subscriber));
        tasks.add(currentTime);

        return tasks;

    }


}
