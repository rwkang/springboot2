package shop.onekorea.springboot2.service;

import org.springframework.stereotype.Service;
import shop.onekorea.springboot2.schedule.ScheduleTasks;

import java.util.List;

@Service
public class HelloService {

    private ScheduleTasks scheduleTasks;

    public HelloService(ScheduleTasks scheduleTasks) {
        this.scheduleTasks = scheduleTasks;
    }

    public String printHello() {
    // public void printHello() {
        System.out.println("Hello");
        return "Hello";
    }

    public List<String> scheduleTasks() {
        List<String> tasks = scheduleTasks.getTasks();
        return tasks;
    }

}
