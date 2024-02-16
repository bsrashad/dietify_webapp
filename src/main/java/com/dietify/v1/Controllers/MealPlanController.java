// package com.recipe.project.Controllers;

// import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RestController;

// import com.recipe.project.Entity.MealPlan;
// import com.recipe.project.Repository.MealPlanRepository;

// @RestController
// public class MealPlanController {

//     @Autowired
//     private MealPlanRepository mealPlanRepository;

//     @PostMapping("/meal-plans/save/{userId}")
//     public ResponseEntity<?> saveMealPlan(@PathVariable String userId, @RequestBody MealPlan mealPlan) {
//         mealPlan.setUserId(userId); // Set the userId for the meal plan
//         mealPlanRepository.save(mealPlan);
//         return ResponseEntity.ok("Meal plan saved successfully");
//     }

//     @GetMapping("/meal-plans/{userId}")
//     public ResponseEntity<List<MealPlan>> getMealPlansByUserId(@PathVariable String userId) {
//         List<MealPlan> mealPlans = mealPlanRepository.findByUserId(userId);
//         return ResponseEntity.ok(mealPlans);
//     }
// }
