package med.voll.api.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class ExceptionErrors {

    @ExceptionHandler(EntityNotFoundException.class)
    ResponseEntity<Void> throwError404() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<List<DadosErrors>> throwError400(MethodArgumentNotValidException exception) {
        var errors = exception.getFieldErrors();
        return ResponseEntity.badRequest().body(
            errors.stream().map(DadosErrors::new).toList()
        );
    }

    @ExceptionHandler(ValidacaoException.class)
    ResponseEntity throwErrorBusinessRole(ValidacaoException exception) {
        return ResponseEntity.badRequest().body(exception.getMessage());
    }
}
