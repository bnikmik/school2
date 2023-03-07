package ru.hogwarts.school.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.HouseService;

import java.util.Collection;
import java.util.stream.Collectors;

@RequestMapping("faculty")
@RestController
public class HouseController {
    HouseService houseService;

    public HouseController(HouseService houseService) {
        this.houseService = houseService;
    }

    @PostMapping
    public ResponseEntity<Faculty> createFaculty(@RequestBody Faculty faculty) {
        Faculty createdFaculty = houseService.createFaculty(faculty);
        return ResponseEntity.ok(createdFaculty);
    }

    @GetMapping("{facultyId}")
    public ResponseEntity<Faculty> getFaculty(@PathVariable Long facultyId) {
        Faculty faculty = houseService.getFaculty(facultyId);
        if (faculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }

    @PutMapping
    public ResponseEntity<Faculty> updateFaculty(@RequestBody Faculty faculty) {
        Faculty updatedFaculty = houseService.updateFaculty(faculty.getId(), faculty);
        return ResponseEntity.ok(updatedFaculty);
    }

    @DeleteMapping("{facultyId}")
    public ResponseEntity<Faculty> deleteFaculty(@PathVariable Long facultyId) {
        Faculty faculty = houseService.deleteFaculty(facultyId);
        if (faculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(faculty);
    }

    @GetMapping("/get-by-color")
    public ResponseEntity<Collection<Faculty>> getAllStudentsByAge(@RequestParam String color) {
        return ResponseEntity.ok(houseService.getAllFaculties().stream()
                .filter(student -> student.getColor().equals(color))
                .collect(Collectors.toList()));
    }
}
