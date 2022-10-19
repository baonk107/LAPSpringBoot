package edu.java.spring.controller;

import edu.java.spring.dao.StudentDAO;
import edu.java.spring.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

@Controller
public class StudentController {

    @Autowired
    private StudentDAO studentDAO;

    @RequestMapping(value = "student/add", method = RequestMethod.GET)
    public ModelAndView add() {
//        ModelAndView mv = new ModelAndView();
//        mv.setViewName("student.form");
//        return mv;
        return new ModelAndView("student.form", "command", new Student());
    }

//    @RequestMapping(value = "student/save", method = RequestMethod.POST)
//    public ModelAndView save(@RequestParam(value = "name", required = false) String name,
//                             @RequestParam(value = "age", required = false) int age) {
//        ModelAndView mv = new ModelAndView();
//        Student student = new Student(name, age);
//        mv.addObject("student", student);
//        mv.setViewName("student.view");
//        return mv;
//    }

    //Validate Data
    @RequestMapping(value = "student/save", method = RequestMethod.POST)
    public ModelAndView save(@Valid @ModelAttribute("command") Student student, BindingResult result) {
        ModelAndView mv;
        //check Error
        if (result.hasErrors()) {
            mv = new ModelAndView("student.form", "command", student);
            mv.addObject("errors", result);
            return mv;
        }
        if (student.getId() > 0) {
            studentDAO.update(student);
        } else {
            studentDAO.insert(student);
        }
//        System.out.println("Insert Successfull");
//        mv.addObject("student", student);
//        mv.setViewName("student.view");
        return new ModelAndView("redirect:/student/list");
    }

    //List Student
    @RequestMapping(value = "/student/list")
    public ModelAndView listStudent(@RequestParam(value = "q", required = false) String query) {
        ModelAndView mv = new ModelAndView();
        System.out.println("Query: " + query);
        if (query == null || query.isEmpty()) {
            mv.addObject("students", studentDAO.list());
        } else {
            mv.addObject("students", studentDAO.listStudentById(query));
        }
        mv.setViewName("student.list");
        return mv;
    }

    //Delete
    @RequestMapping(value = "/student/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable String id) {
        studentDAO.delete(id);
        return "redirect:/student/list";
    }

    //Edit
    @RequestMapping(value = "student/edit/{id}")
    public ModelAndView edit(@PathVariable int id) {
        System.out.println(id);
        Student student = studentDAO.getById(id);
        System.out.println(student);
        return new ModelAndView("../student.form", "command", student);
    }

    //Save Edit
    @RequestMapping(value = "student/edit/save", method = RequestMethod.POST)
    public String saveEdit() {
        return "forward:/student/save";
    }

    //                JSON Demo
    @RequestMapping(value = "/student/json/{id}", method = RequestMethod.GET)
    public @ResponseBody Student viewJson(@PathVariable int id) {
        return studentDAO.getById(id);
    }

    //Demo tiles
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home() {
        return "redirect:/student/list";
    }

    //Upload File
    @RequestMapping(value = "/student/avatar/save", method = RequestMethod.POST)
    public String handleFormUpload(@RequestParam("file") MultipartFile file, int id, HttpServletRequest request) {
        if (file.isEmpty()) return "student.error";
        try {
            byte[] bytes = file.getBytes();
            Path avatarFile = getImageFile(request, id);
            Files.write(avatarFile, bytes, StandardOpenOption.CREATE);
            System.out.println(avatarFile);
            System.out.println("Found ---> " + bytes.length);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return "redirect:/student/list";
    }

    //Get Image
    private Path getImageFile(HttpServletRequest request, int id) {
        ServletContext servletContext = request.getSession().getServletContext();
        String diskPath = servletContext.getRealPath("/");//Return Url của đĩa
        File folder = new File(diskPath + File.separator + "avatar" + File.separator);
        folder.mkdirs();
        return Paths.get(String.valueOf(new File(folder, String.valueOf(id) + ".jpg")));
    }

    //View Avatar
    @RequestMapping("/student/avatar/{id}")
    public ResponseEntity<byte[]> getImage(@PathVariable String  id, HttpServletRequest request) throws IOException {
        ByteArrayOutputStream byteOutput = new ByteArrayOutputStream();
        if(id != null){
            Path avatarPath = getImageFile(request, Integer.parseInt(id));
            if(Files.exists(avatarPath)) byteOutput.write(Files.readAllBytes(avatarPath));
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        return new ResponseEntity<byte[]>(byteOutput.toByteArray(), headers, HttpStatus.CREATED);
    }

}
