package com.exam.controller;


import com.exam.model.exam.Category;
import com.exam.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
@CrossOrigin("*")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    //add category
        @PostMapping("/")
        public ResponseEntity<?> addCategroy(@RequestBody Category category){
            Category category1 = this.categoryService.addCategory(category);
            return ResponseEntity.ok(category1);
        }

        //Get Category
//    @GetMapping("/categoryId")
//    public Category getCategoryById(@PathVariable("categoryId") Long categoryId){
//            return  this.categoryService.getCategory(categoryId);
//
//    }
        @GetMapping("/{categoryId}")
        public ResponseEntity<Category> getCategoryById(@PathVariable("categoryId") Long categoryId) {
            Category category = this.categoryService.getCategory(categoryId);

            if (category != null) {
                return new ResponseEntity<>(category, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }

        // get all category
    @GetMapping("/")
    public ResponseEntity<?> getCategories(){
            return  ResponseEntity.ok(this.categoryService.getCategoriess());
    }


    //update category
    @PutMapping("")
    public  Category updateCategory(@RequestBody Category category){
            return this.categoryService.updateCategory(category);
    }

    //delete Category
    @DeleteMapping("/{categoryId}")
    public void deleteCategory(@PathVariable("categoryId") Long categoryId){
            this.categoryService.deleteCategory(categoryId);
    }
}
