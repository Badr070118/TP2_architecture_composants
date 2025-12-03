package ma.formations.rest.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import ma.formations.rest.domaine.ArticleDTO;
import ma.formations.rest.service.IService;

@RestController
@RequestMapping("/api/articles")
@AllArgsConstructor
@Validated
@CrossOrigin(origins = "*")
public class ArticleController {

    private final IService service;

    @GetMapping(value = "/all", produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public List<ArticleDTO> getAll() {
        return service.getAll();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Object> getByPathId(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping
    public ResponseEntity<Object> getByQueryId(@RequestParam Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping("/create")
    public ResponseEntity<Object> create(@Valid @RequestBody ArticleDTO dto) {
        service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Article is created successfully");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody ArticleDTO dto) {
        service.update(id, dto);
        return ResponseEntity.ok("Article is updated successfully");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.ok("Article is deleted successfully");
    }
}
