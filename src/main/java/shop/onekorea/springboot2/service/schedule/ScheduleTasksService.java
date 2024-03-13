/**
 * 2024.02.01 Added. Schedule Tasks
 * https://www.youtube.com/watch?v=ZMMqAMe6QP8
 */

package shop.onekorea.springboot2.service.schedule;

import org.springframework.stereotype.Service;
import shop.onekorea.springboot2.schedule.ScheduleTasks;

import java.util.List;

@Service
public class ScheduleTasksService {

    private ScheduleTasks scheduleTasks;

    public ScheduleTasksService(ScheduleTasks scheduleTasks) {
        this.scheduleTasks = scheduleTasks;
    }

    public List<String> getScheduleTasks() {
        List<String> tasks = scheduleTasks.getTasks();
        return tasks;
    }

}
