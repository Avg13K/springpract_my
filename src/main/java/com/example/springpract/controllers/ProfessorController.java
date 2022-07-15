package com.example.springpract.controllers;

import com.example.springpract.exporter.ExcelExporter;
import com.example.springpract.models.Professor;
import com.example.springpract.models.Student;
import com.example.springpract.repo.ProfessorRepository;
import com.example.springpract.repo.StudentRepository;
import com.example.springpract.services.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class ProfessorController {

    @Autowired
    private ProfessorRepository professorRepository;

    @GetMapping("/professor")
    public String professorMain(Model model) {
        Iterable<Professor> professors = professorRepository.findAll();
        model.addAttribute("professors", professors);
        return "professorTable";
    }

    @GetMapping("/professor/add")
    public String professorAdd(Model model) {
        return "professor-add";
    }

    @PostMapping("/professor/add")
    public String Professor_Add(@RequestParam String name, @RequestParam String address, @RequestParam String phone_num, double payment, Model model) {
        Professor professor = new Professor(name, address, phone_num, payment);
        professorRepository.save(professor);
        return "redirect:/professor";
    }

    @PostMapping("/professor")
    public String professor_Remove(@RequestParam(value = "id") Long id, Model model) {
        Professor professor= professorRepository.findById(id).orElseThrow();
        professorRepository.delete(professor);

        return "redirect:/professor";
    }

    @GetMapping("/professor/{id}/edit")
    public String professorEdit(@PathVariable(value = "id") Long id, Model model) {
        if(!professorRepository.existsById(id)){
            return "redirect:/professor";
        }
        Optional<Professor> professor = professorRepository.findById(id);
        ArrayList<Professor> res = new ArrayList<>();
        professor.ifPresent(res::add);
        model.addAttribute("professor", res);
        return "professor-edit";
    }


    @PostMapping("/professor/{id}/edit")
    public String professor_edit(@PathVariable(value = "id") Long id, @RequestParam String name, @RequestParam String address, @RequestParam String phone_num, @RequestParam double payment, Model model) {
        Professor professor = professorRepository.findById(id).orElseThrow();
        professor.setName(name);
        professor.setAddress(address);
        professor.setPhone_num(phone_num);
        professor.setPayment(payment);
        professorRepository.save(professor);

        return "redirect:/professor";
    }

    @Autowired
    private ExcelService service;


    @GetMapping("/professor/export")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<Professor> listUsers = service.listAll();

        ExcelExporter excelExporter = new ExcelExporter(listUsers);

        excelExporter.export(response);
    }
}
