package com.example.springpract.controllers;

import com.example.springpract.models.Student;
import com.example.springpract.repo.StudentRepository;
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
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping("/student")
    public String studentMain(Model model) {
        Iterable<Student> students = studentRepository.findAll();
        model.addAttribute("students", students);
        return "studentTable";
    }

    @GetMapping("/student/add")
    public String blogAdd(Model model) {
        return "student-add";
    }

    @PostMapping("/student/add")
    public String blogPostAdd(@RequestParam String name, @RequestParam String address, @RequestParam String phone_num, @RequestParam String email, @RequestParam double avg_academic_performance, Model model) {
        Student student = new Student(name, address, phone_num, email, avg_academic_performance);
        studentRepository.save(student);
        return "redirect:/student";
    }

//       @GetMapping("/student/remove")
//       public String studentRemove(Model model) {
//
//       return "student-remove";
//   }

    @PostMapping("/student")
    public String student_Remove(@RequestParam(value = "id") Long id, Model model) {
        Student student = studentRepository.findById(id).orElseThrow();
        studentRepository.delete(student);

        return "redirect:/student";
    }

   @GetMapping("/student/{id}/edit")
    public String studentEdit(@PathVariable(value = "id") Long id, Model model) {
        if(!studentRepository.existsById(id)){
            return "redirect:/student";
        }
        Optional<Student> student = studentRepository.findById(id);
        ArrayList<Student> res = new ArrayList<>();
        student.ifPresent(res::add);
        model.addAttribute("student", res);
        return "student-edit";
    }


    @PostMapping("/student/{id}/edit")
    public String student_edit(@PathVariable(value = "id") Long id, @RequestParam String name, @RequestParam String address, @RequestParam String phone_num, @RequestParam String email, @RequestParam double avg_academic_performance, Model model) {
        Student student = studentRepository.findById(id).orElseThrow();
        student.setName(name);
        student.setAddress(address);
        student.setPhone_num(phone_num);
        student.setEmail(email);
        student.setAvg_academic_performance(avg_academic_performance);
        studentRepository.save(student);

        return "redirect:/student";
    }
}
