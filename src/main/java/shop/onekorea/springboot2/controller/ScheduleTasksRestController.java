/**
 * 2024.02.01 Added. Schedule Tasks
 * https://www.youtube.com/watch?v=ZMMqAMe6QP8
 */

package shop.onekorea.springboot2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import shop.onekorea.springboot2.service.schedule.ScheduleTasksService;

import java.util.List;

@RestController
public class ScheduleTasksRestController {

    ScheduleTasksService scheduleTasksService;

    // 최근 방식: 생성자 사용
    public ScheduleTasksRestController (ScheduleTasksService scheduleTasksService) {
        this.scheduleTasksService = scheduleTasksService;
    }

    @GetMapping("/tasks")
    public List<String> getScheduleTasks() {
        List<String> scheduleTasks = scheduleTasksService.getScheduleTasks();

        return scheduleTasks;
    }

}
