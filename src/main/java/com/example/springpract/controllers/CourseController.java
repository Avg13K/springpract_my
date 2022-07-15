package com.example.springpract.controllers;

import com.example.springpract.models.Course;
import com.example.springpract.models.Professor;
import com.example.springpract.repo.CourseRepository;
import com.example.springpract.repo.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class CourseController {
    @Autowired
    private CourseRepository courseRepository;

    @GetMapping("/course")
    public String courseMain(Model model) {
        Iterable<Course> courses = courseRepository.findAll();
        model.addAttribute("courses", courses);
        return "courseTable";
    }



    @GetMapping("/course/add")
    public String courseAdd(Model model) {
        return "course-add";
    }

    @PostMapping("/course/add")
    public String Course_Add(@RequestParam String name, @RequestParam String professors_name,@RequestParam int num, @RequestParam double cost, Model model) {
        Course course = new Course(name, professors_name, num, cost);
        courseRepository.save(course);
        return "redirect:/course";
    }

    @PostMapping("/course")
    public String course_Remove(@RequestParam(value = "id") Long id, Model model) {
        Course course= courseRepository.findById(id).orElseThrow();
        courseRepository.delete(course);

        return "redirect:/course";
    }

    @GetMapping("/course/{id}/edit")
    public String courseEdit(@PathVariable(value = "id") Long id, Model model) {
        if(!courseRepository.existsById(id)){
            return "redirect:/course";
        }
        Optional<Course> course = courseRepository.findById(id);
        ArrayList<Course> res = new ArrayList<>();
        course.ifPresent(res::add);
        model.addAttribute("course", res);
        return "course-edit";
    }


    @PostMapping("/course/{id}/edit")
    public String course_edit(@PathVariable(value = "id") Long id, @RequestParam String name, @RequestParam String professors_name,@RequestParam int num, @RequestParam double cost, Model model) {
        Course course = courseRepository.findById(id).orElseThrow();
        course.setName(name);
        course.setProfessors_name(professors_name);
        course.setNum(num);
        course.setCost(cost);
        courseRepository.save(course);

        return "redirect:/course";
    }
}
