package com.project.expensetracker.controller;
import com.project.expensetracker.dto.ExpenseDTO;
import com.project.expensetracker.Services.expense.ExpenseService;
import com.project.expensetracker.entity.Expense;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/expense")
@RequiredArgsConstructor
@CrossOrigin("*")


public class ExpenseController {
    private final ExpenseService expenseService;
    @PostMapping
    public ResponseEntity<?> postExpense(@RequestBody ExpenseDTO dto) {
        Expense createdExpense=expenseService.postExpense(dto);
        if(createdExpense!=null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(createdExpense);

        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

    }
    @GetMapping("/all")
    public ResponseEntity<?> getAllExpenses() {
        return ResponseEntity.ok(expenseService.getAllExpenses());
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getExpenseById(@PathVariable Long id) {
        try{
            return ResponseEntity.ok(expenseService.getExpenseById(id));
        }
        catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
        catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("something went wrong");
        }

    }
    @PutMapping("/{id}")

    public ResponseEntity<?> updateExpense(@PathVariable Long id,@RequestBody ExpenseDTO dto) {
        try{
            return ResponseEntity.ok(expenseService.updateExpense(id, dto));
        }
        catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
        catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("something went wrong");
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteExpense(@PathVariable Long id) {
        try{
            expenseService.deleteExpense(id);
            return ResponseEntity.ok(null);
        }
        catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }
        catch(Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("something went wrong");
        }
    }
}
